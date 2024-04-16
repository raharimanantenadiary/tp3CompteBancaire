/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpbanqueraharimanantena.jsf;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import com.mycompany.tpbanqueraharimanantena.entity.CompteBancaire;
import com.mycompany.tpbanqueraharimanantena.service.GestionnaireCompte;
import com.mycompany.tpbanqueraharimanantena.util.Util;

/**
 *
 * @author Diary
 */
@Named(value = "TransfertBean")
@ViewScoped
public class TransfertBean implements Serializable {

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    private Long idCompteSource;
    private Long idCompteDestination;
    private double montant;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

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
        Util util = new Util(); 

        boolean erreur = false;

        CompteBancaire source = gestionnaireCompte.getCompteById(this.getIdCompteSource());
        CompteBancaire destination = gestionnaireCompte.getCompteById(this.getIdCompteDestination());

        if (source == null) {
            FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Compte source introuvable", "Le compte source spécifié n'existe pas.");
            FacesContext.getCurrentInstance().addMessage("form:sourceId", errorMessage); 
            erreur = true;
        }
        if (destination == null) {
            FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Compte destination introuvable", "Le compte destination spécifié n'existe pas.");
            FacesContext.getCurrentInstance().addMessage("form:destinationId", errorMessage); 
            erreur = true;
        }
        if (source != null && destination != null && source.getSolde() < this.getMontant()) {
            FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Solde insuffisant", "Le solde du compte source est insuffisant pour effectuer le transfert.");
            FacesContext.getCurrentInstance().addMessage(null, errorMessage); 
            erreur = true;
        }

        if (erreur) {
            return null;
        }

        gestionnaireCompte.transferer(source, destination, this.getMontant());

        String compteSource = source.getNom();
        String compteDestination = destination.getNom();

        String messageDetail = "Transfert réussi de " + compteSource + " à " + compteDestination + " pour un montant de " + this.getMontant();
        String messageResume = "Transfert réussi";
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, messageDetail, messageResume);
        util.addFlashMessage(message);

        return "listeComptes.xhtml?faces-redirect=true&transfert=" + this.getIdCompteSource() + this.getIdCompteDestination() + this.getMontant();
    }

}
