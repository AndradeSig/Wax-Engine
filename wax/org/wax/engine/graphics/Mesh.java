package org.wax.engine.graphics;

import org.joml.Matrix4f;
import org.joml.Vector3f;
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

    private Vector3f color = new Vector3f(0.0f, 0.0f, 0.0f);

    public String Color_Uniform         = "";
    public String Texture_Uniform       = "";
    public String Transform_Uniform     = "";

    public int Texture_Location         = 0;

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
            GL20.glUniform1i(GL20.glGetUniformLocation(shader.getProgram(), Texture_Uniform), 0);
            GL30.glEnableVertexAttribArray(Texture_Location);
        }

        for(int i = 0; i < locals; i++)
            if(i != Texture_Location) GL30.glEnableVertexAttribArray(i);
    }

    public void draw(int first, int count)
    {
        GL11.glDisable(GL11.GL_DEPTH_TEST);

        Shader.setColor(color, shader.getProgram(), Color_Uniform);

        Matrix4f _transform = new Matrix4f();
        transform.createTransform(_transform);
        //  Default Transformations
        transform.getTransform().translate(new Vector3f(transform.getX(), transform.getY(), 0.0f));
        transform.getTransform().scale(new Vector3f(transform.getWidth(), -transform.getHeight(), 0.0f));
        transform.getTransform().rotate(transform.getAngle(), transform.getAxis());
        Shader.setMat4(transform.getTransform(), shader.getProgram(), Transform_Uniform);

        GL30.glDrawArrays(GL11.GL_TRIANGLES, first, count);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }

    public void draw(int length)
    {
        GL11.glDisable(GL11.GL_DEPTH_TEST);

        Shader.setColor(color, shader.getProgram(), Color_Uniform);

        Matrix4f _transform = new Matrix4f();
        transform.createTransform(_transform);
        //  Default Transformations
        transform.getTransform().translate(new Vector3f(transform.getX(), transform.getY(), 0.0f));
        transform.getTransform().scale(new Vector3f(transform.getWidth(), -transform.getHeight(), 0.0f));
        transform.getTransform().rotate(transform.getAngle(), transform.getAxis());
        Shader.setMat4(transform.getTransform(), shader.getProgram(), Transform_Uniform);

        GL30.glDrawElements(GL11.GL_TRIANGLES, length, GL11.GL_UNSIGNED_INT, 0);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }

    // ----------------------- SETS --------------------------

    public void setShader(String vertex, String fragment)
    {
        shader = new Shader(vertex, fragment);
    }

    public void setColor(Vector3f color)
    {
        this.color = color;
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
