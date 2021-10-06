package org.wax.engine.IO;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class WaxWindow {

    private long _ID;
    private String title;
    private int WIDTH, HEIGHT;
    private int lastX, lastY;
    private boolean fullscreen = false;

    private double mouseX, mouseY;

    public WaxWindow(String title, int WIDTH, int HEIGHT)
    {
        this.title = title;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
    }

    public void initialize()
    {
        if(!GLFW.glfwInit())
            throw new IllegalArgumentException("The GLFW doens't initialized. For more informations, visit: https://github.com/andradesig");
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 3);
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GLFW.GLFW_TRUE);

        _ID = GLFW.glfwCreateWindow(WIDTH, HEIGHT, title, 0, 0);
        if(_ID == 0)
            throw new IllegalArgumentException("The Window doens't initialized. For more informations, visit: https://github.com/andradesig");

        GLFWVidMode vMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        lastX =  (vMode.width() - WIDTH)/2;
        lastY = (vMode.height() - HEIGHT)/2;
        GLFW.glfwSetWindowPos(_ID, (vMode.width() - WIDTH)/2, (vMode.height() - HEIGHT)/2);

        GLFW.glfwMakeContextCurrent(_ID);

        GLFW.glfwSetWindowSizeCallback(_ID, new GLFWWindowSizeCallback() {
            @Override
            public void invoke(long window, int width, int height) {
                GL11.glViewport(0, 0, width, height);
            }
        });

        GLFW.glfwSetCursorPosCallback(_ID, new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xpos, double ypos) {
                mouseX = xpos;
                mouseY = ypos;
            }
        });

        GL.createCapabilities();
    }

    public void poll()
    {
        GLFW.glfwPollEvents();
    }

    public void clearColor(float r, float g, float b)
    {
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClearColor(r, g, b, 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    }

    public void clearColor(Vector3f color)
    {
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClearColor(color.x, color.y, color.z, 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    }

    public void swap()
    {
        GLFW.glfwSwapBuffers(_ID);
    }

    public void destroy()
    {
        GLFW.glfwSetWindowShouldClose(_ID, true);
        GLFW.glfwDestroyWindow(_ID);
        GLFW.glfwTerminate();
    }

    // ---------------------- SETS -----------------------

    public void setVsync(boolean enable)
    {
        if(enable)
            GLFW.glfwSwapInterval(1);
        else
            GLFW.glfwSwapInterval(0);
    }

    public void setClosed(boolean close)
    {
        GLFW.glfwSetWindowShouldClose(_ID, close);
    }

    public void setFullscreen(boolean fullscreen)
    {
        this.fullscreen = fullscreen;
        if(fullscreen){
            GLFWVidMode vidMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
            GLFW.glfwSetWindowMonitor(_ID, GLFW.glfwGetPrimaryMonitor(), 0, 0, vidMode.width(), vidMode.height(), 0);
        }else{
            GLFW.glfwSetWindowMonitor(_ID, 0, lastX, lastY, WIDTH, HEIGHT, 0);
        }
    }

    // ---------------------- GETS -----------------------

    public boolean isOpen()
    {
        return !GLFW.glfwWindowShouldClose(_ID);
    }

    public boolean isFullscreen()
    {
        return fullscreen;
    }

    public long getID()
    {
        return _ID;
    }

    public double getMouseX()
    {
        return mouseX;
    }

    public double getMouseY()
    {
        return mouseY;
    }
}
