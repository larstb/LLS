package at.ltb.apprenticedeliverysystem.core.groceryworkingday._persistence;

import at.ltb.apprenticedeliverysystem.core._common._persistence.AbstractCrudEntity;
import at.ltb.apprenticedeliverysystem.core.user._persistence.UserEntity;
import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lls_grocery_working_day")
@Getter
@Setter
public class GroceryWorkingDayEntity extends AbstractCrudEntity {

    @Nonnull
    @Column(name = "working_day_date", nullable = false, unique = true)
    private LocalDate date = LocalDate.now();

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name="lls_grocery_working_day_going_user",
            joinColumns = @JoinColumn( name="grocery_working_day_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn( name="user_id", referencedColumnName = "id")
    )
    private List<UserEntity> goingUsers = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "paying_user_id", referencedColumnName = "id")
    private UserEntity payingUser;

}
