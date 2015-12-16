package com.aezin.workshop.business.msejb.control;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class SingletonEjbService {
	
	private StringBuffer messageLog;

	@PostConstruct
    protected void initialize() {
		// Metodo llamado despues de construir la instancia
		messageLog = new StringBuffer();
	}
	
	@PreDestroy 
    protected void freeResources() {
		// Metodo llamado antes de destruir la instancia
		messageLog = null;
	}
	
	@Lock(LockType.READ)
    public String getMessageLog() {
		// @Lock(LockType.READ) indica que el m�todo puede ser accedido en  
    	// modo lectura por m�ltiples hilos de ejecuci�n simultaneamente. 
    	// No se modifica el estado del componente y, por tanto, el acceso en paralelo es seguro.
    	return messageLog.toString();
    }
	
	@Lock(LockType.WRITE)
    public void addMessageLog(String message) {
		// @Lock(LockType.WRITE) indica que el m�todo modifica el estado del componente y necesita
		// una gesti�n de concurrencia. Es el bloqueo por defecto si no se especifica nada.
    	if (messageLog.length() > 0) {
    		messageLog.append("\n");
    	}
		messageLog.append(message);
    	
    }
}
