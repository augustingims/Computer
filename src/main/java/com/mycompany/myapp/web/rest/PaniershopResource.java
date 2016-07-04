package com.mycompany.myapp.web.rest;


import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.Paniershop;
import com.mycompany.myapp.repository.PaniershopRepository;
import com.mycompany.myapp.security.SecurityUtils;
import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class PaniershopResource {
    @Inject
    private PaniershopRepository paniershopRepository;

    @RequestMapping(value = "/paniershop/{image}/{prix}",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public Long create(@PathVariable Long image, @PathVariable BigDecimal prix) {
        Paniershop paniershop = new Paniershop();
        String currentLogin = SecurityUtils.getCurrentLogin();
        List<Paniershop> listshop = paniershopRepository.findByAuteurEquals(currentLogin);
          if(listshop.isEmpty()) {
              paniershop.setImage(image);
              paniershop.setPrix(prix);
              paniershop.setAuteur(currentLogin);
              paniershop.setDate(new DateTime());
              paniershopRepository.save(paniershop);
          }
        return getNbreshop();
    }

    /**
     * GET
     */
    @RequestMapping(value = "/paniershop/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Paniershop> get(@PathVariable Long id) {
        return Optional.ofNullable(paniershopRepository.findOne(id))
            .map(panierItem -> new ResponseEntity<>(
                panierItem,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    /**
     * GET
     */
    @RequestMapping(value = "/panieruser",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Paniershop> getPaniershop() {
        List<Paniershop> paniershop = new ArrayList<Paniershop>();
        String login = SecurityUtils.getCurrentLogin();
        paniershop = paniershopRepository.findByAuteurEquals(login);
        return paniershop;
    }
    /**
     * GET
     */
    @RequestMapping(value = "/nbreshop",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public Long getNbreshop() {
        List<Paniershop> paniershop = new ArrayList<Paniershop>();
        String login = SecurityUtils.getCurrentLogin();
        Long nbre = null;
        paniershop = paniershopRepository.findByAuteurEquals(login);
        for(int i=0;i<paniershop.size();i++){
            nbre = paniershopRepository.countByAuteur(paniershop.get(i).getAuteur());
        }
        return nbre;
    }
    /**
     * DELETE
     */
    @RequestMapping(value = "/delete/{id}",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        paniershopRepository.delete(id);
    }


}
