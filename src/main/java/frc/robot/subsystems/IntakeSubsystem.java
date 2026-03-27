package frc.robot.subsystems;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class IntakeSubsystem extends SubsystemBase {

    private final SparkMax intakeMotor = new SparkMax(Constants.Intake.intakeMotor, MotorType.kBrushed);
    private final SparkMax indexMotor = new SparkMax(Constants.Intake.indexMotor, MotorType.kBrushed);

    public IntakeSubsystem() {
        intakeMotor.setInverted(true);
    }

    public void intake() {
        intakeMotor.set(Constants.Intake.intakeSpeed);
        indexMotor.set(Constants.Intake.indexIntake);
    }

    public void indexShooter() {
        indexMotor.set(Constants.Intake.indexShoot);
    }

    public void intakeIndex() {
        indexMotor.set(Constants.Intake.indexIntake);
    }

    public void intakeToShooter() {
        intakeMotor.set(Constants.Intake.intakeSpeed);
        indexMotor.set(Constants.Intake.indexShoot);
    }

    public void outtakeIndex() {
        indexMotor.set(Constants.Intake.indexOuttake);
    }

    public void outtake() {
        intakeMotor.set(Constants.Intake.outtakeSpeed);
        indexMotor.set(Constants.Intake.indexOuttake);
    }

    public void stopIntake() {
        intakeMotor.set(0);
    }

    public void stopIndex() {
        indexMotor.set(0);
    }
}