package com.general_Utilities;

import java.util.Date;

public class Java_Utility 
{

 public static String currentDate()
 {
	String time = new Date().toString();
	return time.replace(" " , "_".replace(":", "_"));
 }

}
