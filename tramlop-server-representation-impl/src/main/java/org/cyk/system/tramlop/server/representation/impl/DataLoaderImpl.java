package org.cyk.system.tramlop.server.representation.impl;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.core.Response;

import org.cyk.system.tramlop.server.business.api.ProductBusiness;
import org.cyk.system.tramlop.server.persistence.entities.Product;
import org.cyk.utility.server.representation.impl.AbstractDataLoaderImpl;

@org.cyk.system.tramlop.server.annotation.System
public class DataLoaderImpl extends AbstractDataLoaderImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Response __execute__() throws Exception {
		__inject__(ProductBusiness.class).createMany(List.of(new Product().setCode("SABLE").setName("Sable"),new Product().setCode("GRAVIER").setName("Gravier")));
		
		return Response.ok().build();
	}
	
}