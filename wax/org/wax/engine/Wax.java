package org.wax.engine;

import org.lwjgl.glfw.GLFW;
import org.wax.engine.IO.WaxWindow;
import org.wax.engine.staticConfigs.Input;
import org.wax.engine.times.Time;

public class Wax {

    //  -------------------- VARIABLES ----------------------

    public static int DEFAULT       = 0;
    public static int CENTRALIZED   = 1;
    public static int NULL          = -1;
    public static int STATIC        = 0x88E4;
    public static int DYNAMIC       = 0x88E8;
    public static int RGBA          = 4;
    public static int POINT         = 0x1B00;
    public static int LINE          = 0x1B01;
    public static int FILL          = 0x1B02;

    //  --------------------- OBJECTS -----------------------
    public static Time time         = new Time();
    public static Input input       = new Input();
    public static WaxColor color    = new WaxColor();

    // --------------------- FOR INPUTS ---------------------

    public static boolean keyPressed(WaxWindow window, int key)
    {
        return GLFW.glfwGetKey(window.getID(), key) == input.PRESS;
    }

    public static boolean keyReleased(WaxWindow window, int key)
    {
        return GLFW.glfwGetKey(window.getID(), key) == input.RELEASE;
    }

    public static double mouseX(WaxWindow window)
    {
        return window.getMouseX();
    }

    public static double mouseY(WaxWindow window)
    {
        return window.getMouseY();
    }

    // --------------------- FOR OUTPUTS ---------------------

    public static double getClock()
    {
        return GLFW.glfwGetTime();
    }
}
