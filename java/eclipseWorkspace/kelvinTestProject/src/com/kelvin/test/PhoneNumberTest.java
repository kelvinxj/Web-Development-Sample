package com.kelvin.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

public class PhoneNumberTest {
	
	private PhoneNumberUtil phoneUtil;
	
	@Before
	public void getInstance(){
		phoneUtil = PhoneNumberUtil.getInstance();
	}
	
	@Test
	public void checkPhoneNumberGood() throws NumberParseException{
		assertEquals(true,  isValidNumber("+39 339 235001","IT"));
	}
	
	@Test
	public void checkPhoneNumberBad() throws NumberParseException{
		assertEquals(false,isValidNumber("+39 30 7723500","IT"));
	}
	
	@Test
	public void parsePhoneNumber() throws NumberParseException{
		String isoCode = "IT";
		String refNum = "075506781";
		PhoneNumber standardPhoneNum = phoneUtil.parse(refNum, isoCode);
		System.out.println(phoneUtil.isValidNumber(standardPhoneNum));		

		refNum = "75506781";
		standardPhoneNum = phoneUtil.parse(refNum, isoCode);
		System.out.println(phoneUtil.isValidNumber(standardPhoneNum));
		
		refNum = "+3975506781";
		standardPhoneNum = phoneUtil.parse(refNum, isoCode);
		System.out.println(phoneUtil.isValidNumber(standardPhoneNum));
	}
	

	
	@Test
	public void checkPhoneNumberGood1() throws NumberParseException{
		assertEquals(true,isValidNumber("+39 030 7723500","IT"));
	}
	
	public boolean isValidNumber(String phoneNumber, String countryCode) throws NumberParseException{
		PhoneNumber standardPhoneNum = phoneUtil.parse(phoneNumber, countryCode);
		return phoneUtil.isValidNumber(standardPhoneNum);		
	}

	public String getPhoneNumberCleasingCode(String phoneNumber, String countryCode) {
		PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
		
		PhoneNumber standardPhoneNum;
		try {
			standardPhoneNum = phoneUtil.parse(phoneNumber, countryCode);
			//If Incoming phone number is good,then we will set the xStandardRefNum with E164 format.
			if(phoneUtil.isPossibleNumber(standardPhoneNum) && phoneUtil.isValidNumber(standardPhoneNum)){
				return "Good";
				
			}
			else if(phoneUtil.isPossibleNumber(standardPhoneNum) && !phoneUtil.isValidNumber(standardPhoneNum)){
			//if the incoming phone number is possible number but not valid number then we will set the cleansing code as 100098
				//example : if the incoming phone is local number without area code,
				//then isPossibleNumber will be true,isValidNumber will be false
				return "100098";
			}
			else{
				// isPossibleNumber and isValidNumber both are false if incoming number is not in good format
				return "100097";
			}
		} catch (NumberParseException e) {
			return "100099";
		}
	}

}
