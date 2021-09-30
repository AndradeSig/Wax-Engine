package org.wax.engine.wmath;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Transform {

    private Matrix4f transform = new Matrix4f();
    private float x, y;
    private float width, height;
    private float angle;

    public Matrix4f position(Vector2f position)
    {
        this.x = position.x;
        this.y = -position.y;
        return transform.translate(new Vector3f(x, y, 0.0f));
    }

    public Matrix4f scale(Vector2f scale)
    {
        this.width  = scale.x;
        this.height = scale.y;
        return transform.scale(new Vector3f(width, height, 0.0f));
    }

    public Matrix4f rotate(float angle, Vector3f axis)
    {
        this.angle = angle;
        return transform.rotate(this.angle, axis);
    }

    // --------------- GETS -------------------

    public Matrix4f get()
    {
        return transform;
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public float getWidth()
    {
        return width;
    }

    public float getHeight()
    {
        return height;
    }

    public float getAngle()
    {
        return angle;
    }
}
