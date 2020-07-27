package com.group1.EnglishApp.mapper;

import com.group1.EnglishApp.form.UserUpdateForm;
import com.group1.EnglishApp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * @author Hai Dang
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {UserUpdateMapperResolver.class})
public abstract class UserUpdateMapper implements AbstractMapper<User, UserUpdateForm> {
}
