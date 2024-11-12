package org.firstinspires.ftc.teamcode.opMode.TeleOp;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.SequenceCommands.INITsequence;
import org.firstinspires.ftc.teamcode.SequenceCommands.IntakeSequence;
import org.firstinspires.ftc.teamcode.SequenceCommands.OutakeLowBuck;
import org.firstinspires.ftc.teamcode.SequenceCommands.OutakeSequenceHighBuck;
import org.firstinspires.ftc.teamcode.SequenceCommands.ResetAction;
import org.firstinspires.ftc.teamcode.SequenceCommands.SampleDropSeq;
import org.firstinspires.ftc.teamcode.SequenceCommands.SpecimenDropFinal;
import org.firstinspires.ftc.teamcode.SequenceCommands.SpecimenIntake;
import org.firstinspires.ftc.teamcode.SequenceCommands.SpecimenIntakeLowPost;
import org.firstinspires.ftc.teamcode.SequenceCommands.SpecimenIntakePost;
import org.firstinspires.ftc.teamcode.SequenceCommands.TransferSequence;
import org.firstinspires.ftc.teamcode.hardware.Globals;
import org.firstinspires.ftc.teamcode.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lifter;

import java.util.ArrayList;
import java.util.List;


@TeleOp
public class TeleOpTest extends LinearOpMode {
    MecanumDrive drive;
    RobotHardware robot = RobotHardware.getInstance();
    Intake intake;
    Lifter lifter;
    float hsvValues[] = {0F,0F,0F};

    private List<Action> runningActions = new ArrayList<>();
    String [] BucketState = {"HIGH"};
    String [] SpecimenState = {"HIGH"};
    String[] horizontalExtensionStateArray = {"INIT"};
    String[] lifterStateArray = {"INIT"};
    //TODO FLAGS
    public boolean extensionFlag = false;
    public boolean sliderFlag = false;
    public boolean flapperFlag = false;
    public boolean specimenIntakePosFlag = false;

    public TeleOpTest(RobotHardware robot) {
        this.robot = robot;
    }
    private static final double DISTANCE_THRESHOLD_MM = 7;
    @Override
    public void runOpMode() throws InterruptedException {

        drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        lifter = new Lifter(robot);
        intake = new Intake(robot);
        lifter = new Lifter(robot);
         double colour = robot.colourSensor.hashCode();
        waitForStart();

        while (opModeInInit()){
            runningActions.add(INITsequence.initSequence(intake,lifter));
        }
        while (opModeIsActive()) {
            double x = gamepad1.left_stick_x;
            double y = -gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;
            drive.setDrivePowers(new PoseVelocity2d(new Vector2d(x, y), turn));
            drive.updatePoseEstimate();
            if (gamepad1.left_trigger>0.4){
                drive.setDrivePowers(new PoseVelocity2d(new Vector2d(x/2, y/2), turn/2));
                drive.updatePoseEstimate();
            }

        // TODO INTAKE SEQUENCE
            if (gamepad1.start){
                runningActions.add(ResetAction.resetAction(intake,lifter));
            }
            if (gamepad1.x){
                runningActions.add(IntakeSequence.intakeSequence(intake,lifter));
                extensionFlag = true;

            }
            if ( robot.beamBreaker.getState() == true && !flapperFlag){
                runningActions.add(TransferSequence.transferSequence(intake,lifter));
                extensionFlag = false;
            }
            if (gamepad1.right_trigger>0.5){
                runningActions.add(SpecimenIntake.specimenIntakePre(intake,lifter));
                specimenIntakePosFlag= true;
            }
            if (distanceDetection() && specimenIntakePosFlag&&SpecimenState[0].equals("HIGH")){
                runningActions.add(SpecimenIntakePost.specimenIntakePost(intake,lifter));
                sliderFlag = true;
                SpecimenState[0]="HIGH";
                lifterStateArray[0]="HIGHBUCK";
            }
            else if (gamepad1.left_stick_button && specimenIntakePosFlag&&SpecimenState[0].equals("HIGH")){
                runningActions.add(SpecimenIntakePost.specimenIntakePost(intake,lifter));
                sliderFlag = true;
                SpecimenState[0]="HIGH";
                lifterStateArray[0]="HIGHBUCK";
            }

            if (distanceDetection() && specimenIntakePosFlag&&SpecimenState[0].equals("LOW")){
                runningActions.add(SpecimenIntakeLowPost.specimenIntakeLowPost(intake,lifter));
                sliderFlag = true;
                SpecimenState[0]="HIGH";
                lifterStateArray[0]="LOWBUCK";
            }
            if(gamepad1.x){
                runningActions.add(SpecimenDropFinal.specimenDropFinal(intake,lifter));
                lifterStateArray[0]="INIT";
            }
            if (detectColor()=="red"){
                flapperFlag = true;
                runningActions.add(
                        new SequentialAction(
                                intake.flapperState(Intake.flapperState.OPEN),
                                new SleepAction(0.5),
                                intake.flapperState(Intake.flapperState.CLOSE)
                        )
                );
            }
            if (gamepad1.left_bumper && horizontalExtensionStateArray[0].equals("INIT")){
                runningActions.add(
                        new InstantAction(()->intake.horizontalExtensionIntakeState(Intake.extensionState.EXTEND1))
                );
                extensionFlag = true;
                horizontalExtensionStateArray[0] = "EXTEND2";
            }

            if(gamepad1.left_bumper&& horizontalExtensionStateArray[0].equals("EXTEND2")) {
                runningActions.add(
                        new InstantAction(()->intake.horizontalExtensionIntakeState(Intake.extensionState.EXTEND2))
                );
                extensionFlag = true;
                horizontalExtensionStateArray[0] = "EXTEND3";
            }
            if(gamepad1.left_bumper&& horizontalExtensionStateArray[0].equals("EXTEND3")) {
                runningActions.add(
                        new InstantAction(()->intake.horizontalExtensionIntakeState(Intake.extensionState.EXTEND3))
                );
                extensionFlag = true;

            }
            if (gamepad1.b){
                runningActions.add(
                        new InstantAction(()->intake.activeIntakeState(Intake.ActiveIntakeState.REVERSE))
                );
            }


        //TODO OUTAKE SEQUENCE
            if (gamepad1.right_bumper && !sliderFlag && BucketState[0].equals("HIGH")){
                runningActions.add(OutakeSequenceHighBuck.outakeSequenceHighBuck(intake,lifter));
                sliderFlag = true;
                BucketState[0] ="HIGH";
                lifterStateArray[0]="HIGHBUCK";
            }
            if (gamepad1.right_bumper && !sliderFlag && BucketState[0].equals("LOW")){
                runningActions.add(OutakeLowBuck.outakeSequenceLowBuck(intake,lifter));
                sliderFlag = true;
                BucketState[0]="HIGH";
                lifterStateArray[0]="LOWBUCK";
            }
            if (gamepad1.y && sliderFlag){
                runningActions.add(SampleDropSeq.sampleDrop(intake,lifter));
                sliderFlag = false;
                lifterStateArray[0]="INIT";

            }
        //TODO HANGING
        //TODO OPERATOR CONTROLS
        if (gamepad1.a){
            BucketState[0] = "LOW";
        }
        if (gamepad1.b){
            SpecimenState[0] = "LOW";
        }
        if (gamepad2.dpad_up){
            robot.IntYaw.setPosition(Globals.yawInit);
        }
        if (gamepad2.dpad_left){
            robot.IntYaw.setPosition(Globals.yawLeft45);
        }
        if (gamepad2.dpad_up){
            robot.IntYaw.setPosition(Globals.yawRight45);
        }

        if (gamepad2.right_bumper && lifterStateArray[0].equals("LOWBUCK")&& sliderFlag){
            runningActions.add(
                    new InstantAction(()->lifter.lifterState(Lifter.lifterState.HIGHBUCKET))
            );
            lifterStateArray[0]="HIGHBUCK";

        }
        else if (gamepad2.right_bumper && lifterStateArray[0].equals("LOWRUNGS")&& sliderFlag){
            runningActions.add(
                    new InstantAction(()->lifter.lifterState(Lifter.lifterState.HIGHRUNGS))
            );
            lifterStateArray[0]="HIGHRUNGS";

        }
        if (gamepad2.left_bumper && lifterStateArray[0].equals("HIGHBUCK")&& sliderFlag){
            runningActions.add(
                    new InstantAction(()->lifter.lifterState(Lifter.lifterState.LOWBUCKET))
            );
            lifterStateArray[0]="LOWBUCK";

        }
        else if (gamepad2.left_bumper && lifterStateArray[0].equals("HIGHRUNGS")&& sliderFlag){
            runningActions.add(
                    new InstantAction(()->lifter.lifterState(Lifter.lifterState.LOWRUNGS))
            );
            lifterStateArray[0]="LOWRUNGS";

        }

 

        //TODO MISALLANEOUS























           // Color.RGBToHSV(robot.colourSensor.red() * 8, robot.colourSensor.green() * 8, robot.colourSensor.blue() * 8, hsvValues);
            runningActions=updateAction();
            telemetry.addData("Intake Colour Sensor: ", detectColor());
            telemetry.addData("Distance Detection: ", distanceInMM());
            telemetry.addData("Intake Distance Detection: ", robot.colourSensor.getDistance(DistanceUnit.MM));
            telemetry.addData("Lifter State: ", lifterStateArray);
//            telemetry.addData("Beam Breaker", robot.beamBreaker.getState());
//            telemetry.addData("Horizontal Extension ", robot.horizontalExtension.getCurrentPosition());
//            telemetry.addData("Slider L ", robot.lifterL.getCurrentPosition());
//            telemetry.addData("Slider R ", robot.lifterR.getCurrentPosition());
//            telemetry.addData("IntakeWrist ", robot.IntWrist.getPosition());
//            telemetry.addData("IntakeYaw ", robot.IntYaw.getPosition());
//            telemetry.addData("IntakeShoulderR " , robot.IntRightShoulder.getPosition());
//            telemetry.addData("IntakeShoulderL " , robot.IntLeftShoulder.getPosition());
//            telemetry.addData("Gripper ", robot.OutakeGrip.getPosition());
//            telemetry.addData("TransferLeft ", robot.leftOutake.getPosition());
//            telemetry.addData("TransferRight ", robot.rightOutake.getPosition());
//            telemetry.addData("Outake Wrist ", robot.OutakeWirst.getPosition());

            telemetry.update();
        }
    }

    private String detectColor() {
        int redValue = robot.colourSensor.red();
        int greenValue = robot.colourSensor.green();
        int blueValue = robot.colourSensor.blue();

        // Check for blue
        if (blueValue > redValue && blueValue > greenValue && blueValue > 100) {
            return "blue";
        }

        // Check for red
        if (redValue > blueValue && redValue > greenValue && redValue > 100) {
            return "red";
        }

        // Check for yellow (high red and green, low blue)
        if (redValue > 100 && greenValue > 100 && blueValue < 50)    {
            return "yellow";
        }

        // If no color matches, return "none"
        return "none";
    }

    private boolean distanceDetection() {
        // Get the distance in centimeters
        double distance = robot.gripColourSensor.getDistance(DistanceUnit.MM);

        // Check if the distance is within the threshold
        return distance <= DISTANCE_THRESHOLD_MM;
    }
    private double distanceInMM() {
        // Get the distance in centimeters
        double distance = robot.gripColourSensor.getDistance(DistanceUnit.MM);
        return distance;
    }


    public List<Action> updateAction() {
        TelemetryPacket packet = new TelemetryPacket();
        // update running actions
        List<Action> newActions = new ArrayList<>();
        for (Action action : runningActions) {
            action.preview(packet.fieldOverlay());
            if (action.run(packet)) {
                newActions.add(action);
            }
        }
        return newActions;
    }





}
//            if (gamepad1.a) {
//                runningActions.add(
//                        new InstantAction(()->robot.leftIntake.setPower(1))
//                );
//
//            }
//            if(gamepad1.b){
//                runningActions.add(new SequentialAction(intake.shoulderExtension(Intake.shoulderState.INTAKE),
//                                new SleepAction(0.5),
//                                intake.shoulderExtension(Intake.shoulderState.TRANSFER))
//                        );
//            }