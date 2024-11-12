package org.firstinspires.ftc.teamcode.SequenceCommands;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;

import org.firstinspires.ftc.teamcode.subsystes.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystes.subsystems.Lifter;

public class SampleDropSeq {

    public static Action sampleDrop(Intake intake ,Lifter lifter){
        return new SequentialAction(
                lifter.gripperState(Lifter.gripperState.OPEN),
                new SleepAction(0.3),
                new ParallelAction(
                        lifter.lifterState(Lifter.lifterState.INIT),
                        lifter.transferState(Lifter.TransferState.INIT),
                        lifter.wristState(Lifter.outakeWristState.TRANSFER)
                )
        );
    }
}
