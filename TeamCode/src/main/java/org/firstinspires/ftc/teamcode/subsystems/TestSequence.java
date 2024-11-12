package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;

public class TestSequence {
    public static Action testSequence(Intake intake, Lifter lifter){
        return new SequentialAction(
                intake.shoulderExtension(Intake.shoulderState.INTAKE),
                new SleepAction(0.5),
                intake.shoulderExtension(Intake.shoulderState.INIT)



        );
    }
}
