// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSubsystem;

public class ArmUp extends Command {
  private final ArmSubsystem armSubsystem;
  boolean isPressed;

  /** Creates a new ArmUp. */
  public ArmUp(ArmSubsystem armSubsystem, boolean isPressed) {
    this.armSubsystem = armSubsystem;
    this.isPressed = isPressed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(armSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (isPressed){
      armSubsystem.spinMotor(.7);
    }
    else {
      armSubsystem.spinMotor(0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    armSubsystem.spinMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
