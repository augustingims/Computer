package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A Ordinateur.
 */
@Entity
@Table(name = "PAYMENT")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "namecard")
    private String namecard;

    @Column(name = "numbercard")
    private String numbercard;

    @Column(name = "mois")
    private String mois;

    @Column(name = "annee")
    private String annee;

    @Column(name = "montant")
    private BigDecimal montant;

    @Column(name = "cardcv")
    private String cardcv;

    @Column(name = "login")
    private String login;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamecard() {
        return namecard;
    }

    public void setNamecard(String namecard) {
        this.namecard = namecard;
    }

    public String getNumbercard() {
        return numbercard;
    }

    public void setNumbercard(String numbercard) {
        this.numbercard = numbercard;
    }

    public String getMois() {
        return mois;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public String getCardcv() {
        return cardcv;
    }

    public void setCardcv(String cardcv) {
        this.cardcv = cardcv;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Payment payment = (Payment) o;

        if ( ! Objects.equals(id, payment.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Payment{" +
            "id=" + id +
            ", namecard='" + namecard + '\'' +
            ", numbercard='" + numbercard + '\'' +
            ", mois='" + mois + '\'' +
            ", annee='" + annee + '\'' +
            ", montant=" + montant +
            ", cardcv='" + cardcv + '\'' +
            ", login='" + login + '\'' +
            '}';
    }
}
