package at.ltb.apprenticedeliverysystem.core.user._persistence;

import at.ltb.apprenticedeliverysystem.core._common._persistence.AbstractCrudEntity;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "lls_user")
@Getter
@Setter
public class UserEntity extends AbstractCrudEntity {

    @Nonnull
    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Nonnull
    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "status", columnDefinition = "NCLOB")
    private String status;

    @Nonnull
    @Column(name = "phone_number", nullable = false, columnDefinition = "CHAR(30)")
    private String phoneNumber;

    @Nonnull
    @Column(name = "location", nullable = false, length = 150)
    private String location;

    @Column(name = "iban", columnDefinition = "CHAR(20)")
    private String iban;

    @Column(name = "paypal_link", columnDefinition = "NCLOB")
    private String paypalLink;

    @Column(name = "keycloak_reference", columnDefinition = "CHAR(36)")
    private String keycloakReference;
}
