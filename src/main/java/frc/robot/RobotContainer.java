// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Motor;

import java.util.function.BooleanSupplier;

import badgerlog.*;
import badgerlog.entry.*;


public class RobotContainer {
  @Entry(EntryType.Subscriber)
  public static double m_targPos = 0;

  private final Motor m_motor = new Motor(); //Adjust parameters as needed
  private final XboxController m_controller = new XboxController(0);

  public RobotContainer() {
    Dashboard.initialize(DashboardConfig.defaultConfig);
    configureBindings();
  }

  private void configureBindings() {
    if(!RobotBase.isSimulation()) {
      new Trigger(() -> m_controller.getAButton())
      .onTrue(m_motor.setManualVoltage(1))
      .onFalse(m_motor.setManualVoltage(0));
    }
    
    BooleanSupplier hasChanged = new BooleanSupplier() {
      private double target = m_targPos;

      @Override
      public boolean getAsBoolean() {
        if(m_targPos != target) {
          target = m_targPos;
          return true;
        }
        return false;
      }
    };

    new Trigger(hasChanged)
      .onTrue(Commands.sequence(new PrintCommand("Position changed"), m_motor.moveTo(m_targPos)));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
