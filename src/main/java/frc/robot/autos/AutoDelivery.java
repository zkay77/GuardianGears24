// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autos;

//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DeliverySubsystem;

public class AutoDelivery extends Command {
  /** Creates a new AutoDelivery. */
  private final DeliverySubsystem deliverySubsystem;
  private final double speed;
 
  public AutoDelivery(final DeliverySubsystem m_deliverySubsystem, final double m_speed) {
    // Set deliverySubsystem equal to m_deliverySubsystem so m_deliverySubsystem can be used outside of the constructor
    deliverySubsystem = m_deliverySubsystem;
    // Do the same with speed and m_speed
    speed = m_speed;
    addRequirements(deliverySubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Set the motor to the speed passed from RobotContainer
    deliverySubsystem.spinMotor(speed);
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
