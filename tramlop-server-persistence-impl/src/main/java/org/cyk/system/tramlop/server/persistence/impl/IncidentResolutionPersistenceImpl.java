package org.cyk.system.tramlop.server.persistence.impl;
import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.persistence.api.IncidentResolutionPersistence;
import org.cyk.system.tramlop.server.persistence.entities.IncidentResolution;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

@ApplicationScoped
public class IncidentResolutionPersistenceImpl extends AbstractPersistenceEntityImpl<IncidentResolution> implements IncidentResolutionPersistence,Serializable {
	private static final long serialVersionUID = 1L;

}