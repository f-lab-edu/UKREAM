package com.ukream.service;

import com.ukream.dto.SalesAccountDTO;
import com.ukream.error.exception.SalesAccountNotFoundException;
import com.ukream.mapper.SalesAccountMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalesAccountService {
  private final SalesAccountMapper salesAccountMapper;

  public void createSalesAccount(SalesAccountDTO salesAccount) {
    salesAccountMapper.createSalesAccount(salesAccount);
  }

  public List<SalesAccountDTO> getSalesAccounts(Long userId) {
    return salesAccountMapper.getSalesAccounts(userId);
  }

  public SalesAccountDTO getSalesAccount(Long salesAccountId, Long userId) {
    SalesAccountDTO salesAccount = salesAccountMapper.getSalesAccount(salesAccountId, userId);
    if (salesAccount == null) {
      throw new SalesAccountNotFoundException();
    }
    return salesAccount;
  }

  public void deleteSalesAccount(Long salesAccountId) {
    int deletedRows = salesAccountMapper.deleteSalesAccount(salesAccountId);
    if (deletedRows == 0) {
      throw new SalesAccountNotFoundException();
    }
  }

  public void updateSalesAccount(SalesAccountDTO salesAccount) {
    int updatedRows = salesAccountMapper.updateSalesAccount(salesAccount);
    if (updatedRows == 0) {
      throw new SalesAccountNotFoundException();
    }
  }
}
