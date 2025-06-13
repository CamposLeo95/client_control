package com.client_control.client_control.services;
import com.client_control.client_control.dtos.payment.PaymentRequestDTO;
import com.client_control.client_control.entities.Payment;
import com.client_control.client_control.entities.Sign;
import com.client_control.client_control.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.time.LocalDate;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final ClientService clientService;
    private final ServiceOfferingService serviceOfferingService;
    private final SignService signService;

    public PaymentService(
            PaymentRepository paymentRepository,
            ServiceOfferingService serviceOfferingService,
            ClientService clientService,
            SignService signService
    )
    {
        this.paymentRepository = paymentRepository;
        this.signService = signService;
        this.clientService = clientService;
        this.serviceOfferingService = serviceOfferingService;
    }

    public void createPayment(PaymentRequestDTO dto){

        LocalDate dateNow = LocalDate.now();

        if(dto.sign_id() != null){
            var sign = signService.findSignById(dto.sign_id());

            var serviceOffering = serviceOfferingService.findServiceOfferingById(sign.getServiceOffering().getId());

            var totalMonths = dto.value().divide(serviceOffering.getPrice(), 0, RoundingMode.DOWN).intValue();

            var newExpireDate = sign.getExpireDate().plusMonths(totalMonths);

            signService.updateSign(sign, newExpireDate);


            var payment = new Payment(
                    dto.value(),
                    dto.description(),
                    sign.getClient(),
                    sign
            );

            paymentRepository.save(payment);

        } else {
            var client = clientService.findClientById(dto.client_id());
            var serviceOffering = serviceOfferingService.findServiceOfferingById(dto.serviceOffering_id());

            if(dto.value().compareTo(serviceOffering.getPrice()) < 0){
                throw new RuntimeException("Valor inferior ao preço do serviço");
            }

            var totalMonths = dto.value().divide(serviceOffering.getPrice(), 0, RoundingMode.DOWN).intValue();

            var expireDate = dateNow.plusMonths(totalMonths);

            var sign = signService.createSign( new Sign(
                    true,
                    expireDate,
                    client,
                    serviceOffering
            ));

            var payment = new Payment(
                    dto.value(),
                    dto.description(),
                    client,
                    sign
            );

            paymentRepository.save(payment);
        }

    }
}
