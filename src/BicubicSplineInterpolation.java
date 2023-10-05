package src;

public class BicubicSplineInterpolation {

    public static matriks getX() { // Mendapatkan matriks X berukuran 16x16
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

    public static matriks getInversX(matriks X) { // menginversekan matriks X
        matriks identity = new matriks(16, 16);
        identity.Identitas(16,16);

        matriks invers_X = new matriks(16, 16);
        
        invers_X = Balikan.BalikanReduksi(X, identity);
        return invers_X;
    }

    public static matriks Matriks1Kolom(matriks M) { // Mengubah matriks 4x4 menjadi matriks 16x1 agar sesuai dengan pers. y = Xa
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

    public static matriks Matriks4Kolom(matriks M) { // Mengubah matriks 16x1 menjadi matriks 4x4
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

    public static double nilaiFungsi(matriks A, double a, double b) { // Menghitung nilai taksiran f(a,b) dengan rumus persamaan 1
    // f(x,y) = sigma i[0..3] (sigma j [0..3] (a_ij * x^i * y^j))
        double hasil = 0;

        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 3; j++) {
                hasil += A.getelmt(i, j) * Math.pow(a, i) * Math.pow(b, j);
            }
        }

        return hasil;
    }

}