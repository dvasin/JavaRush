package com.javarush.test.level32.lesson08.home01;
/* Создание прокси-объекта
1) В отдельном файле создать публичный класс CustomInvocationHandler, который будет хэндлером при создании прокси-объекта.
2) CustomInvocationHandler должен иметь один публичный конструктор с одним агументом типа SomeInterfaceWithMethods.
3) Перед вызовом любого метода у оригинального объекта должна выводиться фраза [methodName in].
4) После вызова любого метода у оригинального объекта должна выводиться фраза [methodName out].
5) Реализовать логику метода getProxy, который должен создавать прокси для интерфейса SomeInterfaceWithMethods.
См. пример вывода в методе main. Метод main не участвует в тестировании.
*/
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CustomInvocationHandler implements InvocationHandler {
    private SomeInterfaceWithMethods someInterfaceWithMethods;

    public CustomInvocationHandler(SomeInterfaceWithMethods someInterfaceWithMethods) {
        this.someInterfaceWithMethods = someInterfaceWithMethods;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName() + " in");
        try {
            return method.invoke(someInterfaceWithMethods, args);
        } finally {
            System.out.println(method.getName() + " out");
        }
    }
}
