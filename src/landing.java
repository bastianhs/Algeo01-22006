package src;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class landing {
	public static void main(String[] args){
		boolean selesai = false;
		Scanner scan = new Scanner(System.in);
		while (selesai == false) {
			System.out.println("""
					Selamat datang di tubes Algeo 1, pilih menu berikut:
					1. SPL
					2. Balikan
					3. Determinan
					4. Interpolasi Polinom""");
			int pilihan = scan.nextInt();
				if (pilihan == 1) {
					System.out.println("""
							1. Gauss
							2. Gauss-Jordan
							3. Balikan
							4. Kaidah Cramer""");
					int pilihan2 = scan.nextInt();
					System.out.println("""
							Input mengugunakan:
							1. Input Keyboard
							2. File txt""");

					int pilihan3 = scan.nextInt();
					matriks mat1 = new matriks(0, 0);
					matriks mat2 = new matriks(0, 1);
					matriks gabungan = new matriks(0, 0);
					matriks hasil = new matriks(1, 0);
					if (pilihan3 == 1) {
						System.out.println("Masukkan jumlah baris dan kolom");
						int baris = scan.nextInt();
						int kolom = scan.nextInt();
						mat1 = mat1.ubahukuran(baris, kolom);
						mat2 = mat2.ubahukuran(baris, 1);
						gabungan = gabungan.ubahukuran(baris, kolom+1);
						hasil = hasil.ubahukuran(1, kolom);
						mat1.bacamatriks();
						mat2.bacamatriks();
						gabungan.gabung2matriks(mat1,mat2);
					}
						if (pilihan2 == 1) {
							hasil = spl.gauss(gabungan);
							hasil.kurangikolom();
							if (hasil.ceknol() == false ) {
								hasil.tulismatriks();
							}
						}
						else if (pilihan2 == 2) {
							hasil = spl.gaussjordan(gabungan);
							hasil.kurangikolom();
							if (hasil.ceknol() == false) {
								hasil.tulismatriks();
							}
						}
						else if (pilihan2 == 3) {
							if (mat1.getbaris() != mat1.getkolom()) {
								System.out.println("Matriks balikan harus memiliki baris dan kolom yang sama");
							}
							else {
								mat1 = mat1.balikanadjoin();
								if (mat1.ceknol() == true) {
									System.out.println("Matriks ini tidak memiliki balikan");
								}
								else {
									hasil = spl.splbalikan(gabungan);
									hasil.tulismatriks();
								}
							}
						}
						else if (pilihan2 == 4) {
							if (mat1.getbaris() != mat1.getkolom()) {
								System.out.println("Kaidah cramer harus memiliki baris dan kolom yang sama");
							}
							else {
								double determinanutama = mat1.determinankofaktor();
								if (determinanutama == 0) {
									System.out.println("Determinan sama dengan 0");
								}
								else {
									hasil = spl.kramer(determinanutama,gabungan);
									hasil.tulismatriks();
								}
							}
						}
				}
				else if (pilihan == 2) {
					System.out.println("""
							Pilih cara penyelasaian:
							1. Reduksi Baris
							2. Balikan Adjoin""");
					int pilihan2 = scan.nextInt();
					// System.out.
					System.out.println("""
							Masukkan cara input: 
							1. Input Keyboard
							2. file txt""");
					matriks mat1 = new matriks(0, 0);
					int pilihan3 = scan.nextInt();
					if (pilihan3 == 1) {
						System.out.println("Masukkan nilai n");
						int n = scan.nextInt();
						mat1 = mat1.ubahukuran(n, n);
						mat1.bacamatriks();
					}
						if (pilihan2 == 1) {
							matriks identity = new matriks(mat1.getbaris(), mat1.getkolom());
							identity.identitas(identity.getbaris(), identity.getkolom());
							mat1 = balikan.balikanreduksi(mat1, identity);
							mat1.tulismatriks();
						}
						if (pilihan2 == 2) {
							mat1 = balikan.balikanadjoin(mat1);
							mat1.tulismatriks();
						}
				} 
				else if (pilihan == 4) {
					System.out.println("Masukkan jumlah persamaan:");
					int persamaan = scan.nextInt();
					double hasilpersamaan = 0;
					matriks hasil = new matriks(1, persamaan);
					matriks polinom = new matriks(persamaan,2);
					polinom.bacamatriks();
					double nilaix = scan.nextDouble();
					matriks polinompangkat = new matriks(persamaan, persamaan+1);
					polinompangkat = interpolasipolinom.dipangkat(polinom);
					hasil = spl.gaussjordan(polinompangkat);
					hasilpersamaan = interpolasipolinom.carinilai(hasil,nilaix);
					hasil.tulismatriks();
					System.out.println(hasilpersamaan);
				}
		}
	}

    public void tulisHasilDeterminan(double determinan, int metode) {
        if (metode == 1) { //menulis ke terminal
            System.out.println("Det(A) = " + determinan);
        } else { //menulis ke txt
            String namaFile;
            Scanner scan = new Scanner(System.in);
            
            // meminta nama file
            System.out.println("Masukkan nama file: ");
            namaFile = scan.nextLine();
            
            // membuat file
            File file = new File("test/" + namaFile);
            if (file.exists()) { // jika filenya sudah ada
                System.out.println("file " + namaFile + " sudah ada!");
                System.out.println("Apakah anda ingin mengulangi memasukkan nama file? (y/n)");
                String ulang = scan.nextLine();
                if (ulang.equals("y") || ulang.equals("Y")) {
                    tulisHasilDeterminan(determinan, metode);
                } else {
                    System.out.println("Terima kasih");
                }
            } else {
                try {
                    FileWriter fileWriter = new FileWriter(namaFile);
                    fileWriter.write("Det(A) = ");
                    fileWriter.write(Double.toString(determinan));
            
                    System.out.println("File telah selesai dibuat!");
                } catch (IOException e) {

                }
            }
        }
    }

}