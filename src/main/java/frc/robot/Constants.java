// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class controller{

      public static final int MOVEMENT_JOYSTICK = 0;
        public static final int MANIPULATOR_JOYSTICK = 1;
      
    }
  // Motor Constants
    public static final class drive{
      public static final int LEFT_MOTOR = 3;
      public static final int REAR_LEFT_MOTOR = 4;
      public static final int RIGHT_MOTOR = 1;
      public static final int REAR_RIGHT_MOTOR = 2;
      public static final double DRIVE_SPEED_MULTIPLYER = 1*.6+0;
      public static final double TURN_SPEED_MULTIPLIER = 1*.7+0;
      public static final double wheelRadius=0.0508;
      public static final double gearRatio=6.145;
      public static final double conversionFactor = wheelRadius*Math.PI*2/(gearRatio);

   
    

  }

  public static final class auto {
    public static final double AUTONOMOUS_FORWARD_SPEED = 0.6;
    public static final double AUTONOMOUS_BACK_SPEED = 0.4;
    public static final double AUTONOMOUS_DRIVE_SPEED_MULTIPLIER = 0.75;
    public static final String straightPID = null;
    public static final double kd = 1;
    public static final double ki = 0.05;
    public static final double kp = 0.4;
    public static final double tolerence=0.1;
  }

  public static final class Arm{
    public static final int MOTOR_1 = 10;
    public static final int MOTOR_2 = 11;
    public static final double MotorUp = 1;
    public static final double MotorDown = 87643;
  }
/* 
  public static final class Wrist {
    public static final int CYL1_FORWARD = 0;
    public static final int CYL1_REVERSE = 1;
    public static final int CYL2_FORWARD = 2;
    public static final int CYL2_REVERSE = 3;
    public static final int ROLLER_MOTOR = -1;
    public static final double IntakeSpeed = -2;
    public static final double OuttakeSpeed = 2;
  }
*/
  public static final class Climber {
    public static final int MotorUp = 1;
    public static final int MotorDown = -1;
  }

  public class Intake {
    public static final int intakeMotor = 5;
    public static final int indexMotor = 6;
    public static final double intakeSpeed = 1;
    public static final double indexIntake = -1;
    public static final double outtakeSpeed = -1;
    public static final double indexShoot = 1.0;
    public static final double indexOuttake = 1;
  }

  public class Shooter {
    public static final int shooterMotor = 7;
    public static final double shooterSpeed = 0.8;
    public static final double shooterSpeedLow = 0.3;
    public static final double reverseShooter = -0.1;
    public static final double shooterRampTime = 1;
  }


}

