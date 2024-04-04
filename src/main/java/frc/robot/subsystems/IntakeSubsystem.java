// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
  static CANSparkMax leftIntakeMotor = new CANSparkMax(Constants.leftIntakeMotorID, MotorType.kBrushless);
  static CANSparkMax rightIntakeMotor = new CANSparkMax(Constants.rightIntakeMotorID, MotorType.kBrushless);
  /** Creates a new IntakeSubsystem. */
  public IntakeSubsystem() {}

  @Override
  public void periodic() {} // This method will be called once per scheduler run
  
  public void spinMotors(double speed) {
    leftIntakeMotor.set(speed);
    rightIntakeMotor.set(-speed);
  }  
}
