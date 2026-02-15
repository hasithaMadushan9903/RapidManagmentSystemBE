package com.rapidattendencesystem.project.controller;

import com.rapidattendencesystem.project.config.PayHereConfig;
import com.rapidattendencesystem.project.dto.PayHereInitRequest;
import com.rapidattendencesystem.project.dto.PayHereInitResponse;
import com.rapidattendencesystem.project.dto.PayherePaymentDTO;
import com.rapidattendencesystem.project.dto.ResponseDTO;
import com.rapidattendencesystem.project.service.PayherePaymentService;
import com.rapidattendencesystem.project.util.PayHereHashUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/payherectrl")
@CrossOrigin("*")
public class PayHereController {

    @Autowired
    private PayHereConfig payHereConfig;

    @Autowired
    private PayherePaymentService payherePaymentService;

    @Autowired
    private ResponseDTO responseDTO;

    @GetMapping("/init/{orderid}/{amount}/{currency}")
    public ResponseEntity<ResponseDTO> initPayment(@PathVariable String orderid, @PathVariable double amount, @PathVariable String currency){
        try{
            String amountFormatted = String.format("%.2f", amount);

            String hash = PayHereHashUtil.generateHash(
                    payHereConfig.getMerchantId(),
                    payHereConfig.getMerchantSecret(),
                    orderid,
                    amountFormatted,
                    currency
            );
            PayHereInitResponse response = new PayHereInitResponse();
            response.setMerchantId(payHereConfig.getMerchantId());
            response.setOrderId(orderid);
            response.setAmount(amountFormatted);
            response.setCurrency(currency);
            response.setHash(hash);

            responseDTO.setCode("00");
            responseDTO.setMassage("payehere");
            responseDTO.setContent(response);
            return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
        }catch (Exception e){
            responseDTO.setCode("02");
            responseDTO.setContent(null);
            responseDTO.setMassage(e.getMessage());
            return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/veryfypayment/{orderid}")
    public ResponseEntity<ResponseDTO> veryfyPayment(@PathVariable String orderid){
        try{
            PayherePaymentDTO p2 = payherePaymentService.veryfYPayment(orderid);
            responseDTO.setCode("00");
            responseDTO.setMassage("payehere");
            responseDTO.setContent(p2);
            return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
        }catch (Exception e){
            responseDTO.setCode("02");
            responseDTO.setContent(null);
            responseDTO.setMassage(e.getMessage());
            return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/notify")
    public ResponseEntity<ResponseDTO> notifyPayment(@org.jetbrains.annotations.NotNull HttpServletRequest request) {
        String orderId = request.getParameter("order_id");
        String statusCode = request.getParameter("status_code");

        PayherePaymentDTO p1 = new PayherePaymentDTO();
        p1.setReceiptNumber(orderId);
        p1.setStatus(statusCode);

        try{
            PayherePaymentDTO p2 = payherePaymentService.createPayherePayment(p1);
            if(p2.getId()>0){
                responseDTO.setCode("00");
                responseDTO.setMassage("Payment Inserted");
                responseDTO.setContent(p2);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }else{
                responseDTO.setCode("01");
                responseDTO.setMassage("Bad Request");
                responseDTO.setContent(p1);
                return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.OK);
            }
        }
        catch (Exception e){
            responseDTO.setCode("02");
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
