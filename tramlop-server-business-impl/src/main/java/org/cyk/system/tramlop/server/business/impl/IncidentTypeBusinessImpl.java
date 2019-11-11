package org.cyk.system.tramlop.server.business.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.business.api.IncidentTypeBusiness;
import org.cyk.system.tramlop.server.persistence.api.IncidentTypePersistence;
import org.cyk.system.tramlop.server.persistence.entities.IncidentType;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

@ApplicationScoped
public class IncidentTypeBusinessImpl extends AbstractBusinessEntityImpl<IncidentType, IncidentTypePersistence> implements IncidentTypeBusiness,Serializable {
	private static final long serialVersionUID = 1L;
		
}
