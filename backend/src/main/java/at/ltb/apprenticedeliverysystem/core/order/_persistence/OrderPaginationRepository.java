package at.ltb.apprenticedeliverysystem.core.order._persistence;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface OrderPaginationRepository extends PagingAndSortingRepository<OrderEntity, Long> {
}
