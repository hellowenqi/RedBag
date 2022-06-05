package com.wenqi.utils.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.TimeZone;

/**
 * 实现描述：
 * Json工具
 */

public class JsonUtils {
    private static final ObjectMapper mapper;

    static {
        /**jackson*/
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
    }

    /**
     * 对象转json
     *
     * @param object
     * @return
     */
    public static String toJsonString(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new JsonException("json序列化异常.Object=" + object, e);
        }
    }

    /**
     * 对象反序列化
     *
     * @param json
     * @param targetClass
     * @param <T>
     * @return
     */

    public static <T> T parseObject(String json, Class<T> targetClass) {
        try {
            return mapper.readValue(json, targetClass);
        } catch (Exception e) {
            throw new JsonException(String.format("json反序列异常.Json=%s,class=%s", json, targetClass.getCanonicalName()), e);
        }
    }

    public static <T> T parseObject(String json, TypeReference<T> typeReference) {
        try {
            return mapper.readValue(json, typeReference);
        } catch (Exception e) {
            throw new JsonException(String.format("json反序列异常.Json=%s,class=%s", json, typeReference.getClass()), e);
        }
    }


    public static <T> T[] parseArray(String json, Class<?> elementClass) {
        try {
            return mapper.readValue(json, new TypeReference<T[]>() {
                @Override
                public Type getType() {
                    return Array.newInstance(elementClass, 0).getClass();
                }
            });
        } catch (Exception e) {
            throw new JsonException(String.format("json数组反序列异常.Json=%s,class=%s", json, elementClass.getCanonicalName()), e);
        }
    }
}
