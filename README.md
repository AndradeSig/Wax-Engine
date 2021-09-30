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
### First application with WaxEngine
Para começarmos, vamos criar nossa classe com o nome `Main` que será a classe principal do nosso jogo.
<br/>
```java
public class Main implements WaxModel{
	
	public static WaxWindow window;
	public static WaxListener listener;

	public Main(){
	    window = new WaxWindow("My first app with WaxEngine!", 800, 600);
	    listener = new WaxListener();

	    listener.toListener(this);
	}

	@Override
	public void start(){
	    window.initialize();
	}

	@Override
	public void update(){
	    window.poll();
	} 
	
	@Override
	public void draw(){
	    window.clearColor(1.0f, 0.0f, 0.0f);
	    window.swap();
	}
	
	public static void main(String[] args){
	    new Main();
	    listener.run(window);
	    listener.terminate(window);
	}
}
```


