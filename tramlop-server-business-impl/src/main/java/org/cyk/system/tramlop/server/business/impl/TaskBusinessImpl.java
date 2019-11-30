package org.cyk.system.tramlop.server.business.impl;

import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.business.api.TaskBusiness;
import org.cyk.system.tramlop.server.business.api.query.FindTaskByDeliveriesCodes;
import org.cyk.system.tramlop.server.persistence.api.TaskPersistence;
import org.cyk.system.tramlop.server.persistence.api.query.ReadTaskByDeliveriesCodes;
import org.cyk.system.tramlop.server.persistence.entities.Task;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;
import org.cyk.utility.server.business.BusinessFunctionCreator;

@ApplicationScoped
public class TaskBusinessImpl extends AbstractBusinessEntityImpl<Task, TaskPersistence> implements TaskBusiness,FindTaskByDeliveriesCodes,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Collection<Task> findByDeliveriesCodes(Collection<String> codes, Properties properties) {
		return ((ReadTaskByDeliveriesCodes)__persistence__).readByDeliveriesCodes(codes, properties);
	}
	
	@Override
	protected void __listenExecuteCreateBefore__(Task task, Properties properties, BusinessFunctionCreator function) {
		super.__listenExecuteCreateBefore__(task, properties, function);
		if(task.getStartable() == null)
			task.setStartable(Boolean.FALSE);
		if(task.getEndable() == null)
			task.setEndable(Boolean.FALSE);
	}
	
}
