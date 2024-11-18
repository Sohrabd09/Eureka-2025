package org.firstinspires.ftc.teamcode.opMode.TeleOp;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.hardware.RobotHardware;
@Config
@TeleOp
public class HangerTest extends LinearOpMode{








        public static int a = 0;
        public static int b = 0;
        public static int x = 0;
        public static int y = 0;




        public static double power = 1;
        public static int sliderInc = 50;
        RobotHardware robot = RobotHardware.getInstance();
        @Override
        public void runOpMode() throws InterruptedException {
            robot.init1(hardwareMap);


            robot.horizontalExtension.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.lifterL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.lifterR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);



            while(opModeInInit()){
                printStuff(telemetry);

                telemetry.update();
            }
            waitForStart();
            while(opModeIsActive()){


                if(gamepad1.dpad_up){

                    robot.lifterL.setTargetPosition(robot.lifterL.getCurrentPosition() + sliderInc);
                    robot.lifterR.setTargetPosition(robot.lifterR.getCurrentPosition() + sliderInc);

                    robot.lifterL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.lifterR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.lifterL.setPower(power);
                    robot.lifterR.setPower(power);

                }
                if(gamepad2.dpad_down){

                    robot.lifterL.setTargetPosition(robot.lifterL.getCurrentPosition() - sliderInc);
                    robot.lifterR.setTargetPosition(robot.lifterR.getCurrentPosition() - sliderInc);

                    robot.lifterL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.lifterR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.lifterL.setPower(power);
                    robot.lifterR.setPower(power);

                }

                if(gamepad1.a){

                    robot.lifterL.setTargetPosition(a);
                    robot.lifterR.setTargetPosition(a);

                    robot.lifterL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.lifterR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.lifterL.setPower(power);
                    robot.lifterR.setPower(power);

                }
                if(gamepad1.b){

                    robot.lifterL.setTargetPosition(b);
                    robot.lifterR.setTargetPosition(b);

                    robot.lifterL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.lifterR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.lifterL.setPower(power);
                    robot.lifterR.setPower(power);

                }
                if(gamepad1.x){

                    robot.lifterL.setTargetPosition(x);
                    robot.lifterR.setTargetPosition(x);

                    robot.lifterL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.lifterR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.lifterL.setPower(power);
                    robot.lifterR.setPower(power);

                }
                if(gamepad1.y){

                    robot.lifterL.setTargetPosition(y);
                    robot.lifterR.setTargetPosition(y);

                    robot.lifterL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.lifterR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.lifterL.setPower(power);
                    robot.lifterR.setPower(power);

                }


                printStuff(telemetry);

                telemetry.update();
            }

        }

        

        public  void printStuff(Telemetry telemetry){
            telemetry.addData("Current vertical:", robot.lifterL.getCurrent(CurrentUnit.AMPS));
            telemetry.addData("Current vertical:", robot.lifterR.getCurrent(CurrentUnit.AMPS));
            telemetry.addLine();
        }




    }















