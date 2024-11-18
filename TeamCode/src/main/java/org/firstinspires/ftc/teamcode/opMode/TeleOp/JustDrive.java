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

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.hardware.Globals;
import org.firstinspires.ftc.teamcode.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lifter;

import java.util.ArrayList;
import java.util.List;

@TeleOp
public class JustDrive extends LinearOpMode {
    MecanumDrive drive;
    RobotHardware robot = RobotHardware.getInstance(); // Ensures singleton initialization
    Intake intake;
    Lifter lifter;  
    private List<Action> runningActions = new ArrayList<>();

    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize drive and subsystems
        drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        intake = new Intake(robot);
        lifter = new Lifter(robot);

        while (opModeInInit()) {
            robot.init1(hardwareMap); // Robot initialization during init phase
        }
        waitForStart();

        while (opModeIsActive()) {
            // Drive controls
            double x = -gamepad1.left_stick_x;
            double y = gamepad1.left_stick_y;
            double turn = -gamepad1.right_stick_x;

            // Adjust speed with left trigger
            if (gamepad1.left_trigger > 0.4) {
                drive.setDrivePowers(new PoseVelocity2d(new Vector2d(x / 2, y / 2), turn / 2));
            } else {
                drive.setDrivePowers(new PoseVelocity2d(new Vector2d(x, y), turn));
            }
            drive.updatePoseEstimate();;
        }
    }

    public List<Action> updateAction() {
        TelemetryPacket packet = new TelemetryPacket();
        // Update running actions
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
