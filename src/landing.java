package src;


import java.io.BufferedWriter;
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
					4. Interpolasi Polinom
					5. Regresi Linear Berganda""");
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
                // Menjalankan menu regresi linear berganda
				else if (pilihan == 5) {
					System.out.println("""
                        ====================================================
                        Regresi Linear Berganda / Multiple Linear Regression
                        ====================================================
                        """);
                    
                    // Skip spasi dari terminal
                    String inputMode = scan.nextLine();
                    
                    // Memilih cara input data
                    while (true) {
                        System.out.println("""
                            Pilihan cara input data:
                            1. Terminal
                            2. File .txt
                            """);
                        System.out.print("Masukkan nomor pilihan yang diinginkan: ");
                        inputMode = scan.nextLine();
                        if (inputMode.equals("1") | inputMode.equals("2")) {
                            break;
                        }
                        System.out.println("Pilihan tidak valid !\n");
                    }

                    matriks sampleData = null;
                    matriks newXValues = null; // menyimpan nilai-nilai xk untuk diprediksi nilai y-nya
                    // Jika memilih terminal:
                    if (inputMode.equals("1")) {
                        // Memasukkan banyak peubah x
                        int numberOfX = 0;
                        while (true) {
                            System.out.print("Masukkan banyaknya peubah x: ");
                            String stringNumberOfX = scan.nextLine();
                            try {
                                numberOfX = Integer.parseInt(stringNumberOfX);
                                break;
                            } catch (NumberFormatException nfe) {
                                System.out.println("Nilai tidak valid !\n");
                            }
                        }
                        
                        // Memasukkan banyak sampel
                        int numberOfSample = 0;
                        while (true) {
                            System.out.print("Masukkan banyaknya sampel: ");
                            String stringNumberOfSample = scan.nextLine();
                            try {
                                numberOfSample = Integer.parseInt(stringNumberOfSample);
                                break;
                            } catch (NumberFormatException nfe) {
                                System.out.println("Nilai tidak valid !\n");
                            }
                        }

                        // Memasukkan x1i, x2i, x3i, ..., xni, yi
                        sampleData = new matriks(numberOfSample, numberOfX + 1);
                        System.out.println("\nMasukkan nilai x1i, x2i, x3i, ..., xni, yi:");
                        sampleData.bacamatriks();
                        
                        // Memasukkan x1k, x2k, x3k, ..., xnk
                        newXValues = new matriks(1, numberOfX);
                        System.out.println("\nMasukkan nilai x1k, x2k, x3k, ..., xnk:");
                        newXValues.bacamatriks();
                    }
                    // Jika memilih file .txt
                    else {
						// Mengambil semua data dari .txt
						System.out.println("\nMembaca raw data...");
                        matriks rawData = new matriks(0, 0);
						rawData.bacamatriksfile();
						
						// Mengambil nilai-nilai x yang akan diprediksi nilai y-nya
						System.out.println("\nMengambil nilai-nilai x yang akan diprediksi nilai y-nya...");
						newXValues = new matriks(1, rawData.getkolom() - 1);
						for (int i = 0; i < rawData.getkolom() - 1; i++) {
							newXValues.setelmt(0, i, rawData.getelmt(rawData.getbaris() - 1, i));
						}
						
						// Mengambil semua sample
						System.out.println("\nMengambil semua sampel...");
						rawData.kurangibaris();
						sampleData = rawData;
                    }

                    // Mencari persamaan regresi
                    matriks regressionEquation = MultipleLinearRegression.findEquation(sampleData);
                    String displayRegressionEquation = regressionEquationMatrixToString(regressionEquation);

                    // Mencari taksiran nilai y
                    double newY = MultipleLinearRegression.predict(regressionEquation, newXValues);
                    String displayNewY = approximationValueToString(newXValues, newY);

                    // Memilih cara output data
                    String outputMode = null;
                    while (true) {
                        System.out.println("""
                            \nPilihan cara output data:
                            1. Terminal
                            2. File .txt
                            """);
                        System.out.print("Masukkan nomor pilihan yang diinginkan: ");
                        outputMode = scan.nextLine();
                        if (outputMode.equals("1") | outputMode.equals("2")) {
                            break;
                        }
                        System.out.println("Pilihan tidak valid !\n");
                    }

                    // Jika memilih terminal
                    if (outputMode.equals("1")) {
                        System.out.println("\nPersamaan regresi:");
                        System.out.println(displayRegressionEquation + "\n");
                        System.out.println("Taksiran nilai y:");
                        System.out.println(displayNewY + "\n");
                    }
                    // Jika memilih file .txt
                    else {
                        BufferedWriter writer;
                        while (true) {
                            System.out.println("\nMasukkan lokasi/path file .txt (test/): ");
                            String path = scan.nextLine();
                            try {
                                writer = new BufferedWriter(new FileWriter("test/" + path));
                                writer.write(displayRegressionEquation);
                                writer.write("\n" + displayNewY);
                                writer.close();
                                break;
                            } catch (IOException e) {
                                System.out.println("Path file .txt tidak valid !");
                            }
                        }
                    }
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
		if (metode == 1) { //menulis ke terminal
			System.out.println("Solusi SPL: ");
			int subscript = 1;
			for (int i = 0; i < HasilSPL.getkolom(); i++) {
				System.out.println("X" + subscript + " = " + HasilSPL.getelmt(0, i) + "\n");
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
						fileWriter.write(Double.toString(HasilSPL.getelmt(0, i)).getBytes());
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
			}
			System.out.println("");
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


    // Mengkonversi persamaan dalam bentuk matriks menjadi string untuk ditampilkan kepada pengguna
    private static String regressionEquationMatrixToString(matriks regressionEquation) {
        String stringForm = "f(x) = ";
        for (int i = 0; i < regressionEquation.getkolom() - 1 /* matriks regresinya kelebihan 1 kolom */; i++) {
            DecimalFormat decimalFormatter = new DecimalFormat("#.####");
            double bValue = regressionEquation.getelmt(0, i);
            if (i == 0) {
                stringForm += decimalFormatter.format(bValue);
            }
            else {
                if (bValue < 0) {
                    stringForm += " - ";
                    stringForm += decimalFormatter.format(-bValue);
                }
                else {
                    stringForm += " + ";
                    stringForm += decimalFormatter.format(bValue);
                }
                
                stringForm += "x" + i;
            }
        }
        
        return stringForm;
    }


    // Mengkonversi hasil taksiran dari persamaan regresi linear berganda untuk ditampilkan kepada pengguna
    private static String approximationValueToString(matriks independentVariables, double approximationValue) {
        String stringForm = "f(";
        DecimalFormat decimalFormatter = new DecimalFormat("#.####");
        stringForm += decimalFormatter.format(independentVariables.getelmt(0, 0));
        for (int i = 1; i < independentVariables.getkolom(); i++) {
            stringForm += ", " + decimalFormatter.format(independentVariables.getelmt(0, i));
        }

        stringForm += ") = " + decimalFormatter.format(approximationValue);
        return stringForm;
    }
}
