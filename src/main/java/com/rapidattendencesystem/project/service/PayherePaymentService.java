package com.rapidattendencesystem.project.service;

import com.rapidattendencesystem.project.dto.HallDTO;
import com.rapidattendencesystem.project.dto.PayherePaymentDTO;
import com.rapidattendencesystem.project.entity.Hall;
import com.rapidattendencesystem.project.entity.PayherePayment;
import com.rapidattendencesystem.project.repo.ParentRepo;
import com.rapidattendencesystem.project.repo.PayherePaymentRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class PayherePaymentService {

    @Autowired
    private PayherePaymentRepo payherePaymentRepo;

    @Autowired
    private ModelMapper modelmapper;

    public PayherePaymentDTO createPayherePayment(PayherePaymentDTO payherePaymentDTO){
        try {
            PayherePayment p1 = modelmapper.map(payherePaymentDTO, PayherePayment.class);
            if(payherePaymentRepo.existsById(p1.getId())){
                System.out.println("payment exist");
                return modelmapper.map(p1, PayherePaymentDTO.class);
            }else{
                PayherePayment p2 = payherePaymentRepo.save(p1);
                System.out.println(p2);
                return modelmapper.map(p2, PayherePaymentDTO.class);
            }
        }catch (Exception e){
            return payherePaymentDTO;
        }
    }

    public PayherePaymentDTO veryfYPayment(String reciptNumber){
        try {
            PayherePayment p2 = payherePaymentRepo.findByReceiptNumber(reciptNumber);
            System.out.println(p2);
            return modelmapper.map(p2, PayherePaymentDTO.class);
        }catch (Exception e){
            return null;
        }
    }
}
