package org.wax.engine;

import org.lwjgl.glfw.GLFW;
import org.wax.engine.IO.WaxWindow;
import org.wax.engine.staticConfigs.Input;
import org.wax.engine.times.Time;

public class Wax {

    //  -------------------- VARIABLES ----------------------

    public static int STATIC    = 0x88E4;
    public static int DYNAMIC   = 0x88E8;
    public static int RGBA      = 4;

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
}
