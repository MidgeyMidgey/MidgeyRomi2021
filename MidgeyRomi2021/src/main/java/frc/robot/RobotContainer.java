// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.RomiDrivetrain;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.tankDriveCommand;

public class RobotContainer {
  private final RomiDrivetrain m_romiDrivetrain = new RomiDrivetrain();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_romiDrivetrain);
  private final XboxController m_controller = new XboxController(0);

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
}
