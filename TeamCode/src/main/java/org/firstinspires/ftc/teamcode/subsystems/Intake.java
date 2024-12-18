package org.firstinspires.ftc.teamcode.subsystems;

//import static org.firstinspires.ftc.robotcontroller.external.samples.ConceptScanServo.MIN_POS;
//import static org.firstinspires.ftc.robotcontroller.external.samples.ConceptScanServo.MAX_POS;


import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.hardware.Globals;
import org.firstinspires.ftc.teamcode.hardware.RobotHardware;

public class Intake {
    RobotHardware robot;
    public Intake(RobotHardware robot) {
        this.robot = robot;
    }

    public enum extensionState{
        INIT,
        EXTEND1,
        EXTEND2,
        EXTEND3,
    }

    public void update(extensionState state){
        switch (state){
            case INIT:
                extensionPos(Globals.extendInit);
            case EXTEND1:
                extensionPos(Globals.extend1);
            case EXTEND2:
                extensionPos(Globals.extend2);
            case EXTEND3:
                extensionPos(Globals.extend3);
        }
    }

    public  enum shoulderState{

            INIT,
            TRANSFER,
            INTAKE,
            RESET,
            PREINTAKE,
            OBSERVATION,
            OBSERVATIONDROP,



    }


    public void update(shoulderState state){
        switch (state){
            case INTAKE:
                shoulderPos(Globals.shoulderIntakeL, Globals.shoulderIntakeR);
                break;
            case INIT:
                shoulderPos(Globals.shoulderInitL, Globals.shoulderInitR);
                break;
            case RESET:
                shoulderPos(Globals.shoulderResetL, Globals.shoulderResetR);
                break;
            case TRANSFER:
                shoulderPos(Globals.shoulderTransferL, Globals.shoulderTransferR);
                break;
            case PREINTAKE:
                shoulderPos(Globals.shoulderPreIntakeL, Globals.shoulderPreIntakeR);
                break;
            case OBSERVATION:
                shoulderPos(Globals.shoulderObsL, Globals.shoulderObsR);
            case OBSERVATIONDROP:
                robot.IntLeftShoulder.setPosition(Globals.shoulderObsDropL);
                robot.IntRightShoulder.setPosition(Globals.shoulderObsDropR);
        }
    }




    public enum ActiveIntakeState{
        ON,
        OFF,
        REVERSE,
    }


        public void update(ActiveIntakeState state){
                switch (state){
                    case ON:
                        intakeRoller(1);
                        break;

                    case OFF:
                      intakeRoller(0);
                      break;

                    case REVERSE:
                      intakeRoller(-1);
                      break;
                }
        }

        public enum intWristState {
                INTAKE,
                INIT,
                RESET,
                TRANSFER,
                OBSERVATIONPRE,
                PREINTAKE,
                OBSERVATIONDROP,
        }

        public void update(intWristState state){
                    switch (state){
                        case TRANSFER:
                            robot.IntWrist.setPosition(Globals.IntWristTransfer);
                            break;
                        case RESET:
                            robot.IntWrist.setPosition(Globals.IntWristReset);
                            break;
                        case INIT:
                            robot.IntWrist.setPosition(Globals.IntWristInit);
                            break;
                        case INTAKE:
                            robot.IntWrist.setPosition(Globals.IntWristIntake);
                            break;
                        case OBSERVATIONPRE:
                            robot.IntWrist.setPosition(Globals.IntWristObs);
                            break;
                        case PREINTAKE:
                            robot.IntWrist.setPosition(Globals.IntWristPre);
                            break;
                    }
        }
        public enum flapperState {
                OPEN,
                CLOSE,
                HOLD,

        }



        public void update(flapperState state){
                switch (state){
                    case OPEN:
                        robot.IntFlapper.setPosition(Globals.flapperOpen);
                        break;
                    case CLOSE:
                        robot.IntFlapper.setPosition(Globals.flapperClose);
                        break;
                    case HOLD:
                        robot.IntFlapper.setPosition(Globals.flapperHold);
                        break;
                }
        }

        public enum yawState{
                RIGHT45,
                LEFT45,
                RIGHT90,
                LEFT90,
                INIT,
                OBSR,
//                OBSSRDROP

        }

        public void update(yawState state){
                switch (state){
                    case INIT:
                        robot.IntYaw.setPosition(Globals.yawInit);
                        break;
                    case LEFT45:
                        robot.IntYaw.setPosition(Globals.yawLeft45);
                        break;
                    case RIGHT45:
                        robot.IntYaw.setPosition(Globals.yawRight45);
                        break;
                    case LEFT90:
                        robot.IntYaw.setPosition(Globals.yawLeft90);
                        break;
                    case RIGHT90:
                        robot.IntYaw.setPosition(Globals.yawRight90);
                        break;
                        case OBSR:
                            robot.IntYaw.setPosition(Globals.yawObs);
                        break;
                }
        }
        public void intakeRoller(double power){
            robot.rightIntake.setPower(power);
            robot.leftIntake.setPower(power * (-1));
        }
        public void shoulderPos(double left, double right){
            robot.IntRightShoulder.setPosition(right);
            robot.IntLeftShoulder.setPosition(left);
        }

        public void extensionPos(int pos){
            robot.horizontalExtension.setTargetPosition(pos);
            robot.horizontalExtension.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.horizontalExtension.setPower(1);
        }

        public  Action shoulderExtension( shoulderState state){
        return new InstantAction(()->update(state));
        }
        public Action wristState (intWristState state){
            return new InstantAction(()->update(state));
        }
        public Action yawState (yawState state){
            return new InstantAction(()->update(state));
        }
        public Action flapperState (flapperState state){
            return new InstantAction(()->update(state));
        }
        public Action activeIntakeState (ActiveIntakeState state){
            return new InstantAction(()->update(state));
        }
        //
        public Action horizontalExtensionIntakeState (extensionState state){
            return new InstantAction(()->update(state));
        }
    }

    /*





     */

