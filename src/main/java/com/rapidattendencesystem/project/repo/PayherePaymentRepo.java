package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.PayherePayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayherePaymentRepo extends JpaRepository<PayherePayment,Integer> {
    PayherePayment findByReceiptNumber(String reciptNumber);
}
