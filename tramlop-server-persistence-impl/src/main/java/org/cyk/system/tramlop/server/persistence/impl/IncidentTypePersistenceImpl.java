package org.cyk.system.tramlop.server.persistence.impl;
import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.persistence.api.IncidentTypePersistence;
import org.cyk.system.tramlop.server.persistence.entities.IncidentType;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

@ApplicationScoped
public class IncidentTypePersistenceImpl extends AbstractPersistenceEntityImpl<IncidentType> implements IncidentTypePersistence,Serializable {
	private static final long serialVersionUID = 1L;

}