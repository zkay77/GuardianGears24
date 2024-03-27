// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SwerveDriveTrain;
import frc.robot.Constants;

public class AutoSwerveTurn extends Command {
  private final SwerveDriveTrain m_swerveDriveTrain;
  private final double m_speed;
  /** Creates a new AutoSwerveTurn. */
  public AutoSwerveTurn(final SwerveDriveTrain SwerveDriveTrain) {
    // Use addRequirements() here to declare subsystem dependencies.
this(SwerveDriveTrain, Constants.DEFAULT_SPEED);
  }
  public AutoSwerveTurn(final SwerveDriveTrain swerveDriveTrain, final double speed) {
    this.m_swerveDriveTrain = swerveDriveTrain;
    this.m_speed = speed;
    addRequirements(this.m_swerveDriveTrain);
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_swerveDriveTrain.drive(0, 0, m_speed, true, false, true);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_swerveDriveTrain.drive(0, 0, 0, true, false, true);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
