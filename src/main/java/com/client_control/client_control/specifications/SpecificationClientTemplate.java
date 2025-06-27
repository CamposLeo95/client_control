package com.client_control.client_control.specifications;

import com.client_control.client_control.entities.Client;

import net.kaczmarzyk.spring.data.jpa.domain.Between;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Conjunction;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@And({
        @Spec(path = "name", spec = LikeIgnoreCase.class),

})

@Conjunction(value ={
        @Or({
                @Spec(path = "name", params = "all", spec = LikeIgnoreCase.class),
                @Spec(path = "email", params = "all", spec = LikeIgnoreCase.class),
                @Spec(path = "phone", params ="all", spec = LikeIgnoreCase.class  )
        }),
}, and = @Spec(path = "createdAt", params = {"startDate", "endDate"}, spec = Between.class, config = "yyyy-MM-dd")  )

public interface SpecificationClientTemplate extends Specification<Client> {}
