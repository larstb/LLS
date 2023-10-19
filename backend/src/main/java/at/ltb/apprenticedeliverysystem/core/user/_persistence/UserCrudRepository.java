package at.ltb.apprenticedeliverysystem.core.user._persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCrudRepository extends CrudRepository<UserEntity, Long> {
}
