# 🖥️ WaxWindow
A classe `WaxWindow` é responsável por criar, gerenciar e manipular a Janela do seu aplicativo. Ela possui muitas funções e veremos elas aqui
```java

initialize()           -> Faz a inicialização e criação da Janela
poll()                 -> Atualiza os eventos da Janela
clearColor(R, G, B)    -> Limpa a janela com uma cor determinado em RGB
swap()                 -> Faz a swap dos buffers da Janela para a renderização
destroy()              -> Faz a destruição da Janela

setVsync(true or false)       -> Habilita ou Desabilita a opção de Vsync
setClosed(true or false)      -> Habilita ou Desabilita o estado de fechado e aberto da Janela ( Não precisa colocar false )
setFullscreen(true or false)  -> Habilita ou Desabilita a opção de Tela Cheia da Janela

isOpen()            -> Verifica se a Janela está aberta
isFullscreen()      -> Verifica se a Janela está em tela cheia
getID()             -> Pega o ID da Janela, que é adicionado quando criado.

```
