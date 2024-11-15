package org.firstinspires.ftc.teamcode.SequenceCommands;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;

import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lifter;

public class SpecimenIntake {

    public static Action specimenIntakePre(Intake intake, Lifter lifter){
        return new SequentialAction(
                new ParallelAction(
                        lifter.lifterState(Lifter.lifterState.INIT),
                        lifter.gripperState(Lifter.gripperState.OPEN),

                        lifter.wristState(Lifter.outakeWristState.SPECIMEN),
                        lifter.transferState(Lifter.TransferState.SPECIMENINTAKE)
                )

        );

    }
}
