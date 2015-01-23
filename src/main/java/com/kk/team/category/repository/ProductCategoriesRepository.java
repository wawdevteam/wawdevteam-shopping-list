package com.kk.team.category.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.kk.team.category.model.Category;

@NoRepositoryBean
public interface ProductCategoriesRepository extends PagingAndSortingRepository<Category, String> {
	@Override
	public Page<Category> findAll(Pageable pageable);

	@Override
	public List<Category> findAll(Sort sort);

	@Override
	public boolean exists(String id);

	@Override
	public Category findOne(String id);
}
