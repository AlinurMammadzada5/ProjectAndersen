import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException  {



        String className = "Menu";
        CustomClassLoader myClassLoader = new CustomClassLoader();

        try {
            Class<?> loadingClass = myClassLoader.loadClass(className);
            System.out.println(loadingClass.getClassLoader());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }


    }
}