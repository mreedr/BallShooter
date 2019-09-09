/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs241project3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author reeder
 */
public class InstructionPanel extends JPanel implements ActionListener{
    
    private boolean isComplete;
    private JButton compButton = new JButton("Play Game");
    private BufferedImage backgroundImage;
    
    public InstructionPanel() {
        setVisible(true);
        
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(1300, 750));        
        setLayout(new BorderLayout());
        setBackground(Color.red);
        isComplete = false;
        compButton.addActionListener(this);
        compButton.setBackground(Color.red);
        add(compButton, BorderLayout.SOUTH);
        
        try{
            backgroundImage = ImageIO.read(new File("instructionbackground.gif"));
        }catch(IOException ex){
            ex.printStackTrace();
        } 
    }
    /**
     * The actionPerformed method sets isComplete to true.
     * @param ae 
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        isComplete = true;
    }
    /**
     * The paintCompenent method paints components.
     * PreCondition:  Need to paint components.
     * PostCondition: Components are painted.
     * @param g Graphics for drawing
     */
    @Override
    public void paintComponent(Graphics g){
        g.drawImage(backgroundImage, 0, 0, null);
    }
    /**
     * The isComplete method returns if the instruction screen is complete.
     * @return 
     */
    public boolean isComplete(){
        return isComplete;
    }
}