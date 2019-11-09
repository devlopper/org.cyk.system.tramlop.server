package org.cyk.system.tramlop.server.representation.api;
import javax.ws.rs.Path;

import org.cyk.system.tramlop.server.representation.entities.AgreementPathDto;
import org.cyk.utility.server.representation.RepresentationEntity;

@Path(AgreementPathRepresentation.PATH)
public interface AgreementPathRepresentation extends RepresentationEntity<AgreementPathDto> {
	
	String PATH = "/agreementpath";
	
}
