package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.Ordinateur;
import com.mycompany.myapp.repository.OrdinateurRepository;
import com.mycompany.myapp.service.OrdinateurService;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.io.IOException;

/**
 * REST controller for managing Ordinateur.
 */
@RestController
@RequestMapping("/api")
public class OrdinateurResource {

    private final Logger log = LoggerFactory.getLogger(OrdinateurResource.class);

    @Inject
    private OrdinateurRepository ordinateurRepository;

    @Inject
    private OrdinateurService ordinateurService;

    /**
     * POST  /ordinateurs -> Create a new ordinateur.
     */
    @RequestMapping(value = "/ordinateurs",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Ordinateur> createOrdinateur(@RequestBody Ordinateur ordinateur) throws URISyntaxException {
        log.debug("REST request to save Ordinateur : {}", ordinateur);
        if (ordinateur.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new ordinateur cannot already have an ID").body(null);
        }
        Ordinateur result = ordinateurRepository.save(ordinateur);
        return ResponseEntity.created(new URI("/api/ordinateurs/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("ordinateur", result.getId().toString()))
                .body(result);
    }

    /**
     * PUT  /ordinateurs -> Updates an existing ordinateur.
     */
    @RequestMapping(value = "/ordinateurs",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Ordinateur> updateOrdinateur(@RequestBody Ordinateur ordinateur) throws URISyntaxException {
        log.debug("REST request to update Ordinateur : {}", ordinateur);
        if (ordinateur.getId() == null) {
            return createOrdinateur(ordinateur);
        }
        Ordinateur result = ordinateurRepository.save(ordinateur);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("ordinateur", ordinateur.getId().toString()))
                .body(result);
    }

    /**
     * GET  /ordinateurs -> get all the ordinateurs.
     */
    @RequestMapping(value = "/ordinateurs",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Ordinateur> getAllOrdinateurs() {
        log.debug("REST request to get all Ordinateurs");
        return ordinateurRepository.findAll();
    }

    /**
     * GET  /ordinateur/:name -> get id of the ordinateurs.
     */
    @RequestMapping(value = "/ordinateur/{name}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public Long getIdOrdinateur(@PathVariable String name) {
        log.debug("REST request to get id of the Ordinateurs");
        Ordinateur ordinateur = ordinateurRepository.findByNameEquals(name);
        Long id = ordinateur.getId();
        return id;
    }

    /**
     * GET  /ordinateurs/:id -> get the "id" ordinateur.
     */
    @RequestMapping(value = "/ordinateurs/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Ordinateur> getOrdinateur(@PathVariable Long id) {
        log.debug("REST request to get Ordinateur : {}", id);
        return Optional.ofNullable(ordinateurRepository.findOne(id))
            .map(ordinateur -> new ResponseEntity<>(
                ordinateur,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /ordinateurs/:id -> delete the "id" ordinateur.
     */
    @RequestMapping(value = "/ordinateurs/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteOrdinateur(@PathVariable Long id) {
        log.debug("REST request to delete Ordinateur : {}", id);
        ordinateurRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("ordinateur", id.toString())).build();
    }

    @Timed
    @RequestMapping(value = "/ordinateur/getImg/{id}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImg(@PathVariable Long id) throws IOException {
        Ordinateur ordinateur = ordinateurRepository.findById(id);
        byte[] img = ordinateur.getImage();

        final HttpHeaders headers = new HttpHeaders();
        String mime = ordinateurService.checkMime(img);
        headers.setContentType(MediaType.parseMediaType(mime));

        return new ResponseEntity<byte[]>(img, headers, HttpStatus.CREATED);
    }
}
