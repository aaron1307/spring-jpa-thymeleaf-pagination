package com.aaron.spring.bean;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/**
 * Pagination helper class,
 * generate buttons to show in pagination navigation,
 * default button range to show is 5,
 *
 * 
 * @author Aaron Chang
 */
public class Pager implements Serializable{

	//default button range to show
	private int buttonsToShow = 5;

	//start page no to show
	private int startPage;

	//end page number to show
	private int endPage;

	//selected page size
	private int selectedPageSize;

	//initial page no
	private static final int INITIAL_PAGE = 0;

	//initial page size
	private static final int INITIAL_PAGE_SIZE = 5;

	//default button range no
	private static final int PAGINATION_BUTTON_RANGE = 5;

	//page size to select
	private static final int[] PAGE_SIZES = { 5, 10, 20 };

	public Pager(int totalPages, int currentPage, int buttonsToShow, int selectedPageSize) {

		//set buttons range to show
		setButtonRange(buttonsToShow);

		this.selectedPageSize=selectedPageSize;

		//calculate pages to show
		int halfPagesToShow = getButtonsToShow() / 2;

		if (totalPages <= getButtonsToShow()) {
			setStartPage(1);
			setEndPage(totalPages);

		} else if (currentPage - halfPagesToShow <= 0) {
			setStartPage(1);
			setEndPage(getButtonsToShow());

		} else if (currentPage + halfPagesToShow == totalPages) {
			setStartPage(currentPage - halfPagesToShow);
			setEndPage(totalPages);

		} else if (currentPage + halfPagesToShow > totalPages) {
			setStartPage(totalPages - getButtonsToShow() + 1);
			setEndPage(totalPages);

		} else {
			setStartPage(currentPage - halfPagesToShow);
			setEndPage(currentPage + halfPagesToShow);
		}

	}

	public int getButtonsToShow() {
		return buttonsToShow;
	}

	public void setButtonRange(int buttonsToShow) {
		this.buttonsToShow=buttonsToShow;

		if (buttonsToShow % 2 != 0) {
			this.buttonsToShow = buttonsToShow;
		} else {
			throw new IllegalArgumentException("Illegal button number to show");
		}
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getSelectedPageSize() {
		return selectedPageSize;
	}

	public void setSelectedPageSize(int selectedPageSize) {
		this.selectedPageSize = selectedPageSize;
	}

	/**
	 * calculate current page no
	 * @param page
	 * @return calculated current page number
	 */
	public static int calculateCurrentPage(Integer page) {
		return (page == null || page < 1) ? INITIAL_PAGE : page - 1;
	}

	/**
	 * calculate current page size
	 * @param pageSize
	 * @return calculated current page size
	 */
	public static int calculateSelectedPageSize(Integer pageSize) {
		return pageSize == null ? INITIAL_PAGE_SIZE : pageSize;
	}

	public static int getButtonRange(){
		return PAGINATION_BUTTON_RANGE;
	}

	public static int[] getPageSizes(){
		return PAGE_SIZES;
	}


	/**
	 *
	 * @param t
	 * @param <T>
	 * @return empty Page result when no search text defined
	 */
	public static <T> Page<T> getEmptyPageResult(T t){
		return new Page<T>() {
			@Override
			public int getTotalPages() {
				return 0;
			}

			@Override
			public long getTotalElements() {
				return 0;
			}

			@Override
			public <S> Page<S> map(Converter<? super T, ? extends S> converter) {
				return null;
			}

			@Override
			public int getNumber() {
				return 0;
			}

			@Override
			public int getSize() {
				return 0;
			}

			@Override
			public int getNumberOfElements() {
				return 0;
			}

			@Override
			public List<T> getContent() {
				return null;
			}

			@Override
			public boolean hasContent() {
				return false;
			}

			@Override
			public Sort getSort() {
				return null;
			}

			@Override
			public boolean isFirst() {
				return false;
			}

			@Override
			public boolean isLast() {
				return false;
			}

			@Override
			public boolean hasNext() {
				return false;
			}

			@Override
			public boolean hasPrevious() {
				return false;
			}

			@Override
			public Pageable nextPageable() {
				return null;
			}

			@Override
			public Pageable previousPageable() {
				return null;
			}

			@Override
			public Iterator<T> iterator() {
				return null;
			}
		};
	}
}
