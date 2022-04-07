package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropertiesFile {
	
	public Properties readProperties()
	{
		//creating the properties object and reading from the config.properties file in resources folder
		Properties prop=new Properties();
		InputStream rfile=null;
		try 
		{
			rfile=new FileInputStream("src//main//resources//config.properties");
			prop.load(rfile);
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return prop;
	}
}
