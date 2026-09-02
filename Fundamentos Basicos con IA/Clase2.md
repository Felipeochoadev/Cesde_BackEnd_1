# Fundamentos Básicos de JAVA

¡Hola, futuros desarrolladores! Soy su profesor de Backend 1. En este documento, daremos nuestros primeros pasos en el maravilloso mundo de Java. Iremos paso a paso, asegurándonos de que cada concepto quede claro antes de avanzar. 

## Objetivo
Comprender los pilares esenciales de la programación en Java, desde cómo guardar un simple dato en memoria hasta cómo estructurar un programa básico usando clases y objetos. Esto formará la base sólida que necesitan para convertirse en excelentes desarrolladores Backend.

---

## 1. Variables
**¿Qué son?** Imagina una variable como una caja con una etiqueta donde puedes guardar un dato. Esta "caja" vive en la memoria de la computadora mientras el programa se ejecuta.

**Ejemplo:**
```java
// Declaramos una variable llamada "edad" y le guardamos el número 25
int edad = 25; 
```

**Ejercicio simple:**
Imagina que quieres guardar tu edad y tu peso. ¿Cómo llamarías a esas variables? Escríbelo en un papel.

**Solución práctica:**
Usaremos la convención `camelCase` (empezar en minúscula y cada palabra nueva con mayúscula).
```java
int miEdad = 28;
double miPeso = 72.5;

System.out.println("Tengo " + miEdad + " años y peso " + miPeso + " kg.");
```

---

## 2. Tipos de variables: El Catálogo Completo

**¿Qué son? (Definición profunda)**
Java es un lenguaje **fuertemente tipado**. Esto significa que no puedes simplemente crear una "caja" genérica; debes decirle al compilador exactamente qué forma y tamaño tendrá esa caja antes de usarla. En Java, los tipos de variables se dividen en dos grandes familias: **Primitivos** (guardan el valor real y son súper rápidos) y **De Referencia** (guardan la dirección de memoria donde vive un Objeto).

**Los 8 Tipos Primitivos (La base de Java):**

*   **Números Enteros (Sin decimales):**
    *   `byte`: Ocupa 8 bits de memoria. Guarda números del -128 al 127. Útil para ahorrar memoria en grandes arreglos numéricos o manejar datos a nivel de bytes (archivos/red).
    *   `short`: Ocupa 16 bits. Guarda números del -32,768 al 32,767. Poco usado hoy en día.
    *   `int`: Ocupa 32 bits. Guarda números hasta los 2.14 mil millones. **Es el estándar por defecto** para casi cualquier número sin decimales.
    *   `long`: Ocupa 64 bits. Guarda números ridículamente grandes (trillones). **Vital en Backend** para IDs de bases de datos o timestamps (milisegundos). *Nota: Debes poner una 'L' al final del número.*

*   **Números de Punto Flotante (Con decimales):**
    *   `float`: Ocupa 32 bits. Precisión de 6 a 7 dígitos decimales. *Nota: Debes poner una 'f' al final del número, de lo contrario Java lo rechazará.*
    *   `double`: Ocupa 64 bits. Precisión de 15 dígitos decimales. **Es el estándar por defecto** para cálculos matemáticos con decimales (aunque en finanzas reales se usa una clase especial llamada `BigDecimal`).

*   **Caracteres y Lógica:**
    *   `char`: Ocupa 16 bits. Guarda un **único** carácter bajo el estándar Unicode (letras, símbolos). Siempre va entre comillas simples (ej. `'A'`, `'@'`).
    *   `boolean`: Representa un bit de información: Verdadero (`true`) o falso (`false`). Es la base de toda la lógica del programa.

**El Tipo Especial: `String`**
*   `String` **no es un tipo primitivo**, es una Clase (Tipo de Referencia), pero se usa con tanta frecuencia que Java le da un trato especial. Guarda cadenas de texto de cualquier longitud y siempre va entre comillas dobles (ej. `"Hola"`).

**Ejemplo Práctico Completo:**
```java
// ENTEROS
byte edadPerro = 12;
int visitasWeb = 500000;
long idBaseDeDatos = 9876543210L; // <-- Nota la 'L' al final

// DECIMALES
float porcentaje = 15.5f; // <-- Nota la 'f' al final
double pi = 3.14159265359;

// OTROS
char letraCalificacion = 'A'; // <-- Comillas simples
boolean servidorEncendido = true;
String mensaje = "Conexión exitosa a la base de datos"; // <-- Comillas dobles
```

**Ejercicio simple:**
Imagina que estás diseñando el Backend de YouTube. Identifica qué tipo exacto de variable usarías para: 
a) El número total de visualizaciones del video "Baby Shark" (que supera los 10 mil millones). 
b) Si el video está en estado "Oculto" o "Público". 
c) La letra inicial del nombre del canal.

**Solución práctica:**
```java
// a) long: Un 'int' normal explotaría (llega hasta 2.14 mil millones). 
// Necesitamos los 64 bits del long para cifras globales.
long visualizacionesTotales = 13000000000L; 

// b) boolean: Al ser solo dos estados posibles (oculto/no oculto), es la opción más eficiente.
boolean isOculto = false; 

// c) char: Como es solo un carácter, un char es más ligero que un String completo.
char letraInicial = 'Y'; 

System.out.println("Canal empieza con: " + letraInicial);
System.out.println("Vistas: " + visualizacionesTotales + " | ¿Oculto?: " + isOculto);
```
---

## 3. Casting de variables (Conversión de tipos)
**¿Qué es?** A veces necesitamos meter el contenido de una caja en otra de diferente tipo. El "casting" es forzar a un tipo de dato a convertirse en otro.

**Ejemplo:**
```java
double numeroDecimal = 9.99;
// Convertimos el decimal a entero forzosamente. Se pierde el .99
int numeroEntero = (int) numeroDecimal; // El resultado es 9
```

**Ejercicio simple:**
Si tienes `double peso = 75.8;` y haces `int pesoEntero = (int) peso;`, ¿Qué valor se guardará en `pesoEntero`?

**Solución práctica:**
Se guardará el valor `75`, ya que se trunca la parte decimal.
```java
double peso = 75.8;
int pesoEntero = (int) peso;

System.out.println("Peso original: " + peso); // Imprime 75.8
System.out.println("Peso casteado: " + pesoEntero); // Imprime 75
```

---
## 4. Clases Wrap (Clases Envoltorio): Investigación Profunda

**¿Qué son? (Definición y Propósito)**
En Java, existen dos grandes mundos que viven en la memoria: los **datos primitivos** (como `int`, `double`, `boolean`) que son ultrarrápidos y ligeros, y los **Objetos**, que son más pesados, pero tienen métodos, propiedades e inteligencia. 
Las Clases Wrap (o clases envolventes) son el puente entre estos dos mundos. Toman un dato primitivo y lo "envuelven" (wrap) dentro de un Objeto.
*   `int` -> `Integer`
*   `double` -> `Double`
*   `char` -> `Character`
*   `boolean` -> `Boolean`

**¿Cómo están conformadas? (Estructura interna)**
Tomemos la clase `Integer` como caso de estudio. Por dentro, un `Integer` es simplemente una clase que guarda un valor `int` en una variable de tipo `private final`. 
El hecho de que sea `final` significa que son **inmutables**. Una vez que creas un objeto `Integer` con el valor `5`, no puedes modificar ese `5`. Si decides sumarle `1`, Java en realidad destruye la referencia anterior y crea un *nuevo* objeto `Integer` con el valor `6`. Además, las clases numéricas heredan de una superclase abstracta llamada `Number` e implementan interfaces como `Comparable`, lo que nos permite usar funciones para ordenarlos de mayor a menor.

**¿Cuál es su alcance y cuándo usarlas? (Casos de uso en Backend)**
Tienen tres propósitos fundamentales donde los tipos primitivos se quedan cortos:
1.  **Colecciones (Estructuras de datos):** En Java, las listas avanzadas (como `ArrayList` o `HashMap`) **no aceptan tipos primitivos**. No puedes crear una lista de `int`, estás obligado a usar listas de Objetos `Integer`.
2.  **Manejo de Valores Nulos (Bases de datos):** Un primitivo `int` siempre tiene un valor (por defecto es `0`). No puede representar la "ausencia" de un dato. Pero un `Integer`, al ser un objeto, puede ser `null`. Esto es vital en el Backend: si consultas la edad de un usuario en una base de datos y el usuario nunca la ingresó, recibirás un `null`. Si intentas guardar ese `null` en un primitivo `int`, tu programa fallará.
3.  **Métodos de Utilidad:** Proveen funciones estáticas (herramientas) para convertir textos a números, comparar valores o saber los límites matemáticos del sistema.

**¿Cómo usarlas? (Autoboxing y Unboxing)**
En las primeras versiones de Java, meter y sacar el primitivo de su caja requería mucho código manual. Hoy existe un mecanismo automático:
*   **Autoboxing (Empaquetado automático):** Java envuelve el primitivo en un Objeto por ti.
*   **Unboxing (Desempaquetado automático):** Java extrae el primitivo del Objeto automáticamente cuando necesitas hacer operaciones matemáticas.

**Ejemplo Práctico Ampliado:**
```java
// 1. Autoboxing: El primitivo 5 se "envuelve" automáticamente
Integer numeroObjeto = 5; 

// 2. Unboxing: El objeto se "desenvuelve" internamente para poder sumarle 10
int suma = numeroObjeto + 10; 

// 3. Alcance de Nulos (Imposible con un int primitivo)
Integer edadUsuario = null; // Válido si el dato no existe en la Base de Datos

// 4. Utilidades de conversión (Parsing)
String textoDeFormulario = "150";
int convertido = Integer.parseInt(textoDeFormulario);
```

**Ejercicio simple:**
Teniendo en cuenta lo que aprendimos sobre los valores nulos (`null`). ¿Qué crees que pasaría en tiempo de ejecución si intentas hacer "Unboxing" (sacar el valor matemático) de un objeto `Integer` que está vacío (`null`) e intentas guardarlo en un `int` primitivo?

**Solución práctica:**
Al intentar sacar un número de una caja que no existe (`null`), Java entrará en pánico y lanzará un error clásico: el temido `NullPointerException`. En Backend siempre debemos validar nuestros objetos antes de extraer sus datos.
```java
Integer cajaVacia = null;

// ESTO COMPILA BIEN, PERO AL EJECUTARSE... ¡BOOM! 
// Lanza NullPointerException porque intenta extraer un valor que no existe.
// int valorPrimitivo = cajaVacia; 

// SOLUCIÓN: Forma correcta de manejarlo en Backend
if (cajaVacia != null) {
    int valorPrimitivo = cajaVacia; // Unboxing seguro
    System.out.println("El valor es: " + valorPrimitivo);
} else {
    System.out.println("Atención: El dato venía vacío (null).");
}
```

---

## 5. Parsing (Análisis/Conversión desde Texto)
**¿Qué es?** Muy seguido recibiremos números en formato de texto (ej. cuando el usuario escribe en un formulario web). El "parsing" es el proceso de convertir ese `String` a un tipo numérico utilizando las Clases Wrap.

**Ejemplo:**
```java
String edadTexto = "30";
// Parseamos de String a int
int edadReal = Integer.parseInt(edadTexto);
```

**Ejercicio simple:**
¿Qué método usarías para convertir el texto "3.14" a un `double` usando la clase envoltorio `Double`?

**Solución práctica:**
```java
String piTexto = "3.14";
// Usamos parseDouble para convertir el texto a decimal real
double pi = Double.parseDouble(piTexto);

// Ahora sí podemos hacer operaciones matemáticas con él
double resultado = pi * 2; 
System.out.println("El doble de Pi es: " + resultado);
```

---

## 6. Operadores y Tipos
**¿Qué son?** Son símbolos que le dicen al compilador que realice cálculos o comparaciones.
*   **Aritméticos:** `+` (suma), `-` (resta), `*` (multiplicación), `/` (división), `%` (módulo/resto).
*   **Relacionales:** `==` (igual a), `!=` (diferente de), `>` (mayor), `<` (menor), `>=` (mayor o igual).
*   **Lógicos:** `&&` (Y / AND), `||` (O / OR), `!` (Negación / NOT).

**Ejemplo:**
```java
int suma = 5 + 3; // 8
boolean esMayor = (10 > 5); // true
boolean accesoPermitido = (esMayor && suma == 8); // true
```

**Ejercicio simple:**
¿Cuál es el resultado de la expresión `(5 == 5) && (3 > 10)`? (¿Verdadero o falso?).

**Solución práctica:**
El resultado es **Falso**, porque el operador `&&` exige que ambas partes sean verdaderas.
```java
boolean condicion1 = (5 == 5);  // Esto es true
boolean condicion2 = (3 > 10);  // Esto es false

boolean resultadoFinal = condicion1 && condicion2;

System.out.println("¿Es verdadero el resultado final?: " + resultadoFinal); 
// Imprime: false
```

---

## 7. Estructuras de Control
**¿Qué son?** Son las "señales de tránsito" de nuestro código. Permiten tomar decisiones o repetir tareas.

*   **if / else if / else:** Decide qué bloque ejecutar basado en una condición.
*   **switch:** Compara una variable con múltiples valores posibles.
*   **while:** Repite código *mientras* la condición sea verdadera.
*   **do while:** Repite código al menos una vez, y luego revisa si sigue repitiendo.
*   **for:** Repite código un número específico de veces.
*   **foreach:** Recorre cada elemento de una colección (como un array).

**Ejemplo:**
```java
int nota = 8;
if (nota >= 9) {
    System.out.println("Excelente");
} else {
    System.out.println("Sigue intentando");
}
```

**Ejercicio simple:**
Crea un ciclo `while` que imprima los números del 1 al 5.

**Solución práctica:**
```java
int contador = 1; // Punto de inicio

while (contador <= 5) { // Condición límite
    System.out.println("Número: " + contador);
    contador++; // contador = contador + 1 (crucial para no crear un bucle infinito)
}
```

---

## 8. Palabras Reservadas
**¿Qué son?** Son palabras que Java "reserva" para su propio uso. No puedes usarlas como nombres de variables ni de funciones. Ejemplos: `class`, `public`, `static`, `void`, `int`, `return`, `if`.

**Ejemplo:**
```java
// Correcto
int miVariable = 10;
// INCORRECTO: int class = 10; 
```

**Ejercicio simple:**
¿Por qué crees que el lenguaje prohíbe que llames `if` a una variable? Demuestra cómo solucionarías el error si alguien intenta hacerlo.

**Solución práctica:**
Está prohibido para que el compilador no se confunda entre una variable y una estructura de control.
```java
// ESTO DARÁ ERROR DE SINTAXIS:
// int if = 5; 

// SOLUCIÓN (cambiar el nombre agregando contexto):
int miIf = 5;
int condicionIf = 5;

System.out.println("Variable válida: " + condicionIf);
```

---

## 9. Array (Vectores y Matrices)
**¿Qué son?** Un array es como un "estante" con muchos espacios. En lugar de tener 10 variables separadas, tienes 1 array con 10 espacios. Todos los datos deben ser del mismo tipo.
*   **Vector (1D):** Una línea recta de espacios.
*   **Matriz (2D):** Una tabla (filas y columnas).

**Ejemplo:**
```java
String[] nombres = {"Juan", "Pedro", "Maria"};
```

**Ejercicio simple:**
Si tienes un vector `int[] edades = {20, 25, 30};`, ¿cómo accedes al número 30?

**Solución práctica:**
En programación los índices comienzan en 0. Por lo tanto, el tercer elemento está en la posición 2.
```java
int[] edades = {20, 25, 30};

// Accediendo a la posición 2 (que contiene el 30)
int laEdadMayor = edades[2];

System.out.println("La edad extraída es: " + laEdadMayor);
```

---

## 10. Funciones (void y con argumentos)
**¿Qué son?** Son bloques de código que hacen una tarea específica. Pueden recibir datos (argumentos) y pueden o no devolver un resultado. Si usamos `void`, significa "no devuelvo nada, solo hago el trabajo".

**Ejemplo:**
```java
public void saludar(String nombre) {
    System.out.println("Hola, " + nombre + "!");
}
```

**Ejercicio simple:**
Diseña una función void llamada `imprimirSuma` que reciba dos enteros (`int a`, `int b`) y muestre el resultado en pantalla.

**Solución práctica:**
```java
// Definición de la función
public void imprimirSuma(int a, int b) {
    int resultado = a + b;
    System.out.println("El resultado de la suma es: " + resultado);
}

// Así es como la llamaríamos desde nuestro programa principal:
// imprimirSuma(10, 15); -> Imprimiría: El resultado de la suma es: 25
```

---

## 11. ¿Qué son los algoritmos y sus tipos?
**¿Qué son?** Un algoritmo no es código de computadora en sí; es una **serie de pasos lógicos y ordenados** para resolver un problema. Es la receta antes de cocinar.

*   **Tipos principales:**
    *   **Cualitativos:** Pasos descritos con palabras (ej. "Cómo cambiar una llanta").
    *   **Cuantitativos:** Involucran cálculos matemáticos (ej. "Cómo calcular la hipotenusa").

**Ejercicio simple:**
Escribe un algoritmo cualitativo para prepararte un sándwich, traduciéndolo a impresiones en código.

**Solución práctica:**
Un algoritmo es el paso a paso. Llevado a código, sería el orden lógico de ejecución:
```java
public void prepararSandwich() {
    System.out.println("Paso 1: Tomar dos rebanadas de pan.");
    System.out.println("Paso 2: Untar mayonesa en una rebanada.");
    System.out.println("Paso 3: Colocar jamón y queso.");
    System.out.println("Paso 4: Cubrir con la otra rebanada.");
    System.out.println("Resultado: ¡Sándwich listo para comer!");
}
```

---

## 12. ¿Qué es el estándar UML?
**¿Qué es?** UML (Unified Modeling Language) es un estándar visual (dibujos/esquemas) que usamos para planificar y documentar el diseño de nuestro sistema. Los diagramas de Clases son vitales en Backend.

**Ejercicio simple:**
Imagina que en un diagrama UML de Clases tienes un recuadro llamado "Casa" con las variables "color" y "numeroPuertas". Traduce ese plano mental a un esqueleto de código Java.

**Solución práctica:**
El UML nos da el plano, nosotros construimos el código base:
```java
// Esto es lo que deducimos del plano UML
public class Casa {
    // Atributos definidos en el UML
    String color;
    int numeroPuertas;
    
    // Método deducido para darle comportamiento
    public void abrirPuertaPrincipal() {
        System.out.println("Puerta principal abierta.");
    }
}
```

---

## 13. ¿Qué es una Clase?
**¿Qué es?** Una clase es un **molde** o **plantilla**. Define qué atributos (variables) y qué comportamientos (funciones/métodos) tendrá algo que quieres crear en tu sistema. No ocupa espacio de datos todavía.

**Ejemplo:**
```java
public class Coche {
    String marca;
    public void arrancar() { System.out.println("Brumm..."); }
}
```

**Ejercicio simple:**
Escribe el código para crear una clase `Gato` con atributos de color, raza, y edad.

**Solución práctica:**
```java
public class Gato {
    // Atributos (Variables de la clase)
    String nombre;
    String raza;
    String color;
    int edad;
    
    // Comportamiento (Método)
    public void maullar() {
        System.out.println("¡Miau!");
    }
}
```

---

## 14. ¿Qué es un Objeto?
**¿Qué es?** Un objeto es la **materialización (instancia)** de la Clase. Si la clase es el "molde", el objeto es la "figura" ya creada. Puedes crear muchos objetos distintos a partir de una sola clase.

**Ejemplo:**
```java
Coche miCoche = new Coche(); 
```

**Ejercicio simple:**
Basado en la clase `Gato` del punto anterior, crea (instancia) un nuevo objeto `Gato` en código, ponle valores a sus atributos y haz que maúlle.

**Solución práctica:**
```java
public class ProgramaPrincipal {
    public static void main(String[] args) {
        
        // 1. Instanciamos el objeto a partir del molde "Gato"
        Gato miPrimerGato = new Gato();
        
        // 2. Le asignamos estado (valores a sus variables)
        miPrimerGato.nombre = "Garfield";
        miPrimerGato.raza = "Persa";
        miPrimerGato.color = "Naranja";
        miPrimerGato.edad = 5;
        
        // 3. Imprimimos un dato y usamos su comportamiento
        System.out.println("Mi gato se llama: " + miPrimerGato.nombre);
        miPrimerGato.maullar(); 
    }
}
```

---
*¡Felicidades! Has repasado los fundamentos más críticos de Java y resuelto los ejercicios con código 100% práctico. Repite los ejemplos, practica en tu IDE favorito y no dudes en equivocarte, ¡así es como se aprende Backend!*