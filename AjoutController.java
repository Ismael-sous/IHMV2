import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AjoutController implements Initializable {


    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private TextField anneeField;
    @FXML
    private ComboBox promoCombo;
    @FXML
    private ComboBox optionCombo;
    @FXML
    private Label optLabel;

    private MainApp mainApp;

    public void setMainApp (MainApp mainApp){
        this.mainApp = mainApp;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String l3 = "L3";
        String m1 = "M1";
        String m2 = "M2";
        promoCombo.getItems().addAll(l3,m1,m2);
        String opt1 = "Imagerie";
        String opt2 = "Physiologie";
        String opt3 = "Biotechnologie";
        optionCombo.getItems().addAll(opt1,opt2,opt3);
        afficheOption();
    }

    @FXML
    public void back(){
        mainApp.showAccueil();
    }

    @FXML
    private boolean isValid(){

        String errorMsg ="";
        if (nomField.getText() == null || nomField.getText().length() == 0) {
            errorMsg += "Nom invalide\n";
        }
        if (prenomField.getText() == null || prenomField.getText().length() == 0) {
            errorMsg += "Prénom invalide\n";
        }
        if (anneeField.getText() == null || anneeField.getText().length() <= 3 || anneeField.getText().length() >4) {
            errorMsg += "Année de naissance invalide \n";
        } else {
            try {
                Integer.parseInt(anneeField.getText());
            } catch (NumberFormatException e) {
                errorMsg += "Veuillez renseigner une année valide\n";
            }
        }
        if (promoCombo.getValue() == null) {
            errorMsg += "Promotion invalide \n";
        }
        if (promoCombo.getValue() != "L3" && promoCombo.getValue() != null && optionCombo.getValue() == null) {
            errorMsg += "Veuillez sélectionner une option.\n";
        }

        if (errorMsg.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez corriger vos données");
            alert.setContentText(errorMsg);

            alert.showAndWait();

            return false;
        }
    }

    /**
     * Gestion de l'affichage des comboBox en fonction de la promotion sélectionnée
     */
    @FXML
    public void afficheOption() {
        if (promoCombo.getValue() == "L3" || promoCombo.getValue() == null) {
            optionCombo.setVisible(false);
            optLabel.setVisible(false);
            optionCombo.setValue(null);
        } else {
            optionCombo.setVisible(true);
            optLabel.setVisible(true);
        }
    }

    @FXML
    public void ConfirmButton(){
        if(isValid()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("ressources/img/Logo-Master-GPhy.png"));
            alert.setTitle("Confirmation d'ajout");
            alert.setHeaderText("Ajout de l'étudiant");
            alert.setContentText("Souhaitez-vous ajouter cet étudiant ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Etudiant etudiant = new Etudiant(prenomField.getText(),nomField.getText(),Integer.parseInt(anneeField.getText()),(String)promoCombo.getValue(),(String)optionCombo.getValue());
                mainApp.getEtudiantData().add(etudiant);
                reinitialiser();

            }
        }
    }

    @FXML
    public void reinitialiser(){
        prenomField.clear();
        nomField.clear();
        anneeField.clear();
        optionCombo.setValue(null);
        promoCombo.setValue(null);
    }
}





