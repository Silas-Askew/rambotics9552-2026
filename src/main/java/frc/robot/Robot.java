/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.MetersPerSecond;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.AddressableLEDBufferView;
import edu.wpi.first.wpilibj.LEDPattern;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import frc.robot.commands.*;
import frc.robot.subsystems.*;
import frc.robot.commands.Autos.*;



/*
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private final DriveBase m_driveSubsystem = new DriveBase();

  private final Arm m_armSubsystem = new Arm();

  private final ShooterSubsystem m_ShooterSubsystem = new ShooterSubsystem();

  private final IntakeSubsystem m_IntakeSubsystem = new IntakeSubsystem();

  // private final LEDSubsystem m_LedSubsystem = new LEDSubsystem();
  public final AddressableLED m_ledStrand;
  public final AddressableLEDBuffer m_ledBuffer;
  //private final AddressableLEDBufferView m_leftView;
  //private final AddressableLEDBufferView m_rightView;

  // all hues at maximum saturation and half brightness
  private final LEDPattern m_rainbow = LEDPattern.rainbow(255, 128);
  
  // Our LED strip has a density of 120 LEDs per meter
  private static final Distance kLedSpacing = Meters.of(1 / 256.0);
  
  // Create a new pattern that scrolls the rainbow pattern across the LED strip, moving at a speed
  // of 1 meter per second.
  private final LEDPattern m_scrollingRainbow = m_rainbow.scrollAtAbsoluteSpeed(MetersPerSecond.of(0.33), kLedSpacing);

  SendableChooser<Integer> controlChooser = new SendableChooser<Integer>();
  //private NetworkTableEntry cameraSelection;

  SendableChooser<Integer> autoChooser = new SendableChooser<>();

  private final CommandXboxController movementJoystick = new CommandXboxController(Constants.controller.MOVEMENT_JOYSTICK);
  private final CommandXboxController manipulatorJoystick = new CommandXboxController(Constants.controller.MANIPULATOR_JOYSTICK);

  /** The container for the robot. Contains subsystems, OI devices, and commands. 
   * @return */
    // Configure the button bindings
  public Robot(){
    UsbCamera camera = CameraServer.startAutomaticCapture();
    m_ledStrand = new AddressableLED(Constants.LED.kLedPort);
    m_ledBuffer = new AddressableLEDBuffer(Constants.LED.kLedBufferLength);
    m_ledStrand.setLength(m_ledBuffer.getLength());

    controlChooser.setDefaultOption("arcade :)", 0);
    controlChooser.addOption("tank :(", 1);

    autoChooser.setDefaultOption("fuel scoring only auto", 0);
    autoChooser.addOption("score fuel and disrupt field starting left field", 1);
    autoChooser.addOption("score fuel and disrupt field starting center field", 2);
    autoChooser.addOption("score fuel and disrupt field starting right field", 3);

    configureButtonBindings();

    SmartDashboard.putData("control type", controlChooser);
    SmartDashboard.putData("autonomous command", autoChooser);
    camera.setResolution(1920, 1080);
    


    m_ledStrand.setData(m_ledBuffer);
    m_ledStrand.start();
  }


  
  private void configureButtonBindings() {
    while (controlChooser==null){}


    if (controlChooser.getSelected()==0){
      m_driveSubsystem.setDefaultCommand(
        new ArcadeDrive(
              m_driveSubsystem,
              () -> (-0*(-movementJoystick.getLeftTriggerAxis() + movementJoystick.getRightTriggerAxis())-1*movementJoystick.getLeftY()),
              () -> (movementJoystick.getRightX())
        ));
        

      manipulatorJoystick.a().whileTrue(new ArmDown(m_armSubsystem));
      manipulatorJoystick.b().whileTrue(new ArmUp(m_armSubsystem));
      manipulatorJoystick.leftBumper().whileTrue(new Outtake(m_IntakeSubsystem));
      manipulatorJoystick.rightBumper().and(() -> !manipulatorJoystick.rightTrigger().getAsBoolean()).whileTrue(new Intake(m_IntakeSubsystem));
      manipulatorJoystick.leftTrigger().and(() -> !manipulatorJoystick.rightTrigger().getAsBoolean()).toggleOnTrue(new RampUpToShoot(m_ShooterSubsystem));
      manipulatorJoystick.rightTrigger().and(() -> !manipulatorJoystick.leftTrigger().getAsBoolean()).whileTrue(new IndexShooter(m_IntakeSubsystem));
      manipulatorJoystick.rightTrigger().and(() -> manipulatorJoystick.leftTrigger().getAsBoolean()).whileTrue(new ShootShooter(m_ShooterSubsystem, m_IntakeSubsystem));
      manipulatorJoystick.rightTrigger().and(() -> manipulatorJoystick.leftTrigger().getAsBoolean()).and(() -> manipulatorJoystick.rightBumper().getAsBoolean()).whileTrue(new ShootNIntake(m_ShooterSubsystem, m_IntakeSubsystem));
      //manipulatorJoystick.leftTrigger().whileTrue(new ReverseShooter(m_ShooterSubsystem));
      //manipulatorJoystick.rightTrigger().and(() -> !manipulatorJoystick.rightBumper().getAsBoolean()).whileTrue(new ShootShooter(m_ShooterSubsystem));
      //manipulatorJoystick.rightTrigger().and(() -> manipulatorJoystick.rightBumper().getAsBoolean()).whileTrue(new ShootNIntake(m_ShooterSubsystem, m_IntakeSubsystem));
      //manipulatorJoystick.rightBumper().whileTrue(new Intake(m_ShooterSubsystem, m_IntakeSubsystem)).and(() -> manipulatorJoystick.rightTrigger().getAsBoolean()).whileTrue(new ShootNIntake(m_ShooterSubsystem, m_IntakeSubsystem));
    }

    else if (controlChooser.getSelected()==1){
      m_driveSubsystem.setDefaultCommand(
        new TankDrive(
          m_driveSubsystem,
          () -> (movementJoystick.getLeftY()),
          () -> (movementJoystick.getRightY())));

          
      manipulatorJoystick.a().whileTrue(new ArmDown(m_armSubsystem));
      manipulatorJoystick.b().whileTrue(new ArmUp(m_armSubsystem));
      // manipulatorJoystick.leftBumper().whileTrue(new Outtake(m_IntakeSubsystem));
      // manipulatorJoystick.rightBumper().whileTrue(new Intake(m_IntakeSubsystem));
      // manipulatorJoystick.leftTrigger().whileTrue(new ReverseShooter(m_ShooterSubsystem));
      // manipulatorJoystick.rightTrigger().whileTrue(new ShootShooter(m_ShooterSubsystem));
    }

    
     
    //manipulatorJoystick.leftTrigger().onFalse(new stopShooter(shooter, intake));
    //manipulatorJoystick.rightTrigger().onFalse(new stopShooter(shooter, intake));
    

    // final JoystickButton manipulator_x = new JoystickButton(manipulatorJoystick, Button.kX.value);
  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    //return new DriveForward(m_driveSubsystem);
    //return new DriveForwardTimed(m_driveSubsystem, () -> 0.67, 2.5);

    if (autoChooser.getSelected() == 1) {
      m_autonomousCommand = new LeftSideAuto(m_driveSubsystem, m_ShooterSubsystem, m_IntakeSubsystem);
    } else if (autoChooser.getSelected() == 2) {
      m_autonomousCommand = new MiddleAuto(m_driveSubsystem, m_ShooterSubsystem, m_IntakeSubsystem);
    } else if (autoChooser.getSelected() == 3) {
      m_autonomousCommand = new RightSideAuto(m_driveSubsystem, m_ShooterSubsystem, m_IntakeSubsystem);
    } else {
      m_autonomousCommand = new ShootFuelAuto(m_driveSubsystem, m_ShooterSubsystem, m_IntakeSubsystem);
    }
    return m_autonomousCommand;
    //return new MiddleAuto(m_driveSubsystem, m_ShooterSubsystem, m_IntakeSubsystem);
  }
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_scrollingRainbow.applyTo(m_ledBuffer);

    m_ledStrand.setData(m_ledBuffer);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
