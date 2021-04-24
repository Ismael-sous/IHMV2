import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

/**
 * Classe Etudiant: création d'un étudiant
 * @date 04/2021
 * @author A. Calmont, I. Sousane, J. Trouve
 */
public class Etudiant {
    /**
     * Prénom de l'étudiant
     */
    private SimpleStringProperty prenom;

    /**
     * Nom de l'étudiant
     */
    private SimpleStringProperty nom;

    /**
     * Année de naissance de l'étudiant
     */
    private SimpleIntegerProperty annee;

    /**
     * Promotion de l'étudiant (L3, M1 ou M2)
     */
    private SimpleStringProperty promo;

    /**
     * Option de l'étudiant (Physiologie, Biotechnologie, Imagerie)
     */
    private SimpleStringProperty option;

    /**
     * CheckBox liée à l'étudiant pour la sélection
     */
    private CheckBox select;

    /**
     *
     * @param prenom prénom de l'étudiant
     * @param nom nom de l'étudiant
     * @param annee année de naissance de l'étudiant
     * @param promo promotion de l'étudiant
     * @param option option de l'étudiant
     */
    public Etudiant(String prenom, String nom, int annee, String promo, String option) {
        this.prenom = new SimpleStringProperty(prenom);
        this.nom = new SimpleStringProperty(nom);
        this.annee = new SimpleIntegerProperty(annee);
        this.promo = new SimpleStringProperty(promo);
        this.option = new SimpleStringProperty(option);
        this.select = new CheckBox();
    }

    public CheckBox getSelect() {
        return select;
    }

    public void setSelect(CheckBox select) {
        this.select = select;
    }

    public String getPrenom() {
        return prenom.get();
    }

    public String getNom() {
        return nom.get();
    }

    public int getAnnee() {
        return annee.get();
    }

    public String getPromo() {
        return promo.get();
    }

    public String getOption() {
        return option.get();
    }

    public void setPrenom(String prenom) {
        this.prenom.set(prenom);
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public void setAnnee(int annee) {
        this.annee.set(annee);
    }

    public void setPromo(String promo) {
        this.promo.set(promo);
    }

    public void setOption(String option) {
        this.option.set(option);
    }
}
