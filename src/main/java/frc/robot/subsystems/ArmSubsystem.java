// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;

public class ArmSubsystem extends SubsystemBase {
  /** Creates a new ArmSubsystem. */
  public ArmSubsystem() {

  }

  public void activateMotor(double speed){
    armMotor.set(speed);
  }

  static PWMTalonSRX armMotor = new PWMTalonSRX(Constants.armMotorChannel);

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  
  public static void turnOnMotor(double speed){
    armMotor.set(speed);
  }


}
