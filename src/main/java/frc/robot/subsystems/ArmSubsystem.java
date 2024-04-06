// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ArmSubsystem extends SubsystemBase {
  static CANSparkMax armMotor = new CANSparkMax(Constants.armMotorID, MotorType.kBrushed);
  public static DigitalInput upArmLimitSwitch = new DigitalInput(Constants.upArmLimitSwitchChannel);
  public static DigitalInput downArmLimitSwitch = new DigitalInput(Constants.downArmLimitSwitchChannel);
  /** Creates a new ArmSubsystem. */
  public ArmSubsystem() {}

  public void spinMotor(double speed){
    // If arm is moving up (speed greater than zero):
    if(speed > 0) {
      // if top limit switch is pressed (true): set speed to zero
        if(!upArmLimitSwitch.get()) {
            SmartDashboard.putString("Arm Status", "Top Limit Switch");
            armMotor.set(0);
        } 
      // if not, set to speed
        else {
            SmartDashboard.putString("Arm Status", "Moving Up");
            armMotor.set(speed);
        }
    // If arm is moving down (speed less than zero):
    } else {
      // if bottom limit switch is pressed (true): set speed to zero
        if (!downArmLimitSwitch.get()) {
            SmartDashboard.putString("Arm Status", "Bottom Limit Switch");
            armMotor.set(0);
        } 
      // if not, set to speed
        else {
            SmartDashboard.putString("Arm Status", "Moving Down");
            armMotor.set(speed);
        }
    }
  }

  public void brakeMotor(){
    armMotor.setIdleMode(CANSparkBase.IdleMode.kBrake);
  }

  public void coastMotor(){
    armMotor.setIdleMode(CANSparkBase.IdleMode.kCoast);
  }

  @Override
  public void periodic(){ // This method will be called once per scheduler run

  }
}