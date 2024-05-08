package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Deltager;

import java.io.File;

public class LoginVindue extends Stage {

    private TextField txfBrugernavn = new TextField();
    private PasswordField psfKodeord = new PasswordField();

    private Label lblBrugernavn = new Label("Brugernavn :");
    private Label lblKodeOrd = new Label("Kodeord :");


    private Button btnLogin = new Button("Login");
    private Button btnOpretBruger = new Button("Opret Bruger");


    public LoginVindue() {
        this.setTitle("KonferenceAdministrationsSystem");
        BorderPane pane = new BorderPane();
        this.initContent(pane);
        Scene scene = new Scene(pane);
        this.setScene(scene);
        this.setResizable(false);
    }

    // -------------------------------------------------------------------------

    private void initContent(BorderPane pane) {
        GridPane gridPane = new GridPane();
        pane.setCenter(gridPane);
        gridPane.setVgap(25);
        gridPane.setPadding(new Insets(100));
        gridPane.setAlignment(Pos.CENTER);


        File logoFile = new File("src/main/resources/logo.png");
        Image logo = new Image(logoFile.toURI().toString());
        ImageView logoView = new ImageView(logo);
        gridPane.add(logoView, 0, 0);
        logoView.setFitHeight(200);
        logoView.setPreserveRatio(true);

        gridPane.add(txfBrugernavn, 0, 2);
        gridPane.add(psfKodeord, 0, 4);
        txfBrugernavn.setPromptText("Brugernavn");
        psfKodeord.setPromptText("Koderord");

        btnLogin.setDefaultButton(true);
        btnLogin.setOnAction(event -> loginAction());
        btnOpretBruger.setOnAction(event -> VindueManager.visOpretBrugerVindue());
        gridPane.add(btnLogin, 0, 5);
        gridPane.add(btnOpretBruger, 0, 6);
        gridPane.add(lblBrugernavn, 0, 1);
        gridPane.add(lblKodeOrd, 0, 3);

        GridPane.setHalignment(lblBrugernavn, javafx.geometry.HPos.CENTER);
        GridPane.setHalignment(lblKodeOrd, javafx.geometry.HPos.CENTER);
        GridPane.setHalignment(btnLogin, javafx.geometry.HPos.CENTER);
        GridPane.setHalignment(btnOpretBruger, javafx.geometry.HPos.CENTER);
        //GridPane.setHalignment(lblHeading, javafx.geometry.HPos.CENTER);
        GridPane.setHalignment(logoView, javafx.geometry.HPos.CENTER);
    }

    /* Tjekker om dit login er gyldigt samt om det er et Admin login du logger ind med*/
    public void loginAction() {
        if (txfBrugernavn.getText().equalsIgnoreCase("admin") && psfKodeord.getText().equalsIgnoreCase("admin")) {
            this.hide();
            VindueManager.adminVindue.show();
        } else {
            boolean loginValid = true;
            for (Deltager deltager : Controller.getDeltagere()) {
                if (deltager.getBrugernavn().equals(txfBrugernavn.getText())) {
                    if (deltager.getKodeord().equals(psfKodeord.getText())) {
                        DeltagerVindue deltagerVindue = new DeltagerVindue(deltager);
                        this.hide();
                        deltagerVindue.show();
                        return;
                    }
                }
            }
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Forkert brugernavn, eller kodeord");
            alert.showAndWait();
        }
    }
}
