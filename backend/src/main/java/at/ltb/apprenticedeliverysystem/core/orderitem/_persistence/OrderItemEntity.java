package at.ltb.apprenticedeliverysystem.core.orderitem._persistence;

import at.ltb.apprenticedeliverysystem.core._common._persistence.AbstractCrudEntity;
import at.ltb.apprenticedeliverysystem.core.product._persistence.ProductEntity;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "lls_order_item")
@Getter
@Setter
public class OrderItemEntity extends AbstractCrudEntity {

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductEntity product;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @Nonnull
    @Column(name = "price", nullable = false, columnDefinition = "NUMERIC(10,2)")
    private Double price;
}
