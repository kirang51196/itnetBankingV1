package com.NetBanking.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	
	Properties prop;
	public ReadConfig() {
		File src = new File("./Configuration/config.properties");
		try {
			FileInputStream fins = new FileInputStream(src);
			prop = new Properties();
			prop.load(fins);
		}
			catch(Exception e) {
				System.out.println("Exception is"+ e.getMessage());
			}
		}
	
	
	public String getApplicationURL() {
		String url = prop.getProperty("baseURL");
	return url;
		}
	
	public String getUsername() {
		String username = prop.getProperty("username");
		return username;
		}
	
	public String getPassword() {
		String password = prop.getProperty("password");
		return password;
		}
	
	public String getChromePath() {
		String chropath = prop.getProperty("chropath");
		return chropath;
		}
	
	public String getIEPath() {
		String iepath = prop.getProperty("iepath");
		return iepath;
		}
	
	public String getFirePath() {
		String firepath = prop.getProperty("firepath");
		return firepath;
		}
	
	
	}




