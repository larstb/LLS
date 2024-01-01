package at.ltb.apprenticedeliverysystem.core.product._persistence;

import at.ltb.apprenticedeliverysystem.core._common._persistence.AbstractCrudEntity;
import at.ltb.apprenticedeliverysystem.core.category._persistence.CategoryEntity;
import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "lls_product")
@Getter
@Setter
public class ProductEntity extends AbstractCrudEntity {

    @Nonnull
    @NotBlank
    @Column(name = "p_name", nullable = false)
    private String name;

    @Nonnull
    @NotBlank
    @Column(name = "producer", nullable = false)
    private String producer;

    @Nonnull
    @NotBlank
    @Column(name = "quantity", nullable = false, columnDefinition = "CHAR(20)")
    private String quantity;

    @Nonnull
    @Column(name = "price", nullable = false, columnDefinition = "NUMERIC(10,2)")
    private Double price;

    @Nonnull
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = false;

    @Nonnull
    @Column(name = "is_checked", nullable = false)
    private Boolean isChecked = false;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private CategoryEntity category;

}
