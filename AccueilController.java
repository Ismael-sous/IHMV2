import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;


/**
 * Classe AccueilController : gestion de l'accueil de l'application
 * @date 04/2021
 * @author A. Calmont, I. Sousane, J. Trouve
 */
public class AccueilController
{
    private MainApp mainApp ;

    /**
     * Changement de fenêtre pour l'affichage de la liste des étudiants
     */
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

    /**
     * Changement de fenêtre pour l'affichage de l'ajout d'un étudiant
     * @param event
     */
    @FXML
    private void addStudent(MouseEvent event) {
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

    /**
     * Permet de passer l'instance du main
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
