package com.group1.EnglishApp.service;

import com.group1.EnglishApp.dto.LevelAndTopicToDropBoxDto;
import com.group1.EnglishApp.dto.ProcessProgressDto;
import com.group1.EnglishApp.dto.ProgressDto;
import com.group1.EnglishApp.dto.WordDto;
import com.group1.EnglishApp.exception.EnglishAppValidationException;
import com.group1.EnglishApp.form.ProcessProgressForm;
import com.group1.EnglishApp.form.WordCreateForm;
import com.group1.EnglishApp.form.WordSearchForm;
import com.group1.EnglishApp.form.WordUpdateForm;
import com.group1.EnglishApp.mapper.*;
import com.group1.EnglishApp.model.Level;
import com.group1.EnglishApp.model.Topic;
import com.group1.EnglishApp.model.User;
import com.group1.EnglishApp.model.Word;
import com.group1.EnglishApp.repository.LevelRepository;
import com.group1.EnglishApp.repository.TopicRepository;
import com.group1.EnglishApp.repository.UserRepository;
import com.group1.EnglishApp.repository.WordRepository;
import com.group1.EnglishApp.specification.WordSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Hai Dang
 */
@Service
public class WordService extends BaseService<Word, Long>{
    private WordRepository wordRepository;
    private UserRepository userRepository;
    private LevelRepository levelRepository;
    private LevelMapper levelMapper;
    private TopicRepository topicRepository;
    private TopicMapper topicMapper;
    private WordMapper wordMapper;
    private WordCreateMapper wordCreateMapper;
    private WordUpdateMapper wordUpdateMapper;

    @Autowired
    public WordService(WordRepository wordRepository, UserRepository userRepository, LevelRepository levelRepository,
                       TopicRepository topicRepository, WordMapper wordMapper, WordCreateMapper wordCreateMapper,
                       WordUpdateMapper wordUpdateMapper, LevelMapper levelMapper, TopicMapper topicMapper){
        super(wordRepository);
        this.wordRepository = wordRepository;
        this.userRepository = userRepository;
        this.levelRepository = levelRepository;
        this.topicRepository = topicRepository;
        this.wordMapper = wordMapper;
        this.wordCreateMapper = wordCreateMapper;
        this.wordUpdateMapper = wordUpdateMapper;
        this.levelMapper = levelMapper;
        this.topicMapper = topicMapper;
    }


//    public Page<WordDto> getLoadingProgressPage(Long userId, Long levelId, Long topicId) throws EnglishAppValidationException{
//        if(!userRepository.findById(userId).isPresent()){
//            throw new EnglishAppValidationException("Cannot find this user.");
//        } else if (!levelRepository.findById(levelId).isPresent()){
//            throw new EnglishAppValidationException("Cannot find this level.");
//        } else if (!topicRepository.findById(topicId).isPresent()){
//            throw new EnglishAppValidationException("Cannot find this topic");
//        }
//        User userProgress = userRepository.findById(userId).get();
//        Level levelProgress = levelRepository.findById(levelId).get();
//        Topic topicProgress = topicRepository.findById(topicId).get();
//        Pageable defaultPageable = PageRequest.of(0, 1);
//        Page<Word> progressOfUser = wordRepository.findAllByUsersAndLevelOfWordAndTopicOfWord(userProgress, levelProgress, topicProgress, defaultPageable);
//        if(progressOfUser.isEmpty()){
//            Page<Word> defaultList = wordRepository.findAllByLevelOfWordAndTopicOfWord(levelProgress, topicProgress, defaultPageable);
//            return new PageImpl<>(wordMapper.toDtoList(defaultList.getContent()), defaultPageable, defaultList.getTotalElements());
//        }
//        int pageReturn = Math.toIntExact(progressOfUser.getTotalElements());
//        if(pageReturn==10){
//            pageReturn = 0;
//        }
//        Pageable pageable = PageRequest.of(pageReturn, 1);
//        Page<Word> progressPageLoading = wordRepository.findAllByLevelOfWordAndTopicOfWord(levelProgress, topicProgress, pageable);
//        return new PageImpl<>(wordMapper.toDtoList(progressPageLoading.getContent()), pageable, progressPageLoading.getTotalElements());
//    }

    public Page<WordDto> getFirstWord(Long userId, Long levelId, Long topicId) throws EnglishAppValidationException{
        if(!userRepository.findById(userId).isPresent()){
            throw new EnglishAppValidationException("Cannot find this user.");
        } else if (!levelRepository.findById(levelId).isPresent()){
            throw new EnglishAppValidationException("Cannot find this level.");
        } else if (!topicRepository.findById(topicId).isPresent()){
            throw new EnglishAppValidationException("Cannot find this topic");
        }
        User userProgress = userRepository.findById(userId).get();
        Level levelProgress = levelRepository.findById(levelId).get();
        Topic topicProgress = topicRepository.findById(topicId).get();
        List<Word> wordHaveLearnt = wordRepository.findAllByUsersAndLevelOfWordAndTopicOfWord(userProgress, levelProgress, topicProgress);
        int lastestWord = wordHaveLearnt.size();
        Pageable pageable = PageRequest.of(lastestWord, 1);
        Page<Word> progressPageLoading = wordRepository.findAllByLevelOfWordAndTopicOfWord(levelProgress, topicProgress, pageable);
        return new PageImpl<>(wordMapper.toDtoList(progressPageLoading.getContent()), pageable, progressPageLoading.getTotalElements());
    }

//    public Page<WordDto> getBackPageWord(Long wordId)throws EnglishAppValidationException{
//        if(!wordRepository.findById(wordId).isPresent()){
//            throw new EnglishAppValidationException("Cannot find this word.");
//        }
//        Word wordPresent = wordRepository.findById(wordId).get();
//        Level levelPresent = wordPresent.getLevelOfWord();
//        Topic topicPresent = wordPresent.getTopicOfWord();
//        int presentId = wordPresent.getId().intValue();
//        int prevNumberOfPage;
//        if(presentId%10==0){
//            prevNumberOfPage = ((presentId-1)%10)-1;
//        } else {
//            prevNumberOfPage = wordPresent.getId().intValue()%10 - 2;
//        }
//        if(prevNumberOfPage<0){
//            prevNumberOfPage = 0;
//        }
//        Pageable pageable = PageRequest.of(prevNumberOfPage, 1);
//        Page<Word> backPage = wordRepository.findAllByLevelOfWordAndTopicOfWord(levelPresent, topicPresent, pageable);
//        return new PageImpl<>(wordMapper.toDtoList(backPage.getContent()), pageable, backPage.getTotalElements());
//    }
//
//    public Page<WordDto> getNextPageWord(Long userId, Long wordId) throws EnglishAppValidationException{
//        if(!userRepository.findById(userId).isPresent()){
//            throw new EnglishAppValidationException("Cannot find this user.");
//        } else if(!wordRepository.findById(wordId).isPresent()){
//            throw new EnglishAppValidationException("Cannot find this word.");
//        }
//        User userPresent = userRepository.findById(userId).get();
//        Word wordPresent = wordRepository.findById(wordId).get();
//        Level levelPresent = wordPresent.getLevelOfWord();
//        Topic topicPresent = wordPresent.getTopicOfWord();
//        if(!userPresent.getWords().contains(wordPresent)){
//            ProcessProgressForm processProgressForm = new ProcessProgressForm();
//            processProgressForm.setUserId(userPresent.getId());
//            processProgressForm.setWordId(wordPresent.getId());
//            ProcessProgressDto processProgressDto = processProgress(processProgressForm);
//            if(!processProgressDto.getProcessStatus()){
//                throw new EnglishAppValidationException("Cannot save progress. Detail: " + processProgressDto.getMessage());
//            }
//        }
//        int nextNumberOfPage = wordPresent.getId().intValue()%10;
//        Pageable pageable = PageRequest.of(nextNumberOfPage, 1);
//        Page<Word> nextPage = wordRepository.findAllByLevelOfWordAndTopicOfWord(levelPresent, topicPresent, pageable);
//        return new PageImpl<>(wordMapper.toDtoList(nextPage.getContent()), pageable, nextPage.getTotalElements());
//    }

    public Page<WordDto> processPageWord(Long userId, Long wordId, Pageable pageable) throws EnglishAppValidationException{
        if(!userRepository.findById(userId).isPresent()){
            throw new EnglishAppValidationException("Cannot find this user.");
        } else if(!wordRepository.findById(wordId).isPresent()){
            throw new EnglishAppValidationException("Cannot find this word.");
        }
        User userPresent = userRepository.findById(userId).get();
        Word wordPresent = wordRepository.findById(wordId).get();
        Level levelPresent = wordPresent.getLevelOfWord();
        Topic topicPresent = wordPresent.getTopicOfWord();
        if(!userPresent.getWords().contains(wordPresent)){
            ProcessProgressForm processProgressForm = new ProcessProgressForm();
            processProgressForm.setUserId(userPresent.getId());
            processProgressForm.setWordId(wordPresent.getId());
            ProcessProgressDto processProgressDto = processProgress(processProgressForm);
            if(!processProgressDto.getProcessStatus()){
                throw new EnglishAppValidationException("Cannot save progress. Detail: " + processProgressDto.getMessage());
            }
        }
        Page<Word> pageReturn = wordRepository.findAllByLevelOfWordAndTopicOfWord(levelPresent, topicPresent, pageable);
        return new PageImpl<>(wordMapper.toDtoList(pageReturn.getContent()), pageable, pageReturn.getTotalElements());
    }
    public ProcessProgressDto processProgress(ProcessProgressForm processProgressForm) throws EnglishAppValidationException {
        if(!userRepository.findById(processProgressForm.getUserId()).isPresent()){
            throw new EnglishAppValidationException("Cannot find this user.");
        } else if(!wordRepository.findById(processProgressForm.getWordId()).isPresent()){
            throw new EnglishAppValidationException("Cannot find this word.");
        }
        User user = userRepository.findById(processProgressForm.getUserId()).get();
        Word word = wordRepository.findById(processProgressForm.getWordId()).get();
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

    public List<ProgressDto> calculateProgress(Long userId) throws EnglishAppValidationException{
        if(!userRepository.findById(userId).isPresent()){
            throw new EnglishAppValidationException("Cannot find this user.");
        }
        User user = userRepository.findById(userId).get();
        List<ProgressDto> returnListProgress = new ArrayList<>();
        List<Topic> allTopic = topicRepository.findAll();
        List<Level> allLevel = levelRepository.findAll();
        for (Topic topic : allTopic) {
            for (Level level : allLevel) {
                ProgressDto progressDto = new ProgressDto();
                progressDto.setTopicName(topic.getName());
                progressDto.setLevelName(level.getName());
                List<Word> totalWordOfThisTopicAndLevel = wordRepository.findAllByLevelOfWordAndTopicOfWord(level, topic);
                double totalNoWordOfThisTopicAndLevel = totalWordOfThisTopicAndLevel.size();
                List<Word> totalWordProgressOfThisUser = wordRepository.findAllByUsersAndLevelOfWordAndTopicOfWord(user, level, topic);
                double totalNoWordOfThisUser = totalWordProgressOfThisUser.size();
                progressDto.setNoProgress(totalWordProgressOfThisUser.size()+"/"+totalWordOfThisTopicAndLevel.size());
                double progress;
                if(totalNoWordOfThisUser==0){
                    progress = 0;
                } else {
                    progress = Math.round(totalNoWordOfThisUser*100 / totalNoWordOfThisTopicAndLevel*100.0) / 100.0;
                }
                progressDto.setProgress(progress+"%");
                returnListProgress.add(progressDto);
            }
        }
        return returnListProgress;
    }

    public Page<WordDto> getAllWord(Pageable pageable, WordSearchForm wordSearchForm) throws EnglishAppValidationException{
        WordSpecification wordSpecification = new WordSpecification(wordSearchForm);
        Page<Word> returnPage = wordRepository.findAll(wordSpecification, pageable);
        if(returnPage.getTotalElements()==0){
            throw new EnglishAppValidationException("Cannot find any word with these specification.");
        }
        return new PageImpl<>(wordMapper.toDtoList(returnPage.getContent()), pageable, returnPage.getTotalElements());
    }

    public WordDto getOneWord(Long wordId) throws EnglishAppValidationException{
        if(!wordRepository.findById(wordId).isPresent()){
            throw new EnglishAppValidationException("Cannot find word with this id");
        }
        return wordMapper.toDto(wordRepository.findById(wordId).get());
    }

    public WordDto createNewWord(WordCreateForm wordCreateForm) throws EnglishAppValidationException{
        if (!levelRepository.findById(wordCreateForm.getLevelId()).isPresent()){
            throw new EnglishAppValidationException("Cannot find this level.");
        } else if (!topicRepository.findById(wordCreateForm.getTopicId()).isPresent()){
            throw new EnglishAppValidationException("Cannot find this topic");
        }
        Level level = levelRepository.findById(wordCreateForm.getLevelId()).get();
        Topic topic = topicRepository.findById(wordCreateForm.getTopicId()).get();
        Word newWord = wordCreateMapper.toEntity(wordCreateForm);
        newWord.setLevelOfWord(level);
        newWord.setTopicOfWord(topic);
        return wordMapper.toDto(wordRepository.save(newWord));
    }

    public WordDto updateWord(WordUpdateForm wordUpdateForm) throws EnglishAppValidationException{
        if(!wordRepository.findById(wordUpdateForm.getId()).isPresent()){
            throw new EnglishAppValidationException("This word id is not exist in database");
        }
        if (!levelRepository.findById(wordUpdateForm.getLevelId()).isPresent()){
            throw new EnglishAppValidationException("Cannot find this level.");
        } else if (!topicRepository.findById(wordUpdateForm.getTopicId()).isPresent()){
            throw new EnglishAppValidationException("Cannot find this topic");
        }
        Level level = levelRepository.findById(wordUpdateForm.getLevelId()).get();
        Topic topic = topicRepository.findById(wordUpdateForm.getTopicId()).get();
        Word updated = wordUpdateMapper.toEntity(wordUpdateForm);
        updated.setLevelOfWord(level);
        updated.setTopicOfWord(topic);
        return wordMapper.toDto(wordRepository.save(updated));
    }

    public Boolean deleteWord(Long wordId) throws EnglishAppValidationException{
        Boolean delete = false;
        if(!wordRepository.findById(wordId).isPresent()){
            throw new EnglishAppValidationException("This word id is not exist in database");
        } else {
            wordRepository.delete(wordRepository.findById(wordId).get());
            delete = true;
        }
        return delete;
    }

    public LevelAndTopicToDropBoxDto getAllTopicAndLevel() throws EnglishAppValidationException{
        LevelAndTopicToDropBoxDto topicToDropBoxDto = new LevelAndTopicToDropBoxDto();
        List<Level> levelList = levelRepository.findAll();
        if(levelList.isEmpty()){
            throw new EnglishAppValidationException("Cannot load any level");
        }
        List<Topic> topicList = topicRepository.findAll();
        if(topicList.isEmpty()){
            throw new EnglishAppValidationException("Cannot load any topic");
        }
        topicToDropBoxDto.setLevelDtoList(levelMapper.toDtoList(levelList));
        topicToDropBoxDto.setTopicDtoList(topicMapper.toDtoList(topicList));
        return topicToDropBoxDto;
    }
}
