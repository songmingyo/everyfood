package com.doppio.common.util.pagination;


public class PagingFactory {
	
	
	
	public static Paging makePagingObject(int totalCount, int blockSize,int listCount, int current) {
		
		return new DefaultPaging(totalCount, blockSize, listCount, current);
		
	}
	

}
