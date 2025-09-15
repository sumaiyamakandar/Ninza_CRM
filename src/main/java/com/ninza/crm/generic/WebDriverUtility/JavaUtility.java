package com.ninza.crm.generic.WebDriverUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	public int getRandomNumber() {
		Random rd=new Random();
		int randomNumber=rd.nextInt(1000);
		return randomNumber;
	}
	public String getCurrentDate() {
		Date date =new Date();
		SimpleDateFormat sim =new SimpleDateFormat();
		String currentDate =sim.format(date);
		return currentDate;
		
	}
	public String getRequireDate(int day) {
		Date date =new Date();
		SimpleDateFormat  sim=new SimpleDateFormat("dd-MM-yyyy");
		sim.format(date);
		Calendar cal=sim.getCalendar();
		
		cal.add(cal.DAY_OF_MONTH,day);
		String requiredDate =sim.format(cal.getTime());
		return requiredDate;
	}


}
