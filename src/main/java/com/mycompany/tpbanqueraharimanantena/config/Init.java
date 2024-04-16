/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpbanqueraharimanantena.config;

import jakarta.inject.Inject;
import com.mycompany.tpbanqueraharimanantena.service.GestionnaireCompte;
import com.mycompany.tpbanqueraharimanantena.entity.CompteBancaire;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.servlet.ServletContext;
/**
 *
 * @author Diary
 */
@ApplicationScoped
public class Init {

    private final GestionnaireCompte gestionnaireCompte;

    @Inject
    public Init(GestionnaireCompte gestionnaireCompte) {
        this.gestionnaireCompte = gestionnaireCompte;
    }

    @PostConstruct
    public void init(@Observes @Initialized(ApplicationScoped.class) ServletContext context) {
       System.out.println("Initialisation de l'application...");

        if (gestionnaireCompte.nbComptes() == 0) {
            System.out.println("Aucun compte trouvé dans la base de données. Création des comptes...");
            gestionnaireCompte.creerCompte(new CompteBancaire("John Lennon", 150000));
            gestionnaireCompte.creerCompte(new CompteBancaire("Paul McCartney", 950000));
            gestionnaireCompte.creerCompte(new CompteBancaire("Ringo Starr", 20000));
            gestionnaireCompte.creerCompte(new CompteBancaire("George Harrison", 100000));
            System.out.println("Comptes créés avec succès.");
        } else {
            System.out.println("Des comptes sont déjà présents dans la base de données.");
        }
    }
}