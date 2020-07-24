package com.group1.EnglishApp.service;

import com.group1.EnglishApp.dto.ProcessProgressDto;
import com.group1.EnglishApp.dto.UserDto;
import com.group1.EnglishApp.exception.EnglishAppException;
import com.group1.EnglishApp.exception.EnglishAppValidationException;
import com.group1.EnglishApp.form.ProcessProgressForm;
import com.group1.EnglishApp.form.RegisterForm;
import com.group1.EnglishApp.form.UserSearchForm;
import com.group1.EnglishApp.mapper.UserMapper;
import com.group1.EnglishApp.mapper.UserRegisterMapper;
import com.group1.EnglishApp.model.Level;
import com.group1.EnglishApp.model.User;
import com.group1.EnglishApp.model.Word;
import com.group1.EnglishApp.repository.LevelRepository;
import com.group1.EnglishApp.repository.RoleRepository;
import com.group1.EnglishApp.repository.UserRepository;
import com.group1.EnglishApp.repository.WordRepository;
import com.group1.EnglishApp.specification.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author Hai Dang
 */
@Service
public class UserService extends BaseService<User, Long>{
    private UserRepository userRepository;
    private UserRegisterMapper userRegisterMapper;
    private UserMapper userMapper;
    private RoleRepository roleRepository;
    private WordRepository wordRepository;
    private LevelRepository levelRepository;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, UserRegisterMapper userRegisterMapper,
                       RoleRepository roleRepository, WordRepository wordRepository, LevelRepository levelRepository) {
        super(userRepository);
        this.userRepository = userRepository;
        this.userRegisterMapper = userRegisterMapper;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.wordRepository = wordRepository;
        this.levelRepository = levelRepository;
    }

    /**
     *
     * @param username
     * @return User
     * @throws EnglishAppException
     */
    public User getUserByUsername(String username) throws EnglishAppException {

        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new EnglishAppException("User was not found");
        }
        return user;
    }

    /**
     *
     * @param registerForm
     * @return UserDto
     * @throws EnglishAppException
     */
    public UserDto registerUser(RegisterForm registerForm) throws EnglishAppValidationException {
        User newUser = userRegisterMapper.toEntity(registerForm);
        if(userRepository.findUserByUsername(newUser.getUsername())!=null){
            throw new EnglishAppValidationException("This username is already exist.");
        }
        newUser.setRole(roleRepository.findById(2L).get());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        return userMapper.toDto(userRepository.save(newUser));
    }

    public Page<UserDto> getPageUser(Pageable pageable, UserSearchForm userSearchForm) throws EnglishAppValidationException{
        UserSpecification userSpecification = new UserSpecification(userSearchForm);
        Page<User> page = userRepository.findAll(userSpecification, pageable);
        if(page.isEmpty()){
            throw new EnglishAppValidationException("Not found any user with these specification");
        }
        return new PageImpl<>(userMapper.toDtoList(page.getContent()), pageable, page.getTotalElements());
    }

    public ProcessProgressDto processProgress(ProcessProgressForm processProgressForm) throws EnglishAppValidationException{
        User user = userRepository.findById(processProgressForm.getUserId()).get();
        if(user==null){
            throw new EnglishAppValidationException("Not found this user");
        }
        Word word = wordRepository.findById(processProgressForm.getWordId()).get();
        if(word==null){
            throw new EnglishAppValidationException("Not found this word");
        }
        Set<Word> existWordOfThisUser = user.getWords();
        if(user.getWords().contains(word)){
            throw new EnglishAppValidationException("This user already learn this word");
        } else {
            existWordOfThisUser.add(word);
        }
        user.setWords(existWordOfThisUser);
        ProcessProgressDto returnStatus = new ProcessProgressDto();
        try{
            userRepository.save(user);
            returnStatus.setProcessStatus(true);
            returnStatus.setMessage("Save user progress successfully!");
        } catch (Exception e){
            returnStatus.setProcessStatus(false);
            returnStatus.setMessage("Save user progress failed!");
        }
        return returnStatus;
    }

    public UserDto setLevel(Long userId, Long levelId) throws EnglishAppValidationException{
        if(!levelRepository.findById(levelId).isPresent()){
            throw new EnglishAppValidationException("Cannot find this level");
        }
        Level level = levelRepository.findById(levelId).get();
        if(!userRepository.findById(userId).isPresent()){
            throw new EnglishAppValidationException("Cannot find this user");
        }
        User user = userRepository.findById(userId).get();
        user.setLastestLevelId(level);
        return userMapper.toDto(userRepository.save(user));
    }
}
