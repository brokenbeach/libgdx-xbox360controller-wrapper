# libGDX Xbox 360 Controller Wrapper

This package includes a wrapper class that provides useful methods not included in the default implementation of [gdx-controllers](https://github.com/libgdx/libgdx/wiki/Controllers).

The included methods are shown below, and can be used with the controller mappings provided by the `Xbox360Controller.java` class.

```
    public boolean isButtonPressed(int buttonCode)
    public boolean isButtonJustPressed(int buttonCode)
    public boolean isDirectionalPadPressed(int direction)
    public boolean isDirectionalPadJustPressed(int direction)
    public float   getAxis(int axisCode)
    public boolean isRightTriggerPressed()
    public boolean isLeftTriggerPressed()
    public boolean isRightTriggerJustPressed()
    public boolean isLeftTriggerJustPressed()
    public float   getLeftTriggerValue()
    public float   getRightTriggerValue()
```

Example:

```
public class Screen {

    private static final float SPEED = 1.5f;

    private Player player;
    private ControllerWrapper controller;

    public Screen() {

        this.player = new Player();
        
        /* Provide a gdx-controller instance as parameter for the wrapper class */
        this.controller = new ControllerWrapper(Controllers.getControllers().first());
    }

    public void update(float delta) {

        /* Make sure the wrapper is updated once per frame */
        controller.update();

        float xAxis = controller.getAxis(AXIS_LEFT_X);
        float yAxis = controller.getAxis(AXIS_LEFT_Y);

        if(Math.abs(xAxis) > controller.AXIS_CUTOFF) player.vel.x = SPEED * xAxis;
        else player.vel.x = 0;

        if(Math.abs(yAxis) > controller.AXIS_CUTOFF) player.vel.y = -SPEED * yAxis;
        else player.vel.y = 0;
        
        player.update(delta);
    }
    
    ...

}

```

---

Created by brokenbeach
