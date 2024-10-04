package io.justedlev.sb3c;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

/**
 * An abstract class that represents an entity with versioning capability.
 * This class extends the {@link Auditable} class and adds a version field.
 *
 * @param <K> the type of the primary key, which must be {@link Serializable}.
 * @author Justedlev
 * @see Version
 */
@Getter
@Setter(AccessLevel.PROTECTED)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true, fluent = true)
@MappedSuperclass
public abstract class Versionable<K extends Serializable> extends Auditable<K> implements Serializable {

    @Serial
    private static final long serialVersionUID = 202409011946L;

    /**
     * The version number of the entity. This field is used for optimistic locking.
     *
     * @see Version
     */
    @Version
    @NotNull
    private Long version;

}
