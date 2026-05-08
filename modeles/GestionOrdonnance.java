import java.util.ArrayList;

public class GestionOrdonnance {

    private ArrayList<Ordonnance> listeOrdonnances = new ArrayList<>();

    // Ajouter
    public void ajouterOrdonnance(Ordonnance o) {
        listeOrdonnances.add(o);
    }

    // Afficher
    public void afficherOrdonnances() {
        for (Ordonnance o : listeOrdonnances) {
            o.afficher();
        }
    }

    // Rechercher
    public Ordonnance rechercher(int numero) {
        for (Ordonnance o : listeOrdonnances) {
            if (o.getNumero() == numero) {
                return o;
            }
        }
        return null;
    }

    // Supprimer
    public void supprimerOrdonnance(int numero) {
        Ordonnance o = rechercher(numero);
        if (o != null) {
            listeOrdonnances.remove(o);
        } else {
            System.out.println("Ordonnance non trouvée !");
        }
    }
}