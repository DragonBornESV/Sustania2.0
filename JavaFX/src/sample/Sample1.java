package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import java.text.DecimalFormat;
import java.util.Map;


public class Sample1 {


    public Sample1(){

    }

    /**
     * This Data type is used to reduce the amount of decimals to ".00".
     */
    DecimalFormat numberFormat = new DecimalFormat("#.00");

    /**
     *  Every attribute for the invidiual paramters for their progressbar.
     */
    @FXML
    private ProgressBar bar, cleanAirBar, equalityBar, greenEnergyBar, cleanWaterBar, sustainableHousingBar, cleanlinessBar, securityBar;

    /**
     * Every attribute for the inividual paramters for their display of the % percentage.
     */
    @FXML
    private Text percentage, cleanAirPercentage, equalityPercentage, greenEnergyPercentage, cleanWaterPercentage, sustainableHousingPercentage, cleanlinessPercentage, securityPercentage;

    /**
     * This method is used to find the ProgressBar of a certain parameter.
     * @param name
     * @return - returns a ProgressBar which is used to update the parameter progress.
     */
    public ProgressBar getProgressBar(String name){
        switch(name){
            case "City Equality":
                return equalityBar;
            case "City Green Energy":
                return greenEnergyBar;
            case "City Clean Water":
                return cleanWaterBar;
            case "Sustainable Housing":
                return sustainableHousingBar;
            case "City Clean Air":
                return cleanAirBar;
            case "City Cleanliness":
                return cleanlinessBar;
            case "City Security":
                return securityBar;
            default:
                    return null;
        }
    }

    /**
     * This method is used to find the Text Percentage of the certain parameter.
     * @param name
     * @return - returns a Text attribute for a certain parameter so it can update its %(percentage) value.
     */
    public Text getText(String name){
        switch(name){
            case "City Equality":
                return equalityPercentage;
            case "City Green Energy":
                return greenEnergyPercentage;
            case "City Clean Water":
                return cleanWaterPercentage;
            case "Sustainable Housing":
                return sustainableHousingPercentage;
            case "City Clean Air":
                return cleanAirPercentage;
            case "City Cleanliness":
                return cleanlinessPercentage;
            case "City Security":
                return securityPercentage;
            default:
                return null;
        }
    }

    /**
     * updates the all the parameters, whenever a key is pressed the method gets called.
     * @param event
     */
   @FXML
     void update(KeyEvent event) {
        for(Map.Entry<String, Parameter> entry : Parameter.parameterList.entrySet()) {
            getText(entry.getKey()).setText(entry.getValue().getScore() + "%");
            getProgressBar(entry.getKey()).setProgress(entry.getValue().getScore() / 100);
            getProgressBar(entry.getKey()).setStyle("-fx-accent: greenyellow");
            updateMainScore();
        }

    }

    /**
     * Updates the main parameter.
     */
    @FXML
    void updateMainScore() {
        bar.setStyle("-fx-accent: greenyellow");
        bar.setProgress(Parameter.mainScore.getMainAverage()/100);
        percentage.setText(numberFormat.format(Parameter.mainScore.getMainAverage())+"%");
    }





}

