元数据区(Metaspace)
>像类定义(class definitions)之类的信息会被加载到 Metaspace 中。元数据区位于本地内存(native
memory)，不再影响到普通的Java对象。默认情况下，Metaspace的大小只受限于 Java进程可用的本地内 存。

设置 Metaspace的大小
```
 -XX:MaxMetaspaceSize=256m
```