package at.ltb.apprenticedeliverysystem.core.groceryworkingday._persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroceryWorkingCrudRepository extends CrudRepository<GroceryWorkingDayEntity, Long> {
}
