import java.util.Scanner;

public class LigneVente {

    // Attributs privés
    private Medicament medicament;
    private int quantite;
    private double sousTotal;

    // Constructeur
    public LigneVente(Medicament m, int qte) {
        this.medicament = m;
        this.quantite = qte;
        this.sousTotal = m.getPrix() * qte;
    }

    // Getters
    public Medicament getMedicament() {
        return medicament;
    }

    public int getQuantite() {
        return quantite;
    }

    public double getSousTotal() {
        return sousTotal;
    }

    // Modifier la quantité de la ligne de vente
    public void modifierQuantite(int nouvelleQte) {
        if (nouvelleQte > 0) {
            this.quantite = nouvelleQte;
            this.sousTotal = medicament.getPrix() * this.quantite;
        } else {
            System.out.println("La quantité doit être supérieure à 0.");
        }
    }
    // Afficher les informations de la ligne de vente
    public void afficherUneLigneAchat() {
        System.out.println("Nom : " + medicament.getNomCommercial());
        System.out.println("Quantité : " + quantite);
        System.out.println("Sous-total : " + sousTotal + " FCFA");
    }
}