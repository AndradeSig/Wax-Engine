### 🎮 Simple application with WaxEngine 🎮
Aqui nós criaremos um projeto simples, que terá apenas um quadrado com uma textura rotacionando no centro da tela.
<br/>
Este simples projeto serve para você ver um pouco mais sobre o "Quad" e como funciona o Listener.
<br/>
<br/>
Para começarmos, vamos criar uma classe com o nome "SimpleApplication", que terá a inteface "WaxModel" implementada.
<br/>
A interface "WaxModel" entrega as principais funções para os nossos aplicativos/jogo.
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
Após isso, precisamos fazer algumas declarações:
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
`Quad` é a uma classe para criarmos um simples quadrado com algumas funções principais para um objeto.
<br/>
WaxWindow tem um construtor que requer 3 parametros. Sendo eles: `Title, Width and Height`
<br/>
`listener.toListener(this)` faz a linkagem da nossa `WaxModel` para o nosso Listener. `this` se refere a nossa classe principal pois faz o implemento da `WaxModel`
<br/>
<br/>
Após isso, faremos algumas setagens importante:
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
`window.setVsync(true)` habilita o vsync e trava os frames por segundo.
<br/>
`quad.create(Wax.STATIC)` cria a mesh do nosso quadrado com uma forma estática(STATIC). A variável que armazena o valor para Estático está na classe Wax, e por isso acessamos por ela.
<br/>
`quad.transform.scale(new Vector2f(0.4f, -0.4f))` seta uma escala para o nosso quadrado. O número `-0.4f` está negativo para nos dar um efeito de que o qudrado está virado para baixo.
<br/>
<br/>
Após isso, nós precisamos atualizar e renderizar as coisas.
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
`Wax.time.run()` inicia o tempo do nosso aplicativo para nós pegarmos o DeltaTime
<br/>
NOTE: Não precisa usar `Wax.color.BLUE`, vocẽ pode simplesmente usar `new Vector3f(0.0f, 0.0f, 1.0f)`
<br/>
<br/>
Faltou algumas coisas... Não fizemos nada para renderizarmos o nosso quadrado... Então faremos isso agora :)
```java
@Override
public void draw(){
    (...)
    quad.draw();
    (...)
}
```
`quad.draw` é uma função própria da nossa classe do Quadrado para renderizarmos de uma forma automática.
<br/>
<br/>
Agora vamos fazer o mais importante para executarmos o aplicativo... O método main:
```java
public static void main(String[] args){
    SimpleApplication simple = new SimpleApplication();
    
    simple.listener.run(simple.window);
    simple.quad.delete();
    simple.listener.terminate(simple.window);
}
```
`simple.listener.run()` irá rodar o game loop principal do nosso aplicativo. Como já foi dito na Wiki, esta função não é recomendado pra projetos maiores, por conta de não usar Threads, e isso pode ser um problema mais pra frente :(
<br/>
`simple.quad.delete()` irá deletar o nosso quadrado após o fim do game loop.
<br/>
`simple.listener.terminate()` irá destruir a janela e encerrar a WaxEngine após o fim do game loop
<br/>
<br/>
Bom, se você executou o aplicativo e deu tudo certo, então parabéns! O quadrado ainda não está rotacionando, porém já está sendo renderizado com uma textura. 
Para nós fazermos a rotação, é bem simples!:
```java
@Override
public void update(){
    (...)
    float angle_speed = 0.5f * (float)Wax.time.DELTA_TIME;
    quad.transform.rotate(angle_speed, new Vector3f(0.0f, 0.0f, 1.0f));
}
```
`Wax.time.DELTA_TIME` representa o valor entre o tempo atual e o tempo passado do nosso aplicativo. É muito bom e recomendado usar para valores que serão atualizados a cada frame e que terá que rodar de acordo com diferentes tipos de computadores. Como sabemos, os computadores não são iguais, e por isso alguns terão um jogo mais lento que o outro. Para não deixar o jogo mais lento que uma tartaruga na terra, multiplicamos o valor que será atualizado constantemente pelo valor em Delta.
<br/>
`quad.transform.rotate(angle_speed, new Vector3f(0.0f, 0.0f, 1.0f))` faz a rotação do nosso quadrado. Tendo o último parametro pedindo o eixo em que ele será rotacionado. Como estamos trabalhando com o 2D, apenas rotacionaremos no Eixo Z, mas seja livre para testar setando em qualquer eixo.
<br/>
<br/>
Após isso, quando você executar verá um lindo quadrado rotacionando :)
<br/>
The result: <br/> <img src="https://cdn.discordapp.com/attachments/837039667265142838/893557681237930004/unknown.png" width="50%">
<br/>
I know, it's a beautiful face :)
