// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;


//resources to import: controller, SlewRateLimiter, 
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.math.filter.SlewRateLimiter;

//files to import: DriveTrain Sybsystem, Module Subsystem, RobotContainer 
import frc.robot.subsystems.SwerveDriveTrain;
import frc.robot.subsystems.SwerveModuleMK3;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class SwerveDriveCommand extends Command {
  
  //create objects: robotContainer, driveTrain, xbox
  //private RobotContainer robotContainer;
  private SwerveDriveTrain driveTrain;
  private final XboxController xbox;

  //set constants: xSpeed, ySpeed, rotSpeed
  private final SlewRateLimiter xSpeedLimiter = new SlewRateLimiter(0.1);
  private final SlewRateLimiter ySpeedLimiter = new SlewRateLimiter(0.1);
  private final SlewRateLimiter rotLimiter = new SlewRateLimiter(0.1);

  public SwerveDriveCommand(SwerveDriveTrain m_driveTrain, XboxController m_xbox) {
    // Use addRequirements() here to declare subsystem dependencies.
    
    this.driveTrain = m_driveTrain;
    this.xbox = m_xbox; //set controller
    
    //requirements from subsystem
    addRequirements(m_driveTrain);

    
    
  }

  @Override
  public void execute() {

    //calculate ySpeed, xSpeed, rotSpeed
    final var xSpeed = -xSpeedLimiter.calculate(RobotContainer.getLeftXPower() * Constants.kMaxSpeed);  //times max speed
    final var ySpeed = -ySpeedLimiter.calculate(RobotContainer.getLeftYPower()* Constants.kMaxSpeed);  //times max speed
    final var rot = -rotLimiter.calculate(RobotContainer.getRightXPower()* Constants.kMaxAngularSpeed);  //times max angle speed
    SmartDashboard.putNumber("RightXPower", RobotContainer.getRightXPower());

    //set calibration type (self / field)
    boolean calibrate = xbox.getRightBumper();

    //drive method
    driveTrain.drive(xSpeed, ySpeed, rot, Constants.fieldRelative, calibrate, true);

  }

  @Override
  public void end(boolean interrupted) {
    driveTrain.drive(0, 0, 0, Constants.fieldRelative, false, true);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

 
}
