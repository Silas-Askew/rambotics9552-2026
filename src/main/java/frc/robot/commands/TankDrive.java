package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.DriveBase;

public class TankDrive extends RunCommand {
    public TankDrive(DriveBase drive, DoubleSupplier leftSpeed, DoubleSupplier rightSpeed) {
    super(
      ()->{
        drive.arcadeDrive(
          MathUtil.applyDeadband(leftSpeed.getAsDouble(), 0.1),
          MathUtil.applyDeadband(rightSpeed.getAsDouble(), 0.1)
        );
      },
      drive
    );
  }
}
