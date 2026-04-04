package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class IntakeCommand extends Command {
    //public ShooterSubsystem shooter;
    private IntakeSubsystem intake;

    public IntakeCommand(IntakeSubsystem intakeSubsystem) {
        //this.shooter = shooterSubsystem;
        this.intake = intakeSubsystem;

        addRequirements(intakeSubsystem);
    }

    @Override
    public void execute(){
        intake.intake();
    }

    @Override
    public void end(boolean wasInterupted){
       intake.stopIntake();
       intake.stopIndex();
    }
}
