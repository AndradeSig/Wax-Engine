### 🎮 Simple application with WaxEngine 🎮
To start, let's create our class named `Main` which will be the main class of our application.
<br/>
```java
public class SimpleApplication implements WaxModel {

    private WaxWindow window;
    private WaxListener listener;
    private Quad quad;

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
        quad.transform.scale(new Vector2f(0.4f, -0.4f));
    }

    @Override
    public void update() {
        Wax.time.run();
        window.poll();

        float speed_angle = 0.5f * (float)Wax.time.DELTA_TIME;
        quad.transform.rotate(speed_angle, new Vector3f(0.0f, 0.0f, 1.0f));
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
```
The result: <br/> <img src="https://cdn.discordapp.com/attachments/837039667265142838/893557681237930004/unknown.png" width="50%">
<br/>
I know, it's a beautiful face :)
