package com.client_control.client_control.services;
import com.client_control.client_control.dtos.payment.PaymentRequestDTO;
import com.client_control.client_control.dtos.payment.PaymentResponseDTO;
import com.client_control.client_control.entities.Client;
import com.client_control.client_control.entities.Payment;
import com.client_control.client_control.entities.Sign;
import com.client_control.client_control.entities.User;
import com.client_control.client_control.exceptions.BusinessException;
import com.client_control.client_control.exceptions.ResourceNotFoundException;
import com.client_control.client_control.mappers.PaymentMapper;
import com.client_control.client_control.repositories.PaymentRepository;
import com.client_control.client_control.utils.SpecificationUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final ClientService clientService;
    private final ServiceOfferingService serviceOfferingService;
    private final SignService signService;
    private final UserService userService;

    public PaymentService(
            PaymentRepository paymentRepository,
            ServiceOfferingService serviceOfferingService,
            ClientService clientService,
            SignService signService,
            UserService userService

    )
    {
        this.paymentRepository = paymentRepository;
        this.signService = signService;
        this.clientService = clientService;
        this.serviceOfferingService = serviceOfferingService;
        this.userService = userService;
    }

    public void createPayment(PaymentRequestDTO dto){
        User user = userService.mySelf();
        LocalDate dateNow = LocalDate.now();

        if(dto.sign_id() != null){
            LocalDate newExpireDate = dateNow;
            var sign = signService.findSignById(dto.sign_id());

            var serviceOffering = serviceOfferingService.findServiceOfferingById(sign.getServiceOffering().getId());

            var totalMonths = dto.value().divide(serviceOffering.getPrice(), 0, RoundingMode.DOWN).intValue();

            if(dto.manual_date() != null){
                newExpireDate = dto.manual_date().plusMonths(totalMonths);
            }

            if(dto.manual_date() == null) {
                 newExpireDate = sign.getExpireDate().isBefore(dateNow)
                        ? dateNow.plusMonths(totalMonths)
                        : sign.getExpireDate().plusMonths(totalMonths);
            }

            signService.updateSign(sign, newExpireDate);

            var payment = new Payment(
                    dto.value(),
                    dto.description(),
                    sign.getClient(),
                    sign,
                    user
            );

            paymentRepository.save(payment);

        } else {
            var client = clientService.findClientById(dto.client_id());
            var serviceOffering = serviceOfferingService.findServiceOfferingById(dto.serviceOffering_id());
            LocalDate expireDate = LocalDate.now();
            if(dto.value().compareTo(serviceOffering.getPrice()) < 0){
                throw new BusinessException("Valor inferior ao preço do serviço");
            }

            var totalMonths = dto.value().divide(serviceOffering.getPrice(), 0, RoundingMode.DOWN).intValue();

            System.out.println(dto.manual_date());

            if(dto.manual_date() != null){
                expireDate = dto.manual_date().plusMonths(totalMonths);
            } else {
                expireDate = dateNow.plusMonths(totalMonths);
            }

            Sign sign = signService.createSign(new Sign(
                    true,
                    expireDate,
                    client,
                    serviceOffering,
                    user
            ));

            var payment = new Payment(
                    dto.value(),
                    dto.description(),
                    client,
                    sign,
                    user
            );

            paymentRepository.save(payment);
        }

    }

    public List<PaymentResponseDTO> findAllPayments(Specification<Payment> specificationDto, Pageable pageable){
        User user = userService.mySelf();
        Specification<Payment> specification = SpecificationUtils.SpecificationRole(specificationDto, user);

        return paymentRepository.findAll(specification, pageable)
                .stream()
                .map(PaymentMapper::toResponseDTO)
                .toList();
    }

    public PaymentResponseDTO findPaymentById(UUID id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Pagamento não encontrado!")
        );

        return PaymentMapper.toResponseDTO(payment);
    }
}
