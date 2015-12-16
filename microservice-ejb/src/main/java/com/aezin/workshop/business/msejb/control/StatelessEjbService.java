package com.aezin.workshop.business.msejb.control;

import javax.ejb.Stateless;

@Stateless
public class StatelessEjbService {

	public void printMessage(String message) {
		System.out.println("Message: " + message);
	}
}
