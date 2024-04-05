// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import frc.robot.commands.Auto.AutoSwerveDrive;
import frc.robot.commands.Auto.AutoSwerveTurn;
import frc.robot.commands.Auto.SwerveCalibrate;
import frc.robot.commands.Auto.AutoIntakeOn;
import frc.robot.commands.Auto.AutoDelivery;
import frc.robot.commands.Auto.AutoMoveArm;
import frc.robot.commands.Swerve.SwerveBrake;
import frc.robot.commands.Swerve.SwerveDriveCommand;
import frc.robot.commands.ArmUp;
import frc.robot.commands.ArmDown;
import frc.robot.commands.DeliveryIn;
import frc.robot.commands.DeliveryOut;
import frc.robot.commands.IntakeOn;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DeliverySubsystem;
import frc.robot.subsystems.IntakeSubsystem;
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
      private static final XboxController xbox2 = new XboxController(1);

      private final SwerveDriveTrain driveTrain = new SwerveDriveTrain();
      private final ArmSubsystem armSubsystem = new ArmSubsystem();
      private final DeliverySubsystem deliverySubsystem = new DeliverySubsystem();
      private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();



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
    // (In SwerveDriveCommand line 54) Calibrate gyro, RB
    // (In SwerveDriveCommand line 55) Set fieldRelative to false, LB
    // Swerve drive, driver 1
    driveTrain.setDefaultCommand(new SwerveDriveCommand(driveTrain, xbox1)); 
    // Brake swerve, button 11 (LT), driver 1
    new JoystickButton(xbox1, 11).whileTrue(new SwerveBrake(driveTrain)); 
    // Intake on, button 12 (RT), driver 1
    new JoystickButton(xbox1, 12).whileTrue(new IntakeOn(intakeSubsystem, true));
    new JoystickButton(xbox1, 12).whileFalse(new IntakeOn(intakeSubsystem, false)); 
    // Arm up, button 4 (Y), driver 2
    new JoystickButton(xbox1, 4).whileTrue(new ArmUp(armSubsystem, true)); 
    new JoystickButton(xbox1, 4).whileFalse(new ArmUp(armSubsystem, false)); 
    // Arm down, button 1 (A), driver 2
    new JoystickButton(xbox1, 1).whileTrue(new ArmDown(armSubsystem, true)); 
    new JoystickButton(xbox1, 1).whileFalse(new ArmDown(armSubsystem, false)); 
    // Delivery out, button 2 (B), driver 2
    new JoystickButton(xbox1, 2).whileTrue(new DeliveryOut(deliverySubsystem, true)); 
    new JoystickButton(xbox1, 2).whileFalse(new DeliveryOut(deliverySubsystem, false)); 
    // Delivery in, button 3 (X), driver 2
    new JoystickButton(xbox1, 3).whileTrue(new DeliveryIn(deliverySubsystem, true)); 
    new JoystickButton(xbox1, 3).whileFalse(new DeliveryIn(deliverySubsystem, false)); 
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return Commands.sequence(AutoDrive(-.10, .5), AutoTurn(.18, 1.7), AutoCalibrate());
  }

  public Command AutoTurn(double speed, double seconds){// numbers on board
    return new AutoSwerveTurn(driveTrain, speed).withTimeout(seconds)
    .andThen(new WaitCommand(.25));
  }

  public Command AutoDrive(double speed, double seconds){
    return new AutoSwerveDrive(driveTrain, speed).withTimeout(seconds)
    .andThen(new WaitCommand(.25));
  }

  public Command AutoCalibrate(){
    return new SwerveCalibrate(driveTrain);
  }

  public Command AutoIntakeOn(double seconds){
    return new AutoIntakeOn(intakeSubsystem, .12).withTimeout(seconds);
  }

  public Command AutoMoveArm(double speed){
    return new AutoMoveArm(armSubsystem, speed).withTimeout(1);
  }

  public Command AutoDelivery(double speed, double seconds){
    return new AutoDelivery(deliverySubsystem, .12).withTimeout(seconds);
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