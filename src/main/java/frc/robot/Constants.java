// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final double DEFAULT_SPEED = 1000.0;

  //CAN IDs and PWM channels
  public static final int armMotorID = 13;
  public static final int deliveryMotorID = 14;
  public static final int leftIntakeMotorID = 15;
  public static final int rightIntakeMotorID = 16;
  public static final int deliverySensorInChannel = 2;
  public static final int deliverySensorOutChannel = 3;
  public static final int upArmLimitSwitchChannel = 0;
    
  //set offsets for every module 
  public static double frontLeftOffset = 113.5;
  public static double frontRightOffset = 229;
  public static double backLeftOffset = 173.5;
  public static double backRightOffset = 92;

  //set CAN ids for every module
  public static final int frontLeftDriveID = 5; 
  public static final int frontLeftCANCoderID = 1; 
  public static final int frontLeftSteerID = 6; 
  public static final boolean frontLeftInverted = false;
  //put your can ID's here! 
  public static final int frontRightDriveID = 7; 
  public static final int frontRightCANCoderID = 2; 
  public static final int frontRightSteerID = 8; 
  public static final boolean frontRightInverted = false;
  //put your can ID's here! 
  public static final int backLeftDriveID = 9; 
  public static final int backLeftCANCoderID = 3; 
  public static final int backLeftSteerID = 10; 
  public static final boolean backLeftInverted = false;
  //put your can ID's here! 
  public static final int backRightDriveID = 11; 
  public static final int backRightCANCoderID = 4; 
  public static final int backRightSteerID = 12; 
  public static final boolean backRightInverted = false;

  //set constants: max speed, max angle speed, field calibration 
  public static final double kMaxSpeed = Units.feetToMeters(3.6); // 1 feet per second 
  public static final double kMaxAngularSpeed = Math.PI; // 1/2 rotation per second 
  public static double feildCalibration = 0; 
  public static boolean fieldRelative = false;

  public static final double DEAD_ZONE = 0.1;

}