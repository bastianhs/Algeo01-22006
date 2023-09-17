package hitung;

import java.util.Scanner;

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
        for (int i = 0; i < this.baris; i++) {
            for (int j = 0; j < this.kolom; j++) {
                System.out.print(getelmt(i, j)+" ");
            }
            System.out.println();
        }
    }

    public void cekswap() {
        for(int i = 0; i < this.baris; i++) {
            for (int j = i + 1; j < this.baris; j++) {
                if(getidxleadingone(i) > getidxleadingone(j)) {
                    swap(i,j);
                }
            }
        }
        tulismatriks();
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
                if (getelmt(i, this.baris) == 0) {
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
        else if ((parametrik > 0) || (this.kolom != this.baris) ) {
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
            tulismatriks();
        }
        for (int i = this.baris-1; i >= 0; i--) {
            leadingone(i);
            int idx = getidxleadingone(i);
            cekatas(i, idx);
        }
    }

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
        System.out.println("Parametrik masih susah");
        double[] angka = new double[this.kolom-1];
        char[] huruf = new char[this.kolom-1];
        for (int i = 0; i < this.kolom-1; i++) {
            angka[i] = 100000;
        }
    }

    public void solusiunik() {
        double[] angka = new double[this.kolom-1];
        for (int i = 0; i < this.kolom-1; i++) {
            angka[i] = 100000;
        }
        
        for (int i = this.baris-1; i >= 0; i--) {
            double totalkanan = 0;
            double totalkiri = getelmt(i, this.kolom-1);
            for (int j = 0; j < this.kolom-1; j++) {
                if (angka[j] != 100000) {
                    totalkanan += getelmt(i, j)*angka[j];
                }
            }
            angka[getidxleadingone(i)] = (totalkiri-totalkanan) / getelmt(i, getidxleadingone(i));
        }
        for (int i = 0; i < this.kolom-1; i++) {
            System.out.print(angka[i] + " ");
        }
    }

    public void balikanreduksi (matriks identitas) {
        matriks gabung = new matriks(baris, kolom*2);
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
        gabung.gaussjordan();
        for (int i =0; i < gabung.baris; i++) {
            for (int j = 0; j < gabung.kolom; j++) {
                System.out.print(gabung.getelmt(i, j)+" ");
            }
            System.out.println();
        }
    }
}

