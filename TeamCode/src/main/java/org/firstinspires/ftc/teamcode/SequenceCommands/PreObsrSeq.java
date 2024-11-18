package org.firstinspires.ftc.teamcode.SequenceCommands;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;

import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lifter;

public class PreObsrSeq {

    public static Action preObsr(Intake intake, Lifter lifter){
        return new SequentialAction(
                    intake.flapperState(Intake.flapperState.HOLD),
                    new ParallelAction(
                            intake.horizontalExtensionIntakeState(Intake.extensionState.INIT),
                            intake.shoulderExtension(Intake.shoulderState.OBSERVATION),
                            intake.yawState(Intake.yawState.INIT),
                            intake.activeIntakeState(Intake.ActiveIntakeState.OFF),
                            intake.wristState(Intake.intWristState.OBSERVATIONPRE)
                    ),
                    intake.yawState(Intake.yawState.OBSR)

        );
    }
}
