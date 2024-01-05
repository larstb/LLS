package at.ltb.apprenticedeliverysystem.core.groceryworkingday._persistence;

import at.ltb.apprenticedeliverysystem.core._common.response.QueryDslOverviewResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class GroceryWorkingDayQueryDSLRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private final QGroceryWorkingDayEntity qGroceryWorkingDay = QGroceryWorkingDayEntity.groceryWorkingDayEntity;

    public QueryDslOverviewResponse<GroceryWorkingDayEntity> loadGroceryWorkingDays(Optional<String> goingUserId, Optional<String> payingUserId,
                                                                    Optional<LocalDate> date, PageRequest pageRequest) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        goingUserId.ifPresent(value -> booleanBuilder.and(qGroceryWorkingDay.goingUsers.any().uuid.eq(value)));
        payingUserId.ifPresent(value -> booleanBuilder.and(qGroceryWorkingDay.payingUser.uuid.eq(value)));
        date.ifPresent(value -> booleanBuilder.and(qGroceryWorkingDay.date.eq(value)));
        return new QueryDslOverviewResponse<>(buildFactory().selectFrom(qGroceryWorkingDay)
                .where(booleanBuilder)
                .limit(pageRequest.getPageSize())
                .offset(pageRequest.getOffset())
                .fetch(), buildFactory().selectFrom(qGroceryWorkingDay).fetchCount());
    }

    public GroceryWorkingDayEntity loadGroceryWorkingDayByDate(LocalDate date) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qGroceryWorkingDay.date.eq(date));
        return buildFactory().selectFrom(qGroceryWorkingDay)
                .where(booleanBuilder)
                .fetchOne();
    }

    public GroceryWorkingDayEntity loadGroceryWorkingDayByUuid(String uuid) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qGroceryWorkingDay.uuid.eq(uuid));
        return buildFactory().selectFrom(qGroceryWorkingDay)
                .where(booleanBuilder)
                .fetchOne();
    }

    public GroceryWorkingDayEntity loadGroceryWorkingDayToday() {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qGroceryWorkingDay.date.eq(LocalDate.now()));
        return buildFactory().selectFrom(qGroceryWorkingDay)
                .where(booleanBuilder)
                .fetchOne();
    }

    private JPAQueryFactory buildFactory() {
        return new JPAQueryFactory(entityManager);
    }

}
