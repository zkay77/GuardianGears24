// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Swerve;

import frc.robot.Constants;

//motor controls to import: feedbackDevice, NeutralMode, Remote Sensor Source,
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;

//talonFX to import: TalonFX Control Mode, TalonFX, TalonFX Configuration
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;

//sensors to import: CANCoder, CANCoder Configuration
import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenix.sensors.CANCoderConfiguration;

//math to import: Rotation2d, SwerveModuleState, Units
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
 import edu.wpi.first.math.util.Units;

public class SwerveModuleMK3 {

  // set constant PIDF values
  private static final double kDriveP = 5.0; //need to pick these values
  private static final double kDriveI = 0.01; //these are from 2022
  private static final double kDriveD = 0.0;  //TODO: figure out what these should be 
  private static final double kDriveF = 0.2;

  // set constant PID values
  private static final double kAngleP = 1.0;
  private static final double kAngleI = 0.0;
  private static final double kAngleD = 0.0;

  // set ticks count
  private static double kEncoderTicksPerRotation = 4096;

  // create objects: driveMotor, angleMotor, canCoder, offset
  private TalonFX driveMotor;
  private TalonFX angleMotor;
  private CANCoder canCoder;
  private Rotation2d offset;
  private boolean isCoast;

                        //Pass in: created objects
  public SwerveModuleMK3(TalonFX driveMotor, TalonFX angleMotor, CANCoder canCoder, Rotation2d offset, boolean isCoast, boolean isInverted) {
    
    //set passed in objects to created objects 
    this.driveMotor = driveMotor;
    this.angleMotor = angleMotor;
    this.canCoder = canCoder;
    this.offset = offset;
    this.isCoast = isCoast;

//angle motor settings
    // create angle TalonFX Configuration 
    TalonFXConfiguration angleTalonFXConfiguration = new TalonFXConfiguration();

    // set PID values
    angleTalonFXConfiguration.slot0.kP = kAngleP;
    angleTalonFXConfiguration.slot0.kI = kAngleI;
    angleTalonFXConfiguration.slot0.kD = kAngleD;

    // set remote Sensor to canCoder
    angleTalonFXConfiguration.remoteFilter0.remoteSensorDeviceID = canCoder.getDeviceID();
    angleTalonFXConfiguration.remoteFilter0.remoteSensorSource = RemoteSensorSource.CANCoder;

    // set feedback Sensor to remote Sensor
    angleTalonFXConfiguration.primaryPID.selectedFeedbackSensor = FeedbackDevice.RemoteSensor0;
    
    // configure created Configuration^^
    angleMotor.configAllSettings(angleTalonFXConfiguration);
    // set nuetral mode to break
    angleMotor.setNeutralMode(NeutralMode.Brake);

    

  //drive motor settings
    // creat drive Talon Configuration
    TalonFXConfiguration driveTalonFXConfiguration = new TalonFXConfiguration(); 
    // set PIDF values 
    driveTalonFXConfiguration.slot0.kP = kDriveP; 
    driveTalonFXConfiguration.slot0.kI = kDriveI; 
    driveTalonFXConfiguration.slot0.kD = kDriveD;
    driveTalonFXConfiguration.slot0.kF = kDriveF; 


    // configure created Configureation^^ 
    driveMotor.configAllSettings(driveTalonFXConfiguration); 
    // set nuetral mode to break
    if(isCoast)
      driveMotor.setNeutralMode(NeutralMode.Coast); 
    else{
      driveMotor.setNeutralMode(NeutralMode.Brake); 
    }
    // sets inverted based off of individual module
    driveMotor.setInverted(isInverted);

    CANCoderConfiguration canCoderConfiguration = new CANCoderConfiguration(); 
    canCoderConfiguration.magnetOffsetDegrees = offset.getDegrees(); 
    canCoder.configAllSettings(canCoderConfiguration); 

  }

  //method to get Angle (from CANCoder degrees)
  public Rotation2d getAngle() {
    return Rotation2d.fromDegrees(canCoder.getAbsolutePosition()); 
  }
  //method to get Raw Angle (from CANCoder double)
  public double getRawAngle() {
    return canCoder.getAbsolutePosition(); 
  }

  //method to set Desired State 
  public void setDesiredState(SwerveModuleState desiredState) {
    Rotation2d currentRotation = getAngle(); 
    SwerveModuleState state = SwerveModuleState.optimize(desiredState, currentRotation); 

    Rotation2d rotationDelta = state.angle.minus(currentRotation); 

    double deltaTicks = (rotationDelta.getDegrees() / 360) * kEncoderTicksPerRotation;

    double currentTicks = canCoder.getPosition() / canCoder.configGetFeedbackCoefficient(); 
    double desiredTicks = currentTicks + deltaTicks; 
    
    //below is a line to comment out from step 5 
    angleMotor.set(TalonFXControlMode.Position, desiredTicks); 

    double feetPerSecond = Units.metersToFeet(state.speedMetersPerSecond); 

    //below is a line to comment out from step 5 
    driveMotor.set(TalonFXControlMode.PercentOutput, feetPerSecond / Constants.kMaxSpeed); 
  }
}
