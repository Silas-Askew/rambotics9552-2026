package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootShooter extends Command{
    
    private ShooterSubsystem shooter;
    private IntakeSubsystem intake;

    public ShootShooter(ShooterSubsystem shooterSubsystem, IntakeSubsystem intakeSubsystem) {
        this.shooter = shooterSubsystem;
        this.intake = intakeSubsystem;

        addRequirements(shooterSubsystem, intakeSubsystem);
    }

    @Override
    public void execute(){
        shooter.shooterShoot();
        intake.indexShooter();
    }

    @Override
    public void end(boolean wasInterupted){
       shooter.stopShooter();
       intake.stopIndex();
    }
}

