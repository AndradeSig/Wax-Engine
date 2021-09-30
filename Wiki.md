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

