package tests.wax.examples;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.wax.engine.IO.WaxListener;
import org.wax.engine.IO.WaxWindow;
import org.wax.engine.Wax;
import org.wax.engine.graphics.models.Quad;
import org.wax.engine.staticConfigs.WaxModel;

public class SimpleApplication implements WaxModel {

    private WaxWindow window;
    private WaxListener listener;
    private Quad quad;

    private float speed_angle;

    public SimpleApplication() {
        window = new WaxWindow("Simple application with WaxEngine", 800, 600);
        listener = new WaxListener();
        quad = new Quad();

        listener.toListener(this);
    }

    @Override
    public void start() {
        window.initialize();
        window.setVsync(true);

        quad.create(Wax.STATIC);
        quad.setTexture("/res/examples/wax_engine.png", true);
        quad.transform.setScale(new Vector2f(0.4f, 0.4f));
    }

    @Override
    public void update() {
        Wax.time.run();
        window.poll();

        speed_angle += 0.5f * (float)Wax.time.DELTA_TIME;
        quad.transform.setRotate(speed_angle, new Vector3f(0.0f, 0.0f, 1.0f));
    }

    @Override
    public void draw() {
        window.clearColor(Wax.color.BLUE);
        quad.draw();
        window.swap();
    }

    public static void main(String[] args) {
        SimpleApplication simple = new SimpleApplication();

        simple.listener.run(simple.window);
        simple.quad.delete();
        simple.listener.terminate(simple.window);
    }
}
