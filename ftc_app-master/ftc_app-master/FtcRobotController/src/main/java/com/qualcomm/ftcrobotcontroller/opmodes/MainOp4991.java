
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
