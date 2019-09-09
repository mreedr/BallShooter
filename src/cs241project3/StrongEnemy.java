/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs241project3;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author reeder
 */
public class StrongEnemy extends JPanel{
    final static int DIAMETER = 30;
    private int x, y, uXPostiton, uYPostiton, xBound, yBound, counter;
    private BufferedImage strongEnemy;
    
    public StrongEnemy(int uXPostiton, int uYPostiton, int panelWidth, int panelHeight) {
        Random r = new Random();
        int num = r.nextInt(4);
        
        xBound = panelWidth - DIAMETER;
        yBound = panelHeight - DIAMETER; 
        
        this.uXPostiton = uXPostiton;
        this.uYPostiton = uYPostiton;

        counter = 10;
        
        switch(num){
            case 0: this.y = 0;
                    this.x = 0;
                break;
            case 1: this.y = 0;
                    this.x = xBound - 1;
                break;
            case 2: this.y = yBound - 1;
                    this.x = 0;
                break;
            case 3: this.y = yBound - 1;
                    this.x = xBound - 1;
                break;
            default: System.out.println("this can't happen");
        }
        
        try{
            strongEnemy = ImageIO.read(new File("strongenemygif.gif"));
        }catch(IOException ex){
            ex.printStackTrace();
            
        }        
    }
    /**
     * The draw method draws the strong enemy.
     * PreCondition:  Need to draw the strongEnemy.
     * PostCondition: The strongEnemy is drawn.
     * @param g Graphics for drawing
     */
    public void draw(Graphics g){
        g.drawImage(strongEnemy, x, y, this);
    }
    /**
     * The move method moves the strongEnemy.
     * PreCondition:  Need to move the strongEnemy.
     * PostCondition: The strongEnemy is moved.
     * @param uXPos the users x position
     * @param uYPos the users y position
     */
    @Override
    public void move(int uXPos, int uYPos){
        uXPostiton = uXPos;      
        uYPostiton = uYPos;
        
        
        if(uXPostiton > x){
            x+=3;
        }else if(uXPostiton < x){
            x-=3;
        }
        
        if(uYPostiton > y){
            y+=3;
        }else if(uYPostiton < y){
            y-=3;
        }
        
    }
    /**
     * The outOfBounds method checks if the enemy is out of bounds.
     * PreCondition:  Need to check if enemy is out of bounds.
     * PostCondition: Enemy is checked.
     * @return boolean
     */
    public boolean outOfBounds(){
        if(x < 0){
            return true;
        }else if(y < 0){
            return true;
        }else if(x > xBound){
            return true;
        }else if(y > yBound){
            return true;
        }
        return false;
    }
    /**
     * The checkForLoss method checks if the users has lost.
     * PreCondition:  Need to know if the users lost.
     * PostCondition: Return if user lost.
     * @return boolean
     */
    public boolean checkForLoss(){
        Rectangle tempEnemy = new Rectangle(x,y, 70,70);
        Rectangle tempUser = new Rectangle(uXPostiton, uYPostiton, 40,40);
        if(tempEnemy.intersects(tempUser)){
            return true;
        }
        
        return false;
    }    
    /**
     * The getRegEnemyX method returns the x position.
     * PreCondition:  Need the x position.
     * PostCondition: The x position is returned.
     * @return x
     */
    public int getRegEnemyX(){
        return x;
    }
    /**
     * The getRegEnemyY method returns the y position.
     * PreCondition:  Need to the y position.
     * PostCondition: The y position is returned.
     * @return y
     */
    public int getRegEnemyY(){
        return y;
    }
    /**
     * The checkForDeath method checks if the enemy has died.
     * PreCondition: Need to know if the enemy has died.
     * PostCondition: Know if the enemy is dead.
     * @param s Shot to check against.
     * @return boolean
     */
    public boolean checkForDeath(Shot s){
        Rectangle tempEnemy = new Rectangle(x,y, 70,70);
        Rectangle tempUser = new Rectangle(s.getShotX(), s.getShotY(), 10,10);
        if(tempEnemy.intersects(tempUser)){
            return true;
        }
        
        return false;        
    }
    /**
     * Remove health from enemy.
     */
    public void removeCounter(){
        counter--;
    }
    /**
     * The getCounter method returns the counter.
     * PreCondition:  Need the counter.
     * PostCondition: Counter is returned.
     * @return counter
     */
    public int getCounter(){
        return counter;
    }
}
