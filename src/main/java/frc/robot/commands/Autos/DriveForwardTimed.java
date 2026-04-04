package frc.robot.commands.Autos;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.DriveStraight;
import frc.robot.subsystems.DriveBase;

public class DriveForwardTimed extends SequentialCommandGroup {
    
    /**
     just a basic auto to use as a base
        *
        * @param drive     The drive subsystem object used by the robot
        * @param right      The control input for the right sight of the drive
        * @param driveSubsystem The driveSubsystem subsystem to drive
        */
    public DriveForwardTimed(DriveBase drive, DoubleSupplier speed, double duration) {
        super(
            new ArcadeDrive(drive, speed, () -> 0, true).withTimeout(duration)
        );
    }
}
