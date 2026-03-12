package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveBase;
import frc.robot.commands.*;

    public class DriveForward extends SequentialCommandGroup {

        /**
         just a basic auto to use as a base
         *
         * @param drive     The drive subsystem object used by the robot
         * @param right      The control input for the right sight of the drive
         * @param driveSubsystem The driveSubsystem subsystem to drive
         */
        public DriveForward(DriveBase drive) {
          super(
              new DriveStraight(drive, 3.6576)
          );
        }
      }

