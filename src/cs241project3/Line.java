/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs241project3;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author reeder
 */
public class Line {
    private int mouseX, mouseY;
    /**
     * The draw method draws the line on the screen.
     * PreCondition:  Need to draw the line.
     * PostCondtion: The line is drawn to the screen.
     * @param g Graphics for drawing.
     * @param userX user's x position.
     * @param userY user's y position.
     */
    public void draw(Graphics g, int userX, int userY){
        g.setColor(Color.RED);
        g.drawLine(userX+20, userY+20, mouseX, mouseY);
    }
    /**
     * The setMouseX method sets the mouse x.
     * PreCondition:  Need to set the mouse x.
     * PostCondition: The mouse x is set.
     * @param mouseX user's mouse x position.
     */
    public void setMouseX(int mouseX){
        this.mouseX = mouseX;
    }
    
    /**
     * The setMouseY method sets the mouse y.
     * PreCondition:  Need to set the mouse y.
     * PostCondition: The mouse y is set.
     * @param mouseX user's mouse y position.
     */
    public void setMouseY(int mouseY){
        this.mouseY = mouseY;
    }    
}
