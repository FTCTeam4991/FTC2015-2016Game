package com.qualcomm.ftcrobotcontroller.opmodes.FTC4991Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by jalle_000 on 10/16/2015.
 */
public class FTC4991EncoderTest extends OpMode {

    DcMotor driveRightFront;
    DcMotor driveLeftFront;
    DcMotor driveRightBack;
    DcMotor driveLeftBack;

    DcMotorController driveFrontController;
    DcMotorController driveBackController;

    final static int EncoderCpr = 1440;
    final static double GearRatio = 2;
    final static int WheelDiameter = 4;
    final static int Distance = 24;


    final static double Circumference = Math.PI * WheelDiameter;
    final static double Rotations = Distance / Circumference;
    final static double Counts = EncoderCpr * Rotations * GearRatio;

    @Override
    public void init() {

        driveRightFront = hardwareMap.dcMotor.get("driveRightFront");
        driveLeftFront = hardwareMap.dcMotor.get("driveLeftFront");
        driveRightBack = hardwareMap.dcMotor.get("driveRightBack");
        driveLeftBack  = hardwareMap.dcMotor.get("driveLeftBack");
        driveRightFront.setDirection(DcMotor.Direction.REVERSE);
        driveRightBack.setDirection(DcMotor.Direction.REVERSE);

        driveFrontController = hardwareMap.dcMotorController.get("driveFrontController");
        driveBackController = hardwareMap.dcMotorController.get("driveBackController");

        driveRightFront.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        driveLeftFront.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        driveRightBack.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        driveLeftBack.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
    }

    @Override
    public void start() {

        driveRightFront.setTargetPosition((int) Counts);
        driveLeftFront.setTargetPosition((int) Counts);
        driveRightBack.setTargetPosition((int) Counts);
        driveLeftBack.setTargetPosition((int) Counts);

        driveRightFront.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        driveLeftFront.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        driveRightBack.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        driveLeftBack.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);

        driveRightFront.setPower(0.5);
        driveLeftFront.setPower(0.5);
        driveRightBack.setPower(0.5);
        driveLeftBack.setPower(0.5);

    }


    @Override
    public void loop() {
        telemetry.addData("Motor Target", Counts);
        telemetry.addData("Front Right Pos", driveRightFront.getCurrentPosition());
        telemetry.addData("Front Left Pos", driveLeftFront.getCurrentPosition());
        telemetry.addData("Back Right Pos", driveRightBack.getCurrentPosition());
        telemetry.addData("Back Left Pos", driveLeftBack.getCurrentPosition());

    }
}
