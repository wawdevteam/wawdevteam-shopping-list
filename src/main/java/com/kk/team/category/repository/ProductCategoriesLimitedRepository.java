package com.kk.team.category.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.kk.team.category.model.Category;

@RepositoryRestResource(collectionResourceRel = "categories", path = "categories")
public interface ProductCategoriesLimitedRepository extends ProductCategoriesRepository {

	/**
	 * Returns all product categories for a given language.
	 * 
	 * @param lang
	 *            language code e.g. en_US, pl_PL
	 * 
	 * @return list of categories for given language
	 */
	List<Category> findByLang(@Param("lang") String lang);

	/**
	 * Looks up Categories by name
	 * 
	 * @param name
	 *            name of the product category or part of it
	 * @param lang
	 *            language code e.g. en_US, pl_PL
	 * @return list of categories with matched name and language code
	 */
	List<Category> findByNameLikeIgnoreCaseAndLang(@Param("name") String name, @Param("lang") String lang);
}
