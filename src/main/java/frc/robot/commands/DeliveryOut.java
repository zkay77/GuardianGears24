// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DeliverySubsystem;

public class DeliveryOut extends Command {
  private final DeliverySubsystem deliverySubsystem;
  boolean isPressed;

  /** Creates a new DeliveryOut. */
  public DeliveryOut(DeliverySubsystem deliverySubsystem, boolean isPressed) {
    this.deliverySubsystem = deliverySubsystem;
    this.isPressed = isPressed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(deliverySubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  if(DeliverySubsystem.deliverySensorIn.get() && DeliverySubsystem.deliverySensorOut.get() && isPressed){
    deliverySubsystem.spinMotor(-1);
    }
  else{
    deliverySubsystem.spinMotor(0);
    }
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