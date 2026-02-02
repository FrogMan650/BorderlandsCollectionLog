package com.frogman650;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    //Item spreadsheet: https://docs.google.com/spreadsheets/d/1W7sitzbmaNyniM4CwMG44fDkvS6bWYgrKa3ZrBdT-Nk/edit?usp=sharing
    public static Image icon;
    public static Image wikiImage;
    public static Image miniLootlemonImage;
    public static Image lootlemonImage;
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
    public static Image settingsHoverImage;
    public static FlowPane itemFlowPane;
    public static ScrollPane itemScrollPane;
    public static TextField searchTextField;
    public static Document settingsDocument;
    public static Document saveDocument;
    public static Element saveNode;
    public static NodeList filterNodes;
    public static NodeList settingsNodes;
    public static ArrayList<Pane> itemCardArray = new ArrayList<>();
    public static ArrayList<Pane> itemCardFilteredArray = new ArrayList<>();
    public static ArrayList<ToggleButton> toggleButtonArray = new ArrayList<>();
    public static ArrayList<ToggleButton> settingsToggleButtonArray = new ArrayList<>();
    public static File itemsXML;
    public static File settingsXML;
    public static File saveXML;
    public static HostServices hostService;
    public static Lock lock = new ReentrantLock();
    public static ArrayList<String> saveFiles = new ArrayList<>();
    public static String loadedProfile;
    public static ComboBox<String> profileCombobox;
    public static Element profileSettingElement;
    public static Button profileDisplayButton;

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

    public static DocumentBuilderFactory factory;
    public static DocumentBuilder builder;
    public static File executableDirectory;
    public static int totalNodes = 0;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Font willowBody = Font.loadFont(getClass().getResourceAsStream("WillowBody-Regular.ttf"), 10);
        icon = new Image(getClass().getResourceAsStream("BLCL_logo_mini.png"));
        wikiImage = new Image(getClass().getResourceAsStream("Wiki_logo_mini.png"));
        miniLootlemonImage = new Image(getClass().getResourceAsStream("lootlemon_mini.png"));
        lootlemonImage = new Image(getClass().getResourceAsStream("lootlemon_cropped.png"));
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
        settingsHoverImage = new Image(getClass().getResourceAsStream("settings_hover.png"));
        hostService = getHostServices();

        URI uri = getClass().getProtectionDomain().getCodeSource().getLocation().toURI();
        executableDirectory = Paths.get(uri).getParent().toFile();
        settingsXML = new File(executableDirectory, "settings.xml");

        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        settingsDocument = builder.parse(settingsXML);
        settingsNodes = settingsDocument.getDocumentElement().getElementsByTagName("setting");
        getSaveFiles();

        NodeList profileNode = settingsDocument.getDocumentElement().getElementsByTagName("profile");
        profileSettingElement = (Element) profileNode.item(0);
        loadedProfile = profileSettingElement.getElementsByTagName("name").item(0).getTextContent();
        if (!saveFiles.contains(loadedProfile)) {
            loadedProfile = saveFiles.get(0);
            profileSettingElement.getElementsByTagName("name").item(0).setTextContent(loadedProfile);
            writeToXml(settingsDocument, settingsXML);
        }
        saveXML = new File(executableDirectory + "/saves", loadedProfile +".xml");
        saveDocument = builder.parse(saveXML);
        saveNode = saveDocument.getDocumentElement();
        filterNodes = settingsDocument.getDocumentElement().getElementsByTagName("filter");

        //Item cards holder
        itemFlowPane = new FlowPane();
        itemFlowPane.setId("itemFlowPane");
        itemFlowPane.setPadding(new Insets(0));

        itemScrollPane = new ScrollPane(itemFlowPane);
        itemScrollPane.setId("itemScrollPane");

        //Filters
        searchTextField = new TextField();
        searchTextField.setId("searchTextField");
        searchTextField.setPromptText("Item / Source");
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

        filterVBox.getChildren().addAll(filterLabel, allNoneHBox, searchTextField);
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
        settingsVBox.getChildren().add(settingsLabel);
        Label profileSelectionLabel = new Label("PROFILES");
        profileSelectionLabel.setId("filterLabel");
        profileSelectionLabel.setStyle("-fx-cursor: none;");
        profileDisplayButton = new Button(loadedProfile);
        profileDisplayButton.setId("searchTextField");
        profileCombobox = new ComboBox<>();
        profileCombobox.setId("searchTextField");
        profileCombobox.setEditable(true);
        updateComboBox();
        Button loadProfileButton = new Button("Load Profile");
        loadProfileButton.setId("greenButton");
        loadProfileButton.setOnAction(event -> {
            try {
                String newProfile = profileCombobox.getValue();
                File saveToLoad = new File(executableDirectory.getPath() + "/saves", newProfile + ".xml");
                if (saveToLoad.exists()) {
                    updateProfileInfo(newProfile);
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        });
        Button createNewProfileButton = new Button("Create Profile");
        createNewProfileButton.setId("greenButton");
        createNewProfileButton.setOnAction(event -> {
            try {
                String newProfile = profileCombobox.getValue();
                File newSave = new File(executableDirectory.getPath() + "/saves", newProfile + ".xml");
                if (!newSave.exists()) {
                    Files.write(newSave.toPath(), "<items></items>".getBytes());
                    updateProfileInfo(newProfile);
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        });
        Button renameProfileButton = new Button("Rename Profile");
        renameProfileButton.setId("greenButton");
        renameProfileButton.setOnAction(event -> {
            try {
                String newProfile = profileCombobox.getValue();
                File oldSave = new File(executableDirectory.getPath() + "/saves", loadedProfile + ".xml");
                File newSave = new File(executableDirectory.getPath() + "/saves", newProfile + ".xml");
                if (!newSave.exists()) {
                    Files.move(oldSave.toPath(), newSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    updateProfileInfo(newProfile);
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        });
        Button resetProfileButton = new Button("Reset Profile");
        resetProfileButton.setId("redButton");
        resetProfileButton.setOnAction(event -> {
            
        });
        Button deleteProfileButton = new Button("Delete Profile");
        deleteProfileButton.setId("redButton");
        deleteProfileButton.setOnAction(event -> {
            
        });
        settingsVBox.getChildren().addAll(profileSelectionLabel, profileDisplayButton, profileCombobox, 
            loadProfileButton,createNewProfileButton, renameProfileButton, resetProfileButton, deleteProfileButton);

        Label modeSelectionLabel = new Label("MODE");
        modeSelectionLabel.setId("filterLabel");
        modeSelectionLabel.setStyle("-fx-cursor: none;");
        ToggleButton huntModeToggleButton = new ToggleButton("Hunt Mode");//0
        settingsToggleButtonArray.add(huntModeToggleButton);
        Tooltip huntModeToolTip = new Tooltip("Hunt Mode will only show items\n" +
        "worth Hunt points and their sources will\nbetter reflect The Hunt rules." +
        "\nMore information on rules and regulations for\n The Hunt can be found on borderlandshunt.com.");
        huntModeToolTip.setId("toolTip");
        huntModeToggleButton.setOnMouseMoved(event -> {
            huntModeToolTip.show(huntModeToggleButton, event.getScreenX() + 10, event.getScreenY() + 20);
        });
        huntModeToggleButton.setOnMouseExited(event -> {
            huntModeToolTip.hide();
        });
        ToggleButton phospheneModeToggleButton = new ToggleButton("Phosphene Mode");//1
        settingsToggleButtonArray.add(phospheneModeToggleButton);
        Tooltip phospheneModeToolTip = new Tooltip("Phosphene Mode will only show items\n" +
        "with a phosphene skin variant.");
        phospheneModeToolTip.setId("toolTip");
        phospheneModeToggleButton.setOnMouseMoved(event -> {
            phospheneModeToolTip.show(phospheneModeToggleButton, event.getScreenX() + 10, event.getScreenY() + 20);
        });
        phospheneModeToggleButton.setOnMouseExited(event -> {
            phospheneModeToolTip.hide();
        });
        settingsVBox.getChildren().addAll(modeSelectionLabel, huntModeToggleButton, phospheneModeToggleButton);
        Label itemCollectionLabel = new Label("COLLECTION");
        itemCollectionLabel.setId("filterLabel");
        Tooltip itemCollectionToolTip = new Tooltip("These settings control which games items\nwill " + 
        "contribute to the items collected\non the banner at the top.");
        itemCollectionToolTip.setId("toolTip");
        itemCollectionLabel.setOnMouseMoved(event -> {
            itemCollectionToolTip.show(itemCollectionLabel, event.getScreenX() + 10, event.getScreenY() + 20);
        });
        itemCollectionLabel.setOnMouseExited(event -> {
            itemCollectionToolTip.hide();
        });
        ToggleButton toggleButtonCollectionBL = new ToggleButton("Borderlands");//2
        settingsToggleButtonArray.add(toggleButtonCollectionBL);
        ToggleButton toggleButtonCollectionBL2 = new ToggleButton("Borderlands 2");//3
        settingsToggleButtonArray.add(toggleButtonCollectionBL2);
        ToggleButton toggleButtonCollectionBLTPS = new ToggleButton("Borderlands TPS");//4
        settingsToggleButtonArray.add(toggleButtonCollectionBLTPS);
        ToggleButton toggleButtonCollectionBL3 = new ToggleButton("Borderlands 3");//5
        settingsToggleButtonArray.add(toggleButtonCollectionBL3);
        ToggleButton toggleButtonCollectionBL4 = new ToggleButton("Borderlands 4");//6
        settingsToggleButtonArray.add(toggleButtonCollectionBL4);
        settingsVBox.getChildren().addAll(itemCollectionLabel, toggleButtonCollectionBL, toggleButtonCollectionBL2,
        toggleButtonCollectionBLTPS, toggleButtonCollectionBL3, toggleButtonCollectionBL4);
        Label theHuntLabel = new Label("THE HUNT");
        theHuntLabel.setId("filterLabel");
        Tooltip huntCollectionToolTip = new Tooltip("These settings control which games items\nwill " +
        "contribute to the hunt point\ntotal on the banner at the top.");
        huntCollectionToolTip.setId("toolTip");
        theHuntLabel.setOnMouseMoved(event -> {
            huntCollectionToolTip.show(theHuntLabel, event.getScreenX() + 10, event.getScreenY() + 20);
        });
        theHuntLabel.setOnMouseExited(event -> {
            huntCollectionToolTip.hide();
        });
        ToggleButton toggleButtonHuntBL = new ToggleButton("Borderlands");//7
        settingsToggleButtonArray.add(toggleButtonHuntBL);
        ToggleButton toggleButtonHuntBL2 = new ToggleButton("Borderlands 2");//8
        settingsToggleButtonArray.add(toggleButtonHuntBL2);
        ToggleButton toggleButtonHuntBLTPS = new ToggleButton("Borderlands TPS");//9
        settingsToggleButtonArray.add(toggleButtonHuntBLTPS);
        ToggleButton toggleButtonHuntBL3 = new ToggleButton("Borderlands 3");//10
        settingsToggleButtonArray.add(toggleButtonHuntBL3);
        ToggleButton toggleButtonHuntBL4 = new ToggleButton("Borderlands 4");//11
        settingsToggleButtonArray.add(toggleButtonHuntBL4);
        settingsVBox.getChildren().addAll(theHuntLabel, toggleButtonHuntBL, toggleButtonHuntBL2,
        toggleButtonHuntBLTPS, toggleButtonHuntBL3, toggleButtonHuntBL4);
        Label miscSettingLabel = new Label("MISCELLANEOUS");
        miscSettingLabel.setId("filterLabel");
        miscSettingLabel.setStyle("-fx-cursor: none;");
        ToggleButton toggleButtonHideUnobtainable = new ToggleButton("Hide Unobtainable");//12
        settingsToggleButtonArray.add(toggleButtonHideUnobtainable);
        Tooltip unobtainableButtonTooltip = new Tooltip("This setting will hide items that are only\nobtainable " +
        "through promotional DLC, limited time\npromotions, or are just in general not obtainable\nby any normal or " +
        "legitimate means.\n Example: Contraband Sky Rocket from BL2");
        unobtainableButtonTooltip.setId("toolTip");
        toggleButtonHideUnobtainable.setOnMouseMoved(event -> {
            unobtainableButtonTooltip.show(toggleButtonHideUnobtainable, event.getScreenX() + 10, event.getScreenY() + 20);
        });
        toggleButtonHideUnobtainable.setOnMouseExited(event -> {
            unobtainableButtonTooltip.hide();
        });
        toggleButtonHideUnobtainable.setOnAction(event -> {
            Element settingElement = (Element) settingsNodes.item(11);
            if (toggleButtonHideUnobtainable.isSelected()) {
                settingElement.getElementsByTagName("enabled").item(0).setTextContent("true");
            } else {
                settingElement.getElementsByTagName("enabled").item(0).setTextContent("false");
            }
            try {
                fullReset();
            } catch (Exception e) {
                e.printStackTrace();
            }
            new Thread(() -> {
                writeToXml(settingsDocument, settingsXML);
            }).start();
        });
        Button resetSaveButton = new Button("Save Reset");
        resetSaveButton.setId("redButton");
        Tooltip resetButtonTooltip = new Tooltip("Use this button to reset your progress.\nTHIS IS NOT REVERSIBLE");
        resetButtonTooltip.setId("toolTip");
        resetSaveButton.setOnMouseMoved(event -> {
            resetButtonTooltip.show(resetSaveButton, event.getScreenX() + 10, event.getScreenY() + 20);
        });
        resetSaveButton.setOnMouseExited(event -> {
            resetButtonTooltip.hide();
        });
        resetSaveButton.setOnAction(event -> {
            if (resetSaveButton.getText().equals("Save Reset")) {
                resetSaveButton.setText("REALLY?");
            } else if (resetSaveButton.getText().equals("REALLY?")) {
                resetSaveButton.setText("REALLLLY???!??");
            } else if (resetSaveButton.getText().equals("REALLLLY???!??")) {
                resetSaveButton.setText("Save Reset");
                NodeList childNodes = saveNode.getChildNodes();
                for (int i = childNodes.getLength()-1; i >= 0; i--) {
                    saveNode.removeChild(childNodes.item(i));
                }
                writeToXml(saveDocument, new File(executableDirectory, "save.xml"));
                try {
                    fullReset();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        settingsVBox.getChildren().addAll(miscSettingLabel, toggleButtonHideUnobtainable, resetSaveButton);
        for (int i = 1; i < 11; i++) {
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
        huntModeToggleButton.setOnAction(event -> {
            Element settingElement = (Element) settingsNodes.item(0);
            if (huntModeToggleButton.isSelected()) {
                settingElement.getElementsByTagName("enabled").item(0).setTextContent("true");
            } else {
                settingElement.getElementsByTagName("enabled").item(0).setTextContent("false");
            }
            try {
                itemScrollPane.setVvalue(0);
                fullReset();
            } catch (Exception e) {
                e.printStackTrace();
            }
            new Thread(() -> {
                writeToXml(settingsDocument, settingsXML);
            }).start();
        });
        phospheneModeToggleButton.setOnAction(event -> {
            Element settingElement = (Element) settingsNodes.item(1);
            if (phospheneModeToggleButton.isSelected()) {
                settingElement.getElementsByTagName("enabled").item(0).setTextContent("true");
            } else {
                settingElement.getElementsByTagName("enabled").item(0).setTextContent("false");
            }
            try {
                itemScrollPane.setVvalue(0);
                fullReset();
            } catch (Exception e) {
                e.printStackTrace();
            }
            new Thread(() -> {
                writeToXml(settingsDocument, settingsXML);
            }).start();
        });
        itemCollectionLabel.setOnMouseClicked(event -> {
            Boolean allEnabled = toggleButtonCollectionBL.isSelected() && toggleButtonCollectionBL2.isSelected() && 
            toggleButtonCollectionBL3.isSelected() && toggleButtonCollectionBL4.isSelected() && toggleButtonCollectionBLTPS.isSelected();
            String enabledString = allEnabled ? "false" : "true";
            for (int i = 2; i < 7; i++) {
                Element settingElement = (Element) settingsNodes.item(i);
                settingElement.getElementsByTagName("enabled").item(0).setTextContent(enabledString);
                settingsToggleButtonArray.get(i).setSelected(Boolean.parseBoolean(enabledString));
            }
            updateBannerLabels();
            new Thread(() -> {
                writeToXml(settingsDocument, settingsXML);
            }).start();
        });
        theHuntLabel.setOnMouseClicked(event -> {
            Boolean allEnabled = toggleButtonHuntBL.isSelected() && toggleButtonHuntBL2.isSelected() && 
            toggleButtonHuntBL3.isSelected() && toggleButtonHuntBL4.isSelected() && toggleButtonHuntBLTPS.isSelected();
            String enabledString = allEnabled ? "false" : "true";
            for (int i = 7; i < 12; i++) {
                Element settingElement = (Element) settingsNodes.item(i);
                settingElement.getElementsByTagName("enabled").item(0).setTextContent(enabledString);
                settingsToggleButtonArray.get(i).setSelected(Boolean.parseBoolean(enabledString));
            }
            updateBannerLabels();
            new Thread(() -> {
                writeToXml(settingsDocument, settingsXML);
            }).start();
        });

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

        //Banner Start================================================================================================
        //Wiki image with link
        ImageView wikiLinkImageView = new ImageView(wikiImage);
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
        //Lootlemon image with link
        ImageView lootlemonImageView = new ImageView(lootlemonImage);
        lootlemonImageView.setFitHeight(48);
        lootlemonImageView.setFitWidth(26);
        Pane lootlemonViewPane = new Pane(lootlemonImageView);
        lootlemonViewPane.setStyle("-fx-cursor: hand;");
        Tooltip lootlemonViewPaneToolTip = new Tooltip("https://www.lootlemon.com/");
        lootlemonViewPaneToolTip.setId("toolTip");
        lootlemonViewPane.setOnMouseMoved(event -> {
            lootlemonViewPaneToolTip.show(lootlemonViewPane, event.getScreenX() + 10, event.getScreenY() + 20);
        });
        lootlemonViewPane.setOnMouseExited(event -> {
            lootlemonViewPaneToolTip.hide();
        });
        lootlemonViewPane.setOnMouseClicked(event -> {
            hostService.showDocument("https://www.lootlemon.com/");
        });
        //BLCL item collection
        ImageView BLCLImageView = new ImageView(icon);
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
            hostService.showDocument("https://mentalmars.com/the-hunt/");
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
        Region bannerHPusher2 = new Region();
        HBox.setHgrow(bannerHPusher2, Priority.ALWAYS);

        //Settings
        ImageView settingsImageView = new ImageView(settingsImage);
        settingsImageView.setId("settingsImageView");
        settingsImageView.setFitHeight(48);
        settingsImageView.setFitWidth(48);
        Pane settingsViewPane = new Pane(settingsImageView);
        settingsViewPane.setStyle("-fx-cursor: hand;");
        Tooltip settingsViewPaneToolTip = new Tooltip("Settings");
        settingsViewPaneToolTip.setId("toolTip");
        settingsViewPane.setOnMouseMoved(event -> {
            settingsViewPaneToolTip.show(settingsViewPane, event.getScreenX() + 10, event.getScreenY() + 20);
            settingsImageView.setImage(settingsHoverImage);
        });
        settingsViewPane.setOnMouseExited(event -> {
            settingsViewPaneToolTip.hide();
            settingsImageView.setImage(settingsImage);
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

        HBox bannerHBox = new HBox(0, wikiViewPane, lootlemonViewPane, bannerHPusher2, BLCLViewPane, itemsCollectedVBox, huntViewPane, huntItemsCollectedVBox, bannerHPusher, settingsViewPane);
        HBox.setMargin(wikiViewPane, new Insets(0, 10, 0, 0));
        HBox.setMargin(lootlemonViewPane, new Insets(0, 10, 0, 0));
        HBox.setMargin(BLCLViewPane, new Insets(0, 5, 0, 0));
        HBox.setMargin(itemsCollectedVBox, new Insets(0, 10, 0, 0));
        HBox.setMargin(huntViewPane, new Insets(0, 5, 0, 0));
        HBox.setMargin(huntItemsCollectedVBox, new Insets(0, 10, 0, 0));
        bannerHBox.setId("bannerBox");
        //Banner End===============================================================================================

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

        //Adjust card spacing and cards in viewport based on the scenes width
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

        //Adjust card spacing and cards in viewport based on the scenes height
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
                displayCardsInViewport();
            }
        });
    }

    //Set all filter toggle buttons to selected
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

    //Set all filter toggle buttons to not selected
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

    //Write the provided document to the provided file
    public static void writeToXml(Document document, File file) {
        try {
            document.normalize();
            XPath xPath = XPathFactory.newInstance().newXPath();
            NodeList nodeList = (NodeList) xPath.evaluate("//text()[normalize-space()='']", document, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                node.getParentNode().removeChild(node);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", 2);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource docSource = new DOMSource(document);
            StreamResult docResult = new StreamResult(file);
            transformer.transform(docSource, docResult);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    //Update the banner item counters taking into account the settings
    public static void updateBannerLabels() {
        int totalItemsObtained = 0;
        totalItemsObtained = settingsToggleButtonArray.get(2).isSelected() ? totalItemsObtained+countObtainedBL : totalItemsObtained;
        totalItemsObtained = settingsToggleButtonArray.get(3).isSelected() ? totalItemsObtained+countObtainedBL2 : totalItemsObtained;
        totalItemsObtained = settingsToggleButtonArray.get(4).isSelected() ? totalItemsObtained+countObtainedBLTPS : totalItemsObtained;
        totalItemsObtained = settingsToggleButtonArray.get(5).isSelected() ? totalItemsObtained+countObtainedBL3 : totalItemsObtained;
        totalItemsObtained = settingsToggleButtonArray.get(6).isSelected() ? totalItemsObtained+countObtainedBL4 : totalItemsObtained;
        int totalItemsAvailable = 0;
        totalItemsAvailable = settingsToggleButtonArray.get(2).isSelected() ? totalItemsAvailable+countBL : totalItemsAvailable;
        totalItemsAvailable = settingsToggleButtonArray.get(3).isSelected() ? totalItemsAvailable+countBL2 : totalItemsAvailable;
        totalItemsAvailable = settingsToggleButtonArray.get(4).isSelected() ? totalItemsAvailable+countBLTPS : totalItemsAvailable;
        totalItemsAvailable = settingsToggleButtonArray.get(5).isSelected() ? totalItemsAvailable+countBL3 : totalItemsAvailable;
        totalItemsAvailable = settingsToggleButtonArray.get(6).isSelected() ? totalItemsAvailable+countBL4 : totalItemsAvailable;
        int totalHuntPointsObtained = 0;
        totalHuntPointsObtained = settingsToggleButtonArray.get(7).isSelected() ? totalHuntPointsObtained+huntObtainedBL : totalHuntPointsObtained;
        totalHuntPointsObtained = settingsToggleButtonArray.get(8).isSelected() ? totalHuntPointsObtained+huntObtainedBL2 : totalHuntPointsObtained;
        totalHuntPointsObtained = settingsToggleButtonArray.get(9).isSelected() ? totalHuntPointsObtained+huntObtainedBLTPS : totalHuntPointsObtained;
        totalHuntPointsObtained = settingsToggleButtonArray.get(10).isSelected() ? totalHuntPointsObtained+huntObtainedBL3 : totalHuntPointsObtained;
        totalHuntPointsObtained = settingsToggleButtonArray.get(11).isSelected() ? totalHuntPointsObtained+huntObtainedBL4 : totalHuntPointsObtained;
        int totalHuntPointsAvailable = 0;
        totalHuntPointsAvailable = settingsToggleButtonArray.get(7).isSelected() ? totalHuntPointsAvailable+huntBL : totalHuntPointsAvailable;
        totalHuntPointsAvailable = settingsToggleButtonArray.get(8).isSelected() ? totalHuntPointsAvailable+huntBL2 : totalHuntPointsAvailable;
        totalHuntPointsAvailable = settingsToggleButtonArray.get(9).isSelected() ? totalHuntPointsAvailable+huntBLTPS : totalHuntPointsAvailable;
        totalHuntPointsAvailable = settingsToggleButtonArray.get(10).isSelected() ? totalHuntPointsAvailable+huntBL3 : totalHuntPointsAvailable;
        totalHuntPointsAvailable = settingsToggleButtonArray.get(11).isSelected() ? totalHuntPointsAvailable+huntBL4 : totalHuntPointsAvailable;
        itemsCollectedLabel.setText(""+totalItemsObtained);
        itemsTotalLabel.setText(""+totalItemsAvailable);
        huntItemsCollectedLabel.setText(""+totalHuntPointsObtained);
        huntItemsTotalLabel.setText(""+totalHuntPointsAvailable);
    }

    //Set all cards in the FlowPane to be visible
    //Primarily just for initially loading cards into the FlowPane
    public static void setAllCardsVisible() {
        for (int i = 0; i < itemFlowPane.getChildren().size(); i++) {
            itemFlowPane.getChildren().get(i).setVisible(true);
        }
    }

    //Set cards in the viewport or just outside the viewport visible
    public static void displayCardsInViewport() {
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

    //Rebuild all item cards and reset the item counters on the banner
    public static void fullReset() throws Exception {
        itemCardArray.clear();
        buildAllItemCards();
        resetDisplayedCards(searchTextField.getText());
        updateBannerLabels();
    }

    //Clear all item cards from the FlowPane
    public static void clearAllItemCards() {
        itemFlowPane.getChildren().clear();
    }

    //Clear the filtered item card array
    public static void clearFilteredItemCards() {
        itemCardFilteredArray.clear();
    }

    //Re-filter item cards and display them
    public static void resetDisplayedCards(String searchTerm) {
        filterAllItemCards(searchTerm.toLowerCase());
        displayCardsInViewport();
        setAllCardsVisible();
    }

    //Reset all of the item counters
    public static void resetCounters() {
        totalNodes = 0;
        countBL= 0;
        countObtainedBL= 0;
        countBL2= 0;
        countObtainedBL2= 0;
        countBLTPS= 0;
        countObtainedBLTPS= 0;
        countBL3= 0;
        countObtainedBL3= 0;
        countBL4= 0;
        countObtainedBL4= 0;
        huntBL= 0;
        huntObtainedBL= 0;
        huntBL2= 0;
        huntObtainedBL2= 0;
        huntBLTPS= 0;
        huntObtainedBLTPS= 0;
        huntBL3= 0;
        huntObtainedBL3= 0;
        huntBL4= 0;
        huntObtainedBL4= 0;
    }

    //Filter all item cards into an array
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
            String chance = accessibilityText[accessibilityText.length-1].toLowerCase();
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
            } else if (!name.contains(searchTerm) && !source.contains(searchTerm)) {
                continue;
            } else if (!source.contains("any suitable loot source") && !toggleButtonArray.get(31).isSelected()) {
                continue;
            } else if (source.contains("any suitable loot source") && !toggleButtonArray.get(30).isSelected()) {
                continue;
            } else if (points.equals("0") && (!toggleButtonArray.get(32).isSelected() || settingsToggleButtonArray.get(0).isSelected())) {
                continue;
            } else if (chance.equals("unobtainable") && settingsToggleButtonArray.get(12).isSelected()) {
                continue;
            } else if (!chance.contains("phosphene") && settingsToggleButtonArray.get(1).isSelected()) {
                continue;
            }
            itemCardFilteredArray.add(itemCardArray.get(i));
        }
    }

    //Update save file array
    public static void getSaveFiles() {
        try {
            saveFiles.clear();
            File[] files = new File(executableDirectory, "saves").listFiles();
            if (files.length == 0) {
                File newSave = new File(executableDirectory.getPath() + "/saves", "new_save1.xml");
                Files.write(newSave.toPath(), "<items></items>".getBytes());
                NodeList profileNode = settingsDocument.getDocumentElement().getElementsByTagName("profile");
                Element profileSettingElement = (Element) profileNode.item(0);
                profileSettingElement.getElementsByTagName("name").item(0).setTextContent("new_save1");
                writeToXml(settingsDocument, settingsXML);
                files = new File(executableDirectory, "saves").listFiles();
            }
            for (File file : files) {
                saveFiles.add(file.getName().split("\\.")[0]);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    //Update choices in the combobox dropdown
    public static void updateComboBox() {
        profileCombobox.getItems().clear();
        getSaveFiles();
        for (String file : saveFiles) {
            profileCombobox.getItems().add(file);
        }
    }

    //Update profile info
    public static void updateProfileInfo(String newProfile) {
        try {
            loadedProfile = newProfile;
            profileSettingElement.getElementsByTagName("name").item(0).setTextContent(loadedProfile);
            writeToXml(settingsDocument, settingsXML);
            updateComboBox();
            profileDisplayButton.setText(loadedProfile);
            saveXML = new File(executableDirectory + "/saves", loadedProfile +".xml");
            saveDocument = builder.parse(saveXML);
            saveNode = saveDocument.getDocumentElement();
            fullReset();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    //Build all item cards into an array
    public static void buildAllItemCards() throws Exception {
        resetCounters();
        File[] files = new File(executableDirectory, "items").listFiles();
        for (File file : files) {
            if (file.isFile()) {
                Document document = builder.parse(file);
                NodeList nodes = document.getDocumentElement().getElementsByTagName("item");
                totalNodes += nodes.getLength();
                for (int i = 0; i < nodes.getLength(); i++) {
                    Element itemNode = (Element) nodes.item(i);
                        String name = itemNode.getElementsByTagName("name").item(0).getTextContent();
                        String type = itemNode.getElementsByTagName("type").item(0).getTextContent();
                        String game = itemNode.getElementsByTagName("game").item(0).getTextContent();
                        String rarity = itemNode.getElementsByTagName("rarity").item(0).getTextContent();
                        String source;
                        String location;
                        String chance;
                        if (settingsToggleButtonArray.get(0).isSelected()) {
                            source = itemNode.getElementsByTagName("source_hunt").item(0).getTextContent();
                            location = itemNode.getElementsByTagName("location_hunt").item(0).getTextContent();
                            chance = itemNode.getElementsByTagName("chance_hunt").item(0).getTextContent();
                        } else {
                            source = itemNode.getElementsByTagName("source").item(0).getTextContent();
                            location = itemNode.getElementsByTagName("location").item(0).getTextContent();
                            chance = itemNode.getElementsByTagName("chance").item(0).getTextContent();
                        }
                        String text = itemNode.getElementsByTagName("text").item(0).getTextContent();
                        String wiki = itemNode.getElementsByTagName("wiki").item(0).getTextContent();
                        String lootlemon = itemNode.getElementsByTagName("lootlemon").item(0).getTextContent();
                        String points = itemNode.getElementsByTagName("points").item(0).getTextContent();
                        String obtainedText = "false";
                        NodeList saveNodes = saveNode.getElementsByTagName("item");
                        for (int j = 0; j < saveNodes.getLength(); j++) {
                            Element node = (Element) saveNodes.item(j);
                            String nameNode = node.getElementsByTagName("name").item(0).getTextContent();
                            String typeNode = node.getElementsByTagName("type").item(0).getTextContent();
                            String rarityNode = node.getElementsByTagName("rarity").item(0).getTextContent();
                            String gameNode = node.getElementsByTagName("game").item(0).getTextContent();
                            if (name.equals(nameNode) && type.equals(typeNode) && rarity.equals(rarityNode) && 
                            gameNode.equals(game)) {
                                obtainedText = "true";
                                break;
                            }
                        }
                        Boolean obtained = Boolean.parseBoolean(obtainedText);
                    new Thread(() -> {
                        buildItemCard(name, type, game, obtained, rarity, source, text, wiki, points, location, chance, lootlemon);
                    }).start();
                }
            }
        }
        while (itemCardArray.size() != totalNodes) {
            System.out.println(itemCardArray.size() + " of " + totalNodes + "; " + itemCardArray.get(itemCardArray.size()-1).getAccessibleText().split("#%")[0]);
        }
        Collections.sort(itemCardArray, new Comparator<Pane>() {
            public int compare(Pane p1, Pane p2) {
                return p1.getAccessibleText().compareTo(p2.getAccessibleText());
            }
        });
    }

    //Build an item card
    public static void buildItemCard(String name, String type, String game, Boolean obtained, String rarity, 
    String source, String text, String wiki, String points, String location, String chance, String lootlemon) {
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
        //lock the shared variables so only 1 thread can access them at a time
        if (!(chance.equals("unobtainable") && settingsToggleButtonArray.get(12).isSelected())) {
            if (!chance.toLowerCase().contains("phosphene") && settingsToggleButtonArray.get(1).isSelected()) {
            } else {
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
            }
        }
        Pane obtainedPane = new Pane();
        if (obtained) {
            obtainedPane.setBackground(new Background(new BackgroundImage(obtainedImage, null, null, null, null)));
            //lock the shared variables so only 1 thread can access them at a time
            if (!(chance.toLowerCase().equals("unobtainable") && settingsToggleButtonArray.get(12).isSelected())) {
                if (!chance.toLowerCase().contains("phosphene") && settingsToggleButtonArray.get(1).isSelected()) {
                } else {
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
                }
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
            new Thread(() -> {
                Boolean obtainedSwitch = false;
                NodeList saveNodes = saveNode.getElementsByTagName("item");
                for (int j = 0; j < saveNodes.getLength(); j++) {
                    Element node = (Element) saveNodes.item(j);
                    String nameNode = node.getElementsByTagName("name").item(0).getTextContent();
                    String typeNode = node.getElementsByTagName("type").item(0).getTextContent();
                    String rarityNode = node.getElementsByTagName("rarity").item(0).getTextContent();
                    String gameNode = node.getElementsByTagName("game").item(0).getTextContent();
                    if (name.equals(nameNode) && type.equals(typeNode) && rarity.equals(rarityNode) && gameNode.equals(game)) {
                        Platform.runLater(() -> {
                            obtainedPane.setBackground(new Background(new BackgroundImage(notObtainedImage, null, null, null, null)));
                        });
                        obtainedSwitch = true;
                        itemPane.setAccessibleText(name + "#%" + type + "#%" + game + "#%false#%" + rarity + "#%" + source + "#%" + points + "#%" + chance);
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
                        saveNode.removeChild(node);
                        writeToXml(saveDocument, new File(executableDirectory + "/saves", loadedProfile + ".xml"));
                        break;
                    } 
                }
                if (!obtainedSwitch) {
                    Platform.runLater(() -> {
                        obtainedPane.setBackground(new Background(new BackgroundImage(obtainedImage, null, null, null, null)));
                    });
                    itemPane.setAccessibleText(name + "#%" + type + "#%" + game + "#%true#%" + rarity + "#%" + source + "#%" + points + "#%" + chance);
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
                    Element newItemElement = saveDocument.createElement("item");
                    Element newNameElement = saveDocument.createElement("name");
                    newNameElement.appendChild(saveDocument.createTextNode(name));
                    Element newTypeElement = saveDocument.createElement("type");
                    newTypeElement.appendChild(saveDocument.createTextNode(type));
                    Element newRarityElement = saveDocument.createElement("rarity");
                    newRarityElement.appendChild(saveDocument.createTextNode(rarity));
                    Element newGameElement = saveDocument.createElement("game");
                    newGameElement.appendChild(saveDocument.createTextNode(game));
                    newItemElement.appendChild(newNameElement);
                    newItemElement.appendChild(newTypeElement);
                    newItemElement.appendChild(newRarityElement);
                    newItemElement.appendChild(newGameElement);
                    saveDocument.getDocumentElement().appendChild(newItemElement);
                    writeToXml(saveDocument, new File(executableDirectory + "/saves", loadedProfile + ".xml"));
                }
                Platform.runLater(() -> {
                    resetDisplayedCards(searchTextField.getText());
                    updateBannerLabels();
                });
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
        itemTextVBox.setPadding(new Insets(0, 0, 0, 10));
        String[] sourceTextSplit = source.split("_");
        String[] locationTextSplit = location.split("_");
        String[] chanceTextSplit = chance.split("_");
        for (int i = 0; i < sourceTextSplit.length; i++) {
            Label tempSourceTextLabel = new Label();
            tempSourceTextLabel.setId("sourcesListLabel");
            tempSourceTextLabel.setText("\u2022 " + sourceTextSplit[i]);
            itemTextVBox.getChildren().addAll(tempSourceTextLabel);
            if (!location.isEmpty()) {
                if (!locationTextSplit[i].isEmpty()) {
                    Tooltip tempSourceTextToolTip = new Tooltip();
                    try {
                        String[] chanceTextSplitSplit = chanceTextSplit[i].split("#@");
                        String sourceString = locationTextSplit[i];
                        for (String string : chanceTextSplitSplit) {
                            sourceString = sourceString + "\n" + string;
                        }
                        tempSourceTextToolTip.setText(sourceString);
                    } catch (Exception e) {
                        tempSourceTextToolTip.setText(locationTextSplit[i]);
                    }
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
        //Lootlemon button
        ImageView lootlemonLinkImageView = new ImageView(miniLootlemonImage);
        lootlemonLinkImageView.setFitHeight(30);
        lootlemonLinkImageView.setFitWidth(17);
        Pane lootlemonLinkPane = new Pane(lootlemonLinkImageView);
        lootlemonLinkPane.setId("lootlemonLinkPane");
        Tooltip lootlemonLinkPaneToolTip = new Tooltip(lootlemon);
        lootlemonLinkPaneToolTip.setId("toolTip");
        lootlemonLinkPane.setOnMouseMoved(event -> {
            lootlemonLinkPaneToolTip.show(lootlemonLinkPane, event.getScreenX() + 10, event.getScreenY() + 20);
        });
        lootlemonLinkPane.setOnMouseExited(event -> {
            lootlemonLinkPaneToolTip.hide();
        });
        lootlemonLinkPane.setOnMouseClicked(event -> {
            hostService.showDocument(lootlemon);
        });

        StackPane.setMargin(itemWikiLinkPane, new Insets(130, 200, 0, -50));
        StackPane.setMargin(lootlemonLinkPane, new Insets(130, 165, 0, 0));
        itemImageStackPane.getChildren().addAll(itemWikiLinkPane, lootlemonLinkPane);

        ScrollPane itemTextScrollPane = new ScrollPane(itemTextVBox);
        itemTextScrollPane.setId("itemTextScrollPane");
        
        VBox itemVBox = new VBox(topHBox, itemNameLabel, itemImageStackPane, itemTextScrollPane);
        itemVBox.setId("itemVBox");
        VBox.setMargin(itemNameLabel, new Insets(5, 0 ,0, 0));
        VBox.setMargin(itemImageStackPane, new Insets(1, 0 ,0, 0));
        itemPane.getChildren().add(itemVBox);
        itemPane.setId("itemPane");
        itemPane.setCache(true);
        itemPane.setCacheHint(CacheHint.SPEED);
        itemPane.setVisible(true);
        itemPane.setAccessibleText(name + "#%" + type + "#%" + game + "#%" + obtained + "#%" + rarity + "#%" + source + "#%" + points + "#%" + chance);
        //Lock the Array as multithreading can cause cards to be lost if multiple threads try to add a card at the same time
        lock.lock();
        try {
            itemCardArray.add(itemPane);
        } finally {
            lock.unlock();
        }
    }
}
