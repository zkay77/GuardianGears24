// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto;

//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DeliverySubsystem;

public class AutoDelivery extends Command {
  /** Creates a new AutoDelivery. */
  private final DeliverySubsystem deliverySubsystem;
  private final double speed;
 
  public AutoDelivery(final DeliverySubsystem deliverySubsystem, final double speed) {
    this.deliverySubsystem = deliverySubsystem;
    this.speed = speed;
    addRequirements(this.deliverySubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //SmartDashboard.putString("Auto Status", "Delivery Moving");
    deliverySubsystem.spinMotor(speed);
    /*
    if(DeliverySubsystem.deliverySensorIn.get() && DeliverySubsystem.deliverySensorOut.get()) {
      SmartDashboard.putString("Auto Status", "Delivery Moving");
      deliverySubsystem.spinMotor(speed);
      }
    else{
      deliverySubsystem.spinMotor(0);
      }
    */
  } 
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //SmartDashboard.putString("Auto Status", "Stationary");
    deliverySubsystem.spinMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
