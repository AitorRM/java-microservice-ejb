package com.aezin.workshop.business.msejb;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.aezin.workshop.business.msejb.boundary.ServiceFacade;
import com.aezin.workshop.business.msejb.boundary.ServiceFacadeLocal;
import com.aezin.workshop.business.msejb.boundary.ServiceFacadeRemote;
import com.aezin.workshop.business.msejb.control.SingletonEjbService;
import com.aezin.workshop.business.msejb.control.StatelessEjbService;

@RunWith(Arquillian.class)
public class ServiceTest {

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class, "test.jar")
				.addClasses(
						SingletonEjbService.class,
						StatelessEjbService.class,
						ServiceFacadeLocal.class,
						ServiceFacadeRemote.class,
						ServiceFacade.class);
				// Add this to test CDI injection:
				// .addAsManifestResource(EmptyAsset.INSTANCE,"beans.xml");
	}
	
	@EJB
	ServiceFacadeRemote serviceFacadeRemote;
	
	@EJB
	ServiceFacadeLocal serviceFacadeLocal;
	
	private static final String messageTest = "Hello EJB microservice!";

	@Test
	@InSequence(1)
	public void test1() {
		Assert.assertNotNull(serviceFacadeRemote);
		serviceFacadeRemote.addMessageLog(messageTest);
		Assert.assertEquals(messageTest, serviceFacadeRemote.getMessageLog());
	}
	
	@Test
	@InSequence(2)
	public void test2() {
		Assert.assertNotNull(serviceFacadeLocal);
		Assert.assertEquals(messageTest, serviceFacadeLocal.getMessageLog());
	}

}
