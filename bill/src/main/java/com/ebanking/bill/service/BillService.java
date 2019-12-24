package com.ebanking.bill.service;

import java.util.List;
import java.util.Optional;

import com.ebanking.bill.entity.Bill;

public interface BillService {
    List<Bill> retrieveAllBills();
    Optional<Bill> retreiveBillById(Long id);
    List<Bill> retrieveBillByPaymentCodeAndClientId(String paymentCode, String clientId);
    Bill payBillByIdAndPaymentCode(Long id, String paymentCode);
}