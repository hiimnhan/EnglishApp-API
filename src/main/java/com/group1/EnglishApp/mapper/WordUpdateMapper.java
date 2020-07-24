package com.group1.EnglishApp.mapper;

import com.group1.EnglishApp.form.WordUpdateForm;
import com.group1.EnglishApp.model.Word;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * @author Hai Dang
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {WordUpdateMapperResolver.class})
public abstract class WordUpdateMapper implements AbstractMapper<Word, WordUpdateForm> {
}
