// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.sensors.RomiGyro;
import frc.robot.RobotContainer;

public class turnToAngleCommand extends CommandBase {

  double leftSpeed = 0;
  double rightSpeed = 0;

  public turnToAngleCommand() {
    
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    double currentAngle = RobotContainer.m_romiGyro.getAngleZ();
    if (currentAngle > Constants.TARGET_ANGLE) {
      leftSpeed = Constants.K_TURN;
      rightSpeed = - Constants.K_TURN;
    } else {
      leftSpeed = - Constants.K_TURN;
      rightSpeed = Constants.K_TURN;
    }
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
