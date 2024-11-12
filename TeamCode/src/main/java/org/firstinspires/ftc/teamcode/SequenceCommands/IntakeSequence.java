package org.firstinspires.ftc.teamcode.SequenceCommands;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;

import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lifter;

public class IntakeSequence {



    public static Action intakeSequence(Intake intake, Lifter lifter){
        return new SequentialAction(
                new ParallelAction(
                        intake.horizontalExtensionIntakeState(Intake.extensionState.EXTEND1),
                        intake.shoulderExtension(Intake.shoulderState.INTAKE)
                        ),
                new SleepAction(0.3),
                new ParallelAction(
                        intake.wristState(Intake.intWristState.INTAKE),
                        intake.flapperState(Intake.flapperState.CLOSE),
                        intake.yawState(Intake.yawState.INIT)
                        ),
                new SleepAction(0.2),
                intake.activeIntakeState(Intake.ActiveIntakeState.ON)


        );
    }
}
