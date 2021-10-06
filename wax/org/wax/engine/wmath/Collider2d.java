package org.wax.engine.wmath;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.wax.engine.Wax;
import org.wax.engine.graphics.models.Quad;

public class Collider2d {

    private Quad mesh_of_collider;

    public Collider2d()
    {
        mesh_of_collider = new Quad();
    }

    public void create()
    {
        mesh_of_collider.create(Wax.STATIC);
    }

    public void debug(int mode, Vector3f color, boolean enabled)
    {
        if(enabled){
            mesh_of_collider.setColor(color);
            GL15.glPolygonMode(GL11.GL_FRONT_AND_BACK, mode);
            mesh_of_collider.draw();
            GL15.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
        }
    }

    public void destroy()
    {
        mesh_of_collider.delete();
    }

    // ----------------- SETS -----------------

    public void setPosition(Vector2f position)
    {
        mesh_of_collider.transform.setPosition(position);
    }

    public void setScale(Vector2f scale)
    {
        mesh_of_collider.transform.setScale(scale);
    }

    // --------------- GETS -------------------

    public float getX()
    {
        return mesh_of_collider.transform.getX();
    }

    public float getY()
    {
        return mesh_of_collider.transform.getY();
    }

    public float getWidth()
    {
        return mesh_of_collider.transform.getWidth();
    }

    public float getHeight()
    {
        return mesh_of_collider.transform.getHeight();
    }
}
