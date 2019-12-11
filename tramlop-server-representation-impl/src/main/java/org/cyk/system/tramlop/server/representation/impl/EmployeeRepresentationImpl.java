package org.cyk.system.tramlop.server.representation.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.representation.api.EmployeeRepresentation;
import org.cyk.system.tramlop.server.representation.entities.EmployeeDto;
import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

@ApplicationScoped
public class EmployeeRepresentationImpl extends AbstractRepresentationEntityImpl<EmployeeDto> implements EmployeeRepresentation,Serializable {
	private static final long serialVersionUID = 1L;
	
}
