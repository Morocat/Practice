package com.msi;

import java.util.List;

public class PaginationHelper<I> {
	
	private final List<I> list;
	private final int itemsPerPage;
	
	/**
	   * The constructor takes in an array of items and a integer indicating how many
	   * items fit within a single page
	   */
	  public PaginationHelper(List<I> collection, int itemsPerPage) {
		  list = collection;
		  this.itemsPerPage = itemsPerPage;
	  }
	  
	  /**
	   * returns the number of items within the entire collection
	   */
	  public int itemCount() {
		  return list.size();
	  }
	  
	  /**
	   * returns the number of pages
	   */
	  public int pageCount() {
		  return itemCount() / itemsPerPage + (itemCount() % itemsPerPage > 0 ? 1 : 0);
	  }
	  
	  /**
	   * returns the number of items on the current page. page_index is zero based.
	   * this method should return -1 for pageIndex values that are out of range
	   */
	  public int pageItemCount(int pageIndex) {
		  if (pageIndex < 0 || pageIndex > pageCount() - 1)
			  return -1;
		  if (pageIndex != pageCount() - 1)
			  return itemsPerPage;
		  return itemCount() % itemsPerPage;
	  }
	  
	  /**
	   * determines what page an item is on. Zero based indexes
	   * this method should return -1 for itemIndex values that are out of range
	   */
	  public int pageIndex(int itemIndex) {
		  if (itemIndex < 0 || itemIndex > itemCount())
			  return -1;
		  int items;
		  for (int i = 0; i < pageCount(); i++) {
			  items = (i + 1) * itemsPerPage - 1;
			  if (items > itemIndex)
				  return i;
		  }
		  return -1;
	  }
	  
	  public void runTests() {
		  System.out.println(pageCount()); //should == 2
		  System.out.println(itemCount()); //should == 6
		  System.out.println(pageItemCount(0)); //should == 4
		  System.out.println(pageItemCount(1)); // last page - should == 2
		  System.out.println(pageItemCount(2)); // should == -1 since the page is invalid
		  System.out.println();
		  // pageIndex takes an item index and returns the page that it belongs on
		  System.out.println(pageIndex(5)); //should == 1 (zero based index)
		  System.out.println(pageIndex(2)); //should == 0
		  System.out.println(pageIndex(20)); //should == -1
		  System.out.println(pageIndex(-10)); //should == -1
	  }
	  
}
