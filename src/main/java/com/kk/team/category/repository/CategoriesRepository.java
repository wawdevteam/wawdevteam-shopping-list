package com.kk.team.category.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.kk.team.category.model.Category;

@RepositoryRestResource(collectionResourceRel = "categories", path = "categories")
public interface CategoriesRepository extends MongoRepository<Category, String> {

}
