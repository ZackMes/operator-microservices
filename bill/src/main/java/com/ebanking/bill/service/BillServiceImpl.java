package com.ebanking.bill.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.ebanking.bill.entity.Bill;
import com.ebanking.bill.repository.BillRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillRepository billRepository;

    @Override
    public List<Bill> retrieveAllBills(){
        return (List<Bill>) billRepository.findAll();
    }

    @Override
    public Optional<Bill> retreiveBillById(Long id){
        return (Optional<Bill>) billRepository.findById(id);
    }

    @Override
    public List<Bill> retrieveBillByPaymentCodeAndClientId(String paymentCode, String clientId) {
        return (List<Bill>) billRepository.findByPaymentCodeAndClientId(paymentCode, clientId);
    }

    @Override
    public Bill payBillByIdAndPaymentCode(Long id, String paymentCode) {
        Bill bill = (Bill) billRepository.findByIdAndPaymentCode(id, paymentCode);
        if(bill.getPaymentStatus()){
            return null;
        }
        bill.setPaymentStatus(true);
        bill.setPaymentDate(new Date());
        return billRepository.save(bill);
    }
}