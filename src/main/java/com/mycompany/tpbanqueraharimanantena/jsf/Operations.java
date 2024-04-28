/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mycompany.tpbanqueraharimanantena.jsf;

import com.mycompany.tpbanqueraharimanantena.entity.CompteBancaire;
import com.mycompany.tpbanqueraharimanantena.entity.OperationBancaire;
import com.mycompany.tpbanqueraharimanantena.service.GestionnaireCompte;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Diary
 */
@Named(value = "operations")
@ViewScoped
public class Operations implements Serializable {

    private Long id;
    private CompteBancaire compteBancaire;
    private List<OperationBancaire> operations;


    @Inject
    private GestionnaireCompte gestionnaireCompte;
    
    
    public Operations() {
    }


    public CompteBancaire getCompteBancaire() {
        return compteBancaire;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void loadCompte() {
        compteBancaire = gestionnaireCompte.getCompteById(this.id);
    }

    public List<OperationBancaire> getOperations() {
        return this.compteBancaire.getOperations();
    }
}