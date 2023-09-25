package src;
import java.util.*;
public class landing {
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		int baris = scan.nextInt();
		int kolom = scan.nextInt();
		System.out.println("""
				Selamat datang di tubes Algeo 1, pilih menu berikut:
				1. SPL
				2. Balikan
				3. Determinan""");
		int pilihan = scan.nextInt();
		if (pilihan == 1) {
			System.out.println("""
					1. Gauss
					2. Gauss-Jordan
					3. Balikan
					4. Kaidah Cramer""");
			int pilihan2 = scan.nextInt();
			if (pilihan2 == 1) {
				matriks mat1 = new matriks(baris, kolom);
				mat1.bacamatriks();
				matriks mat2 = new matriks(baris, 1);
				mat2.bacamatriks();
				matriks gabungan = new matriks(baris, kolom+1);
				gabungan.gabung2matriks(mat1,mat2);
				gabungan.gauss();
			}
			if (pilihan2 == 2) {
				matriks mat1 = new matriks(baris, kolom);
				mat1.bacamatriks();
				matriks mat2 = new matriks(baris, 1);
				mat2.bacamatriks();
				matriks gabungan = new matriks(baris, kolom+1);
				gabungan.gabung2matriks(mat1,mat2);
				gabungan.gauss();
			}
			// if (pilihan2 == 3) {
			// 	if (pilihan2 == 1) {
			// 	matriks mat1 = new matriks(baris, kolom);
			// 	mat1.bacamatriks();
			// 	matriks mat2 = new matriks(baris, 1);
			// 	mat2.bacamatriks();
				
			// 	}
			// }
			if (pilihan2 == 4) {
				matriks mat1 = new matriks(baris, kolom);
				mat1.bacamatriks();
				double determinanutama = mat1.determinankofaktor();
				matriks mat2 = new matriks(baris, 1);
				mat2.bacamatriks();
				matriks gabungan = new matriks(baris, kolom+1);
				gabungan.gabung2matriks(mat1,mat2);
				gabungan.tulismatriks();
				gabungan.kramer(determinanutama);
			}
		}
		
	}
}