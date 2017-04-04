package com.aaron.spring.service.impl;


import com.aaron.spring.bean.UserBean;
import com.aaron.spring.repository.PaginationRepository;
import com.aaron.spring.service.PaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Pagination service
 *
 * @author Aaron CHang
 */
@Service
public class PaginationServiceImpl implements PaginationService {

	@Autowired
	private PaginationRepository paginationRepository;

	@Transactional
	@Override
	public Page<UserBean> findAllPageable(String name, Pageable pageable) {
		Page<UserBean> listByName=paginationRepository.findByName(name, pageable);

		return paginationRepository.findByName(name, pageable);
	}

	/**
	 * simulate data and store in db for pagination
	 */
	@Override
	public void saveData() {
		List<UserBean> userBeans =new ArrayList<>();

		for(int i=1;i<=500;i++){
			userBeans.add(new UserBean("Aaron"+i, "test@test.com", i));
		}

		paginationRepository.save(userBeans);
	}

}
