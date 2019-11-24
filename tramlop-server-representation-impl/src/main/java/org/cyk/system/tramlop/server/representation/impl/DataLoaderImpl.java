package org.cyk.system.tramlop.server.representation.impl;

import java.io.Serializable;

import javax.ws.rs.core.Response;

import org.cyk.system.tramlop.server.business.impl.ApplicationScopeLifeCycleListener;
import org.cyk.utility.server.representation.AbstractDataLoaderImpl;

@org.cyk.system.tramlop.server.annotation.System
public class DataLoaderImpl extends AbstractDataLoaderImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Response load() {
		try {
			ApplicationScopeLifeCycleListener.createDataBase(2);
		} catch (Exception exception) {
			exception.printStackTrace();
			return Response.serverError().build();
		}
		return Response.ok().build();
	}
	
}