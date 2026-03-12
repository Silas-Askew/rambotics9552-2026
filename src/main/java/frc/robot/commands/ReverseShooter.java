package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

public class ReverseShooter extends Command{
    
    public ShooterSubsystem shooter;

    public ReverseShooter(ShooterSubsystem shooterSubsystem) {
        this.shooter = shooterSubsystem;

        addRequirements(shooterSubsystem);
    }

    @Override
    public void execute(){
        shooter.intakeIndex();
        shooter.reverseShooter();
    }

    @Override
    public void end(boolean wasInterupted){
       shooter.stopShooter();
       shooter.stopIndex();
    }
}

