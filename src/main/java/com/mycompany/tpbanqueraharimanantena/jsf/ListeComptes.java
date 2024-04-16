/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mycompany.tpbanqueraharimanantena.jsf;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import com.mycompany.tpbanqueraharimanantena.entity.CompteBancaire;
import com.mycompany.tpbanqueraharimanantena.service.GestionnaireCompte;
import com.mycompany.tpbanqueraharimanantena.util.Util;

/**
 *
 * @author Diary
 */
@Named
@RequestScoped
public class ListeComptes implements Serializable {

    @Inject
    private GestionnaireCompte gestionnaireCompte;
    
    public List<CompteBancaire> getAllComptes() {
        return gestionnaireCompte.getAllComptes();
    }
    
     /**
     * Supprimer un compte bancaire 
     */
    public String supprimerCompte(CompteBancaire compteBancaire) {
        gestionnaireCompte.supprimerCompte(compteBancaire);
        Util.addFlashInfoMessage("Compte de " + compteBancaire.getNom() + " supprimé");
        return "listeComptes?faces-redirect=true";
    }
}
