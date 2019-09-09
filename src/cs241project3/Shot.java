/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs241project3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author reeder
 */
public class Shot extends JPanel{
    final static int DIAMETER = 10;
    private int x, y, xBound, yBound;
    private double rise, run, b, alpha, beta, r;
    private BufferedImage fireBallImage;
    
    public Shot(int uXPostiton, int uYPostiton, int panelWidth, int panelHeight, int mouseX, int mouseY){
        y = uYPostiton + 15;
        x = uXPostiton + 15;
        
        xBound = panelWidth - DIAMETER;
        yBound = panelHeight - DIAMETER;        
        
        rise = ((double)(uYPostiton - mouseY));
        run = ((double)(uXPostiton - mouseX));
        
        try{
            fireBallImage = ImageIO.read(new File("fireball.png"));
        }catch(IOException ex){
            
        }
    }
    

    /**
     * The draw method draws the shot.
     * PreCondition:  Need to draw a shot.
     * PostCondition: Shot is drawn.
     * @param g Graphics for drawing.
     */
    public void draw(Graphics g){
        //fs.paintComponent(g, (width/2 - DIAMETER/2)+x, (height/2 - DIAMETER/2)+y);
        g.drawImage(fireBallImage, x, y, this);
    }
    /**
     * The move method moves the shot.
     * PreCondition:  Need to move a shot.
     * PostCondition: Shot is moved.
     */
    public void move(){
        r = (rise*rise) + (run*run);
        alpha = Math.sqrt(5/r);
        
        y-= rise * alpha * 2;
        x-= run * alpha * 2;
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
     * The getShotX method returns the shot x.
     * PreCondition:  Need the x.
     * PostCondition: X is returned.
     * @return x
     */
    public int getShotX(){
        return x;
    }
    /**
     * The getShotY method returns the shot y.
     * PreCondition:  Need the y.
     * PostCondition: Y is returned.
     * @return y
     */
    
    public int getShotY(){
        return y;
    }

    
}
