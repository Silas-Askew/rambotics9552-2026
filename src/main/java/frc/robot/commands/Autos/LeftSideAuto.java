package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.IntakeToShooter;
import frc.robot.commands.RampUpToShoot;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class LeftSideAuto extends SequentialCommandGroup {

    /**
     * 
     * @param drive
     * @param shooter
     * @param intake
     */
    public LeftSideAuto(DriveBase drive, ShooterSubsystem shooter, IntakeSubsystem intake) {
        super(
            new RampUpToShoot(shooter)
            .alongWith(new WaitCommand(1).andThen(new IntakeToShooter(intake))).withTimeout(5.0)
            .andThen(new ArcadeDrive(drive, () -> 0, () -> -0.67).withTimeout(1.0)) 
            .andThen(new ArcadeDrive(drive, () -> 1.0, () -> 0).withTimeout(1.0))
            .andThen(new ArcadeDrive(drive, () -> 0, () -> -0.8).withTimeout(1.0))
            .andThen(new ArcadeDrive(drive, () -> 1.0, () -> 0).withTimeout(2.0))
            .andThen(new ArcadeDrive(drive, () -> 0, () -> -0.8).withTimeout(1.0))
            .andThen(new ArcadeDrive(drive, () -> 1.1, () -> 0).withTimeout(3.0))
        );
    }
}
