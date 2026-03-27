package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootShooterWIntake extends Command {
    
    private ShooterSubsystem shooter;
    private IntakeSubsystem intake;
    private BooleanSupplier shouldIntake;


    public ShootShooterWIntake(ShooterSubsystem shooterSubsystem, IntakeSubsystem intakeSubsystem, BooleanSupplier shouldIntake) {
        this.shooter = shooterSubsystem;
        this.intake = intakeSubsystem;
        this.shouldIntake = shouldIntake;

        addRequirements(shooterSubsystem, intakeSubsystem);
    }

    @Override
    public void execute(){
        shooter.shooterShoot();
        if (this.shouldIntake.getAsBoolean()) {
            intake.intake();
        }
    }

    @Override
    public void end(boolean wasInterupted){
        shooter.stopShooter();
        intake.stopIndex();
        //if (this.shouldIntake.getAsBoolean()) {
            intake.stopIntake();
        //}
    }

}
