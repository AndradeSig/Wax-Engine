package org.wax.engine.graphics;

import org.lwjgl.BufferUtils;
import org.wax.engine.Wax;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

public class ImageDecoder {

    public int width;
    public int height;
    public ByteBuffer pixels;

    public ImageDecoder(String path)
    {
        BufferedImage bToDecode;
        try {
            bToDecode = ImageIO.read(ImageDecoder.class.getResourceAsStream(path));
            width = bToDecode.getWidth();
            height = bToDecode.getHeight();

            int[] raws = new int[width * height]; // * Wax.RGBA
            raws = bToDecode.getRGB(0, 0, width, height, null, 0, width);

            pixels = BufferUtils.createByteBuffer(width * height * Wax.RGBA);

            for(int i = 0; i < raws.length; i++)
            {
                int cPixel = raws[i];
                pixels.put((byte) ((cPixel >> 16) & 0XFF) );    // R
                pixels.put((byte) ((cPixel >> 8)  & 0xFF));     // G
                pixels.put((byte) ((cPixel)       & 0xFF));     // B
                pixels.put((byte) ((cPixel >> 24) & 0xFF));     // A
            }
            pixels.flip();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
