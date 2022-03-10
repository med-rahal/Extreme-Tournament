/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableView;

import Services.Captcha;
import java.io.IOException;

import javafx.animation.ScaleTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class CaptchaFXMLSkin extends VBox implements Skin<Captcha> {

	private final Captcha control;

	@FXML
	protected Label captchaQuestion;
	@FXML
	protected TextField captchaInput;
	@FXML
	protected Button okButton;
    @FXML
    private VBox captcha;

	public CaptchaFXMLSkin(Captcha control) {
		this.control = control;
		loadFXML();

		// captcha question
		captchaQuestion.textProperty().bind(
				Bindings.concat(getSkinnable().summand1Property(), " + ",
						getSkinnable().summand2Property()));

		// ok button disabled
		okButton.disableProperty().bind(
				captchaInput.textProperty().isNotEqualTo(
						getSkinnable().sumProperty().asString()));

		// hoverproperty ok button - effekt

		okButton.hoverProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				if (newValue) {
					okButton.setEffect(new DropShadow());
				} else {
					okButton.setEffect(null);
				}
			}
		});
	}

	@FXML
	void okButtonPressed(ActionEvent event) {
		ScaleTransition scaleTransition = new ScaleTransition(
				Duration.seconds(0.5), okButton);

		scaleTransition.setByX(-0.2);
		scaleTransition.setByY(-0.2);
		scaleTransition.setAutoReverse(true);
		scaleTransition.setCycleCount(2);
		scaleTransition.play();
	}

	@Override
	public Captcha getSkinnable() {
		return control;
	}

	@Override
	public Node getNode() {
		return this;
	}

	@Override
	public void dispose() {

	}

	private void loadFXML() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				"CaptchaFXMLSkin.fxml"));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

}