//package com.example.parkingmanagementsystem.payment;
//
//
//import com.example.parkingmanagementsystem.parking.rate.ParkingRate;
//import com.example.parkingmanagementsystem.parking.rate.ParkingRateService;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/Payment")
//@Log4j2
//
//public class PaymentController {
//    PaymentService paymentService;
//
//    public PaymentController(PaymentService paymentService) {
//        this.paymentService = paymentService;
//    }
//
//
//    @GetMapping("/{amount}")
//    public Payment getPayment(@PathVariable("amount") String amount) {
//        log.info("Received hourlyRate = {}",amount);
//        return paymentService.getPayment(amount);
//    }
//
//    @GetMapping()
//    public List<Payment> getAllPayment() {
//        return paymentService.getAllPayment();
//    }
//
//    @PostMapping
//    public String createPayment(@RequestBody Payment payment) {
//        log.info("Received amount details = {}", payment);
//      paymentService.createPayment(payment);
//        return "amount created successfully";
//    }
//
//    @PutMapping
//    public String updatePayment(@RequestBody Payment payment) {
//        paymentService.updatePayment(payment);
//        return "amount updated successfully";
//    }
//
//    @DeleteMapping("{amount}")
//    public String deletePayment(@PathVariable("amount") String amount) {
//     paymentService.deletePayment(amount);
//
//        return "amount deleted successfully";
//    }
//}
