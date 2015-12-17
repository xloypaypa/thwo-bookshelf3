package com.thoughtworks.jimmy.repository;

import org.springframework.data.repository.CrudRepository;
import com.thoughtworks.jimmy.model.SelfTag;

public interface SelfTagRepository extends CrudRepository<SelfTag, Integer> {
    SelfTag findByTag(String tag);
}
