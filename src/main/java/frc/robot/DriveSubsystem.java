package frc.robot;

import com.ctre.phoenix.sensors.CANCoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

//Important! Everything is in METERS, so don't use anything else got it?

public class DriveSubsystem extends SubsystemBase{
     //Declaring the drive motors for this class only.
     private CANSparkMax frightDrive = new CANSparkMax(5, MotorType.kBrushless);
     private CANSparkMax fleftDrive = new CANSparkMax(7, MotorType.kBrushless);
     private CANSparkMax bleftDrive = new CANSparkMax(9, MotorType.kBrushless);
     private CANSparkMax brightDrive = new CANSparkMax(11, MotorType.kBrushless);
 
     //Declaring the turn motors for this class only.
     private CANSparkMax frightTurning = new CANSparkMax(4, MotorType.kBrushless);
     private CANSparkMax fleftTurning = new CANSparkMax(6, MotorType.kBrushless);
     private CANSparkMax brightTurning = new CANSparkMax(10, MotorType.kBrushless);
     private CANSparkMax bleftTurning = new CANSparkMax(8, MotorType.kBrushless);
 
     //Storing encoders for turn SHAFT in this class only.
     private CANCoder frightCC = new CANCoder(0);
     private CANCoder fleftCC = new CANCoder(1);
     private CANCoder bleftCC = new CANCoder(2);
     private CANCoder brightCC = new CANCoder(3);
 
     //Declare some storage variables for use later.
     private double frightAV;
     private double fleftAV;
     private double bleftAV;
     private double brightAV;

     //Declaring some holder variables for math later in the code.
     private double A;
     private double B;
     private double C;
     private double D;
     private double trackwidth = 1;
     private double wheelbase = 1;
     private double R = Math.sqrt(trackwidth*trackwidth+wheelbase*wheelbase)/2;
     private double[] speeds;
     private double[] angles;
    
    public void driveTrain(double fwd, double str, double rot, double movlim, double rotlim) { 
        //Setting math values.
        A = fwd - rot*wheelbase/2;
        B = fwd + rot*wheelbase/2;
        C = str - rot*trackwidth/2;
        D = str + rot*trackwidth/2;

        //Calculating desired movement direction.
        speeds[0] = Math.sqrt(B*B+C*C);
        speeds[1] = Math.sqrt(B*B+D*D);
        speeds[2] = Math.sqrt(A*A+D*D);
        speeds[3] = Math.sqrt(A*A+C*C);

        //Calculate desired angle rotation..
        angles[0] = Math.atan2(B,C)*180/Math.PI;
        angles[1] = Math.atan2(B,D)*180/Math.PI;
        angles[2] = Math.atan2(A,D)*180/Math.PI;
        angles[3] = Math.atan2(A,C)*180/Math.PI;

        for(int i = 0; i<4; i++) {
            if(speeds[i]>1) {
                speeds[0]=speeds[0]/speeds[i];
                speeds[1]=speeds[1]/speeds[i];
                speeds[2]=speeds[2]/speeds[i];
                speeds[3]=speeds[3]/speeds[i];
            }
        }

        frightDrive.set(speeds[0]);
        fleftDrive.set(speeds[1]);
        bleftDrive.set(speeds[2]);
        brightDrive.set(speeds[3]);

        frightTurning.set(angles[0]);
        fleftTurning.set(angles[1]);
        bleftTurning.set(angles[2]);
        brightTurning.set(angles[3]);

        System.out.println(frightCC.getAbsolutePosition());
        System.out.println(fleftCC.getAbsolutePosition());
        System.out.println(bleftCC.getAbsolutePosition());
        System.out.println(brightCC.getAbsolutePosition());
    }
}
