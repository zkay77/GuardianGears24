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
  static CANSparkMax armMotor = new CANSparkMax(Constants.armMotorID, MotorType.kBrushed);
  public static DigitalInput topArmLimitSwitch = new DigitalInput(Constants.topArmLimitSwitchChannel);
  public static DigitalInput bottomArmLimitSwitch = new DigitalInput(Constants.bottomArmLimitSwitchChannel);
  /** Creates a new ArmSubsystem. */
  public ArmSubsystem() {}

  public void spinMotor(double speed){
    // If arm is moving up (speed greater than zero):
    if(speed > 0) {
      // if top limit switch is pressed (true): set speed to zero
        if(topArmLimitSwitch.get()) {
            armMotor.set(0);
        } 
      // if not, set to speed
        else {
            armMotor.set(speed);
        }
    // If arm is moving down (speed less than zero):
    } else {
      // if bottom limit switch is pressed (true): set speed to zero
        if (bottomArmLimitSwitch.get()) {
            armMotor.set(0);
        } 
      // if not, set to speed
        else {
            armMotor.set(speed);
        }
    }
  }

  @Override
  public void periodic(){ // This method will be called once per scheduler run

  }
}