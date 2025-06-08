import java.io.*;

public class CustomClassLoader extends ClassLoader {



    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> loadedClass = findLoadedClass(name);

//        if (loadedClass == null) {
//            try {
//                loadedClass = loadClass(name, false);
//            } catch (ClassNotFoundException ex) {
//                loadedClass = load(name);
//            }
//        }
        if (loadedClass != null) return loadedClass;
        if (!name.equals("Menu") && !name.equals("ScannerGet") && !name.equals("AdminLog") && !name.equals("UserLog") && !name.equals("DB")) {
            return super.loadClass(name, resolve);
        }


        try {
            return load(name);
        } catch ( ClassNotFoundException e) {
            System.out.println("Class not found: " + name);
            throw e;
        }


    }


    public Class<?> load(String name) throws ClassNotFoundException {

        String directory = "out/production/ProjectAndersen";


        String filePath = directory + File.separator  +name+ ".class";

        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            byte[] binaryClassData = fileInputStream.readAllBytes();
            return defineClass(name, binaryClassData, 0, binaryClassData.length);
        }
        catch (IOException ex) {
            throw new ClassNotFoundException("Can't load class " + name + " from the file: " + ex.getMessage());
        }
    }

//     @Override
//    public Class findClass(String name) throws ClassNotFoundException {
//        byte[] classData = loadClassFromFile(name);
//        return defineClass(name, classData, 0, classData.length);
//    }

//    private byte[] loadClassFromFile(String fileName) {
//        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName+".class");
//        byte[] classData;
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        int readValue=0;
//        try {
//            while ( (readValue = inputStream.read()) != -1 ) {
//                byteArrayOutputStream.write(readValue);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        classData = byteArrayOutputStream.toByteArray();
//        return classData;
//
//    }
//
//

}
