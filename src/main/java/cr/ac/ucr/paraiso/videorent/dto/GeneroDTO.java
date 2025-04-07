package cr.ac.ucr.paraiso.videorent.dto;

public class GeneroDTO {

    private int id;
    private String nombre;

    // Constructors
    public GeneroDTO() {}

    public GeneroDTO(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}