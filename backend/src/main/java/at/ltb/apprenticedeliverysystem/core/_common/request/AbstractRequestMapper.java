package at.ltb.apprenticedeliverysystem.core._common.request;

import at.ltb.apprenticedeliverysystem.core._common._persistence.AbstractBaseEntity;

public interface AbstractRequestMapper<S extends BaseRequest, T extends AbstractBaseEntity> {
    T map(S source);
}
