package org.cyk.system.tramlop.server.persistence.impl;
import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.persistence.api.DeliveryTaskPersistence;
import org.cyk.system.tramlop.server.persistence.entities.DeliveryTask;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

@ApplicationScoped
public class DeliveryTaskPersistenceImpl extends AbstractPersistenceEntityImpl<DeliveryTask> implements DeliveryTaskPersistence,Serializable {
	private static final long serialVersionUID = 1L;

}