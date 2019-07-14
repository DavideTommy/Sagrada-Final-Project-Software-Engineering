package it.polimi.se2018.gui;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Objects;

/**
 * MainGraphics Controller extends Application
 * This class manages the graphics
 * @author Davide Lorenzi
 */
public class GUIStarterApp extends Application {

    protected ChoseSchemeController choseSchemeController;
    protected FinalScoreController finalScoreController;
    protected GameBoardController gameBoardController;
    protected IncrementDecrementController incrementDecrementController;
    protected LoginSuccessfulController loginSuccessfulController;
    protected ValueChooserController valueChooserController;

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set. The primary stage will be embedded in
     *                     the browser if the application was launched as an applet.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages and will not be embedded in the browser.
     */

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/gui/layout/login.fxml")));
        primaryStage.setTitle("Sagrada - LogIn");
        primaryStage.setScene(new Scene(root, 500,500));
        primaryStage.show();

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(800.0D),root);
        fadeTransition.setFromValue(0.0D);
        fadeTransition.setToValue(0.8D);
        fadeTransition.play();


        //TODO inizializzazione della prima classe che a catena avia anche le  altre

    }

    /**
     * GUIStarterApp
     * This method initializes the entire GUI
     * @param args are all elements in the graphics scenes
     */
    public static void main(String[] args) {
        launch(args);
    }
}
