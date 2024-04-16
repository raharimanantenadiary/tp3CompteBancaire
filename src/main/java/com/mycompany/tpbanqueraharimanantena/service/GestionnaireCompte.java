/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpbanqueraharimanantena.service;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.List;
import com.mycompany.tpbanqueraharimanantena.entity.CompteBancaire;

@DataSourceDefinition(
        className = "com.mysql.cj.jdbc.MysqlDataSource",
        name = "java:app/jdbc/banque",
        serverName = "localhost",
        portNumber = 3306,
        user = "root",
        password = "root",
        databaseName = "banque",
        properties = {
            "useSSL=false",
            "allowPublicKeyRetrieval=true",
            "driverClass=com.mysql.cj.jdbc.Driver"
        }
)

/**
 *
 * @author Diary
 */
@ApplicationScoped
public class GestionnaireCompte {

    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;

    @Transactional
    public void creerCompte(CompteBancaire compte) {
        em.persist(compte);
    }

    public List<CompteBancaire> getAllComptes() {
        TypedQuery<CompteBancaire> query = em.createQuery("SELECT c FROM CompteBancaire c", CompteBancaire.class);
        return query.getResultList();

    }

    public long nbComptes() {
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(c) FROM CompteBancaire c", Long.class);
        return query.getSingleResult();
    }

    @Transactional
    public void transferer(CompteBancaire source, CompteBancaire destination, double montant) {
        if (source != null && destination != null) {
            if (source.getSolde() >= montant) {
                source.retirer((int) montant);
                destination.deposer((int) montant);
                em.merge(source);
                em.merge(destination);
            } else {
                throw new IllegalArgumentException("Le solde du compte source est insuffisant pour effectuer le transfert.");
            }
        } else {
            throw new IllegalArgumentException("L'un des comptes spécifiés n'existe pas.");
        }
    }

    public CompteBancaire getCompteById(Long id) {
        return em.find(CompteBancaire.class, id);
    }

}
