// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DeliverySubsystem;

public class DeliveryOut extends Command {
  private final DeliverySubsystem deliverySubsystem;
  private boolean sensorsHaveTriggered;

  /** Creates a new DeliveryOut. */
  public DeliveryOut(DeliverySubsystem deliverySubsystem) {
    this.deliverySubsystem = deliverySubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(deliverySubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(DeliverySubsystem.deliverySensorIn.get() && DeliverySubsystem.deliverySensorOut.get()){
      sensorsHaveTriggered = true;
    }
    else{
      sensorsHaveTriggered = false;
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() { // speed value has to be negative to go out
    SmartDashboard.putBoolean("sensorsHaveTriggered", sensorsHaveTriggered);
    if(!sensorsHaveTriggered){
      if(DeliverySubsystem.deliverySensorIn.get() && DeliverySubsystem.deliverySensorOut.get()) {
        SmartDashboard.putString("Delivery Status", "Delivery out sensors not triggered");
        deliverySubsystem.spinMotor(-.7);
      }
      else{
        deliverySubsystem.spinMotor(0);
      }
    }
    else{
      SmartDashboard.putString("Delivery Status", "Delivery out after sensors triggered");
      deliverySubsystem.spinMotor(-.7);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    SmartDashboard.putString("Delivery Status", "Stationary");
    deliverySubsystem.spinMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}