// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Swerve; 


import edu.wpi.first.wpilibj2.command.SubsystemBase; 
import com.ctre.phoenix.motorcontrol.can.TalonFX; 
import com.ctre.phoenix.sensors.CANCoder;
import frc.robot.Constants; 
import edu.wpi.first.math.geometry.Translation2d; 
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState; 
import edu.wpi.first.math.util.Units;  
import com.kauailabs.navx.frc.AHRS; 
import edu.wpi.first.wpilibj.SPI; 
import edu.wpi.first.math.geometry.Rotation2d; 


public class SwerveDriveTrain extends SubsystemBase {


  //set gyro 
  public static AHRS gyro = new AHRS(SPI.Port.kMXP); 
  public boolean m_isCoast;

  //create Swerve Drive Kinematics 
  private SwerveDriveKinematics kinematics = new SwerveDriveKinematics( 
    new Translation2d(
      Units.inchesToMeters(9.591), //y 
      Units.inchesToMeters(9.591) //x
    ), 
    new Translation2d(
      Units.inchesToMeters(9.591), //y
      Units.inchesToMeters(-9.591) //x
    ), 
    new Translation2d(
      Units.inchesToMeters(-9.591), //y
      Units.inchesToMeters(9.591) //x
    ), 
    new Translation2d(
      Units.inchesToMeters(-9.591), //y
      Units.inchesToMeters(-9.591) //x
    )
  ); 
    
      
  //create array of Modules
  private SwerveModuleMK3[] modules = new SwerveModuleMK3[] {
    
    new SwerveModuleMK3(new TalonFX(Constants.frontLeftDriveID), new TalonFX(Constants.frontLeftSteerID), new CANCoder(Constants.frontLeftCANCoderID), Rotation2d.fromDegrees(Constants.frontLeftOffset), m_isCoast, Constants.frontLeftInverted), // Front Left
    new SwerveModuleMK3(new TalonFX(Constants.frontRightDriveID), new TalonFX(Constants.frontRightSteerID), new CANCoder(Constants.frontRightCANCoderID), Rotation2d.fromDegrees(Constants.frontRightOffset), m_isCoast, Constants.frontRightInverted), // Front Right
    new SwerveModuleMK3(new TalonFX(Constants.backLeftDriveID), new TalonFX(Constants.backLeftSteerID), new CANCoder(Constants.backLeftCANCoderID), Rotation2d.fromDegrees(Constants.backLeftOffset), m_isCoast, Constants.backLeftInverted), // Back Left
    new SwerveModuleMK3(new TalonFX(Constants.backRightDriveID), new TalonFX(Constants.backRightSteerID), new CANCoder(Constants.backRightCANCoderID), Rotation2d.fromDegrees(Constants.backRightOffset), m_isCoast, Constants.backRightInverted)  // Back Right
  }; 




  /** Creates a new SwerveDriveTrain. */
  public SwerveDriveTrain() {
    gyro.reset();
    //gyro.zeroYaw(); 
  }

  //Drive method 
  public void drive(double xSpeed, double ySpeed, double rotation, boolean fieldRelative, boolean calibrateGyro, boolean isCoast) {

    if(isCoast){
      this.m_isCoast = true;
    }else{
      this.m_isCoast = false;
    }

    if(calibrateGyro){
      gyro.reset(); // recalibrates gyro offset 
    } 
    // if fieldRelative is true, use speed and rotation from gyro
    SwerveModuleState[] states = kinematics.toSwerveModuleStates(fieldRelative ? ChassisSpeeds.fromFieldRelativeSpeeds(xSpeed, ySpeed, rotation, Rotation2d.fromDegrees(-gyro.getAngle())) : new ChassisSpeeds(xSpeed, ySpeed, rotation)); 

    SwerveDriveKinematics.desaturateWheelSpeeds(states, Constants.kMaxSpeed); 

    for(int i = 0; i < states.length; i++) { 
      SwerveModuleMK3 module = modules[i]; 
      SwerveModuleState state = states[i]; 
      //SmartDashboard.putNumber("Module " + String.valueOf(i) + " RawAngle", module.getRawAngle()); 
      //below is a line to comment out from step 5 
      module.setDesiredState(state); 
      /*
      SmartDashboard.putNumber("gyro Angle", gyro.getAngle());
      SmartDashboard.putNumber("gyro Q X", gyro.getQuaternionX());
      SmartDashboard.putNumber("gyro Q Y", gyro.getQuaternionY());
      SmartDashboard.putNumber("gyro Q Z", gyro.getQuaternionZ());
      SmartDashboard.putNumber("xSpeed", xSpeed);
      SmartDashboard.putNumber("ySpeed", ySpeed);
      SmartDashboard.putNumber("rotation", rotation);
      */
    }
  }

  @Override 
  public void periodic() {
    // This method will be called once per scheduler run
  } 

  @Override 
  public void simulationPeriodic() {
    // This method will be called once per schedule run during simulation
  }
} 
