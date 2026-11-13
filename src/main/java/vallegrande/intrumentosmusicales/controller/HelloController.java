package vallegrande.intrumentosmusicales.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import vallegrande.intrumentosmusicales.model.Instrumento;
import vallegrande.intrumentosmusicales.model.InstrumentoDAO;

public class HelloController {

    @FXML private TextField txtId;
    @FXML private TextField txtNombre;
    @FXML private TextField txtMarca;
    @FXML private TextField txtTipo;
    @FXML private TextField txtModelo;
    @FXML private TextField txtPrecio;
    @FXML private TextField txtStock;
    @FXML private TextField txtProveedor;

    @FXML private TableView<Instrumento> tablaInstrumentos;
    @FXML private TableColumn<Instrumento, Integer> colId;
    @FXML private TableColumn<Instrumento, String> colNombre;
    @FXML private TableColumn<Instrumento, String> colMarca;
    @FXML private TableColumn<Instrumento, String> colTipo;
    @FXML private TableColumn<Instrumento, String> colModelo;
    @FXML private TableColumn<Instrumento, Double> colPrecio;
    @FXML private TableColumn<Instrumento, Integer> colStock;
    @FXML private TableColumn<Instrumento, String> colProveedor;

    private final InstrumentoDAO dao = new InstrumentoDAO();
    private ObservableList<Instrumento> listaInstrumentos;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("idProducto"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipoInstrumento"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colProveedor.setCellValueFactory(new PropertyValueFactory<>("proveedor"));

        cargarTabla();
    }

    private void cargarTabla() {
        listaInstrumentos = FXCollections.observableArrayList(dao.listar());
        tablaInstrumentos.setItems(listaInstrumentos);
    }

    @FXML
    protected void onRegistrar() {
        if (!validarCampos()) return;
        try {
            Instrumento inst = new Instrumento(
                    0,
                    txtNombre.getText(),
                    txtMarca.getText(),
                    txtTipo.getText(),
                    txtModelo.getText(),
                    Double.parseDouble(txtPrecio.getText()),
                    Integer.parseInt(txtStock.getText()),
                    txtProveedor.getText()
            );
            if (dao.registrar(inst)) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Instrumento registrado correctamente.");
                cargarTabla();
                onLimpiar();
            }
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de formato", "El precio y el stock deben ser valores numéricos.");
        }
    }

    @FXML
    protected void onActualizar() {
        if (txtId.getText().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Atención", "Selecciona un registro de la tabla para actualizar.");
            return;
        }
        if (!validarCampos()) return;
        try {
            Instrumento inst = new Instrumento(
                    Integer.parseInt(txtId.getText()),
                    txtNombre.getText(),
                    txtMarca.getText(),
                    txtTipo.getText(),
                    txtModelo.getText(),
                    Double.parseDouble(txtPrecio.getText()),
                    Integer.parseInt(txtStock.getText()),
                    txtProveedor.getText()
            );
            if (dao.actualizar(inst)) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Instrumento actualizado correctamente.");
                cargarTabla();
                onLimpiar();
            }
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de formato", "El precio y el stock deben ser valores numéricos.");
        }
    }

    @FXML
    protected void onEliminar() {
        if (txtId.getText().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Atención", "Selecciona un registro de la tabla para eliminar.");
            return;
        }
        int id = Integer.parseInt(txtId.getText());
        if (dao.eliminar(id)) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Instrumento eliminado correctamente.");
            cargarTabla();
            onLimpiar();
        }
    }

    @FXML
    protected void onSeleccionarFila() {
        Instrumento seleccionado = tablaInstrumentos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            txtId.setText(String.valueOf(seleccionado.getIdProducto()));
            txtNombre.setText(seleccionado.getNombre());
            txtMarca.setText(seleccionado.getMarca());
            txtTipo.setText(seleccionado.getTipoInstrumento());
            txtModelo.setText(seleccionado.getModelo());
            txtPrecio.setText(String.valueOf(seleccionado.getPrecio()));
            txtStock.setText(String.valueOf(seleccionado.getStock()));
            txtProveedor.setText(seleccionado.getProveedor());
        }
    }

    @FXML
    protected void onLimpiar() {
        txtId.clear();
        txtNombre.clear();
        txtMarca.clear();
        txtTipo.clear();
        txtModelo.clear();
        txtPrecio.clear();
        txtStock.clear();
        txtProveedor.clear();
        tablaInstrumentos.getSelectionModel().clearSelection();
    }

    private boolean validarCampos() {
        if (txtNombre.getText().isEmpty() || txtMarca.getText().isEmpty() || txtTipo.getText().isEmpty() ||
                txtModelo.getText().isEmpty() || txtPrecio.getText().isEmpty() || txtStock.getText().isEmpty() ||
                txtProveedor.getText().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos vacíos", "Por favor, completa todos los campos.");
            return false;
        }
        return true;
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}