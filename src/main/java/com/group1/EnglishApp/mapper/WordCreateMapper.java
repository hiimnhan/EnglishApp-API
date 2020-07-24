package com.group1.EnglishApp.mapper;

import com.group1.EnglishApp.form.WordCreateForm;
import com.group1.EnglishApp.model.Word;
import org.mapstruct.Mapper;

/**
 * @author Hai Dang
 */
@Mapper(componentModel = "spring")
public abstract class WordCreateMapper implements AbstractMapper<Word, WordCreateForm> {
}
