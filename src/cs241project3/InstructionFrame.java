/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs241project3;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author reeder
 */
public class InstructionFrame extends JFrame{
    //Main class
    private InstructionPanel instructionPanel;
    
    public InstructionFrame() {
        setTitle("Space-Balls");
        setExtendedState(Frame.MAXIMIZED_BOTH);  
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        instructionPanel = new InstructionPanel();
        add(instructionPanel);
        
        while(true){
            if(instructionPanel.isComplete()==true){
                this.dispose();
                new GameFrame();
                break;
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }// end catch  
            instructionPanel.repaint();
        }
    }

    public static void main(String[] args){
        new InstructionFrame();
    }    
}
