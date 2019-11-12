package org.cyk.system.tramlop.server.persistence.impl;
import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.persistence.api.IncidentPersistence;
import org.cyk.system.tramlop.server.persistence.entities.Incident;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

@ApplicationScoped
public class IncidentPersistenceImpl extends AbstractPersistenceEntityImpl<Incident> implements IncidentPersistence,Serializable {
	private static final long serialVersionUID = 1L;

}