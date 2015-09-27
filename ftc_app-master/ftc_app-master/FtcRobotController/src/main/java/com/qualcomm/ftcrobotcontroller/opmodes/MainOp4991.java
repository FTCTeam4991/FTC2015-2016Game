
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
    public void init() {

        // Drive Motors
        driveRight = hardwareMap.dcMotor.get("driveRight");
        driveLeft = hardwareMap.dcMotor.get("driveLeft");

        // Arm Motors
        armStage1 = hardwareMap.dcMotor.get("armStage1");
        armStage2 = hardwareMap.dcMotor.get("armStage2");

        // Servos


        // Controllers
        driveController = hardwareMap.dcMotorController.get("driveController");
        armController = hardwareMap.dcMotorController.get("armController");
        servoController = hardwareMap.servoController.get("servoController");

    }
