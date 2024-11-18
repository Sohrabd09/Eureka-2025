package org.firstinspires.ftc.teamcode.opMode.TeleOp;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.hardware.Globals;
import org.firstinspires.ftc.teamcode.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lifter;

import java.util.ArrayList;
import java.util.List;

@TeleOp
@Config

public class getValues extends LinearOpMode {
    public static RobotHardware robot = RobotHardware.getInstance();
    Intake intake;
    Lifter lifter;
    public int sliderIncrement = 0;
    public int horizontalSliderIncrement = 0;
    public double shoulderIncrement = 0.5;
    public double yawIncrement = 0.5;
    public double intakeWristIncrement = 0.5;
    public double transferIncrement = 0.5;

    public double outakeWristState = 0.5;
    public double gripperIncrement = 0.5;
    public double flapperIncrement = 0.5;
    public double clutchIncrement = 0 ;
    public static int greenValue = 0;
    public static int blueValue = 0;
    public static int redValue = 0;
    public double LShoulderPosOut = 0;
    public double RShoulderPos = 0;    
    public double LShoulderPos = 0;
    public double RShoulderPosOut = 0;
    public double OutakeDistance = 25;



    @Override
    public void runOpMode() throws InterruptedException {
        while(opModeInInit()){
            robot.init1(hardwareMap);
            robot.IntRightShoulder.setPosition(0.5);
            robot.IntLeftShoulder.setPosition(0.5);
        }

        intake = new Intake(robot);
        lifter = new Lifter(robot);

        waitForStart();
        while (opModeIsActive()){

            if (gamepad1.right_bumper){
                horizontalSliderIncrement += 25;
                intake.extensionPos(horizontalSliderIncrement);

            }
            if (gamepad1.left_bumper){
                horizontalSliderIncrement-=25;
                intake.extensionPos(horizontalSliderIncrement);
            }
            if (gamepad2.left_trigger>0){
                sliderIncrement +=25;
                lifter.lifterPos(sliderIncrement);
            }
            if (gamepad2.right_trigger>0){
                sliderIncrement -=25;
                lifter.lifterPos(sliderIncrement);
            }
            if (gamepad1.x){
                yawIncrement-=0.001;
                robot.IntYaw.setPosition(yawIncrement);

            }
            if (gamepad1.y){
                yawIncrement+=0.001;
                robot.IntYaw.setPosition(yawIncrement);

            }
            if (gamepad1.a){
                intakeWristIncrement +=0.001;
                robot.IntWrist.setPosition(intakeWristIncrement);
            }
            if (gamepad1.b){
                intakeWristIncrement -=0.001;
                robot.IntWrist.setPosition(intakeWristIncrement);
            }
            if (gamepad1.dpad_up){
              viperMotion(true);
            }
            if (gamepad1.dpad_down){
              viperMotion(false);
            }
            if (gamepad1.dpad_left){
                shoulderMotionInt(true);
            }
            if (gamepad1.dpad_right){
                shoulderMotionInt(false);

            }
            if  (gamepad1.start){
                flapperIncrement +=0.01;
                robot.IntFlapper.setPosition(flapperIncrement);
            }
            if  (gamepad1.back){
                flapperIncrement -=0.01;
                robot.IntFlapper.setPosition(flapperIncrement);
            }
            if (gamepad1.right_stick_button){
                robot.leftIntake.setPower(1);
                robot.rightIntake.setPower(-1);
            }
            if (gamepad1.left_stick_button){
                robot.leftIntake.setPower(-1);
                robot.rightIntake.setPower(1);
            }
            else {
                intake.activeIntakeState(Intake.ActiveIntakeState.OFF);
            }
            // TODO OUTAKE
            if (gamepad2.x){
                outakeWristState +=0.001;
                robot.OutakeWirst.setPosition(outakeWristState);
            }
            if (gamepad2.y){
                outakeWristState -=0.001;
                robot.OutakeWirst.setPosition(outakeWristState);
            }
            if(gamepad2.a){

                robot.OutakeGrip.setPosition(Globals.gripperOpen);
            }
            if(gamepad2.b){

                robot.OutakeGrip.setPosition(Globals.gripperOpen);
            }
            if (gamepad2.dpad_left){
                shoulderMotionOut(true);
            }
            if (gamepad2.dpad_right){
                shoulderMotionOut(false);
            }
            if (gamepad2.dpad_up){
                viperMotionOut(true);
            }

            if (gamepad2.dpad_down){
               viperMotionOut(false);
            }
            telemetry.addData("Intake Colour Sensor ", detectColor());
            telemetry.addData("Distance Detection ", robot.gripColourSensor.getDistance(DistanceUnit.MM));
            telemetry.addData("Beam Breaker", robot.beamBreaker.getState());
            telemetry.addData("Horizontal Extension ", robot.horizontalExtension.getCurrentPosition());
            telemetry.addData("Slider L ", robot.lifterL.getCurrentPosition());
            telemetry.addData("Slider R ", robot.lifterR.getCurrentPosition());
            telemetry.addData("Slider L current ", robot.lifterR.getCurrent(CurrentUnit.AMPS));
            telemetry.addData("Slider R current ", robot.lifterL.getCurrent(CurrentUnit.AMPS));
            telemetry.addData("IntakeWrist ", robot.IntWrist.getPosition());
            telemetry.addData("IntakeYaw ", robot.IntYaw.getPosition());
            telemetry.addData("IntakeShoulderR " , robot.IntRightShoulder.getPosition());
            telemetry.addData("IntakeShoulderL " , robot.IntLeftShoulder.getPosition());
            telemetry.addData("Gripper ", robot.OutakeGrip.getPosition());
            telemetry.addData("TransferLeft ", robot.leftOutake.getPosition());
            telemetry.addData("TransferRight ", robot.rightOutake.getPosition());
            telemetry.addData("Outake Wrist ", robot.OutakeWirst.getPosition());

            telemetry.update();

        }
    }
    public static String detectColor() {

             blueValue = robot.colourSensor.blue();
             greenValue = robot.colourSensor.green();
             redValue = robot.colourSensor.red();


            // Check for blue
            if (blueValue > greenValue && blueValue > 100) {
                return "blue";

            }
            // Check for red
            else if (redValue>greenValue && redValue > 100) {
                return "red";
            }

            // Check for yellow (high red and green, low blue)
            else if (greenValue > 100 && blueValue > 100 && redValue <50) {
                return "yellow";
            }
            else {
                // If no color matches, return "none"
                return "none";
            }
    }

    private boolean distanceDetection() {

        double distance = robot.gripColourSensor.getDistance(DistanceUnit.MM);


        return distance<=OutakeDistance;

    }

    private void shoulderMotionOut(boolean increase) {
        if (increase) {
            LShoulderPosOut= Math.min( LShoulderPosOut+ 0.001, 1);
            RShoulderPosOut = Math.min(RShoulderPosOut + 0.001, 1);
        } else {
            LShoulderPosOut= Math.max( LShoulderPosOut- 0.001, 0);
            RShoulderPosOut = Math.max(RShoulderPosOut - 0.001, 0);
        }
        robot.leftOutake.setPosition(LShoulderPosOut);
        robot.rightOutake.setPosition(RShoulderPosOut);
    }
    private void shoulderMotionInt(boolean increase) {
        if (increase) {
            LShoulderPos = Math.min( LShoulderPos+ 0.001, 1);
            RShoulderPos = Math.min(RShoulderPos + 0.001, 1);
        } else {
            LShoulderPosOut= Math.max( LShoulderPos- 0.001, 0);
            RShoulderPos = Math.max(RShoulderPos - 0.001, 0);
        }
        robot.IntLeftShoulder.setPosition(LShoulderPosOut);
        robot.IntRightShoulder.setPosition(RShoulderPos);
    }
    private void viperMotion(boolean increase) {
        if (increase) {
            LShoulderPos = Math.min(LShoulderPos + 0.01, 1);
            RShoulderPos = Math.max(RShoulderPos - 0.01, 0);
        } else {
            LShoulderPos = Math.min(LShoulderPos - 0.01, 1);
            RShoulderPos = Math.max(RShoulderPos + 0.01, 0);
        }
        robot.IntLeftShoulder.setPosition(LShoulderPos);
        robot.IntRightShoulder.setPosition(RShoulderPos);
        }
    private void viperMotionOut(boolean increase) {
        if (increase) {
            LShoulderPosOut = Math.min(LShoulderPosOut + 0.01, 1);
            RShoulderPosOut = Math.max(RShoulderPosOut - 0.01, 0);
        } else {
            LShoulderPosOut = Math.min(LShoulderPosOut - 0.01, 1);
            RShoulderPosOut = Math.max(RShoulderPosOut + 0.01, 0);
        }
        robot.leftOutake.setPosition(LShoulderPosOut);
        robot.rightOutake.setPosition(RShoulderPosOut);
        }



    public List<Action> updateAction() {
        TelemetryPacket packet = new TelemetryPacket();
        // update running actions
        List<Action> newActions = new ArrayList<>();
        Action[] runningActions = new Action[0];
        for (Action action : runningActions) {
            action.preview(packet.fieldOverlay());
            if (action.run(packet)) {
                newActions.add(action);
            }
        }
        return newActions;
    }
}
