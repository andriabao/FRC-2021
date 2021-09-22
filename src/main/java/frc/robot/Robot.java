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

  private int intake = -1;
  private int shooter = -1;

  private CANSparkMax ballIntake = new CANSparkMax(26, MotorType.kBrushless);
  private CANSparkMax intakeConveyer = new CANSparkMax(22, MotorType.kBrushless);

  private CANSparkMax shooterMaster = new CANSparkMax(31, MotorType.kBrushless);
  private CANSparkMax shooterSlave = new CANSparkMax(32, MotorType.kBrushless);
  private CANSparkMax shooterConveyer = new CANSparkMax(23, MotorType.kBrushless);

  public static ExecutorService cocurrentExecutor = Executors.newFixedThreadPool(1);

  public static double matchStartTime;

  private static Control control = Control.getInstance();

  public void robotInit() {

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

    ballIntake.setInverted(true);
    ballIntake.setSmartCurrentLimit(30);
    ballIntake.burnFlash();

    intakeConveyer.setIdleMode(IdleMode.kBrake);
    intakeConveyer.setSmartCurrentLimit(10);
    intakeConveyer.burnFlash();
    
    shooterMaster.setInverted(true);
    shooterMaster.setClosedLoopRampRate(0.3);

    shooterSlave.follow(shooterMaster, true);
    
    shooterConveyer.setInverted(true);
    shooterConveyer.setIdleMode(IdleMode.kBrake);
    shooterConveyer.setSmartCurrentLimit(20);
    shooterConveyer.burnFlash();


    Joystick drive = new Joystick(1);

    double Y = 0.5*drive.getRawAxis(1);
    double X = 0.5*drive.getRawAxis(0);
    
    if(drive.getRawButtonPressed(5)) {
      intake *= -1;
    }

    if(drive.getRawButtonPressed(6)) {
      shooter *= -1;
    }

    if (intake == 1) {
      ballIntake.set(0.5);
      intakeConveyer.set(0.5);
    } else {
      ballIntake.set(0);
      intakeConveyer.set(0);
    }

    if (shooter == 1) {
      shooterConveyer.set(0.5);
      shooterMaster.set(1);
    } else {
      shooterConveyer.set(0);
      shooterMaster.set(0);
    }

    double leftSpeed = 0;
    double rightSpeed = 0;

    leftSpeed = X + Y;
    rightSpeed = X - Y;
    
    // five.set(leftSpeed);
    // six.follow(five);
    one.set(leftSpeed);
    
    two.set(rightSpeed);
    three.follow(two);
    four.follow(two);

   
  }
}
