package frc.robot;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.TimedRobot;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.*;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;

import java.util.ArrayList;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Robot extends TimedRobot {

  private CANSparkMax one, two, three, four, five, six, seven, eight, nine, ten, eleven, twelve, thirteen, fourteen,
      fifteen, sixteen;

  public static ExecutorService cocurrentExecutor = Executors.newFixedThreadPool(1);

  public static double matchStartTime;

  private static Control control = Control.getInstance();

  public void robotInit() {

    Joystick joystick = new Joystick(0);

    one = new CANSparkMax(11, MotorType.kBrushless);
    setSpark(one);
    two = new CANSparkMax(12, MotorType.kBrushless);
    setSpark(two);
    three = new CANSparkMax(13, MotorType.kBrushless);
    setSpark(three);
    four = new CANSparkMax(14, MotorType.kBrushless);
    setSpark(four);
    five = new CANSparkMax(15, MotorType.kBrushless);
    setSpark(five);
    six = new CANSparkMax(16, MotorType.kBrushless);
    setSpark(six);

    
    boolean on = true;
    final IdleMode mode = on? IdleMode.kBrake : IdleMode.kCoast;
    one.setIdleMode(mode);
    two.setIdleMode(mode);
    three.setIdleMode(mode);
    four.setIdleMode(mode);
    five.setIdleMode(mode);
    six.setIdleMode(mode);
  


  }

  private void setSpark(final CANSparkMax spark) {
    spark.restoreFactoryDefaults();
    spark.setOpenLoopRampRate(0.4);
    spark.setClosedLoopRampRate(0.4);
    // spark.enableVoltageCompensation(12.0);
    spark.setSmartCurrentLimit(40);
  }

  @Override
  public void autonomousInit() {
  }

  /*
   * RobotPeriodic is called after the coresponding periodic of the stage, such as
   * teleopPeriodic
   */
  @Override
  public void robotPeriodic() {
  }

  @Override
  public void teleopPeriodic() {
    Joystick drive = new Joystick(1);

    double Y = drive.getRawAxis(1);
    double X = drive.getRawAxis(0);
    double pressed = drive.getRawAxis(3);

    double leftSpeed = 0;
    double rightSpeed = 0;

    if(pressed > 0){
      leftSpeed = X;
      rightSpeed = X;
    } else {
      leftSpeed = X + Y;
      rightSpeed = X - Y;
    }
    
    five.set(leftSpeed);
    six.follow(five);
    one.follow(five);
    
    two.set(rightSpeed);
    three.follow(two);
    //four.follow(two);

   
    // seven.set(2);
    // try {
    //   wait(1000);
    // } catch (InterruptedException e) {
    //   // TODO Auto-generated catch block
    //   e.printStackTrace();
    // }
    // eight.set(2);
    // try {
    //   wait(1000);
    // } catch (InterruptedException e) {
    //   // TODO Auto-generated catch block
    //   e.printStackTrace();
    // }
    // nine.set(2);
    // try {
    //   wait(1000);
    // } catch (InterruptedException e) {
    //   // TODO Auto-generated catch block
    //   e.printStackTrace();
    // }
    // ten.set(2);
    // try {
    //   wait(1000);
    // } catch (InterruptedException e) {
    //   // TODO Auto-generated catch block
    //   e.printStackTrace();
    // }
    // eleven.set(2);
    // try {
    //   wait(1000);
    // } catch (InterruptedException e) {
    //   // TODO Auto-generated catch block
    //   e.printStackTrace();
    // }
    // twelve.set(2);
    // try {
    //   wait(1000);
    // } catch (InterruptedException e) {
    //   // TODO Auto-generated catch block
    //   e.printStackTrace();
    // }
    // thirteen.set(2);
    // try {
    //   wait(1000);
    // } catch (InterruptedException e) {
    //   // TODO Auto-generated catch block
    //   e.printStackTrace();
    // }
    // fourteen.set(2);
    // try {
    //   wait(1000);
    // } catch (InterruptedException e) {
    //   // TODO Auto-generated catch block
    //   e.printStackTrace();
    // }
    // fifteen.set(2);
    // try {
    //   wait(1000);
    // } catch (InterruptedException e) {
    //   // TODO Auto-generated catch block
    //   e.printStackTrace();
    // }
    // sixteen.set(2);
    // try {
    //   wait(1000);
    // } catch (InterruptedException e) {
    //   // TODO Auto-generated catch block
    //   e.printStackTrace();
    // }
  }
}
