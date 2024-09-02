package io.justedlev.commons;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.domain.ReactiveAuditorAware;
import reactor.core.publisher.Mono;

/**
 * An adapter class that bridges the {@link AuditorAware} interface to the {@link ReactiveAuditorAware} interface.
 * This class provides a reactive wrapper around the synchronous {@link AuditorAware#getCurrentAuditor()} method.
 *
 * @param <T> the type of the auditor.
 * @see ReactiveAuditorAware
 * @see AuditorAware
 * @author Justedlev
 */
@RequiredArgsConstructor(staticName = "of", onConstructor_ = {@NonNull})
public final class ReactiveAuditorAwareAdapter<T> implements ReactiveAuditorAware<T> {
    private final AuditorAware<T> other;

    /**
     * Returns the current auditor, wrapped in a {@link Mono}. If the auditor is not present,
     * an empty {@link Mono} is returned.
     *
     * @return a {@link Mono} containing the current auditor, or empty if not present.
     * @see ReactiveAuditorAware#getCurrentAuditor()
     * @see AuditorAware#getCurrentAuditor()
     */
    @NonNull
    @Override
    public Mono<T> getCurrentAuditor() {
        return Mono.justOrEmpty(other.getCurrentAuditor());
    }
}
