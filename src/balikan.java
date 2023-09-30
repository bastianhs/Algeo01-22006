package src;

public class balikan {
    public static matriks balikanadjoin() {
        double determinan = this.determinankofaktor();
        matriks kofaktor = new matriks(baris, kolom);
        if (determinan == 0) {
            kofaktor.allzero();
            return kofaktor;
        }
        else {
            kofaktor = this.buatkofaktor();
            kofaktor.transpose();
            for (int i = 0; i < kofaktor.baris; i++) {
                for (int j = 0; j < kofaktor.kolom; j++) {
                    kofaktor.setelmt(i, j, (1/determinan)*kofaktor.getelmt(i, j));
                }
            }
        }
        return kofaktor;
    }
}
