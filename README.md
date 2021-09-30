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
Para começarmos, vamos criar nossa classe com o nome `Main` que será a classe principal do nosso aplicativo.
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
O implemento `WaxModel` é uma Interface que contém as principais funções para o funcionamento do Game.
```java
start();
update();
draw();
```
Note: É obrigatório implementar esta interface caso você for usar a `WaxListener`
#
A classe `WaxWindow` é a classe que terá o funcionamento da Janela.
```java
1º Argumento: Título da Janela
2º Argumento: Largura da Janela
3º Argumento: Altura da Janela
```
#
A classe `WaxListener` é a classe que fará a comunicação e facilitará o processo do GameLoop do nosso jogo/aplicativo.
<br/>
A função `toListener` indica a linkagem da interface `WaxModel` com os seus métodos ao Listener.
<br/>
A função `run` serve para rodar o GameLoop na janela atual.
<br/>
A função `terminate` irá finalizar e deletar a Janela quando fechada.
