package org.cyk.system.tramlop.server.representation.api;
import javax.ws.rs.Path;

import org.cyk.system.tramlop.server.representation.entities.AgreementProductDto;
import org.cyk.utility.server.representation.RepresentationEntity;

@Path(AgreementProductRepresentation.PATH)
public interface AgreementProductRepresentation extends RepresentationEntity<AgreementProductDto> {
	
	String PATH = "/agreementproduct";
	
}
