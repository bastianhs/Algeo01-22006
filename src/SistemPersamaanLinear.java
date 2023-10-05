package src;
import java.util.Scanner;

public class SistemPersamaanLinear {
    Scanner scan = new Scanner(System.in);
    double[][] matrix;
    int baris;
    int kolom;

    public static matriks Gauss(matriks m) { // Metode Gauss
        matriks mathasil = new matriks(1, m.kolom-1);
        m.CekSwap();
        
        for (int i = 0; i < m.baris; i++) {
            m.LeadingOne(i);
            int idx = m.GetIdxLeadingOne(i);
            m.CekBawah(i, idx);
        }
        
        int tidakadasolusi = 0;
        int parametrik = 0;
        int kosong = 0;
        for (int i = 0; i < m.baris; i++) {
            if (m.IsRowEmpty(i) == true) {
                kosong += 1;
                if (m.getelmt(i, m.kolom-1) == 0) {
                    parametrik += 1;
                } else {
                    tidakadasolusi += 1;
                }
            }
        }
        
        if (tidakadasolusi > 0) {
            Scanner scan = new Scanner(System.in);
            String kalimat = "Tidak ada solusi.";
            int metode = Output.mintaOutputan();
            System.out.println("");
			Output.tulisString(kalimat, metode);
        } else if ((parametrik > 0)  &&  (m.baris-kosong != m.kolom-1)) {
            m.SolusiParametrik();
        } else if ((m.baris != m.kolom-1)  &&  (m.baris-kosong != m.kolom-1)) {
            m.SolusiParametrik();
        } else if ((m.baris-kosong == m.kolom)) {
            mathasil = m.SolusiUnik();
        } else {
            mathasil = m.SolusiUnik();
        }

        return mathasil;
    }

    public static matriks GaussJordan(matriks m) { // Metode Gauss-Jordan
        matriks mathasil = new matriks(1, m.kolom-1);
        m.CekSwap();
        
        for (int i = 0; i < m.baris; i++) {
            m.LeadingOne(i);
            int idx = m.GetIdxLeadingOne(i);
            m.CekBawah(i, idx);
            m.CekSwap();
        }
        
        for (int i = m.baris-1; i >= 0; i--) {
            m.LeadingOne(i);
            int idx = m.GetIdxLeadingOne(i);
            m.CekAtas(i, idx);
            m.CekSwap();
        }
        
        int tidakadasolusi = 0;
        int parametrik = 0;
        
        for (int i = 0; i < m.baris; i++) {
            if (m.IsRowEmpty(i) == true) {
                if (m.getelmt(i, m.kolom-1) == 0) {
                    parametrik += 1;
                } else {
                    tidakadasolusi += 1;
                }
            }
        }

        if (tidakadasolusi > 0) {
            System.out.println("Tidak ada solusi");
            mathasil.allzero();
        }

        else if ((parametrik > 0)  || (m.kolom-1 != m.baris)) {
            m.SolusiParametrik ();
            mathasil.allzero();
        }

        else {
            mathasil = m.SolusiUnik();
        }

        return mathasil;
    }

    public static matriks GaussBalikan(matriks m) {
        m.CekSwap();
        
        for (int i = 0; i < m.baris; i++) {
            m.LeadingOne(i);
            int idx = m.GetIdxLeadingOne(i);
            m.CekBawah(i, idx);
        }

        for (int i = m.baris-1; i >= 0; i--) {
            m.LeadingOne(i);
            int idx = m.GetIdxLeadingOne(i);
            m.CekAtas(i, idx);
            m.CekSwap();
        }

        return m;
    }

    public static matriks SPLBalikan(matriks m) { // Metode Inverse
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

    public static matriks Cramer(double determinanutama, matriks matriksutama) { // Metode Cramer
        matriks mathasil = new matriks(1, matriksutama.kolom-1);
        matriks sementara = new matriks(matriksutama.baris, matriksutama.kolom-1);
        
        double determinan = 0;
        for (int i = 0; i < matriksutama.baris; i++) {
            for (int row = 0; row < matriksutama.baris; row++) {
                for (int col = 0; col < sementara.kolom; col++) {
                    if (i == col) {
                        sementara.setelmt(row, col, matriksutama.getelmt(row, matriksutama.kolom-1));
                    } else {
                        sementara.setelmt(row, col, matriksutama.getelmt(row, col));
                    }
                }
            }
            determinan = sementara.DeterminanKofaktor();
            mathasil.setelmt(0, i, determinan/determinanutama);
        }

        return mathasil;
    }
}









































































































































































































































































