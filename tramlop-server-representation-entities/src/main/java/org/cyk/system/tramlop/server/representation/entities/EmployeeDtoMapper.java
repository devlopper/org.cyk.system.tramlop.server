package org.cyk.system.tramlop.server.representation.entities;

import org.cyk.system.tramlop.server.persistence.entities.Employee;
import org.cyk.utility.server.representation.AbstractMapperSourceDestinationImpl;
import org.mapstruct.Mapper;

@Mapper
public abstract class EmployeeDtoMapper extends AbstractMapperSourceDestinationImpl<EmployeeDto, Employee> {
	private static final long serialVersionUID = 1L;
    
 
}