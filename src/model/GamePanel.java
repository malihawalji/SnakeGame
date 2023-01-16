package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    static final int SCREENWIDTH = 700;
    static final int SCREENHEIGHT = 700;
    static final int UNIT = 25;
    static final int GAMEUNITS = (SCREENWIDTH * SCREENHEIGHT) / UNIT;
    static final int DELAY = 50;
    final int x[] = new int [GAMEUNITS]; //x coord
    final int y[] = new int [GAMEUNITS]; //y coord
    int bodyParts = 6;
    int applesEaten = 0;
    int appleXcoord;
    int appleYcoord;
    char direction = 'R';
    boolean running;
    Timer timer;
    Random random;
    boolean start;
    JButton play2;
    ArrayList<Integer> scores = new ArrayList<>();
    private Date dateLogged = Calendar.getInstance().getTime();
    JButton play;
    boolean over;
    ActionListener ac;

    public GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREENWIDTH, SCREENHEIGHT));
        this.setBackground(new Color(59, 59, 59));
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        playButton();
        //startGame();
    }

    public void playButton() {
        play = new JButton("Play");
        play.setAlignmentX(CENTER_ALIGNMENT);
        this.setLayout(null);
        play.setBounds(300, 550, 70, 40);
        ac = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String action = e.getActionCommand();
                switch (action) {
                    case "play" -> {
                        begin();
                    }
                }
            }
        };
        play.addActionListener(ac);
        play.setActionCommand("play");
        this.add(play);
        running = false;
        start = true;
    }

    public ArrayList<Integer> getScores() {
        return scores;
    }

    public void startGame() {
        newApple();
        running = true;
        timer = new Timer(DELAY, this); //how fast game is
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setUpMain(g);
    }

    public void setUpMain(Graphics g) {
        if (start) {
            g.setColor(new Color(13, 133, 15));
            g.setFont(new Font("Dialog", Font.BOLD, 45));
            FontMetrics fontMetric2 = getFontMetrics(g.getFont());
            g.drawString("Slitherin", (700 - fontMetric2.stringWidth("Slitherin")) / 2, g.getFont().getSize());

            g.setColor(new Color(210, 6, 6));
            g.setFont(new Font("Dialog", Font.BOLD, 25));
            FontMetrics fontMetric = getFontMetrics(g.getFont());
            g.drawString("Score Board: ", (700 - fontMetric.stringWidth("Score Board: ")) / 2, 100);

            g.setColor(new Color(210, 6, 6));
            g.setFont(new Font("Dialog", Font.BOLD, 18));
            FontMetrics fontMetric3 = getFontMetrics(g.getFont());

            for (int i = 0; i < getScores().size(); i++) {
                g.drawString("\nScore: " + getScores().get(i) + " " + dateLogged,
                        (700 - fontMetric3.stringWidth("\nScore: " + getScores().get(i) + " "
                                + dateLogged)) / 2, 150);
            }
        } else {
            draw(g);
        }
    }

    public void draw(Graphics g) {
        if(running) {
            for (int i = 0; i < (SCREENHEIGHT / UNIT); i++) { //make grid
                g.drawLine(i * UNIT, 0, i * UNIT, SCREENHEIGHT);
                g.drawLine(0, i * UNIT, SCREENWIDTH, i * UNIT);
            }
            g.setColor(new Color(206, 72, 72));
            g.fillOval(appleXcoord, appleYcoord, UNIT, UNIT);

            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(new Color(116, 189, 49));
                    g.fillRoundRect(x[i], y[i], UNIT, UNIT, 10, 10);
                } else {
                    g.setColor(new Color(46, 84, 22));
                    g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
                    g.fillRoundRect(x[i], y[i], UNIT, UNIT, 10, 10);
                }

            }

            g.setColor(new Color(210, 6, 6));
            g.setFont(new Font("Dialog", Font.BOLD, 30));
            FontMetrics fontMetric = getFontMetrics(g.getFont());
            g.drawString("Score: " + applesEaten, (SCREENWIDTH - fontMetric.stringWidth("Score: "
                    + applesEaten))/2, g.getFont().getSize());
        } else {
            over = true;
            gameOver(g);
        }
    }

    public void newApple() {
        appleXcoord = random.nextInt(((int)SCREENWIDTH/UNIT)) * UNIT;
        appleYcoord = random.nextInt(((int)SCREENHEIGHT/UNIT)) * UNIT;
    }

    public void move() {
        for(int i = bodyParts; i>0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        switch (direction) {
            case 'U' -> y[0] = y[0] - UNIT;
            case 'D' -> y[0] = y[0] + UNIT;
            case 'L' -> x[0] = x[0] - UNIT;
            case 'R' -> x[0] = x[0] + UNIT;
        }
    }

    public void checkApple() {
        if((x[0] == appleXcoord) && y[0] == appleYcoord) {
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }

    public void checkCollisions() {
        for(int i = bodyParts; i>0; i--) { //head collides body?
            if ((x[0]) == x[i] &&(y[0] == y[i])) {
                running = false; //game over
            }
        }

        if(x[0] < 0) { //left
            running = false;
        }
        if(x[0] > SCREENWIDTH) { //right
            running = false;
        }
        if(y[0] < 0) { // top
            running = false;
        }
        if(y[0] > SCREENHEIGHT) { //bottom
            running = false;
        }

        if(!running) {
            timer.stop();
        }
    }


//    public void playAgain() {
//        play2 = new JButton("Play Again");
//        play2.setAlignmentX(CENTER_ALIGNMENT);
//        this.setLayout(null);
//        play2.setBounds(300, 550, 85, 40);
//        ActionListener ac2 = new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String action = e.getActionCommand();
//                switch (action) {
//                    case "playA" -> {
//                        play2.setVisible(false);
//                        begin();
//                    }
//                }
//            }
//        };
//        play2.addActionListener(ac2);
//        play2.setActionCommand("playA");
//        this.add(play2);
//    }

    public void gameOver(Graphics g) {
            //secondPlay();
            g.setColor(new Color(249, 248, 250));
            g.setFont(new Font("Ink Free", Font.BOLD, 65));
            FontMetrics fontMetric = getFontMetrics(g.getFont());
            g.drawString("Game Over", (SCREENWIDTH -
                    fontMetric.stringWidth("Game Over")) / 2, SCREENHEIGHT / 2);

            g.setColor(new Color(210, 6, 6));
            g.setFont(new Font("Dialog", Font.BOLD, 30));
            FontMetrics fontMetric2 = getFontMetrics(g.getFont());
            g.drawString("Score: " + applesEaten, (SCREENWIDTH - fontMetric2.stringWidth("Score: "
                    + applesEaten)) / 2, g.getFont().getSize());
            scores.add(applesEaten);
//        try {
//            TimeUnit.SECONDS.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        this.setVisible(false);
            //GameFrame.newGamePanel();
//        JButton playAgain = new JButton("Play Again");
//        playAgain.addActionListener(this);
//        playAgain.setActionCommand("play");
//        this.add(playAgain);
//        playAgain.setVisible(true);
    }


    public void begin() {
        start = false;
        running = true;
        play.setVisible(false);
        startGame();
    }

    public void beginAgain(Graphics g) {
        start = true;
        running = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if(direction != 'R') {
                        direction = 'L';
                    }
                break;
                case KeyEvent.VK_RIGHT:
                    if(direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }
        }
    }
}
