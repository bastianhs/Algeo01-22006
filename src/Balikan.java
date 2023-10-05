package src;

public class Balikan {

    public static matriks BalikanAdjoin(matriks m) { // Metode Kofaktor
        double determinan = m.DeterminanKofaktor();
        matriks kofaktor = new matriks(m.baris, m.kolom);
        
        if (determinan == 0) {
            kofaktor.allzero();
            return kofaktor;
        } else {
            kofaktor = m.BuatKofaktor();
            kofaktor.Transpose();
            
            for (int i = 0; i < kofaktor.baris; i++) {
                for (int j = 0; j < kofaktor.kolom; j++) {
                    kofaktor.setelmt(i, j, (1/determinan)*kofaktor.getelmt(i, j));
                }
            }
        }

        return kofaktor;
    }

    public static matriks BalikanReduksi (matriks m, matriks identitas) { // Metode Reduksi Baris
        matriks gabung = new matriks(identitas.baris, (identitas.kolom)*2);
        matriks mbalik = new matriks(identitas.baris, identitas.kolom);
        matriks balikan = new matriks(identitas.baris, identitas.kolom);
        
        int empty = 0;
        for (int i = 0; i < identitas.baris; i++) {
            for (int j = 0; j < identitas.kolom; j++) {
                gabung.setelmt(i, j, m.getelmt(i, j));
            }
        }
        
        for (int i = 0; i < m.baris; i++) {
            for (int j = 0; j < m.kolom; j++) {
                gabung.setelmt(i, j+m.kolom, identitas.getelmt(i, j));
            }
        }
        
        gabung = SistemPersamaanLinear.GaussBalikan(gabung);
        gabung.CekSwap();
        mbalik = SistemPersamaanLinear.GaussBalikan(m);
        
        for (int i = 0; i < mbalik.baris; i++) {
            if(mbalik.IsRowEmpty(i) == true) {
                empty++;
            }
        }

        for (int i = 0; i < gabung.baris; i++) {
            for (int j = identitas.kolom; j < gabung.kolom; j++) {
                balikan.setelmt(i, j-identitas.kolom, gabung.getelmt(i, j));
            }
        }

        if (empty > 0) {
            balikan.allzero();
            return balikan;
        }

        return balikan;
    }
}