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

public class TurnToAngleCommand extends CommandBase {

  double leftSpeed = 0;
  double rightSpeed = 0;

  // what is our angle change each time through?
  double lastAngle = 0;
  double delta_sum = 0;
  int delta_count = 0;

  public TurnToAngleCommand() {
    System.out.println("**** IN TURN");
  }

  @Override
  public void initialize() {
    RobotContainer.m_romiGyro.reset();
    lastAngle = 0.0;
    System.out.println("**** RESET");
  }

  @Override
  public void execute() {
    double currentAngle = RobotContainer.m_romiGyro.getAngleZ();
    double delta = Math.abs(Constants.TARGET_ANGLE - currentAngle);
    delta = delta * 0.01 + 0.26;
    delta = Math.min(delta, Constants.K_TURN);
    if (currentAngle > Constants.TARGET_ANGLE) {
      leftSpeed = delta;
      rightSpeed = - delta;
    } else {
      leftSpeed = - delta;
      rightSpeed = delta;
    }
    delta_count++;
    delta_sum += currentAngle - lastAngle;
    lastAngle = currentAngle;
    RobotContainer.m_romiDrivetrain.tankDrive(-leftSpeed, rightSpeed);
  }

  @Override
  public void end(boolean interrupted) {
    RobotContainer.m_romiDrivetrain.tankDrive(0, 0);
    System.out.println("**** DONE TURN");
    System.out.println(delta_count/delta_sum);
    System.out.println(RobotContainer.m_romiGyro.getAngleZ());
  }

  @Override
  public boolean isFinished() {
    double theta = RobotContainer.m_romiGyro.getAngleZ();
    boolean isDone = Math.abs(Constants.TARGET_ANGLE - theta) < 0.7;
    if (isDone) {
      System.out.println("**** At angle");
      System.out.println(theta);
    }
    return isDone;
  }
}
