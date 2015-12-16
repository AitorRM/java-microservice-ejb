package com.aezin.workshop.business.msejb.boundary;

import javax.ejb.Local;

@Local
public interface ServiceFacadeLocal {
	
	public void printMessage(String message);

	public void addMessageLog(String message);
	
	public String getMessageLog();
}
