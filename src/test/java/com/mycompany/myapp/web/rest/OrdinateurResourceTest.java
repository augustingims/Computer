package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Application;
import com.mycompany.myapp.domain.Ordinateur;
import com.mycompany.myapp.repository.OrdinateurRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the OrdinateurResource REST controller.
 *
 * @see OrdinateurResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class OrdinateurResourceTest {

    private static final String DEFAULT_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_NAME = "UPDATED_TEXT";
    private static final String DEFAULT_TYPE = "SAMPLE_TEXT";
    private static final String UPDATED_TYPE = "UPDATED_TEXT";
    private static final String DEFAULT_CPU = "SAMPLE_TEXT";
    private static final String UPDATED_CPU = "UPDATED_TEXT";
    private static final String DEFAULT_RAM = "SAMPLE_TEXT";
    private static final String UPDATED_RAM = "UPDATED_TEXT";
    private static final String DEFAULT_LECTEUR = "SAMPLE_TEXT";
    private static final String UPDATED_LECTEUR = "UPDATED_TEXT";
    private static final String DEFAULT_GRAVEUR = "SAMPLE_TEXT";
    private static final String UPDATED_GRAVEUR = "UPDATED_TEXT";
    private static final String DEFAULT_BLUETOOTH = "SAMPLE_TEXT";
    private static final String UPDATED_BLUETOOTH = "UPDATED_TEXT";
    private static final String DEFAULT_WIFI = "SAMPLE_TEXT";
    private static final String UPDATED_WIFI = "UPDATED_TEXT";
    private static final String DEFAULT_CARTEGRAPHIQUE = "SAMPLE_TEXT";
    private static final String UPDATED_CARTEGRAPHIQUE = "UPDATED_TEXT";
    private static final String DEFAULT_DD = "SAMPLE_TEXT";
    private static final String UPDATED_DD = "UPDATED_TEXT";
    private static final String DEFAULT_TAILLEECRAN = "SAMPLE_TEXT";
    private static final String UPDATED_TAILLEECRAN = "UPDATED_TEXT";
    private static final String DEFAULT_WEBCAM = "SAMPLE_TEXT";
    private static final String UPDATED_WEBCAM = "UPDATED_TEXT";

    @Inject
    private OrdinateurRepository ordinateurRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restOrdinateurMockMvc;

    private Ordinateur ordinateur;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        OrdinateurResource ordinateurResource = new OrdinateurResource();
        ReflectionTestUtils.setField(ordinateurResource, "ordinateurRepository", ordinateurRepository);
        this.restOrdinateurMockMvc = MockMvcBuilders.standaloneSetup(ordinateurResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        ordinateur = new Ordinateur();
        ordinateur.setName(DEFAULT_NAME);
        ordinateur.setType(DEFAULT_TYPE);
        ordinateur.setCpu(DEFAULT_CPU);
        ordinateur.setRam(DEFAULT_RAM);
        ordinateur.setLecteur(DEFAULT_LECTEUR);
        ordinateur.setGraveur(DEFAULT_GRAVEUR);
        ordinateur.setBluetooth(DEFAULT_BLUETOOTH);
        ordinateur.setWifi(DEFAULT_WIFI);
        ordinateur.setCartegraphique(DEFAULT_CARTEGRAPHIQUE);
        ordinateur.setDd(DEFAULT_DD);
        ordinateur.setTailleecran(DEFAULT_TAILLEECRAN);
        ordinateur.setWebcam(DEFAULT_WEBCAM);
    }

    @Test
    @Transactional
    public void createOrdinateur() throws Exception {
        int databaseSizeBeforeCreate = ordinateurRepository.findAll().size();

        // Create the Ordinateur

        restOrdinateurMockMvc.perform(post("/api/ordinateurs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(ordinateur)))
                .andExpect(status().isCreated());

        // Validate the Ordinateur in the database
        List<Ordinateur> ordinateurs = ordinateurRepository.findAll();
        assertThat(ordinateurs).hasSize(databaseSizeBeforeCreate + 1);
        Ordinateur testOrdinateur = ordinateurs.get(ordinateurs.size() - 1);
        assertThat(testOrdinateur.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testOrdinateur.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testOrdinateur.getCpu()).isEqualTo(DEFAULT_CPU);
        assertThat(testOrdinateur.getRam()).isEqualTo(DEFAULT_RAM);
        assertThat(testOrdinateur.getLecteur()).isEqualTo(DEFAULT_LECTEUR);
        assertThat(testOrdinateur.getGraveur()).isEqualTo(DEFAULT_GRAVEUR);
        assertThat(testOrdinateur.getBluetooth()).isEqualTo(DEFAULT_BLUETOOTH);
        assertThat(testOrdinateur.getWifi()).isEqualTo(DEFAULT_WIFI);
        assertThat(testOrdinateur.getCartegraphique()).isEqualTo(DEFAULT_CARTEGRAPHIQUE);
        assertThat(testOrdinateur.getDd()).isEqualTo(DEFAULT_DD);
        assertThat(testOrdinateur.getTailleecran()).isEqualTo(DEFAULT_TAILLEECRAN);
        assertThat(testOrdinateur.getWebcam()).isEqualTo(DEFAULT_WEBCAM);
    }

    @Test
    @Transactional
    public void getAllOrdinateurs() throws Exception {
        // Initialize the database
        ordinateurRepository.saveAndFlush(ordinateur);

        // Get all the ordinateurs
        restOrdinateurMockMvc.perform(get("/api/ordinateurs"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(ordinateur.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
                .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
                .andExpect(jsonPath("$.[*].cpu").value(hasItem(DEFAULT_CPU.toString())))
                .andExpect(jsonPath("$.[*].ram").value(hasItem(DEFAULT_RAM.toString())))
                .andExpect(jsonPath("$.[*].lecteur").value(hasItem(DEFAULT_LECTEUR.toString())))
                .andExpect(jsonPath("$.[*].graveur").value(hasItem(DEFAULT_GRAVEUR.toString())))
                .andExpect(jsonPath("$.[*].bluetooth").value(hasItem(DEFAULT_BLUETOOTH.toString())))
                .andExpect(jsonPath("$.[*].wifi").value(hasItem(DEFAULT_WIFI.toString())))
                .andExpect(jsonPath("$.[*].cartegraphique").value(hasItem(DEFAULT_CARTEGRAPHIQUE.toString())))
                .andExpect(jsonPath("$.[*].dd").value(hasItem(DEFAULT_DD.toString())))
                .andExpect(jsonPath("$.[*].tailleecran").value(hasItem(DEFAULT_TAILLEECRAN.toString())))
                .andExpect(jsonPath("$.[*].webcam").value(hasItem(DEFAULT_WEBCAM.toString())));
    }

    @Test
    @Transactional
    public void getOrdinateur() throws Exception {
        // Initialize the database
        ordinateurRepository.saveAndFlush(ordinateur);

        // Get the ordinateur
        restOrdinateurMockMvc.perform(get("/api/ordinateurs/{id}", ordinateur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(ordinateur.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.cpu").value(DEFAULT_CPU.toString()))
            .andExpect(jsonPath("$.ram").value(DEFAULT_RAM.toString()))
            .andExpect(jsonPath("$.lecteur").value(DEFAULT_LECTEUR.toString()))
            .andExpect(jsonPath("$.graveur").value(DEFAULT_GRAVEUR.toString()))
            .andExpect(jsonPath("$.bluetooth").value(DEFAULT_BLUETOOTH.toString()))
            .andExpect(jsonPath("$.wifi").value(DEFAULT_WIFI.toString()))
            .andExpect(jsonPath("$.cartegraphique").value(DEFAULT_CARTEGRAPHIQUE.toString()))
            .andExpect(jsonPath("$.dd").value(DEFAULT_DD.toString()))
            .andExpect(jsonPath("$.tailleecran").value(DEFAULT_TAILLEECRAN.toString()))
            .andExpect(jsonPath("$.webcam").value(DEFAULT_WEBCAM.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingOrdinateur() throws Exception {
        // Get the ordinateur
        restOrdinateurMockMvc.perform(get("/api/ordinateurs/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrdinateur() throws Exception {
        // Initialize the database
        ordinateurRepository.saveAndFlush(ordinateur);

		int databaseSizeBeforeUpdate = ordinateurRepository.findAll().size();

        // Update the ordinateur
        ordinateur.setName(UPDATED_NAME);
        ordinateur.setType(UPDATED_TYPE);
        ordinateur.setCpu(UPDATED_CPU);
        ordinateur.setRam(UPDATED_RAM);
        ordinateur.setLecteur(UPDATED_LECTEUR);
        ordinateur.setGraveur(UPDATED_GRAVEUR);
        ordinateur.setBluetooth(UPDATED_BLUETOOTH);
        ordinateur.setWifi(UPDATED_WIFI);
        ordinateur.setCartegraphique(UPDATED_CARTEGRAPHIQUE);
        ordinateur.setDd(UPDATED_DD);
        ordinateur.setTailleecran(UPDATED_TAILLEECRAN);
        ordinateur.setWebcam(UPDATED_WEBCAM);
        

        restOrdinateurMockMvc.perform(put("/api/ordinateurs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(ordinateur)))
                .andExpect(status().isOk());

        // Validate the Ordinateur in the database
        List<Ordinateur> ordinateurs = ordinateurRepository.findAll();
        assertThat(ordinateurs).hasSize(databaseSizeBeforeUpdate);
        Ordinateur testOrdinateur = ordinateurs.get(ordinateurs.size() - 1);
        assertThat(testOrdinateur.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testOrdinateur.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testOrdinateur.getCpu()).isEqualTo(UPDATED_CPU);
        assertThat(testOrdinateur.getRam()).isEqualTo(UPDATED_RAM);
        assertThat(testOrdinateur.getLecteur()).isEqualTo(UPDATED_LECTEUR);
        assertThat(testOrdinateur.getGraveur()).isEqualTo(UPDATED_GRAVEUR);
        assertThat(testOrdinateur.getBluetooth()).isEqualTo(UPDATED_BLUETOOTH);
        assertThat(testOrdinateur.getWifi()).isEqualTo(UPDATED_WIFI);
        assertThat(testOrdinateur.getCartegraphique()).isEqualTo(UPDATED_CARTEGRAPHIQUE);
        assertThat(testOrdinateur.getDd()).isEqualTo(UPDATED_DD);
        assertThat(testOrdinateur.getTailleecran()).isEqualTo(UPDATED_TAILLEECRAN);
        assertThat(testOrdinateur.getWebcam()).isEqualTo(UPDATED_WEBCAM);
    }

    @Test
    @Transactional
    public void deleteOrdinateur() throws Exception {
        // Initialize the database
        ordinateurRepository.saveAndFlush(ordinateur);

		int databaseSizeBeforeDelete = ordinateurRepository.findAll().size();

        // Get the ordinateur
        restOrdinateurMockMvc.perform(delete("/api/ordinateurs/{id}", ordinateur.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Ordinateur> ordinateurs = ordinateurRepository.findAll();
        assertThat(ordinateurs).hasSize(databaseSizeBeforeDelete - 1);
    }
}
