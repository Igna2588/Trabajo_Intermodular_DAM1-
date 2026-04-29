package controller;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import model.Movimiento;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

    public class VentanaController {

        @FXML private Label lblFecha;
        @FXML private Label lblIngresosValor;
        @FXML private Label lblGastosValor;
        @FXML private Label lblAhorrosValor;
        @FXML private VBox boxAhorros;
        @FXML private ListView<String> listaIngresos;
        @FXML private ListView<String> listaGastos;
        @FXML private Label lblResultado;

        private MovimientoController controller = new MovimientoController();

        @FXML
        public void initialize() {
            LocalDate hoy = LocalDate.now();
            String mes = Month.of(hoy.getMonthValue())
                    .getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
            mes = mes.substring(0, 1).toUpperCase() + mes.substring(1);
            lblFecha.setText(hoy.getDayOfMonth() + " de " + mes + " de " + hoy.getYear());
            cargarDatos();
        }

        private void cargarDatos() {
            listaIngresos.getItems().clear();
            listaGastos.getItems().clear();

            List<Movimiento> lista = controller.getTodos();
            for (Movimiento m : lista) {
                String fijo = m.getEsFijo() == 1 ? " - FIJO" : "";
                String linea = "[" + m.getIdMovimiento() + "] " + m.getFecha() +
                        " - " + m.getDescripcion() + " - " + m.getImporte() + " EUR" + fijo;
                if (m.getIdCategoria() == 15) {
                    listaIngresos.getItems().add(linea);
                } else {
                    listaGastos.getItems().add(linea);
                }
            }

            double ingresos = controller.getTotalIngresos();
            double gastos = controller.getTotalGastos();
            double ahorros = ingresos - gastos;

            lblIngresosValor.setText(String.format("%.2f EUR", ingresos));
            lblGastosValor.setText(String.format("%.2f EUR", gastos));
            lblAhorrosValor.setText(String.format("%.2f EUR", ahorros));

            boxAhorros.getStyleClass().clear();
            if (ahorros >= 0) {
                boxAhorros.getStyleClass().add("box-ahorros-positivo");
            } else {
                boxAhorros.getStyleClass().add("box-ahorros-negativo");
            }
        }

        @FXML
        private void anadirGasto() {
            mostrarDialogo(0);
        }

        @FXML
        private void anadirGastoFijo() {
            mostrarDialogo(1);
        }

        @FXML
        private void anadirIngreso() {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Anadir ingreso");

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new javafx.geometry.Insets(15));

            ComboBox<String> cbMiembro = new ComboBox<>();
            cbMiembro.getItems().addAll("1 - Carlos", "2 - Laura", "3 - Ignacio");
            cbMiembro.setPromptText("Selecciona miembro");

            TextField tfImporte = new TextField();
            tfImporte.setPromptText("Ej: 2000.00");

            TextField tfDescripcion = new TextField();
            tfDescripcion.setPromptText("Opcional");

            grid.add(new Label("Miembro:"), 0, 0);
            grid.add(cbMiembro, 1, 0);
            grid.add(new Label("Importe (EUR):"), 0, 1);
            grid.add(tfImporte, 1, 1);
            grid.add(new Label("Descripcion:"), 0, 2);
            grid.add(tfDescripcion, 1, 2);

            dialog.getDialogPane().setContent(grid);
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

            dialog.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    int idMiembro = Integer.parseInt(cbMiembro.getValue().split(" - ")[0]);
                    String descripcion = "Nomina " + cbMiembro.getValue().split(" - ")[1];
                    if (!tfDescripcion.getText().isEmpty()) {
                        descripcion = descripcion + " - " + tfDescripcion.getText();
                    }
                    controller.insertar(idMiembro, 15,
                            Double.parseDouble(tfImporte.getText()), descripcion, 0);
                    lblResultado.setText("Ingreso anadido correctamente");
                    cargarDatos();
                }
            });
        }

        @FXML
        private void eliminarSeleccionado() {
            String seleccionado = listaGastos.getSelectionModel().getSelectedItem();
            if (seleccionado == null) {
                seleccionado = listaIngresos.getSelectionModel().getSelectedItem();
            }
            if (seleccionado == null) {
                lblResultado.setText("Selecciona un movimiento primero");
                return;
            }
            int id = Integer.parseInt(seleccionado.split("]")[0].replace("[", "").trim());
            controller.eliminar(id);
            lblResultado.setText("Movimiento eliminado");
            cargarDatos();
        }

        private void mostrarDialogo(int esFijo) {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle(esFijo == 1 ? "Anadir gasto fijo" : "Anadir gasto");

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new javafx.geometry.Insets(15));

            ComboBox<String> cbMiembro = new ComboBox<>();
            cbMiembro.getItems().addAll("1 - Carlos", "2 - Laura", "3 - Ignacio");
            cbMiembro.setPromptText("Selecciona miembro");

            ComboBox<String> cbCategoria = new ComboBox<>();
            cbCategoria.getItems().addAll(
                    "1 - Alquiler / Hipoteca",
                    "2 - Luz",
                    "3 - Agua",
                    "4 - Internet",
                    "5 - Compra supermercado",
                    "6 - Comida fuera / Restaurante",
                    "7 - Gasolina",
                    "8 - Transporte publico",
                    "9 - Netflix",
                    "10 - Spotify",
                    "11 - PS5 - PS Plus",
                    "12 - Salidas",
                    "13 - Cine",
                    "14 - Deporte"
            );
            cbCategoria.setPromptText("Selecciona categoria");

            TextField tfImporte = new TextField();
            tfImporte.setPromptText("Ej: 45.50");

            TextField tfDescripcion = new TextField();
            tfDescripcion.setPromptText("Opcional");

            grid.add(new Label("Miembro:"), 0, 0);
            grid.add(cbMiembro, 1, 0);
            grid.add(new Label("Categoria:"), 0, 1);
            grid.add(cbCategoria, 1, 1);
            grid.add(new Label("Importe (EUR):"), 0, 2);
            grid.add(tfImporte, 1, 2);
            grid.add(new Label("Descripcion:"), 0, 3);
            grid.add(tfDescripcion, 1, 3);

            dialog.getDialogPane().setContent(grid);
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

            dialog.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    int idMiembro = Integer.parseInt(cbMiembro.getValue().split(" - ")[0]);
                    int idCategoria = Integer.parseInt(cbCategoria.getValue().split(" - ")[0]);
                    String descripcion = cbCategoria.getValue().split(" - ")[1];
                    if (!tfDescripcion.getText().isEmpty()) {
                        descripcion = descripcion + " - " + tfDescripcion.getText();
                    }
                    controller.insertar(idMiembro, idCategoria,
                            Double.parseDouble(tfImporte.getText()), descripcion, esFijo);
                    lblResultado.setText(esFijo == 1 ? "Gasto fijo anadido" : "Gasto anadido");
                    cargarDatos();
                }
            });
        }
    }
