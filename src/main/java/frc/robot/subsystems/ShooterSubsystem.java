package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkFlexConfig;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {

    private final SparkFlex shooterMotor =
        new SparkFlex(Constants.Shooter.shooterMotor, MotorType.kBrushless);

    private final SparkClosedLoopController shooterController =
        shooterMotor.getClosedLoopController();

    private final RelativeEncoder shooterEncoder =
        shooterMotor.getEncoder();

    private final SparkFlexConfig shooterMotorConfig;

    public ShooterSubsystem() {
        shooterMotorConfig = new SparkFlexConfig();

        shooterMotorConfig
            .inverted(true)
            .closedLoopRampRate(Constants.Shooter.shooterRampTime)
            .smartCurrentLimit(60);

        // Default encoder units:
        // position = rotations
        // velocity = RPM
        shooterMotorConfig.encoder
            .positionConversionFactor(1.0)
            .velocityConversionFactor(1.0);

        // Slot 0 closed-loop tuning
        shooterMotorConfig.closedLoop
            .p(Constants.Shooter.kp, ClosedLoopSlot.kSlot0)
            .i(Constants.Shooter.ki, ClosedLoopSlot.kSlot0)
            .d(Constants.Shooter.kd, ClosedLoopSlot.kSlot0)
            .feedForward.kV(Constants.Shooter.kV, ClosedLoopSlot.kSlot0);
            
            // Start here for a NEO Vortex-ish shooter
            // then tune on the real robot

        
        shooterMotor.configure(
            shooterMotorConfig,
            ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters 
        );


    }

    public void setShooterVelocityRPM(double rpm) {
        shooterController.setSetpoint(
            rpm,
            SparkBase.ControlType.kVelocity,
            ClosedLoopSlot.kSlot0
        );
    }

    public void shooterShoot() {
        setShooterVelocityRPM(Constants.Shooter.shooterRPM);
    }

    public void shooterPass() {
        setShooterVelocityRPM(Constants.Shooter.shooterPassRPM);
    }

    public void revShooter() {
        setShooterVelocityRPM(Constants.Shooter.shooterRPM);
    }

    public void revShooterAuto() {
        setShooterVelocityRPM(Constants.auto.AUTO_SHOOTER_RPM);
    }

    public void stopShooter() {
        shooterMotor.stopMotor();
        //setShooterVelocityRPM(0);
    }

    public void reverseShooter() {
        shooterMotor.set(Constants.Shooter.reverseShooter);
        // keep this open-loop unless you specifically want reverse RPM control
    }

    public double getShooterVelocityRPM() {
        return shooterEncoder.getVelocity();
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Shooter RPM", getShooterVelocityRPM());
    }
}