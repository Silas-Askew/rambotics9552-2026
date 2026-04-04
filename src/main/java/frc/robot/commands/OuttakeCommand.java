package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class OuttakeCommand extends Command {
    //public ShooterSubsystem shooter;
    public IntakeSubsystem intake;

    public OuttakeCommand(IntakeSubsystem intakeSubsystem) {
        //this.shooter = shooterSubsystem;
        this.intake = intakeSubsystem;

        addRequirements(intakeSubsystem);
    }

    @Override
    public void execute(){
        intake.outtake();
        //shooter.outtakeIndex();
    }

    @Override
    public void end(boolean wasInterupted){
       intake.stopIntake();
       intake.stopIndex();
    }
}
