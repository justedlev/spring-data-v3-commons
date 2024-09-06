package io.justedlev.commons;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * An abstract class that represents an auditable entity. This class extends the {@link AbstractPersistable}
 * class and includes auditing fields such as the user who created and last modified the entity, along with
 * the timestamps for these actions.
 *
 * @param <K> the type of the primary key, which must be {@link Serializable}.
 * @author Justedlev
 * @see org.springframework.data.jpa.domain.AbstractPersistable
 * @see org.springframework.data.domain.Persistable
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<K extends Serializable> extends AbstractPersistable<K> implements Serializable {
    @Serial
    private static final long serialVersionUID = 202409011946L;

    /**
     * The user who created the entity.
     */
    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;

    /**
     * The timestamp when the entity was created.
     * This field is mandatory.
     */
    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    /**
     * The user who last modified the entity.
     */
    @LastModifiedBy
    @Column(name = "modified_by")
    private String modifiedBy;

    /**
     * The timestamp when the entity was last modified.
     * This field is mandatory.
     */
    @LastModifiedDate
    @Column(name = "modified_at", nullable = false)
    private Timestamp modifiedAt;
}
