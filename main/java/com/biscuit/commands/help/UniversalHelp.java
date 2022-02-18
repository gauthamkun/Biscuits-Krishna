package com.biscuit.commands.help;

import java.awt.*;
import java.io.IOException;

import com.biscuit.commands.Command;

import com.biscuit.views.View;
import de.vandermeer.asciitable.v2.RenderedTable;
import de.vandermeer.asciitable.v2.V2_AsciiTable;
import de.vandermeer.asciitable.v2.render.V2_AsciiTableRenderer;
import de.vandermeer.asciitable.v2.render.WidthLongestLine;
import de.vandermeer.asciitable.v2.themes.V2_E_TableThemes;

import javax.swing.*;

public class UniversalHelp implements Command {

	@Override
	public boolean execute() throws IOException {

		V2_AsciiTable at = new V2_AsciiTable();

		executeChild(at);
	//	View.console.setLayout(new GridLayout(20,2));

		JLabel label1= new JLabel("                                                 Dashboard Commands                                                  \n" );
		JLabel label2= new JLabel("             go_to             Go to a project and open the project view (followed by a project name)                           \n" );
		JLabel label3= new JLabel("             go_to project     Similar to 'go_to', it goes to a project and open the project view (followed by a project name)  \n" );
		JLabel label4= new JLabel("             add project       Create a new project                                                                             \n" );
		JLabel label5= new JLabel("             edit project      Edit project (followed by a project name)                                                        \n" );
		JLabel label6= new JLabel("             remove project    Remove or delete project (followed by a project name)                                            \n" );
		JLabel label7= new JLabel("                                                   Other Commands                                                    \n" );
		JLabel label8= new JLabel("             clear             Clear the terminal screen                                                                        \n" );
		JLabel label9= new JLabel("             exit              Exit/terminate the program                                                                       \n" );
		JLabel label10= new JLabel("            dashboard         Go to dashboard                                                                                  \n" );
		JLabel label11= new JLabel("            go_to dashboard   Go to dashboard                                                                                  \n" );
		JLabel label12= new JLabel("            help              Show help                                                                                        \n" );

		View.console.add(label1);
		View.console.add(label2);
		View.console.add(label3);
		View.console.add(label4);
		View.console.add(label5);
		View.console.add(label6);
		View.console.add(label7);
		View.console.add(label8);
		View.console.add(label9);
		View.console.add(label10);
		View.console.add(label11);
		View.console.add(label12);

		View.console.repaint();
		View.mainFrame.repaint();
		View.mainFrame.setVisible(true);

		return true;
	}


	protected void executeChild(V2_AsciiTable at) {
	}

}
