package src;
import java.util.*;
public class landing {
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		int baris = scan.nextInt();
		int kolom = scan.nextInt();
		matriks matrix = new matriks(baris, kolom);
		matrix.bacamatriks();
		matriks identitas = new matriks(baris, kolom);
		identitas.identitas(baris,kolom);
		matrix.gaussjordan();
		matrix.tulismatriks();
	}
}