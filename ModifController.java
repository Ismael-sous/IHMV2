import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifController implements Initializable {

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

    private Stage dialogStage;
    private Etudiant etudiant;
    private boolean ok = false;

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

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setEtu(Etudiant etu){
        this.etudiant = etu;

        nomField.setText(etudiant.getNom());
        prenomField.setText(etudiant.getPrenom());
        promoCombo.setValue(etudiant.getPromo());
        optionCombo.setValue(etudiant.getOption());
        anneeField.setText(Integer.toString(etudiant.getAnnee()));
        afficheOption();
    }

    public boolean isOkClicked(){
        return ok;
    }

    public void confirm(){

        if(isValid()){
            etudiant.setNom(nomField.getText());
            etudiant.setPrenom(prenomField.getText());
            etudiant.setPromo((String)promoCombo.getValue());
            etudiant.setAnnee(Integer.parseInt(anneeField.getText()));
            etudiant.setOption((String)optionCombo.getValue());

            ok = true;
            dialogStage.close();
        }
    }

    public boolean isValid(){
        String errorMsg ="";
        if (nomField.getText() == null || nomField.getText().length() == 0) {
            errorMsg += "Erreur : Nom invalide.\n";
        }
        if (prenomField.getText() == null || prenomField.getText().length() == 0) {
            errorMsg += "Erreur: Prénom invalide.\n";
        }
        if (anneeField.getText() == null || anneeField.getText().length() <= 3) {
            errorMsg += "Erreur: naissance invalide. \n";
        } else {
            try {
                Integer.parseInt(anneeField.getText());
            } catch (NumberFormatException e) {
                errorMsg += "Erreur: Veuillez renseigner une année valide\n";
            }
        }
        if (promoCombo.getValue() == null) {
            errorMsg += "Erreur: promotion invalide. \n";
        }
        if (promoCombo.getValue() != "L3" && promoCombo.getValue() != null && optionCombo.getValue() == null) {
            errorMsg += "Erreur: Veuillez sélectionner une option.\n";
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
    private void cancel(){
        dialogStage.close();
    }
}
