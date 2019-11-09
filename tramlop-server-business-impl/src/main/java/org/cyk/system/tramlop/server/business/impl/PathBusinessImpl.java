package org.cyk.system.tramlop.server.business.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.business.api.PathBusiness;
import org.cyk.system.tramlop.server.persistence.api.PathPersistence;
import org.cyk.system.tramlop.server.persistence.entities.Path;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

@ApplicationScoped
public class PathBusinessImpl extends AbstractBusinessEntityImpl<Path, PathPersistence> implements PathBusiness,Serializable {
	private static final long serialVersionUID = 1L;
		
}
