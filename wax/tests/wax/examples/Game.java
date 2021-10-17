package tests.wax.examples;

import org.joml.Vector2f;
import org.wax.engine.IO.WaxListener;
import org.wax.engine.IO.WaxWindow;
import org.wax.engine.Wax;
import org.wax.engine.graphics.dynamic.Animator2D;
import org.wax.engine.graphics.models.Quad;
import org.wax.engine.staticConfigs.WaxModel;
import org.wax.engine.wmath.Collider2d;
import org.wax.engine.wmath.Physics;

public class Game implements WaxModel {

    private WaxWindow window;
    private WaxListener listener;

    private Quad player;
    private Quad box;

    private Collider2d collider;
    private Collider2d collider2;

    private Animator2D animator;

    public Game(){
        window = new WaxWindow("Game", 800, 600);
        listener = new WaxListener();
        player = new Quad();

        box = new Quad();

        collider = new Collider2d();
        collider2 = new Collider2d();

        animator = new Animator2D();

        listener.toListener(this);
    }

    @Override
    public void start() {

        //  Configure the Window
        window.setResizable(false);
        window.setLocation(Wax.CENTRALIZED);

        window.initialize();
        window.setVsync(true);

        player.create(Wax.STATIC);
        //player.setTexture("/res/examples/wax_engine.png", true);
        player.transform.setScale(new Vector2f(0.3f, 0.4f));

        box.create(Wax.STATIC);
        box.setTexture("/res/examples/box.jpg", true);
        box.transform.setScale(new Vector2f(0.3f, 0.4f));
        box.transform.setPosition(new Vector2f(-0.5f, 0.1f));

        collider.create();
        collider2.create();

        animator.getMesh(player.getMesh());

        animator.setAnimation(new String[]{"/res/examples/wax_engine.png", "/res/examples/wax_engine_02.png", "/res/examples/wax_engine_03.png"});
    }

    @Override
    public void update() {
        Wax.time.run();
        window.poll();

        float speed = 0.5f * (float)Wax.time.DELTA_TIME;

        if(Wax.keyPressed(window, Wax.input.KEY_D) && !Physics.colliding(collider, collider2, speed, 0.0f)) player.transform.setMovement(new Vector2f(speed, 0.0f));
        if(Wax.keyPressed(window, Wax.input.KEY_A) && !Physics.colliding(collider, collider2, -speed, 0.0f)) player.transform.setMovement(new Vector2f(-speed, 0.0f));
        if(Wax.keyPressed(window, Wax.input.KEY_W) && !Physics.colliding(collider, collider2, 0.0f, speed)) player.transform.setMovement(new Vector2f(0.0f, speed));
        if(Wax.keyPressed(window, Wax.input.KEY_S) && !Physics.colliding(collider, collider2, 0.0f, -speed)) player.transform.setMovement(new Vector2f(0.0f, -speed));

        collider.setPosition(new Vector2f(player.transform.getX(), player.transform.getY()));
        collider.setScale(new Vector2f(player.transform.getWidth(), player.transform.getHeight()));

        collider2.setPosition(new Vector2f(box.transform.getX(), box.transform.getY()));
        collider2.setScale(new Vector2f(box.transform.getWidth(), box.transform.getHeight()));

        animator.playAnimation(0, 8);
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
        game.collider.destroy();
        game.collider2.destroy();
        game.player.delete();
        game.box.delete();

        game.listener.terminate(game.window);
    }
}
