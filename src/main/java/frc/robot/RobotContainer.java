// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import frc.robot.commands.AutoSwerveDrive;
import frc.robot.commands.AutoSwerveTurn;
//import frc.robot.Constants.OperatorConstants;

//import frc.robot.commands.CompressorControl;

//import frc.robot.commands.SolenoidClose;
//import frc.robot.commands.SolenoidOpen;
import frc.robot.commands.SwerveBreak;
import frc.robot.commands.SwerveCalibrate;
import frc.robot.commands.SwerveDriveCommand;

//import frc.robot.subsystems.PneumaticsSubsystem;
import frc.robot.subsystems.SwerveDriveTrain;
import edu.wpi.first.wpilibj.Joystick;
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
  //private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  //private final CommandXboxController m_driverController =
      //new CommandXboxController(OperatorConstants.kDriverControllerPort);


      private static final XboxController xbox1 = new XboxController(0);
      //private static final XboxController xbox2 = new XboxController(1);


      private final SwerveDriveTrain driveTrain = new SwerveDriveTrain();


      //private final PneumaticsSubsystem m_pneumaticsSubsystem = new PneumaticsSubsystem();
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
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    //new Trigger(m_exampleSubsystem::exampleCondition)
    //    .onTrue(new ExampleCommand(m_exampleSubsystem));

    driveTrain.setDefaultCommand(new SwerveDriveCommand(driveTrain, xbox1));

    new JoystickButton(xbox1, 5).whileTrue(new SwerveBreak(driveTrain));


  
    //new JoystickButton(xbox1, 2).toggleOnTrue(new SolenoidOpen(m_pneumaticsSubsystem));
   
    
    //new JoystickButton(xbox2, 2).toggleOnFalse(new SolenoidClose(m_pneumaticsSubsystem));
    
    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    //m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Commands.sequence(AutoDrive(-.10), AutoTurn(), AutoCalibrate());//, AutoOpen(), AutoElevatorDown(), AutoClose(), AutoElevatorUp(2), AutoArm(1.3),AutoTurn());
    //return AutoDrive();
    
  }

  //public Command AutoOpen(){
   // return new SolenoidOpen(m_pneumaticsSubsystem).withTimeout(.5);
 // }

  
 // public Command AutoClose(){
    //return new SolenoidClose(m_pneumaticsSubsystem).withTimeout(.5);
 // }

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
