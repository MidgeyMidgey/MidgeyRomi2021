/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.RomiDrivetrain;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.commands.MidgeShootBall;
import frc.robot.commands.DriveDistanceCommand;
import frc.robot.commands.LimelightDistanceCommand;


public class AutoBaselineCommand extends SequentialCommandGroup {
  /**
   * Creates a new BaselineAuto.
   */
  public AutoBaselineCommand(RomiDrivetrain drive, LimelightSubsystem limelight) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive);
    addCommands(
      new DriveDistanceCommand(10, -0.7, drive),
      new LimelightDistanceCommand(limelight, drive)
      //new MidgeShootBall(limelight)
    );
  }
}
