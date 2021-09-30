package org.wax.engine.graphics.shader;

import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.wax.engine.Wax;

public class Shader {

    public static int STATIC    = GL15.GL_STATIC_DRAW;
    public static int DYNAMIC   = GL15.GL_DYNAMIC_DRAW;

    private int _V_SHADER, _F_SHADER;
    private int _S_PROGRAM;

    public Shader(String vertex, String fragment)
    {
        compileVertexShader(vertex);
        compileFragmentShader(fragment);
        createProgram();
    }

    private void compileVertexShader(String v)
    {
        _V_SHADER = GL30.glCreateShader(GL20.GL_VERTEX_SHADER);
        GL30.glShaderSource(_V_SHADER, v);
        GL30.glCompileShader(_V_SHADER);

        if(GL20.glGetShaderi(_V_SHADER, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE)
            throw new IllegalStateException("Error to compile Vertex Shader -> " + GL20.glGetShaderInfoLog(_V_SHADER));
    }

    private void compileFragmentShader(String f)
    {
        _F_SHADER = GL30.glCreateShader(GL20.GL_FRAGMENT_SHADER);
        GL30.glShaderSource(_F_SHADER, f);
        GL30.glCompileShader(_F_SHADER);

        if(GL20.glGetShaderi(_F_SHADER, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE)
            throw new IllegalStateException("Error to compile Fragment Shader -> " + GL20.glGetShaderInfoLog(_F_SHADER));
    }

    private void createProgram()
    {
        _S_PROGRAM = GL30.glCreateProgram();
        GL30.glAttachShader(_S_PROGRAM, _V_SHADER);
        GL30.glAttachShader(_S_PROGRAM, _F_SHADER);
        GL30.glLinkProgram(_S_PROGRAM);

        GL20.glDeleteShader(_V_SHADER);
        GL20.glDeleteShader(_F_SHADER);
    }

    // ---------------- GETS ------------------

    public int getProgram()
    {
        return _S_PROGRAM;
    }

    // --------------- SETS -------------------

    public static void setColor(Vector3f color, int program, String location)
    {
        GL20.glUniform3f(GL20.glGetUniformLocation(program, location), color.x, color.y, color.z);
    }
}
