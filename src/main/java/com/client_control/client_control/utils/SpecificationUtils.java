package com.client_control.client_control.utils;

import com.client_control.client_control.entities.Client;
import com.client_control.client_control.entities.User;
import com.client_control.client_control.entities.UserRole;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationUtils {

    public static Specification SpecificationRole(Specification specificationDTO, User user ){

        Specification specification = specificationDTO;

        if(user.getRole() != UserRole.ADMIN){
            Specification userSpec = (root, query, cb) -> cb.equal(root.get("user").get("id"), user.getId());
            specification = (specificationDTO == null) ? userSpec : userSpec.and(specificationDTO);
        }

        return  specification;
    }
}
