# Tugas Besar 1 Aljabar Linier dan Geometri
## Sistem Persamaan Linier, Determinan, dan Aplikasinya

Program ini adalah program manipulasi matriks yang dapat digunakan untuk mencari:
- Solusi sistem persamaan linier
- Balikan matriks
- Determinan matriks
- Interpolasi polinomial
- Regresi linier berganda
- Bicubic spline interpolation

## Anggota Kelompok
- Agil Fadillah Sabri (13522006)
- Bastian Hendramukti Suryapratama (13522034)
- Haikal Assyauqi (13522052)

## Cara Menjalankan Program
1. Clone repository
    ```
    git clone https://github.com/bastianhs/Algeo01-22006.git
    ```
2. Masuk ke dalam folder utama project
    ```
    cd Algeo01-22006
    ```
3. Buat file class di folder src
   (Filenya error jika dijalankan langsung di folder bin)
    ```
    javac src/Landing.java
    ```
3. Jalankan program
    ```
    java src/Landing
    ```

## Format File Masukan
1. Untuk file masukan sistem persamaan linier, balikan, dan determinan, format file berupa nilai-nilai yang dipisahkan dengan 1 spasi dalam setiap kolom dan setiap baris diakhiri dengan enter/newline, kecuali baris terakhir.

    Contoh:
    ```
    1 1 -1 -1 1
    2 5 -7 -5 -2
    2 -1 1 3 4
    5 2 -4 2 6
    ```

2. Untuk file masukan interpolasi polinom, setiap baris hanya berisi 1 titik yang terdiri dari nilai x dan y (kecuali baris terakhir). Baris terakhir hanya berisi nilai x. Setiap kolom dipisahkan dengan 1 spasi dan setiap baris diakhiri dengan enter/newline, kecuali baris terakhir.

    Contoh:
    ```
    8.0 2.0794
    9.0 2.1972
    9.5 2.2513
    8.3
    ```

3. Untuk file masukan regresi linier berganda, setiap baris hanya berisi 1 titik yang terdiri dari n buah nilai x dan 1 buah nilai y dengan urutan dari kiri ke kanan: x1i, x2i, x3i, ..., xni, yi (kecuali baris terakhir). Baris terakhir hanya berisi n buah nilai x. Setiap kolom dipisahkan dengan 1 spasi dan setiap baris diakhiri dengan enter/newline, kecuali baris terakhir.

    Contoh:
    ```
    72.4 76.3 29.18 0.90
    41.6 70.3 29.35 0.91
    34.3 77.1 29.24 0.96
    35.1 68.0 29.27 0.89
    10.7 79.0 29.78 1.00
    50 76 29.30
    ```

4. Untuk file masukan bicubic spline interpolation, di dalamnya berisi matriks berukuran 4 x 4 yang berisi konfigurasi nilai fungsi dan turunan berarah di sekitarnya, diikuti dengan nilai a dan b untuk mencari nilai f(a, b). Setiap kolom dipisahkan dengan 1 spasi dan setiap baris diakhiri dengan enter/newline, kecuali baris terakhir.

    Contoh:
    ```
    1 2 3 4
    5 6 7 8
    9 10 11 12
    13 14 15 16
    0.5 0.5
    ```
