package com.group1.EnglishApp.mapper;

import com.group1.EnglishApp.dto.UserManagementDto;
import com.group1.EnglishApp.model.User;
import org.mapstruct.Mapper;

/**
 * @author Hai Dang
 */
@Mapper(componentModel = "spring")
public abstract class UserManagementMapper implements AbstractMapper<User, UserManagementDto> {
}
