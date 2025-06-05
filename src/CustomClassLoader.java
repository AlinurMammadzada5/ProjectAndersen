import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CustomClassLoader extends ClassLoader {



    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> loadedClass = findLoadedClass(name);

        if (loadedClass == null) {
            try {
                loadedClass = super.loadClass(name, false);
            } catch (ClassNotFoundException ex) {
                loadedClass = load(name);
            }
        }

        return loadedClass;
    }


    public Class<?> load(String name) throws ClassNotFoundException {

        String directory = "out/production/ProjectAndersen";


        String filePath = directory + File.separator  +name+ ".class";

        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            byte[] binaryClassData = fileInputStream.readAllBytes();
            return defineClass(name, binaryClassData, 0, binaryClassData.length);
        } catch (IOException ex) {
            throw new ClassNotFoundException("Can't load class " + name + " from the file: " + ex.getMessage());
        }
    }

}
