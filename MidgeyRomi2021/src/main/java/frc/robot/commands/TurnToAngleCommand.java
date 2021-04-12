// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.RomiDrivetrain;
import edu.wpi.first.wpiutil.math.MathUtil;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurnToAngleCommand extends CommandBase {
  RomiDrivetrain m_drive;
  double leftSpeed = 0;
  double rightSpeed = 0;

  // what is our angle change each time through?
  double startAngle = 0;
  double delta_sum = 0;
  int deltaCount = 0;

  public TurnToAngleCommand(RomiDrivetrain drive) {
    m_drive = drive;
    System.out.println("**** IN TURN");
  }

  @Override
  public void initialize() {
    startAngle = m_drive.getAngleZ();
  }

  @Override
  public void execute() {
    double currentAngle = m_drive.getAngleZ() - startAngle;
    double delta = Math.abs(Constants.TARGET_ANGLE - currentAngle);
    delta = delta * 0.04 + 0.5;
 
    delta = Math.min(delta, Constants.K_TURN);
   
    if (currentAngle < Constants.TARGET_ANGLE) {
      leftSpeed = delta;
      rightSpeed = - delta;
    } else {
      leftSpeed = - delta;
      rightSpeed = delta;
    }
    m_drive.tankDrive(leftSpeed, rightSpeed);
    SmartDashboard.putNumber("AngleDelta", delta);
  }

  @Override
  public void end(boolean interrupted) {
    m_drive.tankDrive(0, 0);
    System.out.println("**** DONE TURN");
  }

  @Override
  public boolean isFinished() {
    double currentAngle = m_drive.getAngleZ() - startAngle;
    boolean isDone = Math.abs(Constants.TARGET_ANGLE - currentAngle) < 0.5;
    if (isDone) {
      System.out.println("**** At angle");
      System.out.println(currentAngle);
    }
    return isDone;
  }
}
