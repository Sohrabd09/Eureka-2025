package org.firstinspires.ftc.teamcode.SequenceCommands;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;

import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lifter;

public class SpecimenIntakePost {

    public static Action specimenIntakePost(Intake intake, Lifter lifter){
        return new SequentialAction(

                lifter.gripperState(Lifter.gripperState.CLOSE),
                new SleepAction(0.2),
                new ParallelAction(
                        lifter.lifterState(Lifter.lifterState.HIGHRUNGS),
                        lifter.transferState(Lifter.TransferState.SPECIMEN180),
                        lifter.wristState(Lifter.outakeWristState.SPECIMENDROP)
                )

        );
    }
}
