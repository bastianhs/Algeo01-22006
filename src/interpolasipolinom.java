package src;

import java.lang.Math;
import java.text.DecimalFormat;

public class interpolasipolinom {
    
    public static matriks dipangkat(matriks m) {
        matriks mathasil = new matriks(m.baris, m.baris+1);
        for (int i = 0; i < m.baris; i++) {
            for (int j = 0; j < m.baris; j++) {
                mathasil.setelmt(i, j, Math.pow(m.getelmt(i, 0), j));
            }
            mathasil.setelmt(i, m.baris, m.getelmt(i, m.kolom-1));
        }
        return mathasil;
    }

    public static double carinilai(matriks m, double angka) {
        double nilai = 0;
        for (int i = 0; i < m.kolom; i++) {
            nilai += Math.pow(angka, i) * m.getelmt(0, i);
        }
        return nilai;
    }
}