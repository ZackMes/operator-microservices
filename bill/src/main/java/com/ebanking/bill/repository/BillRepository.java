package com.ebanking.bill.repository;

import java.util.List;

import com.ebanking.bill.entity.Bill;

import org.springframework.data.repository.CrudRepository;

public interface BillRepository extends CrudRepository<Bill, Long> {
    List<Bill> findByPaymentCodeAndClientId(String paymentCode, String clientId);
    Bill findByIdAndPaymentCode(Long id, String paymentCode);
}