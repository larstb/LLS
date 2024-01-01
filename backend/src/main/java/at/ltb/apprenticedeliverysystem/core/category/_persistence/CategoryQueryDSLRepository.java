package at.ltb.apprenticedeliverysystem.core.category._persistence;

import at.ltb.apprenticedeliverysystem.core._common.response.QueryDslOverviewResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class CategoryQueryDSLRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private final QCategoryEntity qCategory = QCategoryEntity.categoryEntity;

    public QueryDslOverviewResponse<CategoryEntity> loadCategory(Optional<String> searchTerm, PageRequest pageRequest) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        searchTerm.ifPresent(value ->
                        booleanBuilder.and(qCategory.name.containsIgnoreCase(value))
                                .or(qCategory.description.containsIgnoreCase(value)));
        return new QueryDslOverviewResponse<>(buildFactory().selectFrom(qCategory)
                .where(booleanBuilder)
                .limit(pageRequest.getPageSize())
                .offset(pageRequest.getOffset())
                .fetch(), buildFactory().selectFrom(qCategory).fetchCount());
    }

    public QueryDslOverviewResponse<CategoryEntity> loadCategoryWithoutPagination(Optional<String> searchTerm) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        searchTerm.ifPresent(value ->
                booleanBuilder.and(qCategory.name.containsIgnoreCase(value))
                        .or(qCategory.description.containsIgnoreCase(value)));
        return new QueryDslOverviewResponse<>(buildFactory().selectFrom(qCategory)
                .where(booleanBuilder)
                .fetch(), buildFactory().selectFrom(qCategory).fetchCount());
    }

    public CategoryEntity loadCategoryByUuid(String uuid) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qCategory.uuid.eq(uuid));
        return buildFactory().selectFrom(qCategory)
                .where(booleanBuilder)
                .fetchOne();
    }

    public CategoryEntity loadCategoryByName(String name) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qCategory.name.eq(name));
        return buildFactory().selectFrom(qCategory)
                .where(booleanBuilder)
                .fetchOne();
    }

    private JPAQueryFactory buildFactory() {
        return new JPAQueryFactory(entityManager);
    }

}
