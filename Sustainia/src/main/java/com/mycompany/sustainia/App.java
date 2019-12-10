package com.mycompany.sustainia;

// Standert javaFX imports
import javafx.application.Application;
import javafx.animation.AnimationTimer;
import javafx.geometry.*;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
// import javafx.scene.input.KeyCode;

// Specific to image loading imports
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

// for key presses

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.lang.Object;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javafx.scene.Node;
import javafx.scene.Parent;

import javafx.scene.text.Text;
import javafx.scene.text.Font;

import javafx.scene.Parent;
import javafx.scene.shape.Rectangle;

// import java.awt.event.KeyEvent;

/**
 * JavaFX App
 */
public class App extends Application {
    Game game = new Game();

    boolean goNorth = false;
    boolean goSouth = false;
    boolean goEast  = false;
    boolean goWest  = false;
    boolean moving  = false;
    
    boolean startingGame = true;
    
    boolean newRoom = true;

    int facing = 0;
    int animationTimer = 0;

    //For items
    private ArrayList<ImageView> items;
    private Group itemsGroup = new Group();   //All the visible items are stored here
    
    FileInputStream inputItems;
    Image itemsImage;
    Image characterImage;
    
    /*
     * the following variables defines the rectangle, witch the rooms are maped to.
     * Depending on the room, the starting position of the rectangels X and Y cordinats need to be difrent, since all of the rooms are lacated on the same image.
     */
    int rectX;
    int rectY;
    int rectWidth;
    int rectHeight;
    
    // roomX and roomY are the variables, that are yosed by each room to determen where on the "room" image their upper left corner is located.
    int roomX;
    int roomY;
    
    /*
     * The variabels imageX and imageY, defines where from the images left corner is drawn.
     * If the character nears the left ore upper barrier of the current room, the point of where the image is drawn needs to change to acomidate.
     */
    int imageX = 0;
    int imageY = 0;
    
    private ImageView background;
    private ImageView rooms;
    private ImageView roomsTop;
    private ImageView character;
    
    //DialogBox
    TextBox textBox;
    
    public App(){
        game.createRooms();        
    }
    
    @Override
    public void start(Stage stage) throws FileNotFoundException {
        
        // black background image

        FileInputStream inputBackground = new   FileInputStream("Sustainia//img//background.png");

        Image backgroundImage = new Image(inputBackground, 600, 600, true, false);

        // Creates a new image, from the selected parth on computer
        FileInputStream inputCharacter = new FileInputStream("Sustainia//img//ch.png");
        characterImage = new Image(inputCharacter,128*World.scale,128*World.scale,true,false);

        // Gets the image of the items

        inputItems = new FileInputStream("Sustainia//img//items.png");

        itemsImage = new Image(inputItems,160*World.scale,16*World.scale,true,false);
        
        // NPC image
        
        
        // Rooms
        FileInputStream inputRooms = new FileInputStream("Sustainia//img//rooms.png");
        Image roomsImage = new Image(inputRooms,1120*World.scale,1188*World.scale,true,false);
        // RoomsTop
        FileInputStream inputRoomsTop = new FileInputStream("Sustainia//img//roomsTop.png");
        Image roomsTopImage = new Image(inputRoomsTop,1120*World.scale,1188*World.scale,true,false);

        FileInputStream startImage = new FileInputStream("Sustainia//img//sustainia.png");

        Image startScreen = new Image(startImage, 900, 600, true, false);
        ImageView view = new ImageView(startScreen);

        StackPane startPane = new StackPane();
        startPane.getChildren().add(view);
        startPane.setAlignment(Pos.CENTER);

        //Setting the image view
        this.background = new ImageView(backgroundImage);
        this.rooms = new ImageView(roomsImage);
        this.character = new ImageView(characterImage);
        character.setViewport(new Rectangle2D(0, 0, World.characterWidth, World.characterHeight));
        this.roomsTop = new ImageView(roomsTopImage);
        
        //Setting the position of the image 
        this.background.setX(0);
        this.background.setY(0);
        
        this.background.setFitWidth(World.gameScreenWidth);
        this.background.setFitHeight(World.gameScreenHeight);
        
        this.rooms.setX(imageX);
        this.rooms.setY(imageY);
        
        this.character.setX(World.characterX -World.characterWidth/2);
        this.character.setY(World.characterY -World.characterHeight/2);
        
        this.roomsTop.setX(imageX);
        this.roomsTop.setY(imageY);
        
        //setting the fit height and width of the image view 
        this.rooms.setFitWidth(World.gameScreenWidth);
        this.rooms.setFitHeight(World.gameScreenHeight);
        
        this.character.setFitWidth(World.characterWidth);
        this.character.setFitHeight(World.characterHeight);
        
        this.roomsTop.setFitWidth(World.gameScreenWidth);
        this.roomsTop.setFitHeight(World.gameScreenHeight);

        
        //Setting the preserve ratio of the image view 
        //this.background.setPreserveRatio(true);
        this.rooms.setPreserveRatio(true);
        this.character.setPreserveRatio(true);
        this.roomsTop.setPreserveRatio(true);

        //Loads the items into the game view
        loadItems();
        
        //Creating a Group object
        Group root = new Group(this.background, this.rooms, this.itemsGroup, this.character, this.roomsTop);

        itemsGroup.setManaged(false);
        root.setManaged(false);

        StackPane game1 = new StackPane();
        game1.getChildren().add(root);
        textBox = new TextBox();
        game1.getChildren().add(textBox.getGridPane());



        GridPane gridpane = new GridPane();
        gridpane.getColumnConstraints().add(new ColumnConstraints(801));
        gridpane.getColumnConstraints().add(new ColumnConstraints(300));
        gridpane.add(game1, 0, 0);

        //creating a gridpane for RightPanel
        GridPane rightColumn = new GridPane();
        rightColumn.getRowConstraints().add(new RowConstraints(230));
        rightColumn.getRowConstraints().add(new RowConstraints(320));
        rightColumn.getRowConstraints().add(new RowConstraints(50));
        rightColumn.getColumnConstraints().add(new ColumnConstraints(300));
        gridpane.add(rightColumn,1,0);
        gridpane.setGridLinesVisible(false);
        rightColumn.setGridLinesVisible(false);


        //ParameterBar
        Text navn = new Text("Parameter");
        navn.setFont(new Font(15));

        Parameter.createParameters();

        //
        ParameterPanel p1 = new ParameterPanel("City Equality");
        ParameterPanel p2 = new ParameterPanel("City Green Energy");
        ParameterPanel p3 = new ParameterPanel("City Clean Water");
        ParameterPanel p4 = new ParameterPanel("Sustainable Housing");
        ParameterPanel p5 = new ParameterPanel("City Clean Air");
        ParameterPanel p6 = new ParameterPanel("City Cleanliness");
        ParameterPanel p7 = new ParameterPanel("City Security");


        //creating a gridpane to hold and display all parameters
        Text titelParamater = new Text("Parameter");
        titelParamater.setFont(new Font(20));
        GridPane parameterGridpane = new GridPane();
        parameterGridpane.getColumnConstraints().add(new ColumnConstraints(300));
        parameterGridpane.setVgap(2);
        parameterGridpane.add(titelParamater, 0 ,0);
        parameterGridpane.setHalignment(titelParamater, HPos.CENTER);
        parameterGridpane.add(ParameterPanel.mainBar.getStackPane(),0,1);

        //Adding all of the Parameters to the gridpane to display them.
        for (int i = 0; i < ParameterPanel.list.size(); i++) {

            parameterGridpane.add(ParameterPanel.list.get(i).getStackPane(),0,i+2);
        }

        rightColumn.add(parameterGridpane, 0,0);

        //Inventory Panel is added to rightColumn
        FileInputStream imageYoda = new FileInputStream("Sustainia\\img\\babyyoda.png");
        ImageView babyYoda = new ImageView(new Image(imageYoda,100, 100, true,false));
        FileInputStream imageYoda1 = new FileInputStream("Sustainia\\img\\babyyodasoup.png");
        ImageView babyYoda1 = new ImageView(new Image(imageYoda1,100, 100, true,false));
        InventoryPanel invPanel = new InventoryPanel(game.getInventory().getItemsInInventory());
        rightColumn.add(invPanel.getGridPane(),0,1);





        Button yodaButton = new Button("Add Baby Yoda");
        yodaButton.setOnAction(actionEvent -> {

        });
        Button removeYodaButton = new Button("Remove Selected Baby Yoda");
        removeYodaButton.setOnAction(actionEvent -> {


        });

        //Materials Panel is created
        MaterialsPanel matPanel = new MaterialsPanel();

        //Creating a gridpane for buttons to be placed in
        GridPane containButtons = new GridPane();
        containButtons.getRowConstraints().add(new RowConstraints(100));
        containButtons.add(yodaButton,2,0);
        containButtons.add(removeYodaButton,3,0);

        rightColumn.add(containButtons,0,2);



        //Buttons to swap between panels
        Button materialsButton = new Button("Materials");
        Button inventoryButton = new Button("Inventory");
        containButtons.add(inventoryButton, 0,0);
        containButtons.add(materialsButton,1,0);
        containButtons.setHgap(10);
        materialsButton.setOnAction(event -> {
            rightColumn.getChildren().remove(invPanel.getGridPane());
            rightColumn.add(matPanel.getGridPane(),0,1);
        });
        inventoryButton.setOnAction(event -> {
            rightColumn.getChildren().remove(matPanel.getGridPane());
            rightColumn.add(invPanel.getGridPane(),0,1);
        });



        //Creating a scene object 
        Scene scene = new Scene(gridpane, World.gameScreenWidth+301, World.gameScreenHeight);
        Scene start = new Scene(startPane, World.gameScreenWidth+301, World.gameScreenHeight);
        //Start screen button
        Button startButton = new Button("Start");
        startButton.setMinSize(200, 100);
        startButton.setOnAction(event -> {
            stage.setScene(scene);
        });

        startPane.getChildren().add(startButton);
// KEYS pressed
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                switch(e.getCode()){
                    case W: goNorth = true; break;
                    case S: goSouth = true; break;
                    case D: goEast  = true; break;
                    case A: goWest  = true; break;
                    
                    //Detects the drop-key 'Q'
                    //Just for now, the item to be dropped is always the first item
                    case Q: dropItem(); break;
                    //Leave convo
                    case L: leaveConvo(); break;
                    //Convo responses
                    case DIGIT1: game.currentRoom.getNPC().getCurrentSay().setChosenResponse(1); break;
                    case DIGIT2: game.currentRoom.getNPC().getCurrentSay().setChosenResponse(2); break;
                    case DIGIT3: game.currentRoom.getNPC().getCurrentSay().setChosenResponse(3); break;
                    case DIGIT4: game.currentRoom.getNPC().getCurrentSay().setChosenResponse(4); break;
                }
            }
        });

// KEYS pressed        
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                switch(e.getCode()){
                    case W: goNorth = false; break;
                    case S: goSouth = false; break;
                    case D: goEast  = false; break;
                    case A: goWest  = false; break;
                }
            }
        });
        
        //Setting title to the Stage 
        stage.setTitle("Moving Image Test");
        
        //Adding scene to the stage        
        stage.setScene(scene);
        //Displaying the contents of the stage
        stage.show();
        characterAnimation();
    }

    public void update(){
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        double value = 0;
        for (ParameterPanel p: ParameterPanel.list) {

            p.getProgressBar().setProgress(Parameter.parameterList.get(p.getParameterName()).getScore()/100);
            p.getProgressText().setText((Parameter.parameterList.get(p.getParameterName()).getScore())+"%");
            value += Parameter.parameterList.get(p.getParameterName()).getScore();
        }
        ParameterPanel.mainBar.getProgressBar().setProgress((value/7)/100);
        ParameterPanel.mainBar.getProgressText().setText(numberFormat.format(value/7)+"%");

        //Checks if dialog is running
        if (game.currentRoom.getNPC().isDialogRunning()) {
            textBox.setTextBox(game.currentRoom.getNPC().getAllText());
        }

    }
    
    private void characterAnimation() {
        AnimationTimer timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    int dx = 0, dy = 0;

                    if      (goNorth) {dy -= World.characterMovementSpeedV; facing = 0; moving = true;}
                    else if (goSouth) {dy += World.characterMovementSpeedV; facing = 1; moving = true;}
                    else if (goEast)  {dx += World.characterMovementSpeedH; facing = 2; moving = true;}
                    else if (goWest)  {dx -= World.characterMovementSpeedH; facing = 3; moving = true;}
                    else    { moving = false;}

                    if (moving) animationTimer ++;
                    
                    // All the metods, that need to be updatet during runtime are called here.
                    moveCharacter(moving, goNorth, goSouth, goEast, goWest, dx, dy, animationTimer, facing);
                    drawRoom(game.currentRoom);

                    game.currentRoom = game.roomChangeCheck(World.gameX, World.gameY);
                    //System.out.println(game.currentRoom.name);
                    update();
                    Parameter.mapAddScore("City Equality", 1);

                }
            };

        timer.start();
    }
    
    private void moveCharacter (boolean moving, boolean goNorth, boolean goSouth, boolean goEast, boolean goWest, int dx, int dy, int at, int facing){
        
        dx = game.collisionDetectionX(dx);
        dy = game.collisionDetectionY(dy);
        
        World.gameX += dx;
        World.gameY += dy;

        // The games cordinants are needet to position the collision.... If this function is not called, the game will run without collision.
        game.collisionWithObjects(World.gameX, World.gameY, game.currentRoom);

        //Updates the items if it needs it
        if (game.needsUpdate()) {
            loadItems();
            game.setNeedsUpdate(false);
        }

        // character_animation
        if (moving) {
            if (goNorth){
                character.setViewport(new Rectangle2D(World.characterWidth*(int)((at/10)%4), World.characterHeight*3, World.characterWidth, World.characterHeight));
            }
            else if (goSouth){
                character.setViewport(new Rectangle2D(World.characterWidth*(int)((at/10)%4), World.characterHeight*0, World.characterWidth, World.characterHeight));
            }
            else if (goEast){
                character.setViewport(new Rectangle2D(World.characterWidth*(int)((at/10)%4), World.characterHeight*2, World.characterWidth, World.characterHeight));
            }
            else if (goWest){
                character.setViewport(new Rectangle2D(World.characterWidth*(int)((at/10)%4), World.characterHeight*1, World.characterWidth, World.characterHeight));
            }
        } else {
            if (facing == 0){
                character.setViewport(new Rectangle2D(0, World.characterHeight*3, World.characterWidth, World.characterHeight));
            }
            else if (facing == 1){
                character.setViewport(new Rectangle2D(0, World.characterHeight*0, World.characterWidth, World.characterHeight));
            }
            else if (facing == 2){
                character.setViewport(new Rectangle2D(0, World.characterHeight*2, World.characterWidth, World.characterHeight));
            }
            else if (facing == 3){
                character.setViewport(new Rectangle2D(0, World.characterHeight*1, World.characterWidth, World.characterHeight));
            }
        }
        
    }
    private void drawRoom(Room currentRoom){
        World.gameX = game.getSpawnPointX(World.gameX, currentRoom, game.previousRoom);
        World.gameY = game.getSpawnPointY(World.gameY, currentRoom, game.previousRoom);
        
        if (currentRoom.equals(game.streets)){
           roomX = 0;
           roomY = 0;
            if (World.streetRoomWidth -World.gameX < World.gameScreenWidth){
                rectWidth = World.streetRoomWidth - World.gameX;
            } else {
                rectWidth = World.gameScreenWidth;
            }
            if (World.streetRoomHeight -World.gameY < World.gameScreenHeight){
                rectHeight = World.streetRoomHeight - World.gameY;
            } else {
                rectHeight = World.gameScreenHeight;
            }
            
        } else {
            if (currentRoom.equals(game.townHall)){
                roomX = 0;
                roomY = 770*World.scale;
                game.previousRoom = 0;
            } else if (currentRoom.equals(game.nonsustainableHouse)){
                roomX = 256*World.scale;
                roomY = 770*World.scale;
                game.previousRoom = 1;
            } else if (currentRoom.equals(game.park)){
                roomX = 512*World.scale;
                roomY = 770*World.scale;
                game.previousRoom = 2;
            } else if (currentRoom.equals(game.bank)){
                roomX = 768*World.scale;
                roomY = 770*World.scale;
                game.previousRoom = 3;
            } else if (currentRoom.equals(game.clothingFactory)){
                roomX = 0;
                roomY = 979*World.scale;
                game.previousRoom = 4;
            } else if (currentRoom.equals(game.policeStation)){
                roomX = 256*World.scale;
                roomY = 979*World.scale;
                game.previousRoom = 5;
            } else if (currentRoom.equals(game.recyclingStation)){
                roomX = 512*World.scale;
                roomY = 979*World.scale;
                game.previousRoom = 6;
            } else if (currentRoom.equals(game.school)){
                roomX = 768*World.scale;
                roomY = 979*World.scale;
                game.previousRoom = 7;
            }
            
            if (World.roomWidth - World.gameX < World.gameScreenWidth){
                rectWidth = World.roomWidth - World.gameX;
            } else {
                rectWidth = World.gameScreenWidth;       
            }
            
            if (World.roomHeight -World.gameY < World.gameScreenHeight){
                rectHeight = World.roomHeight - World.gameY;
            } else {
                rectHeight = World.gameScreenHeight;
            }
        }
        
        if (World.gameX < 0) {
            rectX = roomX;
            imageX = -World.gameX;
            rectWidth = World.gameScreenWidth + World.gameX;
        } else {
            rectX = roomX +World.gameX;
            imageX = 0;
        }
        if (World.gameY < 0) {
            rectY = roomY;
            imageY = -World.gameY;
            rectHeight = World.gameScreenHeight + World.gameY;
        } else {
            rectY = roomY + World.gameY;
            imageY = 0;
        }
        
        rooms.setViewport(new Rectangle2D(rectX, rectY, rectWidth, rectHeight));
        roomsTop.setViewport(new Rectangle2D(rectX, rectY, rectWidth, rectHeight));
        
        this.rooms.setFitWidth(rectWidth);
        this.rooms.setFitHeight(rectHeight);
        this.rooms.setX(imageX);
        this.rooms.setY(imageY);
        
        this.roomsTop.setFitWidth(rectWidth);
        this.roomsTop.setFitHeight(rectHeight);
        this.roomsTop.setX(imageX);
        this.roomsTop.setY(imageY);
        this.itemsGroup.setTranslateX(-World.gameX);
        this.itemsGroup.setTranslateY(-World.gameY);
        
    }

    private void loadItems() {
        //Clears all the previous items.
        itemsGroup.getChildren().clear();
        
        //The items are put into an array of images
        items = new ArrayList<ImageView>();
        
        //roomItems are now the items in the room
        ArrayList<Item> roomItems = game.currentRoom.getItemsInRoom();
        
        for (int i = 0; i < roomItems.size(); i++) {
            //Sets the image to the item
            ImageView tempItem = new ImageView(itemsImage);
            //Shows the correct sprite by using the itemsImage number.
            tempItem.setViewport(new Rectangle2D(roomItems.get(i).imageNumber*16*World.scale, 0, 16*World.scale, 16*World.scale));

            roomItems.get(i).image = tempItem;

            //Sets the image of the items to the correct location in the scene.
            tempItem.setX(roomItems.get(i).getItemX());
            tempItem.setY(roomItems.get(i).getItemY());
            //tempItem.setY(roomItems.get(i).getItemY());
            
            //Adds the imageView of the item to the list.
            //These will be added to the group later.'
            items.add(tempItem);
            
            //This can be used to show the hitboxes.
            //roomItems.get(i).printPosition();
        }
        
        //Load NPC
        if (game.currentRoom.hasNPC()){
            ImageView npcImageView = new ImageView(characterImage);
            npcImageView.setViewport(new Rectangle2D(0, 0, World.characterWidth, World.characterHeight));
            
            npcImageView.setX(game.currentRoom.getNPC().getNpcX());
            npcImageView.setY(game.currentRoom.getNPC().getNpcY());
            
            itemsGroup.getChildren().add(npcImageView);
        }
        
        //Adds all the new items to the group
        itemsGroup.getChildren().addAll(items);
    }
    
    /**
     * Tells the game to drop an item. Right now it's the first item in the
     * inventory.
     * 
     * This method can be used by the press of the button Q or the drop button
     * on screen.
     */
    private void dropItem() {
        game.dropItem(game.getInventory().getItemsInInventory().get(0));
    }
    
    private void leaveConvo(){
        //wantToLeave == true;
    }
    
    public static void runApp(String[] args) {
        launch();
    }

}