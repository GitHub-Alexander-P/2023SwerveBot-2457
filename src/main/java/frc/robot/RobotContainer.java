package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class RobotContainer {
DriveSubsystem driveSubsystem = new DriveSubsystem();

Joystick joystick = new Joystick(0);

public RobotContainer() {

    driveSubsystem.setDefaultCommand(
        driveSubsystem.run(
            //This runs the drivetrain, the percentages standing for the maximum speed the bot can achieve.
            () -> driveSubsystem.driveTrain(joystick.getY(), joystick.getX(), joystick.getZ(), 0.25, 0.1)
        )
    );
}
}
