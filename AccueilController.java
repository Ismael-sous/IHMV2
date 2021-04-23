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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;


/**
 *
 * @author 2.1
 * @version 22/03/2020
 */
public class AccueilController
{
    private MainApp mainApp ;
    private ObservableList<Etudiant> data;

    @FXML
    private TableView myTable;
    @FXML
    private Button listButton;
    @FXML
    private Button addButton;
    @FXML private Button yesDeleteButton;

    //

    public AccueilController(){

    }

    @FXML
    public void list()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("FXML/listeEtu.fxml"));
            AnchorPane listeEtu = (AnchorPane) loader.load();
            ListController controller = loader.getController();
            controller.setMainApp(mainApp);
            mainApp.getRootLayout().setCenter(listeEtu);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void addStudent(MouseEvent event) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("FXML/ajout.fxml"));
            AnchorPane listeEtu = (AnchorPane) loader.load();
            AjoutController controller = loader.getController();
            controller.setMainApp(mainApp);
            mainApp.getRootLayout().setCenter(listeEtu);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    /*@FXML
    private void deleteEtu(ActionEvent event) throws IOException {
        ObservableList<Etudiant> dataListRemove = FXCollections.observableArrayList();


        for(Etudiant etu : mainApp.getData())
        {
            if(etu.getSelect().isSelected()){
                System.out.println("passé");
                dataListRemove.add(etu);
            }
        }
        mainApp.setData(dataListRemove);
        Stage stageDialog = (Stage) yesDeleteButton.getScene().getWindow();
        stageDialog.close();
        list(true,event);
    }*/


    @FXML
    private void modify(){

    }

    public void back(MouseEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("FXML/accueil.fxml")));
            Stage stage = new Stage();
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        //myTable.setItems(mainApp.getData());
    }
}
