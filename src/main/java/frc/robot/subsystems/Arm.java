package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.RelativeEncoder;
import frc.robot.Constants;


public class Arm extends SubsystemBase {
    private final SparkMax Motor1 = new SparkMax(Constants.Arm.MOTOR_1, MotorType.kBrushless);
    private final SparkMax Motor2 = new SparkMax(Constants.Arm.MOTOR_2, MotorType.kBrushless);

    private final RelativeEncoder Motor1Encoder = Motor1.getEncoder();
    private final RelativeEncoder Motor2Encoder = Motor2.getEncoder();
}
/* 
    public Arm() {
        Motor1.setInverted(true);
        SparkMaxConfig motor2config = new SparkMaxConfig(); 
    motor2config.follow(Motor1);
    Motor2.configure(motor2config, SparkBase.ResetMode.kResetSafeParameters, SparkBase.PersistMode.kPersistParameters);


    }
*/
    //final DigitalInput limitSwitch = new DigitalInput(Constants.Climber.toplimitSwitch);
 
    // public void setMotorSpeed(double speed) {
    //     if (speed > 0) {
    //         if (toplimitSwitch.get()) {
    //             // We are going up and top limit is tripped so stop
    //             motor.set(0);
    //         } else {
    //             // We are going up but top limit is not tripped so go at commanded speed
    //             motor.set(speed);
    //         }
    //     } else {
    //         if (bottomlimitSwitch.get()) {
    //             // We are going down and bottom limit is tripped so stop
    //             motor.set(0);
    //         } else {
    //             // We are going down but bottom limit is not tripped so go at commanded speed
    //             motor.set(speed);
    //         }
    //     }
    // }
/* 
    public void armUp() {
        Motor1.setInverted(true);
        Motor1.set(Constants.Arm.MotorUp);
    }
    

    public void armDown() {
        Motor1.set(Constants.Arm.MotorDown);
    }

    public void stopArm(){
        Motor1.set(0);
    }
}
    */