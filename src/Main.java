import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args)  {


        CustomClassLoader messageClassLoader = new CustomClassLoader();

        try {

            Class<?> loadedClass = messageClassLoader.loadClass("Menu");
            System.out.println(loadedClass.getClassLoader());

            Object instance = loadedClass.getDeclaredConstructor().newInstance();
            Method printMessageMethod = loadedClass.getMethod("showMenu");
            printMessageMethod.invoke(instance);

       }
        catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            System.out.println(e.getMessage());
        }



    }
}