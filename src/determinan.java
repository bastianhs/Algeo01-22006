package src;

public class determinan {
    public static double determinanbarisreduksi(matriks m) {
        double jumlahkali = 1;
        int gantibaris = m.hitungjumlahswap();
        m.cekswap();
        for (int i = 0; i < m.baris; i++) {
            jumlahkali = jumlahkali * m.getelmt(i, m.getidxleadingone(i));
            m.leadingone(i);
            int idx =m.getidxleadingone(i);
            m.cekbawah(i, idx);
            gantibaris += m.hitungjumlahswap();
            m.cekswap();

        }
        
        if (gantibaris % 2 == 0) {
            return jumlahkali;
        }
        return jumlahkali*-1;
    }

    public static double determinankofaktor(matriks m) {
        if (m.baris == 2 && m.kolom == 2) {
            return (m.getelmt(0,0)*m.getelmt(1,1)- m.getelmt(1,0)*m.getelmt(0,1));
        } 
        else {
            float determinan = 0;
            for (int i = 0; i < m.kolom; i++) {
                int barisawal = 0;
                matriks det = new matriks(m.baris-1,m.kolom-1);
                for(int baris = 1; baris < m.baris; baris++) {
                    int kolomawal = 0;
                    for(int kolom = 0; kolom < m.kolom; kolom++) {
                        if (kolom == i) {
                            continue;
                        }
                        else {
                            det.setelmt(barisawal, kolomawal, m.getelmt(baris,kolom));
                            kolomawal += 1;
                        }
                    }
                    barisawal += 1;
                }
                if (i % 2 == 0) {
                    determinan += m.getelmt(0, i) * det.determinankofaktor();
                }
                else {
                    determinan += m.getelmt(0, i) * det.determinankofaktor() *-1;
                }
            }
            return determinan;
        }
    }
    
}
