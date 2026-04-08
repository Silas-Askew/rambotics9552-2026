package frc.robot.commands;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.ShooterSubsystem;

public class RampUpToShoot extends Command {
    private ShooterSubsystem shooter;
    private SlewRateLimiter rampLimiter = new SlewRateLimiter(1.0 / Constants.Shooter.shooterRampTime);

    public RampUpToShoot(ShooterSubsystem shooterSubsystem) {
        this.shooter = shooterSubsystem;
        

        addRequirements(shooterSubsystem);
    }

    @Override
    public void execute(){
        shooter.setShooterVelocityRPM(rampLimiter.calculate(Constants.Shooter.shooterRPM));
    }

    @Override
    public void end(boolean wasInterupted){
       shooter.stopShooter();
       //shooter.stopIndex();
    }

}
