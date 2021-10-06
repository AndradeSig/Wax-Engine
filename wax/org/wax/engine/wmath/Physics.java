package org.wax.engine.wmath;

import org.wax.engine.graphics.Mesh;
import org.wax.engine.graphics.models.Quad;

public class Physics {
    public static boolean colliding(Collider2d first, Collider2d second)
    {
        boolean is_colliding =
                first.getX() + first.getWidth() > second.getX() &&
                first.getX() < second.getX() + second.getWidth() &&
                first.getY() + first.getHeight() > second.getY() &&
                first.getY() < second.getY() + second.getHeight();
        return is_colliding;
    }

    public static boolean colliding(Collider2d first, Collider2d second, float offsetX, float offsetY)
    {
        boolean is_colliding =
                first.getX() + first.getWidth() + offsetX > second.getX() &&
                first.getX() + offsetX < second.getX() + second.getWidth() &&
                first.getY() + first.getHeight() + offsetY > second.getY() &&
                first.getY() + offsetY < second.getY() + second.getHeight();
        return is_colliding;
    }
}
