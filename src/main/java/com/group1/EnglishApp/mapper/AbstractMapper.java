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
public interface AbstractMapper<S, T> {
    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    @interface Simple {
    }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    @interface SimpleList {
    }

    @Simple
    T toDto(S entity);

    @SimpleList
    @IterableMapping(qualifiedBy = Simple.class)
    List<T> toDtoList(Collection<S> entities);

    @Simple
    S toEntity(T dto);

    @SimpleList
    @IterableMapping(qualifiedBy = Simple.class)
    List<S> toEntityList(Collection<T> dtos);

    @Simple
    @IterableMapping(qualifiedBy = Simple.class)
    void updateEntity(T dto, @MappingTarget S entity);
}
