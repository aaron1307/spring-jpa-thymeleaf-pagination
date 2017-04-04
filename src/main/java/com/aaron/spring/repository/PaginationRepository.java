package com.aaron.spring.repository;

import com.aaron.spring.bean.UserBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Pagination repository
 *
 * @author Aaron CHang
 */
@Repository
public interface PaginationRepository extends PagingAndSortingRepository<UserBean, Long> {

    /**
     * retrive matches data by pages using jpa
     * self defined @Query to customize search
     *
     * @param name
     * @param pageable
     * @return Matched Page result
     */
    @Query(value = "SELECT u from UserBean u WHERE LOWER(u.name) LIKE CONCAT('%',LOWER(:name),'%') " +
            "or LOWER(u.email) like CONCAT('%',LOWER(:name),'%')",

            countQuery = "Select count(u.id) from UserBean u WHERE LOWER(u.name) LIKE CONCAT('%',LOWER(:name),'%') " +
                    "or LOWER(u.email) like CONCAT('%',LOWER(:name),'%')")
    Page<UserBean> findByName(@Param("name") String name, Pageable pageable);

}
