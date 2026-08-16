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
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

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
    private Boolean worldDrop;
    private String dlc;
    private String mode;
    private String currency;
    private String grinder;
    private String mayhem;
    private Boolean earl;
    private String mission;
    private String phosphene;
    private String id;
    private String card;
    private String mod;

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
        this.worldDrop = false;
        this.dlc = "";
        this.mode = "";
        this.currency = "";
        this.grinder = "";
        this.mayhem = "";
        this.earl = false;
        this.phosphene = "";
        this.id = "";
        this.card = "";
        this.mission = "";
        this.mod = "";
    }

    public ItemCard(String name, String type, String game, Boolean obtained, String rarity, 
        String source, String text, String wiki, String points, String location, String chance, 
        String lootlemon, Boolean worldDrop, String dlc, String mode, String currency, String grinder, 
        String mayhem, Boolean earl, String phosphene, String mission, String card, String id, String mod) {
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
        this.worldDrop = worldDrop;
        this.dlc = dlc;
        this.mode = mode;
        this.currency = currency;
        this.grinder = grinder;
        this.mayhem = mayhem;
        this.earl = earl;
        this.phosphene = phosphene;
        this.mission = mission;
        this.id = id;
        this.card = card;
        this.mod = mod;
        if (!(chance.equals("unobtainable") && App.settingsToggleButtonArray.get(12).isSelected())) {
            if (phosphene.isEmpty() && App.settingsToggleButtonArray.get(1).isSelected()) {
            } else if (points.equals("0") && App.settingsToggleButtonArray.get(0).isSelected()) {
            } else {
                App.lock.lock();
                try {
                    if (game.equals("")) {
                        App.countObtainedBL ++;
                        App.huntObtainedBL += Integer.parseInt(points);
                    } else if (game.equals("2")) {
                        App.countObtainedBL2 ++;
                        App.huntObtainedBL2 += Integer.parseInt(points);
                    } else if (game.equals("TPS")) {
                        App.countObtainedBLTPS ++;
                        App.huntObtainedBLTPS += Integer.parseInt(points);
                    } else if (game.equals("3")) {
                        App.countObtainedBL3 ++;
                        App.huntObtainedBL3 += Integer.parseInt(points);
                    } else if (game.equals("4")) {
                        App.countObtainedBL4 ++;
                        App.huntObtainedBL4 += Integer.parseInt(points);
                    }
                } finally {
                    App.lock.unlock();
                }
            }
        }
        if (obtained) {
            if (!(chance.toLowerCase().equals("unobtainable") && App.settingsToggleButtonArray.get(12).isSelected())) {
                if (phosphene.isEmpty() && App.settingsToggleButtonArray.get(1).isSelected()) {
                } else if (points.equals("0") && App.settingsToggleButtonArray.get(0).isSelected()) {
                } else {
                    //lock the shared variables so only 1 thread can access them at a time
                    App.lock.lock();
                    try {
                        if (game.equals("")) {
                            App.countBL ++;
                            App.huntBL += Integer.parseInt(points);
                        } else if (game.equals("2")) {
                            App.countBL2 ++;
                            App.huntBL2 += Integer.parseInt(points);
                        } else if (game.equals("TPS")) {
                            App.countBLTPS ++;
                            App.huntBLTPS += Integer.parseInt(points);
                        } else if (game.equals("3")) {
                            App.countBL3 ++;
                            App.huntBL3 += Integer.parseInt(points);
                        } else if (game.equals("4")) {
                            App.countBL4 ++;
                            App.huntBL4 += Integer.parseInt(points);
                        }
                    } finally {
                        App.lock.unlock();
                    }
                }
            }
        }
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

    public Boolean getWorldDrop() {
        return worldDrop;
    }

    public String getMission() {
        return mission;
    }

    public String getPhosphene() {
        return phosphene;
    }

    public String getDLC() {
        return dlc;
    }

    public String getID() {
        return id;
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

    public String getVaultCard() {
        return card;
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
        Pane obtainedPane = new Pane();
        if (obtained) {
            obtainedPane.setBackground(new Background(new BackgroundImage(App.obtainedImage, null, null, null, null)));
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
                if (obtained) {
                    Platform.runLater(() -> {
                        obtainedPane.setBackground(new Background(new BackgroundImage(App.notObtainedImage, null, null, null, null)));
                    });
                    NodeList profileNodes = App.profileNode.getElementsByTagName("item");
                    for (int j = 0; j < profileNodes.getLength(); j++) {
                        Element node = (Element) profileNodes.item(j);
                        String iDNode = node.getElementsByTagName("id").item(0).getTextContent();
                        if (id.equals(iDNode)) {
                            App.profileNode.removeChild(node);
                            App.writeToXml(App.profileDocument, new File(App.localShareDirectory + "/profiles", App.loadedProfile + ".xml"));
                            break;
                        }
                    }
                    if (game.equals("")) {
                        App.countBL --;
                        App.huntBL -= Integer.parseInt(points);
                    } else if (game.equals("2")) {
                        App.countBL2 --;
                        App.huntBL2 -= Integer.parseInt(points);
                    } else if (game.equals("TPS")) {
                        App.countBLTPS --;
                        App.huntBLTPS -= Integer.parseInt(points);
                    } else if (game.equals("3")) {
                        App.countBL3 --;
                        App.huntBL3 -= Integer.parseInt(points);
                    } else if (game.equals("4")) {
                        App.countBL4 --;
                        App.huntBL4 -= Integer.parseInt(points);
                    }
                    obtained = false;
                } else {
                    Platform.runLater(() -> {
                        obtainedPane.setBackground(new Background(new BackgroundImage(App.obtainedImage, null, null, null, null)));
                    });
                    if (game.equals("")) {
                        App.countBL ++;
                        App.huntBL += Integer.parseInt(points);
                    } else if (game.equals("2")) {
                        App.countBL2 ++;
                        App.huntBL2 += Integer.parseInt(points);
                    } else if (game.equals("TPS")) {
                        App.countBLTPS ++;
                        App.huntBLTPS += Integer.parseInt(points);
                    } else if (game.equals("3")) {
                        App.countBL3 ++;
                        App.huntBL3 += Integer.parseInt(points);
                    } else if (game.equals("4")) {
                        App.countBL4 ++;
                        App.huntBL4 += Integer.parseInt(points);
                    }
                    Element newItemElement = App.profileDocument.createElement("item");
                    Element newIDElement = App.profileDocument.createElement("id");
                    newIDElement.appendChild(App.profileDocument.createTextNode(id));
                    newItemElement.appendChild(newIDElement);
                    App.profileDocument.getDocumentElement().appendChild(newItemElement);
                    App.writeToXml(App.profileDocument, new File(App.localShareDirectory + "/profiles", App.loadedProfile + ".xml"));
                    obtained = true;
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
            //GraalVM doesn't seem to parse gradients properly on compilation on linux so
            //we need to create a literal gradient image to set as the text
            // 1. Estimate the size boundaries of your text string
            int textWidth = labelCount*15;
            int textHeight = 10;

            // 2. Build the GraalVM-safe raw gradient map
            WritableImage textGradientImage = new WritableImage(textWidth, textHeight);
            PixelWriter writer = textGradientImage.getPixelWriter();

            // 3. Generate the rainbow pixel-by-pixel using Hue (0 to 360)
            for (int x = 0; x < textWidth; x++) {
                // Map the horizontal coordinate to a fraction (0.0 to 1.0)
                double progress = (double) x / (textWidth - 1);
                
                // Convert progress to a 360-degree color wheel cycle
                double hue = progress * 360.0; 
                
                // Create the color: Full saturation (1.0) and full brightness (1.0)
                Color rainbowColor = Color.hsb(hue, 1.0, 1.0);
                
                // Paint a solid vertical line of this color down the column
                for (int y = 0; y < textHeight; y++) {
                    writer.setColor(x, y, rainbowColor);
                }
            }

            // 4. Wrap the image inside an ImagePattern and set it as the Text's fill
            ImagePattern pattern = new ImagePattern(
                textGradientImage, 0, 0, textWidth, textHeight, false // 'false' targets pixel coordinate mode
            );
            itemNameLabel.setTextFill(pattern);
        }

        //Item text VBox to store flavor text and sources text
        VBox itemTextVBox = new VBox();
        itemTextVBox.setId("itemTextVBox");
        itemTextVBox.setFillWidth(false);
        itemTextVBox.setPadding(new Insets(0, 0, 0, 10));

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
            } else {
                tempFlavorTextLabel.setText("");
                itemTextVBox.getChildren().add(tempFlavorTextLabel);
            }
        }

        //Sources text start
        if (!source.isEmpty()) {
            Label sourcesLabel = new Label("Sources");
            sourcesLabel.setId("sourcesLabel");
            itemTextVBox.getChildren().addAll(sourcesLabel);
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
        }
        //Indicator tray start
        HBox indicatorTray = new HBox();
        indicatorTray.setId("indicatorTray");
        //Item wiki indicator button
        if (!wiki.isEmpty()) {
            ImageView itemWikiLinkImageView = new ImageView(App.wikiMiniImage);
            Pane itemWikiLinkPane = new Pane(itemWikiLinkImageView);
            itemWikiLinkPane.setId("indicatorLink");
            Tooltip itemWikiLinkPaneToolTip = new Tooltip("Borderlands Wiki\n" + wiki);
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
            indicatorTray.getChildren().add(itemWikiLinkPane);
        }
        //Lootlemon indicator button
        if (!lootlemon.isEmpty()) {
            ImageView lootlemonLinkImageView = new ImageView(App.miniLootlemonImage);
            lootlemonLinkImageView.setFitWidth(17);
            Pane lootlemonLinkPane = new Pane(lootlemonLinkImageView);
            lootlemonLinkPane.setId("indicatorLink");
            Tooltip lootlemonLinkPaneToolTip = new Tooltip("LootLemon\n" + lootlemon);
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
            indicatorTray.getChildren().add(lootlemonLinkPane);
        }
        //World drop indicator
        if (worldDrop) {
            ImageView worldDropImageView = new ImageView(App.worldDropImage);
            Tooltip worldDropToolTip = new Tooltip("World drop");
            worldDropToolTip.setId("toolTip");
            worldDropImageView.setOnMouseMoved(event -> {
                worldDropToolTip.show(worldDropImageView, event.getScreenX() + 10, event.getScreenY() + 20);
            });
            worldDropImageView.setOnMouseExited(event -> {
                worldDropToolTip.hide();
            }); 
            indicatorTray.getChildren().add(worldDropImageView);
        }
        //DLC indicator
        if (!dlc.isEmpty()) {
            ImageView dlcImageView = new ImageView(App.dlcImage);
            Pane dlcPane = new Pane(dlcImageView);
            Tooltip dlcToolTip = new Tooltip("DLC required\n" + dlc);
            dlcToolTip.setId("toolTip");
            dlcPane.setOnMouseMoved(event -> {
                dlcToolTip.show(dlcPane, event.getScreenX() + 10, event.getScreenY() + 20);
            });
            dlcPane.setOnMouseExited(event -> {
                dlcToolTip.hide();
            });
            indicatorTray.getChildren().add(dlcPane);
        }
        //Mod indicator
        if (!mod.isEmpty()) {
            ImageView modLinkImageView = new ImageView(App.modImage);
            Pane modLinkPane = new Pane(modLinkImageView);
            modLinkPane.setId("indicatorLink");
            String modStrings[] = mod.split("#@")[0].split("_");
            //If the mod text includes a #@ followed by a link at the end
            //make the indicator clickable and open the link on click
            String modLink;
            if (mod.contains("#@")) {
                modLink = mod.split("#@")[1];
            } else {
                modLink = "";
            }
            String modContent = "";
            for (String string : modStrings) {
                modContent = modContent + string + "\n";
            }
            Tooltip modLinkPaneToolTip = new Tooltip("Item added by a mod\n" + modContent);
            modLinkPaneToolTip.setId("toolTip");
            modLinkPane.setOnMouseMoved(event -> {
                modLinkPaneToolTip.show(modLinkPane, event.getScreenX() + 10, event.getScreenY() + 20);
            });
            modLinkPane.setOnMouseExited(event -> {
                modLinkPaneToolTip.hide();
            });
            if (!modLink.isEmpty()) {
                modLinkPane.setOnMouseClicked(event -> {
                    App.hostService.showDocument(modLink);
                });
            } else {
                modLinkPane.setStyle("-fx-cursor: none;");
            }
            indicatorTray.getChildren().add(modLinkPane);
        }
        //Mission indicator
        if (!mission.isEmpty()) {
            String[] missionSplit = mission.split("#@");
            for (String string : missionSplit) {
                String[] stringSplit = string.split("_");
                String missionText = "";
                for (String string2 : stringSplit) {
                    missionText = missionText + string2 + "\n";
                }
                ImageView missionImageView = new ImageView(App.missionImage);
                Pane missionPane = new Pane(missionImageView);
                Tooltip missionToolTip = new Tooltip(missionText);
                missionToolTip.setId("toolTip");
                missionPane.setOnMouseMoved(event -> {
                    missionToolTip.show(missionPane, event.getScreenX() + 10, event.getScreenY() + 20);
                });
                missionPane.setOnMouseExited(event -> {
                    missionToolTip.hide();
                });
                indicatorTray.getChildren().add(missionPane);
            }
        }
        //BL2 game mode indicator
        if (!mode.isEmpty()) {
            ImageView gameModeImageView = new ImageView(App.gameModeImage);
            Pane gameModePane = new Pane(gameModeImageView);
            Tooltip gameModeToolTip = new Tooltip(mode + " required");
            gameModeToolTip.setId("toolTip");
            gameModePane.setOnMouseMoved(event -> {
                gameModeToolTip.show(gameModePane, event.getScreenX() + 10, event.getScreenY() + 20);
            });
            gameModePane.setOnMouseExited(event -> {
                gameModeToolTip.hide();
            }); 
            indicatorTray.getChildren().add(gameModePane);
        }
        //BL2 currency indicator
        if (!currency.isEmpty()) {
            String currencyText = currency.equals("torgue") ? "Torgue tokens" : currency + " Seraph crystals";
            ImageView currencyImageView = new ImageView();
            if (currency.equals("torgue")) {
                currencyImageView.setImage(App.torgueImage);
            } else {
                currencyImageView.setImage(App.seraphImage);
            }
            Pane currencyPane = new Pane(currencyImageView);
            Tooltip currencyToolTip = new Tooltip("Can be bought with\n" + currencyText);
            currencyToolTip.setId("toolTip");
            currencyPane.setOnMouseMoved(event -> {
                currencyToolTip.show(currencyPane, event.getScreenX() + 10, event.getScreenY() + 20);
            });
            currencyPane.setOnMouseExited(event -> {
                currencyToolTip.hide();
            });
            indicatorTray.getChildren().add(currencyPane);
        }
        //BLTPS grinder indicator
        if (!grinder.isEmpty()) {
            ImageView grinderImageView = new ImageView(App.grinderImage);
            Pane grinderPane = new Pane(grinderImageView);
            Tooltip grinderToolTip = new Tooltip("Obtainable from the Grinder\n" + grinder);
            grinderToolTip.setId("toolTip");
            grinderPane.setOnMouseMoved(event -> {
                grinderToolTip.show(grinderPane, event.getScreenX() + 10, event.getScreenY() + 20);
            });
            grinderPane.setOnMouseExited(event -> {
                grinderToolTip.hide();
            });  
            indicatorTray.getChildren().add(grinderPane);
        }
        //BL3 mayhem indicator
        if (!mayhem.isEmpty()) {
            ImageView mayhemImageView = new ImageView(App.mayhemImage);
            Pane mayhemPane = new Pane(mayhemImageView);
            Tooltip mayhemToolTip = new Tooltip("Mayhem " + mayhem + " required");
            mayhemToolTip.setId("toolTip");
            mayhemPane.setOnMouseMoved(event -> {
                mayhemToolTip.show(mayhemPane, event.getScreenX() + 10, event.getScreenY() + 20);
            });
            mayhemPane.setOnMouseExited(event -> {
                mayhemToolTip.hide();
            }); 
            indicatorTray.getChildren().add(mayhemPane);
        }
        //BL3 earl indicator
        if (earl) {
            ImageView earlImageView = new ImageView(App.earlImage);
            Pane earlPane = new Pane(earlImageView);
            Tooltip earlToolTip = new Tooltip("Reobtainable from Earl's\nVeteran Rewards Vending Machine");
            earlToolTip.setId("toolTip");
            earlPane.setOnMouseMoved(event -> {
                earlToolTip.show(earlPane, event.getScreenX() + 10, event.getScreenY() + 20);
            });
            earlPane.setOnMouseExited(event -> {
                earlToolTip.hide();
            }); 
            indicatorTray.getChildren().add(earlPane);
        }
        //BL3/4 vault card indicator
        if (!card.isEmpty()) {
            ImageView cardImageView = new ImageView(App.cardImage);
            Pane cardPane = new Pane(cardImageView);
            String keysTickets = "";
            if (game.equals("3")) {
                keysTickets = "5 keys";
            } else {
                keysTickets = "10 tickets";
            }
            Tooltip cardToolTip = new Tooltip("Vault card reward\n" + card + "\n" + keysTickets);
            cardToolTip.setId("toolTip");
            cardPane.setOnMouseMoved(event -> {
                cardToolTip.show(cardPane, event.getScreenX() + 10, event.getScreenY() + 20);
            });
            cardPane.setOnMouseExited(event -> {
                cardToolTip.hide();
            }); 
            indicatorTray.getChildren().add(cardPane);
        }
        //BL4 Phosphene indicator
        if (!phosphene.isEmpty()) {
            ImageView phospheneImageView = new ImageView(App.phospheneImage);
            Tooltip phospheneToolTip = new Tooltip("Phosphene\n" + phosphene);
            phospheneToolTip.setId("toolTip");
            phospheneImageView.setOnMouseMoved(event -> {
                phospheneToolTip.show(phospheneImageView, event.getScreenX() + 10, event.getScreenY() + 20);
            });
            phospheneImageView.setOnMouseExited(event -> {
                phospheneToolTip.hide();
            }); 
            indicatorTray.getChildren().add(phospheneImageView);
        }

        indicatorTray.setSpacing(5);

        VBox indicatorTrayVBox = new VBox(indicatorTray);
        indicatorTrayVBox.setFillWidth(false);
        indicatorTrayVBox.setId("indicatorTrayVBox");

        ScrollPane itemTextScrollPane = new ScrollPane(itemTextVBox);
        itemTextScrollPane.setId("itemTextScrollPane");
        
        VBox itemVBox = new VBox(topHBox, itemNameLabel, itemImageStackPane, itemTextScrollPane, indicatorTrayVBox);
        itemVBox.setId("itemVBox");
        VBox.setMargin(itemNameLabel, new Insets(5, 0 ,0, 0));
        VBox.setMargin(itemImageStackPane, new Insets(1, 0 ,0, 0));
        // VBox.setMargin(indicatorTray, new Insets(0, 9 ,0, 10));
        itemPane.getChildren().add(itemVBox);
        itemPane.setId("itemPane");
        itemPane.setCache(true);
        itemPane.setCacheHint(CacheHint.SPEED);
        itemPane.setVisible(true);
        return itemPane;
    }
}
