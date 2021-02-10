package org.example.factory;

import org.example.StartApplication;
import org.example.annotation.Autowired;
import org.example.annotation.Repository;
import org.example.annotation.Service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

public class AnnotationBeanFactory implements BeanFactory {

    public static boolean isInit = false;
    public static final String CLASS_STR = ".class";
    public static Map<String, Object> IOC_MAP = new HashMap<>();


    public AnnotationBeanFactory() {
        if (isInit) {
            return;
        }
        String packageName = StartApplication.class.getPackage().getName();
        System.out.println(String.format("初始化开始，扫描的包是：%s",packageName));
        try {
            initBeanFactory(packageName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("初始化完成");
        isInit = true;
    }

    /**
     * 根据包名，将初始化容器
     * @param packageName
     * @throws UnsupportedEncodingException
     */
    public void initBeanFactory(String packageName) throws UnsupportedEncodingException {
        //获取包名和路径
        packageName = packageName.replace(".", File.separator);
        URL resource = AnnotationBeanFactory.class.getClassLoader().getResource(packageName);
        String path = resource.getPath();
        String filePath = URLDecoder.decode(path,"UTF-8");
        System.out.println(filePath);
        Set<String> classNameSet = new HashSet<>();
        parseFilePackName(packageName,classNameSet,path);
        //实例化
        setBean(classNameSet);
        //注入依赖
        beanAutowired();

    }

    @Override
    public Object getBeans(String name) {
        return IOC_MAP.get(name);
    }

    @Override
    public Object getBeans(Class<?> clazz) {
        return IOC_MAP.get(parseClassName(clazz.getSimpleName()));
    }

    @Override
    public Set<String> getAllBeanName() {
        return IOC_MAP.keySet();
    }

    /**
     * 类属性注入
     */
    private static void beanAutowired() {
        IOC_MAP.entrySet().forEach(stringObjectEntry -> {
            Object bean = stringObjectEntry.getValue();
            Class clazz = bean.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                Autowired annotation = field.getAnnotation(Autowired.class);
                if (Objects.nonNull(annotation)) {
                    String name = annotation.name();
                    if (name == null || "".equals(name)) {
                        name = field.getName();
                    }
                    field.setAccessible(true);
                    try {
                        field.set(bean, IOC_MAP.get(name));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    //实例化bean
    public static void setBean(Set<String> classNameSet) {
        classNameSet.forEach(className -> {
            try {
                Class clazz = Class.forName(className);
                serviceAnnotation(clazz);
                repositoryAnnotation(clazz);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }

        });
    }

    /**
     * 将repository注解的类实例化
     * @param clazz
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private static void repositoryAnnotation(Class clazz) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Repository annotation = (Repository) clazz.getAnnotation(Repository.class);
        if (Objects.nonNull(annotation)) {
            setIOCMap(annotation.value(), clazz.getName(), clazz);
        }
    }

    /**
     * 将service注解的类实例化
     * @param clazz
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private static void serviceAnnotation(Class clazz) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Service annotation = (Service) clazz.getAnnotation(Service.class);
        if (Objects.nonNull(annotation)) {
            setIOCMap(annotation.value(), clazz.getName(), clazz);
        }
    }

    /**
     * 将类实例化，并放入容器中
     * @param value autowired注解属性值
     * @param className 类的全限定名
     * @param clazz 类的字节码对象
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private static void setIOCMap(String value, String className, Class clazz) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String iocNameString = value;
        Object bean = Class.forName(className).newInstance();
        if (iocNameString == null || "".equals(iocNameString)) {
            iocNameString = clazz.getName();
        }
        iocNameString = parseClassName(iocNameString);
        if (IOC_MAP.containsKey(iocNameString)) {
            throw new RuntimeException("该实例已经实例化");
        }
        Class<?>[] interfaces = clazz.getInterfaces();
        if (interfaces != null && interfaces.length > 0) {
            for (Class<?> anInterface : interfaces) {
                iocNameString = parseClassName(anInterface.getSimpleName());
                IOC_MAP.put(iocNameString, bean);
            }
        }
        IOC_MAP.put(iocNameString, bean);
    }

    /**
     * 将类名首字母转换成小写
     * @param className
     * @return
     */
    private static String parseClassName(String className) {
        int beginIndex = 0;
        int length = className.length();
        if (className.contains(".")) {
            beginIndex = className.lastIndexOf(".") + 1;
        }
        return className.substring(beginIndex, beginIndex + 1).toLowerCase() + className.substring(beginIndex + 1, length);
    }

    /**
     * 扫描包，将所有类的全限定名放入set集合中
     * @param packName 包名
     * @param classNameSet set集合
     * @param path 路径
     */
    public static void parseFilePackName(String packName, Set<String> classNameSet, String path) {

        File packNamePath = new File(path);

        if (!packNamePath.isDirectory() || !packNamePath.exists()) {
            return;
        }
        //递归路径下所有文件和文件夹
        for (File file : packNamePath.listFiles()) {
            boolean directory = file.isDirectory();
            String classNamePath = packName + File.separator + file.getName().replace(File.separator, ".");
            if (directory) {
                parseFilePackName(classNamePath, classNameSet, file.getPath());
            } else if (file.isFile() && file.getName().endsWith(CLASS_STR)) {
                //存入set
                classNameSet.add(classNamePath.replace(File.separator, ".").replace(CLASS_STR, ""));
            }
        }

    }

}
