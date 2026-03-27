package frc.robot.subsystems;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
   
    private final SparkFlex shooterMotor = new SparkFlex(Constants.Shooter.shooterMotor, MotorType.kBrushless);
    

    public ShooterSubsystem() {
        shooterMotor.setInverted(true);
    }

    public void shooterShoot() {
        shooterMotor.set(Constants.Shooter.shooterSpeed);
        //indexMotor.set(Constants.Shooter.indexShoot);
    }

    public void shooterPass() {
        shooterMotor.set(Constants.Shooter.shooterSpeedLow);
        //indexMotor.set(Constants.Shooter.indexShoot);
    }

    public void rampUpToShoot(double rampTime) {
        
    }

    public void setShooterSpeed(double speed) {
        shooterMotor.set(speed);
    }

    // public void intakeIndex() {
    //     indexMotor.set(Constants.Shooter.indexIntake);
    // }

    public void revShooter() {
        shooterMotor.set(Constants.Shooter.shooterSpeed);
    }

    // public void stopIndex() {
    //     indexMotor.set(0);
    // }

    public void stopShooter() {
        shooterMotor.set(0);
    }

    // public void outtakeIndex() {
    //     indexMotor.set(Constants.Shooter.indexShoot);
    // }

    public void reverseShooter() {
        shooterMotor.set(Constants.Shooter.reverseShooter);
    }
}
