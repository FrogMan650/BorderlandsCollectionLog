package com.frogman650;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.awt.event.ActionEvent;
import java.io.File;
import java.net.URI;
import java.nio.file.Paths;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.AccessibleRole;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.skin.VirtualContainerBase;
import javafx.scene.control.skin.VirtualFlow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application {
    public static Image icon;
    public static Image wikiImage;
    public static Image obtainedImage;
    public static Image notObtainedImage;
    public static Image pistolImage;
    public static Image arImage;
    public static Image classModImage;
    public static Image grenadeOrdnanceImage;
    public static Image heavyOrdnanceImage;
    public static Image grenadeImage;
    public static Image laserImage;
    public static Image launcherImage;
    public static Image ozKitImage;
    public static Image shieldImage;
    public static Image shotgunImage;
    public static Image smgImage;
    public static Image sniperImage;
    public static Image relicImage;
    public static Image eridianImage;
    public static Image repkitImage;
    public static Image enhancementImage;
    public static Image effervescentBackground;
    public static FlowPane itemFlowPane;
    public static ScrollPane itemScrollPane;
    public static TextField searchTextField;
    public static Document itemDocument;
    public static NodeList itemNodes;
    public static ArrayList<Pane> itemCardArray = new ArrayList<>();
    public static ArrayList<Pane> itemCardFilteredArray = new ArrayList<>();
    public static ArrayList<ToggleButton> toggleButtonArray = new ArrayList<>();
    public static File itemsXML;
    public static HostServices hostService;

    private final long[] frameTimes = new long[100];
    private int frameTimeIndex = 0;
    private boolean arrayFilled = false;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Font willowBody = Font.loadFont(getClass().getResourceAsStream("WillowBody-Regular.ttf"), 10);
        icon = new Image(getClass().getResourceAsStream("BLCL_logo_mini.png"));
        wikiImage = new Image(getClass().getResourceAsStream("Wiki_logo_mini.png"));
        obtainedImage = new Image(getClass().getResourceAsStream("obtained.png"));
        notObtainedImage = new Image(getClass().getResourceAsStream("not_obtained.png"));
        pistolImage = new Image(getClass().getResourceAsStream("pistol.png"));
        arImage = new Image(getClass().getResourceAsStream("ar.png"));
        classModImage = new Image(getClass().getResourceAsStream("class_mod.png"));
        grenadeOrdnanceImage = new Image(getClass().getResourceAsStream("grenade_ordnance.png"));
        heavyOrdnanceImage = new Image(getClass().getResourceAsStream("heavy_ordnance.png"));
        grenadeImage = new Image(getClass().getResourceAsStream("grenade.png"));
        laserImage = new Image(getClass().getResourceAsStream("laser.png"));
        launcherImage = new Image(getClass().getResourceAsStream("launcher.png"));
        ozKitImage = new Image(getClass().getResourceAsStream("oz_kit.png"));
        shieldImage = new Image(getClass().getResourceAsStream("shield.png"));
        shotgunImage = new Image(getClass().getResourceAsStream("shotgun.png"));
        smgImage = new Image(getClass().getResourceAsStream("smg.png"));
        sniperImage = new Image(getClass().getResourceAsStream("sniper.png"));
        relicImage = new Image(getClass().getResourceAsStream("relic.png"));
        eridianImage = new Image(getClass().getResourceAsStream("eridian.png"));
        repkitImage = new Image(getClass().getResourceAsStream("repkit.png"));
        enhancementImage = new Image(getClass().getResourceAsStream("enhancement.png"));
        effervescentBackground = new Image(getClass().getResourceAsStream("effervescent_rev2.gif"));
        hostService = getHostServices();

        URI uri = getClass().getProtectionDomain().getCodeSource().getLocation().toURI();
        File executableDirectory = Paths.get(uri).getParent().toFile();
        itemsXML = new File(executableDirectory, "items.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        itemDocument = builder.parse(itemsXML);
        itemNodes = itemDocument.getDocumentElement().getElementsByTagName("item");

        //Item cards holder
        itemFlowPane = new FlowPane();
        itemFlowPane.setId("itemFlowPane");
        itemFlowPane.setPadding(new Insets(0));

        itemScrollPane = new ScrollPane(itemFlowPane);
        itemScrollPane.setId("itemScrollPane");

        //Filters
        searchTextField = new TextField();
        searchTextField.setId("searchTextField");
        searchTextField.setPromptText("Search Item Name");
        VBox.setMargin(searchTextField, new Insets(0, 0, 0, 1));
        VBox filterVBox = new VBox();
        filterVBox.setSpacing(1);
        Label filterLabel = new Label("FILTERS");
        filterLabel.setId("filterLabel");
        Button allButton = new Button("All");
        allButton.setId("all");
        allButton.setOnAction(event -> {
            allToggleButtonsOn(toggleButtonArray);
            resetDisplayedCards(searchTextField.getText());
        });
        Button noneButton = new Button("None");
        noneButton.setId("none");
        noneButton.setOnAction(event -> {
            allToggleButtonsOff(toggleButtonArray);
            resetDisplayedCards(searchTextField.getText());
        });
        HBox allNoneHBox = new HBox(2);
        allNoneHBox.setAlignment(Pos.CENTER);
        allNoneHBox.getChildren().addAll(allButton, noneButton);

        // TEST BUTTONS============================================================================================
        Button testingButton = new Button("Testing");
        Button testingButton2 = new Button("Testing 2");

        filterVBox.getChildren().addAll(testingButton, testingButton2, filterLabel, allNoneHBox, searchTextField);
        Label weaponLabel = new Label("WEAPONS");
        weaponLabel.setId("filterLabel");
        ToggleButton pistolToggleButton = new ToggleButton("Pistols");//0
        toggleButtonArray.add(pistolToggleButton);
        ToggleButton smgToggleButton = new ToggleButton("Submachine Guns");//1
        toggleButtonArray.add(smgToggleButton);
        ToggleButton assaultRifleToggleButton = new ToggleButton("Assault Rifles");//2
        toggleButtonArray.add(assaultRifleToggleButton);
        ToggleButton shotgunToggleButton = new ToggleButton("Shotguns");//3
        toggleButtonArray.add(shotgunToggleButton);
        ToggleButton sniperToggleButton = new ToggleButton("Sniper Rifles");//4
        toggleButtonArray.add(sniperToggleButton);
        ToggleButton launcherToggleButton = new ToggleButton("Rocket Launchers");//5
        toggleButtonArray.add(launcherToggleButton);
        ToggleButton eridianToggleButton = new ToggleButton("Eridian");//6
        toggleButtonArray.add(eridianToggleButton);
        ToggleButton laserToggleButton = new ToggleButton("Lasers");//7
        toggleButtonArray.add(laserToggleButton);
        filterVBox.getChildren().addAll(weaponLabel, pistolToggleButton, smgToggleButton, assaultRifleToggleButton, shotgunToggleButton, 
        sniperToggleButton, launcherToggleButton, eridianToggleButton, laserToggleButton);
        Label equipmentLabel = new Label("EQUIPMENT");
        equipmentLabel.setId("filterLabel");
        ToggleButton classModToggleButton = new ToggleButton("Class Mods");//8
        toggleButtonArray.add(classModToggleButton);
        ToggleButton grenadeToggleButton = new ToggleButton("Grenade Mods");//9
        toggleButtonArray.add(grenadeToggleButton);
        ToggleButton relicToggleButton = new ToggleButton("Relifacts");//10
        toggleButtonArray.add(relicToggleButton);
        ToggleButton shieldToggleButton = new ToggleButton("Shields");//11
        toggleButtonArray.add(shieldToggleButton);
        ToggleButton ozKitToggleButton = new ToggleButton("Oz Kits");//12
        toggleButtonArray.add(ozKitToggleButton);
        ToggleButton enhancementToggleButton = new ToggleButton("Enhancements");//13
        toggleButtonArray.add(enhancementToggleButton);
        ToggleButton repkitToggleButton = new ToggleButton("Repkits");//14
        toggleButtonArray.add(repkitToggleButton);
        ToggleButton grenadeOrdnanceToggleButton = new ToggleButton("Grenade Ordnance");//15
        toggleButtonArray.add(grenadeOrdnanceToggleButton);
        ToggleButton heavyOrdnanceToggleButton = new ToggleButton("Heavy Ordnance");//16
        toggleButtonArray.add(heavyOrdnanceToggleButton);
        filterVBox.getChildren().addAll(equipmentLabel, classModToggleButton, grenadeToggleButton, relicToggleButton, shieldToggleButton, 
        ozKitToggleButton, enhancementToggleButton, repkitToggleButton, grenadeOrdnanceToggleButton, heavyOrdnanceToggleButton);
        Label rarityLabel = new Label("RARITY");
        rarityLabel.setId("filterLabel");
        ToggleButton uniqueToggleButton = new ToggleButton("Unique");//17
        toggleButtonArray.add(uniqueToggleButton);
        ToggleButton legendaryToggleButton = new ToggleButton("Legendary");//18
        toggleButtonArray.add(legendaryToggleButton);
        ToggleButton seraphToggleButton = new ToggleButton("Seraph");//19
        toggleButtonArray.add(seraphToggleButton);
        ToggleButton pearlToggleButton = new ToggleButton("Pearlescent");//20
        toggleButtonArray.add(pearlToggleButton);
        ToggleButton glitchToggleButton = new ToggleButton("Glitch");//27
        toggleButtonArray.add(glitchToggleButton);
        ToggleButton effervescentToggleButton = new ToggleButton("Effervescent");//22
        toggleButtonArray.add(effervescentToggleButton);
        filterVBox.getChildren().addAll(rarityLabel, uniqueToggleButton, legendaryToggleButton, seraphToggleButton, 
        pearlToggleButton, glitchToggleButton, effervescentToggleButton);
        Label gameFilterLabel = new Label("GAME");
        gameFilterLabel.setId("filterLabel");
        ToggleButton bl1ToggleButton = new ToggleButton("Borderlands");//23
        toggleButtonArray.add(bl1ToggleButton);
        ToggleButton bl2ToggleButton = new ToggleButton("Borderlands 2");//24
        toggleButtonArray.add(bl2ToggleButton);
        ToggleButton blTPSToggleButton = new ToggleButton("Borderlands TPS");//25
        toggleButtonArray.add(blTPSToggleButton);
        ToggleButton bl3ToggleButton = new ToggleButton("Borderlands 3");//26
        toggleButtonArray.add(bl3ToggleButton);
        ToggleButton bl4ToggleButton = new ToggleButton("Borderlands 4");//27
        toggleButtonArray.add(bl4ToggleButton);
        filterVBox.getChildren().addAll(gameFilterLabel, bl1ToggleButton, bl2ToggleButton, blTPSToggleButton, bl3ToggleButton, bl4ToggleButton);
        Label miscLabel = new Label("MISCELLANEOUS");
        miscLabel.setId("filterLabel");
        ToggleButton obtainedToggleButton = new ToggleButton("Obtained");//28
        toggleButtonArray.add(obtainedToggleButton);
        ToggleButton notObtainedToggleButton = new ToggleButton("Not Obtained");//29
        toggleButtonArray.add(notObtainedToggleButton);
        for (int i = 0; i < toggleButtonArray.size(); i++) {
            toggleButtonArray.get(i).setOnAction(event -> {
                resetDisplayedCards(searchTextField.getText());
            });
        }
        filterVBox.getChildren().addAll(miscLabel, obtainedToggleButton, notObtainedToggleButton);
        filterVBox.setId("filterVBox");
        allToggleButtonsOn(toggleButtonArray);
        ScrollPane filterScrollPane = new ScrollPane(filterVBox);
        filterScrollPane.setId("filterScrollPane");
        weaponLabel.setOnMouseClicked(event -> {
            Boolean allEnabled = pistolToggleButton.isSelected() && smgToggleButton.isSelected() && 
            assaultRifleToggleButton.isSelected() && shotgunToggleButton.isSelected() && sniperToggleButton.isSelected() && 
            launcherToggleButton.isSelected() && eridianToggleButton.isSelected() && laserToggleButton.isSelected();
            if (allEnabled) {
                pistolToggleButton.setSelected(false);
                smgToggleButton.setSelected(false);
                assaultRifleToggleButton.setSelected(false);
                shotgunToggleButton.setSelected(false);
                sniperToggleButton.setSelected(false);
                launcherToggleButton.setSelected(false);
                eridianToggleButton.setSelected(false);
                laserToggleButton.setSelected(false);
            } else {
                pistolToggleButton.setSelected(true);
                smgToggleButton.setSelected(true);
                assaultRifleToggleButton.setSelected(true);
                shotgunToggleButton.setSelected(true);
                sniperToggleButton.setSelected(true);
                launcherToggleButton.setSelected(true);
                eridianToggleButton.setSelected(true);
                laserToggleButton.setSelected(true);
            }
            resetDisplayedCards(searchTextField.getText());
        });
        equipmentLabel.setOnMouseClicked(event -> {
            Boolean allEnabled = classModToggleButton.isSelected() && grenadeToggleButton.isSelected() && 
            relicToggleButton.isSelected() && shieldToggleButton.isSelected() && ozKitToggleButton.isSelected() && 
            enhancementToggleButton.isSelected() && repkitToggleButton.isSelected() && grenadeOrdnanceToggleButton.isSelected() && heavyOrdnanceToggleButton.isSelected();
            if (allEnabled) {
                classModToggleButton.setSelected(false);
                grenadeToggleButton.setSelected(false);
                relicToggleButton.setSelected(false);
                shieldToggleButton.setSelected(false);
                ozKitToggleButton.setSelected(false);
                enhancementToggleButton.setSelected(false);
                repkitToggleButton.setSelected(false);
                grenadeOrdnanceToggleButton.setSelected(false);
                heavyOrdnanceToggleButton.setSelected(false);
            } else {
                classModToggleButton.setSelected(true);
                grenadeToggleButton.setSelected(true);
                relicToggleButton.setSelected(true);
                shieldToggleButton.setSelected(true);
                ozKitToggleButton.setSelected(true);
                enhancementToggleButton.setSelected(true);
                repkitToggleButton.setSelected(true);
                grenadeOrdnanceToggleButton.setSelected(true);
                heavyOrdnanceToggleButton.setSelected(true);
            }
            resetDisplayedCards(searchTextField.getText());
        });
        rarityLabel.setOnMouseClicked(event -> {
            Boolean allEnabled = uniqueToggleButton.isSelected() && legendaryToggleButton.isSelected() && 
            seraphToggleButton.isSelected() && pearlToggleButton.isSelected() && glitchToggleButton.isSelected() && 
            effervescentToggleButton.isSelected();
            if (allEnabled) {
                uniqueToggleButton.setSelected(false);
                legendaryToggleButton.setSelected(false);
                seraphToggleButton.setSelected(false);
                pearlToggleButton.setSelected(false);
                glitchToggleButton.setSelected(false);
                effervescentToggleButton.setSelected(false);
            } else {
                uniqueToggleButton.setSelected(true);
                legendaryToggleButton.setSelected(true);
                seraphToggleButton.setSelected(true);
                pearlToggleButton.setSelected(true);
                glitchToggleButton.setSelected(true);
                effervescentToggleButton.setSelected(true);
            }
            resetDisplayedCards(searchTextField.getText());
        });
        gameFilterLabel.setOnMouseClicked(event -> {
            Boolean allEnabled = bl1ToggleButton.isSelected() && bl2ToggleButton.isSelected() && 
            blTPSToggleButton.isSelected() && bl3ToggleButton.isSelected() && bl4ToggleButton.isSelected();
            if (allEnabled) {
                bl1ToggleButton.setSelected(false);
                bl2ToggleButton.setSelected(false);
                blTPSToggleButton.setSelected(false);
                bl3ToggleButton.setSelected(false);
                bl4ToggleButton.setSelected(false);
            } else {
                bl1ToggleButton.setSelected(true);
                bl2ToggleButton.setSelected(true);
                blTPSToggleButton.setSelected(true);
                bl3ToggleButton.setSelected(true);
                bl4ToggleButton.setSelected(true);
            }
            resetDisplayedCards(searchTextField.getText());
        });
        miscLabel.setOnMouseClicked(event -> {
            Boolean allEnabled = obtainedToggleButton.isSelected() && notObtainedToggleButton.isSelected();
            if (allEnabled) {
                obtainedToggleButton.setSelected(false);
                notObtainedToggleButton.setSelected(false);
            } else {
                obtainedToggleButton.setSelected(true);
                notObtainedToggleButton.setSelected(true);
            }
            resetDisplayedCards(searchTextField.getText());
        });

        //Banner
        ImageView wikiLinkImageView = new ImageView(wikiImage);
        wikiLinkImageView.setId("wikiLinkImageView");
        wikiLinkImageView.setFitHeight(48);
        wikiLinkImageView.setFitWidth(87);
        Pane wikiViewPane = new Pane(wikiLinkImageView);
        wikiViewPane.setStyle("-fx-cursor: hand;");
        wikiViewPane.setOnMouseClicked(event -> {
            hostService.showDocument("https://borderlands.fandom.com/wiki/Borderlands_Wiki");
        });

        //FPS label
        Label label2 = new Label("Other info here");
        AnimationTimer frameRateMeter = new AnimationTimer() {

            @Override
            public void handle(long now) {
                long oldFrameTime = frameTimes[frameTimeIndex];
                frameTimes[frameTimeIndex] = now;
                frameTimeIndex = (frameTimeIndex + 1) % frameTimes.length;
                if (frameTimeIndex == 0) {
                    arrayFilled = true;
                }
                if (arrayFilled) {
                    long elapsedNanos = now - oldFrameTime;
                    long elapsedNanosPerFrame = elapsedNanos / frameTimes.length;
                    double frameRate = Math.floor(1_000_000_000.0 / elapsedNanosPerFrame);
                    label2.setText("FPS: " + frameRate);
                }
            }
            
        };
        frameRateMeter.start();

        Label label3 = new Label("Other info here");
        VBox sizeVBox = new VBox(label2, label3);
        Label label4 = new Label("Other info here");
        Label label5 = new Label("Other info here");
        VBox sizeVBox2 = new VBox(label4, label5);
        Label label6 = new Label("Other info here");
        Label label7 = new Label("Other info here");
        VBox sizeVBox3 = new VBox(label6, label7);
        HBox bannerHBox = new HBox(5, wikiViewPane, sizeVBox, sizeVBox2, sizeVBox3);
        bannerHBox.setId("bannerBox");

        //Build item cards
        buildAllItemCards();
        Collections.sort(itemCardArray, new Comparator<Pane>() {
            public int compare(Pane p1, Pane p2) {
                return p1.getAccessibleText().compareTo(p2.getAccessibleText());
            }
        });
        resetDisplayedCards(searchTextField.getText());



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

        Scene scene = new Scene(root, 1280, 720);
        scene.getStylesheets().add(this.getClass().getResource("styles.css").toExternalForm());
        
        stage.setTitle("Borderlands Collection Log");
        stage.getIcons().add(icon);
        stage.setResizable(true);

        stage.setScene(scene);
        stage.show();
        itemFlowPane.setPrefWidth(scene.getWidth()-206);
        int itemFlowPaneWidth = (int) itemFlowPane.getPrefWidth();
        int cardsThatFit = (int) Math.floor(itemFlowPaneWidth/336);
        if (cardsThatFit > 1) {
            int hGapValue = (itemFlowPaneWidth-(336*cardsThatFit))/(cardsThatFit-1);
            itemFlowPane.setHgap(hGapValue);
        }
        scene.widthProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                // label2.setText("Width: " + itemFlowPane.getWidth());
                itemScrollPane.setVvalue(0);
                itemFlowPane.setPrefWidth(scene.getWidth()-206);
                int itemFlowPaneWidth = (int) itemFlowPane.getPrefWidth();
                int cardsThatFit = (int) Math.floor(itemFlowPaneWidth/336);
                if (cardsThatFit > 1) {
                int hGapValue = (itemFlowPaneWidth-(336*cardsThatFit))/(cardsThatFit-1);
                itemFlowPane.setHgap(hGapValue);
                }
            }
        });
        scene.heightProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                // label3.setText("Height: " + scene.getHeight());
            }
        });

        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            resetDisplayedCards(searchTextField.getText());
        });

        itemScrollPane.vvalueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double flowPaneWidth = itemFlowPane.getWidth();
                int cardsOnScreenH = (int) Math.floor(flowPaneWidth/336);
                int cardsOnScreenV = (int) Math.ceil(itemScrollPane.getViewportBounds().getHeight()/470);
                Double newValueDouble = newValue.doubleValue();

                int cardOnScreen = (int) Math.round(itemCardFilteredArray.size()*newValueDouble);
                int growingValue = (int) Math.floor(cardsOnScreenH*(newValueDouble));
                int shrinkingValue = (int) Math.ceil(cardsOnScreenH*(1-newValueDouble));
                int minBound = cardOnScreen-(cardsOnScreenH*(cardsOnScreenV)+growingValue);
                int lowBounds = cardOnScreen-(cardsOnScreenH*(cardsOnScreenV-1)+growingValue);
                int highBounds = cardOnScreen+(cardsOnScreenH*(cardsOnScreenV)+shrinkingValue);
                int maxBound = cardOnScreen+(cardsOnScreenH*(cardsOnScreenV+1)+shrinkingValue);
                // label3.setText("cardOnScreen: " + cardOnScreen);
                // label4.setText("cardsOnScreenH: " + cardsOnScreenH);
                // label5.setText("cardsOnScreenV: " + cardsOnScreenV);
                // label6.setText("growingValue: " + growingValue);
                // label7.setText("shrinkingValue: " + shrinkingValue);
                for (int i = minBound; i < maxBound; i++) {
                    if (i >= 0 && i < itemCardFilteredArray.size()) {
                        if (i < lowBounds) {
                            itemFlowPane.getChildren().get(i).setVisible(false);
                        } else if (i >= lowBounds && i <= highBounds) {
                            itemFlowPane.getChildren().get(i).setVisible(true);
                        } else if (i > highBounds) {
                            itemFlowPane.getChildren().get(i).setVisible(false);
                        }
                    }
                }
            }
        });

        testingButton.setOnAction(event -> {
            int visibleCards = 0;
            for (int i = 0; i < itemFlowPane.getChildren().size(); i++) {
                if(itemFlowPane.getChildren().get(i).isVisible()) {
                    visibleCards += 1;
                }
            }
            System.out.println("Visible cards: " + visibleCards);
            System.out.println("Total cards: " + itemFlowPane.getChildren().size());
        });
        testingButton2.setOnAction(event -> {
            Pane blankPane = new Pane();
            blankPane.setId("blankPane");
            blankPane.setAccessibleText("name_type_game_obtained_rarity");
            itemCardArray.set(0, blankPane);
            resetDisplayedCards("");
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

    public static void writeToXml() {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource docSource = new DOMSource(itemDocument);
            StreamResult docResult = new StreamResult(itemsXML);
            transformer.transform(docSource, docResult);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    public static int getCardNumber(String name, String game) {
        for (int i = 0; i < itemNodes.getLength(); i++) {
            Element node = (Element) itemNodes.item(i);
            if (node.getElementsByTagName("name").item(0).getTextContent().equals(name) && 
            node.getElementsByTagName("game").item(0).getTextContent().equals(game)) {
                return i;
            }
        }
        return -1;
    }

    public static void clearAllItemCards() {
        itemFlowPane.getChildren().clear();
    }

    public static void clearFilteredItemCards() {
        itemCardFilteredArray.clear();
    }

    public static void resetDisplayedCards(String searchTerm) {
        filterAllItemCards(searchTerm);
        clearAllItemCards();
        for (int i = 0; i < itemCardFilteredArray.size(); i++) {
            itemFlowPane.getChildren().add(itemCardFilteredArray.get(i));
            if (i < 50) {
                itemFlowPane.getChildren().get(i).setVisible(true);
            } else {
                itemFlowPane.getChildren().get(i).setVisible(false);
            }
        }
        itemScrollPane.setVvalue(0);
    }

    public static void filterAllItemCards(String searchTerm) {
        clearFilteredItemCards();
        for (int i = 0; i < itemCardArray.size(); i++) {
            String name = itemCardArray.get(i).getAccessibleText().split("_")[0];
            String type = itemCardArray.get(i).getAccessibleText().split("_")[1];
            String game = itemCardArray.get(i).getAccessibleText().split("_")[2];
            String obtained = itemCardArray.get(i).getAccessibleText().split("_")[3];
            String rarity = itemCardArray.get(i).getAccessibleText().split("_")[4];
            if (game.equals("") && !toggleButtonArray.get(23).isSelected()) {
                continue;
            } else if (game.equals("2") && !toggleButtonArray.get(24).isSelected()) {
                continue;
            } else if (game.equals("TPS") && !toggleButtonArray.get(25).isSelected()) {
                continue;
            } else if (game.equals("3") && !toggleButtonArray.get(26).isSelected()) {
                continue;
            } else if (game.equals("4") && !toggleButtonArray.get(27).isSelected()) {
                continue;
            } else if (obtained.equals("true") && !toggleButtonArray.get(28).isSelected()) {
                continue;
            } else if (obtained.equals("false") && !toggleButtonArray.get(29).isSelected()) {
                continue;
            } else if ((rarity.equals("unique") || rarity.equals("Unique") || rarity.equals("unique_purple") || 
            rarity.equals("unique_green")) && !toggleButtonArray.get(17).isSelected()) {
                continue;
            } else if ((rarity.equals("legendary") || rarity.equals("Legendary")) && !toggleButtonArray.get(18).isSelected()) {
                continue;
            } else if ((rarity.equals("seraph") || rarity.equals("Seraph")) && !toggleButtonArray.get(19).isSelected()) {
                continue;
            } else if ((rarity.equals("pearl") || rarity.equals("Pearlescent")) && !toggleButtonArray.get(20).isSelected()) {
                continue;
            } else if ((rarity.equals("glitch") || rarity.equals("Glitch")) && !toggleButtonArray.get(21).isSelected()) {
                continue;
            } else if ((rarity.equals("effervescent") || rarity.equals("Effervescent")) && !toggleButtonArray.get(22).isSelected()) {
                continue;
            } else if ((type.equals("pistol") || type.equals("Pistol")) && !toggleButtonArray.get(0).isSelected()) {
                continue;
            } else if ((type.equals("smg") || type.equals("Submachine Gun")) && !toggleButtonArray.get(1).isSelected()) {
                continue;
            } else if ((type.equals("ar") || type.equals("Assault Rifle")) && !toggleButtonArray.get(2).isSelected()) {
                continue;
            } else if ((type.equals("shotgun") || type.equals("Shotgun")) && !toggleButtonArray.get(3).isSelected()) {
                continue;
            } else if ((type.equals("sniper") || type.equals("Sniper") || type.equals("Sniper Rifle")) && 
            !toggleButtonArray.get(4).isSelected()) {
                continue;
            } else if ((type.equals("launcher") || type.equals("Launcher") || type.equals("Rocket Launcher")) && 
            !toggleButtonArray.get(5).isSelected()) {
                continue;
            } else if ((type.equals("eridian") || type.equals("Eridian")) && !toggleButtonArray.get(6).isSelected()) {
                continue;
            } else if ((type.equals("laser") || type.equals("Laser")) && !toggleButtonArray.get(7).isSelected()) {
                continue;
            } else if ((type.equals("class mod") || type.equals("Class Mod")) && !toggleButtonArray.get(8).isSelected()) {
                continue;
            } else if ((type.equals("grenade mod") || type.equals("Grenade Mod")) && !toggleButtonArray.get(9).isSelected()) {
                continue;
            } else if ((type.equals("relic") || type.equals("Relic") || type.equals("artifact") || type.equals("Artifact")) && 
            !toggleButtonArray.get(10).isSelected()) {
                continue;
            } else if ((type.equals("shield") || type.equals("Shield")) && !toggleButtonArray.get(11).isSelected()) {
                continue;
            } else if ((type.equals("oz kit") || type.equals("Oz Kit")) && !toggleButtonArray.get(12).isSelected()) {
                continue;
            } else if ((type.equals("enhancement") || type.equals("Enhancement")) && !toggleButtonArray.get(13).isSelected()) {
                continue;
            } else if ((type.equals("repkit") || type.equals("Repkit")) && !toggleButtonArray.get(14).isSelected()) {
                continue;
            } else if ((type.equals("grenade ordnance") || type.equals("Grenade Ordnance")) && !toggleButtonArray.get(15).isSelected()) {
                continue;
            } else if ((type.equals("heavy ordnance") || type.equals("Heavy Ordnance")) && !toggleButtonArray.get(16).isSelected()) {
                continue;
            } else if (!name.toLowerCase().contains(searchTerm.toLowerCase())) {
                continue;
            }
            itemCardFilteredArray.add(itemCardArray.get(i));
        }
    }

    public static void buildAllItemCards() {
        for (int i = 0; i < itemNodes.getLength(); i++) {
            Element nameItemNode = (Element) itemNodes.item(i);
            String name = nameItemNode.getElementsByTagName("name").item(0).getTextContent();
            Element typeItemNode = (Element) itemNodes.item(i);
            String type = typeItemNode.getElementsByTagName("type").item(0).getTextContent();
            Element gameItemNode = (Element) itemNodes.item(i);
            String game = gameItemNode.getElementsByTagName("game").item(0).getTextContent();
            Element obtainedItemNode = (Element) itemNodes.item(i);
            Boolean obtained = Boolean.parseBoolean(obtainedItemNode.getElementsByTagName("obtained").item(0).getTextContent());
            Element rarityItemNode = (Element) itemNodes.item(i);
            String rarity = rarityItemNode.getElementsByTagName("rarity").item(0).getTextContent();
            Element sourceItemNode = (Element) itemNodes.item(i);
            String source = sourceItemNode.getElementsByTagName("source").item(0).getTextContent();
            Element textItemNode = (Element) itemNodes.item(i);
            String text = textItemNode.getElementsByTagName("text").item(0).getTextContent();
            Element wikiItemNode = (Element) itemNodes.item(i);
            String wiki = wikiItemNode.getElementsByTagName("wiki").item(0).getTextContent();
            Element pointsItemNode = (Element) itemNodes.item(i);
            String points = pointsItemNode.getElementsByTagName("points").item(0).getTextContent();
            buildItemCard(name, type, game, obtained, rarity, source, text, wiki, points);
        }
    }

    public static void buildItemCard(String name, String type, String game, Boolean obtained, String rarity, String source, String text, String wiki, String points) {
        Pane itemPane = new Pane();
        StackPane itemImageStackPane = new StackPane();
        itemImageStackPane.setId("itemImageStackPane");
        ImageView itemImageView = new ImageView();
        if (type.equals("pistol") || type.equals("Pistol")) {
            itemImageView.setImage(pistolImage);
        } else if (type.equals("ar") || type.equals("Assault Rifle")) {
            itemImageView.setImage(arImage);
        } else if (type.equals("class mod") || type.equals("Class Mod")) {
            itemImageView.setImage(classModImage);
        } else if (type.equals("grenade mod") || type.equals("Grenade Mod")) {
            itemImageView.setImage(grenadeImage);
        } else if (type.equals("grenade ordnance") || type.equals("Grenade Ordnance") || type.equals("Grenade (Ordnance)")) {
            itemImageView.setImage(grenadeOrdnanceImage);
        } else if (type.equals("heavy ordnance") || type.equals("Heavy Ordnance") || type.equals("Heavy Weapon (Ordnance)")) {
            itemImageView.setImage(heavyOrdnanceImage);
        } else if (type.equals("laser") || type.equals("Laser")) {
            itemImageView.setImage(laserImage);
        } else if (type.equals("launcher") || type.equals("Launcher") || type.equals("Rocket Launcher")) {
            itemImageView.setImage(launcherImage);
        } else if (type.equals("oz kit") || type.equals("Oz Kit")) {
            itemImageView.setImage(ozKitImage);
        } else if (type.equals("shield") || type.equals("Shield")) {
            itemImageView.setImage(shieldImage);
        } else if (type.equals("shotgun") || type.equals("Shotgun")) {
            itemImageView.setImage(shotgunImage);
        } else if (type.equals("smg") || type.equals("Submachine Gun")) {
            itemImageView.setImage(smgImage);
        } else if (type.equals("sniper") || type.equals("Sniper") || type.equals("Sniper Rifle")) {
            itemImageView.setImage(sniperImage);
        } else if (type.equals("relic") || type.equals("Relic") || type.equals("artifact") || type.equals("Artifact")) {
            itemImageView.setImage(relicImage);
        } else if (type.equals("eridian") || type.equals("Eridian")) {
            itemImageView.setImage(eridianImage);
        } else if (type.equals("repkit") || type.equals("Repkit")) {
            itemImageView.setImage(repkitImage);
        } else if (type.equals("enhancement") || type.equals("Enhancement")) {
            itemImageView.setImage(enhancementImage);
        }
        Pane itemBackgroundColor = new Pane();
        itemBackgroundColor.setId("itemBackgroundColor");
        itemImageStackPane.getChildren().addAll(itemBackgroundColor, itemImageView);
        Label gameLabel = new Label("Borderlands " + game);
        gameLabel.setId("gameLabel");
        Pane obtainedPane = new Pane();
        if (obtained) {
            obtainedPane.setBackground(new Background(new BackgroundImage(obtainedImage, null, null, null, null)));
        } else {
            obtainedPane.setBackground(new Background(new BackgroundImage(notObtainedImage, null, null, null, null)));
        }
        obtainedPane.setId("obtainedPane");
        Label huntPointsLabel = new Label(points);
        huntPointsLabel.setId("huntPointsLabel");
        HBox topHBox = new HBox(obtainedPane, gameLabel, huntPointsLabel);
        HBox.setMargin(obtainedPane, new Insets(5, 0, 0, 12));
        obtainedPane.setOnMouseClicked(event -> {
            Element node = (Element) itemNodes.item(getCardNumber(name, game));
            Node obtainedNode = node.getElementsByTagName("obtained").item(0);
            if (obtainedNode.getTextContent().equals("true")) {
                obtainedPane.setBackground(new Background(new BackgroundImage(notObtainedImage, null, null, null, null)));
                obtainedNode.setTextContent("false");
                itemPane.setAccessibleText(name + "_" + type + "_" + game + "_false_" + rarity);
            } else {
                obtainedPane.setBackground(new Background(new BackgroundImage(obtainedImage, null, null, null, null)));
                obtainedNode.setTextContent("true");
                itemPane.setAccessibleText(name + "_" + type + "_" + game + "_true_" + rarity);
            }
            writeToXml();
            resetDisplayedCards(searchTextField.getText());
        });
        HBox.setMargin(huntPointsLabel, new Insets(7, 0, 0, 23));
        HBox.setMargin(gameLabel, new Insets(0, 0, 0, 25));
        Label itemNameLabel = new Label(name);
        itemNameLabel.setId("itemNameLabel");

        if (rarity.equals("legendary") || rarity.equals("Legendary")) {
            itemNameLabel.setTextFill(Paint.valueOf("#eb8a01"));
            itemBackgroundColor.setStyle("-fx-background-color: #eb8a01;");
        } else if (rarity.equals("pearl") || rarity.equals("Pearlescent")) {
            itemNameLabel.setTextFill(Paint.valueOf("#00ffff"));
            itemBackgroundColor.setStyle("-fx-background-color: #00ffff;");
        } else if (rarity.equals("unique") || rarity.equals("Unique")) {
            itemNameLabel.setTextFill(Paint.valueOf("#2760ca"));
            itemBackgroundColor.setStyle("-fx-background-color: #2760ca;");
        } else if (rarity.equals("unique_purple")) {
            itemNameLabel.setTextFill(Paint.valueOf("#7731b0"));
            itemBackgroundColor.setStyle("-fx-background-color: #7731b0;");
        } else if (rarity.equals("unique_green")) {
            itemNameLabel.setTextFill(Paint.valueOf("#3ac80a"));
            itemBackgroundColor.setStyle("-fx-background-color: #3ac80a;");
        } else if (rarity.equals("unique_etech")) {
            itemNameLabel.setTextFill(Paint.valueOf("#e700e7"));
            itemBackgroundColor.setStyle("-fx-background-color: #e700e7;");
        } else if (rarity.equals("seraph") || rarity.equals("glitch") || 
        rarity.equals("Seraph") || rarity.equals("Glitch")) {
            itemNameLabel.setTextFill(Paint.valueOf("#ff69b4"));
            itemBackgroundColor.setStyle("-fx-background-color: #ff69b4;");
        } else if (rarity.equals("effervescent") || rarity.equals("Effervescent")) {
            itemBackgroundColor.setBackground(new Background(new BackgroundImage(effervescentBackground, null, null, null, null)));
            itemNameLabel.setTextFill(Paint.valueOf("linear-gradient(to right, red 0%, orange 20%, yellow 40%, rgb(0, 255, 0) 60%, rgb(101, 101, 255) 80%, rgb(212, 0, 255) 100%)"));
        }
        int labelCount = itemNameLabel.getText().length();
        int textSize = 25;
        if (labelCount > 25) {
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
        itemNameLabel.setStyle("-fx-font-size: " + textSize + ";");
        String[] textSplit = text.split("_");
        String newTextText = "";
        for (int i = 0; i < textSplit.length; i++) {
            if (i > 0) {
                newTextText = newTextText + "\n";
            }
            newTextText += "\u2022 " + textSplit[i];
        }
        Label flavorTextLabel = new Label(newTextText);
        flavorTextLabel.setId("flavorTextLabel");
        if (name.equals("Norfleet")) {
            flavorTextLabel.setStyle("-fx-text-fill:#eb8a01;");
        }
        Label sourcesLabel = new Label("Sources");
        sourcesLabel.setId("sourcesLabel");
        String[] sourceSplit = source.split("_");
        String newSourceText = "";
        for (int i = 0; i < sourceSplit.length; i++) {
            if (i > 0) {
                newSourceText = newSourceText + "\n";
            }
            newSourceText += "\u2022 " + sourceSplit[i];
        }
        Label sourcesListLabel = new Label(newSourceText);
        sourcesListLabel.setId("sourcesListLabel");
        ImageView itemWikiLinkImageView = new ImageView(wikiImage);
        itemWikiLinkImageView.setId("itemWikiLinkImageView");
        itemWikiLinkImageView.setFitHeight(30);
        itemWikiLinkImageView.setFitWidth(60);
        Pane itemWikiLinkPane = new Pane(itemWikiLinkImageView);
        itemWikiLinkPane.setStyle("-fx-cursor: hand;");
        itemWikiLinkPane.setOnMouseClicked(event -> {
            hostService.showDocument(wiki);
        });
        Region hPusherRegion = new Region();
        HBox.setHgrow(hPusherRegion, Priority.ALWAYS);
        HBox itemBottomHBox = new HBox(itemWikiLinkPane, hPusherRegion);
        itemBottomHBox.setId("itemBottomHBox");
        Region vPusherRegion = new Region();
        VBox.setVgrow(vPusherRegion, Priority.ALWAYS);
        
        VBox itemVBox = new VBox(topHBox, itemNameLabel, itemImageStackPane);
        if (!text.isEmpty()) {
            itemVBox.getChildren().add(flavorTextLabel);
        }
        itemVBox.getChildren().addAll(sourcesLabel, sourcesListLabel, vPusherRegion, itemBottomHBox);
        itemVBox.setId("itemVBox");
        VBox.setMargin(itemNameLabel, new Insets(5, 0 ,0, 0));
        VBox.setMargin(itemImageStackPane, new Insets(1, 0 ,0, 0));
        itemPane.getChildren().add(itemVBox);
        itemPane.setId("itemPane");
        itemPane.setVisible(false);
        itemPane.setAccessibleText(name + "_" + type + "_" + game + "_" + obtained + "_" + rarity);
        itemCardArray.add(itemPane);
    }
}
