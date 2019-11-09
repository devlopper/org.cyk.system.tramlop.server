package org.cyk.system.tramlop.server.persistence.impl;
import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.persistence.api.WeighingPersistence;
import org.cyk.system.tramlop.server.persistence.entities.Weighing;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

@ApplicationScoped
public class WeighingPersistenceImpl extends AbstractPersistenceEntityImpl<Weighing> implements WeighingPersistence,Serializable {
	private static final long serialVersionUID = 1L;

}