package com.group1.EnglishApp.mapper;

import com.group1.EnglishApp.dto.WordDto;
import com.group1.EnglishApp.model.Word;
import org.mapstruct.Mapper;

/**
 * @author Hai Dang
 */
@Mapper(componentModel = "spring")
public abstract class WordMapper implements AbstractMapper<Word, WordDto> {
}
