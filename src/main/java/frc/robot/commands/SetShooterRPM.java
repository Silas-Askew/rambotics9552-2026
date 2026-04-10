package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

public class SetShooterRPM extends Command {

    private ShooterSubsystem shooter;
    private double shooterRPM;

    public SetShooterRPM(ShooterSubsystem shooterSubsystem, double RPM) {
        this.shooter = shooterSubsystem;
        this.shooterRPM = RPM;

        addRequirements(shooterSubsystem);
    }

    @Override
    public void execute(){
        shooter.setShooterVelocityRPM(this.shooterRPM);
    }

    @Override
    public void end(boolean wasInterupted){
        //if (!wasInterupted) {
            shooter.stopShooter();
        //}
    }
}
