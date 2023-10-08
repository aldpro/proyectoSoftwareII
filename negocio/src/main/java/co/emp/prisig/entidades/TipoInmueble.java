package co.emp.prisig.entidades;

public enum TipoInmueble {
    FINCAS("Finca"), APARTAMENTOS("Apartamento"), CONSULTORIOS("Consultorio"),
    DEPOSITOS("Deposito"), CASAS("Casa"), OFICINAS("Oficina"),
    LOCALES("Local"), LOTES("Lote"), EDIFICIOS_DE_OFICINAS("Edificio de oficinas");

    private String nombre;

    private TipoInmueble(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
