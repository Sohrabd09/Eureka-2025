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
        public CRServo leftIntake;
        public CRServo rightIntake;
        public Servo IntYaw;
        public Servo IntLeftShoulder;
        public Servo IntRightShoulder;
        public Servo IntFlapper;
        public Servo IntWrist;
        //TODO HANGER
        public Servo Hanger;
        // TODO OUTAKE
        public Servo leftOutake;
        public Servo rightOutake;
        public Servo OutakeGrip;
        public Servo OutakeWirst;



    public RevColorSensorV3 colourSensor;
    public RevColorSensorV3 gripColourSensor;


   public DigitalChannel beamBreaker;





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
        lifterL = hardwareMap.get(DcMotorEx.class, "lifterL");
        lifterL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lifterR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lifterR = hardwareMap.get(DcMotorEx.class, "lifterR");
        horizontalExtension = hardwareMap.get(DcMotorEx.class, "horizontal");
        horizontalExtension.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        //Used MotorEx so i cant directly set the direction
        lifterL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lifterR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        horizontalExtension.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //Servos
        rightOutake= hardwareMap.get(Servo.class, "grip");
        leftOutake = hardwareMap.get(Servo.class, "wrist");
        OutakeGrip = hardwareMap.get(Servo.class, "wrist");
        IntYaw = hardwareMap.get(Servo.class, "rotate");
        IntLeftShoulder = hardwareMap.get(Servo.class, "leftsInt");
        IntRightShoulder = hardwareMap.get(Servo.class, "rightsInt");
        Hanger = hardwareMap.get(Servo.class, "hanger");
        OutakeWirst = hardwareMap.get(Servo.class, "outakewrist");
        IntWrist = hardwareMap.get(Servo.class, "intwrist");
        IntFlapper = hardwareMap.get(Servo.class, "flapper");
        rightIntake = hardwareMap.get(CRServo.class, "rightintake");
        leftIntake= hardwareMap.get(CRServo.class, "leftintake");

        //Sensor
        colourSensor = hardwareMap.get(RevColorSensorV3.class, "ColourSensor");
//        specimenCam = hardwareMap.get(Camera.class, "cam1");
        beamBreaker = hardwareMap.get(DigitalChannel.class, "beam_breaker");
        beamBreaker.setMode(DigitalChannel.Mode.INPUT);



    }}