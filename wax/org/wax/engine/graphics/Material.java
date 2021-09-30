package org.wax.engine.graphics;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

public class Material {

    private ImageDecoder image;
    private int texture_id;

    public void setTexture(String path)
    {
        image = new ImageDecoder(path);

        texture_id = GL15.glGenTextures();
        GL15.glBindTexture(GL11.GL_TEXTURE_2D, texture_id);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL15.GL_MIRRORED_REPEAT);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL15.GL_MIRRORED_REPEAT);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL15.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL15.GL_NEAREST);

        GL15.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, image.width, image.height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, image.pixels);
    }

    public void bindTexture()
    {
        GL15.glBindTexture(GL11.GL_TEXTURE_2D, texture_id);
    }
}
