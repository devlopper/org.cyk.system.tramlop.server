package org.cyk.system.tramlop.server.persistence.impl;
import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.persistence.api.DeliveryPersistence;
import org.cyk.system.tramlop.server.persistence.entities.Delivery;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

@ApplicationScoped
public class DeliveryPersistenceImpl extends AbstractPersistenceEntityImpl<Delivery> implements DeliveryPersistence,Serializable {
	private static final long serialVersionUID = 1L;

}