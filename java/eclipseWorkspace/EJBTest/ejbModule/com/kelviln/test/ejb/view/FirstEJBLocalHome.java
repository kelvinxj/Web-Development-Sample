package com.kelviln.test.ejb.view;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

public interface FirstEJBLocalHome extends EJBLocalHome {

	public FirstEJBLocalComponent create() throws CreateException;
	
}
