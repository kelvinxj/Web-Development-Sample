package com.kelviln.test.ejb;

import com.kelviln.test.ejb.view.FirstEJBLocal;
import com.kelviln.test.ejb.view.FirstEJBLocalHome;
import com.kelviln.test.ejb.view.FirstEJBRemote;
import com.kelviln.test.ejb.view.FirstEJBRemoteHome;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.LocalHome;
import javax.ejb.Remote;
import javax.ejb.RemoteHome;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class FirstEJB
 */
@Stateless
@Local(FirstEJBLocal.class)
@Remote(FirstEJBRemote.class)
@LocalBean
@LocalHome(FirstEJBLocalHome.class)
@RemoteHome(FirstEJBRemoteHome.class)
public class FirstEJB implements FirstEJBRemote, FirstEJBLocal {

    /**
     * Default constructor. 
     */
    public FirstEJB() {
        // TODO Auto-generated constructor stub
    }

}
