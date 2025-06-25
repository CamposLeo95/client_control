package com.client_control.client_control.specifications;

import com.client_control.client_control.entities.Payment;
import net.kaczmarzyk.spring.data.jpa.domain.Between;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@And({
        @Spec(path = "client.name", params ={"client"}, spec = LikeIgnoreCase.class),
        @Spec(path = "createdAt", params = {"startDate", "endDate"}, spec = Between.class, config = "yyyy-MM-dd")
})
public interface SpecificationPaymentTemplate  extends Specification<Payment> {}
