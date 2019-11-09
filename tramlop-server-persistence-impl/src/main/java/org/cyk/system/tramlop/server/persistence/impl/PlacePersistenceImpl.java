package org.cyk.system.tramlop.server.persistence.impl;
import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.persistence.api.PlacePersistence;
import org.cyk.system.tramlop.server.persistence.entities.Place;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

@ApplicationScoped
public class PlacePersistenceImpl extends AbstractPersistenceEntityImpl<Place> implements PlacePersistence,Serializable {
	private static final long serialVersionUID = 1L;

}