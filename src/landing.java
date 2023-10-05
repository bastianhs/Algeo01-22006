package src;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
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
					// SPL input terminal
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
					// SPL input txt
					else if (pilihan3 == 2) {
						mat1.bacamatriksfile();
						gabungan = gabungan.ubahukuran(mat1.getbaris(), mat1.getkolom());
						gabungan = mat1;
						hasil = hasil.ubahukuran(1, mat1.getkolom());
					}
						// SPL gauss
						if (pilihan2 == 1) {
							hasil = spl.gauss(gabungan);
							hasil.kurangikolom();
							if (hasil.ceknol() == false ) {
								System.out.println("""
										Masukkan cara output: 
										1. Tampilkan di terminal
										2. File txt""");
								int metode = scan.nextInt();
								tulisHasilSPL(hasil, metode);
							}
						}
						else if (pilihan2 == 2) {
							hasil = spl.gaussjordan(gabungan);
							hasil.kurangikolom();
							if (hasil.ceknol() == false) {
								System.out.println("""
										Masukkan cara output: 
										1. Tampilkan di terminal
										2. File txt""");
								int metode = scan.nextInt();
								tulisHasilSPL(hasil, metode);
							}
						}
						else if (pilihan2 == 3) {
							if (mat1.getbaris() != mat1.getkolom()) {
								String kalimat = "Matriks balikan harus memiliki baris dan kolom yang sama";
								System.out.println("""
									Masukkan cara output: 
									1. Tampilkan di terminal
									2. File txt""");
								int metode = scan.nextInt();
								tulisstring(kalimat, metode);
							}
							else {
								mat1 = balikan.balikanadjoin(mat1);
								if (mat1.ceknol() == true) {
									String kalimat = "Matriks tidak memiliki balikan";
									System.out.println("""
										Masukkan cara output: 
										1. Tampilkan di terminal
										2. File txt""");
									int metode = scan.nextInt();
									tulisstring(kalimat, metode);
								}
								else {
									hasil = spl.splbalikan(gabungan);
									System.out.println("""
										Masukkan cara output: 
										1. Tampilkan di terminal
										2. File txt""");
									int metode = scan.nextInt();
									tulisHasilSPL(hasil, metode);
								}
							}
						}
						else if (pilihan2 == 4) {
							if (mat1.getbaris() != mat1.getkolom()) {
								String kalimat = "Kaidah cramer harus memiliki baris dan kolom yang sama";
								System.out.println("""
										Masukkan cara output: 
										1. Tampilkan di terminal
										2. File txt""");
									int metode = scan.nextInt();
									tulisstring(kalimat, metode);
							}
							else {
								double determinanutama = mat1.determinankofaktor();
								if (determinanutama == 0) {
									String kalimat = "Determinan sama dengan 0";
									System.out.println("""
										Masukkan cara output: 
										1. Tampilkan di terminal
										2. File txt""");
									int metode = scan.nextInt();
									tulisstring(kalimat, metode);
								}
								else {
									hasil = spl.kramer(determinanutama,gabungan);
									System.out.println("""
										Masukkan cara output: 
										1. Tampilkan di terminal
										2. File txt""");
									int metode = scan.nextInt();
									tulisHasilSPL(hasil, metode);
								}
							}
						}
				}
				// Balikan
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
					else if (pilihan3 == 2) {
						mat1.bacamatriksfile();
					}
					// Balikan reduksi baris
						if (pilihan2 == 1) {
							matriks identity = new matriks(mat1.getbaris(), mat1.getkolom());
							identity.identitas(identity.getbaris(), identity.getkolom());
							mat1 = balikan.balikanreduksi(mat1, identity);
							if (mat1.ceknol() == true) {
								String kalimat = "Matriks tidak memiliki balikan";
								System.out.println("""
										Masukkan cara output: 
										1. Tampilkan di terminal
										2. File txt""");
								int metode = scan.nextInt();
								tulisstring(kalimat, metode);
							}
							else {
								System.out.println("""
										Masukkan cara output: 
										1. Tampilkan di terminal
										2. File txt""");
								int metode = scan.nextInt();
								tulismatriksfile(mat1, metode);
							}
						}
						// Balikan kofaktor
						if (pilihan2 == 2) {
							mat1 = balikan.balikanadjoin(mat1);
							if (mat1.ceknol() == true) {
								String kalimat = "Matriks tidak memiliki balikan";
								System.out.println("""
										Masukkan cara output: 
										1. Tampilkan di terminal
										2. File txt""");
								int metode = scan.nextInt();
								tulisstring(kalimat, metode);
							}
							else {
								System.out.println("""
										Masukkan cara output: 
										1. Tampilkan di terminal
										2. File txt""");
								int metode = scan.nextInt();
								tulismatriksfile(mat1, metode);
							}
						}
				}
				else if (pilihan == 3) {
					System.out.println("""
							Pilih cara penyelasaian:
							1. Reduksi Baris
							2. Balikan Adjoin""");
					int pilihan2 = scan.nextInt();
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
					else if (pilihan3 == 2) {
						mat1.bacamatriksfile();
					}
						if (pilihan2 == 1) {
							double hasil;
							hasil = determinan.determinanbarisreduksi(mat1);
							System.out.println("""
							Masukkan cara output: 
							1. Tampilkan di terminal
							2. File txt""");
							int metode = scan.nextInt();
							tulisHasilDeterminan(hasil,metode);
						}
						if (pilihan2 == 2) {
							double hasil;
							hasil = determinan.determinankofaktor(mat1);
							System.out.println("""
							Masukkan cara output: 
							1. Tampilkan di terminal
							2. File txt""");
							int metode = scan.nextInt();
							tulisHasilDeterminan(hasil,metode);
						}
				}
				else if (pilihan == 4) {
					System.out.println("""
							Masukkan cara input: 
							1. Input Keyboard
							2. file txt""");
					int pilihan3 = scan.nextInt();
					int persamaan = 0;
					double hasilpersamaan = 0;
					matriks hasil = new matriks(0,0);
					double nilaix = 0;
					matriks polinom = new matriks(0,0);
					if (pilihan3 == 1) {
						System.out.println("Masukkan jumlah persamaan: ");
						persamaan = scan.nextInt();
						hasil.ubahukuran(1, persamaan);
						polinom.ubahukuran(persamaan, 2);
						nilaix = scan.nextDouble();
						polinom.bacamatriks();
					}
					else if (pilihan3 == 2) {
						polinom.bacamatriksfile();
						nilaix = polinom.getelmt(polinom.getbaris()-1, 0);
						polinom.kurangibaris();
						hasil.ubahukuran(1,polinom.getbaris());
						persamaan = polinom.getbaris();
					}
					matriks polinompangkat = new matriks(persamaan, persamaan+1);
					polinompangkat = interpolasipolinom.dipangkat(polinom);
					hasil = spl.gaussjordan(polinompangkat);
					hasilpersamaan = interpolasipolinom.carinilai(hasil,nilaix);
					hasil.kurangikolom();
					System.out.println("""
							Masukkan cara output: 
							1. Tampilkan di terminal
							2. File txt""");
					int metode = scan.nextInt();
					tulisHasilInterpolasi(hasil, hasilpersamaan,metode,nilaix);
				}
		}
	}
public static void tulisHasilInterpolasi(matriks hasil, double hasilpersamaan, int metode, double angka) {
		DecimalFormat df = new DecimalFormat("#.####");
        if (metode == 1) { //menulis ke terminal
            for (int i = hasil.getkolom()-1; i >= 0; i--) {
				if (i == 0) {
					System.out.print(df.format(hasil.getelmt(0, i)));
				}
				else if (i == 1) {
					System.out.print(df.format(hasil.getelmt(0, i)) + "x + ");
				}
				else {
					System.out.print(df.format(hasil.getelmt(0, i)) + "x"+i+ " + ");
				}
			}
			System.out.println("");
			System.out.println("f("+angka+") = "+df.format(hasilpersamaan));
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
                    tulisHasilInterpolasi(hasil, hasilpersamaan, metode, angka);
                } else {
                    System.out.println("Terima kasih");
                }
            } else {
                try {
                    FileOutputStream fileWriter = new FileOutputStream("test/" + namaFile);;
					for (int i = hasil.getkolom()-1; i >= 0; i--) {
						if (i == 0) {
							fileWriter.write(df.format(hasil.getelmt(0, i)).getBytes());
						}
						else if (i == 1) {
							fileWriter.write(df.format(hasil.getelmt(0, i)).getBytes());
							fileWriter.write(" x + ".getBytes());
						}
						else {
							fileWriter.write(df.format(hasil.getelmt(0, i)).getBytes());
							fileWriter.write(" x".getBytes());
							fileWriter.write(Integer.toString(i).getBytes());
							fileWriter.write(" + ".getBytes());
						}
					}
					fileWriter.write("\n".getBytes());
					fileWriter.write("f(".getBytes());
					fileWriter.write(Double.toString(angka).getBytes());
					fileWriter.write(") = ".getBytes());
					fileWriter.write(Double.toString(hasilpersamaan).getBytes());
            
                    System.out.println("File telah selesai dibuat!");
                } catch (IOException e) {}
            }
         }
    }

public static void tulisHasilDeterminan(double determinan, int metode) {
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
                    FileOutputStream fileWriter = new FileOutputStream("test/" + namaFile);;
                    fileWriter.write("Det(A) = ".getBytes());
                    fileWriter.write(Double.toString(determinan).getBytes());
            
                    System.out.println("File telah selesai dibuat!");
                } catch (IOException e) {}
            }
        }
    }

public static void tulisHasilSPL(matriks HasilSPL, int metode) {
		DecimalFormat df = new DecimalFormat("#.####");
		if (metode == 1) { //menulis ke terminal
			System.out.println("Solusi SPL: ");
			int subscript = 1;
			for (int i = 0; i < HasilSPL.getkolom(); i++) {
				System.out.println("X" + subscript + " = " + df.format(HasilSPL.getelmt(0, i)));
				subscript++;
			}
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
                    tulisHasilSPL(HasilSPL, metode);
                } else {
                    System.out.println("Terima kasih");
                }
            } else {
                try {
                    FileOutputStream fileWriter = new FileOutputStream("test/" + namaFile);;
                    fileWriter.write("Solusi SPL: ".getBytes());
		
					int subscript = 1;
					for (int i = 0; i < HasilSPL.getkolom(); i++) {
						fileWriter.write("X".getBytes());
						fileWriter.write(Integer.toString(subscript).getBytes());
						fileWriter.write(" = ".getBytes());
						fileWriter.write(df.format(HasilSPL.getelmt(0, i)).getBytes());
						fileWriter.write("\n".getBytes());
						subscript++;
					}
            		System.out.println("File telah selesai dibuat!");
                } catch (IOException e) {}
            }
        }
	}

	public static void tulisHasilSPLparametrik(double[] angka, char[] nonangka, String[] kalimat ,int metode, int panjang) {
		if (metode == 1) { //menulis ke terminal
			System.out.println("Solusi SPL: ");
			DecimalFormat df = new DecimalFormat("#.####");
			int subscript = 1;
			for (int i = 0; i < panjang; i++) {
				if (angka[i] != 100000) {
					System.out.print("x" + (i+1) + " = " +df.format(angka[i])+" ");
				}
				else if (kalimat[i] != null){
					System.out.print("x" + (i+1) + " = " +kalimat[i]+" ");
				}
				else {
					System.out.print("x" + (i+1) + " = " + nonangka[i]+" ");
				}
				System.out.println("");
			}
        } else { //menulis ke txt
            String namaFile;
			DecimalFormat df = new DecimalFormat("#.####");
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
                    tulisHasilSPLparametrik(angka,nonangka,kalimat ,metode, panjang);
                } else {
                    System.out.println("Terima kasih");
                }
            } else {
                try {
                    FileOutputStream fileWriter = new FileOutputStream("test/" + namaFile);;
                    fileWriter.write("Solusi SPL: ".getBytes());
		
					int subscript = 1;
					for (int i = 0; i < panjang; i++) {
						fileWriter.write("X".getBytes());
						fileWriter.write(Integer.toString(subscript).getBytes());
						fileWriter.write(" = ".getBytes());
						if (angka[i] != 100000) {
							fileWriter.write(df.format(angka[i]).getBytes());
						}
						else if (kalimat[i] != null) {
							fileWriter.write(kalimat[i].getBytes());
						}
						else {
							fileWriter.write(Character.toString(nonangka[i]).getBytes());
						}
						fileWriter.write("\n".getBytes());
						subscript++;
					}
            		System.out.println("File telah selesai dibuat!");
                } catch (IOException e) {}
            }
        }
	}

	public static void tulisstring(String kalimat, int metode) {
        if (metode == 1) { //menulis ke terminal
            System.out.println(kalimat);
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
                    tulisstring(kalimat, metode);
                } else {
                    System.out.println("Terima kasih");
                }
            } else {
                try {
                    FileOutputStream fileWriter = new FileOutputStream("test/" + namaFile);;
                    fileWriter.write(kalimat.getBytes());
                    System.out.println("File telah selesai dibuat!");
                } catch (IOException e) {}
            }
        }
    }

	public static void tulismatriksfile(matriks m, int metode) {
		if (metode == 1) { //menulis ke terminal
			m.tulismatriks();
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
                    tulismatriksfile(m, metode);
                } else {
                    System.out.println("Terima kasih");
                }
            } else {
                try {
                    FileOutputStream fileWriter = new FileOutputStream("test/" + namaFile);;
					DecimalFormat df = new DecimalFormat("#.####");
					for (int i = 0; i < m.getbaris(); i++) {
						for (int j = 0; j < m.getkolom(); j++) {
							fileWriter.write(df.format(m.getelmt(0, i)).getBytes());
							if (j != m.getkolom()-1) {
								fileWriter.write(" ".getBytes());
							}
						}
						fileWriter.write("\n".getBytes());
					}
            		System.out.println("File telah selesai dibuat!");
                } catch (IOException e) {}
            }
        }
	}
}