package org.wax.engine.wmath;

import org.joml.*;

public class Transform {

    private Matrix4f transform = new Matrix4f();
    private float x, y;
    private float width = 1.0f, height = 1.0f;
    private float angle;
    private Vector3f axis = new Vector3f(0.0f, 0.0f, 0.0f);

    public void createTransform(Matrix4f transform)
    {
        this.transform = transform;
    }

    // --------------- GETS -------------------

    public Matrix4f getTransform()
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

    public Vector3f getAxis()
    {
        return axis;
    }

    // --------------- SETS ----------------

    public void setMovement(Vector2f offmove)
    {
        this.x = x + offmove.x;
        this.y = y + offmove.y;
    }

    public void setPosition(Vector2f position)
    {
        this.x = position.x;
        this.y = position.y;
    }

    public void setScale(Vector2f scale)
    {
        this.width  = scale.x;
        this.height = scale.y;
    }

    public void setRotate(float angle, Vector3f axis)
    {
        this.angle = angle;
        this.axis  = axis;
    }
}
