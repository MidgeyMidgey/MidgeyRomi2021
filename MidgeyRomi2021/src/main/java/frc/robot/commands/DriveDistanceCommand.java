/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RomiDrivetrain;

import edu.wpi.first.wpilibj.DigitalOutput;

public class DriveDistanceCommand extends CommandBase {
    private static final DigitalOutput m_rsl = new DigitalOutput(8);

    private final RomiDrivetrain m_driveSubsystem;
    private final double m_distance;
    private final double m_speed;
    private double origin;

    // Distance is always positive; negative speed to move backwards
    public DriveDistanceCommand(double distance, double speed, RomiDrivetrain drive) {
      m_distance = Math.abs(distance);
      m_speed = speed;
      m_driveSubsystem = drive;
      addRequirements(m_driveSubsystem);
      //m_rsl.set(true);
    }

    @Override
    public void initialize() {
      origin = m_driveSubsystem.getLeftDistanceInch();
    }

    @Override
    public void execute() {
      m_driveSubsystem.tankDrive(m_speed, m_speed);
    }

    @Override
    public void end(boolean interrupted) {
      m_driveSubsystem.tankDrive(0.0, 0.0);
      m_rsl.set(false);
    }

    @Override
    public boolean isFinished() {
      return Math.abs(m_driveSubsystem.getLeftDistanceInch() - origin) >= m_distance;
    }
}
