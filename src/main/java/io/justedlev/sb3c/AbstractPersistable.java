package io.justedlev.sb3c;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Persistable;
import org.springframework.lang.Nullable;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * An abstract base class for persistable entities. This class provides a base implementation for
 * entities with an ID and includes methods for checking if an entity is new, as well as
 * {@code equals(Object o)} and {@code hashCode()} implementations.
 *
 * @param <K> the type of the primary key, which must be {@link Serializable}.
 *
 * @author Justedlev
 * @see Persistable
 * @see org.springframework.data.jpa.domain.AbstractPersistable
 */
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true, fluent = true)
@MappedSuperclass
public abstract class AbstractPersistable<K extends Serializable> implements Persistable<K>, Serializable {

    @Serial
    private static final long serialVersionUID = 202409011946L;

    /**
     * @see Persistable#getId()
     */
    @Id
    @GeneratedValue
    @Setter(AccessLevel.PROTECTED)
    private K id;

    /**
     * @see Persistable#getId()
     */
    @Override
    public K getId() {
        return this.id();
    }

    /**
     * @see Persistable#isNew()
     */
    @Transient
    @Override
    public boolean isNew() {
        return Objects.isNull(id);
    }

    @Override
    public final boolean equals(Object o) {

        if (Objects.isNull(o)) {
            return false;
        }

        if (this == o) {
            return true;
        }

        if (!Objects.equals(Hibernate.getClass(this), Hibernate.getClass(o))) {
            return false;
        }

        AbstractPersistable<?> that = (AbstractPersistable<?>) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(id);
    }

}
