package org.firstinspires.ftc.teamcode.opMode.Auto;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.MecanumDrive;
;
@Config
@Autonomous(name = "BLUE_TEST_AUTO_PIXEL", group = "Autonomous")

public class TestAuto extends LinearOpMode{


        @Override
        public void runOpMode() {


            Pose2d initialPose = new Pose2d(11.8, 61.7, Math.toRadians(90));
            MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

            TrajectoryActionBuilder tab1 = drive.actionBuilder(initialPose)
                    .lineToYSplineHeading(33, Math.toRadians(0))
                    .waitSeconds(2)
                    .setTangent(Math.toRadians(90))
                    .lineToY(48)
                    .setTangent(Math.toRadians(0))
                    .lineToX(32)
                    .strafeTo(new Vector2d(44.5, 30))
                    .turn(Math.toRadians(180))
                    .lineToX(47.5)
                    .waitSeconds(3);
            TrajectoryActionBuilder tab2 = drive.actionBuilder(initialPose)
                    .lineToY(37)
                    .setTangent(Math.toRadians(0))
                    .lineToX(18)
                    .waitSeconds(3)
                    .setTangent(Math.toRadians(0))
                    .lineToXSplineHeading(46, Math.toRadians(180))
                    .waitSeconds(3);
            TrajectoryActionBuilder tab3 = drive.actionBuilder(initialPose)
                    .lineToYSplineHeading(33, Math.toRadians(180))
                    .waitSeconds(2)
                    .strafeTo(new Vector2d(46, 30))
                    .waitSeconds(3);
            Action trajectoryActionCloseOut = tab1.fresh()
                    .strafeTo(new Vector2d(48, 12))
                    .build();



//            Actions.runBlocking(
//                    new SequentialAction(
//                            trajectoryActionChosen,
//                            lift.liftUp(),
//                            claw.openClaw(),
//                            lift.liftDown(),
//                            trajectoryActionCloseOut
//                    )
            while  (opModeIsActive()){

            }
        }
    }

