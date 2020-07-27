package com.group1.EnglishApp.specification;

import com.group1.EnglishApp.form.WordSearchForm;
import com.group1.EnglishApp.model.Word;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class WordSpecification implements Specification<Word> {
    private WordSearchForm wordSearchForm;

    public WordSpecification(WordSearchForm wordSearchForm) {
        this.wordSearchForm = wordSearchForm;
    }

    @Override
    public Predicate toPredicate(Root<Word> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if(wordSearchForm.getLevelId()!=null){
            predicates.add(criteriaBuilder.equal(root.get("levelOfWord").get("id"), wordSearchForm.getLevelId()));
        }
        if(wordSearchForm.getTopicId()!=null){
            predicates.add(criteriaBuilder.equal(root.get("topicOfWord").get("id"), wordSearchForm.getTopicId()));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
