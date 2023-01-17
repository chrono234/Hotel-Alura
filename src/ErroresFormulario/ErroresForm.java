package ErroresFormulario;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class ErroresForm {

	private String campoVacio = "El campo no puede estar vacío";
	private String escribeNumeros = "Escribe solo números";

	public ErroresForm(JTextField textField, JLabel JLabel, String textDefault, String numberWordField) {
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String text = textField.getText();
				switch (numberWordField) {
				case "number":
					if (text.isEmpty()) {
						JLabel.setForeground(Color.RED);
						JLabel.setText(escribeNumeros);
					}
					if (!text.isEmpty() && text.matches("[0-9]+")) {
						JLabel.removeFocusListener(this);
						JLabel.setForeground(SystemColor.textInactiveText);
						JLabel.setText(textDefault);
						JLabel.addFocusListener(this);
					}
					break;
				case "word":
					if (text.isEmpty()) {
						JLabel.setForeground(Color.RED);
						JLabel.setText(campoVacio);
					} else if (!text.isEmpty() && text.matches("[a-zA-Z]+")) {
						JLabel.removeFocusListener(this);
						JLabel.setForeground(SystemColor.textInactiveText);
						JLabel.setText(textDefault);
						JLabel.addFocusListener(this);
					}
					break;
				}
			}
		});

		textField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				switch (numberWordField) {
				case "number":
					if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
						e.consume();
					}
					break;
				case "word":
					if (!((c >= 'a' || c >= 'A') && (c <= 'z' || c <= 'Z') || (c == KeyEvent.VK_BACK_SPACE)
							|| (c == KeyEvent.VK_DELETE))) {
						e.consume();
					}
					break;
				}
			}
		});
	}
}
