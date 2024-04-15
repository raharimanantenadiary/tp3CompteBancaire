/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpraharimanantena.jsf;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import tpraharimanantena.entity.CompteBancaire;
import tpraharimanantena.service.GestionnaireCompte;

/**
 *
 * @author Diary
 */
@Named(value="TransfertBean")
@ViewScoped
public class TransfertBean implements Serializable {

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    private Long idCompteSource;
    private Long idCompteDestination;
    private double montant;

    public Long getIdCompteSource() {
        return idCompteSource;
    }

    public void setIdCompteSource(Long idCompteSource) {
        this.idCompteSource = idCompteSource;
    }

    public Long getIdCompteDestination() {
        return idCompteDestination;
    }

    public void setIdCompteDestination(Long idCompteDestination) {
        this.idCompteDestination = idCompteDestination;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
    

    public String transferer() {
            CompteBancaire source = gestionnaireCompte.getCompteById(this.getIdCompteSource());
            CompteBancaire destination = gestionnaireCompte.getCompteById(this.getIdCompteDestination());
            gestionnaireCompte.transferer(source, destination, this.getMontant());
            return "listeComptes.xhtml?faces-redirect=true";
    }

}
