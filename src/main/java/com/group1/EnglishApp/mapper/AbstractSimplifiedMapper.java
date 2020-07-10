package com.group1.EnglishApp.mapper;
import org.mapstruct.IterableMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Collection;
import java.util.List;

/**
 * Abstract mapper maps Entity to DTO and vice versa
 *
 * @param <S> source
 * @param <T> target
 */
public interface AbstractSimplifiedMapper<S, T> {

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    @interface Simplified {
    }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    @interface SimplifiedList {
    }

    @Simplified
    T toSimplifiedDto(S entity);

    @SimplifiedList
    @IterableMapping(qualifiedBy = Simplified.class)
    List<T> toSimplifiedDtoList(Collection<S> entities);

    @Simplified
    S toSimplifiedEntity(T dto);

    @SimplifiedList
    @IterableMapping(qualifiedBy = Simplified.class)
    List<S> toSimplifiedEntityList(Collection<T> dtos);

    @Simplified
    @IterableMapping(qualifiedBy = Simplified.class)
    void updateSimplifiedEntity(T dto, @MappingTarget S entity);
}
