package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.hardware.Globals;
import org.firstinspires.ftc.teamcode.hardware.RobotHardware;

public class Lifter {
   RobotHardware robot;




    public Lifter(RobotHardware robot) {
        this.robot = robot;
    }

        public enum lifterState{
            INIT,
            HIGHBUCKET,

            LOWBUCKET,

            HIGHRUNGS,

            LOWRUNGS,
            HAGNINGPRE,
            HANGINGPOST
        }

    public void update(lifterState state){
            switch (state){
                case LOWRUNGS:
                    lifterPos(Globals.lowRung);
                    break;
                case LOWBUCKET:
                    lifterPos(Globals.lowBuck);
                    break;
                case  HIGHRUNGS:
                    lifterPos(Globals.HigRung);
                    break;
                case HIGHBUCKET:
                    lifterPos(Globals.HighBuck);
                    break;
                case INIT:
                    lifterPos(Globals.lifterInit);
                    break;
                case HAGNINGPRE:
                    lifterPos(Globals.HangingPre);
                    break;
                case HANGINGPOST:
                    lifterPos(Globals.HangingPost);
                    break;
            }


    }

    public enum gripperState {

        OPEN,
        CLOSE,
        INIT,

    }


    public void update(gripperState state){
        switch (state){
            case INIT:
                robot.OutakeGrip.setPosition(Globals.gripperINIT);
                break;
            case CLOSE:
                robot.OutakeGrip.setPosition(Globals.gripperClose);
                break;
            case OPEN:
                robot.OutakeGrip.setPosition(Globals.gripperOpen);
                break;
        }
    }
    public enum clutchState {

       HANGING,
        TELEOP
    }


    public void update(clutchState state){
        switch (state){
            case TELEOP:
                robot.clutch.setPosition(Globals.clutchTeleOp);
                break;
            case HANGING:
                robot.clutch.setPosition(Globals.clutchHanging);
                break;

        }
    }


    public enum outakeWristState {
            TRANSFER,
            SPECIMEN,
            SPECIMENDROP,
            BUCKET,
            DROP,
    }

    public void update(outakeWristState state ){
        switch (state){

            case SPECIMEN:
                robot.OutakeWirst.setPosition(Globals.OutakeWristSpecimen);
                break;
            case DROP:
                robot.OutakeWirst.setPosition(Globals.OutakeWristDrop);
                break;
            case TRANSFER:
                robot.OutakeWirst.setPosition(Globals.OutakeWristTransfer);
                break;
            case BUCKET:
                robot.OutakeWirst.setPosition(Globals.OutakeWristBucket);
                break;
            case SPECIMENDROP:
                robot.OutakeWirst.setPosition(Globals.OutakeWristSpecimenDrop);
                break;
        }
    }

    public enum TransferState {
        TRANSFER,
        INIT,
        SPECIMENINTAKE,
        SPECIMEN180,
        BUCKET,
        SPECIMEN,


    }
    public void update(TransferState state){
        switch (state){
            case INIT:
                transferSetLinearMovement(Globals.OutakeShoulderInit);
                break;
            case TRANSFER:
                transferSetLinearMovement(Globals.OutakeShoulderTransfer);
                break;
            case BUCKET:
                transferSetLinearMovement(Globals.OutakeShoulderBucket);
                break;

            case SPECIMEN:
                transferSetLinearMovement(Globals.OutakeShoulderSpecimen);
                break;
            case SPECIMENINTAKE:
                transferSetLinearMovement(Globals.OutakeShoulderSpecimenIntake);
                break;
            case SPECIMEN180:
                robot.leftOutake.setPosition(Globals.OutakeShoulderSpecimen180L);
                robot.rightOutake.setPosition(Globals.OutakeShoulderSpecimen180R);
                break;
        }
    }
    public void lifterPos(int pos){
        robot.lifterL.setTargetPosition(pos);
        robot.lifterL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.lifterL.setPower(1);
        robot.lifterR.setTargetPosition(pos);
        robot.lifterR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.lifterR.setPower(1);
    }
    public void transferSetLinearMovement(double pos){
        robot.leftOutake.setPosition(pos);
        robot.rightOutake.setPosition(1-pos);
    }

//    public void transferRotate(double left,double right){
//        robot.leftOutake.setPosition(left);
//        robot.rightOutake.setPosition(right);
//    }
    public Action gripperState (gripperState state){
        return new InstantAction(()->update(state));
    }
    public Action transferState (TransferState state){
        return new InstantAction(()->update(state));
    }
    public Action wristState (outakeWristState state){
        return new InstantAction(()->update(state));
    }
    public Action lifterState (lifterState state){
        return new InstantAction(()->update(state));
    }


}
