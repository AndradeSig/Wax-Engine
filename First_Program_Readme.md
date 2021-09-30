### 🎮 First application with WaxEngine 🎮
To start, let's create our class named `Main` which will be the main class of our application.
<br/>
```java
public class Main implements WaxModel {

    public static WaxWindow window;
    public static listener WaxListener;

    public Main () {
        window = new WaxWindow ("My first app with WaxEngine!", 800, 600);
        listener = new WaxListener();

        listener.toListener(this);
    }

    @Override
    public void start() {
        window.initialize();
    }

    @Override
    public void update() {
        window.poll();
    }

    @Override
    public void draw() {
        window.clearColor(1.0f, 0.0f, 0.0f);
        window.swap();
    }

    public static void main(String[] args) {
        new Main();
        listener.run(window);
        listener.terminate(window);
    }
}
```
The `WaxModel` implement is an interface that contains the main functions for running the game.
```java
start();
update();
draw();
```
Note: You must implement this interface if you are going to use a `WaxListener`
#
The `WaxWindow` class is a class that will have the working of the Window.
```java
1st Argument: Window Title
2nd Argument: Window Width
3rd Argument: Window Height
```
Functions
```java
initialize ()   -> Is work to initialize a window and configure its settings
poll()          -> Is work to update Window events
clearColor()    -> Is work to clear the screen with "RGB" colors (remembering that WaxEngine works with normalized coordinates, and so it goes from 0.0 to 1.0)
swap()          -> Is work to swap the buffers on the screen
```
#
The `WaxListener` class is a class that will communicate and facilitate the GameLoop process of our game / application.
```java
toListener()    -> Indicates a binding of the "WaxModel" interface with its methods to the Listener.
run()           -> Is work to run the GameLoop in the current window.
terminate()     -> will terminate and delete a window when closed.
```
Remember: It is only recommended to use a `WaxListener` when creating a simple project, as a `WaxListener` doesn't work with Threads (yet).
