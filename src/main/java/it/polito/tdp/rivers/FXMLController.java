package it.polito.tdp.rivers;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.rivers.model.Model;
import it.polito.tdp.rivers.model.River;
import it.polito.tdp.rivers.model.Stampa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<River> boxRiver;

    @FXML
    private Button btnSimula;

    @FXML
    private TextField txtEndDate;

    @FXML
    private TextField txtFMed;

    @FXML
    private TextField txtK;

    @FXML
    private TextField txtNumMeasurements;

    @FXML
    private TextArea txtResult;

    @FXML
    private TextField txtStartDate;

    @FXML
    void handleRiempi(ActionEvent event) {
    	
    	
    	River r = boxRiver.getValue();
    	Stampa s = this.model.getStampa(r);
    	
    	txtStartDate.setText(s.getFirstDate().toString());
    	txtEndDate.setText(s.getLastDate().toString());
    	txtNumMeasurements.setText(s.getnMisure()+"");
    	txtFMed.setText(""+s.getFlussoMedio());
    	
    }
    
    @FXML
    void handleSimula(ActionEvent event) {
    	
    	txtResult.clear();
    	float k;
    	try {
    		k = Float.parseFloat(txtK.getText());
    	} catch (NullPointerException npe) {
    		txtResult.setText("Errore: inserire k");
    		return;
    	} catch (NumberFormatException nfe) {
    		txtResult.setText("Errore: inserire valore numerico");
    		return;
    	}
    	 
    	River r = boxRiver.getValue();
    	Stampa river = this.model.getStampa(r);
    	
    	this.model.simula(k, river);
    	
    	txtResult.appendText("Simulazione finita! Risulatati\n\n"
    			+ "# Giorni in cui non si è potuta garantire l’erogazione minima: "+this.model.getDateirrMin()+"\n"
    			+ "Occupazione media del bacino nel corso della simulazione: "+this.model.getCapacitaMedia());
    	
    	
    }

    @FXML
    void initialize() {
        assert boxRiver != null : "fx:id=\"boxRiver\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtFMed != null : "fx:id=\"txtFMed\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNumMeasurements != null : "fx:id=\"txtNumMeasurements\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'Scene.fxml'.";

    }

    
    public void setModel(Model model) {
    	this.model = model;
    	
    	boxRiver.getItems().addAll(this.model.getAllivers());
    }
    
    
}
