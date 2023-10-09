package exercise;

import exercise.model.Address;
import exercise.annotation.Inspect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Application {
    public static void main(String[] args) {
        var address = new Address("London", 12345678);

        // BEGIN

        for (Method method: address.getClass().getDeclaredMethods()){
            if (method.isAnnotationPresent(Inspect.class)){

                System.out.println("Method "+"return type and name "+method.getReturnType().getSimpleName());

                try {
                    method.invoke(address);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }

            }
        }

        // END
    }
}
