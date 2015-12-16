package com.aezin.workshop.business.msejb.boundary;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.aezin.workshop.business.msejb.control.SingletonEjbService;
import com.aezin.workshop.business.msejb.control.StatelessEjbService;

@Stateless
public class ServiceFacade implements ServiceFacadeRemote, ServiceFacadeLocal {

	@EJB
	SingletonEjbService singletonEjbService;
	
	@EJB
	StatelessEjbService statelessEjbService;
	
	
	@Override
	public void printMessage(String message) {
		statelessEjbService.printMessage(message);
	}

	@Override
	public void addMessageLog(String message) {
		singletonEjbService.addMessageLog(message);
	}

	@Override
	public String getMessageLog() {
		return singletonEjbService.getMessageLog();
	}

}
