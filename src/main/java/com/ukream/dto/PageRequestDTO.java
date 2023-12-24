package com.ukream.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import org.apache.ibatis.session.RowBounds;



@Getter
@Builder
@AllArgsConstructor
public class PageRequestDTO {

  private int page;
  private int size;

  public RowBounds getRowBounds() {
    return new RowBounds((page - 1) * size, size);
  }

  public PageRequestDTO() {
    this.page = 1;
    this.size = 10;
  }


  public void setPage(int page) {
    this.page = (page <= 0) ? 1 : page;
  }


  public void setSize(int size) {
    this.size = (size <= 0) ? 10 : size;
  }
}
