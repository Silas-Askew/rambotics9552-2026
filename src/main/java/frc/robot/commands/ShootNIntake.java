package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootNIntake extends Command{
    public ShooterSubsystem shooter;
    public IntakeSubsystem intake;

    public ShootNIntake(ShooterSubsystem shooterSubsystem, IntakeSubsystem intakeSubsystem) {
        this.shooter = shooterSubsystem;
        this.intake = intakeSubsystem;

        addRequirements(shooterSubsystem, intakeSubsystem);
    }

    @Override
    public void execute(){
        intake.intake();
        shooter.outtakeIndex();
        shooter.shooterShoot();
    }

    @Override
    public void end(boolean wasInterupted){
       intake.stopIntake();
       shooter.stopIndex();
       shooter.stopShooter();
    }
}
