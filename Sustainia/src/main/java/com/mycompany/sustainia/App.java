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
import java.util.ArrayList;
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

// import java.awt.event.KeyEvent;

/**
 * JavaFX App
 */
public class App extends Application {
    Game game = new Game();
    //  Collision col = new Collision(wo);
    
    int cW = 320;
    int cH = 320;
    boolean goNorth = false;
    boolean goSouth = false;
    boolean goEast  = false;
    boolean goWest  = false;
    boolean moving  = false;
    int facing = 0;
    int animationTimer = 0;
    private ImageView rooms;
    private ImageView streetTop;
    private ImageView character;
    private ArrayList<ImageView> items;
    private Group itemsGroup = new Group();   //All the visible items are stored here
    private Group imageGroup;
    
    
    @Override
    public void start(Stage stage) throws FileNotFoundException {
        //Creates the rooms, etc...
        game.play();
        
        // Creates a new image, from the selected parth on computer
        FileInputStream inputCharacter = new FileInputStream("img\\ch.png");
        Image characterImage = new Image(inputCharacter,1280,1280,true,false);
        
        // Streets
        FileInputStream inputRooms = new FileInputStream("img\\rooms.png");
        Image roomsImage = new Image(inputRooms,1120*4,1188*4,true,false);
        FileInputStream inputStreetsTop = new FileInputStream("img\\street_top.png");
        Image streetsTopImage = new Image(inputStreetsTop,1120*4,770*4,true,false);        
        
        //Setting the image view
        rooms = new ImageView(roomsImage);
        rooms.setViewport(new Rectangle2D(0, 0, 1120*4, 770*4));
        character = new ImageView(characterImage);
        character.setViewport(new Rectangle2D(0, 0, cW, cH));
        streetTop = new ImageView(streetsTopImage);
        
        //All the non-moving elements of the scene
        imageGroup = new Group(rooms, streetTop, itemsGroup);
        itemsGroup.toFront();
        
        //Setting the position of the image 
        this.character.setX(World.characterX);
        this.character.setY(World.characterY);
        
        //setting the fit height and width of the image view 
        this.rooms.setFitWidth(1120*4);
        this.rooms.setFitHeight(1188*4);
        
        this.character.setFitWidth(cW*0.1*4);
        this.character.setFitHeight(cH*0.1*4);
        
        this.streetTop.setFitWidth(1120*4);
        this.streetTop.setFitHeight(770*4);
        
        //Setting the preserve ratio of the image view 
        this.rooms.setPreserveRatio(true);
        this.character.setPreserveRatio(true);
        this.streetTop.setPreserveRatio(true);
        
        loadItems();
        
        //Creating a Group object  
        Group root = new Group(imageGroup, this.character);
        
        //Creating a scene object 
        Scene scene = new Scene(root, World.gameScreenWidth, World.gameScreenHeight);
        
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
        drawRoom(game.currentRoom);
    }
    
    private void characterAnimation() {
        AnimationTimer timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    int dx = 0, dy = 0;

                    if      (goNorth) {dy += World.characterMovementSpeedV; facing = 0; moving = true;}
                    else if (goSouth) {dy -= World.characterMovementSpeedV; facing = 1; moving = true;}
                    else if (goEast)  {dx -= World.characterMovementSpeedH; facing = 2; moving = true;}
                    else if (goWest)  {dx += World.characterMovementSpeedH; facing = 3; moving = true;}
                    else    { moving = false;}

                    if (moving) animationTimer ++;

                    moveCharacter(moving, goNorth, goSouth, goEast, goWest, dx, dy, animationTimer, facing);

                }
            };

        timer.start();
    }
    
    private void moveCharacter (boolean moving, boolean goNorth, boolean goSouth, boolean goEast, boolean goWest, int dx, int dy, int at, int facing){
        
        dx = game.collisionDetectionX(dx);
        dy = game.collisionDetectionY(dy);
        
        World.gameX += dx;
        World.gameY += dy;
        
        //Moves all the images
        imageGroup.setTranslateX(World.gameX);
        imageGroup.setTranslateY(World.gameY);
        
        // The games cordinants are needet to position the collision
        game.collisionWithObjects(World.gameX, World.gameY);
        
        // character_animation
        if (moving) {
            if (goNorth){
                character.setViewport(new Rectangle2D(cW*(int)((at/10)%4), cH*3, cW, cH));
            }
            else if (goSouth){
                character.setViewport(new Rectangle2D(cW*(int)((at/10)%4), cH*0, cW, cH));
            }
            else if (goEast){
                character.setViewport(new Rectangle2D(cW*(int)((at/10)%4), cH*2, cW, cH));
            }
            else if (goWest){
                character.setViewport(new Rectangle2D(cW*(int)((at/10)%4), cH*1, cW, cH));
            }
        } else {
            if (facing == 0){
                character.setViewport(new Rectangle2D(0, cH*3, cW, cH));
            }
            else if (facing == 1){
                character.setViewport(new Rectangle2D(0, cH*0, cW, cH));
            }
            else if (facing == 2){
                character.setViewport(new Rectangle2D(0, cH*2, cW, cH));
            }
            else if (facing == 3){
                character.setViewport(new Rectangle2D(0, cH*1, cW, cH));
            }
        }
        
    }
    
    /**
     * Generates the items from the current room and places them visually in the game.
     */
    private void loadItems() throws FileNotFoundException {
        //Clears all the previous items.
        itemsGroup.getChildren().clear();
        
        FileInputStream inputItems = new FileInputStream("img\\items.png");
        
        Image itemsImage = new Image(inputItems,160*4,16*4,true,false);
        
        //The items are put into an array of images
        items = new ArrayList<ImageView>();
        
        //roomItems are now the items in the room
        ArrayList<Item> roomItems = game.currentRoom.getItemsInRoom();
        
        for (int i = 0; i < roomItems.size(); i++) {
            //Sets the image to the item
            ImageView tempItem = new ImageView(itemsImage);
            //Uses the correct image by using the itemsImage number.
            tempItem.setViewport(new Rectangle2D(roomItems.get(i).itemImage*16*4, 0, 16*4, 16*4));
            
            //Sets the image of the items to the correct location in the scene.
            tempItem.setX(roomItems.get(i).getItemX());
            tempItem.setY(roomItems.get(i).getItemY());
            //tempItem.setY(roomItems.get(i).getItemY());
            
            //Adds the imageView of the item to the list.
            //These will be added to the group later.
            items.add(tempItem);
            
            System.out.println(roomItems.get(i).name + ": " + tempItem.getX() + ", " + tempItem.getY());
        }
        
        //Adds all the new items to the group
        itemsGroup.getChildren().addAll(items);
    }
    
    private void drawRoom(Room currentRoom){
        if (currentRoom.equals(game.streets)){
            World.gameX = -game.currentRoom.spawnPX*4 + World.characterX -64;
            World.gameY = -game.currentRoom.spawnPY*4 + World.characterY;
            this.rooms.setFitWidth(1120*4);
            this.rooms.setFitHeight(770*4);
            rooms.setViewport(new Rectangle2D(0, 0, 1120*4, 770*4));
        } else {
            World.gameX = -game.currentRoom.spawnPX*4 + World.gameScreenWidth/2;
            World.gameY = -game.currentRoom.spawnPY*4 + World.gameScreenHeight/2 +64;
            this.rooms.setFitWidth(256*4);
            this.rooms.setFitHeight(209*4);
            
            if (currentRoom.equals(game.townHall)){
                rooms.setViewport(new Rectangle2D(0, 770*4, 256*4, 209*4));
            } else if (currentRoom.equals(game.nonsustainableHouse)){
                rooms.setViewport(new Rectangle2D(256*4, 770*4, 256*4, 209*4));
            } else if (currentRoom.equals(game.park)){
                rooms.setViewport(new Rectangle2D(512*4, 770*4, 256*4, 209*4));
            } else if (currentRoom.equals(game.bank)){
                rooms.setViewport(new Rectangle2D(768*4, 770*4, 256*4, 209*4));
            } else if (currentRoom.equals(game.clothingFactory)){
                rooms.setViewport(new Rectangle2D(0, 979*4, 256*4, 209*4));
            }
        }
    }
    
    public static void runApp(String[] args) {
        launch();
    }

}