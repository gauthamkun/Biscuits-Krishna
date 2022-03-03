package com.biscuit.views;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BurnDownView {
    JFrame mainFrame = new JFrame();
    JPanel controller  = new JPanel();
    JPanel canvas = new JPanel();
    public BurnDownView(List<String> tasksList){
        System.out.println("Received Stories list: " + tasksList);
        controller.setLayout(new GridLayout(10,2));

        JMenuBar[] mb = new JMenuBar[tasksList.size()];
        for(int i = 0; i< tasksList.size(); i++){
           JLabel temp = new JLabel(tasksList.get(i));
           controller.add(temp);
           controller.repaint();
           mainFrame.repaint();
           mainFrame.pack();
           mainFrame.setVisible(true);
        }

        mainFrame.setLayout(new GridLayout(2,0));
        JLabel placeHolder = new JLabel("PlaceHolder for controller");
        JLabel canvasLabel = new JLabel("PlaceHolder for canvas");
        placeHolder.setFont(new Font("Serif", Font.PLAIN, 40));
        canvasLabel.setFont(new Font("Serif", Font.PLAIN, 40));
        controller.add(placeHolder);
        canvas.add(canvasLabel);
        controller.setBackground(Color.DARK_GRAY);
        canvas.setBackground(Color.YELLOW);
        mainFrame.add(controller);
        mainFrame.add(canvas);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
