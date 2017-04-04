package com.aaron.spring.service;

import com.aaron.spring.bean.UserBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaginationService {

	/**
	 * Find matching data using name
	 *
	 * @param name
	 * @param pageable
	 * @return Matched Page Result
	 */
	Page<UserBean> findAllPageable(String name, Pageable pageable);

	/**
	 * Saves data in db
	 *
	 * @return void
	 */
	void saveData();

}
