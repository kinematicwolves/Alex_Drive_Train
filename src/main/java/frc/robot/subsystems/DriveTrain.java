// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
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

  /** Creates a new DriveTrain. */
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
