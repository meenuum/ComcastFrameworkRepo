package com.comcast.crm.generic.fileutility;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.mysql.cj.xdevapi.JsonParser;

public class Jsonutility {
	
	public void getdatafromjsofile() throws FileNotFoundException {
		FileReader fr=new FileReader("./configAppData/appCommonData.json");
		JsonParser parser = new JsonParser();
		//Object obj = parser.parse(fr);
		
	}

}
