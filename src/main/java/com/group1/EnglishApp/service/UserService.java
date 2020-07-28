package com.group1.EnglishApp.service;

import com.group1.EnglishApp.dto.ProcessProgressDto;
import com.group1.EnglishApp.dto.UserDto;
import com.group1.EnglishApp.dto.UserManagementDto;
import com.group1.EnglishApp.exception.EnglishAppException;
import com.group1.EnglishApp.exception.EnglishAppValidationException;
import com.group1.EnglishApp.form.ProcessProgressForm;
import com.group1.EnglishApp.form.RegisterForm;
import com.group1.EnglishApp.form.UserSearchForm;
import com.group1.EnglishApp.form.UserUpdateForm;
import com.group1.EnglishApp.mapper.UserManagementMapper;
import com.group1.EnglishApp.mapper.UserMapper;
import com.group1.EnglishApp.mapper.UserRegisterMapper;
import com.group1.EnglishApp.mapper.UserUpdateMapper;
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
    private UserUpdateMapper userUpdateMapper;
    private UserManagementMapper userManagementMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, UserRegisterMapper userRegisterMapper,
                       RoleRepository roleRepository, WordRepository wordRepository, LevelRepository levelRepository,
                       UserUpdateMapper userUpdateMapper, UserManagementMapper userManagementMapper) {
        super(userRepository);
        this.userRepository = userRepository;
        this.userRegisterMapper = userRegisterMapper;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.wordRepository = wordRepository;
        this.levelRepository = levelRepository;
        this.userUpdateMapper = userUpdateMapper;
        this.userManagementMapper = userManagementMapper;
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
     * @throws EnglishAppValidationException
     */
    public UserDto registerUser(RegisterForm registerForm) throws EnglishAppValidationException {
        User newUser = userRegisterMapper.toEntity(registerForm);
        if(userRepository.findUserByUsername(newUser.getUsername())!=null){
            throw new EnglishAppValidationException("This username is already exist.");
        }
        newUser.setRole(roleRepository.findById(2L).get());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        newUser.setActive(true);
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

    public UserManagementDto getOneUser(Long userId) throws EnglishAppValidationException{
        if(!userRepository.findById(userId).isPresent()){
            throw new EnglishAppValidationException("Cannot find this user");
        }
        User user = userRepository.findById(userId).get();
        return userManagementMapper.toDto(user);
    }

    public UserManagementDto updateUser(UserUpdateForm userUpdateForm) throws EnglishAppValidationException{
        if(!userRepository.findById(userUpdateForm.getId()).isPresent()){
            throw new EnglishAppValidationException("Cannot find this user");
        }
        return userManagementMapper.toDto(userRepository.save(userUpdateMapper.toEntity(userUpdateForm)));
    }

    public UserManagementDto activeUser(Long userId) throws EnglishAppValidationException{
        if(!userRepository.findById(userId).isPresent()){
            throw new EnglishAppValidationException("Cannot find this user");
        }
        User user = userRepository.findById(userId).get();
        if(user.getActive()){
            user.setActive(false);
        } else {
            user.setActive(true);
        }
        return userManagementMapper.toDto(userRepository.save(user));
    }
}
