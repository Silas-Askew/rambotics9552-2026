package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ReverseShooter extends Command{
    
    private ShooterSubsystem shooter;
    private IntakeSubsystem intake;

    public ReverseShooter(ShooterSubsystem shooterSubsystem, IntakeSubsystem intakeSubsystem) {
        this.shooter = shooterSubsystem;
        this.intake = intakeSubsystem;

        addRequirements(shooterSubsystem, intakeSubsystem);
    }

    @Override
    public void execute(){
        //shooter.intakeIndex();
        intake.intakeIndex();
        shooter.reverseShooter();
    }

    @Override
    public void end(boolean wasInterupted){
       shooter.stopShooter();
       intake.stopIndex();
    }
}

