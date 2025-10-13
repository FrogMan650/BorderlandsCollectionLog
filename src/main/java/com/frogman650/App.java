package com.frogman650;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.awt.Desktop;
import java.io.IOException;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Image icon = new Image(App.class.getResourceAsStream("BLCL_logo_mini.png"));

        Font willowBody = Font.loadFont(getClass().getResourceAsStream("WillowBody-Regular.ttf"), 10);

        FlowPane itemFlowPane = new FlowPane();
        itemFlowPane.setPrefWidth(1080);
        itemFlowPane.setHgap(5);
        itemFlowPane.setVgap(5);
        itemFlowPane.setPadding(new Insets(5));

        // Label testLabel = new Label("Captain Blade's Otto Idol extra words to wrap");
        // testLabel.setMaxWidth(300);
        // testLabel.setWrapText(true);
        // testLabel.setLayoutX(10);
        // testLabel.setLayoutY(5);
        // testLabel.setId("cardLabel");
        // Image image = new Image(getClass().getResourceAsStream("BLCL_logo_mini.png"));
        // ImageView testImageView = new ImageView(image);
        // testImageView.setId("testImage1");
        // testImageView.setLayoutX(280);
        // testImageView.setLayoutY(5);
        // testImageView.setFitHeight(48);
        // testImageView.setFitWidth(48);
        // Image image2 = new Image("https://static.wikia.nocookie.net/borderlands/images/f/fe/Rapier.jpg/revision/latest?cb=20121211225230");
        // ImageView testImageView2 = new ImageView(image2);
        // testImageView2.setFitHeight(162);
        // testImageView2.setFitWidth(288);
        // testImageView2.setLayoutX(2);
        // testImageView2.setLayoutY(2);
        // Rectangle clip = new Rectangle(testImageView2.getFitWidth(), testImageView2.getFitHeight());
        // clip.setArcWidth(20);
        // clip.setArcHeight(20);
        // testImageView2.setClip(clip);
        // Pane testImageView2Pane = new Pane(testImageView2);
        // testImageView2Pane.setId("testImage2");
        // testImageView2Pane.setLayoutX(20);
        // testImageView2Pane.setLayoutY(50);
        // Label testLabel3 = new Label("As I end the refrain, thrust home.");
        // testLabel3.setStyle("-fx-text-fill: red");
        // VBox testVBox = new VBox(testLabel3);
        // testVBox.setAlignment(Pos.CENTER);
        // testVBox.setLayoutX(20);
        // testVBox.setLayoutY(250);
        // Pane testPane = new Pane(testImageView, testImageView2Pane, testVBox, testLabel);
        // testPane.setStyle("-fx-background-color: red; -fx-text-fill: green;\r\n" + //
        //                 "    -fx-pref-height: 470;\r\n" + //
        //                 "    -fx-pref-width: 336;\r\n" + //
        //                 "    -fx-border-color: #3b83cc;\r\n" + //
        //                 "    -fx-border-width: 2;\r\n" + //
        //                 "    -fx-border-style: solid;\r\n" + //
        //                 "    -fx-border-radius: 10;\r\n" + //
        //                 "    -fx-background-insets: 0;\r\n" + //
        //                 "    -fx-background-radius: 10;\r\n" + //
        //                 "    -fx-padding: 0 0 0 0;");

        // itemFlowPane.getChildren().add(testPane);
        // for (int i = 0; i < 50; i++) {
        //     Button tempButton = new Button("Button " + i);
        //     itemFlowPane.getChildren().add(tempButton);
        // }

        Image image2 = new Image("https://static.wikia.nocookie.net/borderlands/images/1/19/Pistol_Torgue_Unkempt_Harold_%28Inspect_View%29.jpg/revision/latest?cb=20170706162939");
        ImageView testImageView2 = new ImageView(image2);
        testImageView2.setFitHeight(162);
        testImageView2.setFitWidth(288);
        Rectangle clip = new Rectangle(testImageView2.getFitWidth(), testImageView2.getFitHeight());
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        testImageView2.setClip(clip);
        Label testLabel1 = new Label("Borderlands 3");
        testLabel1.setId("testLabel1");
        testLabel1.setPrefWidth(204);
        Pane testPane2 = new Pane();
        testPane2.setId("testPane2");
        Label testPane3 = new Label("15");
        testPane3.setId("testPane3");
        HBox testHBox = new HBox(testPane2, testLabel1, testPane3);
        HBox.setMargin(testPane2, new Insets(5, 0, 0, 12));
        HBox.setMargin(testPane3, new Insets(7, 0, 0, 23));
        HBox.setMargin(testLabel1, new Insets(0, 0, 0, 25));
        Label testLabel2 = new Label("Unkempt Harold");
        testLabel2.setId("testLabel2");
        testLabel2.setMinHeight(37);
        testLabel2.setMaxHeight(37);
        int labelCount = testLabel2.getText().length();
        int textSize = 25;
        if (labelCount > 39) {
            textSize = 10;
        } else if (labelCount > 36) {
            textSize = 11;
        } else if (labelCount > 33) {
            textSize = 12;
        } else if (labelCount > 30) {
            textSize = 13;
        } else if (labelCount > 28) {
            textSize = 14;
        } else if (labelCount > 27) {
            textSize = 15;
        } else if (labelCount > 25) {
            textSize = 16;
        } else if (labelCount > 24) {
            textSize = 17;
        } else if (labelCount > 22) {
            textSize = 18;
        } else if (labelCount > 21) {
            textSize = 19;
        } else if (labelCount > 20) {
            textSize = 20;
        } else if (labelCount > 19) {
            textSize = 21;
        } else if (labelCount > 18) {
            textSize = 22;
        } else if (labelCount > 17) {
            textSize = 23;
        } else if (labelCount > 16) {
            textSize = 24;
        }
        testLabel2.setStyle("-fx-font-size: " + textSize + ";");
        Label testLabel3 = new Label("Did I fire six shots, or only five? Three? Seven. Whatever. adding more words for another line and even more for another");
        testLabel3.setId("testLabel3");
        testLabel3.setWrapText(true);
        testLabel3.setMaxHeight(81);
        Label testLabel4 = new Label("Sources");
        testLabel4.setId("testLabel4");
        Label testLabel5 = new Label("Savage Lee\nTorgue Vending Machines\nThird location");
        testLabel5.setWrapText(true);
        testLabel5.setId("testLabel5");
        testLabel5.setMaxHeight(81);
        Image imageBLWiki1 = new Image(getClass().getResourceAsStream("Wiki_logo_mini.png"));
        ImageView imageViewBLWiki1 = new ImageView(imageBLWiki1);
        imageViewBLWiki1.setFitHeight(30);
        imageViewBLWiki1.setFitWidth(60);
        Pane wikiViewPane1 = new Pane(imageViewBLWiki1);
        wikiViewPane1.setStyle("-fx-cursor: hand;");
        ImageView testImageView = new ImageView(imageBLWiki1);
        testImageView.setId("testImageView");
        testImageView.setFitHeight(30);
        testImageView.setFitWidth(60);
        Region testRegion = new Region();
        HBox.setHgrow(testRegion, Priority.ALWAYS);
        HBox testHBox2 = new HBox(wikiViewPane1, testRegion, testImageView);
        testHBox2.setId("testHBox2");
        
        VBox testVBox = new VBox(testHBox, testLabel2, testImageView2, testLabel3, testLabel4, testLabel5, testHBox2);
        testVBox.setId("testVBox");
        testVBox.setAlignment(Pos.TOP_CENTER);
        VBox.setMargin(testLabel2, new Insets(5, 0 ,0, 0));
        VBox.setMargin(testImageView2, new Insets(1, 0 ,0, 0));
        VBox.setMargin(testLabel3, new Insets(0, 0, 0, 0));
        VBox.setMargin(testLabel4, new Insets(0, 0, 0, 0));
        VBox.setMargin(testLabel5, new Insets(0, 0, 0, 0));
        Pane testPane = new Pane(testVBox);
        testPane.setId("testPane");
        itemFlowPane.getChildren().addAll(testPane);

        ScrollPane itemScrollPane = new ScrollPane(itemFlowPane);
        itemScrollPane.setId("itemScrollPane");
        itemScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        itemScrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);

        //Filters
        VBox filterVBox = new VBox();
        filterVBox.setSpacing(1);
        Label filterLabel = new Label("Filters");
        filterLabel.setId("filterLabel");
        Button allButton = new Button("All");
        allButton.setId("all");
        allButton.setMaxWidth(98);
        Button noneButton = new Button("None");
        noneButton.setId("none");
        noneButton.setMaxWidth(98);
        HBox allNoneHBox = new HBox(2);
        allNoneHBox.setAlignment(Pos.CENTER);
        allNoneHBox.getChildren().addAll(allButton, noneButton);
        filterVBox.getChildren().addAll(filterLabel, allNoneHBox);
        ArrayList<ToggleButton> toggleButtonArray = new ArrayList<>();
        Label weaponLabel = new Label("Weapons");
        weaponLabel.setId("filterLabel");
        ToggleButton pistolToggleButton = new ToggleButton("Pistols");
        toggleButtonArray.add(pistolToggleButton);
        ToggleButton smgToggleButton = new ToggleButton("Submachine Guns");
        toggleButtonArray.add(smgToggleButton);
        ToggleButton assaultRifleToggleButton = new ToggleButton("Assault Rifles");
        toggleButtonArray.add(assaultRifleToggleButton);
        ToggleButton shotgunToggleButton = new ToggleButton("Shotguns");
        toggleButtonArray.add(shotgunToggleButton);
        ToggleButton sniperToggleButton = new ToggleButton("Sniper Rifles");
        toggleButtonArray.add(sniperToggleButton);
        ToggleButton launcherToggleButton = new ToggleButton("Rocket Launchers");
        toggleButtonArray.add(launcherToggleButton);
        ToggleButton ordnanceToggleButton = new ToggleButton("Ordnance");
        toggleButtonArray.add(ordnanceToggleButton);
        filterVBox.getChildren().addAll(weaponLabel, pistolToggleButton, smgToggleButton, assaultRifleToggleButton, shotgunToggleButton, 
        sniperToggleButton, launcherToggleButton, ordnanceToggleButton);
        Label equipmentLabel = new Label("Equipment");
        equipmentLabel.setId("filterLabel");
        ToggleButton classModToggleButton = new ToggleButton("Class Mods");
        toggleButtonArray.add(classModToggleButton);
        ToggleButton grenadeToggleButton = new ToggleButton("Grenades");
        toggleButtonArray.add(grenadeToggleButton);
        ToggleButton relicToggleButton = new ToggleButton("Relics");
        toggleButtonArray.add(relicToggleButton);
        ToggleButton shieldToggleButton = new ToggleButton("Shields");
        toggleButtonArray.add(shieldToggleButton);
        ToggleButton ozKitToggleButton = new ToggleButton("Oz Kits");
        toggleButtonArray.add(ozKitToggleButton);
        ToggleButton enhancementToggleButton = new ToggleButton("Enhancements");
        toggleButtonArray.add(enhancementToggleButton);
        ToggleButton repkitToggleButton = new ToggleButton("Repkits");
        toggleButtonArray.add(repkitToggleButton);
        filterVBox.getChildren().addAll(equipmentLabel, classModToggleButton, grenadeToggleButton, relicToggleButton, shieldToggleButton, 
        ozKitToggleButton, enhancementToggleButton, repkitToggleButton);
        Label rarityLabel = new Label("Rarity");
        rarityLabel.setId("filterLabel");
        ToggleButton uniqueToggleButton = new ToggleButton("Unique");
        toggleButtonArray.add(uniqueToggleButton);
        ToggleButton legendaryToggleButton = new ToggleButton("Legendary");
        toggleButtonArray.add(legendaryToggleButton);
        ToggleButton seraphToggleButton = new ToggleButton("Seraph");
        toggleButtonArray.add(seraphToggleButton);
        ToggleButton pearlToggleButton = new ToggleButton("Pearlescent");
        toggleButtonArray.add(pearlToggleButton);
        ToggleButton effervescentToggleButton = new ToggleButton("Effervescent");
        toggleButtonArray.add(effervescentToggleButton);
        filterVBox.getChildren().addAll(rarityLabel, uniqueToggleButton, legendaryToggleButton, seraphToggleButton, pearlToggleButton, effervescentToggleButton);
        Label gameLabel = new Label("Game");
        gameLabel.setId("filterLabel");
        ToggleButton bl1ToggleButton = new ToggleButton("Borderlands");
        toggleButtonArray.add(bl1ToggleButton);
        ToggleButton bl2ToggleButton = new ToggleButton("Borderlands 2");
        toggleButtonArray.add(bl2ToggleButton);
        ToggleButton blTPSToggleButton = new ToggleButton("Borderlands TPS");
        toggleButtonArray.add(blTPSToggleButton);
        ToggleButton bl3ToggleButton = new ToggleButton("Borderlands 3");
        toggleButtonArray.add(bl3ToggleButton);
        ToggleButton bl4ToggleButton = new ToggleButton("Borderlands 4");
        toggleButtonArray.add(bl4ToggleButton);
        filterVBox.getChildren().addAll(gameLabel, bl1ToggleButton, bl2ToggleButton, blTPSToggleButton, bl3ToggleButton, bl4ToggleButton);
        Label miscLabel = new Label("Misc.");
        miscLabel.setId("filterLabel");
        ToggleButton obtainedToggleButton = new ToggleButton("Obtained");
        toggleButtonArray.add(obtainedToggleButton);
        ToggleButton notObtainedToggleButton = new ToggleButton("Not Obtained");
        toggleButtonArray.add(notObtainedToggleButton);
        filterVBox.getChildren().addAll(miscLabel, obtainedToggleButton, notObtainedToggleButton);
        filterVBox.setPrefWidth(200);
        filterVBox.setAlignment(Pos.CENTER);
        allToggleButtonsOn(toggleButtonArray);
        ScrollPane filterScrollPane = new ScrollPane(filterVBox);
        filterScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        filterScrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
        filterScrollPane.setPrefWidth(202);

        //Banner
        Image imageBLWiki = new Image(getClass().getResourceAsStream("Wiki_logo_mini.png"));
        ImageView imageViewBLWiki = new ImageView(imageBLWiki);
        imageViewBLWiki.setFitHeight(48);
        imageViewBLWiki.setFitWidth(87);
        Pane wikiViewPane = new Pane(imageViewBLWiki);
        wikiViewPane.setStyle("-fx-cursor: hand;");
        Label label2 = new Label("Other info here");
        Label label3 = new Label("Other info here");
        VBox sizeVBox = new VBox(label2, label3);
        HBox bannerHBox = new HBox(5, wikiViewPane, sizeVBox);
        bannerHBox.setId("bannerBox");

        AnchorPane root = new AnchorPane();
        root.setId("anchorPane");
        AnchorPane.setTopAnchor(bannerHBox, 0.0);
        AnchorPane.setRightAnchor(bannerHBox, 0.0);
        AnchorPane.setLeftAnchor(bannerHBox, 0.0);

        AnchorPane.setTopAnchor(filterScrollPane, 48.0);
        AnchorPane.setLeftAnchor(filterScrollPane, 0.0);
        AnchorPane.setBottomAnchor(filterScrollPane, 0.0);

        AnchorPane.setTopAnchor(itemScrollPane, 48.0);
        AnchorPane.setRightAnchor(itemScrollPane, 0.0);
        AnchorPane.setLeftAnchor(itemScrollPane, 202.0);
        AnchorPane.setBottomAnchor(itemScrollPane, 0.0);

        root.getChildren().addAll(bannerHBox, filterScrollPane, itemScrollPane);

        // Parent testRoot = FXMLLoader.load(getClass().getResource("scene copy.fxml"));
        // Scene scene = new Scene(testRoot);

        Scene scene = new Scene(root, 1280, 720);
        scene.getStylesheets().add(this.getClass().getResource("styles.css").toExternalForm());
        
        stage.setTitle("Borderlands Collection Log");
        stage.getIcons().add(icon);
        stage.setResizable(true);

        stage.setScene(scene);
        stage.show();
        System.out.println(testPane3.getWidth());

        scene.widthProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                label2.setText("Width: " + scene.getWidth());
                itemFlowPane.setPrefWidth(scene.getWidth()-210);
            }
        });
        scene.heightProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                label3.setText("Height: " + scene.getHeight());
            }
        });
        allButton.setOnAction(event -> {
            allToggleButtonsOn(toggleButtonArray);
        });
        noneButton.setOnAction(event -> {
            allToggleButtonsOff(toggleButtonArray);
        });

        wikiViewPane.setOnMouseClicked(event -> {
            try {
                Desktop.getDesktop().browse(new URI("https://borderlands.fandom.com/wiki/Borderlands_Wiki"));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        });
    }

    public static void allToggleButtonsOn(ArrayList<ToggleButton> toggleButtonArray) {
        for (int i = 0; i < toggleButtonArray.size(); i++) {
                toggleButtonArray.get(i).setSelected(true);
            }
    }

    public static void allToggleButtonsOff(ArrayList<ToggleButton> toggleButtonArray) {
        for (int i = 0; i < toggleButtonArray.size(); i++) {
                toggleButtonArray.get(i).setSelected(false);
            }
    }
}
