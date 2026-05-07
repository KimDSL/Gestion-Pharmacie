public class Medicament {

    // Attributs
    private int    numEtagere;
    private String nomCommercial;
    private String formeGalenique;
    private double prix;
    private String listeMedicament;
    private int    quantiteDisponible;

    // Constructeur
    public Medicament(int numEtagere, String nomCommercial, String formeGalenique,
                      double prix, String listeMedicament, int quantiteDisponible) {
        this.numEtagere         = numEtagere;
        this.nomCommercial      = nomCommercial;
        this.formeGalenique     = formeGalenique;
        this.prix               = prix;
        this.listeMedicament    = listeMedicament;
        this.quantiteDisponible = quantiteDisponible;
    }

    // Getters
    public int    getNumEtagere() { return numEtagere; }
    public String getNomCommercial() { return nomCommercial; }
    public String getFormeGalenique() { return formeGalenique; }
    public double getPrix() { return prix; }
    public String getListeMedicament() { return listeMedicament; }
    public int    getQuantiteDisponible() { return quantiteDisponible; }

    // Setters
    public void setNumEtagere(int numEtagere) { this.numEtagere = numEtagere; }
    public void setNomCommercial(String nomCommercial) { this.nomCommercial = nomCommercial; }
    public void setFormeGalenique(String formeGalenique) { this.formeGalenique = formeGalenique; }
    public void setPrix(double prix) { this.prix = prix; }
    public void setListeMedicament(String listeMedicament) { this.listeMedicament = listeMedicament; }
    public void setQuantiteDisponible(int quantiteDisponible) { this.quantiteDisponible = quantiteDisponible; }

    
    // Retourne true si le médicament est en stock.

    public boolean estDisponible() {
        return quantiteDisponible > 0;
    }

    // diminue le stock du médicament de la quantité spécifiée, avec vérification de la validité de la quantité et du stock disponible
    public void diminuerStock(int q) {
        if (q <= 0) {
            System.out.println("Erreur : la quantité doit être positive.");
            return;
        }
        if (q > quantiteDisponible) {
            System.out.println("Erreur : stock insuffisant pour " + nomCommercial
                    + " (disponible : " + quantiteDisponible + ", demandé : " + q + ")");
            return;
        }
        quantiteDisponible -= q;
        System.out.println("Stock mis à jour : " + nomCommercial
                + " → " + quantiteDisponible + " restant(s).");
    }

    // Affiche les détails du médicament
    public void afficher() {
        System.out.println("");
        System.out.println("Médicament      : " + nomCommercial);
        System.out.println("Forme galénique : " + formeGalenique);
        System.out.println("Prix            : " + prix + " FCFA");
        System.out.println("Stock           : " + quantiteDisponible);
        System.out.println("Liste           : " + listeMedicament);
        System.out.println("Étagère n°      : " + numEtagere);
        System.out.println("");
    }
}
