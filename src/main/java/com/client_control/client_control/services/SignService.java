package com.client_control.client_control.services;

import com.client_control.client_control.dtos.sign.SignUpdateDescriptionDTO;
import com.client_control.client_control.dtos.sign.SignResponseDTO;
import com.client_control.client_control.entities.Sign;
import com.client_control.client_control.entities.User;
import com.client_control.client_control.exceptions.ResourceNotFoundException;
import com.client_control.client_control.mappers.SignMapper;
import com.client_control.client_control.repositories.SignRepository;
import com.client_control.client_control.utils.SpecificationUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SignService {

    private final SignRepository signRepository;
    private final UserService userService;

    public SignService(SignRepository signRepository, UserService userService) {
        this.signRepository = signRepository;
        this.userService = userService;
    }

    public Sign findSignById(Long id){
        return signRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Assinatura nao encontrada!")
        );
    }


    public List<SignResponseDTO> findAllSign(Specification<Sign> specificationDto, Pageable pageable) {
        User user = userService.mySelf();
        Specification<Sign> specification = SpecificationUtils.SpecificationRole(specificationDto, user);

        return signRepository.findAll(specification, pageable)
                .stream()
                .map(SignMapper::toResponseDTO)
                .toList();
    }

    public Sign createSign(Sign sign){
        return signRepository.save(sign);
    }

    public void updateSign(Sign sign, LocalDate expireDate) {
        var currentSign = signRepository.findById(sign.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Assinatura nao encontrada!")
        );

        if(expireDate != null){
            currentSign.setExpireDate(expireDate);
        }

        signRepository.save(currentSign);

    }

    public void toggleStatusSign(Long id){
        Sign sign = findSignById(id);

        sign.setActiveSign(!sign.isActiveSign());
        signRepository.save(sign);

    }

    public void updateSignDescription(SignUpdateDescriptionDTO dto, Long id) {
        Sign signDB = findSignById(id);
        signDB.setDescription(dto.description());
        signRepository.save(signDB);
    }
}
