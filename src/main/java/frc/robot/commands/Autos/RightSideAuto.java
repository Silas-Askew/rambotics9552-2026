package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.IntakeToShooter;
import frc.robot.commands.RampUpToShoot;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class RightSideAuto extends SequentialCommandGroup {


    public RightSideAuto(DriveBase drive, ShooterSubsystem shooter, IntakeSubsystem intake) {
        super(
            new RampUpToShoot(shooter).withTimeout(5) 
            .alongWith(new WaitCommand(1).andThen(new IntakeToShooter(intake).withTimeout(4.0)))
            .andThen(new ArcadeDrive(drive, () -> 0, () -> 0.67).withTimeout(1.0)) 
            .andThen(new ArcadeDrive(drive, () -> 1.0, () -> 0).withTimeout(2.0))
            .andThen(new ArcadeDrive(drive, () -> 0, () -> 0.9).withTimeout(1.0))
            .andThen(new ArcadeDrive(drive, () -> 1.0, () -> 0).withTimeout(2.0))
            .andThen(new ArcadeDrive(drive, () -> 0, () -> 0.9).withTimeout(1.0))
            .andThen(new ArcadeDrive(drive, () -> 1.1, () -> 0).withTimeout(3.0))
        );
    }
}
