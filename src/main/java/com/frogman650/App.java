package com.frogman650;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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

import java.io.File;
import java.net.URI;
import java.nio.file.Paths;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
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
import javafx.scene.shape.Line;
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
    public static Image itemCardBackground;
    public static Image theHuntImage;
    public static Image settingsImage;
    public static FlowPane itemFlowPane;
    public static ScrollPane itemScrollPane;
    public static TextField searchTextField;
    public static Document itemDocument;
    public static Document settingsDocument;
    public static NodeList itemNodes;
    public static NodeList filterNodes;
    public static NodeList settingsNodes;
    public static ArrayList<Pane> itemCardArray = new ArrayList<>();
    public static ArrayList<Pane> itemCardFilteredArray = new ArrayList<>();
    public static ArrayList<ToggleButton> toggleButtonArray = new ArrayList<>();
    public static ArrayList<ToggleButton> settingsToggleButtonArray = new ArrayList<>();
    public static File itemsXML;
    public static File settingsXML;
    public static HostServices hostService;
    public static Lock lock = new ReentrantLock();

    private final long[] frameTimes = new long[100];
    private int frameTimeIndex = 0;
    private boolean arrayFilled = false;

    public static Label label2;
    public static Label label3;
    public static Label label4;
    public static Label label5;
    public static Label label6;
    public static Label label7;

    public static int huntBL= 0;
    public static int huntObtainedBL= 0;
    public static int huntBL2= 0;
    public static int huntObtainedBL2= 0;
    public static int huntBLTPS= 0;
    public static int huntObtainedBLTPS= 0;
    public static int huntBL3= 0;
    public static int huntObtainedBL3= 0;
    public static int huntBL4= 0;
    public static int huntObtainedBL4= 0;
    public static Label huntItemsCollectedLabel;
    public static Label huntItemsTotalLabel;

    public static int countBL= 0;
    public static int countObtainedBL= 0;
    public static int countBL2= 0;
    public static int countObtainedBL2= 0;
    public static int countBLTPS= 0;
    public static int countObtainedBLTPS= 0;
    public static int countBL3= 0;
    public static int countObtainedBL3= 0;
    public static int countBL4= 0;
    public static int countObtainedBL4= 0;
    public static Label itemsCollectedLabel;
    public static Label itemsTotalLabel;

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
        itemCardBackground = new Image(getClass().getResourceAsStream("weapon_card.png"));
        theHuntImage = new Image(getClass().getResourceAsStream("Hunt_logo_mini.png"));
        settingsImage = new Image(getClass().getResourceAsStream("settings.png"));
        hostService = getHostServices();

        URI uri = getClass().getProtectionDomain().getCodeSource().getLocation().toURI();
        File executableDirectory = Paths.get(uri).getParent().toFile();
        itemsXML = new File(executableDirectory, "items.xml");
        settingsXML = new File(executableDirectory, "settings.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        itemDocument = builder.parse(itemsXML);
        settingsDocument = builder.parse(settingsXML);
        itemNodes = itemDocument.getDocumentElement().getElementsByTagName("item");
        filterNodes = settingsDocument.getDocumentElement().getElementsByTagName("filter");
        settingsNodes = settingsDocument.getDocumentElement().getElementsByTagName("setting");

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
        });
        Button noneButton = new Button("None");
        noneButton.setId("none");
        noneButton.setOnAction(event -> {
            allToggleButtonsOff(toggleButtonArray);
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
        ToggleButton glitchToggleButton = new ToggleButton("Glitch");//21
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
        filterVBox.getChildren().addAll(gameFilterLabel, bl1ToggleButton, bl2ToggleButton, 
        blTPSToggleButton, bl3ToggleButton, bl4ToggleButton);
        Label miscLabel = new Label("MISCELLANEOUS");
        miscLabel.setId("filterLabel");
        ToggleButton obtainedToggleButton = new ToggleButton("Obtained");//28
        toggleButtonArray.add(obtainedToggleButton);
        ToggleButton notObtainedToggleButton = new ToggleButton("Not Obtained");//29
        toggleButtonArray.add(notObtainedToggleButton);
        ToggleButton worldDropToggleButton = new ToggleButton("World Drop");//30
        toggleButtonArray.add(worldDropToggleButton);
        ToggleButton nonWorldDropToggleButton = new ToggleButton("Non World Drop");//31
        toggleButtonArray.add(nonWorldDropToggleButton);
        ToggleButton nonHuntToggleButton = new ToggleButton("Non Hunt");//32
        toggleButtonArray.add(nonHuntToggleButton);
        for (int i = 0; i < toggleButtonArray.size(); i++) {
            int toggleButton = i;
            toggleButtonArray.get(toggleButton).setOnAction(event -> {
                itemScrollPane.setVvalue(0);
                resetDisplayedCards(searchTextField.getText());
                Element filterElement = (Element) filterNodes.item(toggleButton);
                if (toggleButtonArray.get(toggleButton).isSelected()) {
                    filterElement.getElementsByTagName("enabled").item(0).setTextContent("true");
                } else {
                    filterElement.getElementsByTagName("enabled").item(0).setTextContent("false");
                }
                new Thread(() -> {
                    writeToXml(settingsDocument, settingsXML);
                }).start();
            });
        }
        filterVBox.getChildren().addAll(miscLabel, obtainedToggleButton, notObtainedToggleButton, 
        worldDropToggleButton, nonWorldDropToggleButton, nonHuntToggleButton);
        filterVBox.setId("filterVBox");
        ScrollPane filterScrollPane = new ScrollPane(filterVBox);
        filterScrollPane.setId("filterScrollPane");
        weaponLabel.setOnMouseClicked(event -> {
            Boolean allEnabled = pistolToggleButton.isSelected() && smgToggleButton.isSelected() && 
            assaultRifleToggleButton.isSelected() && shotgunToggleButton.isSelected() && sniperToggleButton.isSelected() && 
            launcherToggleButton.isSelected() && eridianToggleButton.isSelected() && laserToggleButton.isSelected();
            String enabledString = allEnabled ? "false" : "true";
            for (int i = 0; i < 8; i++) {
                Element settingElement = (Element) filterNodes.item(i);
                settingElement.getElementsByTagName("enabled").item(0).setTextContent(enabledString);
                toggleButtonArray.get(i).setSelected(Boolean.parseBoolean(enabledString));
            }
            itemScrollPane.setVvalue(0);
            resetDisplayedCards(searchTextField.getText());
            new Thread(() -> {
                writeToXml(settingsDocument, settingsXML);
            }).start();
        });
        equipmentLabel.setOnMouseClicked(event -> {
            Boolean allEnabled = classModToggleButton.isSelected() && grenadeToggleButton.isSelected() && 
            relicToggleButton.isSelected() && shieldToggleButton.isSelected() && ozKitToggleButton.isSelected() && 
            enhancementToggleButton.isSelected() && repkitToggleButton.isSelected() && grenadeOrdnanceToggleButton.isSelected() && heavyOrdnanceToggleButton.isSelected();
            String enabledString = allEnabled ? "false" : "true";
            for (int i = 8; i < 17; i++) {
                Element settingElement = (Element) filterNodes.item(i);
                settingElement.getElementsByTagName("enabled").item(0).setTextContent(enabledString);
                toggleButtonArray.get(i).setSelected(Boolean.parseBoolean(enabledString));
            }
            itemScrollPane.setVvalue(0);
            resetDisplayedCards(searchTextField.getText());
            new Thread(() -> {
                writeToXml(settingsDocument, settingsXML);
            }).start();
        });
        rarityLabel.setOnMouseClicked(event -> {
            Boolean allEnabled = uniqueToggleButton.isSelected() && legendaryToggleButton.isSelected() && 
            seraphToggleButton.isSelected() && pearlToggleButton.isSelected() && glitchToggleButton.isSelected() && 
            effervescentToggleButton.isSelected();
            String enabledString = allEnabled ? "false" : "true";
            for (int i = 17; i < 23; i++) {
                Element settingElement = (Element) filterNodes.item(i);
                settingElement.getElementsByTagName("enabled").item(0).setTextContent(enabledString);
                toggleButtonArray.get(i).setSelected(Boolean.parseBoolean(enabledString));
            }
            itemScrollPane.setVvalue(0);
            resetDisplayedCards(searchTextField.getText());
            new Thread(() -> {
                writeToXml(settingsDocument, settingsXML);
            }).start();
        });
        gameFilterLabel.setOnMouseClicked(event -> {
            Boolean allEnabled = bl1ToggleButton.isSelected() && bl2ToggleButton.isSelected() && 
            blTPSToggleButton.isSelected() && bl3ToggleButton.isSelected() && bl4ToggleButton.isSelected();
            String enabledString = allEnabled ? "false" : "true";
            for (int i = 23; i < 28; i++) {
                Element settingElement = (Element) filterNodes.item(i);
                settingElement.getElementsByTagName("enabled").item(0).setTextContent(enabledString);
                toggleButtonArray.get(i).setSelected(Boolean.parseBoolean(enabledString));
            }
            itemScrollPane.setVvalue(0);
            resetDisplayedCards(searchTextField.getText());
            new Thread(() -> {
                writeToXml(settingsDocument, settingsXML);
            }).start();
        });
        miscLabel.setOnMouseClicked(event -> {
            Boolean allEnabled = obtainedToggleButton.isSelected() && notObtainedToggleButton.isSelected() && 
            worldDropToggleButton.isSelected() && nonWorldDropToggleButton.isSelected() && nonHuntToggleButton.isSelected();
            String enabledString = allEnabled ? "false" : "true";
            for (int i = 28; i < toggleButtonArray.size(); i++) {
                Element settingElement = (Element) filterNodes.item(i);
                settingElement.getElementsByTagName("enabled").item(0).setTextContent(enabledString);
                toggleButtonArray.get(i).setSelected(Boolean.parseBoolean(enabledString));
            }
            itemScrollPane.setVvalue(0);
            resetDisplayedCards(searchTextField.getText());
            new Thread(() -> {
                writeToXml(settingsDocument, settingsXML);
            }).start();
        });

        //Settings
        VBox settingsVBox = new VBox();
        settingsVBox.setSpacing(1);
        settingsVBox.setId("filterVBox");
        Label settingsLabel = new Label("SETTINGS");
        settingsLabel.setId("filterLabel");
        settingsLabel.setStyle("-fx-cursor: none;");
        Label itemCollectionLabel = new Label("COLLECTION");
        itemCollectionLabel.setId("filterLabel");
        itemCollectionLabel.setStyle("-fx-cursor: none;");
        Tooltip itemCollectionToolTip = new Tooltip("These settings control which games items\nwill contribute to the items collected\non the banner at the top.");
        itemCollectionToolTip.setId("toolTip");
        itemCollectionLabel.setOnMouseMoved(event -> {
            itemCollectionToolTip.show(itemCollectionLabel, event.getScreenX() + 10, event.getScreenY() + 20);
        });
        itemCollectionLabel.setOnMouseExited(event -> {
            itemCollectionToolTip.hide();
        });
        ToggleButton toggleButtonCollectionBL = new ToggleButton("Borderlands");//0
        settingsToggleButtonArray.add(toggleButtonCollectionBL);
        ToggleButton toggleButtonCollectionBL2 = new ToggleButton("Borderlands 2");//1
        settingsToggleButtonArray.add(toggleButtonCollectionBL2);
        ToggleButton toggleButtonCollectionBLTPS = new ToggleButton("Borderlands TPS");//2
        settingsToggleButtonArray.add(toggleButtonCollectionBLTPS);
        ToggleButton toggleButtonCollectionBL3 = new ToggleButton("Borderlands 3");//3
        settingsToggleButtonArray.add(toggleButtonCollectionBL3);
        ToggleButton toggleButtonCollectionBL4 = new ToggleButton("Borderlands 4");//4
        settingsToggleButtonArray.add(toggleButtonCollectionBL4);
        settingsVBox.getChildren().addAll(settingsLabel, itemCollectionLabel, toggleButtonCollectionBL, toggleButtonCollectionBL2,
        toggleButtonCollectionBLTPS, toggleButtonCollectionBL3, toggleButtonCollectionBL4);
        Label theHuntLabel = new Label("THE HUNT");
        theHuntLabel.setId("filterLabel");
        theHuntLabel.setStyle("-fx-cursor: none;");
        Tooltip huntCollectionToolTip = new Tooltip("These settings control which games items\nwill contribute to the hunt point\ntotal on the banner at the top.");
        huntCollectionToolTip.setId("toolTip");
        theHuntLabel.setOnMouseMoved(event -> {
            huntCollectionToolTip.show(theHuntLabel, event.getScreenX() + 10, event.getScreenY() + 20);
        });
        theHuntLabel.setOnMouseExited(event -> {
            huntCollectionToolTip.hide();
        });
        ToggleButton toggleButtonHuntBL = new ToggleButton("Borderlands");//5
        settingsToggleButtonArray.add(toggleButtonHuntBL);
        ToggleButton toggleButtonHuntBL2 = new ToggleButton("Borderlands 2");//6
        settingsToggleButtonArray.add(toggleButtonHuntBL2);
        ToggleButton toggleButtonHuntBLTPS = new ToggleButton("Borderlands TPS");//7
        settingsToggleButtonArray.add(toggleButtonHuntBLTPS);
        ToggleButton toggleButtonHuntBL3 = new ToggleButton("Borderlands 3");//8
        settingsToggleButtonArray.add(toggleButtonHuntBL3);
        ToggleButton toggleButtonHuntBL4 = new ToggleButton("Borderlands 4");//9
        settingsToggleButtonArray.add(toggleButtonHuntBL4);
        settingsVBox.getChildren().addAll(theHuntLabel, toggleButtonHuntBL, toggleButtonHuntBL2,
        toggleButtonHuntBLTPS, toggleButtonHuntBL3, toggleButtonHuntBL4);
        for (int i = 0; i < settingsToggleButtonArray.size(); i++) {
            int toggleButton = i;
            settingsToggleButtonArray.get(toggleButton).setOnAction(event -> {
                Element settingElement = (Element) settingsNodes.item(toggleButton);
                if (settingsToggleButtonArray.get(toggleButton).isSelected()) {
                    settingElement.getElementsByTagName("enabled").item(0).setTextContent("true");
                } else {
                    settingElement.getElementsByTagName("enabled").item(0).setTextContent("false");
                }
                updateBannerLabels();
                new Thread(() -> {
                    writeToXml(settingsDocument, settingsXML);
                }).start();
            });
        }

        //set Toggle buttons to on or off based on settings.xml
        for (int i = 0; i < toggleButtonArray.size(); i++) {
            Element filterElement = (Element) filterNodes.item(i);
            Boolean elementText = Boolean.parseBoolean(filterElement.getElementsByTagName("enabled").item(0).getTextContent());
            toggleButtonArray.get(i).setSelected(elementText);
        }
        for (int i = 0; i < settingsToggleButtonArray.size(); i++) {
            Element settingElement = (Element) settingsNodes.item(i);
            Boolean elementText = Boolean.parseBoolean(settingElement.getElementsByTagName("enabled").item(0).getTextContent());
            settingsToggleButtonArray.get(i).setSelected(elementText);
        }

        //Banner start
        //Wiki image with link
        ImageView wikiLinkImageView = new ImageView(wikiImage);
        wikiLinkImageView.setId("wikiLinkImageView");
        wikiLinkImageView.setFitHeight(48);
        wikiLinkImageView.setFitWidth(87);
        Pane wikiViewPane = new Pane(wikiLinkImageView);
        wikiViewPane.setStyle("-fx-cursor: hand;");
        Tooltip wikiViewPaneToolTip = new Tooltip("https://borderlands.fandom.com/wiki/Borderlands_Wiki");
        wikiViewPaneToolTip.setId("toolTip");
        wikiViewPane.setOnMouseMoved(event -> {
            wikiViewPaneToolTip.show(wikiViewPane, event.getScreenX() + 10, event.getScreenY() + 20);
        });
        wikiViewPane.setOnMouseExited(event -> {
            wikiViewPaneToolTip.hide();
        });
        wikiViewPane.setOnMouseClicked(event -> {
            hostService.showDocument("https://borderlands.fandom.com/wiki/Borderlands_Wiki");
        });
        //BLCL item collection
        ImageView BLCLImageView = new ImageView(icon);
        BLCLImageView.setId("wikiLinkImageView");
        BLCLImageView.setFitHeight(48);
        BLCLImageView.setFitWidth(48);
        Pane BLCLViewPane = new Pane(BLCLImageView);
        BLCLViewPane.setStyle("-fx-cursor: hand;");
        Tooltip BLCLViewPaneToolTip = new Tooltip("https://github.com/FrogMan650/BorderlandsCollectionLog");
        BLCLViewPaneToolTip.setId("toolTip");
        BLCLViewPane.setOnMouseMoved(event -> {
            BLCLViewPaneToolTip.show(BLCLViewPane, event.getScreenX() + 10, event.getScreenY() + 20);
        });
        BLCLViewPane.setOnMouseExited(event -> {
            BLCLViewPaneToolTip.hide();
        });
        BLCLViewPane.setOnMouseClicked(event -> {
            hostService.showDocument("https://github.com/FrogMan650/BorderlandsCollectionLog");
        });
        itemsCollectedLabel = new Label();
        itemsCollectedLabel.setId("collectionLabel");
        Tooltip itemsCollectedToolTip = new Tooltip("Items obtained");
        itemsCollectedToolTip.setId("toolTip");
        itemsCollectedLabel.setOnMouseMoved(event -> {
            itemsCollectedToolTip.show(itemsCollectedLabel, event.getScreenX() + 10, event.getScreenY() + 20);
        });
        itemsCollectedLabel.setOnMouseExited(event -> {
            itemsCollectedToolTip.hide();
        });
        Line itemsCollectedLine = new Line();
        itemsCollectedLine.setId("collectionLine");
        itemsCollectedLine.setStartX(0);
        itemsCollectedLine.setEndX(35);
        itemsTotalLabel = new Label();
        Tooltip itemsTotalToolTip = new Tooltip("Total items");
        itemsTotalToolTip.setId("toolTip");
        itemsTotalLabel.setOnMouseMoved(event -> {
            itemsTotalToolTip.show(itemsTotalLabel, event.getScreenX() + 10, event.getScreenY() + 20);
        });
        itemsTotalLabel.setOnMouseExited(event -> {
            itemsTotalToolTip.hide();
        });
        itemsTotalLabel.setId("collectionLabel");
        VBox itemsCollectedVBox = new VBox(itemsCollectedLabel, itemsCollectedLine, itemsTotalLabel);
        itemsCollectedVBox.setId("collectionVBox");
        //Hunt points collected
        ImageView huntImageView = new ImageView(theHuntImage);
        huntImageView.setId("wikiLinkImageView");
        huntImageView.setFitHeight(48);
        huntImageView.setFitWidth(87);
        Pane huntViewPane = new Pane(huntImageView);
        huntViewPane.setStyle("-fx-cursor: hand;");
        Tooltip huntViewPaneToolTip = new Tooltip("The Hunt is a Borderlands community\n" +
        "scavenger hunt to raise money for\nSt. Jude Children's Research Hospital.\nClick here for more info");
        huntViewPaneToolTip.setId("toolTip");
        huntViewPane.setOnMouseMoved(event -> {
            huntViewPaneToolTip.show(huntViewPane, event.getScreenX() + 10, event.getScreenY() + 20);
        });
        huntViewPane.setOnMouseExited(event -> {
            huntViewPaneToolTip.hide();
        });
        huntViewPane.setOnMouseClicked(event -> {
            hostService.showDocument("https://borderlandshunt.com");
        });
        huntItemsCollectedLabel = new Label();
        huntItemsCollectedLabel.setId("collectionLabel");
        Tooltip huntItemsCollectedToolTip = new Tooltip("Hunt points obtained");
        huntItemsCollectedToolTip.setId("toolTip");
        huntItemsCollectedLabel.setOnMouseMoved(event -> {
            huntItemsCollectedToolTip.show(huntItemsCollectedLabel, event.getScreenX() + 10, event.getScreenY() + 20);
        });
        huntItemsCollectedLabel.setOnMouseExited(event -> {
            huntItemsCollectedToolTip.hide();
        });
        Line huntItemsCollectedLine = new Line();
        huntItemsCollectedLine.setId("collectionLine");
        huntItemsCollectedLine.setStartX(0);
        huntItemsCollectedLine.setEndX(35);
        huntItemsTotalLabel = new Label();
        huntItemsTotalLabel.setId("collectionLabel");
        Tooltip huntItemsTotalToolTip = new Tooltip("Total hunt points");
        huntItemsTotalToolTip.setId("toolTip");
        huntItemsTotalLabel.setOnMouseMoved(event -> {
            huntItemsTotalToolTip.show(huntItemsTotalLabel, event.getScreenX() + 10, event.getScreenY() + 20);
        });
        huntItemsTotalLabel.setOnMouseExited(event -> {
            huntItemsTotalToolTip.hide();
        });
        VBox huntItemsCollectedVBox = new VBox(huntItemsCollectedLabel, huntItemsCollectedLine, huntItemsTotalLabel);
        huntItemsCollectedVBox.setId("collectionVBox");

        Region bannerHPusher = new Region();
        HBox.setHgrow(bannerHPusher, Priority.ALWAYS);

        //Settings
        ImageView settingsImageView = new ImageView(settingsImage);
        settingsImageView.setId("wikiLinkImageView");
        settingsImageView.setFitHeight(48);
        settingsImageView.setFitWidth(48);
        Pane settingsViewPane = new Pane(settingsImageView);
        settingsViewPane.setStyle("-fx-cursor: hand;");
        Tooltip settingsViewPaneToolTip = new Tooltip("Settings");
        settingsViewPaneToolTip.setId("toolTip");
        settingsViewPane.setOnMouseMoved(event -> {
            settingsViewPaneToolTip.show(settingsViewPane, event.getScreenX() + 10, event.getScreenY() + 20);
        });
        settingsViewPane.setOnMouseExited(event -> {
            settingsViewPaneToolTip.hide();
        });
        settingsViewPane.setOnMouseClicked(event -> {
            if (filterVBox.isVisible()) {
                filterVBox.setVisible(false);
                settingsVBox.setVisible(true);
                filterScrollPane.setContent(settingsVBox);
            } else {
                settingsVBox.setVisible(false);
                filterVBox.setVisible(true);
                filterScrollPane.setContent(filterVBox);

            }
        });

        //FPS label
        label2 = new Label("Other info here");
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

        //testing labels
        label3 = new Label("BL1 hunt: " + huntObtainedBL + " / " + huntObtainedBL + " BL1 total: " + countObtainedBL + " / " + countBL);
        VBox sizeVBox = new VBox(label2, label3);
        label4 = new Label("BL2 hunt: " + huntObtainedBL2 + " / " + huntObtainedBL2 + " BL2 total: " + countObtainedBL2 + " / " + countBL2);
        label5 = new Label("BLTPS hunt: " + huntObtainedBLTPS + " / " + huntObtainedBLTPS + " BLTPS total: " + countObtainedBLTPS + " / " + countBLTPS);
        VBox sizeVBox2 = new VBox(label4, label5);
        label6 = new Label("BL3 hunt: " + huntObtainedBL3 + " / " + huntObtainedBL3 + " BL3 total: " + countObtainedBL3 + " / " + countBL3);
        label7 = new Label("BL4 hunt: " + huntObtainedBL4 + " / " + huntObtainedBL4 + " BL4 total: " + countObtainedBL4 + " / " + countBL4);
        VBox sizeVBox3 = new VBox(label6, label7);

        HBox bannerHBox = new HBox(0, wikiViewPane, BLCLViewPane, itemsCollectedVBox, huntViewPane, huntItemsCollectedVBox,bannerHPusher, settingsViewPane);
        HBox.setMargin(wikiViewPane, new Insets(0, 10, 0, 0));
        HBox.setMargin(BLCLViewPane, new Insets(0, 5, 0, 0));
        HBox.setMargin(itemsCollectedVBox, new Insets(0, 10, 0, 0));
        HBox.setMargin(huntViewPane, new Insets(0, 5, 0, 0));
        HBox.setMargin(huntItemsCollectedVBox, new Insets(0, 10, 0, 0));
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

        Scene scene = new Scene(root, 1280, 720);
        scene.getStylesheets().add(this.getClass().getResource("styles.css").toExternalForm());
        //Set stage and scene
        stage.setTitle("Borderlands Collection Log");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
        //itemFlowPane spacing
        itemFlowPane.setPrefWidth(scene.getWidth()-206);
        int itemFlowPaneWidth = (int) itemFlowPane.getPrefWidth();
        int cardsThatFit = (int) Math.floor(itemFlowPaneWidth/336);
        if (cardsThatFit > 1) {
            int hGapValue = (itemFlowPaneWidth-(336*cardsThatFit))/(cardsThatFit-1);
            itemFlowPane.setHgap(hGapValue);
        }

        //Build item cards
        buildAllItemCards();
        resetDisplayedCards(searchTextField.getText());
        updateBannerLabels();

        scene.widthProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                itemScrollPane.setVvalue(0);
                itemFlowPane.setPrefWidth(scene.getWidth()-206);
                int itemFlowPaneWidth = (int) itemFlowPane.getPrefWidth();
                int cardsThatFit = (int) Math.floor(itemFlowPaneWidth/336);
                if (cardsThatFit > 1) {
                    int hGapValue = (itemFlowPaneWidth-(336*cardsThatFit))/(cardsThatFit-1);
                    itemFlowPane.setHgap(hGapValue);
                    clearAllItemCards();
                    displayCardsInViewport();
                    setAllCardsVisible();
                }
            }
        });

        scene.heightProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                clearAllItemCards();
                displayCardsInViewport();
                setAllCardsVisible();
            }
        });

        //Filter cards as you type in the search box like a live search
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            itemScrollPane.setVvalue(0);
            resetDisplayedCards(searchTextField.getText());
        });

        //Load and un-load cards as you scroll to save on performance
        itemScrollPane.vvalueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                label3.setText(newValue+"");
                displayCardsInViewport();
            }
        });

        testingButton.setOnAction(event -> {
            updateBannerLabels();
        });

        testingButton2.setOnAction(event -> {
            
        });
    }

    public static void allToggleButtonsOn(ArrayList<ToggleButton> toggleButtonArray) {
        for (int i = 0; i < toggleButtonArray.size(); i++) {
            toggleButtonArray.get(i).setSelected(true);
            Element settingElement = (Element) filterNodes.item(i);
            settingElement.getElementsByTagName("enabled").item(0).setTextContent("true");
        }
        itemScrollPane.setVvalue(0);
        resetDisplayedCards(searchTextField.getText());
        new Thread(() -> {
            writeToXml(settingsDocument, settingsXML);
        }).start();
    }

    public static void allToggleButtonsOff(ArrayList<ToggleButton> toggleButtonArray) {
        for (int i = 0; i < toggleButtonArray.size(); i++) {
            toggleButtonArray.get(i).setSelected(false);
            Element settingElement = (Element) filterNodes.item(i);
            settingElement.getElementsByTagName("enabled").item(0).setTextContent("false");
        }
        itemScrollPane.setVvalue(0);
        resetDisplayedCards(searchTextField.getText());
        new Thread(() -> {
            writeToXml(settingsDocument, settingsXML); 
        }).start();
    }

    public static void writeToXml(Document document, File file) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource docSource = new DOMSource(document);
            StreamResult docResult = new StreamResult(file);
            transformer.transform(docSource, docResult);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    public static int getCardNumber(String name, String game, String type, String rarity) {
        for (int i = 0; i < itemNodes.getLength(); i++) {
            Element node = (Element) itemNodes.item(i);
            if (node.getElementsByTagName("name").item(0).getTextContent().equals(name) && 
            node.getElementsByTagName("game").item(0).getTextContent().equals(game) && 
            node.getElementsByTagName("type").item(0).getTextContent().equals(type) && 
            node.getElementsByTagName("rarity").item(0).getTextContent().equals(rarity)) {
                return i;
            }
        }
        return -1;
    }

    public static void updateBannerLabels() {
        int totalItemsObtained = 0;
        totalItemsObtained = settingsToggleButtonArray.get(0).isSelected() ? totalItemsObtained+countObtainedBL : totalItemsObtained;
        totalItemsObtained = settingsToggleButtonArray.get(1).isSelected() ? totalItemsObtained+countObtainedBL2 : totalItemsObtained;
        totalItemsObtained = settingsToggleButtonArray.get(2).isSelected() ? totalItemsObtained+countObtainedBLTPS : totalItemsObtained;
        totalItemsObtained = settingsToggleButtonArray.get(3).isSelected() ? totalItemsObtained+countObtainedBL3 : totalItemsObtained;
        totalItemsObtained = settingsToggleButtonArray.get(4).isSelected() ? totalItemsObtained+countObtainedBL4 : totalItemsObtained;
        int totalItemsAvailable = 0;
        totalItemsAvailable = settingsToggleButtonArray.get(0).isSelected() ? totalItemsAvailable+countBL : totalItemsAvailable;
        totalItemsAvailable = settingsToggleButtonArray.get(1).isSelected() ? totalItemsAvailable+countBL2 : totalItemsAvailable;
        totalItemsAvailable = settingsToggleButtonArray.get(2).isSelected() ? totalItemsAvailable+countBLTPS : totalItemsAvailable;
        totalItemsAvailable = settingsToggleButtonArray.get(3).isSelected() ? totalItemsAvailable+countBL3 : totalItemsAvailable;
        totalItemsAvailable = settingsToggleButtonArray.get(4).isSelected() ? totalItemsAvailable+countBL4 : totalItemsAvailable;
        int totalHuntPointsObtained = 0;
        totalHuntPointsObtained = settingsToggleButtonArray.get(5).isSelected() ? totalHuntPointsObtained+huntObtainedBL : totalHuntPointsObtained;
        totalHuntPointsObtained = settingsToggleButtonArray.get(6).isSelected() ? totalHuntPointsObtained+huntObtainedBL2 : totalHuntPointsObtained;
        totalHuntPointsObtained = settingsToggleButtonArray.get(7).isSelected() ? totalHuntPointsObtained+huntObtainedBLTPS : totalHuntPointsObtained;
        totalHuntPointsObtained = settingsToggleButtonArray.get(8).isSelected() ? totalHuntPointsObtained+huntObtainedBL3 : totalHuntPointsObtained;
        totalHuntPointsObtained = settingsToggleButtonArray.get(9).isSelected() ? totalHuntPointsObtained+huntObtainedBL4 : totalHuntPointsObtained;
        int totalHuntPointsAvailable = 0;
        totalHuntPointsAvailable = settingsToggleButtonArray.get(5).isSelected() ? totalHuntPointsAvailable+huntBL : totalHuntPointsAvailable;
        totalHuntPointsAvailable = settingsToggleButtonArray.get(6).isSelected() ? totalHuntPointsAvailable+huntBL2 : totalHuntPointsAvailable;
        totalHuntPointsAvailable = settingsToggleButtonArray.get(7).isSelected() ? totalHuntPointsAvailable+huntBLTPS : totalHuntPointsAvailable;
        totalHuntPointsAvailable = settingsToggleButtonArray.get(8).isSelected() ? totalHuntPointsAvailable+huntBL3 : totalHuntPointsAvailable;
        totalHuntPointsAvailable = settingsToggleButtonArray.get(9).isSelected() ? totalHuntPointsAvailable+huntBL4 : totalHuntPointsAvailable;
        itemsCollectedLabel.setText(""+totalItemsObtained);
        itemsTotalLabel.setText(""+totalItemsAvailable);
        huntItemsCollectedLabel.setText(""+totalHuntPointsObtained);
        huntItemsTotalLabel.setText(""+totalHuntPointsAvailable);
    }

    public static void setAllCardsVisible() {
        for (int i = 0; i < itemFlowPane.getChildren().size(); i++) {
            itemFlowPane.getChildren().get(i).setVisible(true);
        }
    }

    public static void displayCardsInViewport() {
        // clearAllItemCards();
        double flowPaneHeight = itemFlowPane.getHeight();
        Double scrollPaneVValue = itemScrollPane.getVvalue();
        double scrollPaneViewPortHeight = itemScrollPane.getViewportBounds().getHeight();
        double flowPaneLocation = Math.round(flowPaneHeight*scrollPaneVValue);
        int itemFlowPaneWidth = (int) itemFlowPane.getPrefWidth();
        int cardsThatFit = (int) Math.floor(itemFlowPaneWidth/336);
        int cardOnScreen = (int) Math.round(flowPaneLocation/(475/cardsThatFit));
        int cardToLoad = cardOnScreen+(cardsThatFit*5);
        cardToLoad = cardToLoad > itemCardFilteredArray.size() ? itemCardFilteredArray.size() : cardToLoad;
        if (itemFlowPane.getChildren().size() < cardToLoad && itemFlowPane.getChildren().size() != itemCardFilteredArray.size()) {
            for (int i = itemFlowPane.getChildren().size(); i < cardToLoad; i++) {
                itemFlowPane.getChildren().add(itemCardFilteredArray.get(i));
            }
        }
        int lowBounds = (int) Math.round(flowPaneLocation-(scrollPaneViewPortHeight+(scrollPaneViewPortHeight*scrollPaneVValue)));
        int highBounds = (int) Math.round(flowPaneLocation+(scrollPaneViewPortHeight-(scrollPaneViewPortHeight*scrollPaneVValue)));
        for (int i = 0; i < itemFlowPane.getChildren().size(); i++) {
            int number = i;
            if (itemFlowPane.getChildren().get(number).getLayoutY() >= lowBounds && highBounds >= itemFlowPane.getChildren().get(number).getLayoutY()) {
                itemFlowPane.getChildren().get(number).setVisible(true);
            } else {
                itemFlowPane.getChildren().get(number).setVisible(false);
            }
        }  
    }

    public static void clearAllItemCards() {
        itemFlowPane.getChildren().clear();
    }

    public static void clearFilteredItemCards() {
        itemCardFilteredArray.clear();
    }

    public static void resetDisplayedCards(String searchTerm) {
        filterAllItemCards(searchTerm);
        displayCardsInViewport();
        setAllCardsVisible();
    }

    public static void filterAllItemCards(String searchTerm) {
        clearAllItemCards();
        clearFilteredItemCards();
        for (int i = 0; i < itemCardArray.size(); i++) {
            String[] accessibilityText = itemCardArray.get(i).getAccessibleText().split("#%");
            String name = accessibilityText[0].toLowerCase();
            String type = accessibilityText[1].toLowerCase();
            String game = accessibilityText[2];
            String obtained = accessibilityText[3].toLowerCase();
            String rarity = accessibilityText[4].toLowerCase();
            String source = accessibilityText[5].toLowerCase();
            String points = accessibilityText[6];
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
            } else if ((rarity.contains("unique")) && !toggleButtonArray.get(17).isSelected()) {
                continue;
            } else if ((rarity.equals("legendary")) && !toggleButtonArray.get(18).isSelected()) {
                continue;
            } else if ((rarity.equals("seraph")) && !toggleButtonArray.get(19).isSelected()) {
                continue;
            } else if ((rarity.equals("pearl") || rarity.equals("pearlescent")) && !toggleButtonArray.get(20).isSelected()) {
                continue;
            } else if ((rarity.equals("glitch")) && !toggleButtonArray.get(21).isSelected()) {
                continue;
            } else if ((rarity.equals("effervescent")) && !toggleButtonArray.get(22).isSelected()) {
                continue;
            } else if ((type.equals("pistol")) && !toggleButtonArray.get(0).isSelected()) {
                continue;
            } else if ((type.equals("smg") || type.equals("submachine gun")) && !toggleButtonArray.get(1).isSelected()) {
                continue;
            } else if ((type.equals("ar") || type.equals("assault rifle")) && !toggleButtonArray.get(2).isSelected()) {
                continue;
            } else if ((type.equals("shotgun")) && !toggleButtonArray.get(3).isSelected()) {
                continue;
            } else if ((type.equals("sniper") || type.equals("sniper rifle")) && 
            !toggleButtonArray.get(4).isSelected()) {
                continue;
            } else if ((type.equals("launcher") || type.equals("rocket launcher")) && 
            !toggleButtonArray.get(5).isSelected()) {
                continue;
            } else if ((type.equals("eridian")) && !toggleButtonArray.get(6).isSelected()) {
                continue;
            } else if ((type.equals("laser")) && !toggleButtonArray.get(7).isSelected()) {
                continue;
            } else if ((type.equals("class mod")) && !toggleButtonArray.get(8).isSelected()) {
                continue;
            } else if ((type.equals("grenade mod")) && !toggleButtonArray.get(9).isSelected()) {
                continue;
            } else if ((type.equals("relic") || type.equals("artifact")) && 
            !toggleButtonArray.get(10).isSelected()) {
                continue;
            } else if ((type.equals("shield")) && !toggleButtonArray.get(11).isSelected()) {
                continue;
            } else if ((type.equals("oz kit")) && !toggleButtonArray.get(12).isSelected()) {
                continue;
            } else if ((type.equals("enhancement")) && !toggleButtonArray.get(13).isSelected()) {
                continue;
            } else if ((type.equals("repkit")) && !toggleButtonArray.get(14).isSelected()) {
                continue;
            } else if ((type.equals("grenade ordnance") || type.equals("grenade (ordnance)")) && !toggleButtonArray.get(15).isSelected()) {
                continue;
            } else if ((type.equals("heavy ordnance") || type.equals("heavy weapon (ordnance)")) && !toggleButtonArray.get(16).isSelected()) {
                continue;
            } else if (!name.contains(searchTerm)) {
                continue;
            } else if (!source.contains("any suitable loot source") && !toggleButtonArray.get(31).isSelected()) {
                continue;
            } else if (source.contains("any suitable loot source") && !toggleButtonArray.get(30).isSelected()) {
                continue;
            } else if (points.equals("0") && !toggleButtonArray.get(32).isSelected()) {
                continue;
            }
            itemCardFilteredArray.add(itemCardArray.get(i));
        }
    }

    public static void buildAllItemCards() {
        for (int i = 0; i < itemNodes.getLength(); i++) {
            Element itemNode = (Element) itemNodes.item(i);
            String name = itemNode.getElementsByTagName("name").item(0).getTextContent();
            String type = itemNode.getElementsByTagName("type").item(0).getTextContent();
            String game = itemNode.getElementsByTagName("game").item(0).getTextContent();
            Boolean obtained = Boolean.parseBoolean(itemNode.getElementsByTagName("obtained").item(0).getTextContent());
            String rarity = itemNode.getElementsByTagName("rarity").item(0).getTextContent();
            String source = itemNode.getElementsByTagName("source").item(0).getTextContent();
            String text = itemNode.getElementsByTagName("text").item(0).getTextContent();
            String wiki = itemNode.getElementsByTagName("wiki").item(0).getTextContent();
            String points = itemNode.getElementsByTagName("points").item(0).getTextContent();
            String location = itemNode.getElementsByTagName("location").item(0).getTextContent();
            String chance = itemNode.getElementsByTagName("chance").item(0).getTextContent();
            new Thread(() -> {
                buildItemCard(name, type, game, obtained, rarity, source, text, wiki, points, location, chance);
            }).start();
        }
        while (itemCardArray.size() != itemNodes.getLength()) {
        }
        Collections.sort(itemCardArray, new Comparator<Pane>() {
            public int compare(Pane p1, Pane p2) {
                return p1.getAccessibleText().compareTo(p2.getAccessibleText());
            }
        });
    }

    public static void buildItemCard(String name, String type, String game, Boolean obtained, String rarity, 
    String source, String text, String wiki, String points, String location, String chance) {
        Pane itemPane = new Pane();
        StackPane itemImageStackPane = new StackPane();
        itemImageStackPane.setId("itemImageStackPane");
        ImageView itemImageView = new ImageView();
        itemImageView.setCache(true);
        itemImageView.setCacheHint(CacheHint.SPEED);
        //Setting the item type image
        if (type.toLowerCase().equals("pistol")) {
            itemImageView.setImage(pistolImage);
        } else if (type.equals("ar") || type.toLowerCase().equals("assault rifle")) {
            itemImageView.setImage(arImage);
        } else if (type.toLowerCase().equals("class mod")) {
            itemImageView.setImage(classModImage);
        } else if (type.toLowerCase().equals("grenade mod")) {
            itemImageView.setImage(grenadeImage);
        } else if (type.toLowerCase().equals("grenade ordnance") || type.toLowerCase().equals("grenade (ordnance)")) {
            itemImageView.setImage(grenadeOrdnanceImage);
        } else if (type.toLowerCase().equals("heavy ordnance") || type.toLowerCase().equals("heavy weapon (ordnance)")) {
            itemImageView.setImage(heavyOrdnanceImage);
        } else if (type.toLowerCase().equals("laser")) {
            itemImageView.setImage(laserImage);
        } else if (type.toLowerCase().equals("launcher") || type.toLowerCase().equals("rocket launcher")) {
            itemImageView.setImage(launcherImage);
        } else if (type.toLowerCase().equals("oz kit")) {
            itemImageView.setImage(ozKitImage);
        } else if (type.toLowerCase().equals("shield")) {
            itemImageView.setImage(shieldImage);
        } else if (type.toLowerCase().equals("shotgun")) {
            itemImageView.setImage(shotgunImage);
        } else if (type.equals("smg") || type.toLowerCase().equals("submachine gun")) {
            itemImageView.setImage(smgImage);
        } else if (type.toLowerCase().equals("sniper") || type.toLowerCase().equals("sniper rifle")) {
            itemImageView.setImage(sniperImage);
        } else if (type.toLowerCase().equals("relic") || type.toLowerCase().equals("artifact")) {
            itemImageView.setImage(relicImage);
        } else if (type.toLowerCase().equals("eridian")) {
            itemImageView.setImage(eridianImage);
        } else if (type.toLowerCase().equals("repkit")) {
            itemImageView.setImage(repkitImage);
        } else if (type.toLowerCase().equals("enhancement")) {
            itemImageView.setImage(enhancementImage);
        }
        Pane itemBackgroundColor = new Pane();
        itemBackgroundColor.setId("itemBackgroundColor");
        itemImageStackPane.getChildren().addAll(itemBackgroundColor, itemImageView);
        Label gameLabel = new Label("Borderlands " + game);
        gameLabel.setId("gameLabel");
        lock.lock();
        try {
            if (game.equals("")) {
                gameLabel.setText("Borderlands");
                countBL++;
                huntBL += Integer.parseInt(points);
            } else if (game.equals("2")) {
                countBL2++;
                huntBL2 += Integer.parseInt(points);
            } else if (game.equals("TPS")) {
                countBLTPS++;
                huntBLTPS += Integer.parseInt(points);
            } else if (game.equals("3")) {
                countBL3++;
                huntBL3 += Integer.parseInt(points);
            } else if (game.equals("4")) {
                countBL4++;
                huntBL4 += Integer.parseInt(points);
            }
        } finally {
            lock.unlock();
        }
        Pane obtainedPane = new Pane();
        if (obtained) {
            obtainedPane.setBackground(new Background(new BackgroundImage(obtainedImage, null, null, null, null)));
            lock.lock();
            try {
                if (game.equals("")) {
                    countObtainedBL++;
                    huntObtainedBL += Integer.parseInt(points);
                } else if (game.equals("2")) {
                    countObtainedBL2++;
                    huntObtainedBL2 += Integer.parseInt(points);
                } else if (game.equals("TPS")) {
                    countObtainedBLTPS++;
                    huntObtainedBLTPS += Integer.parseInt(points);
                } else if (game.equals("3")) {
                    countObtainedBL3++;
                    huntObtainedBL3 += Integer.parseInt(points);
                } else if (game.equals("4")) {
                    countObtainedBL4++;
                    huntObtainedBL4 += Integer.parseInt(points);
                }
            } finally {
                lock.unlock();
            }
        } else {
            obtainedPane.setBackground(new Background(new BackgroundImage(notObtainedImage, null, null, null, null)));
        }
        obtainedPane.setId("obtainedPane");
        Label huntPointsLabel = new Label(points);
        huntPointsLabel.setId("huntPointsLabel");
        HBox topHBox = new HBox(obtainedPane, gameLabel, huntPointsLabel);
        HBox.setMargin(obtainedPane, new Insets(5, 0, 0, 12));
        //Defining what happens when you click the obtained/not obtained pane
        obtainedPane.setOnMouseClicked(event -> {
            Element node = (Element) itemNodes.item(getCardNumber(name, game, type, rarity));
            Node obtainedNode = node.getElementsByTagName("obtained").item(0);
            if (obtainedNode.getTextContent().equals("true")) {
                obtainedPane.setBackground(new Background(new BackgroundImage(notObtainedImage, null, null, null, null)));
                obtainedNode.setTextContent("false");
                itemPane.setAccessibleText(name + "#%" + type + "#%" + game + "#%false#%" + rarity + "#%" + source + "#%" + points);
                if (game.equals("")) {
                    countObtainedBL--;
                    huntObtainedBL -= Integer.parseInt(points);
                } else if (game.equals("2")) {
                    countObtainedBL2--;
                    huntObtainedBL2 -= Integer.parseInt(points);
                } else if (game.equals("TPS")) {
                    countObtainedBLTPS--;
                    huntObtainedBLTPS -= Integer.parseInt(points);
                } else if (game.equals("3")) {
                    countObtainedBL3--;
                    huntObtainedBL3 -= Integer.parseInt(points);
                } else if (game.equals("4")) {
                    countObtainedBL4--;
                    huntObtainedBL4 -= Integer.parseInt(points);
                }
            } else {
                obtainedPane.setBackground(new Background(new BackgroundImage(obtainedImage, null, null, null, null)));
                obtainedNode.setTextContent("true");
                itemPane.setAccessibleText(name + "#%" + type + "#%" + game + "#%true#%" + rarity + "#%" +  source + "#%" + points);
                if (game.equals("")) {
                    countObtainedBL++;
                    huntObtainedBL += Integer.parseInt(points);
                } else if (game.equals("2")) {
                    countObtainedBL2++;
                    huntObtainedBL2 += Integer.parseInt(points);
                } else if (game.equals("TPS")) {
                    countObtainedBLTPS++;
                    huntObtainedBLTPS += Integer.parseInt(points);
                } else if (game.equals("3")) {
                    countObtainedBL3++;
                    huntObtainedBL3 += Integer.parseInt(points);
                } else if (game.equals("4")) {
                    countObtainedBL4++;
                    huntObtainedBL4 += Integer.parseInt(points);
                }
            }
            resetDisplayedCards(searchTextField.getText());
            updateBannerLabels();
            new Thread(() -> {
                writeToXml(itemDocument, itemsXML);
            }).start();
        });
        HBox.setMargin(huntPointsLabel, new Insets(7, 0, 0, 23));
        HBox.setMargin(gameLabel, new Insets(0, 0, 0, 25));
        Label itemNameLabel = new Label(name);
        itemNameLabel.setId("itemNameLabel");
        //Setting the color of item image and text
        if (rarity.toLowerCase().equals("legendary")) {
            itemNameLabel.setTextFill(Paint.valueOf("#eb8a01"));
            itemBackgroundColor.setStyle("-fx-background-color: #eb8a01;");
        } else if (rarity.toLowerCase().equals("pearl") || rarity.toLowerCase().equals("pearlescent")) {
            itemNameLabel.setTextFill(Paint.valueOf("#00ffff"));
            itemBackgroundColor.setStyle("-fx-background-color: #00ffff;");
        } else if (rarity.toLowerCase().equals("unique")) {
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
        } else if (rarity.equals("unique_white")) {
            itemNameLabel.setTextFill(Paint.valueOf("#bdbdbdff"));
            itemBackgroundColor.setStyle("-fx-background-color: #bdbdbdff;");
        } else if (rarity.toLowerCase().equals("seraph") || rarity.toLowerCase().equals("glitch")) {
            itemNameLabel.setTextFill(Paint.valueOf("#ff69b4"));
            itemBackgroundColor.setStyle("-fx-background-color: #ff69b4;");
        } else if (rarity.toLowerCase().equals("effervescent")) {
            itemBackgroundColor.setBackground(new Background(new BackgroundImage(effervescentBackground, null, null, null, null)));
            itemNameLabel.setTextFill(Paint.valueOf("linear-gradient(to right, red 0%, orange 20%, yellow 40%, rgb(0, 255, 0) 60%, rgb(101, 101, 255) 80%, rgb(212, 0, 255) 100%)"));
        }
        //Setting size of item name text to fit in the label
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

        //Item text VBox to store flavor text and sources text
        VBox itemTextVBox = new VBox();
        itemTextVBox.setId("itemTextVBox");

        //Flavor text start
        String[] flavorTextSplit = text.split("_");
        for (int i = 0; i < flavorTextSplit.length; i++) {
            Label tempFlavorTextLabel = new Label();
            VBox.setMargin(tempFlavorTextLabel, new Insets(0, 0 ,0, 10));
            tempFlavorTextLabel.setId("flavorTextLabel");
            tempFlavorTextLabel.setText("\u2022 " + flavorTextSplit[i]);
            if (name.equals("Norfleet")) {
                tempFlavorTextLabel.setStyle("-fx-text-fill:#eb8a01;");
            } else if (flavorTextSplit[i].contains("See red text for description")) {
                tempFlavorTextLabel.setStyle("-fx-text-fill:#3dd20b;");
            }
            if (!text.isEmpty()) {
                itemTextVBox.getChildren().add(tempFlavorTextLabel);
            }
        }

        //Sources text start
        Label sourcesLabel = new Label("Sources");
        sourcesLabel.setId("sourcesLabel");
        itemTextVBox.getChildren().addAll(sourcesLabel);
        itemTextVBox.setFillWidth(false);
        String[] sourceTextSplit = source.split("_");
        String[] locationTextSplit = location.split("_");
        String[] chanceTextSplit = chance.split("_");
        for (int i = 0; i < sourceTextSplit.length; i++) {
            Label tempSourceTextLabel = new Label();
            VBox.setMargin(tempSourceTextLabel, new Insets(0, 0 ,0, 10));
            tempSourceTextLabel.setId("sourcesListLabel");
            tempSourceTextLabel.setText("\u2022 " + sourceTextSplit[i]);
            itemTextVBox.getChildren().addAll(tempSourceTextLabel);
            if (!location.isEmpty()) {
                if (!locationTextSplit[i].isEmpty()) {
                    Tooltip tempSourceTextToolTip = new Tooltip();
                    tempSourceTextToolTip.setText(locationTextSplit[i] + "\n" + chanceTextSplit[i] + "%");
                    tempSourceTextToolTip.setId("toolTip");
                    tempSourceTextLabel.setOnMouseMoved(event -> {
                        tempSourceTextToolTip.show(tempSourceTextLabel, event.getScreenX() + 10, event.getScreenY() + 20);
                    });
                    tempSourceTextLabel.setOnMouseExited(event -> {
                        tempSourceTextToolTip.hide();
                    });
                }
            }
        }
        //Item wiki button
        ImageView itemWikiLinkImageView = new ImageView(wikiImage);
        itemWikiLinkImageView.setId("itemWikiLinkImageView");
        itemWikiLinkImageView.setFitHeight(30);
        itemWikiLinkImageView.setFitWidth(60);
        Pane itemWikiLinkPane = new Pane(itemWikiLinkImageView);
        itemWikiLinkPane.setId("itemWikiLinkPane");
        Tooltip itemWikiLinkPaneToolTip = new Tooltip(wiki);
        itemWikiLinkPaneToolTip.setId("toolTip");
        itemWikiLinkPane.setOnMouseMoved(event -> {
            itemWikiLinkPaneToolTip.show(itemWikiLinkPane, event.getScreenX() + 10, event.getScreenY() + 20);
        });
        itemWikiLinkPane.setOnMouseExited(event -> {
            itemWikiLinkPaneToolTip.hide();
        });
        itemWikiLinkPane.setOnMouseClicked(event -> {
            hostService.showDocument(wiki);
        });

        StackPane.setMargin(itemWikiLinkPane, new Insets(130, 200, 0, -50));
        itemImageStackPane.getChildren().add(itemWikiLinkPane);

        ScrollPane itemTextScrollPane = new ScrollPane(itemTextVBox);
        itemTextScrollPane.setId("itemTextScrollPane");
        
        VBox itemVBox = new VBox(topHBox, itemNameLabel, itemImageStackPane, itemTextScrollPane);
        itemVBox.setId("itemVBox");
        VBox.setMargin(itemNameLabel, new Insets(5, 0 ,0, 0));
        VBox.setMargin(itemImageStackPane, new Insets(1, 0 ,0, 0));
        VBox.setMargin(sourcesLabel, new Insets(0, 0 ,0, 10));
        itemPane.getChildren().add(itemVBox);
        itemPane.setId("itemPane");
        itemPane.setCache(true);
        itemPane.setCacheHint(CacheHint.SPEED);
        itemPane.setVisible(true);
        itemPane.setAccessibleText(name + "#%" + type + "#%" + game + "#%" + obtained + "#%" + rarity + "#%" + source + "#%" + points);
        //Lock the Array as multithreading can cause cards to be lost if multiple threads try to add a card at the same time
        lock.lock();
        try {
            itemCardArray.add(itemPane);
        } finally {
            lock.unlock();
        }
    }
}
