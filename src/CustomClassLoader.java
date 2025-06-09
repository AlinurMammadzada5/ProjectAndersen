import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CustomClassLoader extends ClassLoader {
    String dir = "out/production/ProjectAndersen";
    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> loadingClass = findLoadedClass(name);
        if (loadingClass == null) {
            try {
                loadingClass = super.loadClass(name,false);
            } catch (ClassNotFoundException e) {
                loadingClass=load(name);
            }
        }
        if (resolve) {
            resolveClass(loadingClass);
        }
        return loadingClass;
    }

    public Class<?> load (String name) throws ClassNotFoundException {

        String filePath = dir + File.separator + name + ".class";
        byte[] classData;
        try(FileInputStream binaryRead = new FileInputStream(filePath)) {
            classData= binaryRead.readAllBytes();
            return defineClass(name, classData, 0, classData.length);
        } catch (IOException | ClassFormatError e){
            throw new ClassNotFoundException();
        }

    }







}
