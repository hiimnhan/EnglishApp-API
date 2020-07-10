package com.group1.EnglishApp.repository;

import com.group1.EnglishApp.model.Level;
import com.group1.EnglishApp.model.Topic;
import com.group1.EnglishApp.model.User;
import com.group1.EnglishApp.model.Word;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Hai Dang
 */
@Repository
public interface WordRepository extends GenericRepository<Word, Long> {
    Page<Word> findAllByUsersAndLevelOfWordAndTopicOfWord(User user, Level level, Topic topic, Pageable pageable);
    Page<Word> findAllByLevelOfWordAndTopicOfWord(Level level, Topic topic, Pageable pageable);
    List<Word> findAllByLevelOfWordAndTopicOfWord(Level level, Topic topic);
    List<Word> findAllByUsersAndLevelOfWordAndTopicOfWord(User user, Level level, Topic topic);
}
