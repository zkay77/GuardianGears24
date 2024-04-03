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
  public ArmSubsystem() {

  }

  public void spinMotor(double speed){
    if (speed > 0) {
        if (topArmLimitSwitch.get()) {
            // We are going up and top limit is tripped so stop
            armMotor.set(0);
        } else {
            // We are going up but top limit is not tripped so go at commanded speed
            armMotor.set(speed);
        }
    } else {
        if (bottomArmLimitSwitch.get()) {
            // We are going down and bottom limit is tripped so stop
            armMotor.set(0);
        } else {
            // We are going down but bottom limit is not tripped so go at commanded speed
            armMotor.set(speed);
        }
    }
    }

  @Override
  public void periodic(){ // This method will be called once per scheduler run

    }
}