package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.math.BigDecimal;

/**
 * A Ordinateur.
 */
@Entity
@Table(name = "ORDINATEUR")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Ordinateur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "marque")
    private String marque;

    @Column(name = "cpu")
    private String cpu;

    @Column(name = "prix")
    private BigDecimal prix;

    @Column(name = "ram")
    private String ram;

    @Column(name = "lecteur")
    private String lecteur;

    @Column(name = "graveur")
    private String graveur;

    @Column(name = "bluetooth")
    private String bluetooth;

    @Column(name = "wifi")
    private String wifi;

    @Column(name = "cartegraphique")
    private String cartegraphique;

    @Column(name = "dd")
    private String dd;

    @Column(name = "tailleecran")
    private String tailleecran;

    @Column(name = "webcam")
    private String webcam;

    @Column(name = "image")
    private byte[] image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getLecteur() {
        return lecteur;
    }

    public void setLecteur(String lecteur) {
        this.lecteur = lecteur;
    }

    public String getGraveur() {
        return graveur;
    }

    public void setGraveur(String graveur) {
        this.graveur = graveur;
    }

    public String getBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(String bluetooth) {
        this.bluetooth = bluetooth;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public String getCartegraphique() {
        return cartegraphique;
    }

    public void setCartegraphique(String cartegraphique) {
        this.cartegraphique = cartegraphique;
    }

    public String getDd() {
        return dd;
    }

    public void setDd(String dd) {
        this.dd = dd;
    }

    public String getTailleecran() {
        return tailleecran;
    }

    public void setTailleecran(String tailleecran) {
        this.tailleecran = tailleecran;
    }

    public String getWebcam() {
        return webcam;
    }

    public void setWebcam(String webcam) {
        this.webcam = webcam;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Ordinateur ordinateur = (Ordinateur) o;

        if ( ! Objects.equals(id, ordinateur.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Ordinateur{" +
                "id=" + id +
                ", name='" + name + "'" +
                ", type='" + type + "'" +
                ", cpu='" + cpu + "'" +
                ", ram='" + ram + "'" +
                ", lecteur='" + lecteur + "'" +
                ", graveur='" + graveur + "'" +
                ", bluetooth='" + bluetooth + "'" +
                ", wifi='" + wifi + "'" +
                ", cartegraphique='" + cartegraphique + "'" +
                ", dd='" + dd + "'" +
                ", tailleecran='" + tailleecran + "'" +
                ", webcam='" + webcam + "'" +
                '}';
    }
}
