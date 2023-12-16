# Nie obsfukuj klasy głównej
#-keep class me.xxgradzix.channels.Channels {
#    public static void main(java.lang.String[]);
#}

# Zachowaj wszystkie publiczne metody
#-keepclassmembers public class * {
#    public *;
#}

# Zachowaj wszystkie metody oznaczone adnotacją @MyAnnotation
#-keep @com.example.MyAnnotation class * {
#    *;
#}
