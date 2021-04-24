import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Classe ListController: permet d'afficher et de gérer la liste des étudiants
 * @date 04/2021
 * @author A. Calmont, J. Trouve, I. Sousane
 */
public class ListController implements Initializable {
    /**
     * Compte le nombre de checkBox cochée(s)
     */
    private int countCB = 0;

    /**
     * Stockage de l'instance de la classe MainApp
     */
    private MainApp mainApp;

    /**
     * Tableau affichant les étudiants
     */
    @FXML
    private TableView myTable;

    /**
     * Bouton de modification des données d'un étudiant
     */
    @FXML
    private Button modifyButton;

    /**
     * Bouton de suppression d'un étudiant de la liste
     */
    @FXML
    private Button deleteButton;

    /**
     * Initialisation de l'affichage des boutons et du tableau de la liste des étudiants
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn prenom = new TableColumn("Prénom");
        TableColumn nom = new TableColumn<Etudiant, String>("Nom");
        TableColumn annee = new TableColumn<>("Année");
        TableColumn promo = new TableColumn<Etudiant, String>("Promotion");
        TableColumn option = new TableColumn<>("Option");
        TableColumn select = new TableColumn<>("Sélectionner");

        //par défaut les boutons sont désactivés tant que l'utilisateur ne sélectionne pas d'étudiants dans la liste
        modifyButton.setDisable(true);
        deleteButton.setDisable(true);

        myTable.getColumns().addAll(prenom, nom, annee, promo, option, select);

        prenom.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("prenom"));
        nom.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("nom"));
        annee.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("annee"));
        promo.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("promo"));
        option.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("option"));
        select.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("select"));

    }

    /**
     * Permet de confirmer la suppression de l'étudiant ou des étudiants sélectionné(s)
     * @param event
     * @throws IOException
     */
    @FXML
    private void confirmDelete(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("ressources/img/delete.png"));
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Suppression de l'étudiant");
        alert.setContentText("Souhaitez-vous supprimer cet étudiant ?");
        Optional<ButtonType> result = alert.showAndWait();
        ObservableList<Etudiant> dataListRemove = FXCollections.observableArrayList();
        if (result.get() == ButtonType.OK) {
            ObservableList<Etudiant> data = myTable.getItems();
            for(Etudiant etu : data)
            {
                if(!etu.getSelect().isSelected()){
                    dataListRemove.add(etu);
                }
            }
            mainApp.setData(dataListRemove);
            myTable.setItems(mainApp.getEtudiantData());
        }
    }

    /**
     * Permet de lancer la modification des informations d'un étudiant
     */
    @FXML
    public void modify() {
        boolean okClicked = false;
        Etudiant selectedEtu;
        for (Etudiant etu : mainApp.getEtudiantData()) {
            if (etu.getSelect().isSelected()) {
                selectedEtu = etu;
                mainApp.getEtudiantData().remove(etu);
                okClicked = mainApp.etuEdit(selectedEtu);
                mainApp.getEtudiantData().add(selectedEtu);
                break;
            }
        }
        if (okClicked) {
            myTable.setItems(mainApp.getEtudiantData());

        }
    }

    /**
     * Permet de passer l'instance du main
     * Remplissage de la liste des étudiants
     * Gestion de la disponibilité des boutons en fonction de la sélection de l'utilisateur
     * @param mainApp instance du main
     */
    public void setMainApp (MainApp mainApp){
        this.mainApp = mainApp;

        // Add observable list data to the table
        myTable.setItems(mainApp.getEtudiantData());
        for (Etudiant etu : mainApp.getEtudiantData()) {
            etu.getSelect().setOnAction(actionEvent -> {
                deleteButton.setDisable(false);
                if (etu.getSelect().isSelected()) {
                    countCB += 1;
                } else countCB -= 1;
                if (countCB == 1) {
                    modifyButton.setDisable(false);
                } else if (countCB == 0) {
                    modifyButton.setDisable(true);
                    deleteButton.setDisable(true);
                } else modifyButton.setDisable(true);
            });
        }
    }

    /**
     * Permet de retourner sur la page d'accueil
     */
    @FXML
    public void returnAccueil(){
        mainApp.showAccueil();
    }
}