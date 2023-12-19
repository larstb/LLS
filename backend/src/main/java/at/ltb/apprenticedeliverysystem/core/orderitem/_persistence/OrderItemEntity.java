package at.ltb.apprenticedeliverysystem.core.orderitem._persistence;

import at.ltb.apprenticedeliverysystem.core._common._persistence.AbstractCrudEntity;
import at.ltb.apprenticedeliverysystem.core.product._persistence.ProductEntity;
import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
    @Column(name = "ordered_quantity", nullable = false, updatable = false)
    private Integer orderedQuantity = 1;

    @Nonnull
    @Column(name = "old_product_price", nullable = false, updatable = false, columnDefinition = "NUMERIC(10,2)")
    private Double oldProductPrice;
}
