package com.k9sv.util;

/**
 * 分页组件
 * 
 * @author wuyuqi
 * 
 * @param
 */
public class Page {

	private int start;
	// ---------------------------------------------------------
	// static variables
	// ---------------------------------------------------------

	// 默认每页显示条数
	public static final int DEFAULT_MAX_PAGE_ITEMS = 10;

	// 默认开始记录条数
	public static final int DEFAULT_OFFSET = 0;

	// 默认显示页码最大数
	public static final int DEFAULT_MAX_INDEX_PAGES = 5;

	// 默认分页显示的最多页数
	public static final int DEFAULT_MAX_PAGE_INDEX_NUMBER = 10;

	// index 为索引页位置, 可以选择 "center", "forward", "half-full"
	public static final String DEFALUT_INDEX = "center";

	// ---------------------------------------------------------
	// memeber variables
	// ---------------------------------------------------------

	// 开始记录条数
	private int offset = 0;

	// 每页显示条数
	private int maxPageItems = DEFAULT_MAX_PAGE_ITEMS;

	// 显示页码最大数
	private int maxIndexPages = DEFAULT_MAX_INDEX_PAGES;

	// 分页显示的最多页数
	private int maxPageIndexNumber = DEFAULT_MAX_PAGE_INDEX_NUMBER;

	// 总记录数
	private int totalCount;

	// 总页数
	private int totalPage;

	// 当前页
	private int currentPage;

	// 页数数组
	private int[] pages = new int[0];

	private int[] indexes = new int[0];

	public Page() {
		setMaxIndexPages(DEFAULT_MAX_INDEX_PAGES);
		setTotalCount(0);
		setOffset(0);
	}

	public Page(int totalCount) {
		setMaxPageItems(DEFAULT_MAX_PAGE_ITEMS);
		setTotalCount(totalCount);
		setOffset(PageFacade.getOffset());
	}

	public Page(int totalCount, int offset) {
		setMaxPageItems(DEFAULT_MAX_PAGE_ITEMS);
		setTotalCount(totalCount);
		setOffset(offset);
	}

	public Page(int totalCount, int offset, int maxPageItems) {
		setMaxPageItems(maxPageItems);
		setTotalCount(totalCount);
		setOffset(offset);
	}

	public Page(int totalCount, int offset, int maxPageItems, int currentPage) {
		setMaxPageItems(maxPageItems);
		setTotalCount(totalCount);
		setOffset(offset);
		setCurrentPage(currentPage);
	}

	public Page(int totalCount, int offset, int maxPageItems, int currentPage,
			int maxPageIndexNumber) {
		setMaxPageItems(maxPageItems);
		setTotalCount(totalCount);
		setMaxPageIndexNumber(maxPageIndexNumber);
		setCurrentPage(currentPage);
		setOffset(offset);
	}

	public int getMaxPageItems() {
		return maxPageItems;
	}

	public void setMaxPageItems(int maxPageItems) {
		this.maxPageItems = maxPageItems;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		if (totalCount > 0) {
			this.totalCount = totalCount;
			int count = totalCount / maxPageItems;
			if (totalCount % maxPageItems > 0) {
				count++;
			}
			this.totalPage = count;
			indexes = new int[count];
			for (int i = 0; i < count; i++) {
				indexes[i] = maxPageItems * i;
			}
		} else {
			this.totalCount = 0;
		}
	}

	public int[] getPages() {
		return pages;
	}

	public void setPages(int[] pages) {
		this.pages = pages;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int startIndex) {
		if (totalCount <= 0)
			this.offset = 0;
		else if (startIndex >= totalCount)
			this.offset = indexes[indexes.length - 1];
		else if (startIndex < 0)
			this.offset = 0;
		else {
			this.offset = indexes[startIndex / maxPageItems];
		}
		// this.currentPage = offset/maxPageItems + 1;
		// / System.out.println("maxPageIndexNumber:"+maxPageIndexNumber);
		// System.out.println("currentPage:"+maxPageIndexNumber);
		if (totalPage > maxPageIndexNumber) {
			pages = new int[maxPageIndexNumber];
			// pages[0] = 1;
			// pages[0]=this.currentPage - maxPageIndexNumber/2 + 1;
			if (this.currentPage < maxPageIndexNumber / 2) {
				for (int i = 0; i < maxPageIndexNumber; i++) {
					pages[i] = i + 1;
				}
				// pages[maxPageIndexNumber-2] = 0;
			} else if (this.currentPage > maxPageIndexNumber / 2
					&& this.currentPage < (totalPage - maxPageIndexNumber / 2)) {
				// int tmp = (maxPageIndexNumber-2)/2;
				// pages[1] = 0;
				int tmp = maxPageIndexNumber / 2;
				for (int i = 0; i < maxPageIndexNumber; i++) {
					pages[i] = currentPage - tmp + i;
					// pages[i] = currentPage - tmp + i;
				}
				// pages[maxPageIndexNumber-2] = 0;
			} else if (this.currentPage == maxPageIndexNumber / 2) {
				for (int i = 0; i < maxPageIndexNumber; i++) {
					pages[i] = i + 1;
				}
			} else if (this.currentPage >= (totalPage - maxPageIndexNumber / 2)) {
				int tmp = totalPage - maxPageIndexNumber;
				for (int i = 0; i < maxPageIndexNumber; i++) {
					pages[i] = tmp + i;
				}
			}
			// pages[maxPageIndexNumber-1] = totalPage;
		} else {
			pages = new int[totalPage];
			for (int i = 0; i < totalPage; i++) {
				pages[i] = i + 1;
			}
		}
	}

	public int getNextIndex() {
		int nextIndex = getOffset() + maxPageItems;
		return nextIndex >= totalCount ? getOffset() : nextIndex;
	}

	public int getPreviousIndex() {
		int previousIndex = getOffset() - maxPageItems;
		return previousIndex < 0 ? DEFAULT_OFFSET : previousIndex;
	}

	/**
	 * @return Returns the dEFAULT_MAX_INDEX_PAGES.
	 */
	public static int getDEFAULT_MAX_INDEX_PAGES() {
		return DEFAULT_MAX_INDEX_PAGES;
	}

	public int getMaxIndexPages() {
		return maxIndexPages;
	}

	public void setMaxIndexPages(int maxIndexPages) {
		this.maxIndexPages = maxIndexPages;
	}

	public int getMaxPageIndexNumber() {
		return maxPageIndexNumber;
	}

	public void setMaxPageIndexNumber(int maxPageIndexNumber) {
		this.maxPageIndexNumber = maxPageIndexNumber;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int[] getIndexes() {
		return indexes;
	}

	public void setIndexes(int[] indexes) {
		this.indexes = indexes;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	/**
	 * @param args
	 * @throws Exception
	 * @author wuyuqi
	 */
	public static void main(String[] args) throws Exception {
		Page ps = new Page(6, 0, 2, 1, 8);
		int[] pages = ps.getPages();
		System.out.println("pagesLength:" + pages.length);
		for (int i = 0; i < pages.length; i++) {
			if (pages[i] == ps.getCurrentPage()) {
				System.out.println("curentPage:" + pages[i]);
			} else {
				System.out.println(i + ":" + pages[i]);
			}

		}
		System.out.println("TotalPages:" + ps.getTotalPage());
		System.out.println("pages:" + ps.getPages().length);
	}
}
