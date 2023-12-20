package at.ltb.apprenticedeliverysystem.core._common.response;

import at.ltb.apprenticedeliverysystem.core._common._persistence.AbstractBaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QueryDslOverviewResponse<T extends AbstractBaseEntity> {
    private List<T> content;

    private long totalElements;

    public QueryDslOverviewResponse(List<T> content, long totalElements) {
        this.content = content;
        this.totalElements = totalElements;
    }
}
