/*
	Author: Hamad Al Marri;
 */

package com.biscuit.views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.biscuit.ColorCodes;
import com.biscuit.commands.help.*;
import com.biscuit.factories.UniversalCompleterFactory;

import jline.console.ConsoleReader;
import jline.console.completer.AggregateCompleter;
import jline.console.completer.Completer;

import javax.swing.*;

public abstract class View implements ActionListener {

    public static JFrame mainFrame = new JFrame();
    public static JPanel panel = new JPanel();
    public static JPanel console = new JPanel();
    private static boolean flag = true;

    JButton go_to = new JButton("go_to Dashboard");
    JButton clear = new JButton("clear");
    JButton back1 = new JButton("back");
    JButton exit = new JButton("exit");
    JButton back = new JButton("add project");
    JButton go_to_pjct = new JButton("go_to project");
    JButton help = new JButton("help");
    JButton BacklogHelper = new JButton("BacklogHelp");
    JButton DashBoardHelper = new JButton("DashBoardHelp");
    JButton PlannerHelper = new JButton("PlannerHelp");
    JButton ProjectHelper = new JButton("ProjectHelp");
    JButton ReleaseHelper = new JButton("ReleaseHelp");

    static ConsoleReader reader;
    static List<String> promptViews;
    static List<Completer> universalCompleters;
    static Completer completer;
    String name;
    View previousView = null;
    boolean isViewed = false;
    static {
        promptViews = new ArrayList<String>();
        universalCompleters = new ArrayList<Completer>();
        completer = null;

        try {
            reader = new ConsoleReader();
            addUniversalCompleters();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public View(View previousView, String name) {
       // mainFrame.setBounds(1400,1400,1400,1400);
        this.previousView = previousView;
        this.name = name;
        if (flag) {
            console.setBackground(Color.LIGHT_GRAY);



            panel.add(go_to);
            panel.add(back);
            panel.add(clear);
            panel.add(back1);
            panel.add(exit);
            panel.add(go_to_pjct);
            panel.add(help);
            panel.add(BacklogHelper);
            panel.add(DashBoardHelper);
            panel.add(PlannerHelper);
            panel.add(ProjectHelper);
            panel.add(ReleaseHelper);

            BacklogHelper.setForeground(Color.ORANGE);
            DashBoardHelper.setForeground(Color.ORANGE);
            PlannerHelper.setForeground(Color.ORANGE);
            ProjectHelper.setForeground(Color.ORANGE);
            ReleaseHelper.setForeground(Color.ORANGE);

            panel.setBackground(Color.DARK_GRAY);
            go_to.addActionListener(this);
            back.addActionListener(this);
            clear.addActionListener(this);
            back1.addActionListener(this);
            exit.addActionListener(this);
            go_to_pjct.addActionListener(this);
            help.addActionListener(this);
            BacklogHelper.addActionListener(this);
            DashBoardHelper.addActionListener(this);
            ProjectHelper.addActionListener(this);
            PlannerHelper.addActionListener(this);
            ReleaseHelper.addActionListener(this);

            mainFrame.setLayout(new GridLayout(2, 1));
            mainFrame.setBounds(8, 800, 800, 800);
            mainFrame.add(panel);
            mainFrame.add(console);
            mainFrame.pack();
            mainFrame.setVisible(true);
            flag = false;
        }
    }
    public void view() {
        if (!isViewed) {
            addPromptViews();
            isViewed = true;
        }

        setPrompt();

        clearCompleters();

        //addCompleters();
    }

    protected void clearCompleters() {
        if (completer != null)
            reader.removeCompleter(completer);
    }

    private static void addUniversalCompleters() {
        universalCompleters.addAll(UniversalCompleterFactory.getUniversalCompleters());
        completer = new AggregateCompleter(universalCompleters);
        reader.addCompleter(completer);
    }

    protected void addCompleters() {
        List<Completer> completers = new ArrayList<Completer>();

        completers.addAll(universalCompleters);
        //addSpecificCompleters(completers);

        completer = new AggregateCompleter(completers);
        reader.addCompleter(completer);
    }

    abstract void addSpecificCompleters(List<Completer> completers);

    protected void resetCompleters() {
        clearCompleters();
        addCompleters();
    }

    private void read(String line) {
        line = line.trim();
        String words[] = line.split("\\s+");
        for (String iterator : words)
            console.removeAll();
        mainFrame.repaint();
        mainFrame.setVisible(true);
        try {
            if (!checkIfUnivesalCommand(words)) {
                executeCommand(words);
//                if (!executeCommand(words)) {
//
//                    JLabel l = new JLabel("Your command is invalid");
//                    console.add(l);
//                    mainFrame.repaint();
//                    mainFrame.setVisible(true);
//                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkIfUnivesalCommand(String[] words) throws IOException {

        if (words.length == 1) {
            if (words[0].equals("clear")) {
                console.add(new JLabel("Command selected: clear"));
                mainFrame.repaint();
                mainFrame.pack();
                mainFrame.setVisible(true);
                return true;
            } else if (words[0].equals("exit")) {
                console.add(new JLabel("Command selected: exit"));
                mainFrame.repaint();
                mainFrame.pack();
                mainFrame.setVisible(true);
                System.exit(0);
            }  else if (words[0].equals("BacklogHelp")) {
                return  new BacklogHelp().execute();
            }else if (words[0].equals("DashBoardHelp")) {
                return new DashboardHelp().execute();
            }else if (words[0].equals("PlannerHelp")) {
                return new PlannerHelp().execute();
            }else if (words[0].equals("ProjectHelp")) {
                return new ProjectHelp().execute();
            }else if (words[0].equals("ReleaseHelp")) {
               return new ReleaseHelp().execute();
            }
        } else if (words.length == 2) {
            if (words[0].equals("go_to") && words[1].equals("Dashboard")) {
                gotoDashboard();
                return true;
            }
        }

        return false;
    }

    private void gotoDashboard() throws IOException {
        if (this.name.equals("Dashboard")) {
            console.add(new JLabel("Command selected: dashboard. "));
            JLabel label = new JLabel(" You are Already in the DashBoard");
            console.add(label);
            console.repaint();
            console.setVisible(true);
            mainFrame.pack();
            mainFrame.repaint();
            mainFrame.setVisible(true);
        } else {
            promptViews.remove(name);
            View v = this.previousView;
            while (!v.name.equals("Dashboard")) {
                promptViews.remove(v.name);
                v = v.previousView;
            }
            v.view();
        }
    }

    abstract boolean executeCommand(String[] words) throws IOException;

    void addPromptViews() {
        promptViews.add(name);
    }

    void updatePromptViews() {
        promptViews.remove(promptViews.size() - 1);
        promptViews.add(name);
        setPrompt();
    }

    static void setPrompt() {
        StringBuilder prompt = new StringBuilder();

        for (int i = 0; i < promptViews.size(); i++) {
            String pv = promptViews.get(i);
            prompt.append(ColorCodes.PURPLE + pv);

            if (i < promptViews.size() - 1)
                prompt.append(ColorCodes.CYAN + ">");
        }

        prompt.append(ColorCodes.YELLOW + "~" + ColorCodes.GREEN + "$ " + ColorCodes.RESET);

        reader.setPrompt(prompt.toString());
    }

    public void close() {
        if (previousView != null) {
            promptViews.remove(name);
            previousView.view();
        }
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Action performed is called for " + e.getActionCommand() );
        if (e.getSource().getClass().toString().equals("class javax.swing.JButton"))
            read(e.getActionCommand());
    }

}

