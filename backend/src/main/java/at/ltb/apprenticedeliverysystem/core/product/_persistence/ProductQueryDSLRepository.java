package at.ltb.apprenticedeliverysystem.core.product._persistence;

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
public class ProductQueryDSLRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private final QProductEntity qProduct = QProductEntity.productEntity;

    public QueryDslOverviewResponse<ProductEntity> loadProductsOverview(Optional<String> searchTerm,
                                                                        Optional<Boolean> isActive,
                                                                        Optional<Boolean> isChecked,
                                                                        Optional<String> categoryId,
                                                               PageRequest pageRequest) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        searchTerm.ifPresent(value ->
                        booleanBuilder.and(qProduct.name.containsIgnoreCase(value))
                                .or(qProduct.producer.containsIgnoreCase(value))
                                .or(qProduct.category.name.containsIgnoreCase(value)));
        isActive.ifPresent(value -> booleanBuilder.and(qProduct.isActive.eq(value)));
        isChecked.ifPresent(value -> booleanBuilder.and(qProduct.isChecked.eq(value)));
        categoryId.ifPresent(value -> booleanBuilder.and(qProduct.category.uuid.eq(value)));
        return new QueryDslOverviewResponse<>(buildFactory().selectFrom(qProduct)
                .where(booleanBuilder)
                .limit(pageRequest.getPageSize())
                .offset(pageRequest.getOffset())
                .fetch(), buildFactory().selectFrom(qProduct).fetchCount());
    }

    public ProductEntity loadProductByUuid(String uuid) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qProduct.uuid.eq(uuid));
        return buildFactory().selectFrom(qProduct)
                .where(booleanBuilder)
                .fetchOne();
    }

    public ProductEntity loadProductByName(String name) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qProduct.name.eq(name));
        return buildFactory().selectFrom(qProduct)
                .where(booleanBuilder)
                .fetchOne();
    }

    private JPAQueryFactory buildFactory() {
        return new JPAQueryFactory(entityManager);
    }

}
