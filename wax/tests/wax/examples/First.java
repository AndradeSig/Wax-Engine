package tests.wax.examples;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.wax.engine.IO.WaxListener;
import org.wax.engine.IO.WaxWindow;
import org.wax.engine.Wax;
import org.wax.engine.graphics.models.Quad;
import org.wax.engine.staticConfigs.WaxModel;

//  -------------------------------
//  WaxModel is an interface that gives me the main functions of a game.
//  It's not mandatory, but if you're going to use Listener, you'll have to implement WaxModel :)
public class First implements WaxModel {

    public static WaxWindow window;
    public static WaxListener listener;

    private Quad quad;

    public First()
    {

        //  -------------------------------
        //  Create Window and Listener
        window = new WaxWindow("LWJGL and WaxEngine!", 800, 600);
        listener = new WaxListener();

        quad = new Quad();

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

        quad.create(Wax.STATIC);
        quad.setTexture("/res/mine.png", true);
        quad.transform.scale(new Vector2f(0.4f, -0.5f));
    }

    @Override
    public void update() {
        //  ------------------------------
        //  Updating the events off Window
        Wax.time.run();
        window.poll();

        float pos = 2.0f * (float)Wax.time.DELTA_TIME;

        if(Wax.keyPressed(window, Wax.input.KEY_D))
            quad.transform.position(new Vector2f(pos, 0.0f));
        if(Wax.keyPressed(window, Wax.input.KEY_A))
            quad.transform.position(new Vector2f(-pos, 0.0f));
        if(Wax.keyPressed(window, Wax.input.KEY_W))
            quad.transform.position(new Vector2f(0.0f, pos));
        if(Wax.keyPressed(window, Wax.input.KEY_S))
            quad.transform.position(new Vector2f(0.0f, -pos));

    }

    @Override
    public void draw() {
        //  -------------------------------
        //  Cleaning and setting the window color. It will give us a background effect :)
        //  and Swapping the buffers
        window.clearColor(1.0f, 0.0f, 0.0f);

        quad.draw();

        window.swap();
    }

    public static void main(String[] args)
    {
        First first = new First();

        //  -------------------------------
        //  Telling our Listener that it will run the main game loop.
        //  Note that Listener only gives us something simple, it is not recommended for larger projects.
        listener.run(window);
        first.quad.delete();
        listener.terminate(window);
    }
}
