import com.urise.webapp.model.Resume;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Resume resume = new Resume();
        String className = getClassName(resume);
        invokeClassMethod(className, "toString", resume);
        showClassFields(resume);
        showClassMethods(resume);
        showFieldsAnnotations(resume);
    }

    public static String getClassName(Object object) {
        Class clazz = object.getClass();
        Constructor<String>[] constructor =  clazz.getConstructors();
        for (Constructor<String> c : constructor) {
            System.out.println(c);
        }
        return clazz.getName();
    }

    public static void showClassFields(Object object) {
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("Class field: " + field.getName());
        }
    }

    public static void showClassMethods(Object object) {
        Class clazz = object.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("Class method : " + method.getName());
        }
    }

    public static void showFieldsAnnotations(Object object) {
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println("Annotation: " + annotation.toString());
            }
        }
    }

    public static void invokeClassMethod(String className, String methodName, Object object) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = Class.forName(className).getMethod(methodName, null);
        method.invoke(object, null);
        System.out.println("Вызов метода через Reflection : " + object);
    }
}
