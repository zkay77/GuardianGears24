// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SwerveDriveTrain;
import frc.robot.Constants;

public class AutoSwerveDrive extends Command {
  /** Creates a new AutoSwerveDrive. */
  private final SwerveDriveTrain m_swerveDriveTrain;
  private final double m_speed;
 
  public AutoSwerveDrive(final SwerveDriveTrain swerveDriveTrain) {
    this(swerveDriveTrain, Constants.DEFAULT_SPEED);
    //Use addRequirements() here to declare subsystem dependencies.
  }
  public AutoSwerveDrive(final SwerveDriveTrain swerveDriveTrain, final double speed){
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
    m_swerveDriveTrain.drive(m_speed,0, 0, false, false,true);
  } 
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_swerveDriveTrain.drive(0, 0, 0, true, false,true);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
