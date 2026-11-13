package vallegrande.intrumentosmusicales.model;

public class Instrumento {
    private int idProducto;
    private String nombre;
    private String marca;
    private String tipoInstrumento;
    private String modelo;
    private double precio;
    private int stock;
    private String proveedor;

    public Instrumento() {}

    public Instrumento(int idProducto, String nombre, String marca, String tipoInstrumento, String modelo, double precio, int stock, String proveedor) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.marca = marca;
        this.tipoInstrumento = tipoInstrumento;
        this.modelo = modelo;
        this.precio = precio;
        this.stock = stock;
        this.proveedor = proveedor;
    }

    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getTipoInstrumento() { return tipoInstrumento; }
    public void setTipoInstrumento(String tipoInstrumento) { this.tipoInstrumento = tipoInstrumento; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public String getProveedor() { return proveedor; }
    public void setProveedor(String proveedor) { this.proveedor = proveedor; }
}