package com.group1.EnglishApp.service;

import com.group1.EnglishApp.dto.LevelDto;
import com.group1.EnglishApp.exception.EnglishAppValidationException;
import com.group1.EnglishApp.mapper.LevelMapper;
import com.group1.EnglishApp.model.Level;
import com.group1.EnglishApp.repository.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Hai Dang
 */
@Service
public class LevelService extends BaseService<Level, Long> {
    private LevelRepository levelRepository;
    private LevelMapper levelMapper;

    @Autowired
    public LevelService(LevelRepository levelRepository, LevelMapper levelMapper){
        super(levelRepository);
        this.levelRepository = levelRepository;
        this.levelMapper = levelMapper;
    }

    public List<LevelDto> getAllLevel() throws EnglishAppValidationException {
        List<Level> levelList = levelRepository.findAll();
        if(levelList.isEmpty()){
            throw new EnglishAppValidationException("Cannot load any level");
        }
        return levelMapper.toDtoList(levelList);
    }
}
