package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Arm;

public class ArmDown extends Command {
    public Arm arm;

    public ArmDown(Arm armSubsystem) {
        this.arm = armSubsystem;

        addRequirements(armSubsystem);
    }
/* 
    @Override
    public void execute(){
        arm.armDown();
    }

    @Override
    public void end(boolean wasInterupted){
        arm.stopArm();
    }
*/
}
