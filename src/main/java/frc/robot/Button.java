package frc.robot;

import edu.wpi.first.util.sendable.*;

public class Button implements Sendable{
    private String m_buttonName;
    private boolean buttonState = false;

    public Button(String name) {
        m_buttonName = name;
    }

    public void setButtonState(boolean state) {
        this.buttonState = state;
    }

    public boolean getButtonState() {
        return buttonState;
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        // Initialize the sendable properties here
        builder.setSmartDashboardType("Button");
        builder.addBooleanProperty(m_buttonName, 
            this::getButtonState, 
            this::setButtonState);
    }
    
}
