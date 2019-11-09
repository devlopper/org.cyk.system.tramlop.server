package org.cyk.system.tramlop.server.persistence.impl;
import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.persistence.api.PathPersistence;
import org.cyk.system.tramlop.server.persistence.entities.Path;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

@ApplicationScoped
public class PathPersistenceImpl extends AbstractPersistenceEntityImpl<Path> implements PathPersistence,Serializable {
	private static final long serialVersionUID = 1L;

}