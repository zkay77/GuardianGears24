// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autos;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class AutoIntakeOn extends Command {
  /** Creates a new AutoIntakeOn. */
  private final IntakeSubsystem intakeSubsystem;
  private final double speed;
 
  public AutoIntakeOn(final IntakeSubsystem m_intakeSubsystem, final double m_speed) {
    // Set intakeSubsystem equal to m_intakeSubsystem so m_intakeSubsystem can be used outside of the constructor
    intakeSubsystem = m_intakeSubsystem;
    // Do the same with speed and m_speed
    speed = m_speed;
    addRequirements(intakeSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Set the motors to the speed passed from RobotContainer
    intakeSubsystem.spinMotors(speed);
  } 
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // Stop motors
    intakeSubsystem.spinMotors(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
