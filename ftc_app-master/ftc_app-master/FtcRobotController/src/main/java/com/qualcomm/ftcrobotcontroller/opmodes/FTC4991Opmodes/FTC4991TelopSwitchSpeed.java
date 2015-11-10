package com.qualcomm.ftcrobotcontroller.opmodes.FTC4991Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.Range;

public class FTC4991TelopSwitchSpeed extends OpMode{

    //DcMotorController.DeviceMode deviceMode;
    DcMotorController driveFrontController;
    //DcMotorController driveBackController;

    DcMotor driveRightFront;
    DcMotor driveLeftFront;
    //DcMotor driveRightBack;
    //DcMotor driveLeftBack;

    @Override
    public void init() {

        driveRightFront = hardwareMap.dcMotor.get("motor_1");
        driveLeftFront  = hardwareMap.dcMotor.get("motor_2");
        //driveRightBack  = hardwareMap.dcMotor.get("driveRightBack");
        //driveLeftBack   = hardwareMap.dcMotor.get("driveLeftBack");

        driveFrontController = hardwareMap.dcMotorController.get("tank");
        //driveBackController  = hardwareMap.dcMotorController.get("driveBackController");
    }

    @Override
    public void init_loop() {

        driveRightFront.setDirection(DcMotor.Direction.REVERSE);
        //driveRightBack.setDirection(DcMotor.Direction.REVERSE);

        driveRightFront.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        driveLeftFront.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        //driveRightBack.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        // driveLeftBack.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);

    }

    @Override
    public void loop() {

        double rightDriveMotorsSpeed = -gamepad1.right_stick_y;
        double leftDriveMotorsSpeed  = -gamepad1.left_stick_y;

        rightDriveMotorsSpeed = Range.clip(rightDriveMotorsSpeed, -1, 1);
        leftDriveMotorsSpeed = Range.clip(leftDriveMotorsSpeed, -1, 1);

        if(gamepad1.left_bumper  && !gamepad1.right_bumper) {
            driveRightFront.setPower(rightDriveMotorsSpeed/2);
            driveLeftFront.setPower(leftDriveMotorsSpeed/2);
        }
        if(!gamepad1.left_bumper && gamepad1.right_bumper)  {
            driveRightFront.setPower(rightDriveMotorsSpeed/4);
            driveLeftFront.setPower(leftDriveMotorsSpeed/4);
        }
        if(!gamepad1.left_bumper && !gamepad1.right_bumper)  {
            driveRightFront.setPower(rightDriveMotorsSpeed);
            driveLeftFront.setPower(leftDriveMotorsSpeed);
        }









        //driveRightBack.setPower(rightDriveMotorsSpeed);
        //driveLeftBack.setPower(leftDriveMotorsSpeed);

    }

    @Override
    public void stop() {
    }

}
