package org.firstinspires.ftc.teamcode.SequenceCommands;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;

import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lifter;

public class OutakeSequenceHighBuck {

    public static Action outakeSequenceHighBuck(Intake intake, Lifter lifter){

        return new SequentialAction(
                new ParallelAction(
                        lifter.gripperState(Lifter.gripperState.CLOSE),
                        lifter.lifterState(Lifter.lifterState.INIT),
                        lifter.transferState(Lifter.TransferState.TRANSFER),
                        lifter.wristState(Lifter.outakeWristState.TRANSFER)

                ),
                new SleepAction(0.3),
                new ParallelAction(
                        lifter.lifterState(Lifter.lifterState.HIGHBUCKET),
                        lifter.transferState(Lifter.TransferState.BUCKET),
                        lifter.wristState(Lifter.outakeWristState.BUCKET)
                )

        );

    }
}
