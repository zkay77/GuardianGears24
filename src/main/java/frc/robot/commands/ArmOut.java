// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ArmSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;

public class ArmOut extends Command {
  private final ArmSubsystem armSubsystem;

  boolean isPressed;

  /** Creates a new ArmExtend. */
  public ArmOut(ArmSubsystem m_armSubsystem, boolean m_isPressed) {
    armSubsystem = m_armSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_armSubsystem);

    isPressed = m_isPressed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  final XboxController xbox = new XboxController (0);
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (isPressed){
      if(!armSubsystem.coneSensor.get())  
        armSubsystem.spinMotor(-.9);
      else  
        armSubsystem.spinMotor(-.5);
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
