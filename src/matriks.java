

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
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

    // Baca Matrix dari .txt (tambahan)
    public void bacamatriksfile() {
        String namaFile;
        String[][] matrix_string = new String[100][100]; // asumsi kapasitas matriks tidak akan melebihi 100x100
        // menginisiasi matriks_string dengan spasi
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                matrix_string[i][j] = "0";
            }
        }
        
        System.out.println("Masukkan nama file: ");
        System.out.println("Contoh: matriks.txt");
        namaFile = scan.nextLine();
        try {
            // baca file
            File myFile = new File(namaFile);
            Scanner scanfile = new Scanner(myFile);
            
            // memindahkan isi file
            int row = 0;
            int max_col = 0;
            while (scanfile.hasNextLine()) {
                String data = scanfile.nextLine(); // membaca perbaris
                // menyimpan karakter indeks ke-i pada string "data"
                int indeks = 0;
                char current_char = data.charAt(indeks);
                // memindahkan karakter ke matriks
                int col = 0;
                while (current_char != '\n') {
                    if (current_char != ' ') {
                        if (matrix_string[row][col] == "0") {
                            matrix_string[row][col] = String.valueOf(current_char);
                        } else {
                            matrix_string[row][col] += current_char;
                        }
                        if (indeks < data.length()-1) {
                            indeks += 1;
                            current_char = data.charAt(indeks);
                        } else {
                            break;
                        }

                    } else {
                        col += 1;
                        indeks += 1;
                        current_char = data.charAt(indeks);
                        if (col > max_col) {
                            max_col = col;
                        }
                    }
                }
                row += 1;
            }
            // mengubah matriks_string ke matriks (double)
            this.baris = row;
            this.kolom = max_col+1; // ditambah satu karena pada pada saat looping, indeks kolom tidak akan bertambah saat menemukan spasi
            this.matrix = new double[this.baris][this.kolom];
            for (int i = 0; i < this.baris; i++) {
                for (int j = 0; j < this.kolom; j++){
                    this.matrix[i][j] = Double.parseDouble(matrix_string[i][j]);
                }
            }
        } catch (FileNotFoundException e) {
            // file tdak ditemukan
            System.out.println("File " + namaFile + " tidak ditemukan");
            System.out.println("Apakah anda ingin mengulangi memasukkan nama file? (y/n)");
            String ulang = scan.nextLine();
            if (ulang.equals("y") || ulang.equals("Y")) {
                bacamatriksfile();
            } else {
                System.out.println("Terima kasih");
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
                if (getelmt(i, this.baris-1) == 0) {
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
        System.out.println("Parametrik masih susah");
        double[][] angka = new double[this.kolom-1][10];
        char[][] huruf = new char[this.kolom-1][10];
        char[] alfabet = {'a','b','c','d'};
        for (int i = 0; i < this.kolom-1; i++) {
            angka[i][0] = 100000;
        }
        
    }

    public void allzero() {
        for(int i = 0; i < this.baris; i++) {
            for(int j = 0; j < this.kolom; j++) {
                this.setelmt(i, j, 0);
            }
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

    public matriks balikanreduksi (matriks identitas) {
        matriks gabung = new matriks(baris, kolom*2);
        matriks balikan = new matriks(baris, kolom);
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
        System.out.println();
        for (int i = 0; i < gabung.baris; i++) {
            for (int j = this.kolom; j < gabung.kolom; j++) {
                balikan.setelmt(i, j-gabung.baris, gabung.getelmt(i, j));
            }
        }
        return balikan;
    }

    public matriks balikanadjoin() {
        double determinan = this.determinanbarisreduksi();
        matriks kofaktor = new matriks(baris, kolom);
        if (determinan == 0) {
            kofaktor.allzero();
            return kofaktor;
        }
        else {
            
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
                if (i+j % 2 == 0) {
                    kofaktor.setelmt(i, j, tampung.determinanbarisreduksi());
                }
                else {
                    kofaktor.setelmt(i, j, tampung.determinanbarisreduksi()*-1);
                }
            }
        }
        return kofaktor;       
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