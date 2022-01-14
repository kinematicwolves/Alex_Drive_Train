// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

/**
 * An overview of how this works. When you created a new DriveWithJoysticks command, 
 * the command is "scheduled". So on the next schedular call, the initialize() method 
 * will run exactly once. So any initialization parameters your command needs (ex. 
 * setting a motor to an initialization speed) should happen here. Then as long as the 
 * command is active, the execute() method will run. Notice how here you are continuously
 * running the driveWithJoysticks method of the DriveTrain class. So whenever this command 
 * is active, that code is running and the robot will drive. 
 * 
 * So when does the command end?
 * It ends when the output of isFinished = true or the command is no longer scheduled. 
 * In this case, isFinished() just returns false, because we essentially always want 
 * the robot driving.
 * 
 * What does the end() method do?
 * This method is run when the command finishes. So if you had a motor running, you 
 * would probably want to turn it off in the block if it is no longer needed.
 * 
 */
public class DriveWithJoysticks extends CommandBase {
  private final DriveTrain driveTrain;
  /** 
   * Creates a new DriveWithJoysticks. This is the "constructor" and is called when the class
   * is created (instantiated is the proper term).
   * */
  public DriveWithJoysticks(DriveTrain dt) {
    // Use addRequirements() here to declare subsystem dependecies.
    // The purpose of addRequirements is so that two commands that require
    // The same subsystem cannot be run simultaneously. This would be like asking your dog
    // to come and sit at the same time, it wouldn't know what to do.
    driveTrain = dt;
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveTrain.drivewithJoysticks(RobotContainer.driverJoystick, Constants.DRIVETRAINSPEED);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
