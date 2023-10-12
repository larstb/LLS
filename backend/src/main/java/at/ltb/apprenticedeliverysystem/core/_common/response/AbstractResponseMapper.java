package at.ltb.apprenticedeliverysystem.core._common.response;

import at.ltb.apprenticedeliverysystem.core._common._persistence.AbstractBaseEntity;

public interface AbstractResponseMapper<S extends AbstractBaseEntity, T extends BaseResponse> {
    T map(S source);
}
