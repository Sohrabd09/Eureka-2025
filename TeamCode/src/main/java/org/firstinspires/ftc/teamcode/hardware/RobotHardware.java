package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.Camera;

public class RobotHardware {


    //TODO Lifter
    public DcMotorEx lifterL = null;
    public DcMotorEx lifterR= null;
    public DcMotorEx horizontalExtension= null;
    //TODO HANGER
    public DcMotorEx hanger= null;
    //TODO SERVOS

        //TODO INTAKE
        public CRServo leftIntake= null;
        public CRServo rightIntake= null;
        public Servo IntYaw= null;
        public Servo IntLeftShoulder= null;
        public Servo IntRightShoulder= null;
        public Servo IntFlapper= null;
        public Servo IntWrist= null;
        //TODO HANGER
        public Servo Hanger= null;
        // TODO OUTAKE
        public Servo leftOutake= null;
        public Servo rightOutake= null;
        public Servo OutakeGrip= null;
        public Servo OutakeWirst= null;



    public  RevColorSensorV3 colourSensor = null;
    public RevColorSensorV3 gripColourSensor= null;


   public DigitalChannel beamBreaker=null;





    public Camera specimenCam;
    public DcMotorEx frontLeftMotor;
    public DcMotorEx frontRightMotor;
    public DcMotorEx backLeftMotor;
    public DcMotorEx backRightMotor;











    private static RobotHardware instance = null;  // ref variable to use robot hardware

    public boolean enabled;                          //boolean to return instance if robot is enabled.

    private HardwareMap hardwareMap;

    public static RobotHardware getInstance() {
        if (instance == null) {
            instance = new RobotHardware();
        }
        instance.enabled = true;
        return instance;
    }

    public void init1(HardwareMap hardwareMap, Telemetry telemetry) {


        //TODO HARDWARE MAP
        //DcMotor
        lifterL = hardwareMap.get(DcMotorEx.class, "sliderL");
        lifterL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lifterR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lifterR = hardwareMap.get(DcMotorEx.class, "sliderR");
        horizontalExtension = hardwareMap.get(DcMotorEx.class, "horizontal");
        horizontalExtension.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        //Used MotorEx so i cant directly set the direction
        lifterL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lifterR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        horizontalExtension.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //Servos
        rightOutake= hardwareMap.get(Servo.class, "rightOut");
        leftOutake = hardwareMap.get(Servo.class, "leftOut");
        OutakeGrip = hardwareMap.get(Servo.class, "gripper");
        IntYaw = hardwareMap.get(Servo.class, "yaw");
        IntLeftShoulder = hardwareMap.get(Servo.class, "elbowL");
        IntRightShoulder = hardwareMap.get(Servo.class, "elbowR");
        Hanger = hardwareMap.get(Servo.class, "clutch");
        OutakeWirst = hardwareMap.get(Servo.class, "wristOut");
        IntWrist = hardwareMap.get(Servo.class, "wrist");
        IntFlapper = hardwareMap.get(Servo.class, "flap");
        rightIntake = hardwareMap.get(CRServo.class, "intakeR");
        leftIntake= hardwareMap.get(CRServo.class, "intakeL");

        //Sensor
        colourSensor = hardwareMap.get(RevColorSensorV3.class, "csI");
        gripColourSensor = hardwareMap.get(RevColorSensorV3.class,"cs0");
//        specimenCam = hardwareMap.get(Camera.class, "cam1");
        beamBreaker = hardwareMap.get(DigitalChannel.class, "bb");
        beamBreaker.setMode(DigitalChannel.Mode.INPUT);



    }}