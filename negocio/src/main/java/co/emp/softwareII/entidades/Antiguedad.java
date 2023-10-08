package co.emp.softwareII.entidades;

public enum Antiguedad {

    EN_CONSTRUCCION("En construcción"), NUEVO("Nuevo"), ENTRE_0_A_5("Entre 0 a 5 años"),
    ENTRE_5_A_10("Entre 5 a 10 años"), ENTRE_10_A_20("Entre 10 a 20 años"), MAS_DE_20("Más de 20 años");

    private String nombre;


    private Antiguedad(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
