package com.aezin.workshop.business.msejb.client;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import com.aezin.workshop.business.msejb.boundary.ServiceFacadeRemote;

public class EjbRemoteClient {
	
	private static Logger LOGGER = Logger.getLogger(EjbRemoteClient.class.getName());
	
	public void testRemoteClient() {
		try {
			LOGGER.info("Start remote client test.");
			
			LOGGER.info("Init context...");
			Hashtable<Object, Object> jndiProps = new Hashtable<Object, Object>();
			jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
			jndiProps.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
			jndiProps.put("jboss.naming.client.ejb.context", true);
			Context context = new InitialContext(jndiProps);
			
			LOGGER.info("EJB service lookup...");
			String ejbUrl = "microservice-ejb/ServiceFacade!com.aezin.workshop.business.msejb.boundary.ServiceFacadeRemote";
			ServiceFacadeRemote service = (ServiceFacadeRemote) context.lookup(ejbUrl);

			service.printMessage("Hello World !!!!!!!!!!");
			LOGGER.info("Finish remote client test.");
			
		} catch (NamingException e) {
			LOGGER.error("Remote access error " + e.getMessage(), e);
		}
	}
	
	public static void main(String[] args) {
		new EjbRemoteClient().testRemoteClient();
	}
}