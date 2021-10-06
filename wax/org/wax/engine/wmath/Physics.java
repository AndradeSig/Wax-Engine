package org.wax.engine.wmath;

import org.wax.engine.graphics.Mesh;
import org.wax.engine.graphics.models.Quad;

public class Physics {
    public static boolean QuadCollision(Quad first, Quad second)
    {
        boolean is_colliding = first.transform.getX() + first.transform.getWidth() > second.transform.getX() &&
                first.transform.getX() < second.transform.getX() + second.transform.getWidth() &&
                first.transform.getY() + first.transform.getHeight() > second.transform.getY() &&
                first.transform.getY() < second.transform.getY() + second.transform.getHeight();
        return is_colliding;
    }

    public static boolean MeshCollision(Mesh first, Mesh second)
    {
        boolean is_colliding = first.transform.getX() + first.transform.getWidth() > second.transform.getX() &&
                first.transform.getX() < second.transform.getX() + second.transform.getWidth() &&
                first.transform.getY() + first.transform.getHeight() > second.transform.getY() &&
                first.transform.getY() < second.transform.getY() + second.transform.getHeight();
        return is_colliding;
    }
}
