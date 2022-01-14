// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  Talon leftFront;
  Talon rightFront;
  Talon leftBack;
  Talon rightBack;
  SpeedControllerGroup leftMotors;
  SpeedControllerGroup rightMotors;
  DifferentialDrive drive;

  /** Creates a new DriveTrain. 
   * 
   * This is the "constructor", and here we are essentially setting up some 
   * motor controlers. Each motor on the robot is connected to a controller, and 
   * those controllers are mapped in the controls here. 
   * 
   * Each controller is represented by an object (a class), so it is important to 
   * use the correct class for your controller. In this case, the motor controller objects
   * being used are not correct for what is on our robot. Our motors are "Falcons", which have
   * an integrate TalonFX motor controller. I would suggest looking at
   * https://docs.ctre-phoenix.com/en/stable/ch15_WPIDrive.html
   * for the correct class you will want to use. Note you will need to install some 
   * "vendor libraries", Ann has an email from me with the link for how to do that.
   * 
   * Here Constants.LEFT_Front, etc. will be the CAN IDs of the motor controllers.
  */
  public DriveTrain() {
    leftFront = new Talon(Constants.LEFT_Front);
    leftFront.setInverted(false);
    rightFront = new Talon(Constants.RIGHT_Front);
    rightFront.setInverted(false);
    leftBack = new Talon(Constants.LEFT_Front);
    leftBack.setInverted(false);
    rightBack = new Talon(Constants.RIGHT_BACK);
    rightBack.setInverted(false);

    leftMotors = new SpeedControllerGroup(leftFront, leftBack);
    rightMotors = new SpeedControllerGroup(rightFront, rightBack);
    drive = new DifferentialDrive (leftMotors, rightMotors);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void drivewithJoysticks(XboxController controller, double speed)
  {
    drive.arcadeDrive(controller.getRawAxis(Constants.XBOX_LEFT_Y_AXIS)*speed, controller.getRawAxis (Constants.XBOX_LEFT_X_AXIS)*speed );
  }

  public void driveFoward(double speed)
  {
    drive.tankDrive(speed, speed);
  }

  public void stop() 
  {
    drive.stopMotor();
  }
}
