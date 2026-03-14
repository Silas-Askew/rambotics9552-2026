// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
//import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class DriveBase extends SubsystemBase {


  //creates the motors in code
  private final SparkMax m_leftMotor = new SparkMax(Constants.drive.LEFT_MOTOR, MotorType.kBrushless);
  private final SparkMax m_rightMotor = new SparkMax(Constants.drive.RIGHT_MOTOR, MotorType.kBrushless);
  private final SparkMax m_leftBackMotor = new SparkMax(Constants.drive.REAR_LEFT_MOTOR, MotorType.kBrushless);
  private final SparkMax m_rightBackMotor = new SparkMax(Constants.drive.REAR_RIGHT_MOTOR, MotorType.kBrushless);


  RelativeEncoder leftEncoder = m_leftMotor.getEncoder();
  RelativeEncoder rightEncoder = m_rightMotor.getEncoder();

  private final DifferentialDrive m_RobotDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);
  



  @SuppressWarnings("deprecation")
  public DriveBase() {

    // inverts two motors so the drivetrain can run
    //m_leftMotor.setInverted(true);
    //m_leftBackMotor.setInverted(true);

    //declare motor configurings
    SparkMaxConfig m_leftMotorConfig = new SparkMaxConfig();
    SparkMaxConfig m_rightMotorConfig = new SparkMaxConfig();
    SparkMaxConfig m_leftBackMotorConfig = new SparkMaxConfig();
    SparkMaxConfig m_rightBackMotorConfig = new SparkMaxConfig();

    //set motor configurations
    m_leftMotorConfig.inverted(true);
    m_rightMotorConfig.inverted(false);
    m_leftBackMotorConfig.inverted(true).follow(m_leftMotor);
    m_rightBackMotorConfig.follow(m_rightMotor);

    //SparkMaxConfig m_leftBackMotorConfig = new SparkMaxConfig(); 
    //SparkMaxConfig m_rightBackMotorConfig = new SparkMaxConfig(); 
    //m_leftBackMotorConfig.follow(m_leftMotor);
    //m_rightBackMotorConfig.follow(m_rightMotor);
    m_leftMotor.configure(m_leftMotorConfig, SparkBase.ResetMode.kResetSafeParameters, SparkBase.PersistMode.kPersistParameters);
    m_rightMotor.configure(m_rightMotorConfig, SparkBase.ResetMode.kResetSafeParameters, SparkBase.PersistMode.kPersistParameters);
    m_leftBackMotor.configure(m_leftBackMotorConfig, SparkBase.ResetMode.kResetSafeParameters, SparkBase.PersistMode.kPersistParameters);
    m_rightBackMotor.configure(m_rightBackMotorConfig, SparkBase.ResetMode.kResetSafeParameters, SparkBase.PersistMode.kPersistParameters);
    leftEncoder.setPosition(Constants.drive.conversionFactor);
    rightEncoder.setPosition(Constants.drive.conversionFactor);




  }

  public void drive(final double ySpeed, final double rotateValue) {
    m_RobotDrive.arcadeDrive(-ySpeed, -rotateValue);
  }

  public double getEncoder(){
    return (leftEncoder.getPosition()+rightEncoder.getPosition())/2;
  }

  public void resetEncoder(){
    leftEncoder.setPosition(0);
    rightEncoder.setPosition(0);
  }

  public void arcadeDrive(double leftSpeed, double rightSpeed){
    m_RobotDrive.tankDrive(leftSpeed, rightSpeed);
  }
}