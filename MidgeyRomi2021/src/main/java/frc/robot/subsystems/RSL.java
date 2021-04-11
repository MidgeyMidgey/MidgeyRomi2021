package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class RSL extends SubsystemBase {
  private final DigitalOutput m_led = new DigitalOutput(8); // RSL pin on Romi
  private double m_interval = 0.0; // blink interval; 0 == never on
  private double m_lastTime = 0.0; // last FPGA time
  private boolean m_currentState = false; // RSL is off

  public RSL() {
  }

  public void setInterval(double interval) {
    m_interval = interval;
  }

  @Override
  public void periodic() {
    if (m_interval <= 0.0) {
      m_led.set(false);
      return; // disabled
    }
    double currentTime = Timer.getFPGATimestamp();
    if (currentTime >= m_lastTime + m_interval) {
      m_currentState = !m_currentState;
      m_led.set(m_currentState);
      m_lastTime = currentTime;
    }
  }
}
