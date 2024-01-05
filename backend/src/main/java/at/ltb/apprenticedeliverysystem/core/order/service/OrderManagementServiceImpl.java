package at.ltb.apprenticedeliverysystem.core.order.service;

import at.ltb.apprenticedeliverysystem.core._common.auth.AuthUserHelper;
import at.ltb.apprenticedeliverysystem.core._common.exception.CustomEntityNotFoundException;
import at.ltb.apprenticedeliverysystem.core._common.pagination.PaginationUtil;
import at.ltb.apprenticedeliverysystem.core._common.paymenttype.PaymentTypeEnum;
import at.ltb.apprenticedeliverysystem.core._common.response.QueryDslOverviewResponse;
import at.ltb.apprenticedeliverysystem.core._common.response.ResponseWrapper;
import at.ltb.apprenticedeliverysystem.core.groceryworkingday._persistence.GroceryWorkingDayEntity;
import at.ltb.apprenticedeliverysystem.core.groceryworkingday._persistence.GroceryWorkingDayQueryDSLRepository;
import at.ltb.apprenticedeliverysystem.core.order._persistence.OrderCrudRepository;
import at.ltb.apprenticedeliverysystem.core.order._persistence.OrderEntity;
import at.ltb.apprenticedeliverysystem.core.order._persistence.OrderQueryDSLRepository;
import at.ltb.apprenticedeliverysystem.core.order.api.OrderManagementService;
import at.ltb.apprenticedeliverysystem.core.order.dto.CreateOrderDTO;
import at.ltb.apprenticedeliverysystem.core.order.dto.OrderDetailDTO;
import at.ltb.apprenticedeliverysystem.core.order.dto.OrderOverviewDTO;
import at.ltb.apprenticedeliverysystem.core.order.exception.CurrentUserIsNotPayingUserException;
import at.ltb.apprenticedeliverysystem.core.order.mapper.OrderMapper;
import at.ltb.apprenticedeliverysystem.core.orderitem._persistence.OrderItemEntity;
import at.ltb.apprenticedeliverysystem.core.product._persistence.ProductEntity;
import at.ltb.apprenticedeliverysystem.core.product._persistence.ProductQueryDSLRepository;
import at.ltb.apprenticedeliverysystem.core.user._persistence.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class OrderManagementServiceImpl implements OrderManagementService {

    private final Logger logger = LoggerFactory.getLogger(OrderManagementServiceImpl.class);

    private final OrderQueryDSLRepository orderQueryDSLRepository;

    private final OrderCrudRepository orderCrudRepository;

    private final OrderMapper orderMapper;

    private final AuthUserHelper authUserHelper;

    private final GroceryWorkingDayQueryDSLRepository groceryWorkingDayQueryDSLRepository;

    private final ProductQueryDSLRepository productQueryDSLRepository;

    public OrderManagementServiceImpl(OrderQueryDSLRepository orderQueryDSLRepository,
                                      OrderCrudRepository orderCrudRepository, OrderMapper orderMapper,
                                      AuthUserHelper authUserHelper,
                                      GroceryWorkingDayQueryDSLRepository groceryWorkingDayQueryDSLRepository,
                                      ProductQueryDSLRepository productQueryDSLRepository) {
        this.orderQueryDSLRepository = orderQueryDSLRepository;
        this.orderCrudRepository = orderCrudRepository;
        this.orderMapper = orderMapper;
        this.authUserHelper = authUserHelper;
        this.groceryWorkingDayQueryDSLRepository = groceryWorkingDayQueryDSLRepository;
        this.productQueryDSLRepository = productQueryDSLRepository;
    }

    @Override
    public ResponseWrapper<OrderOverviewDTO> loadAllOrders(Integer page, Integer pageSize,
                                                           Optional<String> searchTerm, Optional<Boolean> isPayed,
                                                           Optional<PaymentTypeEnum> paymentType,
                                                           Optional<LocalDate> orderDateFrom,
                                                           Optional<LocalDate> orderDateTo) {
        QueryDslOverviewResponse<OrderEntity> response = orderQueryDSLRepository
                .loadOrders(searchTerm, isPayed, paymentType, orderDateFrom, orderDateTo, PaginationUtil.getPagination(page, pageSize));
        logger.info("loadAllOrders: count: " + response.getTotalElements());
        return new ResponseWrapper<>(orderMapper.mapOrderEntityToOverview(response.getContent()),
                response.getTotalElements());
    }

    @Override
    public OrderDetailDTO loadOrderById(String uuid) {
        OrderEntity foundedEntity = orderQueryDSLRepository.loadOrderByUuid(uuid);

        if(Objects.isNull(foundedEntity)) {
            logger.error("OrderEntity not found: " + uuid);
            throw new CustomEntityNotFoundException(OrderEntity.class.getSimpleName() + " not found");
        }

        logger.info("OrderEntity was found!");
        return orderMapper.mapOrderEntityToDetail(foundedEntity);
    }

    @Override
    @Transactional
    public OrderDetailDTO createOrder(CreateOrderDTO request) {
        UserEntity user = authUserHelper.getCurrentUserEntity();
        GroceryWorkingDayEntity groceryWorkingDay = groceryWorkingDayQueryDSLRepository.loadGroceryWorkingDayToday();
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUser(user);
        orderEntity.setPaymentType(request.paymentType());
        orderEntity.setGroceryWorkingDay(groceryWorkingDay);
        List<OrderItemEntity> orderedItems = request.orderedItems().stream().map(val -> {
                    OrderItemEntity orderItem = new OrderItemEntity();
                    ProductEntity productSelected = productQueryDSLRepository.loadProductByUuid(val.productId());
                    orderItem.setOrderedQuantity(val.orderedQuantity());
                    orderItem.setProduct(productSelected);
                    orderItem.setOldProductPrice(productSelected.getPrice());
                    orderItem.setNote(val.note());
                    return orderItem;
                })
                .collect(Collectors.toList());
        orderEntity.setOrderItems(orderedItems);
        logger.info("OrderEntity:Create Save is called!");
        orderCrudRepository.save(orderEntity);
        logger.info("OrderEntity:Create successfully!");
        return orderMapper.mapOrderEntityToDetail(orderEntity);
    }

    @Override
    public ResponseWrapper<OrderDetailDTO> loadAllOrdersForPayingUser(Integer page, Integer pageSize) {
        UserEntity user = authUserHelper.getCurrentUserEntity();
        QueryDslOverviewResponse<OrderEntity> response = orderQueryDSLRepository
                .loadOrdersForPayingUser(PaginationUtil.getPagination(page, pageSize), user.getId());
        logger.info("loadAllOrdersForPayingUser: count: " + response.getTotalElements());
        return new ResponseWrapper<>(orderMapper.mapOrderEntityToDetail(response.getContent()),
                response.getTotalElements());
    }

    @Override
    public List<OrderDetailDTO> loadTodayOrderForPayingUser() {
        UserEntity user = authUserHelper.getCurrentUserEntity();
        GroceryWorkingDayEntity groceryWorkingDay = groceryWorkingDayQueryDSLRepository.loadGroceryWorkingDayToday();

        if(Objects.isNull(groceryWorkingDay.getPayingUser()) || !Objects.equals(groceryWorkingDay.getPayingUser().getId(), user.getId())) {
            logger.error("current logged in user is not paying user");
            throw new CurrentUserIsNotPayingUserException("can not load Orders");
        }

        return orderMapper.mapOrderEntityToDetail(orderQueryDSLRepository.loadTodayOrderForPayingUser(user.getId()));
    }
}
