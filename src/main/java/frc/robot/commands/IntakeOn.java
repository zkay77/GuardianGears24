// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeOn extends Command {
  private final IntakeSubsystem intakeSubsystem;
  boolean isPressed;

  /** Creates a new IntakeOn. */
  public IntakeOn(IntakeSubsystem intakeSubsystem, boolean isPressed) {
    this.intakeSubsystem = intakeSubsystem;
    this.isPressed = isPressed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intakeSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(isPressed){
      intakeSubsystem.spinMotors(.12);
    }
    else{
      intakeSubsystem.spinMotors(0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intakeSubsystem.spinMotors(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
