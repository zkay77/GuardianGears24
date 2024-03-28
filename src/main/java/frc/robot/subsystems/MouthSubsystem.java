package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.DigitalInput;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class MouthSubsystem extends SubsystemBase {
  /** Creates a new ArmSubsystem. */
  public MouthSubsystem() {
//march 21 2024 hello old me-Isaac Gonterman
  }
  
  static CANSparkMax mouthMotor = new CANSparkMax(Constants.armCanControllerId, MotorType.kBrushless );
  // put sensor here later senorse :]

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

public void spinMotor (double speed){
    mouthMotor.set(speed);
  }
  
public static void turnOnMotor(double speed){
  mouthMotor.set(speed);
}


}

