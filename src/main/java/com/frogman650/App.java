package com.frogman650;

import java.util.ArrayList;

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

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
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
    public static Image effervescentBackground;
    public static FlowPane itemFlowPane;
    public static Document itemDocument;
    public static NodeList itemNodes;
    public static ArrayList<Pane> itemCardArray = new ArrayList<>();

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
        effervescentBackground = new Image(getClass().getResourceAsStream("effervescent_rev2.gif"));

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        // itemDocument = builder.parse(getClass().getResourceAsStream("items.xml"));
        itemDocument = builder.parse(new File("src/main/resources/com/frogman650/items.xml"));
        itemNodes = itemDocument.getDocumentElement().getElementsByTagName("item");

        //Item cards holder
        itemFlowPane = new FlowPane();
        itemFlowPane.setId("itemFlowPane");
        itemFlowPane.setPadding(new Insets(5));

        ScrollPane itemScrollPane = new ScrollPane(itemFlowPane);
        itemScrollPane.setId("itemScrollPane");

        //Filters
        VBox filterVBox = new VBox();
        filterVBox.setSpacing(1);
        Label filterLabel = new Label("FILTERS");
        filterLabel.setId("filterLabel");
        Button allButton = new Button("All");
        allButton.setId("all");
        Button noneButton = new Button("None");
        noneButton.setId("none");
        HBox allNoneHBox = new HBox(2);
        allNoneHBox.setAlignment(Pos.CENTER);
        allNoneHBox.getChildren().addAll(allButton, noneButton);
        filterVBox.getChildren().addAll(filterLabel, allNoneHBox);
        ArrayList<ToggleButton> toggleButtonArray = new ArrayList<>();
        Label weaponLabel = new Label("WEAPONS");
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
        ToggleButton eridianToggleButton = new ToggleButton("Eridian");
        toggleButtonArray.add(eridianToggleButton);
        ToggleButton laserToggleButton = new ToggleButton("Lasers");
        toggleButtonArray.add(laserToggleButton);
        filterVBox.getChildren().addAll(weaponLabel, pistolToggleButton, smgToggleButton, assaultRifleToggleButton, shotgunToggleButton, 
        sniperToggleButton, launcherToggleButton, eridianToggleButton, laserToggleButton);
        Label equipmentLabel = new Label("EQUIPMENT");
        equipmentLabel.setId("filterLabel");
        ToggleButton classModToggleButton = new ToggleButton("Class Mods");
        toggleButtonArray.add(classModToggleButton);
        ToggleButton grenadeToggleButton = new ToggleButton("Grenade Mods");
        toggleButtonArray.add(grenadeToggleButton);
        ToggleButton relicToggleButton = new ToggleButton("Relifacts");
        toggleButtonArray.add(relicToggleButton);
        ToggleButton shieldToggleButton = new ToggleButton("Shields");
        toggleButtonArray.add(shieldToggleButton);
        ToggleButton ozKitToggleButton = new ToggleButton("Oz Kits");
        toggleButtonArray.add(ozKitToggleButton);
        ToggleButton enhancementToggleButton = new ToggleButton("Enhancements");
        toggleButtonArray.add(enhancementToggleButton);
        ToggleButton repkitToggleButton = new ToggleButton("Repkits");
        toggleButtonArray.add(repkitToggleButton);
        ToggleButton grenadeOrdnanceToggleButton = new ToggleButton("Grenade Ordnance");
        toggleButtonArray.add(grenadeOrdnanceToggleButton);
        ToggleButton heavyOrdnanceToggleButton = new ToggleButton("Heavy Ordnance");
        toggleButtonArray.add(heavyOrdnanceToggleButton);
        filterVBox.getChildren().addAll(equipmentLabel, classModToggleButton, grenadeToggleButton, relicToggleButton, shieldToggleButton, 
        ozKitToggleButton, enhancementToggleButton, repkitToggleButton, grenadeOrdnanceToggleButton, heavyOrdnanceToggleButton);
        Label rarityLabel = new Label("RARITY");
        rarityLabel.setId("filterLabel");
        ToggleButton uniqueToggleButton = new ToggleButton("Unique");
        toggleButtonArray.add(uniqueToggleButton);
        ToggleButton legendaryToggleButton = new ToggleButton("Legendary");
        toggleButtonArray.add(legendaryToggleButton);
        ToggleButton seraphToggleButton = new ToggleButton("Seraph");
        toggleButtonArray.add(seraphToggleButton);
        ToggleButton pearlToggleButton = new ToggleButton("Pearlescent");
        toggleButtonArray.add(pearlToggleButton);
        ToggleButton glitchToggleButton = new ToggleButton("Glitch");
        toggleButtonArray.add(glitchToggleButton);
        ToggleButton effervescentToggleButton = new ToggleButton("Effervescent");
        toggleButtonArray.add(effervescentToggleButton);
        filterVBox.getChildren().addAll(rarityLabel, uniqueToggleButton, legendaryToggleButton, seraphToggleButton, 
        pearlToggleButton, glitchToggleButton, effervescentToggleButton);
        Label gameFilterLabel = new Label("GAME");
        gameFilterLabel.setId("filterLabel");
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
        filterVBox.getChildren().addAll(gameFilterLabel, bl1ToggleButton, bl2ToggleButton, blTPSToggleButton, bl3ToggleButton, bl4ToggleButton);
        Label miscLabel = new Label("MISCELLANEOUS");
        miscLabel.setId("filterLabel");
        ToggleButton obtainedToggleButton = new ToggleButton("Obtained");
        toggleButtonArray.add(obtainedToggleButton);
        ToggleButton notObtainedToggleButton = new ToggleButton("Not Obtained");
        toggleButtonArray.add(notObtainedToggleButton);
        filterVBox.getChildren().addAll(miscLabel, obtainedToggleButton, notObtainedToggleButton);
        filterVBox.setId("filterVBox");
        allToggleButtonsOn(toggleButtonArray);
        ScrollPane filterScrollPane = new ScrollPane(filterVBox);
        filterScrollPane.setId("filterScrollPane");

        //Banner
        ImageView wikiLinkImageView = new ImageView(wikiImage);
        wikiLinkImageView.setId("wikiLinkImageView");
        wikiLinkImageView.setFitHeight(48);
        wikiLinkImageView.setFitWidth(87);
        Pane wikiViewPane = new Pane(wikiLinkImageView);
        wikiViewPane.setStyle("-fx-cursor: hand;");
        wikiViewPane.setOnMouseClicked(event -> {
            try {
                Desktop.getDesktop().browse(new URI("https://borderlands.fandom.com/wiki/Borderlands_Wiki"));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        });
        Label label2 = new Label("Other info here");
        Label label3 = new Label("Other info here");
        VBox sizeVBox = new VBox(label2, label3);
        HBox bannerHBox = new HBox(5, wikiViewPane, sizeVBox);
        bannerHBox.setId("bannerBox");

        //Build item cards
        buildAllItemCards();
        addAllItemCards();

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
        itemFlowPane.setPrefWidth(scene.getWidth()-210);
        scene.widthProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                label2.setText("Width: " + scene.getWidth());
                if (scene.getWidth() == 2560) {
                    itemFlowPane.setStyle("-fx-hgap: 0;");
                    itemFlowPane.setPadding(new Insets(0));
                    itemFlowPane.setPrefWidth(scene.getWidth()-208);
                } else {
                    itemFlowPane.setStyle("-fx-hgap: 5;");
                    itemFlowPane.setPadding(new Insets(5));
                    itemFlowPane.setPrefWidth(scene.getWidth()-210);
                }
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
            StreamResult docResult = new StreamResult(new File("src/main/resources/com/frogman650/items.xml"));
            transformer.transform(docSource, docResult);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    public static int getCardNumber(String itemName) {
        for (int i = 0; i < itemNodes.getLength(); i++) {
            Element node = (Element) itemNodes.item(i);
            if (node.getElementsByTagName("name").item(0).getTextContent().equals(itemName)) {
                return i;
            }
        }
        return -1;
    }

    public static void addAllItemCards() {
        for (int i = 0; i < itemCardArray.size(); i++) {
            itemFlowPane.getChildren().add(itemCardArray.get(i));
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
        HBox testHBox = new HBox(obtainedPane, gameLabel, huntPointsLabel);
        HBox.setMargin(obtainedPane, new Insets(5, 0, 0, 12));
        obtainedPane.setOnMouseClicked(event -> {
            Element node = (Element) itemNodes.item(getCardNumber(name));
            Node obtainedNode = node.getElementsByTagName("obtained").item(0);
            if (obtainedNode.getTextContent().equals("true")) {
                obtainedPane.setBackground(new Background(new BackgroundImage(notObtainedImage, null, null, null, null)));
                obtainedNode.setTextContent("false");
            } else {
                obtainedPane.setBackground(new Background(new BackgroundImage(obtainedImage, null, null, null, null)));
                obtainedNode.setTextContent("true");
            }
            writeToXml();
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
        Label flavorTextLabel = new Label("\u2022 " + text);
        flavorTextLabel.setId("flavorTextLabel");
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
            try {
                Desktop.getDesktop().browse(new URI(wiki));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        });
        Region hPusherRegion = new Region();
        HBox.setHgrow(hPusherRegion, Priority.ALWAYS);
        HBox itemBottomHBox = new HBox(itemWikiLinkPane, hPusherRegion);
        itemBottomHBox.setId("itemBottomHBox");
        Region vPusherRegion = new Region();
        VBox.setVgrow(vPusherRegion, Priority.ALWAYS);
        
        VBox itemVBox = new VBox(testHBox, itemNameLabel, itemImageStackPane, flavorTextLabel, 
        sourcesLabel, sourcesListLabel, vPusherRegion, itemBottomHBox);
        itemVBox.setId("itemVBox");
        VBox.setMargin(itemNameLabel, new Insets(5, 0 ,0, 0));
        VBox.setMargin(itemImageStackPane, new Insets(1, 0 ,0, 0));
        Pane itemPane = new Pane(itemVBox);
        itemPane.setId("itemPane");
        itemCardArray.add(itemPane);
    }
}
