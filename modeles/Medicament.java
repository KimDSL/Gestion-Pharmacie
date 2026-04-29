public class Medicament {

    // Attributs
    private int numEtagere;
    private String nomCommercial;
    private String formeGalenique;
    private double prix;
    private int quantiteDisponible;

    // Constructeur complet
    public Medicament(int numEtagere, String nomCommercial, String formeGalenique, double prix, int quantiteDisponible) {
        this.numEtagere = numEtagere;
        this.nomCommercial = nomCommercial;
        this.formeGalenique = formeGalenique;
        this.prix = prix;
        this.quantiteDisponible = quantiteDisponible;
    }

    // Getters
    public String getNomCommercial() {
        return nomCommercial;
    }

    public double getPrix() {
        return prix;
    }

    public int getQuantiteDisponible() {
        return quantiteDisponible;
    }

    // Setter
    public void setQuantiteDisponible(int q) {
        this.quantiteDisponible = q;
    }

    // Vérifie disponibilité
    public boolean estDisponible() {
        return quantiteDisponible > 0;
    }

    // Diminuer stock
    public void diminuerStock(int q) {
        if (q <= quantiteDisponible) {
            quantiteDisponible -= q;
        } else {
            System.out.println("Stock insuffisant !");
        }
    }

    // Affichage
    public void afficher() {
        System.out.println("Nom : " + nomCommercial);
        System.out.println("Forme : " + formeGalenique);
        System.out.println("Prix : " + prix);
        System.out.println("Quantité : " + quantiteDisponible);
        System.out.println("Étagère : " + numEtagere);
        System.out.println("-------------------------");
    }
}