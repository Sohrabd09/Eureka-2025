package org.firstinspires.ftc.teamcode.SequenceCommands;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;

import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lifter;

public class SpecimenDropFinal {

        public static Action specimenDropFinal(Intake intake, Lifter lifter){
            return new SequentialAction(
                    new ParallelAction(
                            lifter.transferState(Lifter.TransferState.SPECIMEN180),
                            lifter.gripperState(Lifter.gripperState.CLOSE)
                    ),
                    lifter.transferState(Lifter.TransferState.SPECIMENDROP),
                    lifter.gripperState(Lifter.gripperState.OPEN),
                    new SleepAction(0.3),
                    lifter.transferState(Lifter.TransferState.SPECIMENINTAKE),
                    lifter.lifterState(Lifter.lifterState.INIT)



            );
        }
}
