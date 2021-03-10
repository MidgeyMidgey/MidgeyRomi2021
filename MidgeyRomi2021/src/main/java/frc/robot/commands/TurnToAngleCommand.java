// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.RomiDrivetrain;
import edu.wpi.first.wpiutil.math.MathUtil;

public class TurnToAngleCommand extends CommandBase {
  RomiDrivetrain m_drive;
  double leftSpeed = 0;
  double rightSpeed = 0;

  // what is our angle change each time through?
  double lastAngle = 0;
  double delta_sum = 0;
  int delta_count = 0;

  public TurnToAngleCommand(RomiDrivetrain drive) {
    m_drive = drive;
    System.out.println("**** IN TURN");
  }

  @Override
  public void initialize() {
    m_drive.resetGyro();
    lastAngle = 0.0;
    System.out.println("**** RESET");
  }

  @Override
  public void execute() {
    double currentAngle = m_drive.getAngleZ();
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
    m_drive.tankDrive(-leftSpeed, rightSpeed);
  }

  @Override
  public void end(boolean interrupted) {
    m_drive.tankDrive(0, 0);
    System.out.println("**** DONE TURN");
    System.out.println(delta_count/delta_sum);
  }

  @Override
  public boolean isFinished() {
    double theta = m_drive.getAngleZ();
    boolean isDone = Math.abs(Constants.TARGET_ANGLE - theta) < 0.7;
    if (isDone) {
      System.out.println("**** At angle");
      System.out.println(theta);
    }
    return isDone;
  }
}
