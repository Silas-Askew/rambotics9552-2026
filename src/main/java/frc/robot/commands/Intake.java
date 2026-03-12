package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class Intake extends Command {
    public ShooterSubsystem shooter;
    public IntakeSubsystem intake;

    public Intake(ShooterSubsystem shooterSubsystem, IntakeSubsystem intakeSubsystem) {
        this.shooter = shooterSubsystem;
        this.intake = intakeSubsystem;

        addRequirements(shooterSubsystem, intakeSubsystem);
    }

    @Override
    public void execute(){
        intake.intake();
        shooter.intakeIndex();
    }

    @Override
    public void end(boolean wasInterupted){
       intake.stopIntake();
       shooter.stopIndex();
    }
}
