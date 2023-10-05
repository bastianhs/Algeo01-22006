package src;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Output {

    // Tulis pemisah ke layar
    public static void Pemisah() {
        System.out.println("====================================================================================================");
    }

    // Pembukaan program
    public static void Pembukaan() {
        System.out.println("=================================================================================");
        System.out.println("====== Selamat datang di program Tugas Besar 1 Aljabar Linear dan Geometri ======");
        System.out.println("=================================================================================");
    }

    // Tulis menu ke layar
    public static void Menu() {
        System.out.println("""
    Menu:
        1. Sistem Persamaan Linear
        2. Balikan/Inverse Matriks
        3. Determinan Matriks
        4. Interpolasi Polinom
        5. Regresi Linear Berganda
        6. Bicubic Spline Interpolation
        7. Keluar""");
    }

    // Tulis metode input
    public static void MetodeInput() {
        System.out.println("""
            Pilih metode input:
                1. Input Keyboard
                2. File txt""");
    }

    // Tulis metode output
    public static void MetodeOutput() {
        System.out.println("""
            Pilih metode output:
                1. Tampilkan di terminal
                2. Salin ke file txt""");
    }

    // meminta inputan
    public static int mintaInputan() {
        Scanner scan = new Scanner(System.in);
        int metode;
        while (true) {
            Output.MetodeInput();
            System.out.print(">> ");
            metode = scan.nextInt();
            if (metode  == 1|metode  == 2) {
                break;
            }
            System.out.println("Pilihan tidak valid!");
        }
        return metode;
    }

    // meminta outputan
    public static int mintaOutputan() {
        Scanner scan = new Scanner(System.in);
        int metode;
        while (true) {
            Output.MetodeOutput();
            System.out.print(">> ");
            metode = scan.nextInt();
            if (metode  == 1|metode  == 2) {
                break;
            }
            System.out.println("Pilihan tidak valid!");
        }
        return metode;
    }

    // Tulis hasil SPL (Solusi Unik) ke layar atau ke file
    public static void tulisHasilSPL(matriks HasilSPL, int metode) {
        DecimalFormat df = new DecimalFormat("#.####");
		if (metode == 1) {                                  // menulis ke terminal
			System.out.println("Solusi SPL: ");
			int subscript = 1;
			for (int i = 0; i < HasilSPL.getkolom(); i++) {
				System.out.println("X" + subscript + " = " + df.format(HasilSPL.getelmt(0, i)));
				subscript++;
			}
        } else {                                            // menulis ke txt
            String namaFile;
            Scanner scan = new Scanner(System.in);
            
            // meminta nama file
            System.out.println("Masukkan nama file: ");
            namaFile = scan.nextLine();
            
            // membuat file
            File file = new File("test/output/" + namaFile);
            if (file.exists()) { // jika filenya sudah ada
                System.out.println("File " + namaFile + " sudah ada!");
                System.out.println("Apakah anda ingin mengulangi memasukkan nama file? (y/n)");
                String ulang = scan.nextLine();
                if (ulang.equals("y") || ulang.equals("Y")) {
                    tulisHasilSPL(HasilSPL, metode);
                } else {
                    System.out.println("Terima kasih :)");
                }
            } else {
                try {
                    FileOutputStream fileWriter = new FileOutputStream("test/output/" + namaFile);;
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
            		System.out.println("File telah selesai dibuat.");
                } catch (IOException e) {}
            }
        }
	}

    // Tulis hasil SPL (Solusi Parametrik) ke layar atau ke file
	public static void tulisHasilSPLparametrik(double[] angka, char[] nonangka, String[] kalimat , int metode, int panjang) {
		if (metode == 1) {                                  // menulis ke terminal
			System.out.println("Solusi SPL: ");
			DecimalFormat df = new DecimalFormat("#.####");
			for (int i = 0; i < panjang; i++) {
				if (angka[i] != 100000) {
					System.out.print("x" + (i+1) + " = " + df.format(angka[i]) + " ");
				}
				else if (kalimat[i] != null){
					System.out.print("x" + (i+1) + " = " + kalimat[i] + " ");
				}
				else {
					System.out.print("x" + (i+1) + " = " + nonangka[i] + " ");
				}
                System.out.println("");
			}
        } else {                                            // menulis ke txt
            String namaFile;
			DecimalFormat df = new DecimalFormat("#.####");
            Scanner scan = new Scanner(System.in);
            
            // meminta nama file
            System.out.println("Masukkan nama file: ");
            namaFile = scan.nextLine();
            
            // membuat file
            File file = new File("test/output/" + namaFile);
            if (file.exists()) { // jika filenya sudah ada
                System.out.println("File " + namaFile + " sudah ada!");
                System.out.println("Apakah anda ingin mengulangi memasukkan nama file? (y/n)");
                String ulang = scan.nextLine();
                if (ulang.equals("y") || ulang.equals("Y")) {
                    tulisHasilSPLparametrik(angka,nonangka,kalimat ,metode, panjang);
                } else {
                    System.out.println("Terima kasih :)");
                }
            } else {
                try {
                    FileOutputStream fileWriter = new FileOutputStream("test/output/" + namaFile);;
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
            		System.out.println("File telah selesai dibuat.");
                } catch (IOException e) {}
            }
        }
	}

    // tulis kalimat ke layar atau ke file
	public static void tulisString(String kalimat, int metode) {
        if (metode == 1) {                                  // menulis ke terminal
            System.out.println(kalimat);
        } else {                                            // menulis ke txt
            String namaFile;
            Scanner scan = new Scanner(System.in);
            
            // meminta nama file
            System.out.println("Masukkan nama file: ");
            namaFile = scan.nextLine();
            
            // membuat file
            File file = new File("test/output/" + namaFile);
            if (file.exists()) { // jika filenya sudah ada
                System.out.println("file " + namaFile + " sudah ada!");
                System.out.println("Apakah anda ingin mengulangi memasukkan nama file? (y/n)");
                String ulang = scan.nextLine();
                if (ulang.equals("y") || ulang.equals("Y")) {
                    tulisString(kalimat, metode);
                } else {
                    System.out.println("Terima kasih :)");
                }
            } else {
                try {
                    FileOutputStream fileWriter = new FileOutputStream("test/output/" + namaFile);;
                    fileWriter.write(kalimat.getBytes());
                    System.out.println("File telah selesai dibuat.");
                } catch (IOException e) {}
            }
        }
    }

    // Tulis matriks ke layar atau ke file (dipakai untuk matriks balikan)
	public static void tulisMatriks(matriks m, int metode) {
		if (metode == 1) { //menulis ke terminal
            DecimalFormat df = new DecimalFormat("#.####");
            for (int i = 0; i < m.getbaris(); i++) {
                for (int j = 0; j < m.getkolom(); j++) {
    
                    double num = m.getelmt(i, j);
                    System.out.print(df.format(num)+" ");
                }
                System.out.println();
            }
        } else { //menulis ke txt
            String namaFile;
            Scanner scan = new Scanner(System.in);
            
            // meminta nama file
            System.out.println("Masukkan nama file: ");
            namaFile = scan.nextLine();
            
            // membuat file
            File file = new File("test/output/" + namaFile);
            if (file.exists()) { // jika filenya sudah ada
                System.out.println("file " + namaFile + " sudah ada!");
                System.out.println("Apakah anda ingin mengulangi memasukkan nama file? (y/n)");
                String ulang = scan.nextLine();
                if (ulang.equals("y") || ulang.equals("Y")) {
                    tulisMatriks(m, metode);
                } else {
                    System.out.println("Terima kasih :)");
                }
            } else {
                try {
                    FileOutputStream fileWriter = new FileOutputStream("test/output/" + namaFile);;
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
            		System.out.println("File telah selesai dibuat.");
                } catch (IOException e) {}
            }
        }
	}

    // Tulis hasil determinan ke layar atau ke file
    public static void tulisHasilDeterminan(double determinan, int metode) {
        if (metode == 1) {                                  // menulis ke terminal
            System.out.println("Det(A) = " + determinan);
        } else {                                            // menulis ke txt
            String namaFile;
            Scanner scan = new Scanner(System.in);
            
            // meminta nama file
            System.out.println("Masukkan nama file: ");
            namaFile = scan.nextLine();
            
            // membuat file
            File file = new File("test/output/" + namaFile);
            if (file.exists()) {                            // jika filenya sudah ada
                System.out.println("File " + namaFile + " sudah ada!");
                System.out.println("Apakah anda ingin mengulangi memasukkan nama file? (y/n)");
                String ulang = scan.nextLine();
                if (ulang.equals("y") || ulang.equals("Y")) {
                    tulisHasilDeterminan(determinan, metode);
                } else {
                    System.out.println("Terima kasih :)");
                }
            } else {
                try {
                    FileOutputStream fileWriter = new FileOutputStream("test/output/" + namaFile);;
                    fileWriter.write("Det(A) = ".getBytes());
                    fileWriter.write(Double.toString(determinan).getBytes());
            
                    System.out.println("File telah selesai dibuat.");
                } catch (IOException e) {}
            }
        }
    }

    // Tulis hasil Interpolasi
    public static void tulisHasilInterpolasi(matriks hasil, double hasilpersamaan, int metode, double angka) {
		DecimalFormat df = new DecimalFormat("#.####");
        if (metode == 1) {                                  // menulis ke terminal
            System.out.print("f(x) = ");
            for (int i = hasil.getkolom()-1; i >= 0; i--) {
				if (i == 0) {
					System.out.print(df.format(hasil.getelmt(0, i)));
				} else if (i == 1 && hasil.getelmt(0, i) != 0) {
					System.out.print(df.format(hasil.getelmt(0, i)) + "x + ");
				} else {
                    if (hasil.getelmt(0, i) != 0) {
					    System.out.print(df.format(hasil.getelmt(0, i)) + "x^"+ i + " + ");
			    	}
                }
			}
			System.out.println("");
			System.out.println("f("+angka+") = " + df.format(hasilpersamaan));
        } else {                                            // menulis ke txt
            String namaFile;
            Scanner scan = new Scanner(System.in);
            
            // meminta nama file
            System.out.println("Masukkan nama file: ");
            namaFile = scan.nextLine();
            
           // membuat file
            File file = new File("test/output/" + namaFile);
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
                    FileOutputStream fileWriter = new FileOutputStream("test/output/" + namaFile);
                    fileWriter.write("f(x) = ".getBytes());
					for (int i = hasil.getkolom()-1; i >= 0; i--) {
						if (i == 0 && hasil.getelmt(0, i) != 0) {
							fileWriter.write(df.format(hasil.getelmt(0, i)).getBytes());
						}
						else if (i == 1 && hasil.getelmt(0, i) != 0) {
							fileWriter.write(df.format(hasil.getelmt(0, i)).getBytes());
							fileWriter.write(" x + ".getBytes());
						}
						else {
                            if (hasil.getelmt(0, i) != 0) {
    							fileWriter.write(df.format(hasil.getelmt(0, i)).getBytes());
	    						fileWriter.write("x^".getBytes());
		    					fileWriter.write(Integer.toString(i).getBytes());
			    				fileWriter.write(" + ".getBytes());
				    		}
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

    // Tulis Hasil Bicubic Spline Interpolation
    public static void tulisHasilBCS(int metode, matriks A, double a, double b) {
        // nilai f(a,b)
        double f_a_b = BicubicSplineInterpolation.nilaiFungsi(A, a, b);

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
                    tulisHasilBCS(metode, A, a, b);
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

    // Mengkonversi persamaan dalam bentuk matriks menjadi string untuk ditampilkan kepada pengguna
    public static String regressionEquationMatrixToString(matriks regressionEquation) {
        String stringForm = "f(x) = ";
        for (int i = 0; i < regressionEquation.getkolom() - 1 /* matriks regresinya kelebihan 1 kolom */; i++) {
            DecimalFormat decimalFormatter = new DecimalFormat("#.####");
            double bValue = regressionEquation.getelmt(0, i);
            
            if (i == 0) {
                stringForm += decimalFormatter.format(bValue);
            } else {
                if (bValue < 0) {
                    stringForm += " - ";
                    stringForm += decimalFormatter.format(-bValue);
                } else {
                    stringForm += " + ";
                    stringForm += decimalFormatter.format(bValue);
                }
                    
                stringForm += "x" + i;
            }
        }
            
        return stringForm;
    }
    
    public static String approximationValueToString(matriks independentVariables, double approximationValue) {
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

