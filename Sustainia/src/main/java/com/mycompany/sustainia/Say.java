package com.mycompany.sustainia;


/**This class contains dialog elements used by the NPC class. 
 * 
 * The class contains a message from the NPC, the available responses and the
 * different persuasion points given by each response. The class also handles
 * the printing of dialog and the choosing of responses.
 */
public class Say {
    /**
     * @return the NpcText
     */
    public String getNpcText() {
        return npcText;
    }

    private String npcText;

    /**
     * @return the responses
     */
    public String[] getResponses() {
        return responses;
    }

    private String[] responses;

    /**
     * @return the persuationPoints
     */
    public int[] getPersuasionPoints() {
        return persuasionPoints;
    }

    private int[] persuasionPoints;
    private int chosenResponse = 0;

    /**
     * @return the points
     */
    public int getPoints() {
        return points;
    }

    private int points = 0;
    
    private boolean wantToLeave = false;    //Checks if wantToLeave. 
    
    
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
    public void print(String npcName, String allText) {

        allText += npcText+"\n";

        //Prints the available responses and the corresponding numbers.
        for (int i = 0; i < responses.length; i++) {
            allText += "[" + (i+1) + "]\t" + responses[i] + "\n";
        }
        System.out.println(allText);
        //Prints the prompt message
        System.out.println("");

    }
    
    /**
     * @param value Gives the chosenResponse a value.
     */
    public void setChosenResponse(int value) {
        this.chosenResponse = value;
    }

    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around the streets of Sustainia.");
        System.out.println();
        System.out.println("Your command words are:");
    }

    /**
     * @return the wantToLeave
     */
    public boolean isWantToLeave() {
        return wantToLeave;
    }

    public void chooseResponse(int num){
        chosenResponse = num;

        //Check if the player wants to leave the conversation.
        if (isWantToLeave()) {
            points = 0;
        }
        System.out.println("loop");

        chosenResponse--;   //This is to make it match the index numbers

        //Makes sure the index isn't out of bounds
        if (chosenResponse < persuasionPoints.length && chosenResponse >= 0) {
            System.out.println("");
            System.out.println("You: " + responses[chosenResponse]);
            //Returns  because of a valid answer.
            points = persuasionPoints[chosenResponse];
        } else {
            System.out.println("Enter a valid number...");
        }


    }
}