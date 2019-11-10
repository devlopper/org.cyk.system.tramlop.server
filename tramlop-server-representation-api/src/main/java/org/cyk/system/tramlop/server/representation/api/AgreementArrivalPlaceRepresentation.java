package org.cyk.system.tramlop.server.representation.api;
import javax.ws.rs.Path;

import org.cyk.system.tramlop.server.representation.entities.AgreementArrivalPlaceDto;
import org.cyk.utility.server.representation.RepresentationEntity;

@Path(AgreementArrivalPlaceRepresentation.PATH)
public interface AgreementArrivalPlaceRepresentation extends RepresentationEntity<AgreementArrivalPlaceDto> {
	
	String PATH = "/agreementarrivalplace";
	
}
