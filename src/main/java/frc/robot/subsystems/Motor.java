package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.*;

import java.util.function.DoubleSupplier;

import com.ctre.phoenix6.configs.*;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import badgerlog.Dashboard;
import badgerlog.entry.*;
import edu.wpi.first.math.*;
import edu.wpi.first.wpilibj.event.EventLoop;

public class Motor extends SubsystemBase {
    public static final double MAX_VOLTS = 1.5;

    @Entry(EntryType.Subscriber)
    private static double kS = 0.0, kG = 0.0, kV = 0.0, kA = 0.0, kP = 0.0, kI = 0.0, kD = 0.0;

    private TalonFX m_motor = new TalonFX(11);
    private MotionMagicVoltage m_request = new MotionMagicVoltage(0);

    
    public Motor() {
        TalonFXConfiguration cfg = new TalonFXConfiguration();

        FeedbackConfigs fdb = cfg.Feedback;
        fdb.SensorToMechanismRatio = 1;

        MotionMagicConfigs mm = cfg.MotionMagic;
        mm.MotionMagicCruiseVelocity = 3000;
        mm.MotionMagicAcceleration = 3000;

        applyFFConfig();
        m_motor.getConfigurator().apply(cfg);

        EventLoop buttonLoop = CommandScheduler.getInstance().getActiveButtonLoop();
        Dashboard.getAutoResettingButton("Apply FF config", buttonLoop)
            .onTrue(Commands.runOnce(() -> {
                applyFFConfig();
            }));
    }

    public Command moveTo(DoubleSupplier position) {
        return Commands.runOnce(() -> {
            System.out.printf("Moving to position: %.2f%n", position.getAsDouble());
            m_motor.setControl(m_request.withPosition(position.getAsDouble()));
        });
    }

    public Command setManualVoltage(double volts) {
        return Commands.runOnce(() -> {
            m_motor.setVoltage(MathUtil.clamp(volts, -MAX_VOLTS, MAX_VOLTS));
        });
    }

    private void applyFFConfig() {
        // System.out.printf("Applying FF Config with kS=%.2f, kG=%.2f, kV=%.2f, kA=%.2f, kP=%.2f, kI=%.2f, kD=%.2f%n",
        //                   kS, kG, kV, kA, kP, kI, kD);
        TalonFXConfiguration cfg = new TalonFXConfiguration();

        Slot0Configs slot0 = cfg.Slot0;
        slot0.kS = kS;
        slot0.kG = kG;
        slot0.kV = kV;
        slot0.kA = kA;
        slot0.kP = kP;
        slot0.kI = kI;
        slot0.kD = kD;

        m_motor.getConfigurator().apply(cfg);
    }
}
