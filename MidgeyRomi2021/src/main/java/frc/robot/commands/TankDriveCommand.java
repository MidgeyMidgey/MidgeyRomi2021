// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RomiDrivetrain;
import java.util.function.Supplier;

//This is basically just a rehashing of arcadeDrive, just with variables that make sense to me for tankDrive
public class TankDriveCommand extends CommandBase {
  private final RomiDrivetrain m_romiDrivetrain;
  private final Supplier<Double> m_leftSpeedSupplier;
  private final Supplier<Double> m_rightSpeedSupplier;
  
  public TankDriveCommand(RomiDrivetrain romiDrivetrain, Supplier<Double> leftSpeedSupplier, Supplier<Double> rightSpeedSupplier) {
    m_romiDrivetrain = romiDrivetrain;
    m_leftSpeedSupplier = leftSpeedSupplier;
    m_rightSpeedSupplier = rightSpeedSupplier;
    addRequirements(romiDrivetrain);
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    m_romiDrivetrain.tankDrive(m_leftSpeedSupplier.get(), m_rightSpeedSupplier.get());
  }

  @Override
  public void end(boolean interrupted) {

  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
