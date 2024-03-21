// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PneumaticsSubsystem extends SubsystemBase {
  /** Creates a new PneumaticsSubsytem. */
  public PneumaticsSubsystem() {}

  //Compressor compressor = new Compressor(Constants.CompressorPort, PneumaticsModuleType.CTREPCM);
  DoubleSolenoid solenoid = new DoubleSolenoid(Constants.SolenoidModule,PneumaticsModuleType.CTREPCM,Constants.fowardChannel,Constants.reverseChannel);
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("Grab", solenoid.isFwdSolenoidDisabled());

  }

  public void turnOnCompressor(){
    //compressor.enableDigital();
  }

  public void turnOffCompressor(){
    //compressor.disable();
  }
/*
  public boolean getPressureSwitchValue(){
    return compressor.getPressureSwitchValue();
  }
*/
  public void openSolenoid(){
    solenoid.set(Value.kReverse);
  }
  
  public void closeSolenoid(){
    solenoid.set(Value.kForward);
  }

}