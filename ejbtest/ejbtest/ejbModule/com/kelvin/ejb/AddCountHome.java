package com.kelvin.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;

public interface AddCountHome {
	 public AddCount create() throws RemoteException,CreateException; 
}
