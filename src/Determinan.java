package src;

public class Determinan {
    
    public static double DeterminanBarisReduksi(matriks m) { // Metode Reduksi Baris
        double jumlahkali = 1;
        int gantibaris = m.HitungJumlahSwap();
        m.CekSwap();
        
        for (int i = 0; i < m.baris; i++) {
            jumlahkali = jumlahkali * m.getelmt(i, m.GetIdxLeadingOne(i));
            m.LeadingOne(i);
            int idx =m.GetIdxLeadingOne(i);
            m.CekBawah(i, idx);
            gantibaris += m.HitungJumlahSwap();
            m.CekSwap();
        }
        
        if (gantibaris % 2 == 0) {
            return jumlahkali;
        }

        return jumlahkali*-1;
    }

    public static double DeterminanKofaktor(matriks m) { // Metode Kofaktor
        if (m.baris == 2 && m.kolom == 2) {
            return (m.getelmt(0,0)*m.getelmt(1,1)- m.getelmt(1,0)*m.getelmt(0,1));
        } else {
            float determinan = 0;
            
            for (int i = 0; i < m.kolom; i++) {
                int barisawal = 0;
                matriks det = new matriks(m.baris-1,m.kolom-1);
                
                for(int baris = 1; baris < m.baris; baris++) {
                    int kolomawal = 0;
                    
                    for(int kolom = 0; kolom < m.kolom; kolom++) {
                        if (kolom == i) {
                            continue;
                        } else {
                            det.setelmt(barisawal, kolomawal, m.getelmt(baris,kolom));
                            kolomawal += 1;
                        }
                    }
                    barisawal += 1;
                }
                
                if (i % 2 == 0) {
                    determinan += m.getelmt(0, i) * det.DeterminanKofaktor();
                } else {
                    determinan += m.getelmt(0, i) * det.DeterminanKofaktor() *-1;
                }
            }
            return determinan;
        }
    }
}