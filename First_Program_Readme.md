### 🎮 Simple application with WaxEngine 🎮
Here we will create a simple application, which will have just one square with a texture rotating in the center of the screen.
<br/>
This simple project is for you to see a little more about the "Quad" and how the Listener works.
<br/>
<br/>
To start, let's create a class named "SimpleApplication", which will have the "WaxModel" interface implemented.
<br/>
The "WaxModel" interface delivers the main functions for our apps/game.
```java
public class SimpleApplication implements WaxModel {

    public SimpleApplication() {
        
    }

    @Override
    public void start() {

    }

    @Override
    public void update() {
    
    }

    @Override
    public void draw() {

    }

    public static void main(String[] args) {

    }
}
```
After that, we need to make some statements:
```java

private WaxWindow window;
private WaxListener listener;
private Quad quad;

public SimpleApplication(){
    window = new WaxWindow("Simple application with WaxEngine!", 800, 600);
    listener = new WaxListener();
    quad = new Quad();
    
    listener.toListener(this);
}
```
`Quad` is a class for creating a simple square with some main functions for an object.
<br/>
WaxWindow has a constructor that requires 3 parameters. They are: `Title, Width and Height`
<br/>
`listener.toListener(this)` links our `WaxModel` to our Listener. `this` refers to our main class as it implements the `WaxModel`
<br/>
<br/>
After that, we'll make some important settings:
```java
@Override
public void start(){
    window.initialize();
    window.setVsync(true);
    
    quad.create(Wax.STATIC);
    quad.setTexture("/res/examples/wax_engine.png", true);
    quad.transform.scale(new Vector2f(0.4f, -0.4f));
}
```
`window.setVsync(true)` enables vsync and locks frames per second.
<br/>
`quad.create(Wax.STATIC)` creates the mesh of our square with a static shape(STATIC). The variable that stores the value for Static is in the Wax class, and that's why we access it.
<br/>
`quad.transform.scale(new Vector2f(0.4f, -0.4f))` sets a scale for our square. The number `-0.4f` is negative to give us an effect that the square is face down.
<br/>
<br/>
After that we need to update and render things.
```java
@Override
public void update(){
    Wax.time.run();
    window.poll();
}
@Override
public void draw(){
    window.clearColor(Wax.color.BLUE);
    window.swap();
}
```
`Wax.time.run()` starts our application's time for us to get the DeltaTime
<br/>
NOTE: You don't need to use `Wax.color.BLUE`, you can just use `new Vector3f(0.0f, 0.0f, 1.0f)`
<br/>
<br/>
Some things were missing... We didn't do anything to render our square... So we'll do it now :)
```java
@Override
public void draw(){
    (...)
    quad.draw();
    (...)
}
```
`quad.draw` is a function of our Square class to render automatically.
<br/>
<br/>
Now let's do the most important thing to run the application... The main method:
```java
public static void main(String[] args){
    SimpleApplication simple = new SimpleApplication();
    
    simple.listener.run(simple.window);
    simple.quad.delete();
    simple.listener.terminate(simple.window);
}
```
`simple.listener.run()` will run our application's main game loop. As already said on the Wiki, this function is not recommended for larger projects, because of not using Threads, and this can be a problem later on :(
<br/>
`simple.quad.delete()` will delete our square after the game loop ends.
<br/>
`simple.listener.terminate()` will destroy the window and terminate the WaxEngine after the game loop ends
<br/>
<br/>
Well, if you ran the application and everything worked out, then congratulations! The square isn't rotating yet, but it's already being rendered with a texture.
For us to do the rotation, it's very simple!:
```java
@Override
public void update(){
    (...)
    float angle_speed = 0.5f * (float)Wax.time.DELTA_TIME;
    quad.transform.rotate(angle_speed, new Vector3f(0.0f, 0.0f, 1.0f));
}
```
`Wax.time.DELTA_TIME` represents the value between the current and past time of our application. It is very good and recommended to use for values ​​that will be updated every frame and that will have to run according to different types of computers. As we know, computers are not the same, so some will have a slower game than the other. In order not to slow down the game than a turtle on earth, we've multiplied the value that will be constantly updated by the value in Delta.
<br/>
`quad.transform.rotate(angle_speed, new Vector3f(0.0f, 0.0f, 1.0f))` does the rotation of our square. Having the last parameter asking for the axis it will be rotated on. Since we're working with 2D, we'll only rotate on the Z Axis, but feel free to experiment by setting on any axis.
<br/>
<br/>
After that when you run you will see a beautiful rotating square :)
<br/>
The result: <br/> <img src="https://cdn.discordapp.com/attachments/837039667265142838/893557681237930004/unknown.png" width="50%">
<br/>
I know, it's a beautiful face :)
<br/>
<br/>
[(Complete code here)]https://github.com/AndradeSig/WaxEngine/blob/master/wax/tests/wax/examples/SimpleApplication.java
