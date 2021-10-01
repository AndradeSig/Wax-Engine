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
A classe `Mesh` é responsável pela principal forma de se criar uma forma geométrica. Ela tem as principais e mais importantes funções básicas para a criação.
```java
bind(int usage)     -> Faz a criação e a principal setagem para a forma geométrica.
                       O parametro "usage" representa qual a forma que queremos que a nossa forma funcione,
                       podendo ser "STATIC" ou "DYNAMIC"
                       
bindElementBufferObject(int[] indices, int usage)   -> Faz a criação da EBO (ElementBufferObject)
unbind()    -> Desabilita e limpa a memória para uma melhor otimização
delete()    -> Delete toda a nossa criação
restore()   -> Faz a restauração das nossas criações (utilizado para restauramos e renderizamos em seguida)
bindToDraw(int locals)        -> Habilita e monta os nossos dados para renderizamos.
                                 "locals" representa todos os nossos locais que atribuimos
                                 
draw(int first, int count)    -> Faz a renderização da nossa forma geométrica.
                                 "first" representa em qual Stride começaremos, e "count" a quantidade de 
                                 vértices que vamos renderizar
                                 
draw(int length)              -> Faz a renderização com Elementos, para caso nós tivermos a EBO ligada
                                 "length" é a quantidade de indices que renderizaremos
 
setPointer(int local, int pos, int stride, int pointer)   -> Serve para atribuir e ligar um ponteiro para a VAO
setShader(String vertex, String fragment)                 -> Serve para você criar uma Shader
setColor(Vector3f color)                                  -> Serve para você setar uma Cor
setTexture(String path, boolean enable)                   -> Serve para você setar uma textura
```
NOTE: Recomendado o uso da classe `Mesh` para quem já tem conhecimento com Computação Gráfica ou OpenGL.

