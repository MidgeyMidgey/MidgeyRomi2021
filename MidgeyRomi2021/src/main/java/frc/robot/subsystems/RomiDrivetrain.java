// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.sensors.RomiGyro;
import frc.robot.RobotContainer;

public class RomiDrivetrain extends SubsystemBase {
  private static final double kCountsPerRevolution = 1440.0; // 12 per turn at 120:1 gearing
  private static final double kWheelDiameterInch = 2.75591; // 70 mm
    
  private final Spark m_leftMotor = new Spark(0);
  private final Spark m_rightMotor = new Spark(1);
  private final Encoder m_leftEncoder = new Encoder(4, 5);
  private final Encoder m_rightEncoder = new Encoder(6, 7);
  private final DifferentialDrive m_diffDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);

  public final static RomiGyro m_romiGyro = new RomiGyro();
  private final BuiltInAccelerometer m_accelerometer = new BuiltInAccelerometer();

  // odometry must be set up after encoder/gyro reset
  private DifferentialDriveOdometry m_odometry;

  // Display odometry
  private Field2d m_field = new Field2d();

  public RomiDrivetrain() {
    m_leftEncoder.setDistancePerPulse((Math.PI * kWheelDiameterInch) / kCountsPerRevolution);
    m_rightEncoder.setDistancePerPulse((Math.PI * kWheelDiameterInch) / kCountsPerRevolution);
    resetEncoders();
    resetGyro();
    m_odometry = new DifferentialDriveOdometry(m_romiGyro.getRotation2d());
    SmartDashboard.putData("Field", m_field);
  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    m_diffDrive.tankDrive(leftSpeed, rightSpeed);
  }
    
  public void arcadeDrive(double speed, double rotation) {
      m_diffDrive.arcadeDrive(speed, rotation, true); // square inputs
  }

  public void resetEncoders() {
    m_leftEncoder.reset();
    m_rightEncoder.reset();
  }

  public double getLeftDistanceInch() {
    return m_leftEncoder.getDistance();
  }

  public double getRightDistanceInch() {
    return m_rightEncoder.getDistance();
  }

  public double getAccelX() {
    return m_accelerometer.getX();
  }

  public double getAccelY() {
    return m_accelerometer.getY();
  }

  public double getAccelZ() {
    return m_accelerometer.getZ();
  }

  public double getAngleZ() {
    return m_romiGyro.getAngleZ();
  }

  public Rotation2d getRotation2d() {
    return m_romiGyro.getRotation2d();
  }
  /** Reset the gyro. */
  public void resetGyro() {
    m_romiGyro.reset();
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    m_odometry.update(m_romiGyro.getRotation2d(),
		      m_leftEncoder.getDistance(),
		      m_rightEncoder.getDistance());
    m_field.setRobotPose(m_odometry.getPoseMeters());
    SmartDashboard.putNumber("Angle Z", m_romiGyro.getAngleZ());
  }

  @Override
  public void simulationPeriodic() {
  }
}
