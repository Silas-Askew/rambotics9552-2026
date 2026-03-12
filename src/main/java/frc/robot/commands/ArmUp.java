package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Arm;

public class ArmUp extends Command {
    public Arm arm;

    public ArmUp(Arm armSubsystem) {
        this.arm = armSubsystem;

        addRequirements(armSubsystem);
    }

    @Override
    public void execute(){
        arm.armUp();
    }

    @Override
    public void end(boolean wasInterupted){
        arm.stopArm();
    }

}