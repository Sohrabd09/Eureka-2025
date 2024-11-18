package org.firstinspires.ftc.teamcode.SequenceCommands;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;

import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lifter;

public class PostObsrSeq {

    public static Action postObsr(Intake intake, Lifter lifter){
        return new SequentialAction(

                intake.shoulderExtension(Intake.shoulderState.OBSERVATIONDROP),
                new ParallelAction(
                intake.wristState(Intake.intWristState.OBSERVATIONDROP),
                intake.yawState(Intake.yawState.LEFT45)),
                new SleepAction(0.2),
                intake.flapperState(Intake.flapperState.OPEN)



        );

    }
}
