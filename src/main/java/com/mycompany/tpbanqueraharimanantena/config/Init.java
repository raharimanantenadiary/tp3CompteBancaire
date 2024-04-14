/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpbanqueraharimanantena.config;

import jakarta.inject.Inject;
import tpraharimanantena.service.GestionnaireCompte;
import tpraharimanantena.entity.CompteBancaire;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextAttributeEvent;
import java.util.List;

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

        System.out.println("Création des comptes...");
        gestionnaireCompte.creerCompte(new CompteBancaire("John Lennon", 150000));
        gestionnaireCompte.creerCompte(new CompteBancaire("Paul McCartney", 950000));
        gestionnaireCompte.creerCompte(new CompteBancaire("Ringo Starr", 20000));
        gestionnaireCompte.creerCompte(new CompteBancaire("George Harrison", 100000));
        System.out.println("Comptes créés avec succès.");
    }
}