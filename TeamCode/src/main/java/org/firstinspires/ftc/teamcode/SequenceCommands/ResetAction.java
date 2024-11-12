package org.firstinspires.ftc.teamcode.SequenceCommands;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;

import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lifter;

public class ResetAction {

    public static Action resetAction(Intake intake, Lifter lifter){
        return  new SequentialAction(
            intake.shoulderExtension(Intake.shoulderState.RESET),
                new SleepAction(0.2),
                new ParallelAction(
                        intake.shoulderExtension(Intake.shoulderState.INTAKE),
                        intake.yawState(Intake.yawState.LEFT45)
                ),
                intake.yawState(Intake.yawState.RIGHT45),
                intake.yawState(Intake.yawState.INIT)

        );
    }
}
