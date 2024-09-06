package br.com.localweb.app.util;

import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

@Service
public class ReflectionService {

    public <I, O> O transform(I input, String dtoPackage) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Class<?> source = input.getClass();
        String targetClassName = dtoPackage + "." + source.getSimpleName() + "DTO";
        Class<?> target = Class.forName(targetClassName);

        Constructor<?> constructor = target.getDeclaredConstructors()[0];
        Object[] constructorArgs = Arrays.stream(constructor.getParameters())
                .map(parameter -> {
                    try {
                        String paramName = parameter.getName();
                        Field sourceField = source.getDeclaredField(paramName);
                        sourceField.setAccessible(true);
                        return sourceField.get(input);
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }).toArray();

        return (O) constructor.newInstance(constructorArgs);
    }

    private void validate(Field sourceField, Field targetField) {
        if (sourceField.getName().equals(targetField.getName()) && sourceField.getType().equals(targetField.getType())) {
            sourceField.setAccessible(true);
            targetField.setAccessible(true);
        }
    }


}
