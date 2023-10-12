package at.ltb.apprenticedeliverysystem.core.order._persistence;

import at.ltb.apprenticedeliverysystem.core._common._persistence.AbstractCrudEntity;
import at.ltb.apprenticedeliverysystem.core._common.paymenttype.PaymentTypeEnum;
import at.ltb.apprenticedeliverysystem.core.orderitem._persistence.OrderItemEntity;
import at.ltb.apprenticedeliverysystem.core.user._persistence.UserEntity;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lls_order")
@Getter
@Setter
public class OrderEntity extends AbstractCrudEntity {

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name="lls_order_order_item",
            joinColumns = @JoinColumn( name="order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn( name="order_item_id", referencedColumnName = "id")
    )
    private List<OrderItemEntity> oderItems = new ArrayList<>();

    @Nonnull
    @Column(name = "is_payed", nullable = false)
    private Boolean isPayed = false;

    @Column(name = "payment_type", columnDefinition = "CHAR(15)")
    @Enumerated(value = EnumType.STRING)
    private PaymentTypeEnum paymentType;
}
