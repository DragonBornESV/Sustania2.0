package com.mycompany.sustainia;

// Standert javaFX imports
import javafx.application.Application;
import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;

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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Region;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Control;

import javafx.scene.control.Labeled;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

import javafx.scene.Parent;


import javafx.scene.layout.ConstraintsBase;
import javafx.scene.layout.ColumnConstraints;

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
    
    public App(){
        game.createRooms();        
    }
    
    @Override
    public void start(Stage stage) throws FileNotFoundException {
        
        // black background image
        FileInputStream inputBackground = new FileInputStream("img\\background.png");
        Image backgroundImage = new Image(inputBackground, 600, 600, true, false);

        // Creates a new image, from the selected parth on computer
        FileInputStream inputCharacter = new FileInputStream("img\\ch.png");
        Image characterImage = new Image(inputCharacter,128*World.scale,128*World.scale,true,false);
        
    // Rooms
        FileInputStream inputRooms = new FileInputStream("img\\rooms.png");
        Image roomsImage = new Image(inputRooms,1120*World.scale,1188*World.scale,true,false);
    // RoomsTop
        FileInputStream inputRoomsTop = new FileInputStream("img\\roomsTop.png");
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
        this.background.setPreserveRatio(true);
        this.rooms.setPreserveRatio(true);
        this.character.setPreserveRatio(true);
        this.roomsTop.setPreserveRatio(true);
        
        //Creating a Group object  
        Group root = new Group(this.background, this.rooms, this.character, this.roomsTop);
        
        Text text = new Text("  baby Yoda \n  will save \n  us all");
        text.setFont(new Font(50));
        
        GridPane gridpane = new GridPane();
        gridpane.getColumnConstraints().add(new ColumnConstraints(601));
        gridpane.getColumnConstraints().add(new ColumnConstraints(300));
        gridpane.add(root, 0, 0);
        gridpane.add(text, 1, 0);
        
        //Creating a scene object 
        Scene scene = new Scene(gridpane, World.gameScreenWidth+301, World.gameScreenHeight);
        
// KEYS pressed
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                switch(e.getCode()){
                    case W: goNorth = true; break;
                    case S: goSouth = true; break;
                    case D: goEast  = true; break;
                    case A: goWest  = true; break;
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
                    game.currentRoom = game.newRoom(World.gameX, World.gameY, game.currentRoom, game.streets);
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
    }
    

    public static void runApp(String[] args) {
        launch();
    }

}