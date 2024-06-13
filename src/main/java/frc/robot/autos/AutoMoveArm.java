// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autos;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSubsystem;

public class AutoMoveArm extends Command {
  /** Creates a new AutoMoveArm. */
  private final ArmSubsystem armSubsystem;
  private final double speed;
 
  public AutoMoveArm(final ArmSubsystem m_armSubsystem, final double m_speed) {
    // Set armSubsystem equal to m_armSubsystem so m_armSubsystem can be used outside of the constructor
    armSubsystem = m_armSubsystem;
    // Do the same with speed and m_speed
    speed = m_speed;
    addRequirements(armSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Set the motor to the speed passed from RobotContainer
    armSubsystem.spinMotor(speed);
  } 

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // Stop motor
    armSubsystem.spinMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
