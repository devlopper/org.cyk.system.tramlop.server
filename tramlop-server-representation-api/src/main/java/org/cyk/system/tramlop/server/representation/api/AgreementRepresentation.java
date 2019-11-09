package org.cyk.system.tramlop.server.representation.api;
import javax.ws.rs.Path;

import org.cyk.system.tramlop.server.representation.entities.AgreementDto;
import org.cyk.utility.server.representation.RepresentationEntity;

@Path(AgreementRepresentation.PATH)
public interface AgreementRepresentation extends RepresentationEntity<AgreementDto> {
	
	String PATH = "/agreement";
	
}
