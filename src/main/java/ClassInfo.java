import java.lang.reflect.*;

public class ClassInfo {
    public static void printObjectInfo(Object obj) throws InvocationTargetException, IllegalAccessException {
        Class<?> printedClass = obj.getClass();
        System.out.printf("%s - class: %s\n", obj, printedClass.getName());
        Constructor<?>[] constructors = printedClass.getDeclaredConstructors();
        System.out.println("   Constructors:");
        for (Constructor<?> constructor: constructors) {
            System.out.printf("       name: %s\n", constructor.getName());
            System.out.printf("          parameters count: %s\n", constructor.getParameterCount());
            Parameter[] parameters = constructor.getParameters();
            for (Parameter parameter: parameters) {
                System.out.printf("              %s: %s\n", parameter.getName(), parameter.getType());
            }
        }
        System.out.println("   Fields:");
        Field[] fields = printedClass.getDeclaredFields();
        for (Field field: fields) {
            field.setAccessible(true);
            System.out.printf("     field: %s, value: %s\n", field.getName(), field.get(obj));

        }
        System.out.println("   Methods:");
        Method[] methods = printedClass.getDeclaredMethods();
        for (Method method: methods) {
            System.out.printf("       name: %s\n", method.getName());
            System.out.printf("          parameters count: %s\n", method.getParameterCount());
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter: parameters) {
                System.out.printf("             %s: %s\n", parameter.getName(), parameter.getType());
            }
            if (method.getName().equals("makeSound")) {
                System.out.println("= CALL makeSound =");
                method.invoke(obj);
            }
        }
        System.out.println();

    }
}
