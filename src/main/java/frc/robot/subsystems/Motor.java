package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.Button;

import com.ctre.phoenix6.configs.*;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import badgerlog.entry.*;
import edu.wpi.first.math.*;

public class Motor extends SubsystemBase {
    public static final double MAX_VOLTS = 1.5;

    @Entry(EntryType.Subscriber)
    private static double kS = 0.0, kG = 0.0, kV = 0.0, kA = 0.0, kP = 0.0, kI = 0.0, kD = 0.0;

    @Entry(EntryType.Sendable)
    private static Button m_applyConfigButton = new Button("Apply FF Config");

    private TalonFX m_motor = new TalonFX(0);
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
    }

    public Command moveTo(double position) {
        return Commands.runOnce(() -> {
            m_motor.setControl(m_request.withPosition(position));
        });
    }

    public Command setManualVoltage(double volts) {
        return Commands.runOnce(() -> {
            m_motor.setVoltage(MathUtil.clamp(volts, -MAX_VOLTS, MAX_VOLTS));
        });
    }

    @Override
    public void periodic() {
        if (m_applyConfigButton.getButtonState()) {
            applyFFConfig();
            m_applyConfigButton.setButtonState(false);
        }
    }

    private void applyFFConfig() {
        System.out.println("Applying FF Config");
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
