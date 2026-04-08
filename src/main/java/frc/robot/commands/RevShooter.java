package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

public class RevShooter extends Command {

    private ShooterSubsystem shooter;

    public RevShooter(ShooterSubsystem shooterSubsystem) {
        this.shooter = shooterSubsystem;

        addRequirements(shooterSubsystem);
    }

    @Override
    public void execute(){
        shooter.revShooter();
    }

    @Override
    public void end(boolean wasInterupted){
        //if (!wasInterupted) {
            shooter.stopShooter();
        //}
    }
}
