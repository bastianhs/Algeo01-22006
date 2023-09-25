package src;

import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
		int baris = scan.nextInt();
		int kolom = scan.nextInt();
        matriks mat1 = new matriks(baris, kolom);
        mat1.bacamatriks();

    }

}
