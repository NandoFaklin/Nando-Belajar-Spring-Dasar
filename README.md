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
Kode : Register Doubleton Scope

Kode : Mengakses Doubleton Bean

Life Cycle

Life Cycle
Spring Container memiliki alur hidup, dan jika kita ingin berinteraksi dengan alur hidup nya Spring, kita juga bisa lakukan
Saat pertama kali Spring berjalan dan sudah selesai membuat bean, Spring akan memberitahu bean tersebut bahwa bean tersebut sudah siap, artinya semua dependencies sudah dimasukkan.
Dan ketika aplikasi Spring akan mati, Spring juga akan memberitahu semua bean bahwa bean tersebut akan dihancurkan

Life Cycle Callback
Secara default, bean tidak bisa tahu alur hidup Spring ketika selesai membuat bean dan ketika akan menghancurkan bean
Jika kita tertarik untuk bereaksi ketika alur hidup Spring terjadi, maka kita bisa implements interface InitializingBean dan DisposableBean
InitializingBean digunakan jika kita ingin bereaksi ketika Spring selesai membuat bean
https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/InitializingBean.html
DisposableBean digunakan jika kita ingin bereaksi ketika Spring akan menghancurkan bean
https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/DisposableBean.html 

Kode : Connection Class

Kode : LifeCycle Configuration

Kode : Mengakses Connection

Life Cycle Annotation

Life Cycle Annotation
Selain menggunakan interface InitializingBean dan DisposableBean, kita juga bisa menggunakan annotation untuk mendaftarkan callback method untuk lifecycle
Pada annotation @Bean, terdapat method initMethod() dan destoyMethod()
initMethod() digunakan untuk meregistrasikan method yang akan dipanggil ketika bean selesai dibuat
destroyMethod() digunakan untuk meregistrasikan method yang akan dipanggil ketika bean akan dihancurkan
Methodnya harus tanpa parameter, dan return value nya tidak akan dipedulikan, jadi sebaiknya gunakan void saja

Kode : Server Class

Kode : Server Configuration

Kode : Mengakses Server

@PostConstruct dan @PreDestroy
Selain menggunakan annotation @Bean, untuk menandai sebuah method adalah init method dan destroy method, kita juga bisa menggunakan annotation pada method nya secara langsung
Ini bisa digunakan untuk menghindari kita lupa menyebutkan init dan destroy method ketika membuat @Bean
@PostConstruct merupakan method yang ditandai harus dipanggil ketika bean selesai dibuat
@PreDestroy merupakan method yang ditandai harus dipanggil ketika bean akan dihancurkan
Jika sudah menggunakan annotation ini, kita tidak perlu lagi menyebutkan nya di @Bean

Kode : Server Class

Import

Import
Saat nanti kita membuat aplikasi, tidak mungkin kita hanya akan membuat satu Configuration Class
Biasanya kita akan membuat banyak sekali, tergantung seberapa kompleks aplikasi kita
Spring mendukung import Configuration Class lain jika dibutuhkan
Kita bisa menggunakan annotation @Import, lalu sebutkan Configuration Class mana yang ingin kita import
Ketika kita melakukan import, kita bisa import lebih dari satu class
https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/annotation/Import.html 

Kode : Foo dan Bar Configuration

Kode : Import Configuration

Kode : Mengakses Bean

Component Scan

Component Scan
Import adalah hal yang sangat menarik, karena kita bisa memecah Configuration Class menjadi banyak Class
Namun semakin lama, pasti aplikasi kita akan semakin besar, dan secara otomatis Configuration Class akan menjadi banyak
Melakukan import satu persatu sudah tidak menyenangkan lagi jika terlalu banyak
Spring memiliki fitur component scan, dimana kita bisa secara otomatis mengimport Configuration di sebuah package dan sub package nya secara otomatis
Untuk melakukan itu, kita bisa gunakan annotation @ComponentScan
https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/annotation/ComponentScan.html 

Kode : Component Scan

Component

Component
Sebelumnya kita sudah belajar membuat bean menggunakan method di configuration dengan annotation @Bean
Spring juga menyediakan cara otomatis tanpa harus kita membuat method @Bean
Kita bisa menandai sebuah class dengan annotation @Component, secara otomatis Spring akan membuatkan bean untuk class tersebut
@Component hanya mendukung pembuatan satu bean, jadi jika kita ingin membuat beberapa bean dengan tipe yang sama, kita tetap harus menggunakan @Bean method

Component Bean Name
Saat kita menggunakan @Component, nama bean akan secara otomatis dibuat otomatis oleh Spring 
Jika kita menggunakan @ComponentScan, secara otomatis bean name secara otomatis akan menggunakan nama class namun menjadi camelCase, misal CategoryService menjadi categoryService, ProductWeb menjadi productWeb
Jika kita menggunakan @Import, secara otomatis bean name akan menjadi NamaClass.class.getName()
Namun jika ingin membuat nama berbeda, kita juga bisa gunakan @Component(value=”namaBean”)

Annotation
Semua annotation yang sudah kita bahas di @Bean juga didukung di @Component
Seperti misalnya @Scope, @PostConstruct, @PreDestroy, @Lazy, @Primary dan lain-lain

Kode : Component

Kode : Component Configuration

Kode : Mengakses Component

Constructor-based
Dependency Injection

Dependency Injection
Sebelumnya untuk melakukan Dependency Injection di @Bean, kita bisa menambah parameter di method nya
Secara otomatis Spring akan memilih bean mana yang cocok untuk parameter tersebut
Bagaimana jika menggunakan @Component?
Untuk @Component kita bisa melakukan beberapa cara untuk Dependency Injection nya

Constructor-based Dependency Injection
Pertama yang bisa kita lakukan untuk Dependency Injection di @Component adalah menggunakan constructor parameter
Kita bisa menambahkan constructor yang memiliki parameter jika membutuhkan bean lain
Secara otomatis Spring akan mencarikan bean tersebut, dan ketika membuat bean @Component, Spring akan menggunakan bean yang dibutuhkan di constructor
Constructor-based Dependency Injection hanya mendukung satu constructor, jadi pastikan kita hanya membuat satu constructor

Kode : Constructor-based DI

Kode : Mengakses Bean

Multiple Constructor
Seperti di awal disebutkan bahwa Spring hanya mendukung satu constructor untuk Dependency Injection nya
Namun bagaimana jika terdapat multiple constructor?
Jika pada kasus seperti ini, kita harus menandai constructor mana yang akan digunakan oleh Spring
Caranya kita bisa menggunakan annotation @Autowired

Kode : Multiple Constructor

Setter-based
Dependency Injection

Setter-based Dependency Injection
Selain menggunakan constructor parameter, kita juga bisa menggunakan setter method jika ingin melakukan dependency injection
Namun khusus untuk setter method, kita perlu menambah annotation @Autowired pada setter method nya
Secara otomatis Spring akan mencari bean yang dibutuhkan di setter method yang memiliki annotation @Autowired
Setter-based DI juga bisa digabung dengan Constructor-based DI

Kode : Settter-based DI

Kode : Mengakses Bean

Field-based
Dependency Injection

Field-based Dependency Injection
Selain constructor dan setter, kita juga bisa melakukan dependency injection langsung menggunakan field
Caranya sama dengan setter, kita bisa tambahkan @Autowired pada fieldnya
Secara otomatis Spring akan mencari bean dengan tipe data tersebut
Field-based DI bisa digabung sekaligus dengan Setter-based DI dan Constructor-based DI
Khusus Field-based DI, Spring sendiri sudah tidak merekomendasikan penggunaan cara melakukan DI dengan Field

Kode : Field-based DI

Kode : Mengakses Bean

Qualifier

Qualifier
Seperti yang sudah dijelaskan di awal, jika terdapat bean dengan tipe data yang sama lebih dari satu, maka secara otomatis Spring akan bingung memilih bean yang mana yang akan digunakan
Kita perlu memilih salah satu menjadi primary, yang secara otomatis akan dipilih oleh Spring
Namun jika kita ingin memilih bean secara manual, kita juga bisa menggunakan @Qualifier
Kita bisa tambahkan @Qualifier di constructor parameter, di setter method atau di field

Kode : Duplicate Bean

Kode : Menggunakan Qualifier

Kode : Mengakses Bean

Optional Dependency

Optional Dependency
Secara default, semua dependency itu wajib
Artinya  jika Spring tidak bisa menemukan bean yang dibutuhkan pada saat DI, maka secara otomatis akan terjadi error
Namun jika kita memang ingin membuat sebuah dependency menjadi Optional, artinya tidak wajib
Kita bisa wrap dependency tersebut dengan menggunakan java.util.Optional<T>
Secara otomatis jika ternyata bean yang dibutuhkan tidak ada, maka tidak akan terjadi error
Kita bisa gunakan Optional<T> pada @Bean (method parameter) ataupun @Component (constructor parameter, setter method parameter, field)

Kode : Optional Configuration

Kode : Mengakses Bean

Object Provider
Selain menggunakan java.util.Optional<T>, kita juga bisa menggunakan ObjectProvider<T>
Yang membedakan pada ObjectProvider<T> adalah, jika ternyata bean nya lebih dari satu, ObjectProvider<T> bisa digunakan untuk mengambil semua bean tersebut
https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/ObjectProvider.html 

Kode : Object Provider

Kode : Optional Configuration

Kode : Mengakses Bean

Factory Bean

Factory Bean
Kadang ada kasus dimana sebuah class misal bukanlah milik kita, misal class third party library
Sehingga agak sulit jika kita harus menambahkan annotation pada class tersebut
Pada kasus seperti ini, cara terbaik untuk membuat bean nya adalah dengan menggunakan @Bean method
Atau di Spring, kita juga bisa menggunakan @Component, namun kita perlu wrap dalam class Factory Bean
https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/FactoryBean.html 

Kode : Payment Gateway Client

Kode : Factory Bean

Kode : Configuration

Kode : Mengakses Bean

Inheritance

Inheritance
Saat kita mengakses bean, kita bisa langsung menyebutkan tipe class bean tersebut, atau bisa juga dengan parent class / parent interface bean
Misal jika kita memiliki sebuah interface bernama MerchantService, lalu kita memiliki bean dengan object implementasi class nya MerchantServiceImpl, maka untuk mengakses bean nya, kita tidak hanya bisa menggunakan tipe MerchantServiceImpl, namun juga bisa dengan MerchantService
Namun perlu berhati-hati, jika misal MerchantService memiliki banyak bean turunan, pastikan tidak terjadi error duplicate

Kode : MerchantService

Kode : Configuration

Kode : Mengakses Bean

Bean Factory

Bean Factory
ApplicationContext adalah interface turunan dari BeanFactory
BeanFactory merupakan kontrak untuk management bean di Spring
Method-method yang sebelumnya kita gunakan untuk mengambil bean, sebenarnya merupakan method kontrak dari interface BeanFactory
https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/BeanFactory.html

Listable Bean Factory
Bean Factory hanya bisa digunakan untuk mengakses single bean, artinya jika terdapat bean dengan tipe yang sama, kita harus sebutkan satu per satu nama bean nya
Listable Bean Factory adalah turunan dari Bean Factory yang bisa kita gunakan untuk mengakses beberapa bean sekaligus
Dalam beberapa kasus, ini sangat berguna, seperti misal kita ingin mengambil semua bean dengan tipe tertentu
Application Context juga merupakan turunan dari interface Listable Bean Factory
https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/ListableBeanFactory.html 

Kode : Menggunakan Listable Bean Factory

Bean Post Processor

Bean Post Processor
Bean Post Processor merupakan sebuah interface yang bisa kita gunakan untuk memodifikasi proses pembuatan bean di Application Context
Bean Post Processor mirip seperti middleware, yang diakses sebelum bean di initialized dan setelah bean di initialized
Karena sangat flexible, bahkan Bean Post Processor bisa memodifikasi hasil object bean
https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/config/BeanPostProcessor.html 

Bean Id Generator
Misal sekarang kita akan coba membuat id unique untuk bean
Dimana kita akan membuat sebuah interface bernama IdAware, lalu memiliki method setId(String)
Kita akan membuat Bean Post Processor, dimana jika bean nya implements IdAware, kita akan setId(String) nya menggunakan UUID

Kode : IdAware Interface

Kode : Bean Post Processor

Kode : Component

Kode : Mengakses Bean

Ordered

Ordered
Saat kita membuat Bean Post Processor, kita bisa membuat lebih dari satu
Kadang ada kasus saat membuat beberapa Bean Post Processor, kita ingin membuat yang berurutan
Sayangnya secara default, Spring tidak menjamin urutan eksekusi nya
Agar kita bisa menentukan urutannya, kita bisa menggunakan interface Ordered
https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/core/Ordered.html 

Kode : Id Aware

Kode : Id Generator

Kode : Prefix Id Generator

Kode : Mengakses Bean

Aware

Aware
Spring memiliki sebuah interface berama Aware
Aware adalah super interface yang digunakan untuk semua Aware interface
Aware ini diperuntukkan untuk penanda agar Spring melakukan injection object yang kita butuhkan
Mirip seperti yang sudah kita lakukan ketika membuat IdAware menggunakan IdGenerator Bean Post Processor
Namun untuk ini, kita tidak perlu lagi membuat Bean Post Processor secara manual
https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/Aware.html 

Daftar Aware
Aware
Keterangan
ApplicationContextAware
Untuk mendapat application context
BeanFactoryAware
Untuk mendapat bean factory
BeanNameAware
Untuk mendapat nama bean
ApplicationEventPublisherAware
Untuk mendapat event publisher
EnvironmentAware
Untuk mendapat environment
dan lain-lain
...

Code : Aware

Kode : Mengakses Bean

Bean Factory Post Processor

Bean Factory Post Processor
Secara default, mungkin kita tidak akan pernah sama sekali membuat Application Context secara manual
Namun kadang ada keadaan kita ingin memodifikasi secara internal Application Context
Spring merekomendasikan kita untuk membuat Bean Factory Post Processor
https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/config/BeanFactoryPostProcessor.html 
https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/support/BeanDefinitionRegistryPostProcessor.html 

Kode : Bean Factory Post Processor

Kode : Mengakses Bean

Event Listener

Event Listener
Spring memiliki fitur Event Listener yang bisa kita gunakan untuk komunikasi antar class menggunakan Event
Event di Spring merupakan object turunan dari ApplicationEvent, sedangkan Listener di Spring merupakan turunan dari ApplicationListener
https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/ApplicationEvent.html 
https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/ApplicationListener.html 

Application Event Publisher
Ketika kita ingin mengirimkan event ke listener, kita bisa menggunakan object ApplicationEventPublisher, dimana ApplicationEventPublisher juga merupakan super interface dari ApplicationContext
Atau kita bisa menggunakan ApplicationEventPublisherAware untuk mendapatkan object ApplicationEventPublisher
https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/ApplicationEventPublisher.html 

Kode : Event

Kode : Listener

Kode : Mengirim Event

Kode : Mengakses Bean

Event Listener Annotation

Event Listener Annotation
Selain menggunakan interface ApplicationListener, kita juga bisa menggunakan Annotation untuk membuat Listener
Ini lebih flexible dibanding menggunakan interface ApplicationListener
Kita bisa menggunakan annotation @EventListener
https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/event/EventListener.html 

Kode : Event Listener Annotation

Cara Kerja Event Listener Annotation?
Annotation @EventListener bekerja dengan menggunakan Bean Post Processor bernama EventListenerMethodProcessor
EventListenerMethodProcessor mendeteksi jika ada bean yang memiliki method dengan annotation @EventListener, maka secara otomatis akan membuat listener baru, lalu meregistrasikannya ke ApplicationContext.addApplicationListener(listener)
https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/event/EventListenerMethodProcessor.html 

Spring Boot Application

Spring Boot Application
Sebelumnya kita selalu membuat Application Context secara manual, dan kita belum menggunakan fitur otomatis Spring Boot
Hal ini sengaja saya jelaskan agar kita tidak kaget bagaimana semua itu bisa berjalan secara otomatis di Spring Boot
Pada saat membuat aplikasi Spring Boot, kita akan mengganti @Configuration utama menjadi @SpringBootApplication
Isi dari @SpringBootApplication itu sendiri sebenarnya gabungan dari @Configuration dan banyak hal lain, seperti contohnya @ComponentScan
https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/autoconfigure/SpringBootApplication.html 

Kode : Foo Application

Spring Application
Selain @SpringBootApplication, untuk membuat Application Context nya, kita tidak perlu membuat manual, kita bisa gunakan class SpringApplication
Secara otomatis SpringApplication akan membuat ApplicationContext dan melakukan hal-hal yang dibutuhkan secara otomatis
https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/SpringApplication.html 

Kode : Spring Application

Spring Boot Test
Untuk membuat unit test di Spring Boot, kita bisa menggunakan annotation @SpringBootTest(classes={YourConfiguration.class})
Selanjutnya kita tidak perlu mengambil bean secara manual lagi menggunakan ApplicationContext, kita bisa menggunakan DI secara langsung di unit test nya menggunakan @Autowired
https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/test/context/SpringBootTest.html 

Kode : Test Foo Application

Startup Failure

Startup Failure
Salah satu yang menarik di Spring Boot adalah, Spring Boot memiliki fitur FailureAnalyzer
FailureAnalyzer digunakan untuk melakukan analisa ketika terjadi error startup yang menyebabkan aplikasi tidak mau berjalan
Spring sudah menyediakan banyak sekali class implementasi FailureAnalyzer yang bisa mempermudah kita menganalisa kesalaha ketika terjadi startup failure
https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/diagnostics/FailureAnalyzer.html 

Kode : Error Configuration

Kode : Tanpa Spring Boot

Kode : Dengan Spring Boot

Banner

Banner
Spring Boot memiliki fitur banner, dimana saat aplikasi Spring Boot berjalan, kita bisa menampilkan tulisan banner di console
Secara default fitur banner ini akan menyala dan akan mencari tulisan banner di classpath dengan nama banner.txt
Jika tidak ada file tersebut, maka secara otomatis akan menampilkan tulisan banner Spring Boot
Salah satu contoh tempat untuk membuat banner adalah http://www.bagill.com/ascii-sig.php 

Kode : Default Banner

Kode : banner.txt

Kode : Hasil Banner

Customizing Spring Application

Customizing Spring Application
Kadang ada kalanya kita ingin melakukan pengaturan di Spring Application sebelum Application Context nya dibuat
https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/SpringApplication.html 
Kita bisa menggunakan langsung SpringApplication, atau bisa juga menggunakan SpringApplicationBuilder
https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/builder/SpringApplicationBuilder.html 

Kode : Customizing Spring Application

Spring Application Event

Spring Application Event
Sebelumnya kita sudah belajar tentang Event Listener
Di Spring Boot, terdapat banyak sekali Event yang dikirim ketika aplikasi Spring Boot berjalan
Jika kita ingin, kita bisa membuat Listener untuk menerima event tersebut
https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/context/event/SpringApplicationEvent.html 

Daftar Event
Event
Keterangan
ApplicationStartingEvent
Dikirim ketika start aplikasi
ApplicationContextInitializedEvent
Dikirim ketika ApplicationContext sudah di initialisasi
ApplicationStartedEvent
Dikirim ketika aplikasi sudah berjalan
ApplicationFailedEvent
Dikirim ketika aplikasi gagal berjalan
dan lain-lain

Menambah Listener
Beberapa Event di Spring Boot Application Event di trigger bahkan sebelum Spring membuat Application Context
Oleh karena itu, jika kita buat menggunakan bean, bisa saja beberapa listener tidak akan dipanggil, karena bean nya belum dibuat
Agar lebih aman, kita bisa menambahkan listener ketika membuat SpringApplication 

Kode : Contoh Listener

Kode : Menambah Listener

Command Line Runner

Command Line Runner
Saat kita membuat aplikasi, kadang kita butuh argument yang diberikan pada main method
Spring Application bisa mengirim data argument tersebut secara otomatis ke bean yang kita buat
Kita hanya butuh membuat bean dari CommandLineRunner
CommandLineRunner secara otomatis akan di jalankan ketika Spring Application berjalan
https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/CommandLineRunner.html 

Kode : Contoh Command Line Runner

Application Runner

Application Runner
Selain CommandLineRunner, Spring Boot menyediakan fitur ApplicationRunner
Penggunaan ApplicationRunner sama seperti CommandLineRunnnner, hanya saja argument nya sudah di wrap dalam object ApplicationArguments
Yang menarik dari ApplicationArguments adalah, memiliki fitur parsing untuk command line argument
https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/ApplicationArguments.html 
https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/ApplicationRunner.html 

Kode : Application Runner

Spring Boot Plugin

Spring Boot Plugin
Saat kita membuat project Spring Boot, secara otomatis terdapat spring-boot-plugin di project maven kita
Plugin ini bisa digunakan untuk mempermudah saat kita menjalankan aplikasi Spring kita
Kita bisa gunakan perintah :mvn spring-boot:run
Untuk menjalankan aplikasi Spring Boot kita, kita harus memastikan bahwa hanya ada satu main class

Distribution File
Spring Boot plugin juga sudah menyediakan cara membuat distribution file aplikasi kita
Plugin ini akan mendeteksi main class di project kita, lalu membundle aplikasi kita beserta dependency yang dibutuhkan dalam satu file jar
Pastikan hanya terdapat satu main class, karena jika lebih dari satu, maka spring boot plugin akan melakukan komplen
Kita cukup gunakan perintah :mvn package
Secara otomatis akan terbuat single jar application



