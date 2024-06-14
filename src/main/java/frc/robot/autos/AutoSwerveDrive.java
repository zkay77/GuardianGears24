// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autos;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Swerve.SwerveDriveTrain;

public class AutoSwerveDrive extends Command {
  private final SwerveDriveTrain swerveDriveTrain;
  private final double speed;

  /** Creates a new AutoSwerveDrive. */
  public AutoSwerveDrive(final SwerveDriveTrain swerveDriveTrain) {
    this(swerveDriveTrain, Constants.DEFAULT_SPEED);
  }

  public AutoSwerveDrive(final SwerveDriveTrain m_swerveDriveTrain, final double m_speed){
    swerveDriveTrain = m_swerveDriveTrain;
    speed = m_speed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_swerveDriveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    swerveDriveTrain.drive(speed, 0, 0, false, false, true);
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
