package com.aezin.workshop.business.msejb.boundary;

import javax.ejb.Remote;

@Remote
public interface ServiceFacadeRemote {
	
	public void printMessage(String message);

	public void addMessageLog(String message);
	
	public String getMessageLog();
}
