package org.wax.engine.graphics;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;
import org.wax.engine.graphics.shader.Shader;
import org.wax.engine.wmath.Transform;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Mesh {

    private Shader shader;
    public Transform transform;
    private Material material;

    private float[] vertex;
    private int VAO, VBO, EBO;

    private FloatBuffer fVertBuffer;
    private IntBuffer iIndBuffer;

    private boolean withTexture = false;

    public Mesh(float[] vertex)
    {
        material = new Material();
        transform = new Transform();
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

    public void bindToDraw(int locals)
    {
        GL20.glUseProgram(shader.getProgram());
        GL30.glBindVertexArray(VAO);

        if(withTexture){
            material.bindTexture();
            GL20.glActiveTexture(GL15.GL_TEXTURE0);
            Shader.enableTexture(0, shader.getProgram(), "texture1");
        }

        for(int i = 0; i < locals; i++)
            GL30.glEnableVertexAttribArray(i);
    }

    public void draw(int first, int count)
    {
        Shader.setMat4(transform.get(), shader.getProgram(), "transform");
        GL30.glDrawArrays(GL11.GL_TRIANGLES, first, count);
    }

    public void draw(int length)
    {
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        Shader.setMat4(transform.get(), shader.getProgram(), "transform");
        GL30.glDrawElements(GL11.GL_TRIANGLES, length, GL11.GL_UNSIGNED_INT, 0);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }

    // ----------------------- SETS --------------------------

    public void setShader(String vertex, String fragment)
    {
        shader = new Shader(vertex, fragment);
    }

    public void setTexture(String path, boolean enable)
    {
        this.withTexture = enable;
        material.setTexture(path);
    }

    public void setPointer(int local, int pos, int stride, int pointer)
    {
        GL30.glVertexAttribPointer(local, pos, GL11.GL_FLOAT, false, stride, pointer);
        GL30.glEnableVertexAttribArray(local);
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
