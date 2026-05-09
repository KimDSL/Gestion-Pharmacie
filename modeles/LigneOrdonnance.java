public class LigneOrdonnance {

    // Attributs
    private String nomMedicament;
    private String formeGalenique;
    private int    quantitePrescrite;

    // Constructeurs
    public LigneOrdonnance(String nomMedicament, String formeGalenique, int quantitePrescrite) {
        this.nomMedicament     = nomMedicament;
        this.formeGalenique    = formeGalenique;
        this.quantitePrescrite = quantitePrescrite;
    }

    // Getters
    public String getNomMedicament()    { return nomMedicament; }
    public String getFormeGalenique()   { return formeGalenique; }
    public int    getQuantitePrescrite() { return quantitePrescrite; }


    // Afficher les détails de la ligne d'ordonnance
    public void afficher() {
        System.out.println("  - " + nomMedicament
                + " (" + formeGalenique + ")"
                + " × " + quantitePrescrite);
    }
}
