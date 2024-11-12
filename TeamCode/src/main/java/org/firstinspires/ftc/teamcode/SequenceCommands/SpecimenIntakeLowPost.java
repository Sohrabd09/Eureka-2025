package org.firstinspires.ftc.teamcode.SequenceCommands;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;

import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lifter;

public class SpecimenIntakeLowPost {
    public static Action specimenIntakeLowPost(Intake intake, Lifter lifter){
    return new SequentialAction(

            lifter.gripperState(Lifter.gripperState.CLOSE),
            new SleepAction(0.2),
            new ParallelAction(
                    lifter.lifterState(Lifter.lifterState.LOWRUNGS),
                    lifter.transferState(Lifter.TransferState.SPECIMEN),
                    lifter.wristState(Lifter.outakeWristState.SPECIMENDROP)
            )

    );
}
}
