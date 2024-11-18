package org.firstinspires.ftc.teamcode.SequenceCommands;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;

import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lifter;

public class INITsequence {




    public static Action initSequence (Intake intake, Lifter lifter){
        return new SequentialAction(
                new ParallelAction(
                        intake.horizontalExtensionIntakeState(Intake.extensionState.INIT),
                        intake.activeIntakeState(Intake.ActiveIntakeState.OFF),
                        intake.yawState(Intake.yawState.INIT),
                        lifter.lifterState(Lifter.lifterState.INIT),
                        lifter.gripperState(Lifter.gripperState.OPEN),
                        lifter.wristState(Lifter.outakeWristState.TRANSFER),
                        lifter.transferState(Lifter.TransferState.INIT),
                        lifter.clutchState(Lifter.clutchState.TELEOP),
                        intake.flapperState(Intake.flapperState.CLOSE)


                        ),
                new SleepAction(0.3),
                intake.shoulderExtension(Intake.shoulderState.INTAKE),
                new SleepAction(0.2),
                intake.wristState(Intake.intWristState.INIT)
        );
    }
}
