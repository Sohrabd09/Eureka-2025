package org.firstinspires.ftc.teamcode.SequenceCommands;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;

import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lifter;

import java.util.function.Predicate;

public class PreIntakeSeq {

        public static Action preIntake(Intake intake, Lifter lifter){
            return new SequentialAction(
                    new ParallelAction(
                            intake.horizontalExtensionIntakeState(Intake.extensionState.INIT),
                            intake.wristState(Intake.intWristState.PREINTAKE),
                            intake.shoulderExtension(Intake.shoulderState.PREINTAKE),
                            intake.yawState(Intake.yawState.INIT)

                    )
            );
        }
}
