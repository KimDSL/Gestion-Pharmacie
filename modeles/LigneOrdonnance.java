public class LigneOrdonnance {

    private Medicament medicament;
    private int quantite;

    public LigneOrdonnance(Medicament medicament, int quantite) {
        this.medicament = medicament;
        this.quantite = quantite;
    }

    public Medicament getMedicament() {
        return medicament;
    }

    public int getQuantite() {
        return quantite;
    }

    public void afficher() {
        System.out.println("Médicament : " + medicament.getNomCommercial());
        System.out.println("Quantité : " + quantite);
        System.out.println("----------------------");
    }
}