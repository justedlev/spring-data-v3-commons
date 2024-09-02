package io.justedlev.commons;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

/**
 * An abstract class that represents an entity with versioning capability.
 * This class extends the {@link Auditable} class and adds a version field.
 *
 * @param <K> the type of the primary key, which must be {@link Serializable}.
 *
 * @author Justedlev
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Versionable<K extends Serializable> extends Auditable<K> implements Serializable {
    @Serial
    private static final long serialVersionUID = 202409011946L;

    /**
     * The version number of the entity. This field is used for optimistic locking.
     */
    @Version
    @Column(name = "version", nullable = false)
    private Long version;
}
