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
### Creating a Window
To get started, we need to know how to create a Window. And for that, WaxEngine provides us with a special Class for that!
The `WaxWindow` is for you to create and manipulate the window freely, in a simple and easy to understand way.
To create our Window, we need to create a variable that will be our object:
```public static WaxWindow window;```
No need to leave static, I prefer it this way :)

After that, let's create and set some default Window properties in our main class constructor where the Window object will be stored:
```window = new WaxWindow("My first program with WaxEngine!", 800, 600);```


