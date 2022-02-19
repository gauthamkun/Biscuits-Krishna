/*
	Author: Hamad Al Marri;
 */

package com.biscuit;

//import java.util.Calendar;
//import java.util.GregorianCalendar;

import com.biscuit.models.Dashboard;
import com.biscuit.views.DashboardView;

public class App {

	public static void main(String[] args) {
		initialize();
	}


	private static void initialize() {

		Dashboard.setInstance(Dashboard.load());

		if (Dashboard.getInstance() == null) {
			Dashboard.setInstance(new Dashboard());
		}

		Dashboard.getInstance().save();

		DashboardView dbv = new DashboardView();
		dbv.view();

	}

}
