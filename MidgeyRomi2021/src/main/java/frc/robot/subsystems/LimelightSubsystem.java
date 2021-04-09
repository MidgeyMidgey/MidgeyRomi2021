/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Timer;

public class LimelightSubsystem extends SubsystemBase {
  private static final DigitalOutput m_greenLed = new DigitalOutput(1);

  private final NetworkTable limelight = NetworkTableInstance.getDefault().getTable("limelight");

  private double last_target_time = 0.0;

  public LimelightSubsystem(int pipeline) {
    limelight.getEntry("pipeline").setNumber(pipeline);
  }
  // ^ Sets which pipeline the limelight is working off of

  public LimelightSubsystem(){
    this(2); // only one romi pipeline now, but does no harm
  }
  // ^ If no pipeline is entered, this constructor makes it pipeline zero
  // ^ FIX: Depending on how we set it up, we should change this

  public boolean hasTarget() {
    double current_time = Timer.getFPGATimestamp();
    if (limelight.getEntry("tv").getDouble(0.0) > 0.0) {
	   last_target_time = current_time;
	    return true;
    }
    if (current_time - last_target_time < 0.2) {
	    return true;
    }
    return false;
  }

  public double getX() {
    return limelight.getEntry("tx").getDouble(0.0);
  }

  public double getY() {
    return limelight.getEntry("ty").getDouble(0.0);
  }

  public double getArea() {
    return limelight.getEntry("ta").getDouble(0.0);
  }
  // ^ Our epic limelight methods!

  public void setOffLed() {
    limelight.getEntry("ledMode").setNumber(0);
    m_greenLed.set(false); // on board the Romi
  }

  public void setOnLed() {
    limelight.getEntry("ledMode").setNumber(3);
    m_greenLed.set(true); // on board the Romi
  }

  @Override
  public void periodic() {

  }
}
