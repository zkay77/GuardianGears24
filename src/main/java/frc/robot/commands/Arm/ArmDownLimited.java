// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Arm;

import frc.robot.subsystems.ArmSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class ArmDownLimited extends Command {
  private final ArmSubsystem armSubsystem;

  /** Creates a new ArmDownLimited. */
  public ArmDownLimited(ArmSubsystem m_armSubsystem) {
    // Set armSubsystem equal to m_armSubsystem so m_armSubsystem can be used outside of the constructor
    armSubsystem = m_armSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_armSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Pass a negative value into the spinMotor method in armSubsystem,
    // makes the motor spin backwards (counterclockwise)
    if(ArmSubsystem.upArmLimitSwitch.get()) {
      armSubsystem.spinMotor(-.5);
    } else {
      armSubsystem.spinMotor(0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // Stop the motor
    armSubsystem.spinMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
