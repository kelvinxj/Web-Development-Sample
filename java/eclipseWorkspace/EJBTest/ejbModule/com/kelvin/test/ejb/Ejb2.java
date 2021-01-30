package com.kelvin.test.ejb;

import com.kelvin.test.ejb.view.Ejb2Local;
import com.kelvin.test.ejb.view.Ejb2Remote;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class Ejb2
 */
@Stateless
@Local(Ejb2Local.class)
@Remote(Ejb2Remote.class)
@LocalBean
public class Ejb2 implements Ejb2Remote, Ejb2Local {

    /**
     * Default constructor. 
     */
    public Ejb2() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public String sayHello() {
		return "Hello, EJB!";
	}

}
