# Nando-Belajar-Spring-Dasar
Tugas Teori Nando Faklin TK2B 2101082028
# -belajar-spring-dasar

## Pengenalan Spring

Pengenalan Spring Framework
Spring Framework adalah framework paling populer di Java
Saking populernya, Spring Framework sampai mengalahkan popularitas Java Enterprise sendiri
Spring Framework dibuat sekitar tahun 2003 oleh Rod Johnson, yang dibuat sebagai alternative Java Enterprise
Spring Framework semakin populer karena sangat ringan dan mudah digunakan dibandingkan Java Enterprise
https://spring.io/ 

## Pengenalan Spring Boot
Spring Boot merupakan framework untuk mempermudah pembuatan aplikasi Spring Framework
Dahulu untuk menggunakan Spring Framework, untuk pemula tidaklah mudah, karena terlalu banyak yang harus dilakukan sebelum bisa membuat aplikasi
Spring Boot menjadikan kompleksitas tersebut ditangani secara otomatis oleh Spring Boot, sehingga kita bisa membuat aplikasi Spring Framework secara cepat tanpa harus melakukan pengaturan apapun
Spring Boot sekarang sudah menjadi salah satu framework wajib ketika kita ingin membuat aplikasi Spring Framework

# Membuat Project

Membuat Project
https://start.spring.io/ 

# Inversion of Control

# Application Context

Application Context
ApplicationContext adalah sebuah interface representasi container IoC di Spring
ApplicationContext adalah inti dari Spring Framework
ApplicationContext banyak sekali class implementasinya, secara garis besar dibagi menjadi 2 jenis implementasi, XML dan Annotation
Pada versi Spring 3, XML masih menjadi pilihan utama, namun sekarang sudah banyak orang beralih dari XML ke Annotation, bahkan Spring Boot pun merekomendasikan menggunakan Annotation untuk membuat aplikasi Spring
https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/ApplicationContext.html 

# Kode:HelloWorldConfiguration

import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldConfiguration {

}

# Configuration Class adalah sebuah class yang terdapat annotation @Configuration pada class tersebut

# Membuat Application Context
Selanjutnya, setelah membuat Class Configuration, kita bisa menggunakan class AnnotationConfigApplicationContext untuk membuat Application Context
https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/annotation/AnnotationConfigApplicationContext.html 

# Kode : Membuat Application Context

ApplicationContext context =
    new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);
Assertions.assertNotNull(context);

## Singleton
Singleton adalah salah satu Design Patterns untuk pembuatan objek, dimana sebuah object hanya dibuat satu kali saja
Dan ketika kita membutuhkan object tersebut, kita hanya akan menggunakan object yang sama
https://refactoring.guru/design-patterns/singleton 

# Kode : Singleton Class

Public class Database {
    private static Database databases;

    public static Databases getInstance() {
        if (database == null){
            database = new Database();
        }
        return database;
    }
    private Database(){
        //can not instanliste database class
    }
    Database database1 = Database.getInstance();
    Database database2 = Database.getInstance();

    Assertions.assertSame(database1, database2);
}

# privat agar tidak bisa diakses dari luar Sehingga user terpaksa menggunakan method static tersebut ketika ingin membuat object nya


## Bean
Saat sebuah object kita masukkan kedalam Spring Container IoC, maka kita sebut object tersebut adalah Bean
Secara default, bean merupakan singleton, artinya jika kita mengakses bean yang sama, maka dia akan mengembalikan object yang sama. Kita juga bisa mengubahnya jika tidak ingin singleton, nanti akan kita bahas di materi tersendiri

# Kode : Membuat Bean

@Configuration
public class BeanConfiguration{

    @Bean 
    public Foo foo()
    Foo foo = new Foo();
    return foo;
}

# method Bean Akan dieksekusi oleh spring 

# Mengakses Bean
Setelah kita membuat bean, secara otomatis semua object akan di-manage oleh Application Context
Untuk mengakses bean, kita bisa menggunakan method getBean milik Application Context

# Kode : Mengakses Bean

Foo foo1 = applicationContext.getBean(Foo.class);
Foo foo2 = applicationContext.getBean(Foo.class);

Assertions.assertSame(foo1 , foo2);

## Duplicate Bean
Di Spring, kita bisa mendaftarkan beberapa bean dengan tipe yang sama
Namun perlu diperhatikan, jika kita membuat bean dengan tipe data yang sama, maka kita harus menggunakan nama bean yang berbeda
Selain itu, saat kita mengakses bean nya, kita wajib menyebutkan nama bean nya, karena jika tidak, Spring akan bingung harus mengakses bean yang mana

# Kode : Duplicate Bean

@Bean 
public Foo foo1{
    Foo foo = new foo();
    return foo;
}

@Bean 
public Foo foo2{
    Foo foo = new foo();
    return foo;
}

# Kode : Mengakses Duplicate Bean

Foo foo1 = applicationContext.getBean(name: "foo1", Foo.class);
Foo foo2 = applicationContext.getBean(name: "foo2", Foo.class);

Assertions.assertNotSame(foo1, foo2);

## Primary Bean
Jika terjadi duplicate bean, selain kita sebutkan nama bean nya ketika ingin mengakses bean nya, kita  juga bisa pilih salah satu bean menjadi primary
Dengan memilih salah satunya menjadi primary, secara otomatis jika kita mengakses bean tanpa menyebutkan nama bean nya, secara otomatis primary nya yang akan dipilih
Untuk memilih primary bean, kita bisa tambahkan annotaiton @Primary

# Kode : Primary Bean

@Primary
@Bean 
public Foo foo1{
    Foo foo = new foo();
    return foo;
}

@Bean 
public Foo foo2{
    Foo foo = new foo();
    return foo;
}

# Kode : Mengakses Primary Bean

Foo foo = applicationContext.getBean(Foo.class);
Foo foo1 = applicationContext.getBean(name: "foo1", Foo.class);
Foo foo2 = applicationContext.getBean(name: "foo2", Foo.class);

Assertions.assertSame(foo, foo1);
Assertions.assertNotSame(foo1, foo2);


## Mengubah Nama Bean
Secara default, nama bean diambil dari nama method
Namun kadang-kadang kita tidak ingin menggunakan nama method untuk nama bean
Saat project kita sudah besar, kadang bisa jadi nama method sama, walaupun isi bean nya berbeda, dan di Spring, nama bean itu unik, tidak boleh sama
Jika kita ingin mengubah nama bean, kita bisa menggunakan method value() milik annotation @Bean

# Kode : Mengubah Nama Bean

@Primary
@Bean(value = "fooFirst")
public Foo foo1{
    Foo foo = new Foo();
    return foo;
}

@Bean(value = "fooSecond")
public Foo foo2{
    Foo foo = new Foo();
    return foo;
}

# Kode : Mengakses Bean

Foo foo = applicationContext.getBean(Foo.class);
Foo foo1 = applicationContext.getBean(name: "fooFirst", Foo.class);
Foo foo2 = applicationContext.getBean(name: "fooSecond", Foo.class);

Assertions.assertSame(foo, foo1);
Assertions.assertNotSame(foo1, foo2);

## Dependency Injection
Saat kita membuat object, sudah pasti kita sering membuat object yang tergantung dengan object lain
Dependency Injection (DI) adalah teknik dimana kita bisa mengotomatisasi proses pembuatan object yang tergantung dengan object lain, atau kita sebut dependencies
Dependencies akan secara otomatis di-inject (dimasukkan) kedalam object yang membutuhkannya
Spring Framework sejak awal dibilang sebuah framework IoC yang memang cara kerjanya menggunakan Dependency Injection

# Kode : Class FoorBar

@AllArgsConstructor
@Data
public clss FooBar{
    private Foo foo;

    private Bar bar;
}

# Kode : Tanpa Dependency Injection

var foo = new Foo();
var bar = new Bar();

var fooBar = new FooBar(foo, bar);

## Spring Dependency Injection
Spring sejak awal dikenal dengan framework untuk Dependency Injection
Ketika kita membuat method untuk bean di Spring, kita bisa menambahkan parameter 
Secara otomatis Spring akan mencarikan bean lain yang sesuai dengan tipe parameter tersebut
Jika ternyata tidak ada bean yang cocok, maka secara otomatis akan terjadi error
Dan jika ternyata terdapat bean lebih dari satu, secara otomatis akan terjadi error, kecuali terdapat primary bean

# Kode : Bean Dependency Injection

@Bean 
public Barbar() {
    return new Bar();

}

@Bean 
public FooBar fooBar(Foo foo, Bar bar) {
    return new FooBar(foo, bar);
}

# Kode : Menggunakan Dependency Injection

Foo foo = applicationContext.getBean(name: "fooFirs",Foo.class);
Bar bar = applicationContext.getBean(Bar.class);
FooBar foobar = applicationContext.getBean(FooBar.class);

Assertions.assertSame(fooBar.getBar(), bar);
Assertions.assertNotSame(fooBar.getFoo(), foo);


## Memilih Dependency
Kadang saat menggunakan DI, kita ingin memilih object mana yang ingin kita gunakan
Saat terdapat duplicate bean dengan tipe data yang sama, secara otomatis Spring akan memilih bean yang primary
Namun kita juga bisa memilih secara manual jika memang kita inginkan
Kita bisa menggunakan annotation @Qualifier(value=”namaBean”) pada parameter di method nya

# Kode : Memilih Dependency

@Bean 
public FooBar fooBar(@Qualifier("fooSecond") Foo foo, Bar bar){
    return new FooBar(foo, bar);
}

# Kode : Mengakses Bean

Foo foo = applicationContext.getBean(name: "fooFirs",Foo.class);
Bar bar = applicationContext.getBean(Bar.class);
FooBar foobar = applicationContext.getBean(FooBar.class);

Assertions.assertSame(fooBar.getBar(), bar);
Assertions.assertSame(fooBar.getFoo(), foo);

## Circular Dependencies
Hati-hati dengan curcular dependencies
Circular dependencies adalah kasus dimana sebuah lingkaran dependency terjadi, misal bean A membutuhkan bean B, bean B membutuhkan bean C, dan ternyata bean C membutuhkan A
Jika terjadi cyclic seperti ini, secara otomatis Spring bisa mendeteksinya, dan akan mengganggap bahwa itu adalah error

# Kode : Contoh Circular Configuration

@Bean 
public CyclicA cyclicA(CyclicB cyclicB) {
    return new CyclicA(cyclicB);
}

@Bean 
public CyclicB cyclicB(CyclicC cyclicC) {
    return new CyclicB(cyclicC);
}

@Bean 
public CyclicC cyclicC(CyclicA cyclicA) {
    return new CyclicC(cyclicA);
}

# Kode : Error Circular Dependencies

try {
    ApplicationContext applicationContext = 
        new AnnotationConfigApplicationContext(CyclicConfiguration.class);

    Assertions.fail("It must be fail because cyclic");
}cath (BeansException exeption) {
    exeption.printStackTracer();
}

## Depends On
Saat sebuah bean membutuhkan bean lain, secara otomatis bean tersebut akan dibuat setelah bean yang dibutuhkan dibuat
Namun bagaimana jika bean tersebut tidak membutuhkan bean lain, namun kita ingin sebuah bean dibuat setelah bean lain dibuat?
Jika ada kasus seperti itu, kita bisa menggunakan annotation @DependsOn(value={”namaBean”})

# Kode : Depends On

@Bean
@DependsOn(value = {"bar"})
public Foo foo(); {
 log.info("Create new Foo");
 return new Foo();
}

@Bean
public Bar bar(); {
 log.info("Create new Bar");
 return new Bar();
}

## Lazy Bean
Secara default, bean di Spring akan dibuat ketika aplikasi Spring pertama kali berjalan
Oleh karena itu, kadang ketika aplikasi Spring pertama berjalan akan sedikit lambat, hal ini dikarenakan semua bean akan dibuat di awal
Namun jika kita mau, kita juga bisa membuat sebuah bean menjadi lazy (malas), dimana bean tidak akan dibuat, sampai memang diakses atau dibutuhkan

# Kode : Lazy Bean

@Lazy
@Bean
@DependsOn(value = {"bar"})
public Foo foo(); {
    log.info("Create new Foo");
    return new Foo();
}

## cope
Scope merupakan strategy cara sebuah object dibuat
Secara default strategy object di Spring adalah singleton, artinya hanya dibuat sekali, dan ketika kita akses, akan mengembalikan object yang sama
Namun kita juga bisa mengubah scope bean yang kita mau di Spring
Untuk mengubah scope sebuah bean, kita bisa tambahkan annotation @Scope(value=”namaScope”)

## ean Scope
Scope
Keterangan
singleton
(Default) Hanya dibuat sekali dalam Spring IoC
prototype
Selalu dibuat object baru setiap kali bean diakses
request
Dibuat baru per HTTP Request (hanya untuk Web App)
session
Dibuat baru per HTTP Session (hanya untuk Web App)
application
Dibuat baru per ServletContext (hanya untuk Web App)
websocket
Dibuat baru per WebSocket (hanya untuk WebSocket App)

# Kode : Scope Prototype

@Bean
@DScope("prototype")
public Foo foo(); {
    log.info("Create new Foo");
    return new Foo();
}

# Kode : Mengakses Bean

Foo foo1 = applicationContext.getBean(Foo.class);
Foo foo2 = applicationContext.getBean(Foo.class);
Foo foo3= applicationContext.getBean(Foo.class);

Assertions.assertNotSame(foo1, foo2);
Assertions.assertNotSame(foo2, foo3);
Assertions.assertNotSame(foo3, foo1);

##Membuat Scope
Jika scope yang disediakan oleh Spring tidak bisa memenuhi kebutuhan kita, kita juga bisa membuat scope sendiri
Caranya dengan membuat class yang implement interface Scope
https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/config/Scope.html 
Selanjutnya untuk meregistrasikannya, kita bisa membuat bean CustomScopeConfigurer
https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/config/CustomScopeConfigurer.html 

# Kode : Doubleton Scope

@Overide
public Object get(String name, ObjectFactory<?>){
    counter++;
    if(objects.size() = 2){
        return objects.get((int) (counter % 2));
    }else {
        Object object = objectFactory.getObject();
        objects.add(object);
        return object;
    }
}
@Overide
public Object remove(String name){
    counter++;
    if(objects.size() != 0){
        return objects.remove (index: 0));
    }
    return null;
}

# Kode : Register Doubleton Scope
