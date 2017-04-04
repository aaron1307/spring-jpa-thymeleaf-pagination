package com.aaron.spring.controller;

import com.aaron.spring.bean.Pager;
import com.aaron.spring.bean.UserBean;
import com.aaron.spring.service.PaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Pagination controller
 *
 * @author Aaron CHang
 */
@Controller
public class PaginationController {

	@Autowired
	private PaginationService personService;

	/**
	 * Get method, redirects to list page
	 * calculate pagination values and stored
	 * in model
	 * 
	 * @param pageSize
	 * @param page
	 * @param search
	 * @return String, to Thymeleaf page name under templates
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showPersonsPage(@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "page", required = false) Integer page
			, @RequestParam(value = "search", required = false) String search
			, Model model) {

		//calculate current page size
		int selectedPageSize = Pager.calculateSelectedPageSize(pageSize);
		//calculate current page number
		int currentPage = Pager.calculateCurrentPage(page);

		//List result
		Page<UserBean> list=null;

		if(search==null||"".equals(search)){
			//if no search text defined, simply returns empty Page result
			list = Pager.getEmptyPageResult(new UserBean());
		}else{
			//search matches data in Service
			list = personService.findAllPageable(search, new PageRequest(currentPage, selectedPageSize));
		}

		//initialize pagination helper class
		Pager pager = new Pager(list.getTotalPages(), list.getNumber(), Pager.getButtonRange(), selectedPageSize);

		model.addAttribute("list", list);
		model.addAttribute("pager", pager);
		model.addAttribute("search", search);

		return "list";
	}

	/**
	 * create data in database for pagination
	 *
	 * @return String, to redirect url "/"
	 */
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public String createDatabase(){
		personService.saveData();

		return "redirect:/";
	}

}
