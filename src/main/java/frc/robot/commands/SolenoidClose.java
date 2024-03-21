// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.PneumaticsSubsystem;

public class SolenoidClose extends Command {
  final PneumaticsSubsystem pneumaticsSubsystem;
  
  /** Creates a new SolenoidControl. */
  public SolenoidClose(PneumaticsSubsystem m_pneumaticsSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
  
    pneumaticsSubsystem = m_pneumaticsSubsystem;
    addRequirements(m_pneumaticsSubsystem);
  
  }
  

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    pneumaticsSubsystem.closeSolenoid();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
