package frc.robot.commands;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.ShooterSubsystem;

public class RampUpShooter extends Command {
    private ShooterSubsystem shooter;
    private SlewRateLimiter rampLimiter = new SlewRateLimiter(1.0 / Constants.Shooter.shooterRampTime);

    public RampUpShooter(ShooterSubsystem shooterSubsystem) {
        this.shooter = shooterSubsystem;

        addRequirements(shooterSubsystem);
    }

    @Override
    public void execute(){
        shooter.setShooterSpeed(rampLimiter.calculate(Constants.Shooter.shooterSpeed));
    }

    @Override
    public void end(boolean wasInterupted){
       shooter.stopShooter();
       //shooter.stopIndex();
    }

}
