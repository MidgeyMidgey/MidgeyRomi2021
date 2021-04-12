package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.RomiDrivetrain;

public class AutoTriangleCommand extends SequentialCommandGroup {
  /**
   * Creates a new BaselineAuto.
   */
  public AutoTriangleCommand(RomiDrivetrain drive) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive);
    addCommands(
      new DriveDistanceCommand(24, 0.7, drive),
      new TurnDegrees(0.45, 120, drive),
      new DriveDistanceCommand(24, 0.7, drive),
      new TurnDegrees(0.45, 120, drive),
      new DriveDistanceCommand(24, 0.7, drive),
      new TurnDegrees(0.45, 120, drive)
    );
  }
}
