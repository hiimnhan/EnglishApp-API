package com.group1.EnglishApp.mapper;

import com.group1.EnglishApp.form.UserUpdateForm;
import com.group1.EnglishApp.form.WordUpdateForm;
import com.group1.EnglishApp.model.User;
import com.group1.EnglishApp.model.Word;
import com.group1.EnglishApp.repository.UserRepository;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Hai Dang
 */
@Component
public class UserUpdateMapperResolver {
    @Autowired
    UserRepository userRepository;
    @ObjectFactory
    public User resolve(UserUpdateForm userUpdateForm, @TargetType Class<User> type){
        return(userUpdateForm != null && userUpdateForm.getId() != null) ? userRepository.findById(userUpdateForm.getId()).get() : new User();
    }
}
