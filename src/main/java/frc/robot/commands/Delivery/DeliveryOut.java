// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Delivery;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DeliverySubsystem;

public class DeliveryOut extends Command {
  private final DeliverySubsystem deliverySubsystem;

  /** Creates a new DeliveryOut. */
  public DeliveryOut(DeliverySubsystem m_deliverySubsystem) {
    // Set deliverySubsystem equal to m_deliverySubsystem so m_deliverySubsystem can be used outside of the constructor
    deliverySubsystem = m_deliverySubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_deliverySubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() { // speed value has to be negative to go out
    deliverySubsystem.spinMotor(-1);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // Stop motor
    deliverySubsystem.spinMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}