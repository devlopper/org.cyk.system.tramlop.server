package org.cyk.system.tramlop.server.representation.api;
import javax.ws.rs.Path;

import org.cyk.system.tramlop.server.representation.entities.ProductDto;
import org.cyk.utility.server.representation.RepresentationEntity;

@Path(ProductRepresentation.PATH)
public interface ProductRepresentation extends RepresentationEntity<ProductDto> {
	
	String PATH = "/product";
	
}
