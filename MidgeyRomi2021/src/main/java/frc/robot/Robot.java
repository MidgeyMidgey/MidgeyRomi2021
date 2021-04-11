// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.AutoBaselineCommand;

public class Robot extends TimedRobot {
  private Command m_autoCommand;
  private RobotContainer m_robotContainer;

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
  }

  public Command getAutoCommand(){
    return new AutoBaselineCommand(m_robotContainer.m_romiDrivetrain, m_robotContainer.m_limelight);
  }
    
  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {
    m_robotContainer.m_rsl.setInterval(0.0);
  }

  @Override
  public void disabledPeriodic() {}

  @Override
  public void autonomousInit() {
    // Get selected routine from the SmartDashboard
    m_autoCommand = m_robotContainer.getAutonomousCommand();

    if (m_autoCommand != null) {
      m_autoCommand.schedule();
      SmartDashboard.putString("temp1", "Auto init working");
    }

    m_robotContainer.m_rsl.setInterval(0.8); // slow blink
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    if (m_autoCommand != null) {
      m_autoCommand.cancel();
    }
    m_robotContainer.m_rsl.setInterval(0.4); // regular blink
  }

  @Override
  public void teleopPeriodic() {
    m_robotContainer.setSmartDashboard();
  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
    m_robotContainer.m_rsl.setInterval(0.25); // fastest blink
  }

  @Override
  public void testPeriodic() {
    m_robotContainer.m_rsl.periodic(); // not called otherwise?
  }
}
