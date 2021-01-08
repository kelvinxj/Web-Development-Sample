package com.kelvin.ejb;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public class AddCountBean implements SessionBean  
{  
        public void ejbCreate()  
        {  
        }  
        public void setSessionContext(SessionContext ctx)  
                       throws EJBException,  
                              java.rmi.RemoteException  
        {  
        }  
        public void ejbRemove()  
               throws EJBException,  
                      java.rmi.RemoteException  
        {  
        }  
 
        public void ejbActivate()  
                 throws EJBException,  
                        java.rmi.RemoteException  
        {  
        }  
        public void ejbPassivate()  
                  throws EJBException,  
                         java.rmi.RemoteException  
        {  
        }  
        public int addCount(int a,int b)  
        {  
                return a+b;  
        }  
} 
