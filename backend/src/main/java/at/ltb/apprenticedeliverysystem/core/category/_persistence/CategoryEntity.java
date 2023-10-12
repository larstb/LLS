package at.ltb.apprenticedeliverysystem.core.category._persistence;

import at.ltb.apprenticedeliverysystem.core._common._persistence.AbstractTaxonomyEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "lls_category")
@Getter
@Setter
public class CategoryEntity extends AbstractTaxonomyEntity {

}
