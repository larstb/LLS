package at.ltb.apprenticedeliverysystem.core.user._persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCrudRepository extends CrudRepository<UserEntity, Long> {
    @Query("select u from UserEntity u where lower(u.email) = lower(:email)")
    Optional<UserEntity> findUserByEmail(@Param("email") String email);

    @Query("select u from UserEntity u where u.id = :id AND u.isActive = true")
    Optional<UserEntity> findActiveUserById(@Param("id") Long id);

    @Query("select u from UserEntity u where lower(u.email) = lower(:email) AND u.isActive = true")
    Optional<UserEntity> findActiveUserByEmail(@Param("email") String email);
}
