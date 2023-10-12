package at.ltb.apprenticedeliverysystem.core._common.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseWrapper<T extends BaseResponse> {
    private List<T> content;
    private long totalElements;

    public ResponseWrapper(List<T> content, long totalElements) {
        this.content = content;
        this.totalElements = totalElements;
    }
}
