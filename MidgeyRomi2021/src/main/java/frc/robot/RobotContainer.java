// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.RomiDrivetrain;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.tankDriveCommand;
import frc.robot.sensors.RomiGyro;

public class RobotContainer {
  final RomiDrivetrain m_romiDrivetrain = new RomiDrivetrain();
  final RomiGyro m_romiGyro = new RomiGyro();
  final ExampleCommand m_autoCommand = new ExampleCommand(m_romiDrivetrain);
  final XboxController m_controller = new XboxController(0);

  public RobotContainer() {
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    m_romiDrivetrain.setDefaultCommand(getTankDriveCommand());
  }

  public Command getAutonomousCommand() {
    return m_autoCommand;
  }

  public Command getTankDriveCommand(){
    return new tankDriveCommand(m_romiDrivetrain, () -> -m_controller.getRawAxis(1), () -> m_controller.getRawAxis(5));
  }

  public void setSmartDashboard(){
    SmartDashboard.putNumber("Left Encoder", m_romiDrivetrain.getLeftDistanceInch());
    SmartDashboard.putNumber("Right Encoder", m_romiDrivetrain.getRightDistanceInch());
    //SmartDashboard.putNumber("Angle X", m_romiGyro.getAngleX());
    //SmartDashboard.putNumber("Angle Y", m_romiGyro.getAngleY());
    //SmartDashboard.putNumber("Angle Z", m_romiGyro.getAngleZ());
    SmartDashboard.putNumber("key", m_romiDrivetrain.getAccelX());
    SmartDashboard.putNumber("key", m_romiDrivetrain.getAccelY());
    SmartDashboard.putNumber("key", m_romiDrivetrain.getAccelZ());
  }
}
