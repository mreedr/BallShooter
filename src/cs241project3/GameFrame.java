/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs241project3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;


/**
 *
 * @author reeder
 */
public final class GameFrame extends JFrame implements KeyListener, ActionListener{

    private GamePanel gamePanel;
    private JLabel scoreLabel;
    private JButton fireButton;
    private boolean aPressed;
    private boolean wPressed;
    private boolean dPressed;
    private boolean sPressed;
    private int userMouseX;
    private int userMouseY;
    private int ans = 0;
    private boolean startGame;
    private boolean isFiring;
    private String jOptionString;
    
    public GameFrame(){

        setTitle("Space-Balls");
        setExtendedState(Frame.MAXIMIZED_BOTH);  
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        gamePanel = new GamePanel();
        add(gamePanel);
        
        JPanel scorePanel = new JPanel();
        scorePanel.setPreferredSize(new Dimension(gamePanel.getWidth(), 50));
        scorePanel.setBackground(Color.BLACK);
        
        fireButton = new JButton("START GAME!");
        fireButton.addKeyListener(this);
        fireButton.addActionListener(this);
        scorePanel.add(fireButton);
        
        scoreLabel = new JLabel("Score : 0");
        scoreLabel.setForeground(Color.red);
        scorePanel.add(scoreLabel);
        add(scorePanel);
        
        run();
    }
    /**
     * The run method runs the main game loop.
     */
    public void run(){
        while(!gamePanel.hasLost() && gamePanel.getScore()<1000){
            moveUser();
            gamePanel.update(userMouseX, userMouseY, isFiring, startGame);
            scoreLabel.setText("Score: "+gamePanel.getScore());
            try{
                Thread.sleep(30);
            }catch(InterruptedException e){

            }
        }

        this.dispose();
        if(!gamePanel.hasLost()){
            jOptionString = "You Won! :)";
        }else{
            jOptionString = "You Lost :(";
        }
        ans = JOptionPane.showConfirmDialog(null, jOptionString+" \nPlay Again?", "Input", JOptionPane.YES_NO_OPTION); 
        if(ans==0){
            new InstructionFrame();
        }
        
    }
    /**
     * The moveUser method moves the user around the screen.
     */
    public void moveUser(){
        userMouseX = MouseInfo.getPointerInfo().getLocation().x - (getLocation().x+(this.getWidth() - gamePanel.getWidth())/2);
        userMouseY = MouseInfo.getPointerInfo().getLocation().y - (getLocation().y+30);
        
        gamePanel.getLine().setMouseX(userMouseX);
        gamePanel.getLine().setMouseY(userMouseY);
        
        if(aPressed && wPressed){
            gamePanel.getUser().moveUp();
            gamePanel.getUser().moveLeft();
        }
        else if(aPressed && sPressed){
            gamePanel.getUser().moveLeft();
            gamePanel.getUser().moveDown();
        }
        else if(dPressed && wPressed){
            gamePanel.getUser().moveUp();
            gamePanel.getUser().moveRight();
        }
        else if(dPressed && sPressed){
            gamePanel.getUser().moveRight();
            gamePanel.getUser().moveDown();
        }
        else if(aPressed){
            gamePanel.getUser().moveLeft();
        }
        else if(wPressed){
            gamePanel.getUser().moveUp();
        }
        else if(sPressed){
            gamePanel.getUser().moveDown();
        }
        else if(dPressed){
            gamePanel.getUser().moveRight();
        }        
    }
    @Override
    public void keyTyped(KeyEvent ke) {
    }
    /**
     * The keyPressed method listens for keys to be pressed.
     * @param ke 
     */
    @Override
    public void keyPressed(KeyEvent ke) {
            if(ke.getKeyChar() == 'a'){
                aPressed = true;
            }
            if(ke.getKeyChar() == 'w'){
                wPressed = true;
            }
            if(ke.getKeyChar() == 'd'){
                dPressed = true;
            }
            if(ke.getKeyChar() == 's'){
                sPressed = true;
            }
   }
    /**
     * The keyReleased method listens for keys to be released
     * @param ke 
     */
    @Override
    public void keyReleased(KeyEvent ke) {
           if(ke.getKeyChar() == 'a'){
                aPressed = false;
            }
            if(ke.getKeyChar() == 'w'){
                wPressed = false;
            }
            if(ke.getKeyChar() == 'd'){
                dPressed = false;
            }
            if(ke.getKeyChar() == 's'){
                sPressed = false;
            }
    }
    /**
     * The actionPerformed method fires shots
     * @param ae 
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(!isFiring){
            fireButton.setText("Stop Firing!");
        }else{
            fireButton.setText("Start Firing!");
        }
        isFiring = !isFiring;
        startGame = true;
    }

}
