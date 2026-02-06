package com.frogman650;

import java.io.File;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.CacheHint;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

public class ItemCard {
    private String name;
    private String type;
    private String game;
    private Boolean obtained;
    private String rarity;
    private String source;
    private String text;
    private String wiki;
    private String points;
    private String location;
    private String chance;
    private String lootlemon;

    public ItemCard() {
        this.name = "";
        this.type = "";
        this.game = "";
        this.obtained = false;
        this.rarity = "";
        this.source = "";
        this.text = "";
        this.wiki = "";
        this.points = "";
        this.location = "";
        this.chance = "";
        this.lootlemon = "";
    }

    public ItemCard(String name, String type, String game, Boolean obtained, String rarity, 
        String source, String text, String wiki, String points, String location, String chance, String lootlemon) {
        this.name = name;
        this.type = type;
        this.game = game;
        this.obtained = obtained;
        this.rarity = rarity;
        this.source = source;
        this.text = text;
        this.wiki = wiki;
        this.points = points;
        this.location = location;
        this.chance = chance;
        this.lootlemon = lootlemon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public Boolean getObtained() {
        return obtained;
    }

    public void setObtained(Boolean obtained) {
        this.obtained = obtained;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getWiki() {
        return wiki;
    }

    public void setWiki(String wiki) {
        this.wiki = wiki;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getChance() {
        return chance;
    }

    public void setChance(String chance) {
        this.chance = chance;
    }

    public String getLootlemon() {
        return lootlemon;
    }

    public void setLootlemon(String lootlemon) {
        this.lootlemon = lootlemon;
    }

    public Pane getItemCard() {
        Pane itemPane = new Pane();
        StackPane itemImageStackPane = new StackPane();
        itemImageStackPane.setId("itemImageStackPane");
        ImageView itemImageView = new ImageView();
        itemImageView.setCache(true);
        itemImageView.setCacheHint(CacheHint.SPEED);
        //Setting the item type image
        if (type.toLowerCase().equals("pistol")) {
            itemImageView.setImage(App.pistolImage);
        } else if (type.equals("ar") || type.toLowerCase().equals("assault rifle")) {
            itemImageView.setImage(App.arImage);
        } else if (type.toLowerCase().equals("class mod")) {
            itemImageView.setImage(App.classModImage);
        } else if (type.toLowerCase().equals("grenade mod")) {
            itemImageView.setImage(App.grenadeImage);
        } else if (type.toLowerCase().equals("grenade ordnance") || type.toLowerCase().equals("grenade (ordnance)")) {
            itemImageView.setImage(App.grenadeOrdnanceImage);
        } else if (type.toLowerCase().equals("heavy ordnance") || type.toLowerCase().equals("heavy weapon (ordnance)")) {
            itemImageView.setImage(App.heavyOrdnanceImage);
        } else if (type.toLowerCase().equals("laser")) {
            itemImageView.setImage(App.laserImage);
        } else if (type.toLowerCase().equals("launcher") || type.toLowerCase().equals("rocket launcher")) {
            itemImageView.setImage(App.launcherImage);
        } else if (type.toLowerCase().equals("oz kit")) {
            itemImageView.setImage(App.ozKitImage);
        } else if (type.toLowerCase().equals("shield")) {
            itemImageView.setImage(App.shieldImage);
        } else if (type.toLowerCase().equals("shotgun")) {
            itemImageView.setImage(App.shotgunImage);
        } else if (type.equals("smg") || type.toLowerCase().equals("submachine gun")) {
            itemImageView.setImage(App.smgImage);
        } else if (type.toLowerCase().equals("sniper") || type.toLowerCase().equals("sniper rifle")) {
            itemImageView.setImage(App.sniperImage);
        } else if (type.toLowerCase().equals("relic") || type.toLowerCase().equals("artifact")) {
            itemImageView.setImage(App.relicImage);
        } else if (type.toLowerCase().equals("eridian")) {
            itemImageView.setImage(App.eridianImage);
        } else if (type.toLowerCase().equals("repkit")) {
            itemImageView.setImage(App.repkitImage);
        } else if (type.toLowerCase().equals("enhancement")) {
            itemImageView.setImage(App.enhancementImage);
        }
        Pane itemBackgroundColor = new Pane();
        itemBackgroundColor.setId("itemBackgroundColor");
        itemImageStackPane.getChildren().addAll(itemBackgroundColor, itemImageView);
        Label gameLabel = new Label("Borderlands " + game);
        gameLabel.setId("gameLabel");
        //lock the shared variables so only 1 thread can access them at a time
        if (!(chance.equals("unobtainable") && App.settingsToggleButtonArray.get(12).isSelected())) {
            if (!chance.toLowerCase().contains("phosphene") && App.settingsToggleButtonArray.get(1).isSelected()) {
            } else {
                App.lock.lock();
                try {
                    if (game.equals("")) {
                        gameLabel.setText("Borderlands");
                        App.countList[5] ++;
                        App.huntList[5] += Integer.parseInt(points);
                    } else if (game.equals("2")) {
                        App.countList[6] ++;
                        App.huntList[6] += Integer.parseInt(points);
                    } else if (game.equals("TPS")) {
                        App.countList[7] ++;
                        App.huntList[7] += Integer.parseInt(points);
                    } else if (game.equals("3")) {
                        App.countList[8] ++;
                        App.huntList[8] += Integer.parseInt(points);
                    } else if (game.equals("4")) {
                        App.countList[9] ++;
                        App.huntList[9] += Integer.parseInt(points);
                    }
                } finally {
                    App.lock.unlock();
                }
            }
        }
        Pane obtainedPane = new Pane();
        if (obtained) {
            obtainedPane.setBackground(new Background(new BackgroundImage(App.obtainedImage, null, null, null, null)));
            //lock the shared variables so only 1 thread can access them at a time
            if (!(chance.toLowerCase().equals("unobtainable") && App.settingsToggleButtonArray.get(12).isSelected())) {
                if (!chance.toLowerCase().contains("phosphene") && App.settingsToggleButtonArray.get(1).isSelected()) {
                } else {
                    App.lock.lock();
                    try {
                        if (game.equals("")) {
                            App.countList[0] ++;
                            App.huntList[0] += Integer.parseInt(points);
                        } else if (game.equals("2")) {
                            App.countList[1] ++;
                            App.huntList[1] += Integer.parseInt(points);
                        } else if (game.equals("TPS")) {
                            App.countList[2] ++;
                            App.huntList[2] += Integer.parseInt(points);
                        } else if (game.equals("3")) {
                            App.countList[3] ++;
                            App.huntList[3] += Integer.parseInt(points);
                        } else if (game.equals("4")) {
                            App.countList[4] ++;
                            App.huntList[4] += Integer.parseInt(points);
                        }
                    } finally {
                        App.lock.unlock();
                    }
                }
            }
        } else {
            obtainedPane.setBackground(new Background(new BackgroundImage(App.notObtainedImage, null, null, null, null)));
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
                NodeList saveNodes = App.saveNode.getElementsByTagName("item");
                for (int j = 0; j < saveNodes.getLength(); j++) {
                    Element node = (Element) saveNodes.item(j);
                    String nameNode = node.getElementsByTagName("name").item(0).getTextContent();
                    String typeNode = node.getElementsByTagName("type").item(0).getTextContent();
                    String rarityNode = node.getElementsByTagName("rarity").item(0).getTextContent();
                    String gameNode = node.getElementsByTagName("game").item(0).getTextContent();
                    if (name.equals(nameNode) && type.equals(typeNode) && rarity.equals(rarityNode) && gameNode.equals(game)) {
                        Platform.runLater(() -> {
                            obtainedPane.setBackground(new Background(new BackgroundImage(App.notObtainedImage, null, null, null, null)));
                        });
                        obtainedSwitch = true;
                        itemPane.setAccessibleText(name + "#%" + type + "#%" + game + "#%false#%" + rarity + "#%" + source + "#%" + points + "#%" + chance);
                        if (game.equals("")) {
                            App.countList[0] --;
                            App.huntList[0] -= Integer.parseInt(points);
                        } else if (game.equals("2")) {
                            App.countList[1] --;
                            App.huntList[1] -= Integer.parseInt(points);
                        } else if (game.equals("TPS")) {
                            App.countList[2] --;
                            App.huntList[2] -= Integer.parseInt(points);
                        } else if (game.equals("3")) {
                            App.countList[3] --;
                            App.huntList[3] -= Integer.parseInt(points);
                        } else if (game.equals("4")) {
                            App.countList[4] --;
                            App.huntList[4] -= Integer.parseInt(points);
                        }
                        App.saveNode.removeChild(node);
                        App.writeToXml(App.saveDocument, new File(App.executableDirectory + "/saves", App.loadedProfile + ".xml"));
                        break;
                    } 
                }
                if (!obtainedSwitch) {
                    Platform.runLater(() -> {
                        obtainedPane.setBackground(new Background(new BackgroundImage(App.obtainedImage, null, null, null, null)));
                    });
                    itemPane.setAccessibleText(name + "#%" + type + "#%" + game + "#%true#%" + rarity + "#%" + source + "#%" + points + "#%" + chance);
                        if (game.equals("")) {
                            App.countList[0] ++;
                            App.huntList[0] += Integer.parseInt(points);
                        } else if (game.equals("2")) {
                            App.countList[1] ++;
                            App.huntList[1] += Integer.parseInt(points);
                        } else if (game.equals("TPS")) {
                            App.countList[2] ++;
                            App.huntList[2] += Integer.parseInt(points);
                        } else if (game.equals("3")) {
                            App.countList[3] ++;
                            App.huntList[3] += Integer.parseInt(points);
                        } else if (game.equals("4")) {
                            App.countList[4] ++;
                            App.huntList[4] += Integer.parseInt(points);
                        }
                    Element newItemElement = App.saveDocument.createElement("item");
                    Element newNameElement = App.saveDocument.createElement("name");
                    newNameElement.appendChild(App.saveDocument.createTextNode(name));
                    Element newTypeElement = App.saveDocument.createElement("type");
                    newTypeElement.appendChild(App.saveDocument.createTextNode(type));
                    Element newRarityElement = App.saveDocument.createElement("rarity");
                    newRarityElement.appendChild(App.saveDocument.createTextNode(rarity));
                    Element newGameElement = App.saveDocument.createElement("game");
                    newGameElement.appendChild(App.saveDocument.createTextNode(game));
                    newItemElement.appendChild(newNameElement);
                    newItemElement.appendChild(newTypeElement);
                    newItemElement.appendChild(newRarityElement);
                    newItemElement.appendChild(newGameElement);
                    App.saveDocument.getDocumentElement().appendChild(newItemElement);
                    App.writeToXml(App.saveDocument, new File(App.executableDirectory + "/saves", App.loadedProfile + ".xml"));
                }
                Platform.runLater(() -> {
                    App.resetDisplayedCards(App.searchTextField.getText());
                    App.updateBannerLabels();
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
            itemBackgroundColor.setBackground(new Background(new BackgroundImage(App.effervescentBackground, null, null, null, null)));
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
        ImageView itemWikiLinkImageView = new ImageView(App.wikiImage);
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
            App.hostService.showDocument(wiki);
        });
        //Lootlemon button
        ImageView lootlemonLinkImageView = new ImageView(App.miniLootlemonImage);
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
            App.hostService.showDocument(lootlemon);
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
        // App.lock.lock();
        // // try {
        //     App.itemCardArray.add(itemPane);
        // } finally {
        //     App.lock.unlock();
        // }
        return itemPane;
    }
}
