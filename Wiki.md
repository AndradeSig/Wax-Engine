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
A classe `WaxListener` faz a comunicação e o gerenciamento do GameLoop do nosso aplicativo. Ele é simples porém bem eficaz em projetos simples
```java
toListener(WaxModel target)     -> Faz a linkagem de uma interface "WaxModel" para o Listener
run(WaxWindow window)           -> Roda o GameLoop, executando as funções do "WaxModel" 
                                   e recebendo a Janela em que queremos rodar o Loop como parametro
                                   
terminate(WaxWindow window)     -> Finaliza o Loop e sua janela, destruindo-a
```
Note: É recomendado o uso do `WaxListener` para situações simples, devido ao funcionamento dele. Ainda não é possível ativar Threads no Listener e por isso não é recomendado o uso constante.
