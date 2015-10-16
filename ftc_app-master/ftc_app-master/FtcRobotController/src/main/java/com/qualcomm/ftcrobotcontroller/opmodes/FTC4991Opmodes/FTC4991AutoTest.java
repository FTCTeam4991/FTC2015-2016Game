package com.qualcomm.ftcrobotcontroller.opmodes.FTC4991Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.ElapsedTime;

public class FTC4991AutoTest extends OpMode{

    DcMotor driveRightFront;
    DcMotor driveLeftFront;
    DcMotor driveRightBack;
    DcMotor driveLeftBack;

    DcMotorController driveFrontController;
    DcMotorController driveBackController;

    ElapsedTime time;

    static final double forwardTime = 1.0;
    static final double turnTime = 1.0;
    int count = 0;

    enum State {
        drivingStraight,
        turning,
        done
    }
    State state;

    @Override
    public void init() {

        driveRightFront = hardwareMap.dcMotor.get("driveRightFront");
        driveLeftFront = hardwareMap.dcMotor.get("driveLeftFront");
        driveRightBack = hardwareMap.dcMotor.get("driveRightBack");
        driveLeftBack = hardwareMap.dcMotor.get("driveLeftBack");
        driveRightFront.setDirection(DcMotor.Direction.REVERSE);
        driveRightBack.setDirection(DcMotor.Direction.REVERSE);

        driveFrontController = hardwareMap.dcMotorController.get("driveFrontController");
        driveBackController = hardwareMap.dcMotorController.get("driveBackController");

        time = new ElapsedTime();
        state = State.drivingStraight;

    }

    @Override
    public void loop() {

        double currentTime = time.time();
        switch (state) {
            case drivingStraight:
                driveRightFront.setPower(0.5);
                driveRightBack.setPower(0.5);
                driveLeftFront.setPower(0.5);
                driveLeftBack.setPower(0.5);
                if(currentTime > forwardTime) {
                    state = State.turning;
                    time.reset();
                }
                break;
            case turning:
                driveRightFront.setPower(-0.5);
                driveRightBack.setPower(-0.5);
                driveLeftFront.setPower(0.5);
                driveLeftBack.setPower(0.5);
                if(currentTime > turnTime) {
                    count++;
                    if(count < 4) {
                        state = State.drivingStraight;
                    }else {
                        state = State.done;
                    }
                }
                break;
            case done:
                driveRightFront.setPower(0);
                driveRightBack.setPower(0);
                driveLeftFront.setPower(0);
                driveLeftBack.setPower(0);
                break;


        }

    }
}
