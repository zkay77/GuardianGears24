// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;

public class ArmSubsystem extends SubsystemBase {
  // Create an object to control the motor with a spark max motor controller, 
  // pass it the CAN ID from constants, and we're using a CIM motor which is brushed
  static CANSparkMax armMotor = new CANSparkMax(Constants.armMotorID, MotorType.kBrushed);
  // Use DigitalInput to create objects for up and down limit switches (we never got to use these)
  // Pass them each their PWM channel from constants
  public static DigitalInput upArmLimitSwitch = new DigitalInput(Constants.upArmLimitSwitchChannel);
  public static DigitalInput downArmLimitSwitch = new DigitalInput(Constants.downArmLimitSwitchChannel);
 
  /** Creates a new ArmSubsystem. */
  public ArmSubsystem() {

  }

  public void spinMotor(double speed){
    // set the CIM motor to whatever speed is passed in
    armMotor.set(speed);
  }

  @Override
  public void periodic(){ // This method will be called once per scheduler run

  }
}