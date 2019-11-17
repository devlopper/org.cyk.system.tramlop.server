package org.cyk.system.tramlop.server.persistence.api;

import org.cyk.system.tramlop.server.persistence.entities.Task;
import org.cyk.utility.server.persistence.PersistenceEntity;

public interface TaskPersistence extends PersistenceEntity<Task> {

	Task readByOrderNumber(Integer orderNumber);
	
}
