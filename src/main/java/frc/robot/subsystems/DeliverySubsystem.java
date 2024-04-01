package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class DeliverySubsystem extends SubsystemBase {
  static CANSparkMax deliveryMotor = new CANSparkMax(Constants.deliveryMotorID, MotorType.kBrushless);
  // put sensor here later senorse :]

  /** Creates a new DeliverySubsystem. */
  public DeliverySubsystem() {
  //march 21 2024 hello old me-Isaac Gonterman
  }

  @Override
  public void periodic() { // This method will be called once per scheduler run

  }

public void spinMotor(double speed){
    deliveryMotor.set(speed);
  }
  
public static void turnOnMotor(double speed){
  deliveryMotor.set(speed);
  }


}

