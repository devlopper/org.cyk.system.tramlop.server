package org.cyk.system.tramlop.server.business.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.business.api.TaskBusiness;
import org.cyk.system.tramlop.server.persistence.api.TaskPersistence;
import org.cyk.system.tramlop.server.persistence.entities.Task;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

@ApplicationScoped
public class TaskBusinessImpl extends AbstractBusinessEntityImpl<Task, TaskPersistence> implements TaskBusiness,Serializable {
	private static final long serialVersionUID = 1L;
		
}
