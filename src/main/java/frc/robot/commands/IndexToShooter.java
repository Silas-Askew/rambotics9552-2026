package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class IndexToShooter extends Command {
    private ShooterSubsystem shooter;
    private IntakeSubsystem intake;

    public IndexToShooter(IntakeSubsystem intakeSubsystem) {
        //this.shooter = shooterSubsystem;
        this.intake = intakeSubsystem;

        addRequirements(intakeSubsystem);
    }

    @Override
    public void execute(){
        intake.indexShooter();
    }

    @Override
    public void end(boolean wasInterupted){
       intake.stopIndex();
    }
}
