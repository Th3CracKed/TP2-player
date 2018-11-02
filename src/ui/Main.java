/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Asus
 */
public class Main extends Application {

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
        HBox hbox3 = new HBox(20);
        hbox3.getChildren().addAll(musicTitle,timeCurrent);
        
        Slider lecteurSlider = new Slider();
        
        Slider sliderVolume = new Slider();
        HBox hbox4 = new HBox(20);
        hbox4.getChildren().addAll(sliderVolume,new HBox(2,new Button("|||"),new Button(":=")));
        
        VBox vboxCenter = new VBox(8);
        vboxCenter.getChildren().addAll(hbox3,lecteurSlider,hbox4);
        vboxCenter.setPadding(new Insets(0, 0, 0, 10));
        
        BorderPane border = new BorderPane();
        border.setLeft(vboxLeft);
        border.setCenter(vboxCenter);
        //TODO FIX Size
        //TODO Readapt layout when changing the width
        stage.setScene(new Scene(border));
        stage.setTitle("Lecteur Multimédia VLC");
        stage.show();
    }
    
    public static void main(String[] args) {
	launch(args);
    }
    
}
