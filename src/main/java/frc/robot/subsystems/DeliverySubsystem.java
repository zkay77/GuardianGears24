package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class DeliverySubsystem extends SubsystemBase {
  // Create an object to control the motor with a spark max motor controller, 
  // pass it the CAN ID from constants, and we're using a NEO motor which is brushless
  static CANSparkMax deliveryMotor = new CANSparkMax(Constants.deliveryMotorID, MotorType.kBrushless);
  // Use DigitalInput to create objects for sensors at both ends of the delivery head (we never used these)
  public static DigitalInput deliverySensorIn = new DigitalInput(Constants.deliverySensorInChannel);
  public static DigitalInput deliverySensorOut = new DigitalInput(Constants.deliverySensorOutChannel);

  /** Creates a new DeliverySubsystem. */
  public DeliverySubsystem() {
  //march 21 2024 hello old me-Isaac Gonterman
  }

  @Override
  public void periodic() { // This method will be called once per scheduler run

  }

  public void spinMotor(double speed){
    // set motor to speed passed from command
    deliveryMotor.set(speed);
  }
  
}

