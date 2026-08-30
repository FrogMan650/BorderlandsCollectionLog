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
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalTime;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application {
    //Item spreadsheet: https://docs.google.com/spreadsheets/d/1W7sitzbmaNyniM4CwMG44fDkvS6bWYgrKa3ZrBdT-Nk/edit?usp=sharing
    public static Image icon;
    public static Image wikiImage;
    public static Image wikiMiniImage;
    public static Image miniLootlemonImage;
    public static Image lootlemonImage;
    public static Image mentalMarsImage;
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
    public static Image phospheneImage;
    public static Image worldDropImage;
    public static Image mayhemImage;
    public static Image dlcImage;
    public static Image grinderImage;
    public static Image seraphImage;
    public static Image torgueImage;
    public static Image earlImage;
    public static Image gameModeImage;
    public static Image missionImage;
    public static Image itemPickerImage;
    public static Image cardImage;
    public static Image modImage;

    public static FlowPane itemFlowPane;
    public static ScrollPane itemScrollPane;
    public static TextField searchTextField;
    public static Document settingsDocument;
    public static Document profileDocument;
    public static Element profileNode;
    public static NodeList filterNodes;
    public static NodeList settingsNodes;
    public static ArrayList<ItemCard> itemCardArray = new ArrayList<>();
    public static ArrayList<ItemCard> itemCardFilteredArray = new ArrayList<>();
    public static ArrayList<ToggleButton> toggleButtonArray = new ArrayList<>();
    public static ArrayList<ToggleButton> settingsToggleButtonArray = new ArrayList<>();
    public static File itemsXML;
    public static File settingsXML;
    public static File profileXML;
    public static File logFile;
    public static HostServices hostService;
    public static Lock lock = new ReentrantLock();
    public static ArrayList<String> profiles = new ArrayList<>();
    public static String loadedProfile;
    public static ComboBox<String> profileCombobox;
    public static ComboBox<String> bannerProfileCombobox;
    public static Element profileSettingElement;
    public static Button profileDisplayButton;
    public static HBox itemPickerHBox;
    public static VBox statsVBox;

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
    public static File userDataDirectory;
    public static File itemsDirectory;
    public static int totalNodes = 0;

    public static Boolean linux = false;
    public static Boolean windows = false;
    public static Boolean mac = false;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //Define global variables
        Font willowBody = Font.loadFont(getClass().getResourceAsStream("WillowBody-Regular.ttf"), 10);
        icon = new Image(getClass().getResourceAsStream("BLCL_logo_mini.png"));
        wikiImage = new Image(getClass().getResourceAsStream("Wiki_logo.png"));
        wikiMiniImage = new Image(getClass().getResourceAsStream("Wiki_logo_mini.png"));
        miniLootlemonImage = new Image(getClass().getResourceAsStream("lootlemon_mini.png"));
        lootlemonImage = new Image(getClass().getResourceAsStream("lootlemon_cropped.png"));
        mentalMarsImage = new Image(getClass().getResourceAsStream("mentalmars_logo.png"));
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
        phospheneImage = new Image(getClass().getResourceAsStream("phosphene_indicator.png"));
        worldDropImage = new Image(getClass().getResourceAsStream("world_drop.png"));
        mayhemImage = new Image(getClass().getResourceAsStream("mayhem_indicator.png"));
        grinderImage = new Image(getClass().getResourceAsStream("grinder_indicator.png"));
        earlImage = new Image(getClass().getResourceAsStream("earl_indicator.png"));
        seraphImage = new Image(getClass().getResourceAsStream("seraph_indicator.png"));
        torgueImage = new Image(getClass().getResourceAsStream("torgue_indicator.png"));
        gameModeImage = new Image(getClass().getResourceAsStream("game_mode_indicator.png"));
        missionImage = new Image(getClass().getResourceAsStream("mission_indicator.png"));
        dlcImage = new Image(getClass().getResourceAsStream("dlc_indicator.png"));
        cardImage = new Image(getClass().getResourceAsStream("vault_card_indicator.png"));
        modImage = new Image(getClass().getResourceAsStream("mod_indicator.png"));
        itemPickerImage = new Image(getClass().getResourceAsStream("item_picker.png"));
        hostService = getHostServices();
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();

        //======================================
        //    Directory and File Setup Start
        //======================================
        //Determine which operating system is being used
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            windows = true;
        } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            linux = true;
        } else if (os.contains("mac")) {
            mac = true;
        }
        //Get/create the users BorderlandsCollectionLog directory
        //Windows: /Users/User/AppData/Local/BorderlandsCollectionLog
        //Linux: /home/User/.local/share/BorderlandsCollectionLog
        String userDataString = "";
        Path userDataPath = null;
        try {
            if (windows) {
                userDataString = System.getenv("LOCALAPPDATA");
                userDataPath = Paths.get(userDataString, "BorderlandsCollectionLog");
            } else if (linux) {
                userDataString = System.getProperty("user.home");
                userDataPath = Paths.get(userDataString, ".local", "share", "BorderlandsCollectionLog");
            } else if (mac) {
                System.out.println("Maybe some day...");
            }
        } catch (Exception e) {
            System.out.println("Couldn't find User data directory\n" + e);
        }
        userDataDirectory = userDataPath.toFile();
        if (!userDataDirectory.exists()) {
            try {
                Files.createDirectory(userDataDirectory.toPath());
            } catch (Exception e) {
                System.out.println("Error creating user data BorderlandsCollectionLog directory\n" + e);
            }
        }
        //Get executable directory
        URI uri = getClass().getProtectionDomain().getCodeSource().getLocation().toURI();
        executableDirectory = Paths.get(uri).getParent().toFile();
        //Get items directory
        if (windows) {
            itemsDirectory = new File(executableDirectory, "items");
        } else if (linux) {
            if (executableDirectory.toString().toLowerCase().contains("tmp")) {
                itemsDirectory = new File(executableDirectory.getParent(), "lib/items");
            } else {
                itemsDirectory = new File(executableDirectory, "items");
            }
        }
        //Get/create settings file
        settingsXML = new File(userDataDirectory, "settings.xml");
        if (!settingsXML.exists()) {
            try {
                settingsXML.createNewFile();
                Files.write(settingsXML.toPath(), "<settings></settings>".getBytes());
            } catch (Exception e) {
                System.out.println("Error creating settings.xml file:\n" + e);
            }
        }
        settingsDocument = builder.parse(settingsXML);
        settingsNodes = settingsDocument.getDocumentElement().getElementsByTagName("setting");
        filterNodes = settingsDocument.getDocumentElement().getElementsByTagName("filter");
        //Get/create log file
        logFile = new File(userDataDirectory, "logs.txt");
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (Exception e) {
                System.out.println("Error creating logs.txt file:\n" + e);
            }
        }
        //Get/create profile directory and files
        File profileDirectory = new File(userDataDirectory, "profiles");
        if (!profileDirectory.exists()) {
            try {
                Files.createDirectory(profileDirectory.toPath());
            } catch (Exception e) {
                System.out.println("Error creating profiles folder:\n" + e);
            }
        }
        getProfiles();
        NodeList profileNodes = settingsDocument.getDocumentElement().getElementsByTagName("profile");
        profileSettingElement = (Element) profileNodes.item(0);
        if (profileSettingElement == null) {
                Element root = settingsDocument.getDocumentElement();
                Element newProfileElement = settingsDocument.createElement("profile");
                Element profileNameElement = settingsDocument.createElement("name");
                profileNameElement.appendChild(settingsDocument.createTextNode(profiles.get(0)));
                newProfileElement.appendChild(profileNameElement);
                root.appendChild(newProfileElement);
                writeToXml(settingsDocument, settingsXML);
                profileNodes = settingsDocument.getDocumentElement().getElementsByTagName("profile");
                profileSettingElement = (Element) profileNodes.item(0);
            }
        loadedProfile = profileSettingElement.getElementsByTagName("name").item(0).getTextContent();
        if (!profiles.contains(loadedProfile)) {
            loadedProfile = profiles.get(0);
            profileSettingElement.getElementsByTagName("name").item(0).setTextContent(loadedProfile);
            writeToXml(settingsDocument, settingsXML);
        }
        profileXML = new File(userDataDirectory + "/profiles", loadedProfile +".xml");
        profileDocument = builder.parse(profileXML);
        profileNode = profileDocument.getDocumentElement();
        //======================================
        //    Directory and File Setup End
        //======================================

        //Item cards holder
        itemFlowPane = new FlowPane();
        itemFlowPane.setId("itemFlowPane");
        itemFlowPane.setPadding(new Insets(0));

        itemScrollPane = new ScrollPane(itemFlowPane);
        itemScrollPane.setId("itemScrollPane");

        //======================================
        //          Filters Start
        //======================================
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
        ToggleButton DLCToggleButton = new ToggleButton("DLC");//30
        toggleButtonArray.add(DLCToggleButton);
        ToggleButton worldDropToggleButton = new ToggleButton("World Drop");//31
        toggleButtonArray.add(worldDropToggleButton);
        ToggleButton nonWorldDropToggleButton = new ToggleButton("Mission Reward");//32
        toggleButtonArray.add(nonWorldDropToggleButton);
        ToggleButton nonHuntToggleButton = new ToggleButton("Vault Card");//33
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
        filterVBox.getChildren().addAll(miscLabel, obtainedToggleButton, notObtainedToggleButton, DLCToggleButton, 
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
        //======================================
        //          Filters End
        //======================================

        //======================================
        //          Settings Start
        //======================================
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
        profileDisplayButton.setMnemonicParsing(false);
        profileDisplayButton.setId("profileDisplayButton");
        addToolTip(profileDisplayButton, loadedProfile);
        profileCombobox = new ComboBox<>();
        profileCombobox.setEditable(true);
        profileCombobox.setPromptText("Profile Name");
        Button loadProfileButton = new Button("Load Profile");
        loadProfileButton.setId("greenButton");
        loadProfileButton.setOnAction(event -> {
            try {
                String newProfile = profileCombobox.getValue();
                File profileToLoad = new File(userDataDirectory.getPath() + "/profiles", newProfile + ".xml");
                if (profileToLoad.exists()) {
                    updateProfileInfo(newProfile);
                }
            } catch (Exception e) {
                writeToLogFile("Error loading profile", e.toString());
            }
        });
        addToolTip(loadProfileButton, "Load selected profile");
        Button createNewProfileButton = new Button("Create Profile");
        createNewProfileButton.setId("greenButton");
        createNewProfileButton.setOnAction(event -> {
            try {
                String newProfile = profileCombobox.getValue();
                File newProfileFile = new File(userDataDirectory.getPath() + "/profiles", newProfile + ".xml");
                if (!newProfileFile.exists()) {
                    Files.write(newProfileFile.toPath(), "<items></items>".getBytes());
                    updateProfileInfo(newProfile);
                }
            } catch (Exception e) {
                writeToLogFile("Error creating profile", e.toString());
            }
        });
        addToolTip(createNewProfileButton, "Create selected profile");
        Button renameProfileButton = new Button("Rename Profile");
        renameProfileButton.setId("greenButton");
        renameProfileButton.setOnAction(event -> {
            try {
                String newProfile = profileCombobox.getValue();
                File oldProfileFile = new File(userDataDirectory.getPath() + "/profiles", loadedProfile + ".xml");
                File newProfileFile = new File(userDataDirectory.getPath() + "/profiles", newProfile + ".xml");
                if (!newProfileFile.exists()) {
                    Files.move(oldProfileFile.toPath(), newProfileFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    updateProfileInfo(newProfile);
                }
            } catch (Exception e) {
                writeToLogFile("Error renaming profile", e.toString());
            }
        });
        addToolTip(renameProfileButton, "Rename selected profile");
        Button resetProfileButton = new Button("Reset Profile");
        resetProfileButton.setId("redButton");
        resetProfileButton.setOnAction(event -> {
            try {
                String newProfile = profileCombobox.getValue();
                File selectedProfile = new File(userDataDirectory.getPath() + "/profiles", newProfile + ".xml");
                if (resetProfileButton.getText().equals("Reset Profile")) {
                    resetProfileButton.setText("REALLY?");
                } else if (resetProfileButton.getText().equals("REALLY?")) {
                    resetProfileButton.setText("REALLLLY???!??");
                } else if (resetProfileButton.getText().equals("REALLLLY???!??")) {
                    resetProfileButton.setText("Reset Profile");
                    if (selectedProfile.exists()) {
                        Document selectedProfileDocument = builder.parse(selectedProfile);
                        Element selectedProfileNode = selectedProfileDocument.getDocumentElement();
                        NodeList childNodes = selectedProfileNode.getChildNodes();
                        for (int i = childNodes.getLength()-1; i >= 0; i--) {
                            selectedProfileNode.removeChild(childNodes.item(i));
                        }
                        writeToXml(selectedProfileDocument, selectedProfile);
                        updateProfileInfo(loadedProfile);
                    }
                }
            } catch (Exception e) {
                writeToLogFile("Error resetting profile", e.toString());
            }
        });
        addToolTip(resetProfileButton, "Reset selected profile");
        Button deleteProfileButton = new Button("Delete Profile");
        deleteProfileButton.setId("redButton");
        deleteProfileButton.setOnAction(event -> {
            try {
                String newProfile = profileCombobox.getValue();
                File selectedProfile = new File(userDataDirectory.getPath() + "/profiles", newProfile + ".xml");
                File currentProfile = new File(userDataDirectory.getPath() + "/profiles", loadedProfile + ".xml");
                if (deleteProfileButton.getText().equals("Delete Profile")) {
                    deleteProfileButton.setText("REALLY?");
                } else if (deleteProfileButton.getText().equals("REALLY?")) {
                    deleteProfileButton.setText("REALLLLY???!??");
                } else if (deleteProfileButton.getText().equals("REALLLLY???!??")) {
                    deleteProfileButton.setText("Delete Profile");
                    if (selectedProfile.exists()) {
                        Files.delete(selectedProfile.toPath());
                        getProfiles();
                        if (selectedProfile.toPath().equals(currentProfile.toPath())) {
                            loadedProfile = profiles.get(0);
                        }
                        updateProfileInfo(loadedProfile); 
                    }
                }
            } catch (Exception e) {
                writeToLogFile("Error deleting profile", e.toString());
            }
        });
        addToolTip(deleteProfileButton, "Delete selected profile");
        settingsVBox.getChildren().addAll(profileSelectionLabel, profileDisplayButton, profileCombobox, 
            loadProfileButton,createNewProfileButton, renameProfileButton, resetProfileButton, deleteProfileButton);

        Label modeSelectionLabel = new Label("MODE");
        modeSelectionLabel.setId("filterLabel");
        modeSelectionLabel.setStyle("-fx-cursor: none;");
        ToggleButton huntModeToggleButton = new ToggleButton("Hunt Mode");//0
        settingsToggleButtonArray.add(huntModeToggleButton);
        addToolTip(huntModeToggleButton, "Hunt Mode will only show\nitems worth Hunt points." +
        "\nMore information on rules and regulations for\n The Hunt can be found on borderlandshunt.com.");
        ToggleButton phospheneModeToggleButton = new ToggleButton("Phosphene Mode");//1
        settingsToggleButtonArray.add(phospheneModeToggleButton);
        addToolTip(phospheneModeToggleButton, "Phosphene Mode will only show items\n" +
        "with a phosphene skin variant.");
        settingsVBox.getChildren().addAll(modeSelectionLabel, huntModeToggleButton, phospheneModeToggleButton);
        Label itemCollectionLabel = new Label("COLLECTION");
        itemCollectionLabel.setId("filterLabel");
        addToolTip(itemCollectionLabel, "These settings control which games items\nwill " + 
        "contribute to the items collected\non the banner at the top.");
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
        addToolTip(theHuntLabel, "These settings control which games items\nwill " +
        "contribute to the hunt point\ntotal on the banner at the top.");
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
        addToolTip(toggleButtonHideUnobtainable, "This setting will hide items that are only\nobtainable " +
        "through promotional DLC, limited time\npromotions, or are just in general not obtainable\nby any normal or " +
        "legitimate means.\n Example: Contraband Sky Rocket from BL2");
        toggleButtonHideUnobtainable.setOnAction(event -> {
            Element settingElement = (Element) settingsNodes.item(12);
            if (toggleButtonHideUnobtainable.isSelected()) {
                settingElement.getElementsByTagName("enabled").item(0).setTextContent("true");
            } else {
                settingElement.getElementsByTagName("enabled").item(0).setTextContent("false");
            }
            fullReset();
            new Thread(() -> {
                writeToXml(settingsDocument, settingsXML);
            }).start();
        });
        ToggleButton toggleButtonAppStats = new ToggleButton("App Stats");//13
        settingsToggleButtonArray.add(toggleButtonAppStats);
        addToolTip(toggleButtonAppStats, "Show FPS and RAM usage");
        toggleButtonAppStats.setOnAction(event -> {
            Element settingElement = (Element) settingsNodes.item(13);
            if (toggleButtonAppStats.isSelected()) {
                settingElement.getElementsByTagName("enabled").item(0).setTextContent("true");
                statsVBox.setVisible(true);
            } else {
                settingElement.getElementsByTagName("enabled").item(0).setTextContent("false");
                statsVBox.setVisible(false);
            }
            fullReset();
            new Thread(() -> {
                writeToXml(settingsDocument, settingsXML);
            }).start();
        });
        settingsVBox.getChildren().addAll(miscSettingLabel, toggleButtonHideUnobtainable, toggleButtonAppStats);
        for (int i = 1; i < 12; i++) {
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
            itemScrollPane.setVvalue(0);
            fullReset();
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
            itemScrollPane.setVvalue(0);
            fullReset();
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
            if (filterElement == null) {
                Element root = settingsDocument.getDocumentElement();
                Element newFilterElement = settingsDocument.createElement("filter");
                Element filterNameElement = settingsDocument.createElement("name");
                filterNameElement.appendChild(settingsDocument.createTextNode(toggleButtonArray.get(i).getText()));
                Element filterEnabledElement = settingsDocument.createElement("enabled");
                filterEnabledElement.appendChild(settingsDocument.createTextNode("true"));
                newFilterElement.appendChild(filterNameElement);
                newFilterElement.appendChild(filterEnabledElement);
                root.appendChild(newFilterElement);
                writeToXml(settingsDocument, settingsXML);
                filterNodes = settingsDocument.getDocumentElement().getElementsByTagName("filter");
                filterElement = (Element) filterNodes.item(i);
            }
            Boolean elementText = Boolean.parseBoolean(filterElement.getElementsByTagName("enabled").item(0).getTextContent());
            toggleButtonArray.get(i).setSelected(elementText);
        }
        for (int i = 0; i < settingsToggleButtonArray.size(); i++) {
            Element settingElement = (Element) settingsNodes.item(i);
            if (settingElement == null) {
                Element root = settingsDocument.getDocumentElement();
                Element newSettingElement = settingsDocument.createElement("setting");
                Element settingNameElement = settingsDocument.createElement("name");
                String nameNodeText = settingsToggleButtonArray.get(i).getText();
                settingNameElement.appendChild(settingsDocument.createTextNode(nameNodeText));
                Element settingEnabledElement = settingsDocument.createElement("enabled");
                if (nameNodeText.equals("Hunt Mode") || nameNodeText.equals("Phosphene Mode") || nameNodeText.equals("App stats")) {
                    settingEnabledElement.appendChild(settingsDocument.createTextNode("false"));
                } else {
                    settingEnabledElement.appendChild(settingsDocument.createTextNode("true"));
                }
                newSettingElement.appendChild(settingNameElement);
                newSettingElement.appendChild(settingEnabledElement);
                root.appendChild(newSettingElement);
                writeToXml(settingsDocument, settingsXML);
                settingsNodes = settingsDocument.getDocumentElement().getElementsByTagName("setting");
                settingElement = (Element) settingsNodes.item(i);
            }
            Boolean elementText = Boolean.parseBoolean(settingElement.getElementsByTagName("enabled").item(0).getTextContent());
            settingsToggleButtonArray.get(i).setSelected(elementText);
        }
        //======================================
        //          Settings End
        //======================================


        //======================================
        //          Banner Start
        //======================================
        //Profile selector
        bannerProfileCombobox = new ComboBox<>();
        bannerProfileCombobox.setEditable(false);
        bannerProfileCombobox.setId("bannerProfileCombobox");
        addToolTip(bannerProfileCombobox, "Loaded profile\n" + loadedProfile);
        //Wiki image with link
        ImageView wikiLinkImageView = new ImageView(wikiImage);
        wikiLinkImageView.setFitHeight(48);
        Pane wikiViewPane = new Pane(wikiLinkImageView);
        wikiViewPane.setStyle("-fx-cursor: hand;");
        addToolTip(wikiViewPane, "Borderlands Wiki\nGreat resource for additional information on\n" +
            "items, drop sources, and all things Borderlands.");
        wikiViewPane.setOnMouseClicked(event -> {
            hostService.showDocument("https://borderlands.fandom.com/wiki/Borderlands_Wiki");
        });
        //Lootlemon image with link
        ImageView lootlemonImageView = new ImageView(lootlemonImage);
        lootlemonImageView.setFitHeight(48);
        lootlemonImageView.setFitWidth(26);
        Pane lootlemonViewPane = new Pane(lootlemonImageView);
        lootlemonViewPane.setStyle("-fx-cursor: hand;");
        addToolTip(lootlemonViewPane, "LootLemon\n#1 best resource for information on all Borderlands\n" +
            "items, drop sources, drop rates, and skill tree builders.");
        lootlemonViewPane.setOnMouseClicked(event -> {
            hostService.showDocument("https://www.lootlemon.com/");
        });
        //MentalMars image with link
        ImageView mentalMarsImageView = new ImageView(mentalMarsImage);
        mentalMarsImageView.setFitHeight(48);
        mentalMarsImageView.setFitWidth(48);
        Pane mentalMarsViewPane = new Pane(mentalMarsImageView);
        mentalMarsViewPane.setStyle("-fx-cursor: hand;");
        addToolTip(mentalMarsViewPane, "MentalMars\nOne of the best resources for Borderlands news,\n" +
            "walkthroughs, Golden keys, and SHiFT codes in general.");
        mentalMarsViewPane.setOnMouseClicked(event -> {
            hostService.showDocument("https://mentalmars.com/");
        });
        //Item picker
        VBox itemPickerVBox = new VBox();
        itemPickerVBox.setId("itemPickerVBox");
        itemPickerVBox.setSpacing(10);
        itemPickerHBox = new HBox();
        Button rerollItemPickerButton = new Button("Reroll");
        rerollItemPickerButton.setId("rerollItemPickerButton");
        itemPickerVBox.getChildren().addAll(rerollItemPickerButton, itemPickerHBox);
        FlowPane itemPickerFlowPane = new FlowPane(itemPickerVBox);
        itemPickerFlowPane.setId("itemPickerFlowPane");
        ImageView itemPickerImageView = new ImageView(itemPickerImage);
        Pane itemPickerViewPane = new Pane(itemPickerImageView);
        itemPickerViewPane.setStyle("-fx-cursor: hand;");
        addToolTip(itemPickerViewPane, "Not sure which item to go for next?\nLet the item picker decide!");
        itemPickerViewPane.setOnMouseClicked(event -> {
            if (itemCardFilteredArray.size() > 0) {
                itemPickerHBox.getChildren().clear();
                if (itemFlowPane.isVisible()) {
                    itemFlowPane.setVisible(false);
                    itemPickerFlowPane.setVisible(true);
                    itemScrollPane.setContent(itemPickerFlowPane);
                } else {
                    itemPickerFlowPane.setVisible(false);
                    itemFlowPane.setVisible(true);
                    itemScrollPane.setContent(itemFlowPane);
                }
            }
        });
        rerollItemPickerButton.setOnAction(event -> {
            if (itemCardFilteredArray.size() > 0) {
                itemPickerHBox.getChildren().clear();
                int cardToShow = 0;
                if (itemCardFilteredArray.size() != 1) {
                    cardToShow = (int) (itemCardFilteredArray.size()*Math.random());
                }
                itemPickerHBox.getChildren().add(itemCardFilteredArray.get(cardToShow).getItemCard());
            }
        });
        //BLCL item collection
        ImageView BLCLImageView = new ImageView(icon);
        BLCLImageView.setFitHeight(48);
        BLCLImageView.setFitWidth(48);
        Pane BLCLViewPane = new Pane(BLCLImageView);
        BLCLViewPane.setStyle("-fx-cursor: hand;");
        addToolTip(BLCLViewPane, "https://github.com/FrogMan650/BorderlandsCollectionLog");
        BLCLViewPane.setOnMouseClicked(event -> {
            hostService.showDocument("https://github.com/FrogMan650/BorderlandsCollectionLog");
        });
        itemsCollectedLabel = new Label();
        itemsCollectedLabel.setId("collectionLabel");
        addToolTip(itemsCollectedLabel, "Items obtained");
        Line itemsCollectedLine = new Line();
        itemsCollectedLine.setId("collectionLine");
        itemsCollectedLine.setStartX(0);
        itemsCollectedLine.setEndX(35);
        itemsTotalLabel = new Label();
        addToolTip(itemsTotalLabel, "Total items");
        itemsTotalLabel.setId("collectionLabel");
        VBox itemsCollectedVBox = new VBox(itemsCollectedLabel, itemsCollectedLine, itemsTotalLabel);
        itemsCollectedVBox.setId("collectionVBox");
        //Hunt points collected
        ImageView huntImageView = new ImageView(theHuntImage);
        huntImageView.setFitHeight(48);
        huntImageView.setFitWidth(87);
        Pane huntViewPane = new Pane(huntImageView);
        huntViewPane.setStyle("-fx-cursor: hand;");
        addToolTip(huntViewPane, "The Hunt is a Borderlands community\n" +
        "scavenger hunt to raise money for\nSt. Jude Children's Research Hospital.\nClick here for more info");
        huntViewPane.setOnMouseClicked(event -> {
            hostService.showDocument("https://mentalmars.com/the-hunt/");
        });
        huntItemsCollectedLabel = new Label();
        huntItemsCollectedLabel.setId("collectionLabel");
        addToolTip(huntItemsCollectedLabel, "Hunt points obtained");
        Line huntItemsCollectedLine = new Line();
        huntItemsCollectedLine.setId("collectionLine");
        huntItemsCollectedLine.setStartX(0);
        huntItemsCollectedLine.setEndX(35);
        huntItemsTotalLabel = new Label();
        huntItemsTotalLabel.setId("collectionLabel");
        addToolTip(huntItemsTotalLabel, "Total hunt points");
        VBox huntItemsCollectedVBox = new VBox(huntItemsCollectedLabel, huntItemsCollectedLine, huntItemsTotalLabel);
        huntItemsCollectedVBox.setId("collectionVBox");

        Region bannerHPusher = new Region();
        HBox.setHgrow(bannerHPusher, Priority.ALWAYS);
        Region bannerHPusher2 = new Region();
        HBox.setHgrow(bannerHPusher2, Priority.ALWAYS);

        //Settings
        Pane settingsViewPane = new Pane();
        settingsViewPane.setId("settingsViewPane");
        addToolTip(settingsViewPane, "Settings");
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

        //Application stats
        Label fpsLabel = new Label("FPS: Calculating...");
        fpsLabel.setId("statsLabel");
        Label memoryLabel = new Label("RAM: Calculating...");
        memoryLabel.setId("statsLabel");
        AnimationTimer fpsTimer = new AnimationTimer() {
            // FPS Tracking variables (Class scope)
            final long[] frameTimes = new long[100];
            int frameTimeIndex = 0;
            boolean arrayFilled = false;

            // Memory Tracking variables (Class scope)
            long lastMemoryUpdate = 0;
            final Runtime runtime = Runtime.getRuntime();

            @Override
            public void handle(long now) {
                long oldFrameTime = frameTimes[frameTimeIndex];
                frameTimes[frameTimeIndex] = now;
                frameTimeIndex = (frameTimeIndex + 1) % frameTimes.length;

                if (frameTimeIndex == 0) {
                    arrayFilled = true;
                }

                if (arrayFilled) {
                    // Get elapsed nanoseconds between oldest and newest frame in buffer
                    long elapsedNanos = now - oldFrameTime;
                    long elapsedNanosPerFrame = elapsedNanos / frameTimes.length;
                    
                    // Convert nanoseconds per frame to frames per second
                    double fps = 1_000_000_000.0 / elapsedNanosPerFrame;
                    
                    fpsLabel.setText(String.format("FPS: %.1f", fps));
                }

                // --- 2. CALCULATE RAM (Updated once every 1 second) ---
                // 'now' is in nanoseconds. 1 second = 1,000,000,000 nanoseconds.
                if (now - lastMemoryUpdate >= 1_000_000_000L) {
                    // Total memory currently allocated to the JVM by the OS
                    long totalMemory = runtime.totalMemory();
                    // Memory currently free within that allocated pool
                    long freeMemory = runtime.freeMemory();
                    // Actual memory actively being used by your program
                    long usedMemory = totalMemory - freeMemory;

                    // Convert bytes to Megabytes (MB)
                    double usedMegabytes = usedMemory / (1024.0 * 1024.0);
                    double totalMegabytes = totalMemory / (1024.0 * 1024.0);

                    memoryLabel.setText(String.format("RAM: %.1f MB / %.0f MB", usedMegabytes, totalMegabytes));
                    
                    lastMemoryUpdate = now;
                }
            }
        };
        // fpsTimer.start();
        fpsLabel.setText(executableDirectory.toString());
        memoryLabel.setText(itemsDirectory.toString());
        statsVBox = new VBox(fpsLabel, memoryLabel);
        statsVBox.setId("statsVBox");
        if (settingsToggleButtonArray.get(13).isSelected()) {
            statsVBox.setVisible(true);
        } else {
            statsVBox.setVisible(false);
        }

        HBox bannerHBox = new HBox(0, bannerProfileCombobox, itemPickerViewPane, statsVBox, bannerHPusher2, BLCLViewPane, itemsCollectedVBox, 
            huntViewPane, huntItemsCollectedVBox, bannerHPusher, wikiViewPane, lootlemonViewPane, mentalMarsViewPane, settingsViewPane);
        bannerHBox.setId("bannerBox");
        HBox.setMargin(statsVBox, new Insets(0, 0, 0, 10));
        HBox.setMargin(itemPickerViewPane, new Insets(0, 0, 0, 10));
        HBox.setMargin(wikiViewPane, new Insets(0, 10, 0, 0));
        HBox.setMargin(lootlemonViewPane, new Insets(0, 10, 0, 0));
        HBox.setMargin(mentalMarsViewPane, new Insets(0, 10, 0, 0));
        HBox.setMargin(BLCLViewPane, new Insets(0, 5, 0, 0));
        HBox.setMargin(itemsCollectedVBox, new Insets(0, 10, 0, 0));
        HBox.setMargin(huntViewPane, new Insets(0, 5, 0, 0));
        HBox.setMargin(huntItemsCollectedVBox, new Insets(0, 10, 0, 0));
        //======================================
        //          Banner End
        //======================================

        //======================================
        //          Final Setup Start
        //======================================
        AnchorPane root = new AnchorPane();
        root.setId("anchorPane");
        //Create the radial gradiant for the main background
        // 1. Define a low-res rendering grid (Fixed: Never changes during resize)
        int bufferSize = 200; 
        double centerX = bufferSize / 2;
        double centerY = bufferSize / 2;
        double maxRadius = Math.sqrt(centerX * centerX + centerY * centerY); // Corner distance

        // 2. Define your colors
        Color centerColor = Color.web("#5879b7");
        Color edgeColor = Color.web("#49506e");

        WritableImage radialImage = new WritableImage(bufferSize, bufferSize);
        PixelWriter writer = radialImage.getPixelWriter();

        // 3. Calculate the Circle Distance Map
        for (int x = 0; x < bufferSize; x++) {
            for (int y = 0; y < bufferSize; y++) {
                // Find distance from current pixel to center point
                double dx = x - centerX;
                double dy = y - centerY;
                double distance = Math.sqrt(dx * dx + dy * dy);
                
                // Normalize the progress value (Clamped between 0.0 and 1.0)
                double progress = Math.min(1.0, distance / maxRadius);
                
                // Linear Interpolation (lerp) math
                double r = centerColor.getRed() + progress * (edgeColor.getRed() - centerColor.getRed());
                double g = centerColor.getGreen() + progress * (edgeColor.getGreen() - centerColor.getGreen());
                double b = centerColor.getBlue() + progress * (edgeColor.getBlue() - centerColor.getBlue());
                
                writer.setColor(x, y, new Color(r, g, b, 1.0));
            }
        }

        // 4. Wrap the buffer in an ImageView and configure stretching
        ImageView backgroundView = new ImageView(radialImage);
        backgroundView.setPreserveRatio(false);
        backgroundView.fitWidthProperty().bind(root.widthProperty());   // Binds image width to AnchorPane width
        backgroundView.fitHeightProperty().bind(root.heightProperty());

        // CRITICAL: Tells the GPU to smoothly blend the stretched low-res pixels
        backgroundView.setSmooth(true); 
        backgroundView.setId("backgroundView");

        // Dynamic Layout Binding: Stretches the image automatically via AnchorPane anchors
        AnchorPane.setTopAnchor(backgroundView, 0.0);
        AnchorPane.setBottomAnchor(backgroundView, 0.0);
        AnchorPane.setLeftAnchor(backgroundView, 0.0);
        AnchorPane.setRightAnchor(backgroundView, 0.0);

        // 5. Push the background image layer to the absolute bottom of the Pane
        root.getChildren().add(0, backgroundView);

        //Create banner gradient
        // 1. Define the fixed height of your banner and the low-res horizontal buffer width
        double bannerHeight = 50.0; // Set this to whatever your fixed height requirement is
        int bufferWidth = 1;      // 200 points is more than enough for a smooth blend
        int bufferHeight = 50;       // CRITICAL: Only 1 pixel tall to optimize CPU usage

        // 2. Define your colors
        Color topColor = Color.web("#71c5d6");  // Start color (Purple)
        Color bottomColor = Color.web("#4671a9"); // End color (Pink)

        WritableImage bannerImage = new WritableImage(bufferWidth, bufferHeight);
        PixelWriter pixelWriter = bannerImage.getPixelWriter();

        // 3. Calculate the vertical gradient exactly ONCE at initialization
        for (int y = 0; y < bufferHeight; y++) {
            // Map the vertical coordinate to a fraction (0.0 to 1.0)
            double progress = (double) y / (bufferHeight - 1);
            
            // Linear Interpolation (lerp) math down the color channels
            double r = topColor.getRed() + progress * (bottomColor.getRed() - topColor.getRed());
            double g = topColor.getGreen() + progress * (bottomColor.getGreen() - topColor.getGreen());
            double b = topColor.getBlue() + progress * (bottomColor.getBlue() - topColor.getBlue());
            
            // Write the pixel to the single row (y = 0)
            pixelWriter.setColor(0, y, new Color(r, g, b, 1.0));
        }

        // 4. Wrap the buffer in an ImageView and configure stretching
        ImageView bannerView = new ImageView(bannerImage);

        bannerView.setPreserveRatio(false); // Allows independent width and height scaling
        bannerView.setSmooth(true);         // Forces the GPU to smoothly blur the 200 pixels across the screen

        // 5. Explicitly set the fixed height and bind the width dynamically to the app window
        bannerView.setFitHeight(bannerHeight);
        bannerView.fitWidthProperty().bind(root.widthProperty());

        // 6. Place it firmly at the very top of your AnchorPane layout
        AnchorPane.setTopAnchor(bannerView, 0.0);
        AnchorPane.setLeftAnchor(bannerView, 0.0);
        AnchorPane.setRightAnchor(bannerView, 0.0);

        // 7. Add the banner view layer to your layout pane
        root.getChildren().add(bannerView);




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
        stage.setMinHeight(620);
        stage.setMinWidth(880);
        stage.setTitle("Borderlands Collection Log");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
        //itemFlowPane spacing
        itemFlowPane.setPrefWidth(scene.getWidth()-206);
        itemPickerFlowPane.setPrefWidth(scene.getWidth()-206);
        itemPickerFlowPane.setPrefHeight(scene.getHeight()-50);
        int itemFlowPaneWidth = (int) itemFlowPane.getPrefWidth();
        int cardsThatFit = (int) Math.floor(itemFlowPaneWidth/336);
        if (cardsThatFit > 1) {
            int hGapValue = (itemFlowPaneWidth-(336*cardsThatFit))/(cardsThatFit-1);
            itemFlowPane.setHgap(hGapValue);
        }

        //Build item cards and pull in and load profile info
        updateProfileInfo(loadedProfile);

        //Adjust card spacing and cards in viewport based on the scenes width
        scene.widthProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                itemScrollPane.setVvalue(0);
                itemFlowPane.setPrefWidth(scene.getWidth()-206);
                itemPickerFlowPane.setPrefWidth(scene.getWidth()-206);
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
                itemPickerFlowPane.setPrefHeight(scene.getHeight()-50);
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
        //======================================
        //          Final Setup End
        //======================================
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
            writeToLogFile("Error writing to an XML document", e.toString());
        }
    }

    //Update the banner item counters taking into account the settings
    public static void updateBannerLabels() {
        int totalItemsObtained = 0;
        totalItemsObtained = settingsToggleButtonArray.get(2).isSelected() ? totalItemsObtained+countBL : totalItemsObtained;
        totalItemsObtained = settingsToggleButtonArray.get(3).isSelected() ? totalItemsObtained+countBL2 : totalItemsObtained;
        totalItemsObtained = settingsToggleButtonArray.get(4).isSelected() ? totalItemsObtained+countBLTPS : totalItemsObtained;
        totalItemsObtained = settingsToggleButtonArray.get(5).isSelected() ? totalItemsObtained+countBL3 : totalItemsObtained;
        totalItemsObtained = settingsToggleButtonArray.get(6).isSelected() ? totalItemsObtained+countBL4 : totalItemsObtained;
        int totalItemsAvailable = 0;
        totalItemsAvailable = settingsToggleButtonArray.get(2).isSelected() ? totalItemsAvailable+countObtainedBL : totalItemsAvailable;
        totalItemsAvailable = settingsToggleButtonArray.get(3).isSelected() ? totalItemsAvailable+countObtainedBL2 : totalItemsAvailable;
        totalItemsAvailable = settingsToggleButtonArray.get(4).isSelected() ? totalItemsAvailable+countObtainedBLTPS : totalItemsAvailable;
        totalItemsAvailable = settingsToggleButtonArray.get(5).isSelected() ? totalItemsAvailable+countObtainedBL3 : totalItemsAvailable;
        totalItemsAvailable = settingsToggleButtonArray.get(6).isSelected() ? totalItemsAvailable+countObtainedBL4 : totalItemsAvailable;
        int totalHuntPointsObtained = 0;
        totalHuntPointsObtained = settingsToggleButtonArray.get(7).isSelected() ? totalHuntPointsObtained+huntBL : totalHuntPointsObtained;
        totalHuntPointsObtained = settingsToggleButtonArray.get(8).isSelected() ? totalHuntPointsObtained+huntBL2 : totalHuntPointsObtained;
        totalHuntPointsObtained = settingsToggleButtonArray.get(9).isSelected() ? totalHuntPointsObtained+huntBLTPS : totalHuntPointsObtained;
        totalHuntPointsObtained = settingsToggleButtonArray.get(10).isSelected() ? totalHuntPointsObtained+huntBL3 : totalHuntPointsObtained;
        totalHuntPointsObtained = settingsToggleButtonArray.get(11).isSelected() ? totalHuntPointsObtained+huntBL4 : totalHuntPointsObtained;
        int totalHuntPointsAvailable = 0;
        totalHuntPointsAvailable = settingsToggleButtonArray.get(7).isSelected() ? totalHuntPointsAvailable+huntObtainedBL : totalHuntPointsAvailable;
        totalHuntPointsAvailable = settingsToggleButtonArray.get(8).isSelected() ? totalHuntPointsAvailable+huntObtainedBL2 : totalHuntPointsAvailable;
        totalHuntPointsAvailable = settingsToggleButtonArray.get(9).isSelected() ? totalHuntPointsAvailable+huntObtainedBLTPS : totalHuntPointsAvailable;
        totalHuntPointsAvailable = settingsToggleButtonArray.get(10).isSelected() ? totalHuntPointsAvailable+huntObtainedBL3 : totalHuntPointsAvailable;
        totalHuntPointsAvailable = settingsToggleButtonArray.get(11).isSelected() ? totalHuntPointsAvailable+huntObtainedBL4 : totalHuntPointsAvailable;
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
                itemFlowPane.getChildren().add(itemCardFilteredArray.get(i).getItemCard());
                updateBannerLabels();
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
    public static void fullReset() {
        try {
            itemPickerHBox.getChildren().clear();
            itemCardArray.clear();
            buildAllItemCards();
            resetDisplayedCards(searchTextField.getText());
            updateBannerLabels();
        } catch (Exception e) {
            writeToLogFile("Error doing full reset", e.toString());
        }
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
        //Manual garbage collector
        //Should replace this eventually by reworking the
        //card load/unload system
        System.gc();
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
            String name = itemCardArray.get(i).getName().toLowerCase();
            String type = itemCardArray.get(i).getType().toLowerCase();
            String game = itemCardArray.get(i).getGame();
            Boolean obtained = itemCardArray.get(i).getObtained();
            String rarity = itemCardArray.get(i).getRarity().toLowerCase();
            String source = itemCardArray.get(i).getSource().toLowerCase();
            String points = itemCardArray.get(i).getPoints();
            String chance = itemCardArray.get(i).getChance().toLowerCase();
            Boolean phosphene = !itemCardArray.get(i).getPhosphene().toLowerCase().isEmpty();
            Boolean worldDrop = itemCardArray.get(i).getWorldDrop();
            String missionReward = itemCardArray.get(i).getMission();
            String dlc = itemCardArray.get(i).getDLC();
            String vaultCard = itemCardArray.get(i).getVaultCard();
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
            } else if (obtained && !toggleButtonArray.get(28).isSelected()) {
                continue;
            } else if (!obtained && !toggleButtonArray.get(29).isSelected()) {
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
            } else if ((type.equals("relic") || type.equals("artifact")) && !toggleButtonArray.get(10).isSelected()) {
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
            } else if (!missionReward.isEmpty() && source.isEmpty() && !toggleButtonArray.get(32).isSelected()) {
                continue;
            } else if (worldDrop && source.isEmpty() && !toggleButtonArray.get(31).isSelected()) {
                continue;
            } else if (!vaultCard.isEmpty() && !toggleButtonArray.get(33).isSelected()) {
                continue;
            } else if (chance.equals("unobtainable") && settingsToggleButtonArray.get(12).isSelected()) {
                continue;
            } else if (!phosphene && settingsToggleButtonArray.get(1).isSelected()) {
                continue;
            } else if (!dlc.isEmpty() && !toggleButtonArray.get(30).isSelected()) {
                continue;
            } else if (points.equals("0") && settingsToggleButtonArray.get(0).isSelected()) {
                continue;
            }
            totalNodes ++;
            itemCardFilteredArray.add(itemCardArray.get(i));
        }
    }

    //Update profile file array
    public static void getProfiles() {
        try {
            profiles.clear();
            File[] files = new File(userDataDirectory, "profiles").listFiles();
            if (files.length == 0) {
                File newProfileFile = new File(userDataDirectory.getPath() + "/profiles", "default_profile.xml");
                Files.write(newProfileFile.toPath(), "<items></items>".getBytes());
                Element root = settingsDocument.getDocumentElement();
                Element profileElement = settingsDocument.createElement("profile");
                Element profileNameElement = settingsDocument.createElement("name");
                profileNameElement.appendChild(settingsDocument.createTextNode("default_profile"));
                profileElement.appendChild(profileNameElement);
                root.appendChild(profileElement);
                writeToXml(settingsDocument, settingsXML);
                files = new File(userDataDirectory, "profiles").listFiles();
            }
            for (File file : files) {
                profiles.add(file.getName().split("\\.")[0]);
            }
        } catch (Exception e) {
            writeToLogFile("Error getting profiles", e.toString());
        }
    }

    //Write a new line to the log file
    public static void writeToLogFile(String line) {
        try {
            String lineCombo = getDate() + " " + getTime() + ": " + line;
            Files.writeString(logFile.toPath(), lineCombo + System.lineSeparator(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Error writing to log file | " + e.toString());
        }
    }

    //Write 2 lines to the log file
    public static void writeToLogFile(String line, String line2) {
        try {
            String lineCombo = getDate() + " " + getTime() + ": " + line + " | " + line2;
            Files.writeString(logFile.toPath(), lineCombo + System.lineSeparator(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Error writing to log file | " + e.toString());
        }
    }

    //Return the current date in day-month-year format
    public static String getDate() {
        String dateNow = String.valueOf(LocalDate.now());
        String[] dateSplit = dateNow.split("-");
        return dateSplit[1] + "-" + dateSplit[2] + "-20" + dateSplit[0].split("0")[1];
    }

    //Return the current time in hour.minute.second format
    public static String getTime() {
        String timeNow = String.valueOf(LocalTime.now());
        String[] timeSplit = timeNow.split(":");
        return timeSplit[0] + ":" + timeSplit[1] + ":" + Math.round(Double.parseDouble(timeSplit[2]));
    }

    //Update choices in the combobox dropdown
    public static void updateComboBox() {
        profileCombobox.getItems().clear();
        bannerProfileCombobox.getItems().clear();
        profileCombobox.setValue("");
        getProfiles();
        for (String file : profiles) {
            profileCombobox.getItems().add(file);
            bannerProfileCombobox.getItems().add(file);
            bannerProfileCombobox.setOnHiding(event -> {
                String bannerSelectedProfile = bannerProfileCombobox.getValue();
                updateProfileInfo(bannerSelectedProfile);
            });
        }
    }

    //Update profile info
    public static void updateProfileInfo(String newProfile) {
        try {
            loadedProfile = newProfile;
            profileSettingElement.getElementsByTagName("name").item(0).setTextContent(loadedProfile);
            writeToXml(settingsDocument, settingsXML);
            profileDisplayButton.setText(loadedProfile);
            updateToolTip(profileDisplayButton, loadedProfile);
            updateToolTip(bannerProfileCombobox, "Loaded profile\n" + loadedProfile);
            profileXML = new File(userDataDirectory + "/profiles", loadedProfile +".xml");
            profileDocument = builder.parse(profileXML);
            profileNode = profileDocument.getDocumentElement();
            updateComboBox();
            ObservableList<String> profileItems = bannerProfileCombobox.getItems();
            for (int i = 0; i < profileItems.size(); i++) {
                if (loadedProfile.equals(profileItems.get(i))) {
                    bannerProfileCombobox.getSelectionModel().select(i);
                }
            }
            fullReset();
        } catch (Exception e) {
            writeToLogFile("Error updating profile info", e.toString());
        }
    }

    public static void addToolTip(Region pane, String text) {
        Tooltip newToolTip = new Tooltip(text);
        newToolTip.setId("toolTip");
        pane.setOnMouseMoved(event -> {
            newToolTip.show(pane, event.getScreenX() + 10, event.getScreenY() + 20);
        });
        pane.setOnMouseExited(event -> {
            newToolTip.hide();
        });
        pane.getProperties().put("TOOLTIP_KEY", newToolTip);
    }

    public static void updateToolTip(Region pane, String newText) {
        Tooltip tooltip = (Tooltip) pane.getProperties().get("TOOLTIP_KEY");
        if (tooltip != null) {
            tooltip.setText(newText);
        }
    }

    //Build all item cards into an array
    public static void buildAllItemCards() throws Exception {
        resetCounters();
        File[] files = itemsDirectory.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                Document document = builder.parse(file);
                NodeList nodes = document.getDocumentElement().getElementsByTagName("item");
                for (int i = 0; i < nodes.getLength(); i++) {
                    Element itemNode = (Element) nodes.item(i);
                    //Get item information that is required like ID, name, etc.
                    String id = itemNode.getElementsByTagName("id").item(0).getTextContent();
                    String name = itemNode.getElementsByTagName("name").item(0).getTextContent();
                    String type = itemNode.getElementsByTagName("type").item(0).getTextContent();
                    String game = itemNode.getElementsByTagName("game").item(0).getTextContent();
                    String rarity = itemNode.getElementsByTagName("rarity").item(0).getTextContent();
                    String obtainedText = "false";
                    NodeList profileNodes = profileNode.getElementsByTagName("item");
                    for (int j = 0; j < profileNodes.getLength(); j++) {
                        Element node = (Element) profileNodes.item(j);
                        String iDNode = node.getElementsByTagName("id").item(0).getTextContent();
                        if (id.equals(iDNode)) {
                            obtainedText = "true";
                            break;
                        }
                    }
                    //Get item information that is optional
                    String mod = "";
                    try {
                        mod = itemNode.getElementsByTagName("mod").item(0).getTextContent();
                    } catch (Exception e) { }
                    String chance = "";
                    try {
                        chance = itemNode.getElementsByTagName("chance").item(0).getTextContent();
                    } catch (Exception e) { }
                    String location = "";
                    try {
                        location = itemNode.getElementsByTagName("location").item(0).getTextContent();
                    } catch (Exception e) { }
                    String source = "";
                    try {
                        source = itemNode.getElementsByTagName("source").item(0).getTextContent();
                    } catch (Exception e) { }
                    String text = "";
                    try {
                        text = itemNode.getElementsByTagName("text").item(0).getTextContent();
                    } catch (Exception e) { }
                    String points = "0";
                    try {
                        points = itemNode.getElementsByTagName("points").item(0).getTextContent();
                    } catch (Exception e) { }
                    String dlc = "";
                    try {
                        dlc = itemNode.getElementsByTagName("dlc").item(0).getTextContent();
                    } catch (Exception e) { }
                    String worldDropText = "";
                    try {
                        worldDropText = itemNode.getElementsByTagName("worldDrop").item(0).getTextContent();
                    } catch (Exception e) { }
                    String wiki = "";
                    try {
                        wiki = itemNode.getElementsByTagName("wiki").item(0).getTextContent();
                    } catch (Exception e) { }
                    String lootlemon = "";
                    try {
                        lootlemon = itemNode.getElementsByTagName("lootlemon").item(0).getTextContent();
                    } catch (Exception e) { }
                    String mode = "";
                    try {
                        mode = itemNode.getElementsByTagName("mode").item(0).getTextContent();
                    } catch (Exception e) { }
                    String currency = "";
                    try {
                        currency = itemNode.getElementsByTagName("currency").item(0).getTextContent();
                    } catch (Exception e) { }
                    String grinder = "";
                    try {
                        grinder = itemNode.getElementsByTagName("grinder").item(0).getTextContent();
                    } catch (Exception e) { }
                    String mayhem = "";
                    try {
                        mayhem = itemNode.getElementsByTagName("mayhem").item(0).getTextContent();
                    } catch (Exception e) { }
                    String earlText = "";
                    try {
                        earlText = itemNode.getElementsByTagName("earl").item(0).getTextContent();
                    } catch (Exception e) { }
                    String phosphene = "";
                    try {
                        phosphene = itemNode.getElementsByTagName("phosphene").item(0).getTextContent();
                    } catch (Exception e) { }
                    String card = "";
                    try {
                        card = itemNode.getElementsByTagName("card").item(0).getTextContent();
                    } catch (Exception e) { }
                    String mission = "";
                    try {
                        mission = itemNode.getElementsByTagName("mission").item(0).getTextContent();
                    } catch (Exception e) { }
                    Boolean obtained = Boolean.parseBoolean(obtainedText);
                    Boolean worldDrop = Boolean.parseBoolean(worldDropText);
                    Boolean earl = Boolean.parseBoolean(earlText);
                    itemCardArray.add(new ItemCard(name, type, game, obtained, rarity, source, text, wiki, points, location, chance, lootlemon, 
                        worldDrop, dlc, mode, currency, grinder, mayhem, earl, phosphene, mission, card, id, mod));
                }
            }
        }
        //Sort the array by item name alphabetically
        Collections.sort(itemCardArray, new Comparator<ItemCard>() {
            public int compare(ItemCard p1, ItemCard p2) {
                return p1.getName().compareTo(p2.getName());
            }
        });
    }
}
