package org.cyk.system.tramlop.server.representation.api;
import javax.ws.rs.Path;

import org.cyk.system.tramlop.server.representation.entities.AgreementTruckDto;
import org.cyk.utility.server.representation.RepresentationEntity;

@Path(AgreementTruckRepresentation.PATH)
public interface AgreementTruckRepresentation extends RepresentationEntity<AgreementTruckDto> {
	
	String PATH = "/agreementtruck";
	
}
