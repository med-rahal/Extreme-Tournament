/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import TableView.CaptchaFXMLSkin;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

public class Captcha extends Control {

	private final IntegerProperty summand1 = new SimpleIntegerProperty();
	private final IntegerProperty summand2 = new SimpleIntegerProperty();

	private final ReadOnlyIntegerWrapper sum = new ReadOnlyIntegerWrapper();

	public Captcha() {
		sum.bind(summand1.add(summand2));
	}

	@Override
	protected Skin<?> createDefaultSkin() {
		return new CaptchaFXMLSkin(this);
	}

	/*
	 * Summand 1
	 */
	public IntegerProperty summand1Property() {
		return summand1;
	}

	public void setSummand1(int summand) {
		this.summand1.set(summand);
	}

	public int getSummand1() {
		return summand1.get();
	}

	/*
	 * Summand 2
	 */
	public IntegerProperty summand2Property() {
		return summand2;
	}

	public void setSummand2(int summand) {
		this.summand2.set(summand);
	}

	public int getSummand2() {
		return summand2.get();
	}

	/*
	 * Sum
	 */
	public int getSum() {
		return sum.get();
	}

	public ReadOnlyIntegerProperty sumProperty() {
		return sum.getReadOnlyProperty();
	}
    
    
}
