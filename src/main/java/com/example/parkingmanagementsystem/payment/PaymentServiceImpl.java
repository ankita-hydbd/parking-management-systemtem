//package com.example.parkingmanagementsystem.payment;
//
//
//
//import com.example.parkingmanagementsystem.parking.rate.ParkingRate;
//import com.example.parkingmanagementsystem.parking.rate.ParkingRateRepository;
//import com.example.parkingmanagementsystem.parking.rate.ParkingRateService;
//import com.example.parkingmanagementsystem.parking.rate.exception.ParkingRateNotFoundException;
//import com.example.parkingmanagementsystem.payment.exception.PaymentNotFoundException;
//import lombok.SneakyThrows;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//
//public class PaymentServiceImpl implements PaymentService {
//    private PaymentRepository paymentRepository;
//
//    public PaymentServiceImpl(PaymentRepository paymentRepository) {
//        this.paymentRepository = paymentRepository;
//    }
//
//
//    @Override
//    public String createPayment(Payment payment) {
//        paymentRepository.save(payment);
//        return "successfully created";
//    }
//
//    @Override
//    public String updatePayment(Payment payment) {
//        paymentRepository.save(payment);
//        return "successfully updated";
//    }
//
//    @Override
//    public String deletePayment(String amount) {
//       paymentRepository.deleteById(amount);
//        return "successfully deleted";
//    }
//
//    @SneakyThrows
//    @Override
//    public Payment getPayment(String amount)  {
//        if(paymentRepository.findById(amount).isEmpty())
//            throw new PaymentNotFoundException("ParkingRate detail doesn't exist");
//
//        return  paymentRepository.findById(amount).get();
//    }
//
//    @Override
//    public List<Payment> getAllPayment() {
//        return paymentRepository.findAll();
//    }
//}
