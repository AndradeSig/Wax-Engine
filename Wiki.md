# Engine
## 🔅 Wax
Can the `Wax` class be considered the main engine? Perhaps! But with this class, you have access to very important objects and functions.
```java
Wax.time -> Object that manages your application time (more detailed information in the explanation of the Time class)
Wax.inputs -> Object that stores and stores information
              of all inputs from our application (Like keys, mouse buttons, etc.)

Wax.keyPressed(WaxWindow window, int key)   -> Checks if a key was pressed in the chosen Window
Wax.keyReleased(WaxWindow window, int key)  -> Checks if a key was released in the chosen Window
```
## 🧷 WaxModel
The `WaxModel` Interface is responsible for delivering the main functions based on your Application
```java
start()
update()
draw()
```

## ⏰ Time
The `Time` class is responsible for managing your application's time in a simple way
```java
run()       -> It is responsible for running time and updating every Update of your application
DELTA_TIME  -> It is responsible for indicating the 
               time between the current time of your application, and the elapsed time
              
FRAMES      -> It is responsible for indicating the amount of Frames per second of your application(FPS)
```

# I/O
## 🖥️ WaxWindow
The `WaxWindow` class is responsible for creating, managing and manipulating a Window of your application. It has many functions and we will see them here
```java

initialize()                  -> Initialize and create the Window
poll()                        -> Update Window events
clearColor(R, G, B)           -> Clears the window with a specified color in RGB
swap()                        -> Swap Window buffers for rendering
destroy()                     -> Destroy the Window

setResizable(boolean resizable) -> Set your window resizable or not resizable
setVsync(true or false)       -> Enable or Disable the Vsync option
setClosed(true or false)      -> Enables or Disables the Window closed and open state
setFullscreen(true or false)  -> Enable or Disable Window Full Screen option
setResolution(int width, int height)  -> Set a new Resolution for your Window
setSize(int width, int height)        -> Set a new Size for yout window

isOpen()                      -> Check if the Window is open
isFullscreen()                -> Check if Window is full screen
getID()                       -> Gets the Window ID, which is added when created.
isResizable()                 -> Check if Window is resizable
getMonitorWidth()             -> Get the Width of your Monitor
getMonitorHeight()            -> Get the Height of your Monitor
getMouseX()                   -> Get the X position of your mouse
getMouseY()                   -> Get the Y position of yout mouse

```

## 🎧 WaxListener
The `WaxListener` class makes communication and GameLoop management of our application. It is simple but very effective in simple projects.
```java
toListener(WaxModel target) -> Link a "WaxModel" interface to the Listener
run(WaxWindow window)       -> Run GameLoop, executing "WaxModel" functions
                               and receiving the Window in which we want to run the Loop as a parameter
                                   
terminate(WaxWindow window) -> Terminate the Loop and its window, destroying it
```
Note: It is recommended to use `WaxListener` for simple situations, due to its functioning. It is not yet possible to enable Threads in the Listener and therefore constant use is not recommended.

## 📼 WaxThread
The `WaxThread` class is responsible for creating, activating and manipulating your Application Threads in an easy and fast way.
```java
enable(Runnable target)       -> Activate the current Thread and select the "run" function of the "Runnable" interface
stop()                        -> Stops and ends the current Thread
interval(int time)            -> Sleeps the Thread at a certain time

getName()                     -> Get the current Thread name
getID()                       -> Get current thread ID
```
Note: `WaxThread` is just a class to facilitate the use of threads

## 📜 WaxUtils
The `WaxUtils` class is a class with static functions that facilitate some processes for creating your project
```java
readFile(String path) -> Read a file
```

# 🌟 Graphics
## 🚨 Shader
The `Shader` class is quite simple, it just has its constructor as the main working of a Shader. The constructor takes 2 parameters, indicating the location of the VertexShader file and the second parameter indicates the location of the FragmentShader file.
<img src="https://learnopengl.com/img/getting-started/pipeline.png">
<br/>
**Functions**
```java
setColor(Vector3f color, int program, String location) -> Sets a color to some object that has a Shader
setMat4(Matrix4f mat, int program, String location)    -> Sets a 4-coordinate matrix to an object
                                                          that has a shader
```
NOTE: Deeper use of Shader is recommended for those already familiar with computer graphics or OpenGL.

## 🔦 Material
The `Material` class is responsible for creating and indicating some Material(Texture) to your object.
```java
setTexture(String path)     -> Creates a Texture with the parameter indicating the location of the Image
bindTexture()               -> Enable the texture
```
## 📐 Mesh
The `Mesh` class is responsible for the main way to create a geometric shape. It has the main and most important basic functions for creation.
```java
bind(int usage) -> Makes the creation and main setting for the geometric shape.
                       The parameter "usage" represents how we want our form to work,
                       it can be "STATIC" or "DYNAMIC"
                       
bindElementBufferObject(int[] indices, int usage)     -> Makes EBO creation (ElementBufferObject)
unbind()                                              -> Disable and clear memory for better optimization
delete()                                              -> Delete our entire creation
restore()                                             -> Restores our creations (used to restore and then render)
bindToDraw(int locals)                                -> Enable and assemble our data for rendering.
                                                         "locals" represents all of our assigned locations.
                                 
draw(int first, int count)                            -> Render our geometric shape.
                                                      "first" represents which Stride we will start in, 
                                                      and "count" the amount of vertices that we are going to render
                                 
draw(int length)                                      -> Render with Elements, in case we have EBO turned on
                                                         "length" is the number of indices we will render
 
setPointer(int local, int pos, int stride, int pointer) -> Sets and binds a pointer to the VAO
setShader(String vertex, String fragment)               -> You can create a Shader
setColor(Vector3f color)                                -> You can set a color
setTexture(String path, boolean enable)                 -> Set a texture
```
The way we work with Mesh is unique.
<br/>
All of our meshes will have a default Mesh Color, Texture and Location.
<br/>
It would not be mandatory to use texture or color, so you are free to name the default Uniforms as you wish.
```java
Color_Uniform       -> Represents the name given to the Uniform of your mesh color
Texture_Uniform     -> Represents the name given to the Uniform of your Mesh texture
Transform_Uniform   -> Represents the name given to the Uniform of your Mesh location

Texture_Location    -> Represents your texture location value
```
NOTE: It is recommended to use the `Mesh` class for those who already know about Computer Graphics or OpenGL.
<br/>
<br/>
Examples with Mesh:
```java

private Mesh mesh;

@Override
public void start(){

    float[] vertices = {
      -0.5f, -0.5f, 0.0f,
       0.5f, -0.5f, 0.0f,
       0.0f,  0.5f, 0.0f
     };
     
    mesh = new Mesh(vertices);
    mesh.setShader(Wax.readFile("my_vertex.glsl"), Wax.readFile("my_fragment.glsl"));
  
    mesh.Color_Uniform      = "_my_color_uniform";      // The "_my_color_uniform" is located on fragment shader
    mesh.Texture_Uniform    = "_my_texture_uniform";    // The "_my_texture_uniform" is located on fragment shader
    mesh.Transform_Uniform  = "_my_transform_uniform";  // The "_my_transform_uniform" is located on vertex shader
    mesh.Texture_Location   = 1;                        // The location "1" is located on vertex shader

    mesh.bind(Wax.STATIC);
    mesh.setPointer(0, 3, 3 * Float.BYTES, 0);
    mesh.unbind();
}

@Override
public void draw(){
    mesh.bindToDraw(1);
    mesh.draw(0, 3);
    mesh.restore(1);
}
```


# ➕ Wmath
## 🔢 Transform
The `Transform` class is responsible for controlling the positions, scales, rotations... of your Mesh.
```java
createTransform(Matrix4f transform)      -> Set a new Transform to the current transform
setPosition(Vector2f position)           -> Set a new position for your mesh
setScale(Vector2f scale)                 -> Set a new scale for your Mesh
setRotate(float angle, Vector3f axis)    -> Set a new rotation for your Mesh

getTransform()       -> Returns the variable that controls these transformations
getX()               -> Return the "X" position of your Mesh
getY()              -> Returns the "Y" position of your Mesh
getWidth()          -> Return your Mesh Width
getHeight()         -> Returns the height of your mesh
getAngle()          -> Return your Mesh Angle
getAxis()           -> Return the Axis of yout Mesh
```

## 🎓 Physics
The `Physics` class consists of having basic physics functions for your project
```java
colliding(Collider2d first, Collider2d second) -> Detects collision between a 2D Collider and another 2D Collider

colliding(Collider2d first, Collider2d second, float offsetX, float offsetY)
-> Detects collision between a 2D collider and another 2D collider with offsets in the X and Y position.
```

## ⏹️ Collider2D
The `Collider2D` class consists of a class that will have functions and references to a 2D Collider
```java
create()                                         -> Create collider mesh
debug(int mode, Vector3f color, boolean enabled) -> debug and render collider
destroy()                                        -> Does the collider destroy

setPosition(Vector2f position)                  -> Sets a position for the collider
setScale(Vector2f scale)                        -> Sets a scale for the collider

getX()                                          -> Get the X position of the collider
getY()                                          -> Get the Y position of the collider
getWidth()                                      -> Get the collider width
getHeight()                                     -> Get collider height
```



