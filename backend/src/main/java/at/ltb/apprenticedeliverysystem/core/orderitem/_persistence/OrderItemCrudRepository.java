package at.ltb.apprenticedeliverysystem.core.orderitem._persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemCrudRepository extends CrudRepository<OrderItemEntity, Long> {
}
