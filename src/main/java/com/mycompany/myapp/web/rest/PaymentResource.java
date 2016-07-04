package com.mycompany.myapp.web.rest;


import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.Paniershop;
import com.mycompany.myapp.domain.Payment;
import com.mycompany.myapp.repository.PaniershopRepository;
import com.mycompany.myapp.repository.PaymentRepository;
import com.mycompany.myapp.security.SecurityUtils;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class PaymentResource {
    @Inject
    private PaymentRepository paymentRepository;

    @RequestMapping(value = "/payment",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Payment> create(@Valid @RequestBody Payment payment) throws URISyntaxException {
        if (payment.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new Payment cannot already have an ID").body(null);
        }
        String currentLogin = SecurityUtils.getCurrentLogin();
        payment.setLogin(currentLogin);
        Payment result = paymentRepository.save(payment);

        return ResponseEntity.created(new URI("/api/payment/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("payment", result.getId().toString()))
            .body(result);
    }

    /**
     * GET
     */
    @RequestMapping(value = "/payment/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Payment> get(@PathVariable Long id) {
        return Optional.ofNullable(paymentRepository.findOne(id))
            .map(panierItem -> new ResponseEntity<>(
                panierItem,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    /**
     * GET
     */
    @RequestMapping(value = "/payment",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Payment> getAll() {
        List<Payment> paymentList = new ArrayList<Payment>();
        String login = SecurityUtils.getCurrentLogin();
        paymentList = paymentRepository.findByLoginEquals(login);
        return paymentList;
    }

}
