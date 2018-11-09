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
        
        Slider lecteurSlider = new Slider();
        
        Slider sliderVolume = new Slider();
        BorderPane rightBottom = new BorderPane();
        rightBottom.setLeft(sliderVolume);
        Button equilizer = new Button("|||");
        ToggleButton extendBtn = new ToggleButton(":=");
        HBox hbox3 = new HBox(5,equilizer,extendBtn);
        rightBottom.setRight(hbox3);
        
        VBox vboxCenter = new VBox(8);
        vboxCenter.getChildren().addAll(rightTop,lecteurSlider,rightBottom);
        vboxCenter.setPadding(new Insets(0, 0, 0, 10));
        
        BorderPane rootPane = new BorderPane();
        rootPane.setLeft(vboxLeft);
        rootPane.setCenter(vboxCenter);
        //TODO FIX height size
        
        BorderPane expandPane = new BorderPane();
        TreeTableView<musicModel> treeTable = new TreeTableView<>();
        treeTable.setColumnResizePolicy(TreeTableView.CONSTRAINED_RESIZE_POLICY);
        TreeItem<musicModel> root = new TreeItem<>(new musicModel("A Pile Of Dust","Johan Johaanson","4:00"));
        //TODO FIX dureeCol doesn't show up
        TreeTableColumn<musicModel,String> musicCol = new TreeTableColumn<>("Musique");
        musicCol.setCellValueFactory(new TreeItemPropertyValueFactory("Musique"));
        TreeTableColumn<musicModel,String> autorCol = new TreeTableColumn<>("Auteur");
        autorCol.setCellValueFactory(new TreeItemPropertyValueFactory("Auteur"));
        TreeTableColumn<musicModel,String> dureeCol = new TreeTableColumn<>("Durée");
        dureeCol.setCellValueFactory(new TreeItemPropertyValueFactory("Duree"));
        treeTable.getColumns().addAll(musicCol,autorCol,dureeCol);
        treeTable.setRoot(root);
        expandPane.setTop(treeTable);//TODO Replace with TreeViewtable
        Pane pane = new Pane();//pane vide pour occuper l'espace a gauche du toolbox
        HBox.setHgrow(pane, Priority.ALWAYS);
        TextField textField = new TextField();
        textField.setPromptText("Rechercher un titre");
        expandPane.setBottom(new ToolBar(pane,textField));
        
        extendBtn.setOnAction((event) -> {
            visible = !visible; // changer la visiblité du tableau, on peut utiliser isChecked()
            if(visible){
                extendBtn.setStyle("-fx-base : #4286f4");
                rootPane.setBottom(expandPane);
                //TODO increase rootPane (BorderPane) height to match the required size with treeViewTable & TextField
                //TODO Unfix Height size (allow resize)
            }else{
                extendBtn.setStyle("");
                rootPane.setBottom(null);
                //TODO reduce rootPane (BorderPane) height to match the required size without treeViewTable & TextField
                //TODO Fix Height size (allow resize)
            }
        });
        
        stage.setScene(new Scene(rootPane));
        stage.setTitle("Lecteur Multimédia VLC");
        stage.show();
    }
    
    public static void main(String[] args) {
	launch(args);
    }
    
}
