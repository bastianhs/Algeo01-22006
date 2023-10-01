package src;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class BicubicSplineInterpolation {

    public matriks getX() {
    // Mendapatkan matriks X berukuran 16x16
        matriks X = new matriks(16,16);
        double koefisien_a;
        int baris, kolom;

        baris = 0; // baris [0..15]
        for (int persamaan = 1; persamaan <= 4; persamaan++) {
            // iterasi titik (x,y) = (0,0), (1,0), (0,1), (1,1)
            for (int y = 0; y <= 1; y++) {
                for (int x = 0; x <= 1; x++) {
                    
                    kolom = 0;
                    // iterasi rumus sigma dengan (sigma j = 0..3 (sigma i = 0..3))
                    for (int j = 0; j <= 3; j++) {
                        for (int i = 0; i <= 3; i++) {

                            if (persamaan == 1) {           // persamaan 1 = f(x,y)
                                koefisien_a = Math.pow(x,i) * Math.pow(y,j);
                            } else if (persamaan == 2) {    // persamaan 2 = df(x,y)/dx
                                if (i == 0) {
                                    koefisien_a = 0;        // menghindari kasus NaN/pembagian dengan 0
                                } else {
                                    koefisien_a = i * Math.pow(x,i-1) * Math.pow(y,j);
                                }
                            } else if (persamaan == 3) {    // persamaan 3 = df(x,y)/dy
                                if (j == 0) {
                                    koefisien_a = 0;        // menghindari kasus NaN/pembagian dengan 0
                                } else {
                                    koefisien_a = j * Math.pow(x,i) * Math.pow(y,j-1);
                                }
                            } else {                        // persamaan 4 = d^2f(x,y)/dxdy
                                if (i == 0 || j == 0) {
                                    koefisien_a = 0;        // menghindari kasus NaN/pembagian dengan 0
                                } else {
                                    koefisien_a = i * j * Math.pow(x,i-1) * Math.pow(y,j-1);
                                }
                            }
                            X.setelmt(baris, kolom, koefisien_a);
                            kolom++;
                        }
                    }
                    baris++;
                }
            }
        }
        return X;
    }

    public matriks getInversX(matriks X) {
    // menginversekan matriks X
        matriks identity = new matriks(16, 16);
        identity.identitas(16,16);

        matriks invers_X = new matriks(16, 16);
        
        invers_X = balikan.balikanreduksi(X, identity);
        return invers_X;
    }

    public matriks Matriks1Kolom(matriks M) {
    // Mengubah matriks 4x4 menjadi matriks 16x1 agar sesuai dengan pers. y = Xa
        matriks temp = new matriks(16, 1);
        int baris = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                temp.setelmt(baris, 0, M.getelmt(i, j));
                baris++;
            }
        }

        return temp;
    }

    public matriks Matriks4Kolom(matriks M) {
    // Mengubah matriks 16x1 menjadi matriks 4x4
        matriks temp = new matriks(4, 4);
        int baris = 0;

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                temp.setelmt(i, j, M.getelmt(baris, 0));
                baris++;
            }
        }

        return temp;
    }

    public static double nilaiFungsi(matriks A, double a, double b) {
    // Menghitung nilai taksiran f(a,b) dengan rumus persamaan 1
    // f(x,y) = sigma i[0..3] (sigma j [0..3] (a_ij * x^i * y^j))
        double hasil = 0;

        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 3; j++) {
                hasil += A.getelmt(i, j) * Math.pow(a, i) * Math.pow(b, j);
            }
        }

        return hasil;
    }

    public void tulisHasil(int metode, matriks A, double a, double b) {
        // nilai f(a,b)
        double f_a_b = nilaiFungsi(A, a, b);

        if (metode == 1) {              // menulis ke terminal
            System.out.println("Nilai f(" + a + "," + b + ") = " + f_a_b);
        } else {                        //menulis ke txt
            String namaFile;
            Scanner scan = new Scanner(System.in);
            
            // meminta nama file
            System.out.println("Masukkan nama file: ");
            namaFile = scan.nextLine();
            
            // membuat file
            File file = new File("test/output/" + namaFile);
            if (file.exists()) {        // jika filenya sudah ada
                System.out.println("file " + namaFile + " sudah ada!");
                System.out.println("Apakah anda ingin mengulangi memasukkan nama file? (y/n)");
                String ulang = scan.nextLine();
                if (ulang.equals("y") || ulang.equals("Y")) {
                    tulisHasil(metode, A, a, b);
                } else {
                    System.out.println("Terima kasih");
                }
            } else {
                try {
                    FileOutputStream fileWriter = new FileOutputStream("test/output/" + namaFile);
                    fileWriter.write("Nilai f(".getBytes());
                    fileWriter.write(Double.toString(a).getBytes());
                    fileWriter.write(",".getBytes());
                    fileWriter.write(Double.toString(b).getBytes());
                    fileWriter.write(") = ".getBytes());
                    fileWriter.write(Double.toString(f_a_b).getBytes());

                    System.out.println("File telah selesai dibuat!");
                } catch (IOException e) {}
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        BicubicSplineInterpolation bcs = new BicubicSplineInterpolation();
        matriks y = new matriks(16,1);
        double a, b;

		System.out.println("""
                            Metode Input:
                            1. Terminal
                            2. File .txt""");
        int pilihan2 = scan.nextInt();
        
        if (pilihan2 == 1) {
            // meminta inputan pengguna
            matriks from_keyboard = new matriks(4,4);
            System.out.println("Masukkan nilai fungsi dan turunannya:");
            from_keyboard.bacamatriks();
            System.out.print("a = ");
            a = scan.nextInt();
            System.out.print("b = ");
            b = scan.nextInt();
            // mengubah matriks 4x4 menjadi matriks 16x1
            y = bcs.Matriks1Kolom(from_keyboard);
        } else {
            // memindahkan isi txt ke matriks
            matriks from_txt = new matriks(0,0);
            from_txt.bacamatriksfile();
                // jika mengikuti spek, diperoleh matriks 5x4, dimana baris terakhir adalah nilai a, b yang hendak di cari
            a = from_txt.getelmt(4,0);
            b = from_txt.getelmt(4,1);
            // mengubah matriks 4x4 menjadi matriks 16x1
            y = bcs.Matriks1Kolom(from_txt);
        }
        
        matriks X = bcs.getX();
        matriks Xinv = bcs.getInversX(X);

        // mencari koeffisien a
        matriks a_matriks = new matriks(16,1);
        a_matriks = a_matriks.multiply(Xinv, y);

        // mengubah matriks a 16x1 menjadi matriks 4x4
        a_matriks = bcs.Matriks4Kolom(a_matriks);

        // mencari nilai f(a,b)
        // Masukkan metode output: 1 untuk terminal, 2 untuk txt
        System.out.println("""
                            Metode Output:
                            1. Terminal
                            2. File .txt""");
        int metode = scan.nextInt();
        bcs.tulisHasil(metode, a_matriks, a, b);
    }
}