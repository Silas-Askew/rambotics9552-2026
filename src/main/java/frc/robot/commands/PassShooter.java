package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

public class PassShooter extends Command{
    
    public ShooterSubsystem shooter;

    public PassShooter(ShooterSubsystem shooterSubsystem) {
        this.shooter = shooterSubsystem;

        addRequirements(shooterSubsystem);
    }

    @Override
    public void execute(){
        shooter.shooterPass();
    }

    @Override
    public void end(boolean wasInterupted){
       shooter.stopShooter();
       shooter.stopIndex();
    }
}

