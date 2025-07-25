package com.client_control.client_control.specifications;

import com.client_control.client_control.entities.Payment;
import net.kaczmarzyk.spring.data.jpa.domain.Between;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Conjunction;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@And({
        @Spec(path = "createdAt", params = {"startDate", "endDate"}, spec = Between.class, config = "yyyy-MM-dd")
})

@Conjunction(value ={
        @Or({
                @Spec(path = "client.name", params = "all", spec = LikeIgnoreCase.class),
                @Spec(path = "client.email", params = "all", spec = LikeIgnoreCase.class),
                @Spec(path = "client.login", params ="all", spec = LikeIgnoreCase.class),
                @Spec(path = "client.phone", params = "all", spec = LikeIgnoreCase.class)
        })
})
public interface SpecificationPaymentTemplate  extends Specification<Payment> {}
