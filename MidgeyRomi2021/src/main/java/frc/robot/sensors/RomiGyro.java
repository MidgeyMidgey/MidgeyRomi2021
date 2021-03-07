// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.sensors;

import edu.wpi.first.hal.SimDevice;
import edu.wpi.first.hal.SimDevice.Direction;
import edu.wpi.first.hal.SimDouble;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class RomiGyro implements Gyro {
  private SimDouble m_simRateX;
  private SimDouble m_simRateY;
  private SimDouble m_simRateZ;
  private SimDouble m_simAngleX;
  private SimDouble m_simAngleY;
  private SimDouble m_simAngleZ;

  private double m_angleXOffset;
  private double m_angleYOffset;
  private double m_angleZOffset;

  private SimDevice m_gyroSimDevice;

  public RomiGyro() {
    m_gyroSimDevice = SimDevice.create("Gyro:RomiGyro");
    if (m_gyroSimDevice != null) {
      m_gyroSimDevice.createBoolean("init", Direction.kOutput, true);
      m_simRateX = m_gyroSimDevice.createDouble("rate_x", Direction.kInput, 0.0);
      m_simRateY = m_gyroSimDevice.createDouble("rate_y", Direction.kInput, 0.0);
      m_simRateZ = m_gyroSimDevice.createDouble("rate_z", Direction.kInput, 0.0);

      m_simAngleX = m_gyroSimDevice.createDouble("angle_x", Direction.kInput, 0.0);
      m_simAngleY = m_gyroSimDevice.createDouble("angle_y", Direction.kInput, 0.0);
      m_simAngleZ = m_gyroSimDevice.createDouble("angle_z", Direction.kInput, 0.0);
    }
  }

  public double getRateX() {
    if (m_simRateX != null) {
      return m_simRateX.get();
    }

    return 0.0;
  }

  public double getRateY() {
    if (m_simRateY != null) {
      return m_simRateY.get();
    }

    return 0.0;
  }

  public double getRateZ() {
    if (m_simRateZ != null) {
      return m_simRateZ.get();
    }

    return 0.0;
  }

  public double getAngleX() {
    if (m_simAngleX != null) {
      return m_simAngleX.get() - m_angleXOffset;
    }

    return 0.0;
  }

  public double getAngleY() {
    if (m_simAngleY != null) {
      return m_simAngleY.get() - m_angleYOffset;
    }

    return 0.0;
  }

  public double getAngleZ() {
    if (m_simAngleZ != null) {
      return m_simAngleZ.get() - m_angleZOffset;
    }

    return 0.0;
  }

  public void reset() {
    if (m_simAngleX != null) {
      m_angleXOffset = m_simAngleX.get();
      m_angleYOffset = m_simAngleY.get();
      m_angleZOffset = m_simAngleZ.get();
    }
  }

  @Override
  public void close() throws Exception {
    if (m_gyroSimDevice != null) {
      m_gyroSimDevice.close();
    }
  }

  @Override
  public void calibrate() {
    // no-op
  }

  @Override
  public double getAngle() {
    return getAngleZ();
  }

  @Override
  public double getRate() {
    return getRateZ();
  }
    
}
