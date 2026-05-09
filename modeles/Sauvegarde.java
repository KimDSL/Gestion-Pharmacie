import java.io.*;

public class Sauvegarde {

    // Chemins des fichiers de données 
    public static final String FICHIER_ETAGERES     = "data/etageres.csv";
    public static final String FICHIER_MEDICAMENTS  = "data/medicaments.csv";
    public static final String FICHIER_CLIENTS      = "data/clients.csv";
    public static final String FICHIER_LIGNES_VENTE = "data/lignes_vente.csv";
    public static final String FICHIER_ORDONNANCES  = "data/ordonnances.csv";
    public static final String FICHIER_LIGNES_ORDO  = "data/lignes_ordonnance.csv";
    public static final String FICHIER_PHARMACIENS  = "data/pharmaciens.csv";

    // Sauvegarde Etagere
    public static void sauvegarderEtageres(Etagere[] etageres, int nb) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FICHIER_ETAGERES))) {
            for (int i = 0; i < nb; i++) {
                pw.println(etageres[i].getNumero() + ";" + etageres[i].getTypeTraitement());
            }
            System.out.println("[Sauvegarde] " + nb + " étagère(s) sauvegardée(s).");
        } catch (IOException e) {
            System.out.println("[Erreur] Impossible de sauvegarder les étagères : " + e.getMessage());
        }
    }

    // Charge Etagere
    public static int chargerEtageres(Etagere[] etageres) {
        int nb = 0;
        File f = new File(FICHIER_ETAGERES);
        if (!f.exists()) return 0;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String ligne;
            while ((ligne = br.readLine()) != null && nb < etageres.length) {
                String[] parts = ligne.split(";");
                if (parts.length < 2) continue;
                int num    = Integer.parseInt(parts[0].trim());
                String type = parts[1].trim();
                etageres[nb++] = new Etagere(num, type);
            }
            System.out.println("[Chargement] " + nb + " étagère(s) chargée(s).");
        } catch (IOException | NumberFormatException e) {
            System.out.println("[Erreur] Impossible de charger les étagères : " + e.getMessage());
        }
        return nb;
    }

    // Sauvegarde Medicament
    public static void sauvegarderMedicaments(Etagere[] etageres, int nb) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FICHIER_MEDICAMENTS))) {
            for (int i = 0; i < nb; i++) {
                for (Medicament m : etageres[i].getMedicaments()) {
                    pw.println(
                        m.getNumEtagere()         + ";" +
                        m.getNomCommercial()      + ";" +
                        m.getFormeGalenique()     + ";" +
                        m.getPrix()               + ";" +
                        m.getListeMedicament()    + ";" +
                        m.getQuantiteDisponible()
                    );
                }
            }
            System.out.println("[Sauvegarde] Médicaments sauvegardés.");
        } catch (IOException e) {
            System.out.println("[Erreur] Impossible de sauvegarder les médicaments : " + e.getMessage());
        }
    }

    // Charge Medicament
    public static void chargerMedicaments(Etagere[] etageres, int nbEtageres) {
        File f = new File(FICHIER_MEDICAMENTS);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String ligne;
            int count = 0;
            while ((ligne = br.readLine()) != null) {
                String[] p = ligne.split(";");
                if (p.length < 6) continue;
                int    numEt  = Integer.parseInt(p[0].trim());
                String nom    = p[1].trim();
                String forme  = p[2].trim();
                double prix   = Double.parseDouble(p[3].trim());
                String liste  = p[4].trim();
                int    qte    = Integer.parseInt(p[5].trim());
                Medicament m = new Medicament(numEt, nom, forme, prix, liste, qte);
                // Chercher l'étagère correspondante
                for (int i = 0; i < nbEtageres; i++) {
                    if (etageres[i].getNumero() == numEt) {
                        etageres[i].ajouterMedicamentSilencieux(m);
                        count++;
                        break;
                    }
                }
            }
            System.out.println("[Chargement] " + count + " médicament(s) chargé(s).");
        } catch (IOException | NumberFormatException e) {
            System.out.println("[Erreur] Impossible de charger les médicaments : " + e.getMessage());
        }
    }

    // Sauvegarde Pharmacien
    public static void sauvegarderPharmaciens(Pharmacien[] pharmaciens, int nb) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FICHIER_PHARMACIENS))) {
            for (int i = 0; i < nb; i++) {
                pw.println(
                    pharmaciens[i].getIdPharmacien()  + ";" +
                    pharmaciens[i].getNomPharmacien() + ";" +
                    pharmaciens[i].getHeureArrivee()  + ";" +
                    pharmaciens[i].getHeureDepart()
                );
            }
            System.out.println("[Sauvegarde] " + nb + " pharmacien(s) sauvegardé(s).");
        } catch (IOException e) {
            System.out.println("[Erreur] Impossible de sauvegarder les pharmaciens : " + e.getMessage());
        }
    }

    // Charge Pharmacien
    public static int chargerPharmaciens(Pharmacien[] pharmaciens) {
        int nb = 0;
        File f = new File(FICHIER_PHARMACIENS);
        if (!f.exists()) return 0;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String ligne;
            while ((ligne = br.readLine()) != null && nb < pharmaciens.length) {
                String[] p = ligne.split(";");
                if (p.length < 4) continue;
                int    id   = Integer.parseInt(p[0].trim());
                String nom  = p[1].trim();
                String arr  = p[2].trim();
                String dep  = p[3].trim();
                pharmaciens[nb++] = new Pharmacien(id, nom, arr, dep);
            }
            System.out.println("[Chargement] " + nb + " pharmacien(s) chargé(s).");
        } catch (IOException | NumberFormatException e) {
            System.out.println("[Erreur] Impossible de charger les pharmaciens : " + e.getMessage());
        }
        return nb;
    }

    // Sauvegarde Client
    public static void sauvegarderClients(Client[] clients, int nb) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FICHIER_CLIENTS))) {
            for (int i = 0; i < nb; i++) {
                pw.println(
                    clients[i].getIdClient()  + ";" +
                    clients[i].getDateHeure() + ";" +
                    clients[i].getPrixTotal()
                );
            }
            System.out.println("[Sauvegarde] " + nb + " client(s) sauvegardé(s).");
        } catch (IOException e) {
            System.out.println("[Erreur] Impossible de sauvegarder les clients : " + e.getMessage());
        }
    }

    // Sauvegarde LigneVente
    public static void sauvegarderLignesVente(Client[] clients, int nb) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FICHIER_LIGNES_VENTE))) {
            for (int i = 0; i < nb; i++) {
                for (LigneVente lv : clients[i].getAchats()) {
                    pw.println(
                        clients[i].getIdClient()                  + ";" +
                        lv.getMedicament().getNomCommercial()     + ";" +
                        lv.getQuantite()                          + ";" +
                        lv.getSousTotal()
                    );
                }
            }
            System.out.println("[Sauvegarde] Lignes de vente sauvegardées.");
        } catch (IOException e) {
            System.out.println("[Erreur] Impossible de sauvegarder les lignes de vente : " + e.getMessage());
        }
    }

    // Charge Client
    public static int chargerClients(Client[] clients) {
        int nb = 0;
        File f = new File(FICHIER_CLIENTS);
        if (!f.exists()) return 0;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String ligne;
            while ((ligne = br.readLine()) != null && nb < clients.length) {
                String[] p = ligne.split(";");
                if (p.length < 2) continue;
                int    id = Integer.parseInt(p[0].trim());
                String dh = p[1].trim();
                clients[nb++] = new Client(id, dh);
            }
            System.out.println("[Chargement] " + nb + " client(s) chargé(s).");
        } catch (IOException | NumberFormatException e) {
            System.out.println("[Erreur] Impossible de charger les clients : " + e.getMessage());
        }
        return nb;
    }
    
    // Charge LigneVente
    public static void chargerLignesVente(Client[] clients, int nbClients,
                                           Etagere[] etageres, int nbEtageres) {
        File f = new File(FICHIER_LIGNES_VENTE);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String ligne;
            int count = 0;
            while ((ligne = br.readLine()) != null) {
                String[] p = ligne.split(";");
                if (p.length < 3) continue;
                int    idClient = Integer.parseInt(p[0].trim());
                String nomMed   = p[1].trim();
                int    qte      = Integer.parseInt(p[2].trim());
                // Retrouver le client
                Client c = null;
                for (int i = 0; i < nbClients; i++) {
                    if (clients[i].getIdClient() == idClient) { c = clients[i]; break; }
                }
                if (c == null) continue;
                // Retrouver le médicament
                Medicament m = null;
                for (int i = 0; i < nbEtageres && m == null; i++) {
                    m = etageres[i].chercherMedicament(nomMed);
                }
                if (m == null) continue;
                c.ajouterAchat(new LigneVente(m, qte));
                count++;
            }
            System.out.println("[Chargement] " + count + " ligne(s) de vente chargée(s).");
        } catch (IOException | NumberFormatException e) {
            System.out.println("[Erreur] Impossible de charger les lignes de vente : " + e.getMessage());
        }
    }
    
    // Sauvegarde Ordonnance
    public static void sauvegarderOrdonnances(Ordonnance[] ordonnances, int nb) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FICHIER_ORDONNANCES))) {
            for (int i = 0; i < nb; i++) {
                pw.println(
                    ordonnances[i].getNumero()       + ";" +
                    ordonnances[i].getIdClient()     + ";" +
                    ordonnances[i].isEstPeriodique() + ";" +
                    ordonnances[i].getPeriodicite()
                );
            }
            System.out.println("[Sauvegarde] " + nb + " ordonnance(s) sauvegardée(s).");
        } catch (IOException e) {
            System.out.println("[Erreur] Impossible de sauvegarder les ordonnances : " + e.getMessage());
        }
    }

    // Sauvegarde LigneOrdonnance
    public static void sauvegarderLignesOrdonnance(Ordonnance[] ordonnances, int nb) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FICHIER_LIGNES_ORDO))) {
            for (int i = 0; i < nb; i++) {
                for (LigneOrdonnance lo : ordonnances[i].getLignes()) {
                    pw.println(
                        ordonnances[i].getNumero()    + ";" +
                        lo.getNomMedicament()         + ";" +
                        lo.getFormeGalenique()        + ";" +
                        lo.getQuantitePrescrite()
                    );
                }
            }
            System.out.println("[Sauvegarde] Lignes d'ordonnance sauvegardées.");
        } catch (IOException e) {
            System.out.println("[Erreur] Impossible de sauvegarder les lignes d'ordonnance : " + e.getMessage());
        }
    }

    // Charge Ordonnance
    public static int chargerOrdonnances(Ordonnance[] ordonnances) {
        int nb = 0;
        File f = new File(FICHIER_ORDONNANCES);
        if (!f.exists()) return 0;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String ligne;
            while ((ligne = br.readLine()) != null && nb < ordonnances.length) {
                String[] p = ligne.split(";");
                if (p.length < 4) continue;
                int     num     = Integer.parseInt(p[0].trim());
                int     idC     = Integer.parseInt(p[1].trim());
                boolean period  = Boolean.parseBoolean(p[2].trim());
                String  periStr = p[3].trim();
                ordonnances[nb++] = new Ordonnance(num, idC, period, periStr);
            }
            System.out.println("[Chargement] " + nb + " ordonnance(s) chargée(s).");
        } catch (IOException | NumberFormatException e) {
            System.out.println("[Erreur] Impossible de charger les ordonnances : " + e.getMessage());
        }
        return nb;
    }

    // Charge LigneOrdonnance
    public static void chargerLignesOrdonnance(Ordonnance[] ordonnances, int nbOrdonnances) {
        File f = new File(FICHIER_LIGNES_ORDO);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String ligne;
            int count = 0;
            while ((ligne = br.readLine()) != null) {
                String[] p = ligne.split(";");
                if (p.length < 4) continue;
                int    numOrdo = Integer.parseInt(p[0].trim());
                String nom     = p[1].trim();
                String forme   = p[2].trim();
                int    qte     = Integer.parseInt(p[3].trim());
                for (int i = 0; i < nbOrdonnances; i++) {
                    if (ordonnances[i].getNumero() == numOrdo) {
                        ordonnances[i].ajouterLigne(new LigneOrdonnance(nom, forme, qte));
                        count++;
                        break;
                    }
                }
            }
            System.out.println("[Chargement] " + count + " ligne(s) d'ordonnance chargée(s).");
        } catch (IOException | NumberFormatException e) {
            System.out.println("[Erreur] Impossible de charger les lignes d'ordonnance : " + e.getMessage());
        }
    }

    // Tout sauvegarder et tout charger
    public static void toutSauvegarder(Etagere[] etageres, int nbEt,
                                        Client[] clients, int nbCl,
                                        Ordonnance[] ordonnances, int nbOr,
                                        Pharmacien[] pharmaciens, int nbPh) {
        // Créer le dossier data/ s'il n'existe pas
        new File("data").mkdirs();
        System.out.println("\n--- Sauvegarde en cours ---");
        sauvegarderEtageres(etageres, nbEt);
        sauvegarderMedicaments(etageres, nbEt);
        sauvegarderPharmaciens(pharmaciens, nbPh);
        sauvegarderClients(clients, nbCl);
        sauvegarderLignesVente(clients, nbCl);
        sauvegarderOrdonnances(ordonnances, nbOr);
        sauvegarderLignesOrdonnance(ordonnances, nbOr);
        System.out.println("--- Sauvegarde terminée ---\n");
    }

    // Charger tout au démarrage
    public static int[] toutCharger(Etagere[] etageres, Client[] clients,
                                     Ordonnance[] ordonnances, Pharmacien[] pharmaciens) {
        System.out.println("\n--- Chargement des données ---");
        int nbEt = chargerEtageres(etageres);
        chargerMedicaments(etageres, nbEt);
        int nbPh = chargerPharmaciens(pharmaciens);
        int nbCl = chargerClients(clients);
        chargerLignesVente(clients, nbCl, etageres, nbEt);
        int nbOr = chargerOrdonnances(ordonnances);
        chargerLignesOrdonnance(ordonnances, nbOr);
        System.out.println("--- Chargement terminé ---\n");
        return new int[]{nbEt, nbCl, nbOr, nbPh};
    }
}
