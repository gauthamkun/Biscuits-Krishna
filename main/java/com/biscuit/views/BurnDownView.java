package com.biscuit.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BurnDownView implements ActionListener {
    private final List<UserStoryPlaceholder> storiesList;
    public int assignees = 0;
    JFrame mainFrame = new JFrame();
    JPanel controller = new JPanel();
    JPanel canvas = new JPanel();

    JPanel storyHolder1 = new JPanel();
    JPanel storyHolder2 = new JPanel();
    JPanel storyHolder3 = new JPanel();
    JPanel storyHolder4 = new JPanel();
    JPanel storyHolder5 = new JPanel();

    JLabel us1;
    JLabel us2;
    JLabel us3;
    JLabel us4;
    JLabel us5;

    JMenuBar[] us1MenuBar;
    JMenuBar[] us2MenuBar;
    JMenuBar[] us3MenuBar;
    JMenuBar[] us4MenuBar;
    JMenuBar[] us5MenuBar;

    JMenu[] us1menus;
    JMenu[] us2menus;
    JMenu[] us3menus;
    JMenu[] us4menus;
    JMenu[] us5menus;

    JMenuItem us1Assign;
    JMenuItem us1Progress;
    JMenuItem us1Test;
    JMenuItem us1Done;

    JMenuItem us2Assign;
    JMenuItem us2Progress;
    JMenuItem us2Test;
    JMenuItem us2Done;

    JMenuItem us3Assign;
    JMenuItem us3Progress;
    JMenuItem us3Test;
    JMenuItem us3Done;

    JMenuItem us4Assign;
    JMenuItem us4Progress;
    JMenuItem us4Test;
    JMenuItem us4Done;

    JMenuItem us5Assign;
    JMenuItem us5Progress;
    JMenuItem us5Test;
    JMenuItem us5Done;

    public BurnDownView(List<UserStoryPlaceholder> storiesList) {
        this.storiesList = storiesList;
        System.out.println("Received Stories list: " + storiesList);
        controller.setLayout(new GridLayout(6, 1));

        //Setting title for the Controller

        JLabel title = new JLabel("THE SCRUM BOARD WITH WISDOM", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.PLAIN, 40));
        title.setForeground(Color.WHITE);
        controller.add(title);
        //

        // setting up panels and things in the controller

        storyHolder1.setBackground(Color.LIGHT_GRAY);
        storyHolder2.setBackground(Color.DARK_GRAY);
        storyHolder3.setBackground(Color.WHITE);
        storyHolder4.setBackground(Color.GRAY);
        storyHolder5.setBackground(Color.DARK_GRAY);


        controller.add(storyHolder1);
        controller.add(storyHolder2);
        controller.add(storyHolder3);
        controller.add(storyHolder4);
        controller.add(storyHolder5);

        //over here

        //setting the user story placeholders here

        us1 = new JLabel(storiesList.get(0).getName());
        us2 = new JLabel(storiesList.get(1).getName());
        us3 = new JLabel(storiesList.get(2).getName());
        us4 = new JLabel(storiesList.get(3).getName());
        us5 = new JLabel(storiesList.get(4).getName());

        us1.setForeground(Color.BLACK);
        us2.setForeground(Color.WHITE);
        us3.setForeground(Color.BLACK);
        us4.setForeground(Color.WHITE);
        us5.setForeground(Color.WHITE);


        us1.setFont(new Font("Serif", Font.PLAIN, 20));
        us2.setFont(new Font("Serif", Font.PLAIN, 20));
        us3.setFont(new Font("Serif", Font.PLAIN, 20));
        us4.setFont(new Font("Serif", Font.PLAIN, 20));
        us5.setFont(new Font("Serif", Font.PLAIN, 20));


        storyHolder1.setLayout(new GridLayout(1, 5));
        storyHolder2.setLayout(new GridLayout(1, 5));
        storyHolder3.setLayout(new GridLayout(1, 5));
        storyHolder4.setLayout(new GridLayout(1, 5));
        storyHolder5.setLayout(new GridLayout(1, 5));

        storyHolder1.add(us1);
        storyHolder2.add(us2);
        storyHolder3.add(us3);
        storyHolder4.add(us4);
        storyHolder5.add(us5);
        //

        // displaying a user story's respective tasks on the scrum Board

        // us1
        us1MenuBar = new JMenuBar[storiesList.get(0).tasksList.size()];
        for (int i = 0; i < storiesList.get(0).tasksList.size(); i++) {
            us1MenuBar[i] = new JMenuBar();
        }

        us1menus = new JMenu[storiesList.get(0).tasksList.size()];
        for (int i = 0; i < storiesList.get(0).tasksList.size(); i++) {
            us1menus[i] = new JMenu(storiesList.get(0).tasksList.get(i));
        }
        for (int i = 0; i < us1MenuBar.length; i++) {
            us1MenuBar[i].add(us1menus[i]);
        }
        for (int i = 0; i < us1MenuBar.length; i++) {
            storyHolder1.add(us1MenuBar[i]);
        }

        for (int i = 0; i < us1menus.length; i++) {

            us1Assign = new JMenuItem("us1 " + i + " Assign");
            us1Progress = new JMenuItem("us1 " + i + " InProgress");
            us1Test = new JMenuItem("us1 " + i + " ReadyToTest");
            us1Done = new JMenuItem("us1 " + i + " Done");

            us1menus[i].add(us1Assign).addActionListener(this);
            us1menus[i].add(us1Progress).addActionListener(this);
            us1menus[i].add(us1Test).addActionListener(this);
            us1menus[i].add(us1Done).addActionListener(this);
        }

        //us2

        us2MenuBar = new JMenuBar[storiesList.get(1).tasksList.size()];
        for (int i = 0; i < storiesList.get(1).tasksList.size(); i++) {
            us2MenuBar[i] = new JMenuBar();
        }

        us2menus = new JMenu[storiesList.get(1).tasksList.size()];
        for (int i = 0; i < storiesList.get(1).tasksList.size(); i++) {
            us2menus[i] = new JMenu(storiesList.get(1).tasksList.get(i));
        }
        for (int i = 0; i < us2MenuBar.length; i++) {
            us2MenuBar[i].add(us2menus[i]);
        }
        for (int i = 0; i < us2MenuBar.length; i++) {
            storyHolder2.add(us2MenuBar[i]);
        }

        for (int i = 0; i < us2menus.length; i++) {
            us2Assign = new JMenuItem("us2 " + i + " Assign");
            us2Progress = new JMenuItem("us2 " + i + " InProgress");
            us2Test = new JMenuItem("us2 " + i + " ReadyToTest");
            us2Done = new JMenuItem("us2 " + i + " Done");

            us2menus[i].add(us2Assign).addActionListener(this);
            us2menus[i].add(us2Progress).addActionListener(this);
            us2menus[i].add(us2Test).addActionListener(this);
            us2menus[i].add(us2Done).addActionListener(this);
        }

        // us3

        us3MenuBar = new JMenuBar[storiesList.get(2).tasksList.size()];
        for (int i = 0; i < storiesList.get(2).tasksList.size(); i++) {
            us3MenuBar[i] = new JMenuBar();
        }

        us3menus = new JMenu[storiesList.get(2).tasksList.size()];
        for (int i = 0; i < storiesList.get(2).tasksList.size(); i++) {
            us3menus[i] = new JMenu(storiesList.get(2).tasksList.get(i));
        }
        for (int i = 0; i < us3MenuBar.length; i++) {
            us3MenuBar[i].add(us3menus[i]);
        }
        for (int i = 0; i < us3MenuBar.length; i++) {
            storyHolder3.add(us3MenuBar[i]);
        }

        for (int i = 0; i < us3menus.length; i++) {
            us3Assign = new JMenuItem("us3 " + i + " Assign");
            us3Progress = new JMenuItem("us3 " + i + " InProgress");
            us3Test = new JMenuItem("us3 " + i + " ReadyToTest");
            us3Done = new JMenuItem("us3 " + i + " Done");

            us3menus[i].add(us3Assign).addActionListener(this);
            us3menus[i].add(us3Progress).addActionListener(this);
            us3menus[i].add(us3Test).addActionListener(this);
            us3menus[i].add(us3Done).addActionListener(this);
        }

        // us4

        us4MenuBar = new JMenuBar[storiesList.get(3).tasksList.size()];
        for (int i = 0; i < storiesList.get(3).tasksList.size(); i++) {
            us4MenuBar[i] = new JMenuBar();
        }

        us4menus = new JMenu[storiesList.get(3).tasksList.size()];
        for (int i = 0; i < storiesList.get(3).tasksList.size(); i++) {
            us4menus[i] = new JMenu(storiesList.get(3).tasksList.get(i));
        }
        for (int i = 0; i < us4MenuBar.length; i++) {
            us4MenuBar[i].add(us4menus[i]);
        }
        for (int i = 0; i < us4MenuBar.length; i++) {
            storyHolder4.add(us4MenuBar[i]);
        }

        for (int i = 0; i < us4menus.length; i++) {
            us4Assign = new JMenuItem("us4 " + i + " Assign");
            us4Progress = new JMenuItem("us4 " + i + " InProgress");
            us4Test = new JMenuItem("us4 " + i + " ReadyToTest");
            us4Done = new JMenuItem("us4 " + i + " Done");

            us4menus[i].add(us4Assign).addActionListener(this);
            us4menus[i].add(us4Progress).addActionListener(this);
            us4menus[i].add(us4Test).addActionListener(this);
            us4menus[i].add(us4Done).addActionListener(this);
        }

        //us5

        us5MenuBar = new JMenuBar[storiesList.get(4).tasksList.size()];
        for (int i = 0; i < storiesList.get(4).tasksList.size(); i++) {
            us5MenuBar[i] = new JMenuBar();
        }

        us5menus = new JMenu[storiesList.get(4).tasksList.size()];
        for (int i = 0; i < storiesList.get(4).tasksList.size(); i++) {
            us5menus[i] = new JMenu(storiesList.get(4).tasksList.get(i));
        }
        for (int i = 0; i < us5MenuBar.length; i++) {
            us5MenuBar[i].add(us5menus[i]);
        }
        for (int i = 0; i < us5MenuBar.length; i++) {
            storyHolder5.add(us5MenuBar[i]);
        }

        for (int i = 0; i < us5menus.length; i++) {
            us5Assign = new JMenuItem("us5 " + i + " Assign");
            us5Progress = new JMenuItem("us5 " + i + " InProgress");
            us5Test = new JMenuItem("us5 " + i + " ReadyToTest");
            us5Done = new JMenuItem("us5 " + i + " Done");

            us5menus[i].add(us5Assign).addActionListener(this);
            us5menus[i].add(us5Progress).addActionListener(this);
            us5menus[i].add(us5Test).addActionListener(this);
            us5menus[i].add(us5Done).addActionListener(this);
        }
        //

        mainFrame.setLayout(new GridLayout(2, 0));
        controller.setBackground(Color.DARK_GRAY);
        canvas.setBackground(Color.YELLOW);
        canvas.setLayout(new GridLayout(2, 0));
        mainFrame.add(controller);
        mainFrame.add(canvas);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().split(" ")[2].equals("Assign")) {

            String name = JOptionPane.showInputDialog(mainFrame, "Who do you want it to be assigned? ");
            assign(name, e);

            if (e.getActionCommand().split(" ")[0].substring(0, 3).equals("us1")) {
                System.out.println(e.getActionCommand());
                int taskNum = Integer.parseInt(e.getActionCommand().split(" ")[1]);
                us1MenuBar[taskNum].setForeground(Color.BLUE);
                us1menus[taskNum].setForeground(Color.BLUE);
                storyHolder1.repaint();
                controller.repaint();
                mainFrame.repaint();
                mainFrame.setVisible(true);

            } else if (e.getActionCommand().split(" ")[0].substring(0, 3).equals("us2")) {
                System.out.println(e.getActionCommand());
                int taskNum = Integer.parseInt(e.getActionCommand().split(" ")[1]);
                us2MenuBar[taskNum].setForeground(Color.BLUE);
                us2menus[taskNum].setForeground(Color.BLUE);
                storyHolder2.repaint();
                controller.repaint();
                mainFrame.repaint();
                mainFrame.setVisible(true);

            } else if (e.getActionCommand().split(" ")[0].substring(0, 3).equals("us3")) {
                System.out.println(e.getActionCommand());
                int taskNum = Integer.parseInt(e.getActionCommand().split(" ")[1]);
                us3MenuBar[taskNum].setForeground(Color.BLUE);
                us3menus[taskNum].setForeground(Color.BLUE);
                storyHolder3.repaint();
                controller.repaint();
                mainFrame.repaint();
                mainFrame.setVisible(true);

            } else if (e.getActionCommand().split(" ")[0].substring(0, 3).equals("us4")) {
                System.out.println(e.getActionCommand());
                int taskNum = Integer.parseInt(e.getActionCommand().split(" ")[1]);
                us4MenuBar[taskNum].setForeground(Color.BLUE);
                us4menus[taskNum].setForeground(Color.BLUE);
                storyHolder4.repaint();
                controller.repaint();
                mainFrame.repaint();
                mainFrame.setVisible(true);

            } else if (e.getActionCommand().split(" ")[0].substring(0, 3).equals("us5")) {
                System.out.println(e.getActionCommand());
                int taskNum = Integer.parseInt(e.getActionCommand().split(" ")[1]);
                us5MenuBar[taskNum].setForeground(Color.BLUE);
                us5menus[taskNum].setForeground(Color.BLUE);
                storyHolder5.repaint();
                controller.repaint();
                mainFrame.repaint();
                mainFrame.setVisible(true);
            }

        }
    }

    private void assign(String name, ActionEvent e) {
        String story = e.getActionCommand().split(" ")[0].substring(0, 3);
        switch (story) {
            case "us1": {
                storiesList.get(0).assignee.add(name);
                break;
            }
            case "us2": {
                storiesList.get(1).assignee.add(name);
                break;
            }
            case "us3": {
                storiesList.get(2).assignee.add(name);
                break;
            }
            case "us4": {
                storiesList.get(3).assignee.add(name);
                break;
            }
            case "us5": {
                storiesList.get(4).assignee.add(name);
                break;
            }
        }
        System.out.println(storiesList);
        assignees++;
        determineDAndC(story);
        determinePush(story);
        determinePull(story);
    }

    private void determinePull(String story) {
        UserStoryPlaceholder current = null;
        if (assignees < 4) {

            for (UserStoryPlaceholder iterator : storiesList) {
                if (iterator.getName().equals(story)) {
                    current = iterator;
                    break;
                }
            }

            System.out.println("Story under consideration is " + current);

            if (!(current == null)) {
                if (current.assignee.size() == current.tasksList.size()) {
                    if (current.assignee.stream().distinct().count() == current.tasksList.size()) {
                        canvas.setBackground(Color.RED);
                        JLabel stop = new JLabel("Wait a Minute!!!", SwingConstants.CENTER);
                        stop.setFont(new Font("Serif", Font.BOLD, 60));
                        stop.setForeground(Color.WHITE);
                        JLabel dAndC = new JLabel(" You are following PULL Strategy. " +
                                "All the developers bombarding a single user story is more lean.", SwingConstants.CENTER);
                        dAndC.setFont(new Font("Serif", Font.BOLD, 25));
                        dAndC.setForeground(Color.WHITE);
                        canvas.add(stop);
                        canvas.add(dAndC);
                        canvas.repaint();
                        mainFrame.repaint();
                        mainFrame.setVisible(true);
                    }
                }
            }
        }

    }


    private void determinePush(String story) {
        int totalTasks = 14;
        int totalAssignees = 0;
        for (UserStoryPlaceholder iterator : storiesList) {
            totalAssignees += iterator.assignee.size();
        }
        if (totalAssignees == totalTasks) {
            canvas.setBackground(Color.RED);
            JLabel stop = new JLabel("STOP!!!", SwingConstants.CENTER);
            stop.setFont(new Font("Serif", Font.BOLD, 60));
            stop.setForeground(Color.WHITE);
            JLabel dAndC = new JLabel(" You are following PUSH Strategy. " +
                    "Do not assign the tasks upfront when they are in new state. It is Anti Agile and " +
                    "follows more of a plan driven approach.", SwingConstants.CENTER);
            dAndC.setFont(new Font("Serif", Font.BOLD, 20));
            dAndC.setForeground(Color.WHITE);
            canvas.add(stop);
            canvas.add(dAndC);
            canvas.repaint();
            mainFrame.repaint();
            mainFrame.setVisible(true);

        }
    }

    private void determineDAndC(String story) {
        UserStoryPlaceholder current = null;
        for (UserStoryPlaceholder iterator : storiesList) {
            if (iterator.getName().equals(story)) {
                current = iterator;
                break;
            }
        }
        if (!(current == null)) {
            if (current.assignee.stream()
                    .distinct()
                    .count() <= 1 && current.assignee.size() == current.tasksList.size()) {
                canvas.setBackground(Color.RED);
                JLabel stop = new JLabel("STOP!!!", SwingConstants.CENTER);
                stop.setFont(new Font("Serif", Font.BOLD, 60));
                stop.setForeground(Color.WHITE);
                JLabel dAndC = new JLabel(" You are following Divide and Conquer. " +
                        "Do not assign all the tasks in a user story to a single developer.", SwingConstants.CENTER);
                dAndC.setFont(new Font("Serif", Font.BOLD, 25));
                dAndC.setForeground(Color.WHITE);
                canvas.add(stop);
                canvas.add(dAndC);
                canvas.repaint();
                mainFrame.repaint();
                mainFrame.setVisible(true);
            }
        }
    }
}
