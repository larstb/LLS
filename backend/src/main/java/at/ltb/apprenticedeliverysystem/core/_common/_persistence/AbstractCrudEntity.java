package at.ltb.apprenticedeliverysystem.core._common._persistence;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class AbstractCrudEntity extends AbstractBaseEntity {

    @Column(name = "created_at", columnDefinition = "TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP",
            updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "modified_at", columnDefinition = "TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP")
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @Column(name = "created_by", updatable = false, columnDefinition = "CHAR(36)")
    @CreatedBy
    private String createdBy;

    @Column(name = "modified_by", columnDefinition = "CHAR(36)")
    @LastModifiedBy
    private String modifiedBy;

}
