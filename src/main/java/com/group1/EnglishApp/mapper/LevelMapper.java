package com.group1.EnglishApp.mapper;

import com.group1.EnglishApp.dto.LevelDto;
import com.group1.EnglishApp.model.Level;
import org.mapstruct.Mapper;

/**
 * @author Hai Dang
 */
@Mapper(componentModel = "spring")
public abstract class LevelMapper implements AbstractMapper<Level, LevelDto> {
}
