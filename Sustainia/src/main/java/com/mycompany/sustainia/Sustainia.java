package com.mycompany.sustainia;

import java.awt.*;
import java.awt.event.*;

public class Sustainia {

    public static void main (String[] args){
       //boolean done = false;
       //while (!done){
       App.runApp(args);
       Game game = new Game();
       game.play();
       //}
    }

}

