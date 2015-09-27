package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.util.Range;

public class MainOp4991 extends OpMode{

    //Controllers
    DcMotorController.DeviceMode devMode;
    DcMotorController driveController;
    DcMotorController armController;
    ServoController servoController;

    //Drive Motors
    DcMotor driveRight;
    DcMotor driveLeft;

    //Arm Motors
    DcMotor armStage1;
    DcMotor armStage2;

    //Servo
    Servo hookServo;
    Servo triggerServo;

    //Servo Position
    double hookServoPosition;
    double triggerServoPosition;

    //Servo Delta
    double hookServoDelta;
    double triggerServoDelta;

    int hookServoClick = 0;
    int triggerServoClick = 0;

    @Override
    public void init() {

        // Drive Motors
        driveRight = hardwareMap.dcMotor.get("driveRight");
        driveLeft = hardwareMap.dcMotor.get("driveLeft");

        // Arm Motors
        armStage1 = hardwareMap.dcMotor.get("armStage1");
        armStage2 = hardwareMap.dcMotor.get("armStage2");

        // Servos
        hookServo = hardwareMap.servo.get("hookServo");
        triggerServo = hardwareMap.servo.get("triggerServo");

        // Controllers
        driveController = hardwareMap.dcMotorController.get("driveController");
        armController = hardwareMap.dcMotorController.get("armController");
        servoController = hardwareMap.servoController.get("servoController");

        //sets servo Pos
        hookServoPosition = 0.2;
        triggerServoPosition = 0.2;

    }

    @Override
    public void init_loop() {
        //Reverses the Right Wheel
        driveRight.setDirection(DcMotor.Direction.REVERSE);

        //Sets motors to run without incoders
        driveLeft.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        driveRight.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);


    }

    @Override
    public void loop() {

        // Gamepad 1 - Drive Controller

        float rightDriveMotorSpeed = -gamepad1.right_stick_y;
        float leftDriveMotorSpeed = -gamepad1.left_stick_y;

        rightDriveMotorSpeed = Range.clip(rightDriveMotorSpeed, -1, 1);
        leftDriveMotorSpeed = Range.clip(leftDriveMotorSpeed, -1, 1);

        rightDriveMotorSpeed = (float)scaleInput(rightDriveMotorSpeed);
        leftDriveMotorSpeed = (float)scaleInput(leftDriveMotorSpeed);

        driveRight.setPower(rightDriveMotorSpeed);
        driveLeft.setPower(leftDriveMotorSpeed);

        if (gamepad1.a & hookServoClick == 0) {
            hookServoPosition -= hookServoDelta;
            hookServoClick = 1;
        }

        if (gamepad1.a & hookServoClick == 1){
            hookServoPosition += hookServoDelta;
            hookServoClick = 0;
        }

        if (gamepad1.x & triggerServoClick == 0) {
            triggerServoPosition -= triggerServoDelta;
            triggerServoClick = 1;
        }

        if (gamepad1.x & triggerServoClick == 1){
            triggerServoPosition += triggerServoDelta;
            triggerServoClick = 0;
        }

        //Gamepad 2

        float armStage1MotorSpeed = -gamepad2.right_stick_y;
        float armStage2MotorSpeed = -gamepad2.left_stick_y;

        armStage1MotorSpeed = Range.clip(armStage1MotorSpeed, -1, 1);
        armStage2MotorSpeed = Range.clip(armStage2MotorSpeed, -1, 1);

        armStage1MotorSpeed = (float)scaleInput(armStage1MotorSpeed);
        armStage2MotorSpeed = (float)scaleInput(armStage2MotorSpeed);

        armStage1.setPower(armStage1MotorSpeed);
        armStage2.setPower(armStage2MotorSpeed);

    }

    @Override
    public void stop() {

    }



    //Scales Motor Power
    double scaleInput(double Val) {

        double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24, 0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00};

        // get the corresponding index for the scaleInput array.
        int index = (int) (Val * 16.0);

        // index should be positive.
        if(index < 0){
            index = -index;
        }

        // index cannot exceed size of array minus 1.
        if(index > 16){
            index = 16;
        }

        // get value from the array.
        double Scale = 0.0;
        if(Val < 0){
            Scale = -scaleArray[index];
        } else {
            Scale = scaleArray[index];
        }

        // return scaled value.
        return Scale;
    }

}
