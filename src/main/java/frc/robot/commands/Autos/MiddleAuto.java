package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.IndexShooter;
import frc.robot.commands.RampUpShooter;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class MiddleAuto extends SequentialCommandGroup {
    
    public MiddleAuto(DriveBase drive, ShooterSubsystem shooter, IntakeSubsystem intake) {
        super(
            new RampUpShooter(shooter).withTimeout(3.0),
            new WaitCommand(1).andThen(new IndexShooter(intake)).withTimeout(2.0)
            .andThen(new ArcadeDrive(drive, () -> 0, () -> 0.5)).withTimeout(1.0)
        );
    }
}
