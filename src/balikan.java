package src;

public class balikan {
    public static matriks balikanadjoin(matriks m) {
        double determinan = m.determinankofaktor();
        matriks kofaktor = new matriks(m.baris, m.kolom);
        if (determinan == 0) {
            kofaktor.allzero();
            return kofaktor;
        }
        else {
            kofaktor = m.buatkofaktor();
            kofaktor.transpose();
            for (int i = 0; i < kofaktor.baris; i++) {
                for (int j = 0; j < kofaktor.kolom; j++) {
                    kofaktor.setelmt(i, j, (1/determinan)*kofaktor.getelmt(i, j));
                }
            }
        }
        return kofaktor;
    }

    public static matriks balikanreduksi (matriks m, matriks identitas) {
        matriks gabung = new matriks(identitas.baris, (identitas.kolom)*2);
        matriks balikan = new matriks(identitas.baris, identitas.kolom);
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
        gabung = spl.gaussbalikan(gabung);
        gabung.cekswap();
        for (int i = 0; i < gabung.baris; i++) {
            for (int j = identitas.kolom; j < gabung.kolom; j++) {
                balikan.setelmt(i, j-identitas.kolom, gabung.getelmt(i, j));
            }
        }
        return balikan;
    }
}