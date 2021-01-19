// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.sensors.RomiGyro;
import frc.robot.RobotContainer;
import frc.robot.subsystems.RomiDrivetrain;
import edu.wpi.first.wpiutil.math.MathUtil;

public class turnToAngleCommand extends CommandBase {

  double leftSpeed = 0;
  double rightSpeed = 0;

  public turnToAngleCommand() {
    System.out.println("**** IN TURN");
  }

  @Override
  public void initialize() {
    RobotContainer.m_romiGyro.reset();
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
    System.out.println(leftSpeed);
    System.out.println(rightSpeed);
    RobotContainer.m_romiDrivetrain.tankDrive(leftSpeed, -rightSpeed);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return (Math.abs(RobotContainer.m_romiGyro.getAngleZ()) > Constants.TARGET_ANGLE);
      
  }
}
