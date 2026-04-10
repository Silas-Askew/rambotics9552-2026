package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

public class RevShooterAuto extends Command {

    private ShooterSubsystem shooter;

    public RevShooterAuto(ShooterSubsystem shooterSubsystem) {
        this.shooter = shooterSubsystem;

        addRequirements(shooterSubsystem);
    }

    @Override
    public void execute(){
        shooter.revShooterAuto();
    }

    @Override
    public void end(boolean wasInterupted){
        //if (!wasInterupted) {
            shooter.stopShooter();
        //}
    }
}
