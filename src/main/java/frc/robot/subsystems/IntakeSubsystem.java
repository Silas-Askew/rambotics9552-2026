package frc.robot.subsystems;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class IntakeSubsystem extends SubsystemBase {

    private final SparkMax intakeMotor = new SparkMax(Constants.Intake.intakeMotor, MotorType.kBrushed);

    public IntakeSubsystem() {
        intakeMotor.setInverted(true);
    }

    public void intake() {
        intakeMotor.set(Constants.Intake.intakeSpeed);
    }

    public void outtake() {
        intakeMotor.set(Constants.Intake.outtakeSpeed);
    }

    public void stopIntake() {
        intakeMotor.set(0);
    }
}