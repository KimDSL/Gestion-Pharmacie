import java.util.ArrayList;
import java.time.LocalDateTime;
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

    // Getters
    public int getIdClient() {
        return idClient;
    }
    public String getDateHeure() {
        return dateHeure;
    }
    public double getPrixTotal() {
        return prixTotal;
    }

    // Setters
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    // Ajouter un achat
    public void ajouterAchat(LigneVente lv) {
        achats.add(lv);
        calculerTotal(); // mise à jour automatique
    }

    // Supprimer un achat
    public void supprimerAchat(String nomMedicament) {
        LigneVente ligneASupprimer = null;
        for (LigneVente lv : achats) {
            if (lv.getMedicament() != null && lv.getMedicament().getNomCommercial().equalsIgnoreCase(nomMedicament)) {
                ligneASupprimer = lv;
                break;
            }
        }
        if (ligneASupprimer != null) {
            achats.remove(ligneASupprimer);
            calculerTotal(); // mise à jour automatique
            System.out.println("Achat de " + nomMedicament + " supprimé.");
        } else {
            System.out.println("Médicament " + nomMedicament + " non trouvé dans les achats.");
        }
    }

    // Modifier la quantité d'un achat
    public void modifierAchat(String nomMedicament, int nouvelleQuantite) {
        for (LigneVente lv : achats) {
            if (lv.getMedicament() != null && lv.getMedicament().getNomCommercial().equalsIgnoreCase(nomMedicament)) {
                lv.modifierQuantite(nouvelleQuantite);
                calculerTotal(); // mise à jour automatique
                System.out.println("Quantité de " + nomMedicament + " modifiée à " + nouvelleQuantite + ".");
                return;
            }
        }
        System.out.println("Médicament " + nomMedicament + " non trouvé dans les achats.");
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