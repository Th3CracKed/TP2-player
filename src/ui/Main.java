/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
    
    private Boolean visibility = false;
    private final BorderPane rootPane = new BorderPane();
    private final int FIXED_HEIGHT =  145;
    private final int EXPAND_HEIGHT =  400;
    private final int MIN_WIDTH =  534;
    
    @Override
    public void start(Stage stage) {
        
        VBox vboxLeft = new VBox(8);
        vboxLeft.getChildren().addAll(getHboxButtons1(),getHboxButtons2());
        vboxLeft.setPadding(new Insets(5));
        //BorderPane avec slider a gauche et 2 buttons a droite
        BorderPane rightBottom = new BorderPane();
        rightBottom.setPadding(new Insets(0,5,0,0));
        rightBottom.setLeft(new Slider());
        Button equilizer = new Button("|||");
        equilizer.setMinSize(40, 30);
        ToggleButton expandButton = new ToggleButton(":=");
        expandButton.setMinSize(40, 30);
        rightBottom.setRight(new HBox(5,equilizer,expandButton));
        
        VBox vboxCenter = new VBox(8);
        vboxCenter.getChildren().addAll(getRightTop(),new Slider(),rightBottom);
        
        BorderPane playerPane = new BorderPane();
        playerPane.setLeft(vboxLeft);
        playerPane.setCenter(vboxCenter);
        
        rootPane.setTop(playerPane);//mettre le lecteur dans le root principale
        

        
        BorderPane expandPane = new BorderPane();
        expandPane.setCenter(getTreeTable());
        
       /* Creation du footer
        * On peut utiliser un borderPane :
        * Left (HBox pour les 3 buttons)
        * Center Label
        * Right Textfield
        * Mais je trouve que cette solution avec Toolbar est plus joli niveau interface (et plus delicat à realiser ^^)
        */
        Pane pane1 = new Pane();//pane vide pour occuper l'espace entre les 3 buttons et le label
        HBox.setHgrow(pane1, Priority.ALWAYS);
        Pane pane2 = new Pane();//pane vide pour occuper l'espace entre le label et le textfield
        HBox.setHgrow(pane2, Priority.ALWAYS);
        TextField textField = new TextField();
        textField.setPromptText("Rechercher un titre"); 
        expandPane.setBottom(new ToolBar(new Button("+"),new Button("->"),new Button("-><-"),pane1,new Label("2 éléments"),pane2,textField));
        rootPane.setCenter(expandPane);
        expandPane.setVisible(visibility);
        
        
        
        expandButton.setOnAction((event) -> {
            visibility = !visibility; // changer la visiblité du tableau, on pouvait utiliser isChecked() 
            expandPane.setVisible(visibility);
            if(visibility){
                expandButton.setStyle("-fx-base : #4286f4");
                stage.setHeight(EXPAND_HEIGHT);
                stage.setMaxHeight(Double.MAX_VALUE);
            }else{
                expandButton.setStyle("");//remettre le style par défault
                stage.setMaxHeight(FIXED_HEIGHT);//Fix Height
            }
        });
        
        stage.heightProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.intValue() < 200){ //si l'utilisateur redimensionne la fenêtre avec une hauteur inférieure à 200 px, executer l'action du button
                expandButton.fire();
            }
        });
        //Fixé la hauteur
        stage.setMinHeight(FIXED_HEIGHT);
        stage.setMaxHeight(FIXED_HEIGHT);
        stage.setMinWidth(MIN_WIDTH);//pour avoir une interface correcte, Pas demander?
        stage.setScene(new Scene(rootPane));
        stage.setTitle("Lecteur Multimédia VLC");
        stage.show();
    }
    
    public static void main(String[] args) {
	launch(args);
    }
    
    private HBox getHboxButtons1(){
        HBox hbox1 = new HBox(3);
        hbox1.setAlignment(Pos.CENTER);//pour positioner les buttons dans le centre
        Button prev = new Button("<<");
        prev.setMinSize(40, 30);
        Button play = new Button(">");
        play.setMinSize(50, 50);
        Button next = new Button(">>");
        next.setMinSize(40, 30);
        hbox1.getChildren().addAll(prev,play,next);
        return hbox1;
    }
    
    private HBox getHboxButtons2(){
        HBox hbox2 = new HBox(3);
        Button back = new Button("|<");
        back.setMinSize(40, 30);
        Button stop = new Button("||");
        stop.setMinSize(40, 30);
        Button forward = new Button(">|");
        forward.setMinSize(40, 30);
        hbox2.getChildren().addAll(back,stop,forward);
        return hbox2;
    }
    
    private BorderPane getRightTop(){
        BorderPane rightTop = new BorderPane();
        rightTop.setPadding(new Insets(10, 5, 0, 0));
        rightTop.setLeft(new Label("Lecteur Multimédia VLC"));
        rightTop.setRight(new Label("00:00"));
        return rightTop;
    }
    
    private TreeTableView getTreeTable(){
        TreeTableView<MusicModel> treeTable = new TreeTableView<>();
        treeTable.setColumnResizePolicy(TreeTableView.CONSTRAINED_RESIZE_POLICY);//Pour que le colonnes prennent tout l'espace disponible
        
        //Creation des 3 colonnes 
        TreeTableColumn<MusicModel,String> musicCol = new TreeTableColumn<>("Musique");
        musicCol.setCellValueFactory(new TreeItemPropertyValueFactory("Musique"));
        TreeTableColumn<MusicModel,String> autorCol = new TreeTableColumn<>("Auteur");
        autorCol.setCellValueFactory(new TreeItemPropertyValueFactory("Auteur"));
        TreeTableColumn<MusicModel,String> dureeCol = new TreeTableColumn<>("Durée");
        dureeCol.setCellValueFactory(new TreeItemPropertyValueFactory("Duree"));
        treeTable.getColumns().addAll(musicCol,autorCol,dureeCol);
        
        //remplir le root du tree avec des données
        MusicModel musique1 = new MusicModel("A Pile Of Dust","Johan Johaanson","04:00");
        TreeItem<MusicModel> tree1 = new TreeItem<>(musique1);
        MusicModel musique2 = new MusicModel("How We Left Fordlandia","Johan Johaanson","12:00");
        TreeItem<MusicModel> tree2 = new TreeItem<>(musique2);//Specifier le root
        TreeItem<MusicModel> rootItem = new TreeItem<>(new MusicModel("Orphée","Johan Johaanson","16:00"));
        rootItem.getChildren().addAll(tree1,tree2);
        
        treeTable.setRoot(rootItem);//mettre le root dans le treeTable
        return treeTable;
    }
}
