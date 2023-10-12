package at.ltb.apprenticedeliverysystem.core.product._persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCrudRepository extends CrudRepository<ProductEntity, Long> {
}
