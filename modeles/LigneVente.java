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

    // Retourne le médicament
    public Medicament getMedicament() {
        return medicament;
    }

    // Retourne la quantité
    public int getQuantite() {
        return quantite;
    }

    // Retourne le sous-total
    public double getSousTotal() {
        return sousTotal;
    }

    // Afficher les informations
    public void afficher() {
        System.out.println("Nom : " + medicament.getNom());
        System.out.println("Quantité : " + quantite);
        System.out.println("Sous-total : " + sousTotal + " FCFA");
    }
}