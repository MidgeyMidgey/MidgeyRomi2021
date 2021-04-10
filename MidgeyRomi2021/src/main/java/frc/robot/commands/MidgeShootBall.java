/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.LimelightSubsystem;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DigitalOutput;

public class MidgeShootBall extends CommandBase {
  private static final DigitalOutput m_rsl = new DigitalOutput(8);
  LimelightSubsystem m_limelightSubsystem;
  private double start_time;
  private static final double duration = 5.0; // Five seconds
  public MidgeShootBall(LimelightSubsystem LimelightSubsystem) {
     m_limelightSubsystem = LimelightSubsystem;
     m_rsl.set(false); // light off when done
  }

  @Override
  public void initialize() {
    start_time = Timer.getFPGATimestamp();
    m_rsl.set(true); // light on for shooting
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {
    m_rsl.set(false); // light off when done
  }

  @Override
  public boolean isFinished() {
    return Timer.getFPGATimestamp() - start_time > duration;
  }

}
