package pe.edu.upeu.calcfx.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class CalcControl {

    @FXML
    TextField txtResultado;

    @FXML
    public void accionButton(ActionEvent event) {
        Button button = (Button) event.getSource();
        switch (button.getId()) {
            case "btn7", "btn8", "btn9", "btn6", "btn5", "btn4", "btn3", "btn2", "btn1", "btn0": {
                escribirNumeros(button.getText());
            } break;
            case "btnSum", "btnMul", "btnRest", "btnDiv", "btnPowY", "btnPow2": {
                operador(button.getText());
            } break;
            case "btnIgual": {
                calcularResultado();
            } break;
            case "btnBorrar": {
                txtResultado.clear();
            } break;
            case "btnRaiz", "btnSin", "btnCos", "btnTan", "btnLn", "btnExp": {
                operadorUnario(button.getText());
            }
        }
    }

    public void escribirNumeros(String valor) {
        txtResultado.appendText(valor);
    }

    public void operador(String valor) {
        txtResultado.appendText(" " + valor + " ");
    }

    public void operadorUnario(String valor) {
        txtResultado.appendText(" " + valor);
    }

    public void calcularResultado() {
        String[] valores = txtResultado.getText().split(" ");
        double val1 = Double.parseDouble(valores[0]);

        // Manejar operaciones unarias como sin, cos, tan, sqrt, ln, exp
        if (valores.length == 2) {
            switch (valores[1]) {
                case "√": {
                    txtResultado.setText(String.valueOf(Math.sqrt(val1)));
                } break;
                case "sin": {
                    txtResultado.setText(String.valueOf(Math.sin(Math.toRadians(val1))));
                } break;
                case "cos": {
                    txtResultado.setText(String.valueOf(Math.cos(Math.toRadians(val1))));
                } break;
                case "tan": {
                    // Redondeamos el valor de la tangente a 6 decimales
                    double resultado = Math.tan(Math.toRadians(val1));
                    txtResultado.setText(String.valueOf(Math.round(resultado * 1000000.0) / 1000000.0));
                } break;
                case "ln": {
                    txtResultado.setText(String.valueOf(Math.log(val1)));
                } break;
                case "exp": {
                    txtResultado.setText(String.valueOf(Math.exp(val1)));
                } break;
            }
        } else if (valores.length == 3) {
            // Manejar operaciones binarias como suma, resta, multiplicación, división, potencias
            double val2 = Double.parseDouble(valores[2]);
            switch (valores[1]) {
                case "+": {
                    txtResultado.setText(String.valueOf(val1 + val2));
                } break;
                case "-": {
                    txtResultado.setText(String.valueOf(val1 - val2));
                } break;
                case "/": {
                    txtResultado.setText(String.valueOf(val1 / val2));
                } break;
                case "*": {
                    txtResultado.setText(String.valueOf(val1 * val2));
                } break;
                case "x²": {
                    txtResultado.setText(String.valueOf(Math.pow(val1, 2)));
                } break;
                case "xʸ": {
                    txtResultado.setText(String.valueOf(Math.pow(val1, val2)));
                } break;
            }
        }
    }

}
