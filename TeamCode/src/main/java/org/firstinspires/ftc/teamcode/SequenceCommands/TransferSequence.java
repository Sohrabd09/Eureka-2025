package org.firstinspires.ftc.teamcode.SequenceCommands;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;

import org.firstinspires.ftc.teamcode.subsystes.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystes.subsystems.Lifter;

public class TransferSequence {

    public static Action transferSequence(Intake intake, Lifter lifter){
        return new SequentialAction(
                intake.wristState(Intake.intWristState.TRANSFER),
                intake.shoulderExtension(Intake.shoulderState.TRANSFER),
                new ParallelAction(
                        lifter.lifterState(Lifter.lifterState.INIT),
                        lifter.wristState(Lifter.outakeWristState.SPECIMEN),
                        lifter.transferState(Lifter.TransferState.INIT),
                        lifter.gripperState(Lifter.gripperState.OPEN),

                        intake.horizontalExtensionIntakeState(Intake.extensionState.INIT),
                        intake.yawState(Intake.yawState.INIT),
                        intake.activeIntakeState(Intake.ActiveIntakeState.ON),
                        intake.flapperState(Intake.flapperState.CLOSE)
                ),
                new SleepAction(1),
                new ParallelAction(
                        lifter.wristState(Lifter.outakeWristState.TRANSFER),
                        lifter.transferState(Lifter.TransferState.TRANSFER)
                ),
                new SleepAction(0.5),
                lifter.gripperState(Lifter.gripperState.CLOSE),
                intake.activeIntakeState(Intake.ActiveIntakeState.OFF)

        );
    }
}
