/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs241project3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author reeder
 */
public class GamePanel extends JPanel{
    
    public final int GAME_WIDTH = 1300;
    public final int GAME_HEIGHT = 750;
    private ArrayList<Shot> shotList;
    private User user;
    private Line line;
    private ArrayList<RegEnemy> regEnemyList;
    private ArrayList<StrongEnemy> strongEnemyList;
    private int userMouseX;
    private int userMouseY;
    private boolean playerHasLost,firstDraw;
    private int count;
    private int points;
    private boolean userLost;
    
    
    public GamePanel(){
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        
        playerHasLost = false;
        points = 0;
        
        user = new User(GAME_WIDTH, GAME_HEIGHT);
        line = new Line();
        shotList = new ArrayList<Shot>();
        regEnemyList = new ArrayList<RegEnemy>();
        strongEnemyList = new ArrayList<StrongEnemy>();
        
        firstDraw = true;
    }
    

    /**
     * The update method updates the screen with enemies and shots.
     * PreCondition:  Need to update the screen.
     * PostCondition: Screen is updated
     * @param userMouseX the users x mouse position
     * @param userMouseY the users y mouse position
     * @param isShooting whether or not the shoot button is pressed
     * @param startGame  starts the game.
     */
    public void update(int userMouseX, int userMouseY, boolean isShooting, boolean startGame){
        this.userMouseX = userMouseX;
        this.userMouseY = userMouseY;
        if(count % 7 == 0 && isShooting)
            shotList.add(new Shot(user.getX(), user.getY(), GAME_WIDTH, GAME_HEIGHT, userMouseX, userMouseY));
        if(count % 40 == 0 && startGame)
            regEnemyList.add(new RegEnemy(user.getX(), user.getY(), GAME_WIDTH, GAME_HEIGHT));
        if(count % 200 == 0 && startGame){
            strongEnemyList.add(new StrongEnemy(user.getX(), user.getY(), GAME_WIDTH, GAME_HEIGHT));
        }
        repaint();
        
        count++;
    }
    /**
     * The paintComponent method paints components to the screen.
     * PreCondition:  Need components painted to the screen.
     * PostConditino: Components are painted to the screen.
     * @param g graphics used to draw the components.
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        // Loading a new font lags on the first drawString so I do a dummy
        // draw to load the new font at the begining.
        g.setFont(new Font("Book Antiqua", Font.PLAIN, 20));
        if(firstDraw){
            g.drawString("", 0, 0);
        }
        firstDraw = false;
        
        user.drawBase(g);
        line.draw(g, user.getX(), user.getY());
        for(int i = 0; i<shotList.size(); i++){
            shotList.get(i).move();
            shotList.get(i).draw(g);
            if(shotList.get(i).outOfBounds()){
                shotList.remove(i);
            }
        }
        user.drawCannon(g);
        
        for(int i = 0; i<regEnemyList.size(); i++){
            if(regEnemyList.get(i).checkForLoss()){
                userLost = true;
            }else{
                regEnemyList.get(i).move(user.getX(), user.getY());
                regEnemyList.get(i).draw(g);
            }
        }
        for(int i = 0; i<strongEnemyList.size(); i++){
            if(strongEnemyList.get(i).checkForLoss()){
                userLost = true;
            }else{
                strongEnemyList.get(i).move(user.getX(), user.getY());
                strongEnemyList.get(i).draw(g);
            }
        }        
        

        for(int i = 0; i<regEnemyList.size(); i++){
            for(int j=0; j<shotList.size()&& i<regEnemyList.size(); j++){
                if(regEnemyList.get(i).checkForDeath(shotList.get(j))){
                    g.drawString("+10", regEnemyList.get(i).getRegEnemyX(), regEnemyList.get(i).getRegEnemyY());
                    regEnemyList.remove(i);
                    shotList.remove(j);
                    points += 10;                    
                }                
            }
        }        
        
        for(int i = 0; i<strongEnemyList.size(); i++){
            for(int j=0; j<shotList.size()&& i<strongEnemyList.size(); j++){
                if(strongEnemyList.get(i).checkForDeath(shotList.get(j))){
                    g.setColor(Color.yellow);
                    g.drawString("-1", strongEnemyList.get(i).getRegEnemyX(), strongEnemyList.get(i).getRegEnemyY());
                    g.setColor(Color.red);
                    strongEnemyList.get(i).removeCounter();
                    if(strongEnemyList.get(i).getCounter() == 0){
                        g.drawString("+50", strongEnemyList.get(i).getRegEnemyX(), strongEnemyList.get(i).getRegEnemyY()-20);
                        strongEnemyList.remove(i);
                        points += 50;                       
                    }
                    shotList.remove(j);
                }                
            }
        }
        
        for(int i=0; i<regEnemyList.size(); i++){
            if(regEnemyList.get(i).checkForLoss()){
                playerHasLost = true;
            }
        }
        for(int i=0; i<strongEnemyList.size(); i++){
            if(strongEnemyList.get(i).checkForLoss()){
                playerHasLost = true;
            }
        }
    }
    /**
     * The getWidth method returns the width.
     * PreCondition:  Need the width.
     * PostCondition: Width is returned
     * @return width
     */
    @Override
    public int getWidth(){
        return GAME_WIDTH;
    }
    /**
     * The getHeight method returns the height.
     * PreCondition:  Need to get height.
     * PostCondition: Height is returned.
     * @return height
     */
    @Override
    public int getHeight(){
        return GAME_HEIGHT;
    }
    /**
     * The getUser method returns the user object.
     * PreCondition:  Need the user.
     * PostCondition: The user is returned
     * @return the user
     */
    public User getUser(){
        return user;
    }
    /**
     * The getLine method returns the line.
     * PreCondition:  Need the line.
     * PostCondition: The line is returned.
     * @return line
     */
    public Line getLine(){
        return line;
    }
    /**
     * The hasLost method tells whether or not the player lost.
     * PreCondition:  Need to know if the player lost.
     * PostCondition: Boolean is returned.
     * @return playerHasLost
     */
    public boolean hasLost(){
        return playerHasLost;
    }
    /**
     * The getScore method returns the score.
     * PreCondition:  Need the score.
     * PostCondition: The score is returned.
     * @return points
     */
    public int getScore(){
        return points;
    }
}
