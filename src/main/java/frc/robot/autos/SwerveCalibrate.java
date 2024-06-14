// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autos;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Swerve.SwerveDriveTrain;

public class SwerveCalibrate extends Command {
  private final SwerveDriveTrain swerveDriveTrain;

  /** Creates a new SwerveCalibrate. */
  public SwerveCalibrate(final SwerveDriveTrain m_swerveDriveTrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.swerveDriveTrain = m_swerveDriveTrain;
    addRequirements(m_swerveDriveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    swerveDriveTrain.drive(0, 0, 0, true, true, true);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    swerveDriveTrain.drive(0, 0, 0, true, false, true);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
