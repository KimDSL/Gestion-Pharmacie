import java.util.ArrayList;

public class Client {

    // attributs
    private int    idClient;
    private String dateHeure;
    private double prixTotal;
    private ArrayList<LigneVente> achats;

    // constructeur
    public Client(int idClient, String dateHeure) {
        this.idClient  = idClient;
        this.dateHeure = dateHeure;
        this.prixTotal = 0.0;
        this.achats    = new ArrayList<>();
    }

    // getters
    public int    getIdClient()  { return idClient; }
    public String getDateHeure() { return dateHeure; }
    public double getPrixTotal() { return prixTotal; }

    // Retourne une copie de la liste des achats utilisée par Sauvegarde.java pour écrire dans le fichier
    public ArrayList<LigneVente> getAchats() {
        return new ArrayList<>(achats);
    }

    // Ajoute une ligne de vente à la liste des achats du client et recalculer le total
    public void ajouterAchat(LigneVente lv) {
        achats.add(lv);
        calculerTotal();
    }

    // Recalcule le prix total en sommant tous les sous-totaux
    public void calculerTotal() {
        prixTotal = 0.0;
        for (LigneVente lv : achats) {
            prixTotal += lv.getSousTotal();
        }
    }

    // Affiche le récapitulatif complet de l'achat (reçu)
    public void afficherRecu() {
        System.out.println("");
        System.out.println("          REÇU — CLIENT n°" + idClient);
        System.out.println("  Date/Heure : " + dateHeure);
        System.out.println("");
        if (achats.isEmpty()) {
            System.out.println("  (aucun achat enregistré)");
        } else {
            for (LigneVente lv : achats) {
                lv.afficher();
            }
        }
        System.out.println("");
        System.out.println("  TOTAL      : " + prixTotal + " FCFA");
        System.out.println("");
    }
}
