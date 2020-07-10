package com.group1.EnglishApp.repository;

import com.group1.EnglishApp.model.Topic;
import org.springframework.stereotype.Repository;

/**
 * @author Hai Dang
 */
@Repository
public interface TopicRepository extends GenericRepository<Topic, Long> {
}
