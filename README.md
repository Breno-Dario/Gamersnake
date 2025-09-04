# 🐍 Snake Game em Java

Um jogo clássico da Cobrinha implementado em Java Swing para fins de estudo e diversão.
Feito para praticar conceitos de orientação a objetos, eventos de teclado e interface gráfica com Swing.

# 🎮 Funcionalidades

- Controle da cobra com as setas do teclado ⬅️⬆️➡️⬇️

- Sistema de pontuação e crescimento da cobra ao comer a comida

- Detecção de colisão com o próprio corpo → Game Over

- Reinício do jogo com a tecla Espaço

- Movimento contínuo e velocidade ajustável

# 🕹️ Controles

- Seta para cima (↑) → Mover para cima

- Seta para baixo (↓) → Mover para baixo

- Seta para direita (→) → Mover para direita

- Seta para esquerda (←) → Mover para esquerda

- Espaço → Reiniciar após Game Over

# 🚀 Como rodar

1. Clone o repositório:

```shell
git clone https://github.com/seu-usuario/snake-java.git
cd snake-java/src
```
2. Compile os arquivos:
```shell
javac Application.java Gameplay.java
```
3. Execute o jogo:
```shell
java Application
```
# Estrutura do projeto
```shell
snake-java/
│── src/
│   ├── Application.java   # Classe principal (abre a janela do jogo)
│   ├── Gameplay.java      # Lógica e interface do jogo
│   └── img/               # Imagens usadas no jogo
│       ├── snaketitle.jpg
│       ├── rightmouth.png
│       ├── leftmouth.png
│       ├── upmouth.png
│       ├── downmouth.png
│       ├── snakeimage.png
│       └── enemy.png
│── README.md
```

