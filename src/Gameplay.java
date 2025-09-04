import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Gameplay extends JPanel implements KeyListener, ActionListener {

    //Array para amazenar tamanho da cobra
    private int[] snakexlenght = new int[750];
    private int[] snakeylenght = new int[750];

    //Controle de direção da cobra
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;

    //Imagem da cobra
    private ImageIcon rightmouth;
    private ImageIcon upmouth;
    private ImageIcon downmouth;
    private ImageIcon leftmouth;

    //Comprimento atual da cobra
    private int lengthosnake = 3;

    //Velocidade da cobre
    private Timer timer;
    private int delay = 100;
    private ImageIcon snakeImage;

    //Cordenada onde inimigos possa parecer
    private int[] enemyxpos = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850};
    private int[] enemyypos = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625};
    private ImageIcon enemyImage;       //Imagem do inimigo
    private Random random = new Random();       //Gerando de número aleátorio
    private int xpos = random.nextInt(34);
    private int ypos = random.nextInt(23);

    //Pontuação atual do Jogador
    private int score = 0;
    private int moves = 0;

    private ImageIcon titleImage;

    //Ação do jogo contrutor
    public Gameplay() {
        addKeyListener(this);  //Ouvinte de teclado (detectar teclad de direção)
        setFocusable(true);      //Fazer janela do jogo ter foco no teclado
        setFocusTraversalKeysEnabled(false);    //Desativar tab do teclado não intefiram na jogabilidade
        timer = new Timer(delay, this);
        timer.start();
    }

    //Posição incial da cobra
    public void paint(Graphics g) {
        super.paintComponent(g);   //limpa fundo corretamente
        setBackground(Color.BLACK);

        if (moves == 0)    //Cordenada onde a cobra deve fica posição ao inicia o jogo
        {
            snakexlenght[0] = 100;
            snakexlenght[1] = 75;
            snakexlenght[2] = 50;

            snakeylenght[0] = 100;
            snakeylenght[1] = 100;
            snakeylenght[2] = 100;
        }

        //Desenha o titulo e cor da borda
        g.setColor(Color.WHITE);
        g.drawRect(24, 10, 851, 51);

        //Desenhar imagem do titulo
        titleImage = new ImageIcon("src/img/snaketitle.jpg");
        titleImage.paintIcon(this, g, 25, 11);

        //Desenhar borda para jogabilidade
        g.setColor(Color.WHITE);
        g.drawRect(24, 74, 850, 577);

        //Desenhar Pontuação
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Lenght: " + lengthosnake, 780, 50);
        g.drawString("Score: " + score, 780, 30);

        rightmouth = new ImageIcon("src/img/rightmouth.png");  //Carregar imagem da cabeça da cobra
        rightmouth.paintIcon(this, g, snakexlenght[0], snakeylenght[0]);

        //Faz carregamento da image da cobra em deversas posições
        for (int a = 0; a < lengthosnake; a++) {

            if (a == 0 && right) {
                rightmouth = new ImageIcon("src/img/rightmouth.png");
                rightmouth.paintIcon(this, g, snakexlenght[a], snakeylenght[a]);
            }

           else if (a == 0 && left) {
                leftmouth = new ImageIcon("src/img/leftmouth.png");
                leftmouth.paintIcon(this, g, snakexlenght[a], snakeylenght[a]);
            }

           else if (a == 0 && down) {
                downmouth = new ImageIcon("src/img/downmouth.png");
                downmouth.paintIcon(this, g, snakexlenght[a], snakeylenght[a]);
            }

           else if (a == 0 && up) {
                upmouth = new ImageIcon("src/img/upmouth.png");
                upmouth.paintIcon(this, g, snakexlenght[a], snakeylenght[a]);
            }

            else {
                snakeImage = new ImageIcon("src/img/snakeimage.png");
                snakeImage.paintIcon(this, g, snakexlenght[a], snakeylenght[a]);
            }
        }

        enemyImage = new ImageIcon("src/img/enemy.png");

        //Detecta quando a cobra come a comida
        if ((enemyxpos[xpos] == snakexlenght[0] && enemyypos[ypos] == snakeylenght[0]))
        {
            lengthosnake++;                 //Se verdadeiro a cobra aumenta um
            score++;                        //Incrementa pontuação
            xpos = random.nextInt(34);      // Garar posição da comida aleatoria
            ypos = random.nextInt(23);
        }

        //Gamer over e reiniciar o jogo
        enemyImage.paintIcon(this, g, enemyxpos[xpos], enemyypos[ypos]);

        for (int b = 1; b < lengthosnake; b++)
        {
            if (snakexlenght[b] == snakexlenght[0] && snakeylenght[b] == snakeylenght[0])
            {
                right = false;
                left = false;
                up = false;
                down = false;
                timer.stop();

                g.setColor(Color.WHITE);
                g.setFont(new Font("arial", Font.BOLD, 50));
                g.drawString("GAME OVER", 300, 300);

                g.setFont(new Font("arial", Font.BOLD, 20));
                g.drawString("Press Space to Restart", 350, 340);
            }
        }

        g.dispose();
    }

    //Progrma não fará nada quando o tecla não for pressionado
    @Override
    public void keyTyped(KeyEvent ke) {

    }

    //Verificar qual teclado foi pressionado
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            moves = 0;
            score = 0;
            lengthosnake = 3;
            right = true;
            left = false;
            up = false;
            down = false;
            timer.start();
            repaint();
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT && !left)
            {
                moves++;
                right = true;
                up = false;
                down = false;
            }

        if (e.getKeyCode() == KeyEvent.VK_LEFT && !right)
            {
                moves++;
                left = true;
                up = false;
                down = false;
            }

        if (e.getKeyCode() == KeyEvent.VK_UP && !down)
            {
                moves++;
                up = true;
                left = false;
                right = false;
            }

            if (e.getKeyCode() == KeyEvent.VK_DOWN && !up)
            {
              moves++;
              down = true;
              left = false;
              right = false;
            }

        }

    @Override
    public void keyReleased(KeyEvent keyEvent) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();

        //Mover corpo da cobra
        for (int r = lengthosnake - 1; r > 0; r--)
        {
            snakexlenght[r] = snakexlenght[r - 1];
            snakeylenght[r] = snakeylenght[r -1];
        }

        //Mover cabeça
        if (right) snakexlenght[0] += 25;
        if (left) snakexlenght[0] -= 25;
        if (up) snakeylenght[0] -= 25;
        if (down) snakeylenght[0] += 25;

        //Verificar colisão com borda na cabeça
        if (snakexlenght[0] > 850) snakexlenght[0] = 25;
        if (snakexlenght[0] < 28) snakexlenght[0] = 850;
        if (snakeylenght[0] > 625) snakeylenght[0] = 75;
        if (snakeylenght[0] < 75) snakeylenght[0] = 625;

        repaint();

            }
        }

