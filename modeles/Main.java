import java.util.Scanner;

public class Main {

    // Données globales partagées dans tout le programme
    static Etagere[]     etageres     = new Etagere[10];
    static int           nbEtageres   = 0;
    static Client[]      clients      = new Client[100];
    static int           nbClients    = 0;
    static Ordonnance[]  ordonnances  = new Ordonnance[100];
    static int           nbOrdonnances = 0;
    static Pharmacien[]  pharmaciens  = new Pharmacien[20];
    static int           nbPharmaciens = 0;

    static Scanner sc = new Scanner(System.in);
    
    // Méthodes
    static boolean[] ordonnanceServie = new boolean[100];
    static int[] renouvellementsRestants = new int[100];
    static boolean[] ordonnancePeriodique = new boolean[100];
    static String[] periodiciteOrdonnance = new String[100];
    
    static boolean isOrdonnanceServie(Ordonnance o) {
        int num = o.getNumero();
        if (num >= 0 && num < 100) {
            return ordonnanceServie[num];
        }
        return false;
    }
    
    static void setOrdonnanceServie(Ordonnance o, boolean servie) {
        int num = o.getNumero();
        if (num >= 0 && num < 100) {
            ordonnanceServie[num] = servie;
        }
    }
    
    static int getRenouvellementsRestantsOrdo(Ordonnance o) {
        int num = o.getNumero();
        if (num >= 0 && num < 100) {
            return renouvellementsRestants[num];
        }
        return 0;
    }
    
    static void setRenouvellementsRestantsOrdo(Ordonnance o, int nb) {
        int num = o.getNumero();
        if (num >= 0 && num < 100) {
            renouvellementsRestants[num] = nb;
        }
    }
    
    static void decrementerRenouvellement(Ordonnance o) {
        int num = o.getNumero();
        if (num >= 0 && num < 100 && renouvellementsRestants[num] > 0) {
            renouvellementsRestants[num]--;
        }
    }
    
    static void servirOrdonnance(Ordonnance o) {
        int num = o.getNumero();
        if (num >= 0 && num < 100) {
            if (o.isEstPeriodique() && renouvellementsRestants[num] > 0) {
                renouvellementsRestants[num]--;
                System.out.println("Renouvellement utilise. Reste: " + renouvellementsRestants[num]);
            } else {
                ordonnanceServie[num] = true;
            }
        }
    }
    
    // Début
    
    public static void main(String[] args) {
        // Initialiser les tableaux de suivi
        for (int i = 0; i < 100; i++) {
            ordonnanceServie[i] = false;
            renouvellementsRestants[i] = 0;
            ordonnancePeriodique[i] = false;
            periodiciteOrdonnance[i] = "";
        }
        
        // Charger les données depuis les fichiers
        int[] counts = Sauvegarde.toutCharger(etageres, clients, ordonnances, pharmaciens);
        nbEtageres    = counts[0];
        nbClients     = counts[1];
        nbOrdonnances = counts[2];
        nbPharmaciens = counts[3];

        // Si aucune donnée trouvée, charger les données de test
        if (nbEtageres == 0) {
            System.out.println("Aucune donnee trouvee - chargement des donnees de test.");
            chargerDonneesTest();
        }

        boolean continuer = true;
        while (continuer) {
            afficherMenu();
            int choix = lireEntier("Votre choix : ");
            switch (choix) {
                case 1 -> menuMedicaments();
                case 2 -> menuVentes();
                case 3 -> menuOrdonnances();
                case 4 -> menuPharmaciens();
                case 0 -> continuer = false;
                default -> System.out.println("Choix invalide.");
            }
        }

        // Sauvegarder toutes les données avant de quitter
        Sauvegarde.toutSauvegarder(etageres, nbEtageres, clients, nbClients,
                                    ordonnances, nbOrdonnances, pharmaciens, nbPharmaciens);
        System.out.println("Au revoir !");
        sc.close();
    }

    // Menu principal
    static void afficherMenu() {
        System.out.println("\n");
        System.out.println("   +--  GESTION PHARMACIE  --+   ");
        System.out.println("");
        System.out.println("  1. Medicaments et Etageres   ");
        System.out.println("  2. Ventes                    ");
        System.out.println("  3. Ordonnances              ");
        System.out.println("  4. Pharmaciens              ");
        System.out.println("  0. Quitter                  ");
        System.out.println("");
    }

    // Menu Medicaments et Etageres
    static void menuMedicaments() {
        boolean retour = false;
        while (!retour) {
            System.out.println("\n  ---Medicaments et Etageres--- \n");
            System.out.println(" 1. Afficher toutes les etageres");
            System.out.println(" 2. Chercher un medicament");
            System.out.println(" 3. Ajouter un medicament a une etagere");
            System.out.println(" 4. Retirer un medicament d'une etagere");
            System.out.println(" 0. Retour");
            int choix = lireEntier("Votre choix : ");
            switch (choix) {
                case 1 -> {
                    for (int i = 0; i < nbEtageres; i++) etageres[i].afficherMedicaments();
                }
                case 2 -> {
                    System.out.print("Nom du medicament : ");
                    String nom = sc.nextLine();
                    boolean trouve = false;
                    for (int i = 0; i < nbEtageres; i++) {
                        Medicament m = etageres[i].chercherMedicament(nom);
                        if (m != null) { 
                            m.afficher(); 
                            trouve = true; 
                            break; 
                        }
                    }
                    if (!trouve) System.out.println("Medicament introuvable.");
                }
                case 3 -> {
                    if (nbEtageres == 0) { System.out.println("Aucune etagere disponible."); break; }
                    System.out.print("Numero etagere : ");
                    int num = lireEntier("");
                    Etagere e = trouverEtagere(num);
                    if (e == null) { System.out.println("Etagere introuvable."); break; }
                    System.out.print("Nom commercial : ");    String nom  = sc.nextLine();
                    System.out.print("Forme galenique : ");   String fg   = sc.nextLine();
                    System.out.print("Prix : ");              double prix = lireDouble("");
                    System.out.print("Liste (Liste I, Liste II, Hors liste) : ");  String liste = sc.nextLine();
                    System.out.print("Quantite dispo : ");    int qte  = lireEntier("");
                    Medicament nouveau = new Medicament(num, nom, fg, prix, liste, qte);
                    e.ajouterMedicament(nouveau);
                    if (liste.equals("Liste I")) {
                        System.out.println("Attention: Medicament sous ordonnance obligatoire !");
                    }
                }
                case 4 -> {
                    System.out.print("Numero etagere : ");
                    Etagere e = trouverEtagere(lireEntier(""));
                    if (e == null) { System.out.println("Etagere introuvable."); break; }
                    System.out.print("Nom du medicament a retirer : ");
                    e.retirerMedicament(sc.nextLine());
                }
                case 0 -> retour = true;
                default -> System.out.println("Choix invalide.");
            }
        }
    }

    // Menu Ventes
    static void menuVentes() {
        boolean retour = false;
        while (!retour) {
            System.out.println("\n  ---Ventes---  \n");
            System.out.println(" 1. Vente SANS ordonnance (vente libre)");
            System.out.println(" 2. Vente AVEC ordonnance");
            System.out.println(" 3. Afficher le recu d'un client");
            System.out.println(" 4. Nouveau client");
            System.out.println(" 0. Retour");
            int choix = lireEntier("Votre choix : ");
            switch (choix) {
                case 1 -> venteSansOrdonnance();
                case 2 -> venteAvecOrdonnance();
                case 3 -> afficherRecuClient();
                case 4 -> nouveauClient();
                case 0 -> retour = true;
                default -> System.out.println("Choix invalide.");
            }
        }
    }

    // Vente sans ordonnance
    static void venteSansOrdonnance() {
        System.out.println("\n--- VENTE LIBRE (sans ordonnance) ---");
        System.out.println("Rappel: Medicaments Liste I = ordonnance OBLIGATOIRE");
        System.out.println("        Medicaments Liste II = ordonnance si plus de 2 boites");
        
        System.out.print("ID client : ");
        int idClient = lireEntier("");
        Client c = trouverClient(idClient);
        if (c == null) {
            System.out.println("Client introuvable. Creation d'un nouveau client.");
            c = nouveauClient();
            if (c == null) return;
        }
        
        boolean continuer = true;
        while (continuer) {
            System.out.print("\nNom du medicament : ");
            String nom = sc.nextLine();
            Medicament m = chercherMedicamentPartout(nom);
            
            if (m == null) {
                System.out.println("Medicament introuvable.");
                continue;
            }
            
            // Recuperer la liste du medicament
            String liste = m.getListeMedicament();
            if (liste == null || liste.isBlank()) {
                System.out.print("Quel est le type de liste ? (Liste I, Liste II, Hors liste) : ");
                liste = sc.nextLine();
                m.setListeMedicament(liste);
            }
            
            if (liste.equals("Liste I")) {
                System.out.println("ERREUR: " + m.getNomCommercial() + " est en Liste I - ordonnance OBLIGATOIRE !");
                System.out.println("Vente impossible sans ordonnance.");
                continue;
            }
            
            System.out.print("Quantite : ");
            int qte = lireEntier("");
            
            if (liste.equals("Liste II") && qte > 2) {
                System.out.println("ERREUR: " + m.getNomCommercial() + " (Liste II) - plus de 2 boites necessite une ordonnance !");
                continue;
            }
            
            if (m.getQuantiteDisponible() < qte) {
                System.out.println("Stock insuffisant. Disponible: " + m.getQuantiteDisponible());
                continue;
            }
            
            c.ajouterAchat(new LigneVente(m, qte));
            m.diminuerStock(qte);
            System.out.println("Achat ajoute: " + qte + "x " + m.getNomCommercial());
            
            System.out.print("Ajouter un autre medicament ? (o/n) : ");
            continuer = sc.nextLine().trim().equalsIgnoreCase("o");
        }
        
        System.out.println("\nVente libre enregistree !");
        c.afficherRecu();
    }

    // Vente avec Ordonnance
    static void venteAvecOrdonnance() {
        System.out.println("\n--- VENTE SUR ORDONNANCE ---");
        
        System.out.print("Numero de l'ordonnance : ");
        int numOrdo = lireEntier("");
        Ordonnance o = trouverOrdonnance(numOrdo);
        if (o == null) {
            System.out.println("Ordonnance introuvable.");
            return;
        }
        
        // Vérification Ordonnance
        if (isOrdonnanceServie(o)) {
            if (!o.isEstPeriodique()) {
                System.out.println("ERREUR: Cette ordonnance a deja ete servie !");
                return;
            } else if (getRenouvellementsRestantsOrdo(o) <= 0) {
                System.out.println("ERREUR: Plus de renouvellements disponibles !");
                return;
            } else {
                System.out.println("Ordonnance periodique - Renouvellement possible.");
                System.out.println("Renouvellements restants: " + getRenouvellementsRestantsOrdo(o));
            }
        }
        
        // Verification par le pharmacien
        System.out.print("ID du pharmacien qui verifie : ");
        Pharmacien ph = trouverPharmacien(lireEntier(""));
        if (ph == null) {
            System.out.println("Pharmacien introuvable.");
            return;
        }
        
        boolean ok = ph.verifierOrdonnance(o, etageres);
        if (!ok) {
            System.out.println("Vente annulee par le pharmacien.");
            return;
        }
        
        // Trouver le client
        Client c = trouverClient(o.getIdClient());
        if (c == null) {
            System.out.println("Client associe a l'ordonnance non trouve. Creation d'un nouveau client.");
            System.out.print("Date et heure (ex: 29/04/2026 10:30) : ");
            String dh = sc.nextLine();
            c = new Client(o.getIdClient(), dh);
            clients[nbClients++] = c;
        }
        
        // Enregistrer la vente
        System.out.println("\nDelivrance des medicaments prescrits:");
        for (LigneOrdonnance lo : o.getLignes()) {
            Medicament m = chercherMedicamentPartout(lo.getNomMedicament());
            if (m != null) {
                c.ajouterAchat(new LigneVente(m, lo.getQuantitePrescrite()));
                m.diminuerStock(lo.getQuantitePrescrite());
                System.out.println("  - " + lo.getQuantitePrescrite() + "x " + m.getNomCommercial());
            } else {
                System.out.println("  - Medicament non trouve: " + lo.getNomMedicament());
            }
        }
        
        // Marquer l'ordonnance comme servie
        servirOrdonnance(o);
        
        System.out.println("\nVente sur ordonnance enregistree !");
        c.afficherRecu();
    }

    static void afficherRecuClient() {
        System.out.print("ID client : ");
        Client c = trouverClient(lireEntier(""));
        if (c == null) System.out.println("Client introuvable.");
        else c.afficherRecu();
    }

    static Client nouveauClient() {
        System.out.print("Date et heure (ex: 29/04/2026 10:30) : ");
        String dh = sc.nextLine();
        Client c = new Client(nbClients + 1, dh);
        clients[nbClients++] = c;
        System.out.println("Client n°" + c.getIdClient() + " cree.");
        return c;
    }

    // Menu Ordonnances
    static void menuOrdonnances() {
        boolean retour = false;
        while (!retour) {
            System.out.println("\n--- Ordonnances ---");
            System.out.println("1. Creer une ordonnance");
            System.out.println("2. Afficher une ordonnance");
            System.out.println("3. Vente sur ordonnance");
            System.out.println("4. Gerer les renouvellements");
            System.out.println("5. Afficher toutes les ordonnances");
            System.out.println("0. Retour");
            int choix = lireEntier("Votre choix : ");
            switch (choix) {
                case 1 -> creerOrdonnance();
                case 2 -> {
                    System.out.print("Numero ordonnance : ");
                    Ordonnance o = trouverOrdonnance(lireEntier(""));
                    if (o == null) System.out.println("Ordonnance introuvable.");
                    else {
                        o.afficher();
                        System.out.println("Statut: " + (isOrdonnanceServie(o) ? "Deja servie" : "Non servie"));
                        if (o.isEstPeriodique()) {
                            System.out.println("Renouvellements restants: " + getRenouvellementsRestantsOrdo(o));
                        }
                    }
                }
                case 3 -> venteAvecOrdonnance();
                case 4 -> gererRenouvellements();
                case 5 -> {
                    for (int i = 0; i < nbOrdonnances; i++) {
                        ordonnances[i].afficher();
                        System.out.println("Statut: " + (isOrdonnanceServie(ordonnances[i]) ? "Deja servie" : "Non servie"));
                        if (ordonnances[i].isEstPeriodique()) {
                            System.out.println("Renouvellements restants: " + getRenouvellementsRestantsOrdo(ordonnances[i]));
                        }
                        System.out.println();
                    }
                }
                case 0 -> retour = true;
                default -> System.out.println("Choix invalide.");
            }
        }
    }

    static void gererRenouvellements() {
        System.out.print("Numero de l'ordonnance : ");
        int num = lireEntier("");
        Ordonnance o = trouverOrdonnance(num);
        if (o == null) {
            System.out.println("Ordonnance introuvable.");
            return;
        }
        
        if (!o.isEstPeriodique()) {
            System.out.println("Cette ordonnance n'est pas periodique.");
            return;
        }
        
        System.out.println("Ordonnance n°" + num);
        System.out.println("Deja servie: " + (isOrdonnanceServie(o) ? "Oui" : "Non"));
        System.out.println("Renouvellements restants: " + getRenouvellementsRestantsOrdo(o));
        
        System.out.print("Ajouter des renouvellements ? (o/n) : ");
        if (sc.nextLine().trim().equalsIgnoreCase("o")) {
            System.out.print("Nombre de renouvellements a ajouter : ");
            int ajout = lireEntier("");
            setRenouvellementsRestantsOrdo(o, getRenouvellementsRestantsOrdo(o) + ajout);
            System.out.println("Nouveau total: " + getRenouvellementsRestantsOrdo(o));
        }
    }

    static void creerOrdonnance() {
        System.out.print("ID client : ");
        int idC = lireEntier("");
        
        Client client = trouverClient(idC);
        if (client == null) {
            System.out.println("Client introuvable. Creation d'un nouveau client.");
            System.out.print("Date et heure (ex: 29/04/2026 10:30) : ");
            String dh = sc.nextLine();
            client = new Client(idC, dh);
            clients[nbClients++] = client;
        }
        
        System.out.print("Ordonnance periodique ? (o/n) : ");
        boolean periode = sc.nextLine().trim().equalsIgnoreCase("o");
        String periodStr = "aucune";
        int nouveauNumero = nbOrdonnances + 1;
        
        int nbRenouvs = 0;
        if (periode) {
            System.out.print("Periodicite (ex: mensuelle, trimestrielle) : ");
            periodStr = sc.nextLine();
            System.out.print("Nombre de renouvellements : ");
            nbRenouvs = lireEntier("");
        }
        
        Ordonnance o = new Ordonnance(nouveauNumero, idC, periode, periodStr);
        if (periode) {
            setRenouvellementsRestantsOrdo(o, nbRenouvs);
        }
        
        boolean ajout = true;
        while (ajout) {
            System.out.print("Ajouter un medicament ? (o/n) : ");
            if (!sc.nextLine().trim().equalsIgnoreCase("o")) break;
            System.out.print("  Nom medicament : ");    String nom  = sc.nextLine();
            System.out.print("  Forme galenique : ");   String fg   = sc.nextLine();
            System.out.print("  Quantite prescrite : "); int qte = lireEntier("");
            o.ajouterLigne(new LigneOrdonnance(nom, fg, qte));
        }
        
        ordonnances[nbOrdonnances++] = o;
        System.out.println("Ordonnance n°" + o.getNumero() + " cree.");
    }

    // Menu Pharmacien
    static void menuPharmaciens() {
        boolean retour = false;
        while (!retour) {
            System.out.println("\n--- Pharmaciens ---");
            System.out.println("1. Ajouter un pharmacien");
            System.out.println("2. Afficher tous les pharmaciens");
            System.out.println("3. Afficher un pharmacien");
            System.out.println("0. Retour");
            int choix = lireEntier("Votre choix : ");
            switch (choix) {
                case 1 -> {
                    System.out.print("Nom : ");           String nom = sc.nextLine();
                    System.out.print("Heure arrivee : "); String arr = sc.nextLine();
                    System.out.print("Heure depart : ");  String dep = sc.nextLine();
                    Pharmacien ph = new Pharmacien(nbPharmaciens + 1, nom, arr, dep);
                    pharmaciens[nbPharmaciens++] = ph;
                    System.out.println("Pharmacien n°" + ph.getIdPharmacien() + " ajoute.");
                }
                case 2 -> {
                    for (int i = 0; i < nbPharmaciens; i++) pharmaciens[i].afficher();
                }
                case 3 -> {
                    System.out.print("ID pharmacien : ");
                    Pharmacien ph = trouverPharmacien(lireEntier(""));
                    if (ph == null) System.out.println("Pharmacien introuvable.");
                    else ph.afficher();
                }
                case 0 -> retour = true;
                default -> System.out.println("Choix invalide.");
            }
        }
    }

    // Utilitaires
    
    static Etagere trouverEtagere(int num) {
        for (int i = 0; i < nbEtageres; i++)
            if (etageres[i].getNumero() == num) return etageres[i];
        return null;
    }

    static Client trouverClient(int id) {
        for (int i = 0; i < nbClients; i++)
            if (clients[i].getIdClient() == id) return clients[i];
        return null;
    }

    static Ordonnance trouverOrdonnance(int num) {
        for (int i = 0; i < nbOrdonnances; i++)
            if (ordonnances[i].getNumero() == num) return ordonnances[i];
        return null;
    }

    static Pharmacien trouverPharmacien(int id) {
        for (int i = 0; i < nbPharmaciens; i++)
            if (pharmaciens[i].getIdPharmacien() == id) return pharmaciens[i];
        return null;
    }

    static Medicament chercherMedicamentPartout(String nom) {
        for (int i = 0; i < nbEtageres; i++) {
            Medicament m = etageres[i].chercherMedicament(nom);
            if (m != null) return m;
        }
        return null;
    }

    static int lireEntier(String message) {
        if (!message.isEmpty()) System.out.print(message);
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Entier attendu, reessayez : ");
            }
        }
    }

    static double lireDouble(String message) {
        if (!message.isEmpty()) System.out.print(message);
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Nombre attendu, reessayez : ");
            }
        }
    }

    // Donnees test
    static void chargerDonneesTest() {
        // Etageres
        etageres[nbEtageres++] = new Etagere(1, "Antibiotiques");
        etageres[nbEtageres++] = new Etagere(2, "Analgesiques");
        etageres[nbEtageres++] = new Etagere(3, "Vitamines");

        // Medicaments
        Medicament amox = new Medicament(1, "Amoxicilline", "gelule", 1500.0, "Liste I", 30);
        etageres[0].ajouterMedicament(amox);
        
        Medicament doxy = new Medicament(1, "Doxycycline", "comprime", 1200.0, "Liste I", 20);
        etageres[0].ajouterMedicament(doxy);
        
        Medicament para = new Medicament(2, "Paracetamol", "comprime", 500.0, "Liste II", 50);
        etageres[1].ajouterMedicament(para);
        
        Medicament ibu = new Medicament(2, "Ibuprofene", "comprime", 800.0, "Liste II", 15);
        etageres[1].ajouterMedicament(ibu);
        
        Medicament vitC = new Medicament(3, "Vitamine C", "comprime", 300.0, "Hors liste", 100);
        etageres[2].ajouterMedicament(vitC);
        
        Medicament mag = new Medicament(3, "Magnesium", "comprime", 400.0, "Hors liste", 80);
        etageres[2].ajouterMedicament(mag);

        // Pharmaciens
        pharmaciens[nbPharmaciens++] = new Pharmacien(1, "Dr. Mbida", "08:00", "16:00");

        // Client
        clients[nbClients++] = new Client(1, "29/04/2026 09:15");

        // Ordonnance non periodique
        Ordonnance o1 = new Ordonnance(1, 1, false, "");
        o1.ajouterLigne(new LigneOrdonnance("Amoxicilline", "gelule", 14));
        o1.ajouterLigne(new LigneOrdonnance("Paracetamol", "comprime", 6));
        ordonnances[nbOrdonnances++] = o1;
        setOrdonnanceServie(o1, false);

        // Ordonnance periodique
        Ordonnance o2 = new Ordonnance(2, 1, true, "mensuelle");
        o2.ajouterLigne(new LigneOrdonnance("Doxycycline", "comprime", 30));
        ordonnances[nbOrdonnances++] = o2;
        setOrdonnanceServie(o2, false);
        setRenouvellementsRestantsOrdo(o2, 3);

        System.out.println("\n[Donnees de test chargees]");
        System.out.println("  - Liste I: ordonnance OBLIGATOIRE");
        System.out.println("  - Liste II: ordonnance si > 2 boites");
        System.out.println("  - Hors liste: vente libre");
        System.out.println("  - Ordonnance n°2: periodique avec 3 renouvellements");
        System.out.println("  - Pharmacien: Dr. Mbida (ID: 1)");
        System.out.println();
    }
}