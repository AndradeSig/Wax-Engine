package org.wax.engine;

import org.lwjgl.glfw.GLFW;
import org.wax.engine.IO.WaxWindow;
import org.wax.engine.staticConfigs.Input;
import org.wax.engine.times.Time;

public class Wax {

    //  --------------------- OBJECTS ---------------------
    public static Time time = new Time();
    public static Input input = new Input();

    // --------------------- FOR INPUTS ---------------------

    public static boolean keyPressed(WaxWindow window, int key)
    {
        return GLFW.glfwGetKey(window.getID(), key) == input.PRESS;
    }

    public static boolean keyReleased(WaxWindow window, int key)
    {
        return GLFW.glfwGetKey(window.getID(), key) == input.RELEASE;
    }
}
