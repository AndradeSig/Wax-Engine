package org.wax.engine.graphics;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;
import org.wax.engine.graphics.shader.Shader;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Mesh {

    private Shader shader;
    private float[] vertex;
    private int VAO, VBO, EBO;

    private FloatBuffer fVertBuffer;
    private IntBuffer iIndBuffer;

    public Mesh(float[] vertex)
    {
        this.vertex = vertex;
    }

    public void bind(int usage)
    {
        VBO = GL20.glGenBuffers();
        VAO = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(VAO);

        fVertBuffer = MemoryUtil.memAllocFloat(vertex.length);
        fVertBuffer.put(vertex).flip();

        GL20.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBO);
        GL20.glBufferData(GL15.GL_ARRAY_BUFFER, fVertBuffer, usage);
    }

    public void bindElementBufferObject(int[] indices, int usage)
    {
        iIndBuffer = MemoryUtil.memAllocInt(indices.length);
        iIndBuffer.put(indices).flip();

        EBO = GL20.glGenBuffers();
        GL20.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, EBO);
        GL20.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, iIndBuffer, usage);
    }

    public void unbind()
    {
        GL20.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        GL30.glBindVertexArray(0);
        if(EBO != 0)
            GL20.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        if(fVertBuffer != null)
            MemoryUtil.memFree(fVertBuffer);
        if(iIndBuffer != null)
            MemoryUtil.memFree(iIndBuffer);

    }

    public void delete()
    {
        System.out.println("Aoooobaaaa baooo?");
        GL20.glDeleteBuffers(VBO);
        if(EBO != 0)
            GL20.glDeleteBuffers(VBO);
        GL30.glDeleteVertexArrays(VAO);
        GL30.glDeleteProgram(shader.getProgram());
    }

    public void restore(int locals)
    {
        for(int i = 0; i < locals; i++)
            GL30.glDisableVertexAttribArray(i);
        GL30.glBindVertexArray(0);
    }

    // ----------------------- SETS --------------------------

    public void setShader(String vertex, String fragment)
    {
        shader = new Shader(vertex, fragment);
    }

    public void setPointer(int local, int pos, int stride, int pointer)
    {
        GL30.glVertexAttribPointer(local, pos, GL11.GL_FLOAT, false, stride, pointer);
        GL30.glEnableVertexAttribArray(local);
    }

    public void bindToDraw(int locals)
    {
        GL20.glUseProgram(shader.getProgram());
        GL30.glBindVertexArray(VAO);

        for(int i = 0; i < locals; i++)
            GL30.glEnableVertexAttribArray(i);
    }

    public void draw(int first, int count)
    {
        GL30.glDrawArrays(GL11.GL_TRIANGLES, first, count);
    }

    // ----------------------- GETS --------------------------

    public Shader getShader()
    {
        return shader;
    }

    public int getVAO()
    {
        return VAO;
    }

    public int getVBO()
    {
        return VBO;
    }

    public int getEBO()
    {
        return EBO;
    }
}
