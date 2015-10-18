package com.qualcomm.ftcrobotcontroller.opmodes.FTC4991ResourceClasses;


public class ScaleInput {

    public ScaleInput(float Val) {
        scaleInput(Val);
    }

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
