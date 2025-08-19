# Working with Java Command Based

Welcome to this tutorial on using Java Command Based with WPILIB. In this project, you will attempt to drive a motor to a desired position using Feedforward and PID.

## Get Started

Go to `src/main/java/frc/robot`. You should see the following contents:
1. `Main.java` - This is the entry point for the code. You should not need to modify this.
2. `Robot.java` - The RoboRio is inherently time-based, running the periodic functions every 20 ms or so. Using the `CommandScheduler`, we abstract the periodic nature away and work solely with Commands. You should not need to modify this.
3. `RobotContainer.java` - This class captures your entire robot.
4. `subsystem` folder - Open `subsystem/Motor.java` and fill in the required fields.

You will need to refer to [WPILIB Commands](https://docs.wpilib.org/en/stable/docs/software/commandbased/commands.html) and [MotionMagic](https://v6.docs.ctr-electronics.com/en/latest/docs/api-reference/device-specific/talonfx/motion-magic.html) documentation.