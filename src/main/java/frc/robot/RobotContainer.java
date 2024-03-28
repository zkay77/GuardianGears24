// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import frc.robot.commands.Auto.AutoSwerveDrive;
import frc.robot.commands.Auto.AutoSwerveTurn;
import frc.robot.commands.Swerve.SwerveBreak;
import frc.robot.commands.Swerve.SwerveCalibrate;
import frc.robot.commands.Swerve.SwerveDriveCommand;
import frc.robot.subsystems.Swerve.SwerveDriveTrain;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
      private static final XboxController xbox1 = new XboxController(0);

      private final SwerveDriveTrain driveTrain = new SwerveDriveTrain();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    driveTrain.setDefaultCommand(new SwerveDriveCommand(driveTrain, xbox1));
    new JoystickButton(xbox1, 5).whileTrue(new SwerveBreak(driveTrain));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return Commands.sequence(AutoDrive(-.10), AutoTurn(), AutoCalibrate());
  }

  public Command AutoTurn(){// numbers on board
    return new AutoSwerveTurn(driveTrain, .18).withTimeout(1.7)
    .andThen(new WaitCommand(.25));
  }

  public Command AutoDrive(double speed){
    return new AutoSwerveDrive(driveTrain, speed).withTimeout(.5)
    .andThen(new WaitCommand(.25));
  }

  public Command AutoCalibrate(){
    return new SwerveCalibrate(driveTrain);
  }

  public static double getLeftYPower(){
    double drivePower = xbox1.getRawAxis(0);
    if (Math.abs(drivePower) < Constants.DEAD_ZONE){
      drivePower = 0.0;
    }

    return drivePower;
  }

  public static double getLeftXPower(){
    double drivePower = xbox1.getRawAxis(1);
    if (Math.abs(drivePower) < Constants.DEAD_ZONE){
      drivePower = 0.0;
    }

    return drivePower;
  }

  public static double getRightXPower(){
    double drivePower = xbox1.getRawAxis(4);
    if (Math.abs(drivePower) < Constants.DEAD_ZONE){
      drivePower = 0.0;
    }

    return drivePower;
  }
}