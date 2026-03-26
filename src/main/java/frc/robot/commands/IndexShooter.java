package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

public class IndexShooter extends Command {
    private ShooterSubsystem shooter;

    public IndexShooter(ShooterSubsystem shooterSubsystem) {
        this.shooter = shooterSubsystem;

        addRequirements(shooterSubsystem);
    }

    @Override
    public void execute(){
        shooter.outtakeIndex();
    }

    @Override
    public void end(boolean wasInterupted){
       shooter.stopIndex();
    }
}
