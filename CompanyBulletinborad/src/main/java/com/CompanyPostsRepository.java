
package com;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface CompanyPostsRepository extends CrudRepository<CompanyPost, Integer> {

	List<CompanyPost> findByUser(User user);
	
}