package src;

import java.text.DecimalFormat;
import java.util.Scanner;
import java.lang.Math;

public class matriks {
    Scanner scan = new Scanner(System.in);
    double[][] matrix;
    int baris;
    int kolom;

    public matriks(int m, int n) {
        this.baris = m;
        this.kolom = n;
        this.matrix = new double[m][n];
    }

    public double getelmt(int i, int j) {
        return(this.matrix[i][j]);
    }
    
    public int getbaris() {
        return(this.baris);
    }

    public int getkolom() {
        return(this.kolom);
    }

    public void setelmt (int i, int j, double val) {
        this.matrix[i][j] = val;
    }

    public matriks ubahukuran (int row, int col) {
        matriks mat1 = new matriks(row, col);
        return mat1;
    }

    public matriks identitas (int m, int n) {
        matriks identitas = new matriks(m,n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(i == j) {
                    setelmt(i, j, 1);
                }
                else {
                    setelmt(i, j, 0);
                }
            }
        }
        return identitas;
    }
    public void gabung2matriks (matriks mat1, matriks mat2) {
        for (int i = 0; i < mat1.baris; i++) {
            for (int j = 0; j < mat1.kolom; j++) {
                this.setelmt(i, j, mat1.getelmt(i, j));
            }
        }
        for (int i = 0; i < mat2.baris; i++) {
            this.setelmt(i, mat1.kolom, mat2.getelmt(i, 0));
        }
    }

    public void kurangibaris() {
        this.baris = this.baris-1;
    }

    public void kurangikolom() {
        this.kolom = this.kolom- 1;
    }

    public boolean isrowempty (int baris) {
        int jumlahkosong = 0;
        for (int i = 0; i < this.kolom-1; i++) {
            if (getelmt(baris, i) == 0) {
                jumlahkosong += 1;
            }
        }
        if (jumlahkosong == this.kolom-1) {
            return true;
        }
        return false;
    }

    // Untuk mencari baris yang hanya terdapat satu angka
    public boolean onlyone (int baris) {
        int nonkosong = 0;
        for (int j = 0; j < this.kolom-1; j++) {
            if(getelmt(baris, j) != 0) {
                nonkosong += 1;
            }
        }
        if (nonkosong == 1) {
            return true;
        }
        return false;
    }

    public void bacamatriks() {
        for (int i = 0; i < this.baris; i++) {
            for (int j = 0; j < this.kolom; j++) {
                double val = scan.nextDouble();
                setelmt(i, j, val);
            }
        }
    }

    public void tulismatriks() {
        DecimalFormat df = new DecimalFormat("#.####");
        for (int i = 0; i < this.baris; i++) {
            for (int j = 0; j < this.kolom; j++) {

                double num = getelmt(i, j);
                System.out.print(df.format(num)+" ");
            }
            System.out.println();
        }
    }

    public matriks multiply(matriks m1, matriks m2) {
        matriks multi = new matriks(m1.baris, m2.kolom);
        for (int i = 0; i < m1.kolom; i++) {
            for (int j = 0; j < m2.kolom; j++) {
                int angka = 0;
                for (int k =0; k < m1.kolom; k++) {
                    angka += m1.getelmt(i,k)*m2.getelmt(k,j);
                }
                multi.setelmt(i, j, angka);
            }
        }
        return multi;
    }

    public boolean ceknol() {
        int nol = 0;
        for(int i = 0; i < this.baris; i++) {
            for (int  j = 0; j < this.kolom; j++) {
                if (this.getelmt(i, j) == 0) {
                    nol += 1;
                }
            }
        }
        if (nol == this.baris*this.kolom) {
            return true;
        }
        return false;
    }

    public void cekswap() {
        for(int i = 0; i < this.baris; i++) {
            for (int j = i + 1; j < this.baris; j++) {
                if(getidxleadingone(i) > getidxleadingone(j)) {
                    swap(i,j);
                }
            }
        }
    }

    public int hitungjumlahswap() {
        int jumlah = 0;
        for(int i = 0; i < this.baris; i++) {
            for (int j = i + 1; j < this.baris; j++) {
                if(getidxleadingone(i) > getidxleadingone(j)) {
                    swap(i,j);
                    jumlah += 1;
                }
            }
        }
        return jumlah;
    }

    public void swap (int baris1, int baris2) {
        double temp;
        for(int i = 0; i < this.kolom; i++) {
            temp = getelmt(baris2, i);
            setelmt(baris2, i, getelmt(baris1, i));
            setelmt(baris1, i, temp);
        }
    }

    public void gauss() {
        for (int i = 0; i < this.baris; i++) {
            leadingone(i);
            int idx = getidxleadingone(i);
            cekbawah(i, idx);
        }
        int tidakadasolusi = 0;
        int parametrik = 0;
        for (int i = 0; i < this.baris; i++) {
            if (isrowempty(i) == true) {
                if (getelmt(i, this.kolom-1) == 0) {
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
            solusiparametrik ();
        }
        else {
            solusiunik();
        }
    }

    public void gaussjordan() {
        cekswap();
        for (int i = 0; i < this.baris; i++) {
            leadingone(i);
            int idx = getidxleadingone(i);
            cekbawah(i, idx);
        }
        for (int i = this.baris-1; i >= 0; i--) {
            leadingone(i);
            int idx = getidxleadingone(i);
            cekatas(i, idx);
        }
        int tidakadasolusi = 0;
        int parametrik = 0;
        for (int i = 0; i < this.baris; i++) {
            if (isrowempty(i) == true) {
                if (getelmt(i, this.kolom-1) == 0) {
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
            solusiparametrik ();
        }
        else {
            solusiunik();
        }
    }

    // public void kramer(double determinanutama) {
    //     matriks sementara = new matriks(baris, kolom-1);
    //     double determinan;
    //     for (int i = 0; i < this.baris; i++) {
    //         for (int row = 0; row < this.baris; row++) {
    //             for (int col = 0; col < sementara.kolom; col++) {
    //                 if (i == col) {
    //                     sementara.setelmt(row, col, this.getelmt(row, kolom-1));
    //                 }
    //                 else {
    //                     sementara.setelmt(row, col, this.getelmt(row, col));
    //                 }
    //             }
    //         }
    //         sementara.tulismatriks();
    //     }
    // }

    // public void splbalikan() {
    //     for(int row = 0; row < this.baris; row++ ) {
    //         double hasil = 0;
    //         for (int col = 0; col < this.kolom-1; col++) {
    //             hasil += this.getelmt(row, col)*getelmt(col, this.kolom-1);
    //         }
    //         System.out.println(hasil);   
    //     }
    // }

    public void leadingone(int baris) {
        boolean ketemu = false;
        int j = 0;
        while ((ketemu == false) && (j < this.kolom)) {
            if ((getelmt(baris, j) != (double)1) && (getelmt(baris, j) != 0)) {
                bagibaris(getelmt(baris, j),baris);
                ketemu = true;
            }
            else if (getelmt(baris, j) == 1) {
                ketemu = true;
            }
            else {
                j += 1;
            }
        }
    }

    public void bagibaris(double pembagi, int baris) {
        for (int j = 0; j < this.kolom; j++) {
            if (getelmt(baris, j)/pembagi == -0) {
                setelmt(baris, j, 0);
            }
            else {
                setelmt(baris, j, getelmt(baris, j)/pembagi);   
            }
        } 
    }

    public int getidxleadingone(int baris) {
        boolean ketemu = false;
        int j = 0;
        while ((ketemu == false) && j < this.kolom) {
            if (getelmt(baris, j) != 0) {
                return j;
            }
            else {
                j += 1;
            }
        }
        return j-1;
    }

    public int cekjumlahindeks(int baris) {
        int jumlahangka = 0;
        for (int i = 0; i < this.kolom-1; i++) {
            if (getelmt(baris, i) > 0) {
                jumlahangka += 1;
            }
        }
        return jumlahangka;
    }

    public void cekbawah(int baris, int kolom) {
        for (int i = baris+1; i < this.baris; i++) { // cek kolom yang sama dengan leading one baris atas
            if (getelmt(i, kolom) != 0.0) {
                kurangbaris(baris, i, getelmt(i, kolom));
            }
        }
    }

    public void cekatas(int baris, int kolom) {
        for (int i = baris-1; i >= 0; i--) { // cek kolom yang sama dengan leading one baris bawah
            if (getelmt(i, kolom) != 0.0) {
                kurangbaris(baris, i, getelmt(i, kolom));
            }
        }
    }

    public void kurangbaris(int baris1, int baris2, double koefisien) {
        for (int j = 0; j < this.kolom; j++) { // cek seluruh kolom yang terdapat ada baris yang akan dikurangi nilainya
            this.matrix[baris2][j] =getelmt(baris2, j) - koefisien * getelmt(baris1, j);
        }
    }

    public void solusiparametrik() {
        double angka[] = new double[this.kolom-1];
        char nonangka[] = new char[this.kolom-1];
        for (int i = 0; i < this.kolom-1; i++) {
            angka[i] = 100000;
            nonangka[i] = 'a';
        }
        String kalimat[] = new String[this.kolom-1];
        DecimalFormat df = new DecimalFormat("#.####");
        char var = 'p';
        int awal, awalgerak, para;
        double bilbul;
        for (int i = this.baris-1; i >= 0; i--) {
            para = 0;
            if (isrowempty(i) == true) {
                continue;
            }
            else {
                if (onlyone(i) == true) {
                    angka[getidxleadingone(i)] = getelmt(i, this.kolom-1);
                }
                else {
                    awal = getidxleadingone(i);
                    awalgerak = awal+1;
                    bilbul = getelmt(i, this.kolom-1);
                    while (awalgerak < this.kolom-1) {
                        if (getelmt(i, awalgerak) != 0) {
                            if ((angka[awalgerak] == 100000) && (nonangka[awalgerak] == 'a')) {
                                nonangka[awalgerak] = var;
                                var++;
                                para++;
                            }
                            else if (angka[awalgerak] != 100000) {
                                bilbul -= getelmt(i, awalgerak) * angka[awalgerak];
                            }
                            else if (nonangka[awalgerak] != 'a') {
                                para++;
                            }
                        }
                        awalgerak += 1;
                    } 
                    if (para >= 1) {
                    String sentence = "";
                    if (bilbul != 0) {
                        sentence = sentence + df.format(bilbul);
                    }
                    awalgerak = awal + 1;
                    int pertama = 0;
                    while (awalgerak < this.kolom-1) {
                            if (getelmt(i, awalgerak) != 0) {
                                if(angka[awalgerak] == 100000 || kalimat[awalgerak] != null) {
                                    String suku = "";
                                    double koefisien = -1*getelmt(i, awalgerak);
                                    String koef = df.format(Math.abs(koefisien));
                                    if (pertama == 0) {
                                        if (koefisien > 0) {
                                            if (kalimat[awalgerak] == null) {
                                                suku += koef + nonangka[awalgerak];
                                                sentence += suku;
                                                pertama += 1;
                                            }
                                            else {
                                                suku += koef + "*(" + kalimat[awalgerak]+")"; 
                                                sentence += suku;
                                                pertama += 1;
                                            }
                                        }
                                        else {
                                            if (kalimat[awalgerak] == null) {
                                                suku += "-" + koef + nonangka[awalgerak];
                                                sentence += suku;
                                                pertama += 1;
                                            }
                                            else {
                                                suku +="-" + koef + "*(" + kalimat[awalgerak]+")"; 
                                                sentence += suku;
                                                pertama += 1;
                                            }
                                        }
                                    }
                                    else {
                                        if (koefisien > 0) {
                                            if (kalimat[awalgerak] == null) {
                                            suku += " + " + koef + nonangka[awalgerak];
                                            sentence += suku;
                                            }
                                            else {
                                            suku += " + " + koef + "* (" + kalimat[awalgerak]+")"; 
                                            sentence += suku;
                                            }
                                        }
                                        else {
                                            if (kalimat[awalgerak] == null) {
                                            suku += " - " + koef + nonangka[awalgerak];
                                            sentence += suku;
                                        }
                                        else {
                                            suku += " - " + koef + "* (" + kalimat[awalgerak]+")"; 
                                            sentence += suku;
                                        }
                                        }
                                        
                                    }
                                }
                            }
                            awalgerak += 1;
                    }
                    kalimat[awal] = sentence;
                }
                }
            }
        }
        for (int i = 0; i < this.kolom-1; i++) {
            if (angka[i] != 100000) {
                System.out.println(angka[i]);
            }
            else if (kalimat[i] != null){
                System.out.println(kalimat[i]);
            }
            else {
                System.out.println(nonangka[i]);
            }
        }
    }

    public void allzero() {
        for(int i = 0; i < this.baris; i++) {
            for(int j = 0; j < this.kolom; j++) {
                this.setelmt(i, j, 0);
            }
        }
    }

    public matriks solusiunik() {
        matriks angka = new matriks(1, kolom);
        for (int i = 0; i < this.kolom-1; i++) {
            angka.setelmt(0, i, 100000);
        }
        
        for (int i = this.baris-1; i >= 0; i--) {
            double totalkanan = 0;
            double totalkiri = getelmt(i, this.kolom-1);
            for (int j = 0; j < this.kolom-1; j++) {
                if (angka.getelmt(0, j) != 100000) {
                    totalkanan += getelmt(i, j)*angka.getelmt(0, j);
                }
            }
            angka.setelmt(0, getidxleadingone(i),(totalkiri-totalkanan) / getelmt(i, getidxleadingone(i)));
        }
        return angka;
    }

    public matriks balikanreduksi (matriks identitas) {
        matriks gabung = new matriks(identitas.baris, kolom*2);
        matriks balikan = new matriks(identitas.baris, kolom);
        for (int i = 0; i < this.baris; i++) {
            for (int j = 0; j < this.kolom; j++) {
                gabung.setelmt(i, j, getelmt(i, j));
            }
        }
        for (int i = 0; i < this.baris; i++) {
            for (int j = 0; j < this.kolom; j++) {
                gabung.setelmt(i, j+this.kolom, identitas.getelmt(i, j));
            }
        }
        gabung = spl.gaussbalikan(gabung);
        // for (int i =0; i < gabung.baris; i++) {
        //     for (int j = 0; j < gabung.kolom; j++) {
        //         System.out.print(gabung.getelmt(i, j)+" ");
        //     }
        //     System.out.println();
        // }
        for (int i = 0; i < gabung.baris; i++) {
            for (int j = identitas.kolom; j < gabung.kolom; j++) {
                balikan.setelmt(i, j-identitas.kolom, gabung.getelmt(i, j));
            }
        }
        return balikan;
    }

    public matriks balikanadjoin() {
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

    public matriks buatkofaktor() {
        matriks kofaktor = new matriks(this.kolom, this.baris);
        matriks tampung = new matriks(this.kolom-1, this.baris-1);
        for (int i = 0; i < this.baris; i++) {
            for (int j = 0; j < this.kolom; j++) {
                int barisawal = 0;
                for (int row = 0; row < this.baris; row++) {
                    int kolomawal = 0;
                    for (int col = 0; col < this.kolom; col++) {
                        if (row != i && col != j) {
                            tampung.setelmt(barisawal, kolomawal, this.getelmt(row, col));
                            kolomawal += 1;
                        }
                    }
                    if (row != i) {
                        barisawal += 1;
                    }              
                }
                if ((i+j) % 2 == 0) {
                    kofaktor.setelmt(i, j, tampung.determinanbarisreduksi());
                }
                else{
                    kofaktor.setelmt(i, j, tampung.determinanbarisreduksi()*-1);
                }
            }
        }
        return kofaktor;       
    }

    public void transpose() {
        double temp;
        for (int i = 0; i < this.baris; i ++) {
            for (int j = i; j < this.kolom; j++) {
                temp = getelmt(i, j);
                setelmt(i, j, getelmt(j, i));
                setelmt(j, i, temp);
            }
        }
    }

    public double determinanbarisreduksi() {
        double jumlahkali = 1;
        int gantibaris = hitungjumlahswap();
        cekswap();
        for (int i = 0; i < this.baris; i++) {
            jumlahkali = jumlahkali * getelmt(i, getidxleadingone(i));
            leadingone(i);
            int idx = getidxleadingone(i);
            cekbawah(i, idx);
        }
        if (gantibaris % 2 == 0) {
            return jumlahkali;
        }
        return jumlahkali*-1;
    }

    public double determinankofaktor() {
        if (this.baris == 2 && this.kolom == 2) {
            return (this.getelmt(0,0)*this.getelmt(1,1)- this.getelmt(1,0)*this.getelmt(0,1));
        } 
        else {
            float determinan = 0;
            for (int i = 0; i < this.kolom; i++) {
                int barisawal = 0;
                matriks det = new matriks(this.baris-1,this.kolom-1);
                for(int baris = 1; baris < this.baris; baris++) {
                    int kolomawal = 0;
                    for(int kolom = 0; kolom < this.kolom; kolom++) {
                        if (kolom == i) {
                            continue;
                        }
                        else {
                            det.setelmt(barisawal, kolomawal, this.getelmt(baris,kolom));
                            kolomawal += 1;
                        }
                    }
                    barisawal += 1;
                }
                if (i % 2 == 0) {
                    determinan += this.getelmt(0, i) * det.determinankofaktor();
                }
                else {
                    determinan += this.getelmt(0, i) * det.determinankofaktor() *-1;
                }
            }
            return determinan;
        }
    }
}
