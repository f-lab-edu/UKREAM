package com.ukream.mapper;

import com.ukream.dto.SalesAccountDTO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SalesAccountMapper {
  public void createSalesAccount(SalesAccountDTO salesAccount);

  public List<SalesAccountDTO> getSalesAccounts(Long userId);

  public SalesAccountDTO getSalesAccount(Long salesAccountId, Long userId);

  public int deleteSalesAccount(Long salesAccountId);

  public int updateSalesAccount(SalesAccountDTO salesAccount);
}
