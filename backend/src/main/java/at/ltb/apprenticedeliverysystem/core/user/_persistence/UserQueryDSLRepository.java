package at.ltb.apprenticedeliverysystem.core.user._persistence;

import at.ltb.apprenticedeliverysystem.core._common.response.QueryDslOverviewResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class UserQueryDSLRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private final QUserEntity qUser = QUserEntity.userEntity;

    public QueryDslOverviewResponse<UserEntity> loadUsers(Optional<String> searchTerm, PageRequest pageRequest) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        searchTerm.ifPresent(value ->
                        booleanBuilder.and(qUser.firstname.containsIgnoreCase(value))
                                .or(qUser.lastname.containsIgnoreCase(value)
                                        .or(qUser.email.containsIgnoreCase(value))));
        return new QueryDslOverviewResponse<>(buildFactory().selectFrom(qUser)
                .where(booleanBuilder)
                .limit(pageRequest.getPageSize())
                .offset(pageRequest.getOffset())
                .fetch(), buildFactory().selectFrom(qUser).fetchCount());
    }

    public UserEntity loadUserByEmail(String email) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qUser.email.containsIgnoreCase(email));
        return buildFactory().selectFrom(qUser)
                .where(booleanBuilder)
                .fetchOne();
    }

    public UserEntity loadUserByUuid(String uuid) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qUser.uuid.eq(uuid));
        return buildFactory().selectFrom(qUser)
                .where(booleanBuilder)
                .fetchOne();
    }

    public UserEntity loadUserByKeyCloakReference(String keyCloakReference) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qUser.keycloakReference.eq(keyCloakReference));
        return buildFactory().selectFrom(qUser)
                .where(booleanBuilder)
                .fetchOne();
    }

    private JPAQueryFactory buildFactory() {
        return new JPAQueryFactory(entityManager);
    }

}
