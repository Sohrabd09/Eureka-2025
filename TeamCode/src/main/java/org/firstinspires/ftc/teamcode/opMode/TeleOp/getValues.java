package org.firstinspires.ftc.teamcode.opMode.TeleOp;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
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
    public double shoulderIncrement = 0;
    public double yawIncrement = 0;
    public double intakeWristIncrement = 0;
    public double transferIncrement = 0;

    public double outakeWristState = 0;
    public double gripperIncrement = 0;
    public double clutchIncrement = 0 ;
    public static int greenValue = 0;
    public static int blueValue = 0;
    public double LShoulderPos = 0;
    public double RShoulderPos = 0;



    @Override
    public void runOpMode() throws InterruptedException {

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
            if (gamepad1.left_trigger>0){
                sliderIncrement +=25;
                lifter.lifterPos(sliderIncrement);
            }
            if (gamepad1.right_trigger>0){
                sliderIncrement -=25;
                lifter.lifterPos(sliderIncrement);
            }
            if (gamepad1.x){
                shoulderIncrement+=0.001;
                intake.shoulderPos(shoulderIncrement);
            }
            if (gamepad1.y){
                shoulderIncrement-=0.001;
                intake.shoulderPos(shoulderIncrement);
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
                transferIncrement +=0.001;
                lifter.transferSetLinearMovement(transferIncrement);
            }
            if (gamepad1.dpad_down){
                transferIncrement -=0.001;
                lifter.transferSetLinearMovement(transferIncrement);
            }
            if (gamepad1.dpad_left){
                outakeWristState +=0.001;
                robot.OutakeWirst.setPosition(outakeWristState);
            }
            if (gamepad1.dpad_right){
                outakeWristState -=0.001;
                robot.OutakeWirst.setPosition(outakeWristState);

            }
            if (gamepad2.x){
                yawIncrement+=0.001;
                robot.IntYaw.setPosition(yawIncrement);
            }
            if (gamepad2.y){
                yawIncrement-=0.001;
                robot.IntYaw.setPosition(yawIncrement);
            }
            if(gamepad2.a){
                gripperIncrement+=0.001;
                robot.OutakeGrip.setPosition(gripperIncrement);
            }
            if(gamepad2.b){
                gripperIncrement-=0.001;
                robot.OutakeGrip.setPosition(gripperIncrement);
            }
            if (gamepad2.dpad_left){
                shoulderMotion(true);
            }
            if (gamepad2.dpad_right){
                shoulderMotion(false);
            }
            telemetry.addData("Intake Colour Sensor ", robot.colourSensor.green());
            telemetry.addData("Distance Detection ", distanceDetection());
            telemetry.addData("Beam Breaker", robot.beamBreaker.getState());
            telemetry.addData("Horizontal Extension ", robot.horizontalExtension.getCurrentPosition());
            telemetry.addData("Slider L ", robot.lifterL.getCurrentPosition());
            telemetry.addData("Slider R ", robot.lifterR.getCurrentPosition());
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

            // Check for blue
            if (blueValue > greenValue && blueValue > 100) {
                return "blue";

            }

            // Check for red
    //        if (greenValue > blueValue && greenValue > greenValue && greenValue > 100) {
    //            return "red";
    //        }

            // Check for yellow (high red and green, low blue)
            else if (greenValue > 100 && blueValue < 50) {
                return "yellow";
            }
            else {
                // If no color matches, return "none"
                return "none";
            }
    }

    private double distanceDetection() {
        // Get the distance in centimeters
        double distance = robot.gripColourSensor.getDistance(DistanceUnit.MM);

        // Check if the distance is within the threshold
        return distance;
        //<= 7;
    }
    private void shoulderMotion(boolean increase) {
        if (increase) {
            LShoulderPos= Math.min( LShoulderPos+ 0.001, 1);
            RShoulderPos = Math.min(RShoulderPos + 0.001, 1);
        } else {
            LShoulderPos= Math.max( LShoulderPos- 0.001, 0);
            RShoulderPos = Math.max(RShoulderPos - 0.001, 0);
        }
        robot.IntLeftShoulder.setPosition(LShoulderPos);
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
