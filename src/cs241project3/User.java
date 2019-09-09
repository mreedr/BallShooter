/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs241project3;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author reeder
 */
public class User extends JPanel{
    private static final int speed = 20;
    private final int DIAMETER = 40;
    private BufferedImage userBaseImage;
    private BufferedImage userCannonImage;
    private int x, y, xBound, yBound;
    
    
    public User(int panelWidth, int panelHeight) {
        x = panelWidth/2;
        y = panelHeight/2;
        
        xBound = panelWidth - DIAMETER;
        yBound = panelHeight - DIAMETER;
        
        try{
            userBaseImage = ImageIO.read(new File("mainshipbottom.png"));
            userCannonImage = ImageIO.read(new File("usercannon.png"));
        }catch(IOException e){
        }
    }
    /**
     * The drawBase method draws the base of the user.
     * PreCondition: Need to draw base.
     * PostCondition: Base is drawn. 
     * @param g graphics for drawing
     */
    public void drawBase(Graphics g){
        g.drawImage(userBaseImage, x, y, null);
    }
    /**
     * The drawCannon method draws the top of the user.
     * PreCondition:  Need to draw the user.
     * PostCondition: The user is drawn.
     * @param g Graphics for drawing.
     */
    public void drawCannon(Graphics g){
        g.drawImage(userCannonImage, x, y, null);
    }
    /**
     * The moveUp method moves user up.
     */
    public void moveUp(){
        if(y <= 0 || y-speed <=0)
            y=0;
        else
            y-=speed;
    }
    /**
     * The moveDown method moves the user down.
     */
    public void moveDown(){
        if(y>= yBound || y+speed >= yBound)
            y=yBound;
        else
            y+=speed;
    }
    /**
     * The moveRight method moves the user right.
     */
    public void moveRight(){
        if(x >= xBound || x+speed >= xBound)
            x=xBound;
        else
            x+=speed;
    }
    /**
     * The moveLeft method moves the user left.
     */
    public void moveLeft(){
        if(x <= 0 || x-speed <= 0)
            x=0;
        else
            x-=speed;
    }
    /**
     * The getX method returns the users x.
     * PreCondition:  Need the users x.
     * PostCondition: The users x is returned
     * @return x
     */
    @Override
    public int getX(){
        return x;
    }
    /**
     * The getY method returns the users y.
     * PreCondition:  Need the users y.
     * PostCondition: The users y is returned.
     * @return y
     */
    @Override
    public int getY(){
        return y;
    }
    
    
}
