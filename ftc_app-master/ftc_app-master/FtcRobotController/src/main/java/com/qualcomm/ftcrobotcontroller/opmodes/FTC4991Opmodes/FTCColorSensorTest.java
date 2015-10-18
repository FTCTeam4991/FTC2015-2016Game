package com.qualcomm.ftcrobotcontroller.opmodes.FTC4991Opmodes;

import android.graphics.Color;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.ftcrobotcontroller.opmodes.ColorSensorDriver;
import com.qualcomm.ftcrobotcontroller.opmodes.FTC4991ResourceClasses.ScaleInput;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.Range;

public class FTCColorSensorTest extends OpMode{

    ColorSensor ColorSensor;

    @Override
    public void init() {

        ColorSensor = hardwareMap.colorSensor.get("ColorSensor");

        ColorSensor.enableLed(true);

    }

    @Override
    public void loop() {

        float hsvValues[] = {0F,0F,0F};

        Color.RGBToHSV(ColorSensor.red(), ColorSensor.green(), ColorSensor.blue(), hsvValues);

        boolean bPrevState = false;
        boolean bCurrState = false;

        boolean bEnabled = true;

        bCurrState = gamepad1.x || gamepad2.x;

        // check for button state transitions.
        if (bCurrState == true && bCurrState != bPrevState)  {
            // button is transitioning to a pressed state.

            // print a debug statement.
            DbgLog.msg("MY_DEBUG - x button was pressed!");

            // update previous state variable.
            bPrevState = bCurrState;

            // on button press, enable the LED.
            bEnabled = true;

            // turn on the LED.
            ColorSensor.enableLed(bEnabled);
        } else if (bCurrState == false && bCurrState != bPrevState)  {
            // button is transitioning to a released state.

            // print a debug statement.
            DbgLog.msg("MY_DEBUG - x button was released!");

            // update previous state variable.
            bPrevState = bCurrState;

            // on button press, enable the LED.
            bEnabled = false;

            // turn off the LED.
            ColorSensor.enableLed(false);
        }

        telemetry.addData("Text", "*** Robot Data***");
        telemetry.addData("Clear", ColorSensor.alpha());
        telemetry.addData("Red  ", ColorSensor.red());
        telemetry.addData("Green", ColorSensor.green());
        telemetry.addData("Blue ", ColorSensor.blue());
        telemetry.addData("Hue", hsvValues[0]);

    }

}
