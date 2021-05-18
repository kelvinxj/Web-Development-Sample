package com.kelvin.kelvinTestProjectMaven.standardizePhoneTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IDMPhoneNumberUtil {

	public static String getPhoneNumberExtension(String phoneNumber) {
		String ExtensionString = "";
		String[] ExtStrArray = new String[]{"extension","extn","ext","ex","x","#"};
		for(int i = 0; i < ExtStrArray.length; i++){
			String ExtStr = ExtStrArray[i];
			ExtStr = ExtStr.toUpperCase();
			int SubStrIdx = ((phoneNumber.toUpperCase())).indexOf(ExtStr);
			// Below regular expression is to check extension string is not part of garbage string. like asdsextsd or dcsdxewee
			String regex = "(?<![a-zA-Z])"+ExtStr+"(?![a-zA-Z])";
		    Pattern pattern = Pattern.compile(regex);
		    Matcher matcher = pattern.matcher(phoneNumber.toUpperCase());
		    boolean check=matcher.find();
		    if(SubStrIdx > 0 && check){
		    	
		    	phoneNumber = (phoneNumber.toUpperCase()).replace(ExtStr, "Ext");
		    	ExtensionString = phoneNumber.substring(SubStrIdx);
		    	break;
		    }
		}
	    //System.out.println(PhoneNum);
	    return ExtensionString;
	}
}
