package src;
import java.util.*;
public class landing {
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
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
				System.out.println("Masukkan jumlah baris dan kolom");
				int baris = scan.nextInt();
				int kolom = scan.nextInt();
				matriks mat1 = new matriks(baris, kolom);
				matriks mat2 = new matriks(baris, 1);
				matriks gabungan = new matriks(baris, kolom+1);
				matriks hasil = new matriks(1, kolom);
				if (pilihan3 == 1) {
					mat1.bacamatriks();
					mat2.bacamatriks();
					gabungan.gabung2matriks(mat1,mat2);
				}
					if (pilihan2 == 1) {
						hasil = spl.gauss(gabungan);
						if (hasil.ceknol() == false ) {
							hasil.tulismatriks();
						}
					}
					else if (pilihan2 == 2) {
						hasil = spl.gaussjordan(gabungan);
						hasil.tulismatriks();
					}
					else if (pilihan2 == 3) {
						mat1 = mat1.balikanadjoin();
						gabungan.gabung2matriks(mat1,mat2);
						hasil = spl.splbalikan(gabungan);
						hasil.tulismatriks();
					}
					else if (pilihan2 == 4) {
						double determinanutama = mat1.determinankofaktor();
						hasil = spl.kramer(determinanutama,gabungan);
						hasil.tulismatriks();
					}
			}
			else if (pilihan == 2) {
				System.out.println("""
						Pilih cara penyelasaian:
						1. Reduksi Baris
						2. Balikan Adjoin""");
				int pilihan2 = scan.nextInt();
				// System.out.
				if (pilihan2 == 1) {

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