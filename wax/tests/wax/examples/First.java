package tests.wax.examples;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.wax.engine.IO.WaxListener;
import org.wax.engine.IO.WaxUtils;
import org.wax.engine.IO.WaxWindow;
import org.wax.engine.Wax;
import org.wax.engine.graphics.Mesh;
import org.wax.engine.graphics.shader.Shader;
import org.wax.engine.staticConfigs.WaxModel;

//  -------------------------------
//  WaxModel is an interface that gives me the main functions of a game.
//  It's not mandatory, but if you're going to use Listener, you'll have to implement WaxModel :)
public class First implements WaxModel {

    public static WaxWindow window;
    public static WaxListener listener;

    private float[] vertexes = {
            -0.5f, -0.5f, 0.0f,
            0.5f, -0.5f, 0.0f,
            0.0f,  0.5f, 0.0f
    };

    private Mesh mesh;

    public First()
    {

        //  -------------------------------
        //  Create Window and Listener
        window = new WaxWindow("LWJGL and WaxEngine!", 800, 600);
        listener = new WaxListener();

        mesh = new Mesh(vertexes);

        //  -------------------------------
        //  Referencing the class that has the "WaxModel" implement to the listener
        listener.toListener(this);
    }

    @Override
    public void start() {

        //  -------------------------------
        //  Initialize the Window and set Vsync
        window.initialize();
        window.setVsync(true);

        mesh.setShader(WaxUtils.readFile("res/graphics/simpleVertex.glsl"), WaxUtils.readFile("res/graphics/simpleFragment.glsl"));
        mesh.bind(Shader.STATIC);
        mesh.setPointer(0, 3, 3 * Float.BYTES, 0);
        mesh.unbind();
    }

    @Override
    public void update() {
        //  ------------------------------
        //  Updating the events off Window
        window.poll();
    }

    @Override
    public void draw() {
        //  -------------------------------
        //  Cleaning and setting the window color. It will give us a background effect :)
        //  and Swapping the buffers
        window.clearColor(1.0f, 0.0f, 0.0f);

        mesh.bindToDraw(1);
        mesh.draw(0, 3);

        window.swap();
    }

    public static void main(String[] args)
    {
        First first = new First();

        //  -------------------------------
        //  Telling our Listener that it will run the main game loop.
        //  Note that Listener only gives us something simple, it is not recommended for larger projects.
        listener.run(window);
        first.mesh.delete();
        listener.terminate(window);
    }
}
