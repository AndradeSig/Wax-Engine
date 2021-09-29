package org.wax.engine.times;

import org.lwjgl.glfw.GLFW;

public class Time {

    public double DELTA_TIME = 0.0;
    public double FRAMES = 0;
    private double lastTime = 0.0;

    public void run()
    {
        double currentTime = GLFW.glfwGetTime();
        DELTA_TIME = currentTime - lastTime;
        FRAMES = 1 / DELTA_TIME;
        lastTime = currentTime;
    }
}
