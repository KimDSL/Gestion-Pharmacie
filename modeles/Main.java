import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // Listes pour stocker les données en mémoire
    static ArrayList<Etagere> pharmacie = new ArrayList<>();
    static ArrayList<Client> historiqueClients = new ArrayList<>();
    static Pharmacien pharmacienGarde;
    static int compteurClientId = 1;

    public static void main(String[] args) {
        initialiserDonnees();
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        System.out.println("================================================");
        System.out.println("  Bienvenue dans le Système de Gestion de Pharmacie  ");
        System.out.println("================================================\n");

        while (continuer) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Voir l'inventaire complet");
            System.out.println("2. Traiter un nouveau client (Vente)");
            System.out.println("3. Vérifier une ordonnance");
            System.out.println("4. Voir l'historique des ventes");
            System.out.println("0. Quitter");
            System.out.print("Choix : ");

            String choix = scanner.nextLine();

            switch (choix) {
                case "1":
                    voirInventaire();
                    break;
                case "2":
                    traiterClient(scanner);
                    break;
                case "3":
                    verifierOrdonnanceTest();
                    break;
                case "4":
                    voirHistorique();
                    break;
                case "0":
                    System.out.println("Fermeture du programme...");
                    continuer = false;
                    break;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        }
        scanner.close();
    }

    // 1. Initialiser avec de fausses données
    public static void initialiserDonnees() {
        // Création du pharmacien
        pharmacienGarde = new Pharmacien(1, "Dr. Dupont", "08:00", "20:00");

        // Création des étagères
        Etagere etagere1 = new Etagere(1, "Douleurs et Fièvre");
        Etagere etagere2 = new Etagere(2, "Antibiotiques");

        // Ajout de médicaments
        etagere1.ajouterMedicament(new Medicament("Paracetamol", "Comprimés", 1500, 50));
        etagere1.ajouterMedicament(new Medicament("Ibuprofene", "Comprimés", 2000, 30));
        
        etagere2.ajouterMedicament(new Medicament("Amoxicilline", "Gélules", 3500, 20));

        pharmacie.add(etagere1);
        pharmacie.add(etagere2);
    }

    // 2. Voir l'inventaire
    public static void voirInventaire() {
        System.out.println("\n=== INVENTAIRE DE LA PHARMACIE ===");
        for (Etagere e : pharmacie) {
            e.afficherMedicaments();
            System.out.println("-------------------------");
        }
    }

    // 3. Traiter un client
    public static void traiterClient(Scanner scanner) {
        String dateHeure = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        Client client = new Client(compteurClientId++, dateHeure);
        boolean achatEnCours = true;

        System.out.println("\n--- Vente au Client ID " + client.getIdClient() + " ---");

        while (achatEnCours) {
            System.out.print("Entrez le nom du médicament à acheter (ou 'fin' pour terminer) : ");
            String nomMed = scanner.nextLine();

            if (nomMed.equalsIgnoreCase("fin")) {
                achatEnCours = false;
                continue;
            }

            // Chercher le médicament dans toute la pharmacie
            Medicament medTrouve = null;
            for (Etagere e : pharmacie) {
                medTrouve = e.chercherMedicament(nomMed);
                if (medTrouve != null) break;
            }

            if (medTrouve != null) {
                System.out.print("Quantité souhaitée : ");
                try {
                    int qte = Integer.parseInt(scanner.nextLine());

                    if (medTrouve.estDispo(qte)) {
                        medTrouve.diminuerStock(qte);
                        client.ajouterAchat(new LigneVente(medTrouve, qte));
                        System.out.println("Ajouté au panier !");
                    } else {
                        System.out.println("Désolé, stock insuffisant. Stock actuel: " + medTrouve.getStock());
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Veuillez entrer un nombre valide.");
                }
            } else {
                System.out.println("Médicament non trouvé.");
            }
        }

        if (client.getPrixTotal() > 0) {
            historiqueClients.add(client);
            client.afficherRecu();
        } else {
            System.out.println("Aucun achat effectué.");
        }
    }

    // 4. Tester la vérification d'une ordonnance
    public static void verifierOrdonnanceTest() {
        System.out.println("\n=== VÉRIFICATION ORDONNANCE ===");
        Ordonnance ord = new Ordonnance(101, 2, false, "");
        ord.ajouterLigne(new LigneOrdonnance("Amoxicilline", "Gélules", 1));
        
        pharmacienGarde.afficher();
        pharmacienGarde.verifierOrdonnance(ord);
        System.out.println("Vérification terminée.");
    }

    // 5. Historique
    public static void voirHistorique() {
        System.out.println("\n=== HISTORIQUE DES VENTES ===");
        if (historiqueClients.isEmpty()) {
            System.out.println("Aucune vente pour le moment.");
        } else {
            for (Client c : historiqueClients) {
                c.afficherRecu();
            }
        }
    }
}
