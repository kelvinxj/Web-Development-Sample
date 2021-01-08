package com.kelvin.ejb;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

public interface AddCount extends EJBObject  
{  
        public int addCount(int a,int b) throws RemoteException;  
}  
