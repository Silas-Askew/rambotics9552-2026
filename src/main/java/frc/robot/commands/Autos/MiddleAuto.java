package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.IndexToShooter;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.IntakeToShooter;
import frc.robot.commands.OuttakeCommand;
import frc.robot.commands.RampUpToShoot;
import frc.robot.commands.RevShooter;
import frc.robot.commands.ShootNIntake;
import frc.robot.commands.ShootShooter;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class MiddleAuto extends SequentialCommandGroup {
    
    /**
     * 
     * @param drive - drive subsystem
     * @param shooter - shooter subsystem 
     * @param intake - intake subsystem
     * 
     * Current Auto:
     *  Rev up shooter and shoot fuel for 1st 5 seconds, 
     *  then turn left 90*, 
     *  drive forward, 
     *  turn left 90*, 
     *  drive forward (hopefully under the trench), 
     *  turn left 90* again, 
     *  drive forward across the field disrupting fuel
     * 
     */
    public MiddleAuto(DriveBase drive, ShooterSubsystem shooter, IntakeSubsystem intake) {
        super(
            new RampUpToShoot(shooter)
            .alongWith(new WaitCommand(1).andThen(new IntakeToShooter(intake))).withTimeout(5.0)
            .andThen(new ArcadeDrive(drive, () -> 1.0, () -> 0, true).withTimeout(1.0)) 
            .andThen(new ArcadeDrive(drive, () -> 0, () -> 0.7, true).withTimeout(1.0))
            .andThen(new ArcadeDrive(drive, () -> 1.0, () -> 0, true).withTimeout(1.0))
            .andThen(new ArcadeDrive(drive, () -> 0, () -> -0.7, true).withTimeout(1.0))
            .andThen(new ArcadeDrive(drive, () -> 1.0, () -> 0, true).withTimeout(1.1).alongWith(new IntakeCommand(intake).withTimeout(1.5)))

        );
    }
}
