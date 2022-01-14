Alex, great job taking the initiative to pull this together. In its current state, it won't quite work for our robot. Let me take you through why:

## Overview of the architecture
Before getting into what needs to change, let's talk about the architecture. The robot's software package exists in `src/main/java/frc/robot`. Within this, you have a few things:

- A folder called `commands`:
    - The command based architecture requires any action a robot to do to be in the form of a "command". That is what you created with your `DriveForwardTime` and `DriveWithJoysticks` java classes. Each class will have always have a few methods, and you can add others as you with. I added comments to `DriveWithJoysticks` to explain what each method does, please take a look at this if you are not familiar. 

- A folder called `subsystems`:
    - As a way of organizing our code, the robot's controls are broken into "subsystems". We actually do something similar with cars, although it is much larger scale and much more complex, but the principle is the same.
    - When you think or talk about the robot, we talk about different subsystems (ex. the drivetrain, the shooter, the intake, and the conveyors), so it is natural to group methods/attributes of these together in a class for each subsystem.
    - So you created the `DriveTrain` subsystem, which will house all of the controls the drivetrain needs. So any motors/actions will be defined here. 
    - Like commands, each subsystem will have a standard set of methods, and you can add any others you wish. Take a look at `DriveTrain` for some comments from me on what each method does.
    - This is the principle of "Object oriented programming". Take a look at [this](https://www.youtube.com/watch?v=m_MQYyJpIjg) video for an overview of it. This is not a robotics thing, but a software engineering thing. The concept of object oriented programming is used in many cases beyond robotics.

- A file called `Constants.java`:
    - This serves as a place to define constant values for our robot. An example of this is CAN IDS, etc. 
    - So when you see things calling `Constants.<some variable>`, it is pulling that value from `Constants`. The point of this is to try and define all of the constants in one place, so you don't have to search for them once the robot has a lot of controls.

- A file called `RobotContainer.java`;
    - This is where everything in the robot is defined and commands are bound. So what do I mean by "robot is defined"?
        - Each subsystem is represented with a class, but objects for that classes are not created anywhere except in `RobotContainer`. So if you look at your code in `RobotContainer.java`, you will see you defined a `DriveTrain` in line 30, and create a `DriveWithJoysticks` command right below it.

- A file called `Robot.java`:
    - This is what actually runs when the robot is in operation. You will see different methods for running during telop, auton, etc.
    - Notice in the `robotInit()` method, a `RobotContainer` is created. This calls all the code you wrote in `RobotContainer.java`
    - You should not need to make many changes here.

- A file called `Main.java`:
    - Do not change anything here, java runs the `Main` class. 

And now it is important to think about what happens when your robot is running. There is something called a `CommandScheduler` that is running during your robot operation. This essentially runs your code every 20 ms. You will see me mention this in my comments.

#### Why this won't work
- You need to change the objects you use to map the motor controller to the correct ones. Check out my comments in DriveTrain.java for more detail here.
- The controllers you used are setup for PWM control. Our robot uses controllers on a CAN network, not through PWM. 

#### So what is a "CAN network"
- This is a simplified explanation, but it is essentially a network the different controllers use to communicate. Think of it like this:
- You want to tell your friends to do something. So think of yourself as the "Roborio", where you are in control of everyone and all your friends as the motor controllers.
    - You need some means of communicating with everyone. Everyone has a phone, so you text them what you want them to do.
    - This of their phone number as the "CAN ID", and the cellular network as the "CAN network". So you are sending your friends messages through the cell network. Similarly, the roborio is sending messages to all the controllers via the CAN network, where each controller has a CAN ID.
- I won't elaborate on what PWM is here.

#### Summarizing what needs to change:
- Use the correct objects in `DriveTrain.java` for the TalonFX controlers.
- Make sure the CAN IDs are set correctly. Define them in `Constants.java` and verify that the CAN IDs on the controllers match. The Pheonix Tuner tool will connect to the physical controllers and tell you this. Jarvis and the older students are probably familiar with this.

Good luck!!



