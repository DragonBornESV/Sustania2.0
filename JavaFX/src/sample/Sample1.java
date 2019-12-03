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


     DecimalFormat numberFormat = new DecimalFormat("#.00");

    @FXML
    private ProgressBar bar, cleanAirBar, equalityBar, greenEnergyBar, cleanWaterBar, sustainableHousingBar, cleanlinessBar, securityBar;

    @FXML
    private Text percentage, cleanAirPercentage, equalityPercentage, greenEnergyPercentage, cleanWaterPercentage, sustainableHousingPercentage, cleanlinessPercentage, securityPercentage;


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


   @FXML
     void update(KeyEvent event) {
        for(Map.Entry<String, Parameter> entry : Parameter.parameterList.entrySet()) {
            getText(entry.getKey()).setText(entry.getValue().getScore() + "%");
            getProgressBar(entry.getKey()).setProgress(entry.getValue().getScore() / 100);
            getProgressBar(entry.getKey()).setStyle("-fx-accent: greenyellow");
            updateMainScore();
        }

    }

    @FXML
    void updateMainScore() {
        bar.setStyle("-fx-accent: greenyellow");
        bar.setProgress(Parameter.mainScore.getMainAverage()/100);
        percentage.setText(numberFormat.format(Parameter.mainScore.getMainAverage())+"%");
    }





}

