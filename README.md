# Janji
Saya nama Rifky Fadhillah Akbar dengan Nim 2401248 mengerjakan tugas praktikum 6
dalam mata kuliah DPBO untuk keberkahan-Nya maka saya
tidak akan melakukan kecurangan seperti yang telah di spesifikasikan Aamiin.

# Desain Program
Program ini dibagi menjadi beberapa kelas, masing-masing dengan tanggung jawab yang spesifik:

**1. MainMenu.java**
  -	Ini adalah titik masuk (entry point) utama program.
  -	Bertugas menampilkan JFrame (jendela) menu utama.
  -	Menampilkan dua tombol: "Mulai Mainkan" dan "Tutup Program".
  -	Menangani klik tombol untuk memulai game atau menutup aplikasi.
    
**2. App.java**
  -	Ini adalah JFrame (jendela) utama untuk permainan.
  -	Bertanggung jawab untuk memegang dan menghubungkan komponen game.
  -	Kelas ini membuat JLabel untuk skor dan menempatkannya di bagian atas.
  -	Kelas ini juga membuat panel View (kanvas game) dan menempatkannya di tengah.
  -	Kelas ini membuat Logic dan View, lalu "memberi tahu" Logic tentang View dan App (dirinya sendiri) agar bisa berkomunikasi.
    
**3. Logic.java**
  - Kelas ini adalah otak dari permainan.
  - Memuat semua aset gambar (player, pipa, dan latar belakang).
  - Mengimplementasikan ActionListener untuk menjadi game loop utama (menggunakan Timer).
  -	Mengimplementasikan KeyListener untuk menerima input "Spasi" untuk (lompat) dan "R" untuk (restart).
  -	Mengelola semua status game: posisi player, gravitasi, daftar pipa (ArrayList<Pipe>), dan status gameOver.
  -	Membuat pipa baru secara berkala (menggunakan Timer kedua).
  -	Mendeteksi tabrakan (checkCollisions()).
  -	Menghitung skor dan memanggil app.updateScore() untuk memperbarui label.
  -	Menyediakan metode restartGame() untuk mereset game ke kondisi awal.
    
**4. View.java**
  -	Ini adalah JPanel (kanvas) tempat semua grafis digambar.
  -	Tugas utamanya hanya menggambar.
  -	Meng-override metode paintComponent() untuk menggambar gambar latar belakang terlebih dahulu, baru kemudian menggambar Player dan semua Pipe di atasnya.
  -	Meminta fokus keyboard (requestFocusInWindow()) agar bisa menerima input.
    
**5. Player.java**
  -	Kelas data sederhana yang menyimpan semua informasi tentang pemain (posisi x/y, lebar, tinggi, gambar, dan kecepatan vertikal velocityY).
    
**6. Pipe.java**
  -	Kelas data sederhana yang menyimpan informasi tentang satu pipa (posisi x/y, lebar, tinggi, gambar, dan boolean passed untuk melacak skor).
# Penjelasan Alur Program
Berikut adalah alur jalannya program dari awal hingga akhir:
1.	Mulai Program: Pengguna menjalankan MainMenu.java. Jendela menu utama muncul.
2.	Klik "Mulai": Pengguna menekan tombol "Mulai Mainkan".
    -	Jendela MainMenu ditutup (dispose()).
    -	Sebuah instance new App() dibuat, yang memunculkan jendela game.
3.	Inisialisasi Game:
    -	App membuat JLabel (skor), Logic (otak), dan View (kanvas).
    -	Logic memulai constructor-nya:
        - Memuat semua aset gambar (player, pipa, latar belakang).
        - Memulai dua Timer: gameLoop (untuk pergerakan) dan pipesCooldown (untuk membuat pipa baru).
    -	View meminta fokus keyboard.
4.	Game Berlangsung (Game Loop):
    -	Timer gameLoop di Logic berjalan terus-menerus (sekitar 60 kali per detik).
    -	Setiap tick, Logic memanggil move():
        -	Gravitasi diterapkan ke player (posisi Y berubah).
        -	Semua Pipe di ArrayList digerakkan ke kiri (posisi X berubah).
        -	Logic memeriksa apakah player baru saja melewati sepasang pipa. Jika ya, score bertambah dan label skor diperbarui (app.updateScore()).
        -	Logic memanggil checkCollisions() untuk memeriksa tabrakan.
    -	Setelah move(), Logic memanggil view.repaint().
    -	View.paintComponent() dipanggil, menggambar ulang latar belakang, lalu player, lalu semua pipa di posisi baru mereka.
5.	Input Pemain:
    -	Pemain menekan "Spasi".
    -	View menangkap input dan meneruskannya ke Logic.keyPressed().
    -	Logic mengubah velocityY pemain agar melompat ke atas.
6.	Game Over:
    -	Logic.checkCollisions() mendeteksi tabrakan (dengan pipa atau tanah).
    -	Logic mengatur gameOver = true.
    -	Pada tick Timer berikutnya, Logic.actionPerformed() melihat gameOver bernilai true, lalu menghentikan gameLoop dan pipesCooldown. Permainan "membeku".
7.	Restart:
    -	Saat game "beku", pemain menekan "R".
    -	Logic.keyPressed() mendeteksi "R" dan memanggil Logic.restartGame().
    -	Metode restartGame() mereset posisi player, mengosongkan daftar pipa, mereset score = 0 (dan memperbarui label), lalu memulai kembali kedua Timer.
    -	Alur kembali ke langkah 4 (Game Berlangsung).

# Dokumentasi

![bandicam 2025-11-02 20-15-14-474](https://github.com/user-attachments/assets/c195fd68-594f-4730-9abc-a558bffab83f)


