/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Asus
 */
public class Main extends Application {
    private Boolean visible = false;
    private final BorderPane rootPane = new BorderPane();
    @Override
    public void start(Stage stage) {
        HBox hbox1 = new HBox(2);
        Button play = new Button(">");
        play.setMinSize(40, 40);
        Button prev = new Button("<<");
        prev.setMinSize(40, 30);
        Button next = new Button(">>");
        next.setMinSize(40, 30);
        hbox1.getChildren().addAll(prev,play,next);
        
        HBox hbox2 = new HBox(2);
        Button back = new Button("|<");
        back.setMinSize(40, 30);
        Button stop = new Button("||");
        stop.setMinSize(40, 30);
        Button forward = new Button(">|");
        forward.setMinSize(40, 30);
        hbox2.getChildren().addAll(back,stop,forward);
        
        VBox vboxLeft = new VBox(8);
        vboxLeft.getChildren().addAll(hbox1,hbox2);
        
        Label musicTitle = new Label("Lecteur Multimédia VLC");
        Label timeCurrent = new Label("00:00");
        BorderPane rightTop = new BorderPane();
        rightTop.setLeft(musicTitle);
        rightTop.setRight(timeCurrent);
        
        Slider playerSlider = new Slider();
        
        Slider sliderVolume = new Slider();
        BorderPane rightBottom = new BorderPane();
        rightBottom.setLeft(sliderVolume);
        Button equilizer = new Button("|||");
        ToggleButton expandButton = new ToggleButton(":=");
        HBox hbox3 = new HBox(5,equilizer,expandButton);
        rightBottom.setRight(hbox3);
        
        VBox vboxCenter = new VBox(8);
        vboxCenter.getChildren().addAll(rightTop,playerSlider,rightBottom);
        vboxCenter.setPadding(new Insets(0, 0, 0, 10));
        
        BorderPane playerPane = new BorderPane();
        playerPane.setLeft(vboxLeft);
        playerPane.setCenter(vboxCenter);
        
        rootPane.setTop(playerPane);//mettre le lecteur dans le root principale
        
        TreeTableView<musicModel> treeTable = new TreeTableView<>();
        treeTable.setColumnResizePolicy(TreeTableView.CONSTRAINED_RESIZE_POLICY);
        TreeItem<musicModel> rootItem = new TreeItem<>(new musicModel("Orphée","Johan Johaanson","16:00"));
        TreeTableColumn<musicModel,String> musicCol = new TreeTableColumn<>("Musique");
        musicCol.setCellValueFactory(new TreeItemPropertyValueFactory("Musique"));
        TreeTableColumn<musicModel,String> autorCol = new TreeTableColumn<>("Auteur");
        autorCol.setCellValueFactory(new TreeItemPropertyValueFactory("Auteur"));
        TreeTableColumn<musicModel,String> dureeCol = new TreeTableColumn<>("Durée");
        dureeCol.setCellValueFactory(new TreeItemPropertyValueFactory("Duree"));
        treeTable.getColumns().addAll(musicCol,autorCol,dureeCol);
        treeTable.setRoot(rootItem);
        musicModel musique1 = new musicModel("A Pile Of Dust","Johan Johaanson","04:00");
        TreeItem<musicModel> tree1 = new TreeItem<>(musique1);
        musicModel musique2 = new musicModel("How We Left Fordlandia ","Johan Johaanson","12:00");
        TreeItem<musicModel> tree2 = new TreeItem<>(musique2);
        
        rootItem.getChildren().addAll(tree1,tree2);
        
        /*On peut utiliser un borderPane
        * Left (HBox pour les 3 buttons)
        * Center Label
        * Right Textfield
        * Mais je trouve que cette solution avec Toolbar est plus joli niveau interface
        */
        Pane pane1 = new Pane();//pane vide pour occuper l'espace a gauche du toolbox
        HBox.setHgrow(pane1, Priority.ALWAYS);
        Pane pane2 = new Pane();//pane vide pour occuper l'espace a gauche du toolbox
        HBox.setHgrow(pane2, Priority.ALWAYS);
        TextField textField = new TextField();
        textField.setPromptText("Rechercher un titre"); 
        BorderPane expandPane = new BorderPane();
        expandPane.setCenter(treeTable);
        expandPane.setBottom(new ToolBar(new Button("+"),new Button("->"),new Button("-><-"),pane1,new Label("2 éléments"),pane2,textField));
        
        
        
        
        expandButton.setOnAction((event) -> {
            visible = !visible; // changer la visiblité du tableau, on peut utiliser isChecked()
            if(visible){
                expandButton.setStyle("-fx-base : #4286f4");
                rootPane.setCenter(expandPane);
                stage.setMinWidth(534);//pour avoir une interface correcte, Pas demander?
                stage.setHeight(400);
                stage.setMaxHeight(Double.MAX_VALUE);
            }else{
                expandButton.setStyle("");//remettre le style par défault
                rootPane.setCenter(null);//cacher le borderpane, l'implementation du BorderPlane gere  les valeurs null et initialise une valeur par default
                stage.setMaxHeight(133);//Fix Height
                stage.setMinWidth(440);//pour avoir une interface correcte, Pas demander?
            }
        });
        stage.heightProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.intValue() < 200){ //si l'utilisateur redimensionne la fenêtre avec une hauteur inférieure à 200 px, executer l'action du button
                expandButton.fire();
            }
       });
        //FIX height
        stage.setMinHeight(133);
        stage.setMaxHeight(133);
        stage.setMinWidth(440);//pour avoir une interface correcte, Pas demander?
        stage.setScene(new Scene(rootPane));
        stage.setTitle("Lecteur Multimédia VLC");
        stage.show();
    }
    
    public static void main(String[] args) {
	launch(args);
    }
    
}
