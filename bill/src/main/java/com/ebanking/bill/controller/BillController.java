package com.ebanking.bill.controller;

import java.util.List;
import java.util.Optional;

import com.ebanking.bill.entity.Bill;
import com.ebanking.bill.service.BillService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BillController {

    private BillService billService;

    @Autowired
    public void setBillService(BillService billService) {
        this.billService = billService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Bill>> getAllBills(){
        List<Bill> bills = billService.retrieveAllBills();
        if(bills.isEmpty()){
            return new ResponseEntity<List<Bill>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Bill>>(bills, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Bill>> getBillById(@PathVariable Long id){
        Optional<Bill> bill = billService.retreiveBillById(id);
        return new ResponseEntity<Optional<Bill>>(bill, HttpStatus.OK);
    }

    @GetMapping("/{paymentCode}/{clientId}")
    public ResponseEntity<List<Bill>> getBillByIdAndPaymentCode(@PathVariable String paymentCode, @PathVariable String clientId){
        List<Bill> bills = billService.retrieveBillByPaymentCodeAndClientId(paymentCode, clientId);
        if(bills.isEmpty()){
            return new ResponseEntity<List<Bill>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Bill>>(bills, HttpStatus.OK);
    }

    @GetMapping("/pay/{id}/{paymentCode}")
    public ResponseEntity<Bill> payBill(@PathVariable Long id, @PathVariable String paymentCode) {
        Bill bill = billService.payBillByIdAndPaymentCode(id, paymentCode);
        if(bill == null){
            return new ResponseEntity<Bill>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Bill>(bill, HttpStatus.OK);
    }
}