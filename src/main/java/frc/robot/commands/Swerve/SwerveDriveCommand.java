// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Swerve;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.math.filter.SlewRateLimiter;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Swerve.SwerveDriveTrain;

public class SwerveDriveCommand extends Command {
  
  //create objects: robotContainer, driveTrain, xbox
  //private RobotContainer robotContainer;
  private SwerveDriveTrain driveTrain;
  private final XboxController xbox;

  //set constants: xSpeed, ySpeed, rotSpeed
  private final SlewRateLimiter xSpeedLimiter = new SlewRateLimiter(.6);
  private final SlewRateLimiter ySpeedLimiter = new SlewRateLimiter(.6);
  private final SlewRateLimiter rotLimiter = new SlewRateLimiter(.7);

  public SwerveDriveCommand(SwerveDriveTrain m_driveTrain, XboxController m_xbox) {
    
    this.driveTrain = m_driveTrain;
    this.xbox = m_xbox; //set controller
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_driveTrain);

    
    
  }

  @Override
  public void execute() {

    //calculate ySpeed, xSpeed, rotSpeed
    final var xSpeed = -xSpeedLimiter.calculate(RobotContainer.getLeftXPower() * Constants.kMaxSpeed);  //times max speed
    final var ySpeed = -ySpeedLimiter.calculate(RobotContainer.getLeftYPower()* Constants.kMaxSpeed);  //times max speed
    final var rot = -rotLimiter.calculate(RobotContainer.getRightXPower()* Constants.kMaxAngularSpeed);  //times max angle speed

    //calibrate gyro
    boolean calibrate = xbox.getBButton();
    //hold left bumper to activate robot centric gyro
    boolean fieldRelative = xbox.getLeftBumper();
    SmartDashboard.putBoolean("fieldRelative", !fieldRelative);

    //drive method
    driveTrain.drive(xSpeed, ySpeed, rot, !fieldRelative, calibrate, false);

  }

  @Override
  public void end(boolean interrupted) {
    driveTrain.drive(0, 0, 0, Constants.fieldRelative, false, false);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

 
}
