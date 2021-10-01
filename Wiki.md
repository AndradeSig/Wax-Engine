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

setVsync(true or false)       -> Enable or Disable the Vsync option
setClosed(true or false)      -> Enables or Disables the Window closed and open state
setFullscreen(true or false)  -> Enable or Disable Window Full Screen option

isOpen()                      -> Check if the Window is open
isFullscreen()                -> Check if Window is full screen
getID()                       -> Gets the Window ID, which is added when created.

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
A forma em que nós trabalhamos com Mesh é algo único.
<br/>
Todas as nossas Meshs terão uma Cor, textura e localização padrão para as Meshs.
<br/>
Não seria obrigatório usar textura ou cor, e por isso você é livre em colocar os nomes que você quiser para as Uniforms padrões.
```java
Color_Location      -> Representa o nome que se da ao Uniform da cor da sua mesh
Texture_Location    -> Representa o nome que se da ao Uniform da textura da sua Mesh
Transform_Location  -> Representa o nome que se da ao Uniform da localização da sua Mesh

Texture_Index       -> Representa o valor da localizaç
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
