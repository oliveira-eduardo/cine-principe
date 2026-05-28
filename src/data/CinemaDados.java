package data;

import model.Sala;

public class CinemaDados {
    private static Sala[] salasInstanciadas = null;

    public static Sala[] getSalas() {

        if (salasInstanciadas == null) {
            salasInstanciadas = new Sala[4];
            salasInstanciadas[0] = new Sala("IMAX");
            salasInstanciadas[1] = new Sala("3D");
            salasInstanciadas[2] = new Sala("COMUM");
            salasInstanciadas[3] = new Sala("IMAX/3D");
        }

        return salasInstanciadas;
    }
}