// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class AutoIntakeOn extends Command {
  /** Creates a new AutoIntakeOn. */
  private final IntakeSubsystem intakeSubsystem;
  private final double speed;
 
  public AutoIntakeOn(final IntakeSubsystem intakeSubsystem, final double speed) {
    this.intakeSubsystem = intakeSubsystem;
    this.speed = speed;
    addRequirements(this.intakeSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putString("Auto Status", "Intake Moving");
    intakeSubsystem.spinMotors(speed);
  } 
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    SmartDashboard.putString("Auto Status", "Stationary");
    intakeSubsystem.spinMotors(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
