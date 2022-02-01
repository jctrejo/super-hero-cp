# Super-Hero - JoseT

## Arquitectura utilizada
* MVVM modulos

## Consumo de servicio
* Retrofit

## Android Compose
* Navegation
* Dagger
* Glide
* Rxjava
* Room
* ViewBinding

## Test
* Se agrego Pruebas unitarias

## Descripción de aplicación
* Navegación con componente "navegation" agregando interfaces y conexiones de fragmen a fragmento, enviando datos entre ellos con "argument".
* Esta app fue realizado con componentes como jetpack con algunos de sus componentes de interfaz, utilizando corutinas al ser llamado de servicios obteniendo mayor legilidad de datos y optimización para el control de fuga de memoria.
* Usando injection de dependencias.
* Se uso lottie en el Splash de la app.

### Class files ###
* Los nombres de las clases están escritos en UpperCamelCase.
* Para las clases que extienden un componente de Android, el nombre de la clase debe terminar con el nombre del componente SignInActivity, SignInFragment, ImageUploaderService, ChangePasswordDialog.

**Fields**

## String constants, naming, and values ##

* Muchos elementos del SDK de Android, como SharedPreferences, Bundle o Intent, utilizan un enfoque de par clave-valor, por lo que es muy probable que incluso para una aplicación pequeña termine teniendo que escribir muchas constantes de cadena.
* Cuando use uno de estos componentes, debe definir las claves como campos finales estáticos y deben tener el prefijo que se indica a continuación.

| Element | Field Name Prefix |
| --- | --- | --- |
| SharedPreferences | PREF_ |
| Bundle | BUNDLE_ |
| Fragment Arguments | ARGUMENT_ |
| Intent Extra | EXTRA_ |
| Intent Action | ACTION_ |


## Arguments in Fragments and Activities ##
* Cuando los datos se pasan a una Actividad o Fragmento a través de una Intención o un Paquete, las claves para los diferentes valores deben seguir las reglas descritas en la sección anterior.
* Cuando una Actividad o Fragmento espera argumentos, debe proporcionar un método estático público que facilite la creación de la Intención o Fragmento relevante.
* En el caso de Actividades, el método se suele llamar getStartIntent():
* Para Fragmentos, se llama newInstance() y maneja la creación del Fragmento con los Arg
*  Note that the arguments of a Fragment - Fragment.getArguments() - are also a Bundle. However, because this is a quite common use of Bundles, we define a different prefix for them.

