package ErroresFormulario;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import views.Exito;
import views.RegistroHuesped;
import views.ReservasView;

public class ErroresFormulario {

	private String campoVacio = "El campo no puede estar vacío";
	private String escribeNumeros = "Escribe solo números";
	private String completarCampos = "Debes llenar todos los campos.";	

	// muestra el error de que tiene que completar todos los campos en reservasView 
	public ErroresFormulario(JPanel btn, JTextField textField) {
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (ReservasView.txtFechaE.getDate() != null && ReservasView.txtFechaS.getDate() != null
						&& !textField.getText().isEmpty()) {
					RegistroHuesped registro = new RegistroHuesped(0);
					registro.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, completarCampos);
				}
			}
		});

	}
	
	//muestra el error de completar campos en RegistroHuesped
	public ErroresFormulario(JPanel btn, JTextField textNombre, 
			JTextField textApellido, JTextField numeroTelefono,JTextField numeroReserva ) {
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!textNombre.getText().isEmpty()
				    && !textApellido.getText().isEmpty() 
				    && RegistroHuesped.getTxtFechaN().getDate() != null
				    && !numeroTelefono.getText().isEmpty() 
				    && !numeroReserva.getText().isEmpty()) {
					Exito exito = new Exito();
					exito.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, completarCampos);
				}
			}
		});

	}
	

	// Muestra los errores de escribir solo numeros o campo vacío
	public ErroresFormulario(JTextField textField, JLabel JLabel, String textDefault, boolean numberField) {
		if (numberField == true) {
			textField.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (!((c >= '0') && (c <= '9') 
					   || (c == KeyEvent.VK_BACK_SPACE)
					   || (c == KeyEvent.VK_DELETE))) {
					    e.consume();
					}
					
				}
			});

			 textField.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					String text = textField.getText();
					if (text.isEmpty()) {
						JLabel.setForeground(Color.RED);
						JLabel.setText(escribeNumeros);
					}
					if (!text.isEmpty() && text.matches("[0-9]+")
					    || (!text.isEmpty() && text.matches("[a-zA-Z]+")) && numberField == false) {
						JLabel.removeFocusListener(this);
						JLabel.setForeground(SystemColor.textInactiveText);
						JLabel.setText(textDefault);
						JLabel.addFocusListener(this);
					}
				}
			});
		} else {
			if (numberField == false) {
				textField.addKeyListener(new KeyAdapter() {
					public void keyTyped(KeyEvent e) {
						char c = e.getKeyChar();
						if (!((c >= 'a' || c >= 'A') && (c <= 'z' || c <= 'Z') 
						   || (c == KeyEvent.VK_BACK_SPACE)
						   || (c == KeyEvent.VK_DELETE))) {
							e.consume();
						}
					}
				});
				textField.addFocusListener(new FocusAdapter() {
					@Override
					public void focusLost(FocusEvent e) {
						String text = textField.getText();
						if (text.isEmpty()) {
							JLabel.setForeground(Color.RED);
							JLabel.setText(campoVacio);
						}
						else if (!text.isEmpty() && text.matches("[a-zA-Z]+")) {
							JLabel.removeFocusListener(this);
							JLabel.setForeground(SystemColor.textInactiveText);
							JLabel.setText(textDefault);
							JLabel.addFocusListener(this);
						}
					}
				});
			}

		}

	}

}
