package at.ltb.apprenticedeliverysystem.core.order._persistence;

import at.ltb.apprenticedeliverysystem.core._common.paymenttype.PaymentTypeEnum;
import at.ltb.apprenticedeliverysystem.core._common.response.QueryDslOverviewResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class OrderQueryDSLRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private final QOrderEntity qOrder = QOrderEntity.orderEntity;

    public QueryDslOverviewResponse<OrderEntity> loadOrders(Optional<String> searchTerm, Optional<Boolean> isPayed,
                                                           Optional<PaymentTypeEnum> paymentType,
                                                           Optional<LocalDate> orderDateFrom,
                                                           Optional<LocalDate> orderDateTo,
                                                           PageRequest pageRequest) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        searchTerm.ifPresent(value ->
                        booleanBuilder.and(qOrder.user.firstname.containsIgnoreCase(value))
                                .or(qOrder.user.lastname.containsIgnoreCase(value))
                                .or(qOrder.user.email.containsIgnoreCase(value))
                                .or(qOrder.orderItems.any().product.producer.containsIgnoreCase(value))
                                .or(qOrder.orderItems.any().product.name.containsIgnoreCase(value))
                                .or(qOrder.orderItems.any().product.category.name.containsIgnoreCase(value)));
        isPayed.ifPresent(val -> booleanBuilder.and(qOrder.isPayed.eq(val)));
        paymentType.ifPresent(val -> booleanBuilder.and(qOrder.paymentType.eq(val)));
        orderDateFrom.ifPresent(val -> booleanBuilder.and(qOrder.groceryWorkingDay.date.after(val)));
        orderDateTo.ifPresent(val -> booleanBuilder.and(qOrder.groceryWorkingDay.date.before(val)));
        return new QueryDslOverviewResponse<>(buildFactory().selectFrom(qOrder)
                .where(booleanBuilder)
                .limit(pageRequest.getPageSize())
                .offset(pageRequest.getOffset())
                .fetch(), buildFactory().selectFrom(qOrder).fetchCount());
    }

    public QueryDslOverviewResponse<OrderEntity> loadOrdersForPayingUser(PageRequest pageRequest, Long payingUserId) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qOrder.groceryWorkingDay.payingUser.id.eq(payingUserId));
        return new QueryDslOverviewResponse<>(buildFactory().selectFrom(qOrder)
                .where(booleanBuilder)
                .limit(pageRequest.getPageSize())
                .offset(pageRequest.getOffset())
                .fetch(), buildFactory().selectFrom(qOrder).fetchCount());
    }

    public OrderEntity loadOrderByUuid(String uuid) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qOrder.uuid.eq(uuid));
        return buildFactory().selectFrom(qOrder)
                .where(booleanBuilder)
                .fetchOne();
    }

    public List<OrderEntity> loadTodayOrderForPayingUser(Long payingUserId) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qOrder.groceryWorkingDay.date.eq(LocalDate.now()));
        booleanBuilder.and(qOrder.groceryWorkingDay.payingUser.id.eq(payingUserId));
        return buildFactory().selectFrom(qOrder)
                .where(booleanBuilder)
                .fetch();
    }

    private JPAQueryFactory buildFactory() {
        return new JPAQueryFactory(entityManager);
    }

}
