package com.kelviln.test.ejb.view;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface FirstEJBRemoteHome extends EJBHome {

	public FirstEJBRemoteComponent create() throws CreateException, RemoteException;
	
}
