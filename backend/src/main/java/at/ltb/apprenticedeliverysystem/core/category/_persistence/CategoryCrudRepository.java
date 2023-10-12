package at.ltb.apprenticedeliverysystem.core.category._persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryCrudRepository extends CrudRepository<CategoryEntity, Long> {
}
