package com.brokenbeach.controllers;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.PovDirection;

/**
 * @author brokenbeach
 * @since 15/02/2017
 */
public class ControllerWrapper {

    public static final float TRIGGER_CUTOFF = 0.2f;
    public static final float AXIS_CUTOFF    = 0.15f;
    private static final int LEFT_TRIGGER  = 0;
    private static final int RIGHT_TRIGGER = 1;

    private Controller controller;
    private boolean[] buttonPressed;
    private boolean[] buttonPressedPrevious;
    private boolean[] triggerPressed;
    private boolean[] triggerPressedPrevious;
    private boolean[] dpadPressed;
    private boolean[] dpadPressedPrevious;

    public ControllerWrapper(Controller controller) {

        this.controller = controller;
        buttonPressed          = new boolean[Xbox360Controller.N_BUTTONS];
        buttonPressedPrevious  = new boolean[Xbox360Controller.N_BUTTONS];
        triggerPressed         = new boolean[Xbox360Controller.N_TRIGGERS];
        triggerPressedPrevious = new boolean[Xbox360Controller.N_TRIGGERS];
        dpadPressed            = new boolean[Xbox360Controller.N_DPAD];
        dpadPressedPrevious    = new boolean[Xbox360Controller.N_DPAD];
    }

    public void update() {

        // buttons
        for(int i = 0; i< Xbox360Controller.N_BUTTONS; i++) {
            buttonPressedPrevious[i] = buttonPressed[i];
            buttonPressed[i] = isButtonPressed(i);
        }

        // triggers
        triggerPressedPrevious[LEFT_TRIGGER]  = triggerPressed[LEFT_TRIGGER];
        triggerPressedPrevious[RIGHT_TRIGGER] = triggerPressed[RIGHT_TRIGGER];
        triggerPressed[LEFT_TRIGGER]  = isLeftTriggerPressed();
        triggerPressed[RIGHT_TRIGGER] = isRightTriggerPressed();

        // dpad
        int code = -1;
        PovDirection currentDirection = controller.getPov(0);
        if(currentDirection == PovDirection.north) code = Xbox360Controller.BUTTON_DPAD_NORTH;
        if(currentDirection == PovDirection.south) code = Xbox360Controller.BUTTON_DPAD_SOUTH;
        if(currentDirection == PovDirection.west)  code = Xbox360Controller.BUTTON_DPAD_WEST;
        if(currentDirection == PovDirection.east)  code = Xbox360Controller.BUTTON_DPAD_EAST;
        if(currentDirection == PovDirection.northWest) code = Xbox360Controller.BUTTON_DPAD_NORTHWEST;
        if(currentDirection == PovDirection.northEast) code = Xbox360Controller.BUTTON_DPAD_NORTHEAST;
        if(currentDirection == PovDirection.southWest) code = Xbox360Controller.BUTTON_DPAD_SOUTHWEST;
        if(currentDirection == PovDirection.southEast) code = Xbox360Controller.BUTTON_DPAD_SOUTHEAST;

        for(int i = 0; i< Xbox360Controller.N_DPAD; i++) {
            boolean pressed = (i == code);
            dpadPressedPrevious[i] = dpadPressed[i];
            dpadPressed[i] = pressed;
        }
    }

    public boolean isButtonPressed(int buttonCode) {

        return controller.getButton(buttonCode);
    }

    public boolean isButtonJustPressed(int buttonCode) {

        if(buttonCode < 0 || buttonCode >= Xbox360Controller.N_BUTTONS) return false;
        else return buttonPressed[buttonCode] && !buttonPressedPrevious[buttonCode];
    }

    public float getAxis(int axisCode) {

        return controller.getAxis(axisCode);
    }

    public boolean isRightTriggerPressed() {

        return getRightTriggerValue() > TRIGGER_CUTOFF;
    }

    public boolean isLeftTriggerPressed() {

        return getLeftTriggerValue() > TRIGGER_CUTOFF;
    }

    public boolean isRightTriggerJustPressed() {

        return triggerPressed[RIGHT_TRIGGER] && !triggerPressedPrevious[RIGHT_TRIGGER];
    }

    public boolean isLeftTriggerJustPressed() {

        return triggerPressed[LEFT_TRIGGER] && !triggerPressedPrevious[LEFT_TRIGGER];
    }

    public float getLeftTriggerValue() {

        return controller.getAxis(Xbox360Controller.AXIS_LEFT_TRIGGER);
    }

    public float getRightTriggerValue() {

        return controller.getAxis(Xbox360Controller.AXIS_RIGHT_TRIGGER);
    }

    public boolean isDirectionalPadPressed(int direction) {

        return dpadPressed[direction];
    }

    public boolean isDirectionalPadJustPressed(int direction) {

        return dpadPressed[direction] && !dpadPressedPrevious[direction];
    }

}