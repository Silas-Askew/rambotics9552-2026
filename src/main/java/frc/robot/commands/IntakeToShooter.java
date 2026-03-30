package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeToShooter extends Command {
    private IntakeSubsystem intake;

    public IntakeToShooter(IntakeSubsystem intakeSubsystem) {
        //this.shooter = shooterSubsystem;
        this.intake = intakeSubsystem;

        addRequirements(intakeSubsystem);
    }

    @Override
    public void execute(){
        intake.intakeToShooter();
    }

    @Override
    public void end(boolean wasInterupted){
       intake.stopIntake();
       intake.stopIndex();
    }
}
