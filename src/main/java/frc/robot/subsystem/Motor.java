package frc.robot.subsystem;

import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.networktables.*;
import com.ctre.phoenix6.configs.*;

public class Motor extends SubsystemBase {
    private NetworkTable m_tab; // This is the table that data will be sent to
    // @TODO - Initialize a motor object

    /**
     * @param name The name of the NetworkTable to use for this motor.
     */
    public Motor(String name) {
        // Network tables are used to send data to and from the robot. 
        // Values in network table can be viewed and edited in Elastic Dashboard
        m_tab = NetworkTableInstance.getDefault().getTable(name);

        TalonFXConfiguration cfg = new TalonFXConfiguration();

        FeedbackConfigs fdb = cfg.Feedback;
        // @TODO - Set gear ratio and offset 

        MotionMagicConfigs mm = cfg.MotionMagic;
        // @TODO - Set physical limits (vel, acc and optionally jerk)

        Slot0Configs slot0 = cfg.Slot0;
        // @TODO - Set PID and FF constants

        // @TODO - apply the configuration to the motor
    }

    public Command moveTo(double position) {
        // @TODO: make the motor move to desired position
        return null;
    }

    @Override
    public void periodic(){
        // TODO: Put the motor position in the network table
        // TODO: Get the setpoint from network table and move the motor to that position
        // (hint: you might need a member variable and you need to first put the setpoint in the network table)
    }
}
