package org.cyk.system.tramlop.server.business.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.business.api.PlaceBusiness;
import org.cyk.system.tramlop.server.persistence.api.PlacePersistence;
import org.cyk.system.tramlop.server.persistence.entities.Place;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

@ApplicationScoped
public class PlaceBusinessImpl extends AbstractBusinessEntityImpl<Place, PlacePersistence> implements PlaceBusiness,Serializable {
	private static final long serialVersionUID = 1L;
		
}
