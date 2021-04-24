import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AjoutController {


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
    private Stage dialogStage;

    public void setMainApp (MainApp mainApp){
        this.mainApp = mainApp;
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
            alert.initOwner(dialogStage);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez corriger vos données");
            alert.setContentText(errorMsg);

            alert.showAndWait();

            return false;
        }
    }
    @FXML
    public void ConfirmButton(){

        if(isValid()){
            Etudiant etudiant = new Etudiant();
            etudiant.setNom(nomField.getText());
            etudiant.setPrenom(prenomField.getText());
            etudiant.setPromo((String)promoCombo.getValue());
            etudiant.setAnnee(Integer.parseInt(anneeField.getText()));
            etudiant.setOption((String)optionCombo.getValue());

            ok = true;
            dialogStage.close();
        }
    }
}





