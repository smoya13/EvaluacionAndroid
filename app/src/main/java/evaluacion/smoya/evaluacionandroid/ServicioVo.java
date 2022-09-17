package evaluacion.smoya.evaluacionandroid;

public class ServicioVo {

    // Variables de instancia
    private String nombre;
    private String info;
    private int foto;



    private int rating;

    // Constructor
    public ServicioVo(String nombre, String info, int foto, int rating) {
        this.nombre = nombre;
        this.info = info;
        this.foto = foto;
        this.rating = rating;
    }

    // Getters y Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public int getRating() {return rating;}

    public void setRating(int rating) {this.rating = rating;}
}
