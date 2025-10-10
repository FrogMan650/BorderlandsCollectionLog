package com.frogman650;

import java.util.List;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Image icon = new Image(App.class.getResourceAsStream("LK_logo_square.png"));

        FlowPane itemFlowPane = new FlowPane();
        itemFlowPane.setPrefWidth(1080);
        for (int i = 0; i < 50; i++) {
            Button tempButton = new Button("Button " + i);
            itemFlowPane.getChildren().add(tempButton);
        }
        ScrollPane itemScrollPane = new ScrollPane(itemFlowPane);
        itemScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        itemScrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
        Label filterLabel = new Label("Filters");
        VBox filterVBox = new VBox(filterLabel);
        filterVBox.setPrefWidth(200);
        for (int i = 0; i < 50; i++) {
            ToggleButton tempButton = new ToggleButton("Toggle Button " + i);
            filterVBox.getChildren().add(tempButton);
            filterVBox.setAlignment(Pos.CENTER);
        }
        ScrollPane filterScrollPane = new ScrollPane(filterVBox);
        filterScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        filterScrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
        filterScrollPane.setPrefWidth(200);
        Label label1 = new Label("Borderlands Collection Log");
        Label label2 = new Label("Other info here");
        Label label3 = new Label("Link to BL Wiki");
        HBox bannerHBox = new HBox(label1, label2, label3);

        AnchorPane root = new AnchorPane();
        AnchorPane.setTopAnchor(bannerHBox, 0.0);
        AnchorPane.setRightAnchor(bannerHBox, 0.0);
        AnchorPane.setLeftAnchor(bannerHBox, 0.0);

        AnchorPane.setTopAnchor(filterScrollPane, 50.0);
        AnchorPane.setLeftAnchor(filterScrollPane, 0.0);
        AnchorPane.setBottomAnchor(filterScrollPane, 0.0);

        AnchorPane.setTopAnchor(itemScrollPane, 50.0);
        AnchorPane.setRightAnchor(itemScrollPane, 0.0);
        AnchorPane.setLeftAnchor(itemScrollPane, 200.0);
        AnchorPane.setBottomAnchor(itemScrollPane, 0.0);

        root.getChildren().addAll(bannerHBox, filterScrollPane, itemScrollPane);

        Scene scene = new Scene(root, 1280, 720);
        scene.getStylesheets().add(this.getClass().getResource("styles.css").toExternalForm());
        
        stage.setTitle("Borderlands Collection Log");
        stage.getIcons().add(icon);
        stage.setResizable(true);

        stage.setScene(scene);
        stage.show();

        scene.widthProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                label3.setText("Width: " + scene.getWidth());
                itemFlowPane.setPrefWidth(scene.getWidth()-202);
            }
        });
        scene.heightProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                label3.setText("Height: " + scene.getHeight());
            }
        });
    
    }
}
