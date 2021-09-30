# Wax Engine
💫 A simple, and fast Game Engine for Java.

### What's it?
⚙️ WaxEngine is an engine for Java, using LWJGL 3 and GLFW.
The WaxEngine was created to facilitate your process in creating a game, so that you do not need to create an engine from scratch, or spend a long time reading a boring and tedious documentation.

### It's easy?
Yes! It's pretty easy, with a little reading you'll understand WaxEngine and you'll be able to create your games freely 😄
# Wiki
📒 WaxEngine Basic Wiki
<br/>
### 🎮 First application with WaxEngine 🎮
To start, let's create our class named `Main` which will be the main class of our application.
<br/>
```java
public class Main implements WaxModel {

public static WaxWindow window;
static public listener WaxListener;

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
listener.run (window);
listener.terminate (window);
}
}
```
The `WaxModel` implement is an interface that contains the main functions for running the game.
```java
to start();
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
`` `
Roles
`` `java
initialize () -> Serve to initialize a window and configure its settings
poll() -> Serve to update Window events
clearColor() -> Serve to clear the screen with "RG B" cores (remembering that WaxEngine works with normalized coordinates, and so it goes from 0.0 to 1.0)
swap() -> Serve to swap the buffers on the screen
```
#
The `WaxListener` class is a class that will communicate and facilitate the GameLoop process of our game / application.
```java
toListener() -> indicates a binding of the "WaxModel" interface with its methods to the Listener.
run() -> serves to run the GameLoop in the current window.
terminate() -> will terminate and delete a window when closed.
```
Remember: It is only recommended to use a `WaxListener` when creating a simple project, as a `WaxListener` doesn't work with Threads (yet).

