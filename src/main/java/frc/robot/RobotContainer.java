// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.event.EventLoop;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Motor;
import badgerlog.*;
import badgerlog.entry.*;


public class RobotContainer {
  @Entry(EntryType.Subscriber)
  public static double m_targPos = 0;

  private final Motor m_motor;
  private final XboxController m_controller = new XboxController(0);

  public RobotContainer() {
    Dashboard.initialize(DashboardConfig.defaultConfig);
    m_motor = new Motor();
    configureBindings();

    EventLoop buttonLoop = CommandScheduler.getInstance().getActiveButtonLoop();
    Dashboard.getAutoResettingButton("Move to Pos", buttonLoop)
        .onTrue(
            m_motor.moveTo(() -> m_targPos)
        );
  }

  private void configureBindings() {
    if(!RobotBase.isSimulation()) {
      new Trigger(() -> m_controller.getAButton())
      .onTrue(m_motor.setManualVoltage(1))
      .onFalse(m_motor.setManualVoltage(0));
    }
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
