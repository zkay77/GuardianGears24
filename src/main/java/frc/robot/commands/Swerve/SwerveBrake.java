// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Swerve;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Swerve.SwerveDriveTrain;

public class SwerveBrake extends Command {
  private final SwerveDriveTrain m_swerveDriveTrain;

  /** Creates a new SwerveBrake. */
  public SwerveBrake(final SwerveDriveTrain swerveDriveTrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.m_swerveDriveTrain = swerveDriveTrain;
    addRequirements(swerveDriveTrain);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_swerveDriveTrain.drive(0, 0, 0, true, false, false);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_swerveDriveTrain.drive(0, 0, 0, true, false, false);
  
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
