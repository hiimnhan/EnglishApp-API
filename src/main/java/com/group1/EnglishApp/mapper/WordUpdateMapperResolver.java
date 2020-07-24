package com.group1.EnglishApp.mapper;

import com.group1.EnglishApp.form.WordUpdateForm;
import com.group1.EnglishApp.model.Word;
import com.group1.EnglishApp.repository.WordRepository;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Hai Dang
 */
@Component
public class WordUpdateMapperResolver {
    @Autowired
    WordRepository wordRepository;
    @ObjectFactory
    public Word resolve(WordUpdateForm wordUpdateForm, @TargetType Class<Word> type){
        return(wordUpdateForm != null && wordUpdateForm.getId() != null) ? wordRepository.findById(wordUpdateForm.getId()).get() : new Word();
    }
}
