package com.group1.EnglishApp.specification;

import com.group1.EnglishApp.form.UserSearchForm;
import com.group1.EnglishApp.model.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class UserSpecification implements Specification<User> {
    private UserSearchForm userSearchForm;

    public UserSpecification(UserSearchForm userSearchForm) {
        this.userSearchForm = userSearchForm;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if(userSearchForm.getUsername()!=null){
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("username")),"%" + userSearchForm.getUsername().toLowerCase() + "%"));
        }
        if(userSearchForm.getRole()!=null){
            predicates.add(criteriaBuilder.equal(root.get("role").get("id"), userSearchForm.getRole()));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
