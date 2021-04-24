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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ListController implements Initializable {
    private int countCB = 0;
    private MainApp mainApp;
    @FXML
    private TableView myTable;
    @FXML
    private Button modifyButton;
    @FXML
    private Button deleteButton;
    @FXML
    private AnchorPane paneList;
    @FXML
    private ImageView returnImage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn prenom = new TableColumn("Prénom");
        TableColumn<Etudiant, String> nom = new TableColumn<Etudiant, String>("Nom");
        TableColumn<Etudiant, String> annee = new TableColumn<>("Année");
        TableColumn<Etudiant, String> promo = new TableColumn<Etudiant, String>("Promotion");
        TableColumn<Etudiant, String> option = new TableColumn<>("Option");
        TableColumn<Etudiant, String> select = new TableColumn<>("Sélectionner");
        //Button modifyButton = (Button) loader.getNamespace().get("modifyButton");
        //Button deleteButton = (Button) loader.getNamespace().get("deleteButton");

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


    @FXML
    private void confirmDelete(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Suppression de l'étudiant");
        alert.setContentText("Souhaites-vous supprimer cet étudiant?");
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

    public void setMainApp (MainApp mainApp){
        this.mainApp = mainApp;
        System.out.println(mainApp);

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
    @FXML
    public void returnAccueil(){
        mainApp.showAccueil();
    }
}

