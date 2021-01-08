package ejbTestClient;

import java.rmi.RemoteException;
import java.util.Hashtable;

import javax.ejb.CreateException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import com.kelvin.ejb.AddCount;
import com.kelvin.ejb.AddCountHome;

public class ejbTest {

	public static void main(String[] args) throws NamingException, RemoteException, CreateException {
		// TODO Auto-generated method stub
		String serverUrl = "corbaloc::127.0.0.1:2809";
		
		Hashtable<String, String> params = new Hashtable<>();
		params.put("java.naming.factory.initial", "com.ibm.websphere.naming.WsnInitialContextFactory");
		params.put("java.naming.provider.url", serverUrl);
		InitialContext initCtx = new InitialContext(params);
		long time1 = System.currentTimeMillis();
		Object object = initCtx.lookup("ejbtest");
//		Object object = initCtx.lookup( "com/dwl/base/requestHandler/beans/DWLServiceController");
		
//		AddCountHome remoteHome = (AddCountHome)PortableRemoteObject.narrow(object, AddCountHome.class);
//		AddCount ejb = remoteHome.create();
//		System.out.println(ejb.addCount(1, 2));
	}

}
