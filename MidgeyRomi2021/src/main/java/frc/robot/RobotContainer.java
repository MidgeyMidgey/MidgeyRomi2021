// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.subsystems.RomiDrivetrain;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.RSL;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.AutoBaselineCommand;
import frc.robot.commands.ArcadeDriveCommand;
import frc.robot.commands.TankDriveCommand;
import frc.robot.commands.TurnToAngleCommand;
import frc.robot.commands.DriveDistanceCommand;

public class RobotContainer {
  public final RomiDrivetrain m_romiDrivetrain = new RomiDrivetrain();
  public final LimelightSubsystem m_limelight = new LimelightSubsystem();
  public final RSL m_rsl = new RSL();

  // final XboxController m_controller = new XboxController(0);
  final Joystick m_controller = new Joystick(0);

  private static final int A_BUTTON_XBOX = 1;
  private static final int B_BUTTON_XBOX = 2;
  private static final int X_BUTTON_XBOX = 3;
  private static final int Y_BUTTON_XBOX = 4;
  private static final int LEFT_BUMPER_XBOX = 5;
  private static final int RIGHT_BUMPER_XBOX = 6;
  private static final int BACK_ARROW = 7;
  private static final int START_ARROW = 8;
  private static final int JOYSTICK_LEFT_CLICK = 9;
  private static final int JOYSTICK_RIGHT_CLICK = 10;

  // Create SmartDashboard chooser for autonomous routines
  private final SendableChooser<Command> m_chooser = new SendableChooser<>();

  public RobotContainer() {
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    // m_romiDrivetrain.setDefaultCommand(getTankDriveCommand());
    m_romiDrivetrain.setDefaultCommand(getArcadeDriveCommand());

    // Setup SmartDashboard auto command chooser
    m_chooser.setDefaultOption("Auto AutoBaseline", new AutoBaselineCommand(m_romiDrivetrain, m_limelight));
    m_chooser.addOption("Auto TurnToAngle", new TurnToAngleCommand(m_romiDrivetrain));
    m_chooser.addOption("Auto DriveDistance", new DriveDistanceCommand(2, 0.5, m_romiDrivetrain));
    SmartDashboard.putData(m_chooser);

    JoystickButton turnToAngleCommandButton = new JoystickButton(m_controller, A_BUTTON_XBOX);
    turnToAngleCommandButton.whenPressed(new TurnToAngleCommand(m_romiDrivetrain));
  }

  public Command getAutonomousCommand() {
    return m_chooser.getSelected();
  }

  public Command getTankDriveCommand(){
    return new TankDriveCommand(m_romiDrivetrain, () -> -m_controller.getRawAxis(1), () -> m_controller.getRawAxis(5));
  }

  public Command getArcadeDriveCommand() {
    return new ArcadeDriveCommand(
        m_romiDrivetrain, () -> -m_controller.getRawAxis(1), () -> m_controller.getRawAxis(2));
  }

  public void setSmartDashboard(){
    // SmartDashboard.putNumber("Left Encoder", m_romiDrivetrain.getLeftDistanceInch());
    // SmartDashboard.putNumber("Right Encoder", m_romiDrivetrain.getRightDistanceInch());
  }
}
