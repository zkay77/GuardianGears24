// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import frc.robot.commands.Auto.AutoSwerveDrive;
import frc.robot.commands.Auto.AutoSwerveTurn;
import frc.robot.commands.Swerve.SwerveBrake;
import frc.robot.commands.Swerve.SwerveCalibrate;
import frc.robot.commands.Swerve.SwerveDriveCommand;
import frc.robot.commands.ArmUp;
import frc.robot.commands.ArmDown;
import frc.robot.commands.DeliveryIn;
import frc.robot.commands.DeliveryOut;
import frc.robot.commands.IntakeOn;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ClimberSubsystem;
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
      private final ClimberSubsystem climberSubsystem = new ClimberSubsystem();

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
    // Swerve drive, driver 1
    driveTrain.setDefaultCommand(new SwerveDriveCommand(driveTrain, xbox1)); 
    // Brake swerve, button 5 (LB), driver 1
    new JoystickButton(xbox1, 5).whileTrue(new SwerveBrake(driveTrain)); 
    // Intake on, button 6 (RB), driver 1
    new JoystickButton(xbox1, 6).whileTrue(new IntakeOn(intakeSubsystem, true)); 
    new JoystickButton(xbox1, 6).whileFalse(new IntakeOn(intakeSubsystem, false)); 
    // Climber up, button 12 (RT), driver 1
    // Climber down, button 11 (LT), driver 1
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