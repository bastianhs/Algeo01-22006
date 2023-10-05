package src;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Landing {
	public static void main(String[] args) {
		boolean selesai = false;
		Scanner scan = new Scanner(System.in);
		Output.Pembukaan();
		
		while (selesai == false) {
			Output.Menu();
			System.out.print(">> ");
			int pilihan = scan.nextInt();
			Output.Pemisah();

			if (pilihan == 1) { // SPL
				System.out.println("""
					Pilih metode penyelesaian:
					    1. Gauss
					    2. Gauss-Jordan
					    3. Balikan
					    4. Kaidah Cramer""");
				System.out.print(">> ");
				int pilihan2 = scan.nextInt();
				System.out.print("\n");

				int pilihan3 = Output.mintaInputan();
				System.out.print("\n");

				matriks mat1 = new matriks(0, 0);
				matriks mat2 = new matriks(0, 1);
				matriks gabungan = new matriks(0, 0);
				matriks hasil = new matriks(1, 0);
				
				// meminta inputan
				if (pilihan3 == 1) { // SPL input terminal
					System.out.println("Masukkan jumlah baris dan kolom");
					System.out.print("Baris: "); int baris = scan.nextInt();
					System.out.print("Kolom: "); int kolom = scan.nextInt();
					
					mat1 = mat1.UbahUkuran(baris, kolom);
					mat2 = mat2.UbahUkuran(baris, 1);
					gabungan = gabungan.UbahUkuran(baris, kolom+1);
					hasil = hasil.UbahUkuran(1, kolom);
					
					System.out.println("Masukkan matriks A");
					mat1.BacaMatriks();
					System.out.println("Masukkan matriks B");
					mat2.BacaMatriks();
					gabungan.Gabung2Matriks(mat1,mat2);
				} else if (pilihan3 == 2) { // SPL input txt
					mat1.BacaMatriksFile();
					gabungan = gabungan.UbahUkuran(mat1.getbaris(), mat1.getkolom());
					gabungan = mat1;
					hasil = hasil.UbahUkuran(1, mat1.getkolom());
				}
				System.out.print("\n");

				// metode penyelesaian
				if (pilihan2 == 1) { // Gauss
					hasil = SistemPersamaanLinear.Gauss(gabungan);
					hasil.KurangiKolom();
					
					if (hasil.CekNol() == false ) {
						int metode = Output.mintaOutputan();
						System.out.print("\n");
						Output.tulisHasilSPL(hasil, metode);
					}
				} else if (pilihan2 == 2) { // Gauss-Jordan
					hasil = SistemPersamaanLinear.GaussJordan(gabungan);
					hasil.KurangiKolom();
					
					if (hasil.CekNol() == false) {
						int metode = Output.mintaOutputan();
						System.out.print("\n");
						Output.tulisHasilSPL(hasil, metode);
					}
				} else if (pilihan2 == 3) { // Balikan
					if (mat1.getbaris() != mat1.getkolom()) {
						String kalimat = "Matriks harus memiliki baris dan kolom yang sama!";
						int metode = Output.mintaOutputan();
						System.out.print("\n");
						Output.tulisString(kalimat, metode);
					} else {
						mat1 = Balikan.BalikanAdjoin(mat1);
						if (mat1.CekNol() == true) {
							String kalimat = "Matriks tidak memiliki balikan.";
							int metode = Output.mintaOutputan();
							System.out.print("\n");
							Output.tulisString(kalimat, metode);
						} else {
							hasil = SistemPersamaanLinear.SPLBalikan(gabungan);
							int metode = Output.mintaOutputan();
							System.out.print("\n");
							Output.tulisHasilSPL(hasil, metode);
						}
					}
				} else if (pilihan2 == 4) { // Cramer
					if (mat1.getbaris() != mat1.getkolom()) {
						String kalimat = "Matriks harus memiliki baris dan kolom yang sama!";
						int metode = Output.mintaOutputan();
						System.out.print("\n");
						Output.tulisString(kalimat, metode);
					} else {
						double determinanutama = mat1.DeterminanKofaktor();
						if (determinanutama == 0) {
							String kalimat = "Determinan sama dengan 0.";
							int metode = Output.mintaOutputan();
							System.out.print("\n");
							Output.tulisString(kalimat, metode);
						} else {
							hasil = SistemPersamaanLinear.Cramer(determinanutama,gabungan);
							int metode = Output.mintaOutputan();
							System.out.print("\n");
							Output.tulisHasilSPL(hasil, metode);
						}
					}
				}
			} else if (pilihan == 2) { // Balikan
				System.out.println("""
					Pilih cara penyelasaian:
					    1. Reduksi Baris
					    2. Balikan Adjoin""");
				int pilihan2 = scan.nextInt();
				System.out.print("\n");
				
				// metode input
				int pilihan3 = Output.mintaInputan();
				System.out.print("\n");
                matriks mat1 = new matriks(0, 0);
				
				// penyelesaian
				if (pilihan3 == 1) {
					System.out.println("Masukkan nilai n");
					System.out.print("n = ");
					int n = scan.nextInt();
					mat1 = mat1.UbahUkuran(n, n);
					System.out.println("Masukkan matriks A");
					mat1.BacaMatriks();
				} else if (pilihan3 == 2) {
					mat1.BacaMatriksFile();
				}
				System.out.print("\n");
				
				if (pilihan2 == 1) { // Balikan reduksi baris
					matriks identity = new matriks(mat1.getbaris(), mat1.getkolom());
					identity.Identitas(identity.getbaris(), identity.getkolom());
					mat1 = Balikan.BalikanReduksi(mat1, identity);
					
					if (mat1.CekNol() == true) {
						String kalimat = "Matriks tidak memiliki balikan.";
						int metode = Output.mintaOutputan();
						System.out.print("\n");
						Output.tulisString(kalimat, metode);
					} else {
						int metode = Output.mintaOutputan();
						System.out.print("\n"); 
						Output.tulisMatriks(mat1, metode);
					}
				}
						
				if (pilihan2 == 2) { // Balikan kofaktor
					mat1 = Balikan.BalikanAdjoin(mat1);
					if (mat1.CekNol() == true) {
						String kalimat = "Matriks tidak memiliki balikan.";
						int metode = Output.mintaOutputan();
						System.out.print("\n");
						Output.tulisString(kalimat, metode);
					} else {
						int metode = Output.mintaOutputan();
						System.out.print("\n");
						Output.tulisMatriks(mat1, metode);
					}
				}
			} else if (pilihan == 3) { // Determinan
				System.out.println("""
					Pilih cara penyelasaian:
					    1. Reduksi Baris
					    2. Balikan Adjoin""");
				System.out.print(">> ");
				int pilihan2 = scan.nextInt();
				System.out.print("\n");
				
				// metode input
				int pilihan3 = Output.mintaInputan();
				System.out.print("\n");
				matriks mat1 = new matriks(0, 0);
				
				if (pilihan3 == 1) {
					System.out.println("Masukkan nilai n");
					System.out.print("n = ");
					int n = scan.nextInt();
					mat1 = mat1.UbahUkuran(n, n);
					System.out.println("Masukkan matriks A");
					mat1.BacaMatriks();
				} else if (pilihan3 == 2) {
					mat1.BacaMatriksFile();
				}
				System.out.print("\n");
				
				if (pilihan2 == 1) { // Reduksi Baris
					double hasil;
					hasil = Determinan.DeterminanBarisReduksi(mat1);
					int metode = Output.mintaOutputan();
					System.out.print("\n");
					Output.tulisHasilDeterminan(hasil,metode);
				}
				if (pilihan2 == 2) { // Balikan Adjoin
					double hasil;
					hasil = Determinan.DeterminanKofaktor(mat1);
					int metode = Output.mintaOutputan();
					System.out.print("\n");
					Output.tulisHasilDeterminan(hasil,metode);
				}
			} else if (pilihan == 4) { // Interpolasi Polinomial
				int pilihan3 = Output.mintaInputan();
				System.out.print("\n");
				int persamaan = 0;
				
				double hasilpersamaan = 0;
				matriks hasil = new matriks(0,0);
				
				double nilaix = 0;
				matriks polinom = new matriks(0,0);
				
				if (pilihan3 == 1) { // Interpolasi input terminal
					System.out.println("Masukkan jumlah titik: ");
					System.out.print("n = ");
					persamaan = scan.nextInt();
					hasil = hasil.UbahUkuran(1, persamaan);
					polinom = polinom.UbahUkuran(persamaan, 2);
					System.out.println("Masukkan nilai titik: ");
					polinom.BacaMatriks();
					System.out.println("Masukkan nilai x yang ingin dicari: ");
					nilaix = scan.nextDouble();
				} else if (pilihan3 == 2) { // Interpolasi input txt
					polinom.BacaMatriksFile();
					nilaix = polinom.getelmt(polinom.getbaris()-1, 0);
					polinom.KurangiBaris();
					hasil = hasil.UbahUkuran(1,polinom.getbaris());
					persamaan = polinom.getbaris();
				}
				System.out.print("\n");
				
				matriks polinompangkat = new matriks(persamaan, persamaan+1);
				polinompangkat = InterpolasiPolinom.dipangkat(polinom);
				hasil = SistemPersamaanLinear.GaussJordan(polinompangkat);
				hasilpersamaan = InterpolasiPolinom.CariNilai(hasil,nilaix);
				hasil.KurangiKolom();
				
				int metode = Output.mintaOutputan();
				System.out.print("\n");
			 	Output.tulisHasilInterpolasi(hasil, hasilpersamaan,metode,nilaix);
			} else if (pilihan == 5) { // Regrsi
                // Skip spasi dari terminal
                String inputMode = scan.nextLine();
                    
                // Memilih cara input data
                while (true) {
                    Output.MetodeInput();
                    System.out.print(">> ");
                    inputMode = scan.nextLine();
                    if (inputMode.equals("1") | inputMode.equals("2")) {
                        break;
                    }
                	System.out.println("Pilihan tidak valid!");
                }

                matriks sampleData = null;
                matriks newXValues = null; // menyimpan nilai-nilai xk untuk diprediksi nilai y-nya
                
                if (inputMode.equals("1")) { // Jika memilih terminal:
                	// Memasukkan banyak peubah x
                    int numberOfX = 0;
                    
					while (true) {
                    	System.out.print("\nMasukkan banyaknya peubah x: ");
                        String stringNumberOfX = scan.nextLine();
                        try {
							numberOfX = Integer.parseInt(stringNumberOfX);
                            break;
                        } catch (NumberFormatException nfe) {
                            System.out.println("Nilai tidak valid!");
                        }
                    }
                        
                    // Memasukkan banyak sampel
                    int numberOfSample = 0;
                    while (true) {
                    	System.out.print("\nMasukkan banyaknya sampel: ");
                        String stringNumberOfSample = scan.nextLine();
                        try {
                        	numberOfSample = Integer.parseInt(stringNumberOfSample);
                            break;
                        } catch (NumberFormatException nfe) {
                        	System.out.println("Nilai tidak valid!");
                        }
                    }

                    // Memasukkan x1i, x2i, x3i, ..., xni, yi
                    sampleData = new matriks(numberOfSample, numberOfX + 1);
                    System.out.println("\nMasukkan nilai x1i, x2i, x3i, ..., xni, yi:");
                    sampleData.BacaMatriks();
                        
                    // Memasukkan x1k, x2k, x3k, ..., xnk
                    newXValues = new matriks(1, numberOfX);
                    System.out.println("\nMasukkan nilai x1k, x2k, x3k, ..., xnk:");
                    newXValues.BacaMatriks();
                } else { // Jika memilih file .txt
					// Mengambil semua data dari .txt
					System.out.println("\nMembaca raw data...");
                    matriks rawData = new matriks(0, 0);
					rawData.BacaMatriksFile();
						
					// Mengambil nilai-nilai x yang akan diprediksi nilai y-nya
					System.out.println("\nMengambil nilai-nilai x yang akan diprediksi nilai y-nya...");
					newXValues = new matriks(1, rawData.getkolom() - 1);
					for (int i = 0; i < rawData.getkolom() - 1; i++) {
						newXValues.setelmt(0, i, rawData.getelmt(rawData.getbaris() - 1, i));
					}
						
					// Mengambil semua sample
					System.out.println("Mengambil semua sampel...\n");
					rawData.KurangiBaris();
					sampleData = rawData;
                }

                // Mencari persamaan regresi
                matriks regressionEquation = MultipleLinearRegression.findEquation(sampleData);
                String displayRegressionEquation = Output.regressionEquationMatrixToString(regressionEquation);

                // Mencari taksiran nilai y
                double newY = MultipleLinearRegression.predict(regressionEquation, newXValues);
                String displayNewY = Output.approximationValueToString(newXValues, newY);

                // Memilih cara output data
                String outputMode = null;
                while (true) {
                    Output.MetodeOutput();
					System.out.print(">> ");
                    outputMode = scan.nextLine();
                    if (outputMode.equals("1") | outputMode.equals("2")) {
                    	break;
                    }
                        System.out.println("Pilihan tidak valid !\n");
                }

                if (outputMode.equals("1")) { // Jika memilih terminal
                    System.out.println("\nPersamaan regresi:");
                	System.out.println(displayRegressionEquation + "\n");
                    System.out.println("Taksiran nilai y:");
                    System.out.println(displayNewY + "\n");
                } else { // Jika memilih file .txt
                    BufferedWriter writer;
                    while (true) {
                        System.out.println("\nMasukkan lokasi/path file .txt (test/output): ");
                        String path = scan.nextLine();
                        try {
                            writer = new BufferedWriter(new FileWriter("test/output/" + path));
                            writer.write(displayRegressionEquation);
                            writer.write("\n" + displayNewY);
                            writer.close();
                            break;
                        } catch (IOException e) {
                            System.out.println("Path file .txt tidak valid !");
                        }
                    }
                }
			} else if (pilihan == 6) { // Bicubic Spline Interpolation
				matriks y = new matriks(16,1);
				double a, b;

				int pilihan2 = Output.mintaInputan();
				System.out.print("\n");

				if (pilihan2 == 1) {
					// meminta inputan pengguna
					matriks from_keyboard = new matriks(4,4);
					System.out.println("Masukkan nilai fungsi dan turunannya: ");
					from_keyboard.BacaMatriks();
					System.out.print("a = ");
					a = scan.nextInt();
					System.out.print("b = ");
					b = scan.nextInt();
					// mengubah matriks 4x4 menjadi matriks 16x1
					y = BicubicSplineInterpolation.Matriks1Kolom(from_keyboard);
				} else {
					// memindahkan isi txt ke matriks
					matriks from_txt = new matriks(0,0);
					from_txt.BacaMatriksFile();
						// jika mengikuti spek, diperoleh matriks 5x4, dimana baris terakhir adalah nilai a, b yang hendak di cari
					a = from_txt.getelmt(4,0);
					b = from_txt.getelmt(4,1);
					// mengubah matriks 4x4 menjadi matriks 16x1
					y = BicubicSplineInterpolation.Matriks1Kolom(from_txt);
				}

				matriks X = BicubicSplineInterpolation.getX();
				matriks Xinv = BicubicSplineInterpolation.getInversX(X);

				// mencari koeffisien a
				matriks a_matriks = new matriks(16,1);
				a_matriks = a_matriks.Multiply(Xinv, y);
			
				// mengubah matriks a 16x1 menjadi matriks 4x4
				a_matriks = BicubicSplineInterpolation.Matriks4Kolom(a_matriks);

				// mencari nilai f(a,b)
				// Masukkan metode output: 1 untuk terminal, 2 untuk txt
				System.out.print("\n");
				int metode = Output.mintaOutputan();
				System.out.print("\n");
				Output.tulisHasilBCS(metode, a_matriks, a, b);
			} else if (pilihan == 7) { // Keluar
				selesai = true;
				System.out.println("Terima kasih telah menggunakan program ini!");
				scan.close();
			}
			Output.Pemisah();
		}
	}
}