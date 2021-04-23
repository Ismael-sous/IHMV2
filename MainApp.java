import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

    public class MainApp extends Application {

        private Stage primaryStage;
        private BorderPane rootLayout;
        private ObservableList<Etudiant> data = FXCollections.observableArrayList();

        public MainApp(){
            System.out.println("remplissage");
            data.add(new Etudiant("Jacob", "Smith", 1999, "M1", "Biotech"));
            data.add(new Etudiant("Emma", "Jones", 1998, "M2", "Imagerie"));
        }
        public ObservableList<Etudiant> getEtudiantData(){
            return data;
        }
        public void setData(ObservableList<Etudiant> data) {
            this.data = data;
        }
        @Override
        public void start(Stage primaryStage) {
            this.primaryStage = primaryStage;
            this.primaryStage.setTitle("GphyApp");

            initRootLayout();
            showAccueil();
        }

        /**
         * Initializes the root layout.
         */
        public void initRootLayout() {
            try {
                // Load root layout from fxml file.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class.getResource("FXML/rootLayout.fxml"));
                rootLayout = (BorderPane) loader.load();

                // Show the scene containing the root layout.
                Scene scene = new Scene(rootLayout);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * Shows the person overview inside the root layout.
         */
        public void showAccueil() {
            try {
                // Load person overview.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class.getResource("FXML/accueil.fxml"));
                AnchorPane accueil = loader.load();
                AccueilController controller = loader.getController();
                controller.setMainApp(this);
                // Set person overview into the center of root layout.
                rootLayout.setCenter(accueil);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * Returns the main stage.
         * @return
         */
        public Stage getPrimaryStage() {
            return primaryStage;
        }

        public static void main(String[] args) {
            launch(args);
        }

        public BorderPane getRootLayout() {
            return rootLayout;
        }
    }

