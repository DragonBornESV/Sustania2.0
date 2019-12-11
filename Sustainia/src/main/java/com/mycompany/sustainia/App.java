package com.mycompany.sustainia;

import com.mycompany.sustainia.GUI.*;
// Standert javaFX imports
import javafx.application.Application;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
// import javafx.scene.input.KeyCode;

// Specific to image loading imports
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

// for key presses

import static javafx.application.Application.launch;

import javafx.geometry.Rectangle2D;

import java.text.DecimalFormat;
import java.util.ArrayList;

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
    static TextBox textBox;
    
    public App(){
        game.createRooms();        
    }
    
    @Override
    public void start(Stage stage) throws FileNotFoundException {
        
        // black background image

        FileInputStream inputBackground = new FileInputStream("Sustainia/img/background.png");

        Image backgroundImage = new Image(inputBackground, 600, 600, true, false);

        // Creates a new image, from the selected parth on computer
        FileInputStream inputCharacter = new FileInputStream("Sustainia/img/ch.png");
        characterImage = new Image(inputCharacter,128*World.scale,128*World.scale,true,false);

        // Gets the image of the items

        inputItems = new FileInputStream("Sustainia/img/items.png");

        itemsImage = new Image(inputItems,160*World.scale,16*World.scale,true,false);
        
        // NPC image
        
        
        // Rooms
        FileInputStream inputRooms = new FileInputStream("Sustainia/img/rooms.png");
        Image roomsImage = new Image(inputRooms,1120*World.scale,1188*World.scale,true,false);
        // RoomsTop
        FileInputStream inputRoomsTop = new FileInputStream("Sustainia/img/roomsTop.png");
        Image roomsTopImage = new Image(inputRoomsTop,1120*World.scale,1188*World.scale,true,false);

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

        //Creating a stackPane to insert a TerminalBox/TextBox onto the gameWindow
        StackPane gamePanel = new StackPane();
        //Adding the Game to the StackPane.
        gamePanel.getChildren().add(root);
        //Creating a TextBox/TerminalBox
        textBox = new TextBox();
        //Adding the TextBox/TerminalBox to the StackPane.
        gamePanel.getChildren().add(textBox.getGridPane());


        //Creating a gridPane to make a splitview for the Window with 2 cells.
        GridPane splitView = new GridPane();
        //Arranging the size of the 2 Columns.
        splitView.getColumnConstraints().add(new ColumnConstraints(801));
        splitView.getColumnConstraints().add(new ColumnConstraints(300));
        //Adding the Game into the left column at cell (0, 0).
        splitView.add(gamePanel, 0, 0);

        //creating a gridPane for the right column which is the UI Panel, that allows the user to see their progress, display between Inventory and Materials.
        GridPane rightColumn = new GridPane();
        //Arranging the size of the column/row cells.
        rightColumn.getRowConstraints().add(new RowConstraints(230));
        rightColumn.getRowConstraints().add(new RowConstraints(320));
        rightColumn.getRowConstraints().add(new RowConstraints(50));
        rightColumn.getColumnConstraints().add(new ColumnConstraints(300));


        StackPane cover = new StackPane();
        Rectangle coverBox = new Rectangle(300, 600);
        coverBox.setFill(Color.WHITE);
        cover.getChildren().add(coverBox);

        cover.getChildren().add(rightColumn);

        //Adding the Panel into the right column at cell (1,0)
        splitView.add(cover,1,0);
        //Creating all the parameters(Domain)
        Parameter.createParameters();
        //Creating all GUI Parameters(GUI/Presentation)
        ParameterPanel.createParameterPanel();
        //Inserting all the GUI Parameters into the Panel
        ParameterPanel.insertParametersIntoPanel();
        //Inserting the GridPane (who holds all the GUI Parameters) into the rightColumnPanel
        rightColumn.add(ParameterPanel.parameterGridpane, 0,0);

        //Inventory Panel is added to rightColumn
        InventoryPanel invPanel = new InventoryPanel(game.getInventory().getItemsInInventory());
        rightColumn.add(invPanel.getGridPane(),0,1);
        //Materials Panel is created
        MaterialsPanel matPanel = new MaterialsPanel();

        //Creates ButtonPanel which contains all the buttons
        ButtonPanel buttonPanel = new ButtonPanel();
        //Adding all the Buttons to the UI Panel into cell (0, 2).
        rightColumn.add(buttonPanel.getGridPaneButtons(),0,2);

        //Assigning the button "Material" to remove the InvPanel and add MatPanel into the cell the whose InvPanel was removed from.
        buttonPanel.getMaterialsButton().setOnAction(event -> {
            rightColumn.getChildren().remove(invPanel.getGridPane());
            rightColumn.add(matPanel.getGridPane(),0,1);
        });
        //Assigning the button "Inventory" to remove the MatPanel and add InvPanel into the cell the whose MatPanel was removed from.
        buttonPanel.getInventoryButton().setOnAction(event -> {
            rightColumn.getChildren().remove(matPanel.getGridPane());
            rightColumn.add(invPanel.getGridPane(),0,1);
        });

        //Created StartMenu to display Start screen when game starts
        StartMenu startMenu = new StartMenu();

        //Creating a scene object 
        Scene scene = new Scene(splitView, World.gameScreenWidth+301, World.gameScreenHeight);
        Scene start = new Scene(startMenu.getStartMenu(), World.gameScreenWidth+301, World.gameScreenHeight);

        startMenu.getStartButton().setOnAction(event -> {
            //possible to use player name for the rest of the game
            Game.name = startMenu.getPlayerName().getText();
            stage.setScene(scene);
            System.out.println(game.name);
        });

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
                    //Leave conversation
                    case L: leaveConvo(); break;
                    //Conversation responses
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
        stage.setScene(start);
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
            
            npcImageView.setX(game.currentRoom.getNPC().getNpcX()*World.scale);
            npcImageView.setY(game.currentRoom.getNPC().getNpcY()*World.scale);
            
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