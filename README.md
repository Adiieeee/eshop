# Eshop
### Muhammad Adiansyah - 2306244980
---
## Week 3
### Refleksi 1
> Explain what principles you apply to your project!
- SOLID
- Don't Repeat Yourself
- Keep It Simple, Stupid
- You Aren't Gonna Need It
- Model-View-Controller
  
> Explain the advantages of applying SOLID principles to your project with examples
a. Maintability
  - Dengan memisahkan tanggung jawab dan menggunakan antarmuka, perubahan hanya berdampak pada bagian terkait.
  - Memisahkan tanggung jawab antara CarController dan CarService, serta menggunakan antarmuka CarService.
b. Testability
  - Injeksi dependensi memungkinkan pengujian unit tanpa ketergantungan pada implementasi konkret.
  - Menggunakan antarmuka CarService dan injeksi dependensi untuk mempermudah pengujian.
c. Flexibility & Extensibility
  - Kode dapat diperluas tanpa harus mengubah struktur yang sudah ada.
  - Menggunakan antarmuka CarService, memungkinkan berbagai implementasi tanpa mengubah kode utama.
d. Readability & Understandability
  - Kode lebih jelas karena setiap kelas dan antarmuka memiliki tanggung jawab spesifik.
  - Memisahkan CarController dan CarService, serta membuat antarmuka CarService yang lebih spesifik.

> Explian the disadvantages of not applying SOLID principles to yout projext with examples
a. Tight Coupling
  - Tanpa DIP (Dependency Inversion Principle), modul tingkat tinggi bergantung langsung pada implementasi konkret, membuat perubahan sulit.
  - Menggunakan antarmuka CarService dan injeksi dependensi agar CarController tidak bergantung pada implementasi spesifik.
b. Code Duplication
  - Tanpa SRP (Single Responsibility Principle), kelas memiliki tanggung jawab ganda yang menyebabkan kode berulang dan sulit dipelihara.
  - Memisahkan logika bisnis ke dalam CarService, sehingga tidak ada duplikasi dalam CarController.
c. Difficulty in Extending Functionality
  - Tanpa OCP (Open/Closed Principle), setiap fitur baru mengharuskan perubahan pada kode lama, meningkatkan risiko bug.
  - Menggunakan CarService sebagai antarmuka agar bisa ditambahkan implementasi baru tanpa mengubah kode yang ada.
d. Poor Readability & Maintainability
  - Tanpa SRP dan ISP (Interface Segregation Principle), kelas menjadi terlalu besar dan sulit dimengerti.
  - Memecah CarService menjadi antarmuka lebih kecil dan spesifik agar klien hanya menggunakan metode yang relevan.
  
---
## Week 2
### Refleksi 1
> List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.
- Membenarkan return pada file ProductController agar tidak terjadi error saat mendeploy
- Membenarkan pmd pada workflow GitHub

> Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment?
- Menurut saya implementasi saya terhadap Continous Integration dan Continous Deployment sudah sesuai karena saya menggunakan GiHub Actions dan koyeb yang memenuhi definisi CI/CD.

---
## Week 1
### Refleksi 1
> Write clean code principles and secure coding practices that have been applied to your code.
- Saya sudah menggunakan penggunaan nama yang benar sesuai dengan prinsip meaningful names dalam penamaan variable, function, class dan lainnya.
- Saya menggunakan beberapa input validation untuk memastikan hal yang diinput sesuai dengan yang diminta seperti pada productQuantity saya memastikan hanya bisa menginput int positif.
- Saya juga sudah menerapkan prinsip dimana penggunaan function hanya untuk satu fungsi dan tidak bertumpuk tumpuk, membuat function menjadi jelas dan lebih singkat.

> If you find any mistake in your source code, please explain how to improve your code.
- Tidak adanya validasi input yang memeriksa apakah input sudah sesuai, seperti pada productQuantity tidak ada validasi bahwa input harus int positif. Cara untuk mengimprovenya adalah dengan membuat sistem validasi input.
- Tidak adanya sistem ID yang dibuat secara otomatis. Cara untuk mengimprovenya adalah dengan menggunkan sistem auto generation UUID

### Refleksi 2

> After writing the unit test, how do you feel? How many unit tests should be made in a class? How to make sure that our unit tests are enough to verify our program? It would be good if you learned about code coverage. Code coverage is a metric that can help you understand how much of your source is tested. If you have 100% code coverage, does that mean your code has no bugs or errors?
- Menurut saya banyaknya unit test pada sebuah class tergantung dari berapa banyak kegunaan dari fitur yang ingin di test. tapi sebaiknya dalam unit test diperlukan setidaknya dua scenario yaitu case positive dan case negative.
- Menurut saya titik dimana unit test kita sudah cukup untuk memverifikasi program kita adalah saat unit test kita sudah mengcover semua case yang mungkin terjadi.
- 100% Code coverage merupakan patokan yang bagus tapi bukan berarti tidak ada edge case dimana error bisa terjadi.

> Suppose that after writing the CreateProductFunctionalTest.java along with the corresponding test case, you were asked to create another functional test suite that verifies the number of items in the product list. You decided to create a new Java class similar to the prior functional test suites with the same setup procedures and instance variables. What do you think about the cleanliness of the code of the new functional test suite? Will the new code reduce the code quality? Identify the potential clean code issues, explain the reasons, and suggest possible improvements to make the code cleaner
- Jika kita langung menulis langsung dengan class yang sama maka kode akan menjadi tidak bersih karena terdapat duplikasi. penggunaan selector yang tidak stabil, dan kurangnya assertions. Duplikasi terjadi pada instance variables, metode setup, dan inisialisasi Selenium, yang seharusnya direfaktor ke dalam superclass agar lebih terorganisir. Dengan menerapkan refaktor pada setup, menggunakan selector yang lebih stabil, serta menambahkan assertions yang relevan, kode akan lebih bersih, mudah dipelihara
