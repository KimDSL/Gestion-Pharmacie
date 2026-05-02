public class LigneVente {

    // Attributs
    private Medicament medicament;
    private int        quantite;
    private double     sousTotal;

    // Constructeur
    public LigneVente(Medicament medicament, int quantite) {
        this.medicament = medicament;
        this.quantite   = quantite;
        this.sousTotal  = medicament.getPrix() * quantite;
    }

    // Getters
    public Medicament getMedicament() { return medicament; }
    public int        getQuantite()   { return quantite; }
    public double     getSousTotal()  { return sousTotal; }

    // afficher les détails de la ligne de vente
    public void afficher() {
        System.out.println("  - " + medicament.getNomCommercial()
                + " x" + quantite
                + " → " + sousTotal + " FCFA");
    }
}
