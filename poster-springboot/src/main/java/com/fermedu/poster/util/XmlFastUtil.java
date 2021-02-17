package com.fermedu.poster.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import cn.hutool.core.util.XmlUtil;

import com.sun.xml.bind.marshaller.CharacterEscapeHandler;

/**
 * @Program: poster-generator
 * @Create: 2021-02-16 22:11
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
public class XmlFastUtil extends XmlUtil{

        /**
         * xml字符串转为bean
         * @param xml xml字符串
         * @param clazz 需要转成的对象
         * @return
         */
        @SuppressWarnings("unchecked")
        public static <T> T parseBean(String xml, Class<T> clazz) {
            try {
                JAXBContext context = JAXBContext.newInstance(clazz);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                return (T) unmarshaller.unmarshal(new StringReader(xml));
            } catch (JAXBException e) {
                e.printStackTrace();
                return null;
            }
        }
        /**
         * 将对象转为xml字符串
         * 编码为utf-8
         * 不格式化
         * 省略头部声明
         * @param obj 待转的对象
         * @return
         */
        public static String toStr(Object obj) {
            return toStr(obj, false, true);
        }
        /**
         * 将对象转为xml字符串
         * 编码为utf-8
         * @param obj 待转的对象
         * @param isFormat 是否格式化
         * @param isIgnoreHead 是否忽略头部
         * @return
         */
        public static String toStr(Object obj, boolean isFormat, boolean isIgnoreHead) {
            try {
                JAXBContext context = JAXBContext.newInstance(obj.getClass());
                Marshaller marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");// //编码格式
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, isFormat);// 是否格式化生成的xml串
                marshaller.setProperty(Marshaller.JAXB_FRAGMENT, isIgnoreHead);// 是否省略xm头声明信息

                // 不进行转义字符的处理
                marshaller.setProperty(CharacterEscapeHandler.class.getName(),
                        new CharacterEscapeHandler() {
                            @Override
                            public void escape(char[] ch, int start, int length,
                                               boolean isAttVal, Writer writer) throws IOException {
                                writer.write(ch, start, length);
                            }
                        });

                StringWriter writer = new StringWriter();
                marshaller.marshal(obj, writer);
                return writer.toString();
            } catch (JAXBException e) {
                e.printStackTrace();
                return null;
            }
        }
        /**
         * 将对象转为xml字符串
         * 编码为utf-8
         * 格式化
         * 省略头部声明
         * @param obj 待转的对象
         * @return
         */
        public static String toPrettyStr(Object obj) {
            return toStr(obj, true, true);
        }


        /**
         * 将对象转为xml字符串
         * 编码为 GBK
         * @param obj 待转的对象
         * @param isFormat 是否格式化
         * @param isIgnoreHead 是否忽略头部
         * @return
         */
        public static String toStrGF(Object obj, boolean isFormat, boolean isIgnoreHead) {
            try {
                JAXBContext context = JAXBContext.newInstance(obj.getClass());
                Marshaller marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_ENCODING, "GBK");// //编码格式
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, isFormat);// 是否格式化生成的xml串
                marshaller.setProperty(Marshaller.JAXB_FRAGMENT, isIgnoreHead);// 是否省略xm头声明信息

                // 不进行转义字符的处理
                marshaller.setProperty(CharacterEscapeHandler.class.getName(),
                        new CharacterEscapeHandler() {
                            @Override
                            public void escape(char[] ch, int start, int length,
                                               boolean isAttVal, Writer writer) throws IOException {
                                writer.write(ch, start, length);
                            }
                        });

                StringWriter writer = new StringWriter();
                marshaller.marshal(obj, writer);
                return writer.toString();
            } catch (JAXBException e) {
                e.printStackTrace();
                return null;
            }
        }


}
