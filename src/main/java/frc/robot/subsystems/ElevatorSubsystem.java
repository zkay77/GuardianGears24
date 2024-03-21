// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;

public class ElevatorSubsystem extends SubsystemBase {
  /** Creates a new ElevatorSubsystem. */
  public ElevatorSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  static CANSparkMax elevatorMotor = new CANSparkMax(Constants.sparkMaxDeviceID,MotorType.kBrushless);
  public static DigitalInput topSwitch = new DigitalInput(Constants.topSwitchChannel);
  public static DigitalInput bottomSwitch = new DigitalInput(Constants.bottomSwitchChannel);

public static void turnOnMotor(double speed){
  elevatorMotor.set(speed);
}


}
