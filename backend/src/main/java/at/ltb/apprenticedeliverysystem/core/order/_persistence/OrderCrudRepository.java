package at.ltb.apprenticedeliverysystem.core.order._persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderCrudRepository extends CrudRepository<OrderEntity, Long> {
}
