package org.cyk.system.tramlop.server.representation.impl;

import java.io.Serializable;

import javax.ws.rs.core.Response;

import org.cyk.system.tramlop.server.business.impl.ApplicationScopeLifeCycleListener;
import org.cyk.utility.server.representation.impl.AbstractDataLoaderImpl;

@org.cyk.system.tramlop.server.annotation.System
public class DataLoaderImpl extends AbstractDataLoaderImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Response __execute__() throws Exception {
		ApplicationScopeLifeCycleListener.createDataBase();
		return Response.ok().build();
	}
	
}