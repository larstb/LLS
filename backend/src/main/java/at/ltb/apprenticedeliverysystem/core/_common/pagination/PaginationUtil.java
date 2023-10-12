package at.ltb.apprenticedeliverysystem.core._common.pagination;

import org.springframework.data.domain.PageRequest;

public class PaginationUtil {

    public static PageRequest getPagination(Integer page, Integer size) {
        return PageRequest.of(page == null ? 0 : page, size == null ? 20 : size);
    }
}
