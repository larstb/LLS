package at.ltb.apprenticedeliverysystem.core._common._persistence;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class AbstractTaxonomyEntity extends AbstractCrudEntity {

    @Nonnull
    @Column(name = "t_name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "NCLOB")
    private String description;

}
