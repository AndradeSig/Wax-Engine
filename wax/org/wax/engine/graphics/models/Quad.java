package org.wax.engine.graphics.models;

import org.joml.Vector3f;
import org.wax.engine.IO.WaxUtils;
import org.wax.engine.Wax;
import org.wax.engine.graphics.Mesh;
import org.wax.engine.wmath.Transform;

public class Quad {

    private float[] quad_vertices = {
        0.5f,  0.5f, 0.0f,  1.0f, 1.0f,
        0.5f, -0.5f, 0.0f,   1.0f, 0.0f,
        -0.5f, -0.5f, 0.0f,   0.0f, 0.0f,
        -0.5f,  0.5f, 0.0f,   0.0f, 1.0f
    };

    int[] indices = {
        0, 1, 3,
        1, 3, 2
    };

    private Mesh mesh;
    public Transform transform;

    public Quad()
    {
        mesh = new Mesh(quad_vertices);
        transform = mesh.transform;

        mesh.Color_Location     = "_color";
        mesh.Texture_Location   = "_sampler";
        mesh.Transform_Location = "_transform";
        mesh.Texture_Index      = 1;
    }

    public void create(int usage)
    {
        mesh.setShader(WaxUtils.readFile("res/graphics/models/quadVertex.glsl"), WaxUtils.readFile("res/graphics/models/quadFragment.glsl"));
        mesh.bind(usage);
        mesh.bindElementBufferObject(indices, usage);

        mesh.setPointer(0, 3, 5 * Float.BYTES, 0);
        mesh.setPointer(1, 2, 5 * Float.BYTES, 3 * Float.BYTES);

        mesh.unbind();
    }

    public void draw()
    {
        mesh.bindToDraw(2);
        mesh.draw(indices.length);
        mesh.restore(2);
    }

    public void delete()
    {
        mesh.delete();
    }

    // -------------------- SETS ----------------------

    public void setColor(Vector3f color)
    {
        mesh.setColor(color);
    }

    public void setTexture(String path, boolean enable)
    {
        mesh.setTexture(path, enable);
    }
}
