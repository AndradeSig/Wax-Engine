package tests.wax.examples;

import org.joml.Vector2f;
import org.wax.engine.IO.WaxListener;
import org.wax.engine.IO.WaxWindow;
import org.wax.engine.Wax;
import org.wax.engine.graphics.models.Quad;
import org.wax.engine.staticConfigs.WaxModel;
import org.wax.engine.wmath.Physics;

public class Game implements WaxModel {

    private WaxWindow window;
    private WaxListener listener;

    private Quad player;
    private Quad box;

    public Game(){
        window = new WaxWindow("Game", 800, 600);
        listener = new WaxListener();
        player = new Quad();
        box = new Quad();

        listener.toListener(this);
    }

    @Override
    public void start() {
        window.initialize();
        window.setVsync(true);

        player.create(Wax.STATIC);
        player.setTexture("/res/examples/wax_engine.png", true);
        player.transform.setScale(new Vector2f(0.3f, 0.4f));

        box.create(Wax.STATIC);
        box.setTexture("/res/examples/box.jpg", true);
        box.transform.setScale(new Vector2f(0.3f, 0.4f));
        box.transform.setPosition(new Vector2f(-0.5f, -0.5f));
    }

    @Override
    public void update() {
        Wax.time.run();
        window.poll();

        float speed = 0.5f * (float)Wax.time.DELTA_TIME;

        if(Wax.keyPressed(window, Wax.input.KEY_D)) player.transform.setPosition(new Vector2f(speed, 0.0f));
        if(Wax.keyPressed(window, Wax.input.KEY_A)) player.transform.setPosition(new Vector2f(-speed, 0.0f));
        if(Wax.keyPressed(window, Wax.input.KEY_W)) player.transform.setPosition(new Vector2f(0.0f, -speed));
        if(Wax.keyPressed(window, Wax.input.KEY_S)) player.transform.setPosition(new Vector2f(0.0f, speed));

        if(Physics.QuadCollision(player, box))
            System.out.println("Colliding!");
    }

    @Override
    public void draw() {
        window.clearColor(Wax.color.DARK_GRAY);
        box.draw();
        player.draw();
        window.swap();
    }

    public static void main(String[] args){
        Game game = new Game();
        game.listener.run(game.window);
        game.player.delete();
        game.listener.terminate(game.window);
    }
}
