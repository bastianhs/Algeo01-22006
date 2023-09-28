package src;

import java.util.Scanner;

public class spl {
    Scanner scan = new Scanner(System.in);
    double[][] matrix;
    int baris;
    int kolom;

    public static matriks gauss(matriks m) {
        matriks mathasil = new matriks(1, m.kolom-1);
        m.cekswap();
        for (int i = 0; i < m.baris; i++) {
            m.leadingone(i);
            int idx = m.getidxleadingone(i);
            m.cekbawah(i, idx);
        }
        int tidakadasolusi = 0;
        int parametrik = 0;
        for (int i = 0; i < m.baris; i++) {
            if (m.isrowempty(i) == true) {
                if (m.getelmt(i, m.kolom-1) == 0) {
                    parametrik += 1;
                }
                else {
                    tidakadasolusi += 1;
                }
            }
        }
        if (tidakadasolusi > 0) {
            System.out.println("Tidak ada solusi");
        }
        else if ((parametrik > 0)) { //|| (this.kolom != this.baris)// ) {
            m.solusiparametrik ();
        }
        else {
            mathasil = m.solusiunik();
        }
        return mathasil;
    }

    public static matriks gaussjordan(matriks m) {
        matriks mathasil = new matriks(1, m.kolom-1);
        m.cekswap();
        for (int i = 0; i < m.baris; i++) {
            m.leadingone(i);
            int idx = m.getidxleadingone(i);
            m.cekbawah(i, idx);
        }
        for (int i = m.baris-1; i >= 0; i--) {
            m.leadingone(i);
            int idx = m.getidxleadingone(i);
            m.cekatas(i, idx);
        }
        int tidakadasolusi = 0;
        int parametrik = 0;
        for (int i = 0; i < m.baris; i++) {
            if (m.isrowempty(i) == true) {
                if (m.getelmt(i, m.kolom-1) == 0) {
                    parametrik += 1;
                }
                else {
                    tidakadasolusi += 1;
                }
            }
        }
        if (tidakadasolusi > 0) {
            System.out.println("Tidak ada solusi");
            mathasil.allzero();
        }
        else if ((parametrik > 0)) { //|| (this.kolom != this.baris)// ) {
            m.solusiparametrik ();
            mathasil.allzero();
        }
        else {
            mathasil = m.solusiunik();
        }
        return mathasil;
    }

    public static matriks splbalikan(matriks m) {
        matriks mathasil = new matriks(1, m.kolom-1);
        for(int row = 0; row < m.baris; row++ ) {
            double hasil = 0;
            for (int col = 0; col < m.kolom-1; col++) {
                hasil += m.getelmt(row, col)*m.getelmt(col, m.kolom-1);
            }
            mathasil.setelmt(0, row, hasil);   
        }
        return mathasil;
    }

    public static matriks kramer(double determinanutama, matriks matriksutama) {
        matriks mathasil = new matriks(1, matriksutama.kolom-1);
        matriks sementara = new matriks(matriksutama.baris, matriksutama.kolom-1);
        double determinan = 0;
        for (int i = 0; i < matriksutama.baris; i++) {
            for (int row = 0; row < matriksutama.baris; row++) {
                for (int col = 0; col < sementara.kolom; col++) {
                    if (i == col) {
                        sementara.setelmt(row, col, matriksutama.getelmt(row, matriksutama.kolom-1));
                    }
                    else {
                        sementara.setelmt(row, col, matriksutama.getelmt(row, col));
                    }
                }
            }
            determinan = sementara.determinankofaktor();
            mathasil.setelmt(0, i, determinan/determinanutama);
        }
        return mathasil;
    }
}
