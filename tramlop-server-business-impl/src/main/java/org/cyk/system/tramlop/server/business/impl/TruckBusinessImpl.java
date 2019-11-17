package org.cyk.system.tramlop.server.business.impl;

import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.business.api.TruckBusiness;
import org.cyk.system.tramlop.server.business.api.query.FindTruckByAgreementsCodes;
import org.cyk.system.tramlop.server.business.api.query.FindTruckByTasksCodes;
import org.cyk.system.tramlop.server.persistence.api.TruckPersistence;
import org.cyk.system.tramlop.server.persistence.api.query.ReadTruckByAgreementsCodes;
import org.cyk.system.tramlop.server.persistence.api.query.ReadTruckByTasksCodes;
import org.cyk.system.tramlop.server.persistence.entities.Truck;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

@ApplicationScoped
public class TruckBusinessImpl extends AbstractBusinessEntityImpl<Truck, TruckPersistence> implements TruckBusiness,FindTruckByAgreementsCodes,FindTruckByTasksCodes,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Collection<Truck> findWhereAgreementClosedIsFalseExist(Properties properties) {
		return __persistence__.readWhereAgreementClosedIsFalseExist(properties);
	}

	@Override
	public Collection<Truck> findWhereAgreementClosedIsFalseDoesNotExist(Properties properties) {
		return __persistence__.readWhereAgreementClosedIsFalseExist(properties);
	}

	@Override
	public Collection<Truck> findWhereDeliveryClosedIsFalseExist(Properties properties) {
		return __persistence__.readWhereDeliveryClosedIsFalseExist(properties);
	}

	@Override
	public Collection<Truck> findWhereDeliveryClosedIsFalseDoesNotExist(Properties properties) {
		return __persistence__.readWhereDeliveryClosedIsFalseDoesNotExist(properties);
	}

	@Override
	public Collection<Truck> findWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseExist(Properties properties) {
		return __persistence__.readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseExist(properties);
	}

	@Override
	public Collection<Truck> findWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseDoesNotExist(Properties properties) {
		return __persistence__.readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseDoesNotExist(properties);
	}

	@Override
	public Collection<Truck> findByAgreementsCodes(Collection<String> codes, Properties properties) {
		return ((ReadTruckByAgreementsCodes)__persistence__).readByAgreementsCodes(codes,properties);
	}

	@Override
	public Collection<Truck> findByTasksCodes(Collection<String> codes, Properties properties) {
		return ((ReadTruckByTasksCodes)__persistence__).readByTasksCodes(codes,properties);
	}
		
}
