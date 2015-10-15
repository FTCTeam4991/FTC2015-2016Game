package com.qualcomm.ftcrobotcontroller.opmodes.FTC4991Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.Range;

public class FTC4991TankOp extends OpMode{

    DcMotorController.DeviceMode deviceMode;
    DcMotorController driveFrontController;
    DcMotorController driveBackController;

    DcMotor driveRightFront;
    DcMotor driveLeftFront;
    DcMotor driveRightBack;
    DcMotor driveLeftBack;

    @Override
    public void init() {

        driveRightFront = hardwareMap.dcMotor.get("driveRightFront");
        driveLeftFront  = hardwareMap.dcMotor.get("driveLeftFront");
        driveRightBack  = hardwareMap.dcMotor.get("driveRightBack");
        driveLeftBack   = hardwareMap.dcMotor.get("driveLeftBack");

        driveFrontController = hardwareMap.dcMotorController.get("driveFrontController");
        driveBackController  = hardwareMap.dcMotorController.get("driveBackController");
    }

    @Override
    public void init_loop() {

        driveRightFront.setDirection(DcMotor.Direction.REVERSE);
        driveRightBack.setDirection(DcMotor.Direction.REVERSE);

        driveRightFront.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        driveLeftFront.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        driveRightBack.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        driveLeftBack.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);

    }

    @Override
    public void loop() {

        double rightDriveMotorsSpeed = -gamepad1.right_stick_y;
        double leftDriveMotorsSpeed  = -gamepad1.left_stick_y;

        rightDriveMotorsSpeed = Range.clip(rightDriveMotorsSpeed, -1, 1);
        leftDriveMotorsSpeed = Range.clip(leftDriveMotorsSpeed, -1, 1);

        rightDriveMotorsSpeed = scaleInput(rightDriveMotorsSpeed);
        leftDriveMotorsSpeed = scaleInput(leftDriveMotorsSpeed);

        driveRightFront.setPower(rightDriveMotorsSpeed);
        driveRightBack.setPower(rightDriveMotorsSpeed);
        driveLeftFront.setPower(leftDriveMotorsSpeed);
        driveLeftBack.setPower(leftDriveMotorsSpeed);

    }

    @Override
    public void stop() {
    }

    //Scales Motor Power
    double scaleInput(double Val) {

        double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24, 0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00};

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
            Scale = scaleArray[index];
        } else {
            Scale = -scaleArray[index];
        }

        // return scaled value.
        return Scale;
    }

}
