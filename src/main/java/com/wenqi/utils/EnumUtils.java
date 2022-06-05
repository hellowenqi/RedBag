package com.wenqi.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by wuwenqi04 on 2018/7/26.
 */
@Slf4j
public class EnumUtils {
    private static final String ENUM_VALUE_GET_METHOD_NAME = "getValue";
    private static final String ENUM_CLASSPATH = "java.lang.Enum";

    /**
     * 将整形转化为枚举类型(枚举类型必须提供 public getValue()方法)
     *
     * @param value
     * @param enumClass
     * @param <T>
     * @return
     */
    public static <T extends Enum> T castValueToEnum(int value, Class<T> enumClass) {
        try {
            T[] enumInstants = enumClass.getEnumConstants();
            Method method = enumClass.getMethod(ENUM_VALUE_GET_METHOD_NAME);
            for (T enumInstant : enumInstants) {
                int enumValue = (int) (method.invoke(enumInstant));
                if (enumValue == value) {
                    return enumInstant;
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(enumClass.getName() + "没有提供正确的getValue()方法");
        }
        throw new IllegalArgumentException(enumClass.getName() + "无法找到" + value + "对应的实例");
    }

    /**
     * 将byte型转化为枚举类型(枚举类型必须提供 public getValue()方法)
     * @param value
     * @param enumClass
     * @param <T>
     * @return
     */
    public static <T extends Enum> T castValueToEnum(byte value, Class<T> enumClass) {
        try {
            T[] enumInstants = enumClass.getEnumConstants();
            Method method = enumClass.getMethod(ENUM_VALUE_GET_METHOD_NAME);
            for (T enumInstant : enumInstants) {
                byte enumValue = (byte) (method.invoke(enumInstant));
                if (enumValue == value) {
                    return enumInstant;
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(enumClass.getName() + "没有提供正确的getValue()方法");
        }
        throw new IllegalArgumentException(enumClass.getName() + "无法找到" + value + "对应的实例");
    }

    /**
     * 将String转化为枚举类型(枚举类型必须提供 public getValue()方法)
     *
     * @param value
     * @param enumClass
     * @param <T>
     * @return
     */
    public static <T extends Enum> T castValueToEnum(String value, Class<T> enumClass) {
        try {
            T[] enumInstants = enumClass.getEnumConstants();
            Method method = enumClass.getMethod(ENUM_VALUE_GET_METHOD_NAME);
            for (T enumInstant : enumInstants) {
                String enumValue = method.invoke(enumInstant).toString();
                if (enumValue.equals(value)) {
                    return enumInstant;
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(enumClass.getName() + "没有提供正确的getValue()方法");

        }
        throw new IllegalArgumentException(enumClass.getName() + "无法找到" + value + "对应的实例");
    }

    /**
     * 获取该枚举类型列表
     * @param enumClass 枚举类型
     *                  若该类型中除了 Getter 方法外有其它以 get 开头的自定义方法，可能出现未知错误
     *                  若 Getter 方法有以除 get 外其它字符开头的，会导致字段不全
     * @return 枚举列表，包含每个枚举值的所有字段
     */
    public static List<Map<String, Object>> getEnumList(Class<?> enumClass) {
        List<Map<String, Object>> result = new ArrayList<>();
        if (Objects.isNull(enumClass)) {
            throw new IllegalArgumentException("parameter enumClass cannot be null");
        }
        if (Objects.isNull(enumClass.getSuperclass())
                || !ENUM_CLASSPATH.equals(enumClass.getSuperclass().getCanonicalName())) {
            throw new IllegalArgumentException("parameter enumClass is not an enumeration type");
        }
        Method[] methods = enumClass.getMethods();
        List<Field> fields = new ArrayList<>();
        // 通过 get 方法获取字段名称
        Arrays.stream(methods)
                .map(Method::getName)
                .filter(
                        methodName -> methodName.startsWith("get") && !"getDeclaringClass".equals(methodName) && !"getClass".equals(methodName)
                ).forEachOrdered(methodName -> {
                    try {
                        Field field = enumClass.getDeclaredField(StringUtils.uncapitalize(methodName.substring(3)));
                        if (null != field) {
                            fields.add(field);
                        }
                    } catch (NoSuchFieldException | SecurityException e) {
                        log.error("[EnumUtils::getEnumList] error: ", e);
                    }
                });
        Enum<?>[] enums = (Enum<?>[]) enumClass.getEnumConstants();
        for (Enum<?> anEnum : enums) {
            Map<String, Object> map = new HashMap<>(fields.size());
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    map.put(field.getName(), field.get(anEnum));
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    log.error("[EnumUtils::getEnumList] error: ", e);
                }
            }
            result.add(map);
        }
        return result;
    }
}
