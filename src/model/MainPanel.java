package model;

import javax.swing.*;

//Represents main screen before game starts
public class MainPanel extends JPanel { //implements ActionListener

//    JLabel snakeGame;
//    JLabel scoreBoard;
//    JTextArea scores;
//    JButton play;
//    GamePanel gp;
//    Boolean start;
//    private Date dateLogged = Calendar.getInstance().getTime();
//
//    public MainPanel() {
//        this.setPreferredSize(new Dimension(700, 700));
//        this.setBackground(new Color(59, 59, 59));
//        this.setFocusable(true);
//        this.setVisible(true);
//        play = new JButton("Play");
//        play.setAlignmentX(CENTER_ALIGNMENT);
//        this.setLayout(null);
//        play.setBounds(300, 550, 70, 40);
//        play.addActionListener(this);
//        play.setActionCommand("play");
//        this.add(play);
//        start = true;
//    }
//
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        draw(g);
//    }
//
//    public void draw(Graphics g) {
////        if (start) {
////            g.setColor(new Color(13, 133, 15));
////            g.setFont(new Font("Dialog", Font.BOLD, 45));
////            FontMetrics fontMetric2 = getFontMetrics(g.getFont());
////            g.drawString("Snakesss", (700 - fontMetric2.stringWidth("Snakesss")) / 2, g.getFont().getSize());
////
////            g.setColor(new Color(210, 6, 6));
////            g.setFont(new Font("Dialog", Font.BOLD, 25));
////            FontMetrics fontMetric = getFontMetrics(g.getFont());
////            g.drawString("Score Board: ", (700 - fontMetric.stringWidth("Score Board: ")) / 2, 100);
////
////            g.setColor(new Color(210, 6, 6));
////            g.setFont(new Font("Dialog", Font.BOLD, 18));
////            FontMetrics fontMetric3 = getFontMetrics(g.getFont());
////
////            gp = new GamePanel();
////            for (int i = 0; i < gp.getScores().size(); i++) {
////                g.drawString("\nScore: " + gp.getScores().get(i) + " " + dateLogged,
////                        (700 - fontMetric3.stringWidth("\nScore: " + gp.getScores().get(i) + " "
////                                + dateLogged)) / 2, 150);
////            }
//        }
//
//    public void newPanel() {
//        scores = new JTextArea();
//        //scores.setText(scoreBoard());
//        scores.setAlignmentX(CENTER_ALIGNMENT);
//        this.add(scores);
//        play = new JButton("play");
//        play.setAlignmentX(CENTER_ALIGNMENT);
//        this.add(play);
//        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
//        this.setLayout(boxLayout);
//    }
//
//    public void startGame() {
//
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        String action = e.getActionCommand();
//        switch (action) {
//            case "play" -> startGame();
//        }
    }

