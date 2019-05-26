/**
 * Skeleton for 'Borders.fxml' Controller Class
 */

package it.polito.tdp.borders;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BordersController {

	Model model;
	boolean started = false;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="txtAnno"
	private TextField txtAnno; // Value injected by FXMLLoader

	@FXML // fx:id="txtResult"
	private TextArea txtResult; // Value injected by FXMLLoader

	@FXML
    private ComboBox<String> cbStato;

    @FXML
    private Button btnRaggiungibili;
	
	@FXML
	void doCalcolaConfini(ActionEvent event) {
		String stringYear = txtAnno.getText();
		try {
			int year = Integer.parseInt(stringYear);
			if(year<1816 || year>2016) {
				txtResult.setText("L'anno dev'essere nell'intervallo 1816-2016 (compresi)");
				return;
			}
			String result = model.calcolaRisultato(year);
			txtResult.setText(result);
			started = true;
		}catch(NumberFormatException e) {
			txtResult.setText("Valore dell'anno introdotto non valido, riprova\n");
			return;
		}
	}
	
	@FXML
    void doFindRaggiungibili(ActionEvent event) {
		if(started == false) {
			txtResult.setText("Selezionare prima un anno\n");
			return;
		}
		String scelta = cbStato.getValue();
		if(scelta == null) {
			txtResult.setText("Selezionare prima uno stato!\n");
			return;
		}
		System.out.println(scelta);
		List<Country> lista = new LinkedList<Country>(model.reachable(scelta));
//		if(lista.size()==0) {
//			txtResult.setText("Nessun paese raggingibile da "+scelta+"\n");
//			return;
//		}
//		List<String> resultList = new LinkedList<String>();
//		for(Country c : lista) {
//			resultList.add(c.getStateName());
//		}
//		resultList.sort(new ComparatoreStati());
//		txtResult.setText("");
//		for(String s : resultList) {
//			txtResult.appendText(s+"\n");
//		}
		txtResult.setText(lista.toString());
    }

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Borders.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Borders.fxml'.";
	}

	public void setModel(Model model) {
		this.model = model;
		List<String> lista = new LinkedList<String>();
		for(Country c : model.getCountries())
			lista.add(c.getStateName());
		lista.sort(new ComparatoreStati());
		cbStato.getItems().addAll(lista);
	}
}
