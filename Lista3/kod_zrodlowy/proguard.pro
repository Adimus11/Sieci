-libraryjars <java.home>/lib/rt.jar

-dontwarn javax.annotation.**
-dontwarn javax.inject.**
-dontwarn sun.misc.Unsafe

-keep public class pl.andrzejressel.sieci.lista3.zadanie1.Main {
    public static void main(java.lang.String[]);
}