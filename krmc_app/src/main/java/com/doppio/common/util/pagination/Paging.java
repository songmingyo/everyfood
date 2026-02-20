package com.doppio.common.util.pagination;

public abstract interface Paging{
	
  public abstract Integer getLast();

  public abstract Integer getStart();

  public abstract Integer getTotalCount();

  public abstract Integer getNext();

  public abstract Integer getCurrent();

  public abstract StyleSheet[] getList();

  public abstract Integer getFirst();

  public abstract Integer getEnd();

  public abstract Integer getPrev();
  
  public abstract int getStartRowNum();
  
  public abstract int getEndRowNum();
  
  public abstract int getPageCount();
}
