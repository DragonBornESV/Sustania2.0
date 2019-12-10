package com.mycompany.sustainia;


/**This class contains dialog elements used by the NPC class. 
 * 
 * The class contains a message from the NPC, the available responses and the
 * different persuasion points given by each response. The class also handles
 * the printing of dialog and the choosing of responses.
 */
public class Say {
    private String npcText;
    private String[] responses;
    private int[] persuasionPoints;
    private int chosenResponse = 0;
    
    private boolean wantToLeave = false;
    
    
    /**The constructor for instantiating an instans of the Say class.
     * 
     * @param npcText           The initial message from the NPC.
     * @param responses         A string-array of available responses.
     * @param persuasionPoints  The points available, when choosing the responses above.
     */
    Say(String npcText, String[] responses, int[] persuasionPoints) {
        this.npcText = npcText;
        this.responses = responses;
        this.persuasionPoints = persuasionPoints;
        }
    
    /**Prints the message from the NPC and displays the available responses and
     * the corresponding numbers. It then prompts the player to choose an
     * response. It then returns the points gained from the response.
     * 
     * @return the points gained from the chosen response.
     */
    public int print(String npcName) {
        
        //Prints the available responses and the corresponding numbers.
        for (int i = 0; i < responses.length; i++) {
            System.out.println("[" + (i+1) + "]\t" + responses[i]);
        }
        
        //Prints the prompt message
        System.out.println("");
        
        //The player loops until they return a valid answer.
        while (true) {
            
            //This is the same statements as in the Game class, but with special
            //commands just for the conversation scenarios. 
           // Command command = parser.getCommand();
           // processCommand(command);
            
            //Check if the player wants to leave the conversation.
            if (isWantToLeave()) {
                return 0;
            }
            
            //If chosenResponse is still 0, then it means that the user did not 
            //enter a valid command... That means we loop back around.
            if (chosenResponse == 0) {
                continue;   //We loop back around
            }
            
            chosenResponse--;   //This is to make it match the index numbers
            
            //Makes sure the index isn't out of bounds
            if (chosenResponse < persuasionPoints.length && chosenResponse >= 0) {
                System.out.println("");
                System.out.println("You: " + responses[chosenResponse]);
                //Returns  because of a valid answer.
                return persuasionPoints[chosenResponse];
            } else {
                System.out.println("Enter a valid number...");
            }
        }
    }
    
    /**private void chooseReponse(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Say what? Choose a response...");
        } else {
            //This is to catch if the second word isn't able to be converted to an integer.
            try {
                chosenResponse = Integer.parseInt(command.getSecondWord());
                
            } catch (NumberFormatException e) {
                System.out.println("Enter a number...");
            }
        }
    }

    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around the streets of Sustainia.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /**
     * @return the wantToLeave
     */
    public boolean isWantToLeave() {
        return wantToLeave;
    }
}