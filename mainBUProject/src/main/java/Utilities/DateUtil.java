package Utilities;

import java.util.Date;

public class DateUtil {
	//getting the timestamp
	public static String getTimeStamp(){
		Date date = new Date();
		return date.toString().replaceAll(":", "_").replaceAll(" ", "_");
	}
}
