import java.util.ArrayList;
//classe client
public class Client {

    // Attributs privés
    private int idClient;
    private String dateHeure;
    private double prixTotal;
    private ArrayList<LigneVente> achats;

    // Constructeur
    public Client(int id, String dateHeure) {
        this.idClient = id;
        this.dateHeure = dateHeure;
        this.prixTotal = 0.0;
        this.achats = new ArrayList<>();
    }

    // Getter idClient
    public int getIdClient() {
        return idClient;
    }

    // Getter prixTotal
    public double getPrixTotal() {
        return prixTotal;
    }

    // Ajouter un achat
    public void ajouterAchat(LigneVente lv) {
        achats.add(lv);
        calculerTotal(); // mise à jour automatique
    }

    // Calculer le total
    public void calculerTotal() {
        prixTotal = 0.0;

        for (LigneVente lv : achats) {
            prixTotal += lv.getSousTotal();
        }
    }

    // Afficher le reçu complet
    public void afficherRecu() {
        System.out.println("===== RECU CLIENT =====");
        System.out.println("ID Client : " + idClient);
        System.out.println("Date/Heure : " + dateHeure);
        System.out.println("-----------------------");

        for (LigneVente lv : achats) {
            System.out.println(lv);
        }

        System.out.println("-----------------------");
        System.out.println("TOTAL A PAYER : " + prixTotal + " FCFA");
        System.out.println("=======================");
    }
}