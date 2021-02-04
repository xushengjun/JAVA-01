<map version="1.0.1"><node CREATED="1611300460012" ID="ID_root" MODIFIED="1611300460012" TEXT="jvm"><node CREATED="1611300460012" ID="ID_9d251ffc29f1" POSITION="right" MODIFIED="1611300460012" TEXT="堆"><node CREATED="1611300460012" ID="ID_1563380056ba" MODIFIED="1611300460012" TEXT="含义"><richcontent TYPE="NOTE"><html><head></head><body><p>jvm开辟的一块供线程共享、存放对象的内存。
根据弱代理论，将堆划分为Eden区、S区和old区。

对象包含三部分：

对象头： 标记字（1byte）、Class指针（1byte）、数组长度（int）。

实例数据： 属性字段。

对齐填充： 为了高效寻址，空间换时间。

</p></body></html></richcontent></node><node CREATED="1611300460012" ID="ID_1d55d9fc75af" MODIFIED="1611300460012" TEXT="启动参数"><node CREATED="1611300460012" ID="ID_a825ff329b60" MODIFIED="1611300460012" TEXT="-Xmx"><richcontent TYPE="NOTE"><html><head></head><body><p>最大堆大小
</p></body></html></richcontent></node><node CREATED="1611300460012" ID="ID_58e44f9d7ca2" MODIFIED="1611300460012" TEXT="-Xms"><richcontent TYPE="NOTE"><html><head></head><body><p>最小堆大小
</p></body></html></richcontent></node><node CREATED="1611300460012" ID="ID_4bea97265e2c" MODIFIED="1611300460012" TEXT="-XX:-UseAdaptiveSizePolicy"><richcontent TYPE="NOTE"><html><head></head><body><p>关闭自适应，年轻代和老年代会按照NewRatio进行分配
</p></body></html></richcontent></node><node CREATED="1611300460012" ID="ID_55878ddbfe5a" MODIFIED="1611300460012" TEXT="-Xmn"><richcontent TYPE="NOTE"><html><head></head><body><p>年轻代大小
</p></body></html></richcontent></node><node CREATED="1611300460012" ID="ID_3dc0de0c64e5" MODIFIED="1611300460012" TEXT="-XX:NewRatio"><richcontent TYPE="NOTE"><html><head></head><body><p> 设置Old和Young区比例
如果是2，Old:Young=2:1

</p></body></html></richcontent></node><node CREATED="1611300460012" ID="ID_1bcdc3830b00" MODIFIED="1611300460012" TEXT="-XX:SurvivorRatio"><richcontent TYPE="NOTE"><html><head></head><body><p>设置Eden:S0:S1的比例，
如果是8则是8:1:1</p></body></html></richcontent></node></node><node CREATED="1611300460012" ID="ID_c47c4c5c5d0a" MODIFIED="1611300460012" TEXT="常见的占内存"><richcontent TYPE="NOTE"><html><head></head><body><p>包装类型

多维数组：存放嵌套数组的引用占额外的空间。

String：可以用char[]代替。</p></body></html></richcontent></node></node><node CREATED="1611300460012" ID="ID_4b7ea5f7814a" POSITION="right" MODIFIED="1611300460012" TEXT="GC"><node CREATED="1611300460012" ID="ID_82404872bacb" MODIFIED="1611300460012" TEXT="GC背景、原理"><richcontent TYPE="NOTE"><html><head></head><body><p>详细清晰的讲解了各垃圾回收算法
https://blog.csdn.net/tjiyu/article/details/53983064

</p></body></html></richcontent><node CREATED="1611300460012" ID="ID_cb240230bedd" MODIFIED="1611300460012" TEXT="分代假设（弱代理论）"><richcontent TYPE="NOTE"><html><head></head><body><p> 分代垃圾收集基于弱代理论（weak generational hypothesis），具体描述如下：

       （1）、大多数分配了内存的对象并不会存活太长时间，在处于年轻代时就会死掉；

       （2）、很少有对象会从老年代变成年轻代；

       其中IBM研究表明：新生代中98%的对象都是&amp;quot;朝生夕死&amp;quot;；

        所以并不需要按1:1比例来划分内存

内存池划分 不同类型对象不同区域，不同策略处理。</p></body></html></richcontent></node><node CREATED="1611300460012" ID="ID_7d69d2037672" MODIFIED="1611300460012" TEXT="分配担保"><richcontent TYPE="NOTE"><html><head></head><body><p>如果另一块Survivor空间没有足够空间存放上一次新生代收集下来的存活对象时，这些对象将直接通过分配担保机制（Handle Promotion）进入老年代；

</p></body></html></richcontent></node></node><node CREATED="1611300460012" ID="ID_98488774d622" MODIFIED="1611300460012" TEXT="GC算法"><node CREATED="1611300460012" ID="ID_d1b31834b195" MODIFIED="1611300460012" TEXT="引用计数法"><richcontent TYPE="NOTE"><html><head></head><body><p>仓库与引用计数：计数为 0 
简单粗暴，一般有效 
实际情况复杂一点 
仓库与仓库之间也有关系 导致形成一个环，大家的计数永远不为 0 （跟线程、事务死锁一个原理） 
这些仓库都没法再用：内存泄漏-&amp;gt;内存溢出 怎么改进呢？引用计数-&amp;gt;引用跟踪</p></body></html></richcontent></node><node CREATED="1611300460012" ID="ID_a43ecfc06765" MODIFIED="1611300460012" TEXT="标记清除算法"><richcontent TYPE="NOTE"><html><head></head><body><p>1、优点

       基于最基础的可达性分析算法，它是最基础的收集算法；

       而后续的收集算法都是基于这种思路并对其不足进行改进得到的；

2、缺点

       主要有两个缺点：

（A）、效率问题

       标记和清除两个过程的效率都不高；

（B）、空间问题

       标记清除后会产生大量不连续的内存碎片；

       这会导致分配大内存对象时，无法找到足够的连续内存；

       从而需要提前触发另一次垃圾收集动作；

3、应用场景

      针对老年代的CMS收集器；</p></body></html></richcontent></node><node CREATED="1611300460012" ID="ID_8540317761c6" MODIFIED="1611300460012" TEXT="复制算法"><richcontent TYPE="NOTE"><html><head></head><body><p> &amp;quot;复制&amp;quot;（Copying）收集算法，为了解决标记-清除算法的效率问题；

1、算法思路

       （A）、把内存划分为大小相等的两块，每次只使用其中一块；

       （B）、当一块内存用完了，就将还存活的对象复制到另一块上（而后使用这一块）；

       （C）、再把已使用过的那块内存空间一次清理掉，而后重复步骤2；    

      执行过程如下图：



2、优点

       这使得每次都是只对整个半区进行内存回收；

       内存分配时也不用考虑内存碎片等问题（可使用&amp;quot;指针碰撞&amp;quot;的方式分配内存）；

      实现简单，运行高效；

       （关于&amp;quot;指针碰撞&amp;quot;请参考《Java对象在HotSpot虚拟机中的创建过程》）

3、缺点

（A）、空间浪费

      可用内存缩减为原来的一半，太过浪费（解决：可以改良，不按1:1比例划分）；

（B）、效率随对象存活率升高而变低

      当对象存活率较高时，需要进行较多复制操作，效率将会变低（解决：后面的标记-整理算法）；

4、应用场景

      现在商业JVM都采用这种算法（通过改良缺点1）来回收新生代；

      如Serial收集器、ParNew收集器、Parallel Scavenge收集器、、G1（从局部看）；

5、HotSpot虚拟机的改良算法

（A）、弱代理论

       分代垃圾收集基于弱代理论（weak generational hypothesis），具体描述如下：

       （1）、大多数分配了内存的对象并不会存活太长时间，在处于年轻代时就会死掉；

       （2）、很少有对象会从老年代变成年轻代；

       其中IBM研究表明：新生代中98%的对象都是&amp;quot;朝生夕死&amp;quot;；

        所以并不需要按1:1比例来划分内存（解决了缺点1）；

（B）、HotSpot虚拟机新生代内存布局及算法

                      （1）、将新生代内存分为一块较大的Eden空间和两块较小的Survivor空间；

                      （2）、每次使用Eden和其中一块Survivor；

                      （3）、当回收时，将Eden和使用中的Survivor中还存活的对象一次性复制到另外一块Survivor；

                      （4）、而后清理掉Eden和使用过的Survivor空间；

                      （5）、后面就使用Eden和复制到的那一块Survivor空间，重复步骤3；

         默认Eden：Survivor=8:1，即每次可以使用90%的空间，只有一块Survivor的空间被浪费；

（C）、分配担保

       如果另一块Survivor空间没有足够空间存放上一次新生代收集下来的存活对象时，这些对象将直接通过分配担保机制（Handle Promotion）进入老年代；

          更多请参考：http://docs.oracle.com/javase/8/docs/technotes/guides/vm/gctuning/generations.html#sthref16</p></body></html></richcontent></node><node CREATED="1611300460012" ID="ID_feb9011791fd" MODIFIED="1611300460012" TEXT="标记整理算法"><richcontent TYPE="NOTE"><html><head></head><body><p>1、算法思路

（1）、标记

      标记过程与&amp;quot;标记-清除&amp;quot;算法一样；

（2）、整理

       但后续不是直接对可回收对象进行清理，而是让所有存活的对象都向一端移动；

       然后直接清理掉端边界以外的内存；

执行过程如下图：



2、优点

（A）、不会像复制算法，效率随对象存活率升高而变低

       老年代特点：

       对象存活率高，没有额外的空间可以分配担保；

       所以老年代一般不能直接选用复制算法算法；

       而选用标记-整理算法；

（B）、不会像标记-清除算法，产生内存碎片

       因为清除前，进行了整理，存活对象都集中到空间一侧；

3、缺点

       主要是效率问题：除像标记-清除算法的标记过程外，还多了需要整理的过程，效率更低；

4、应用场景

       很多垃圾收集器采用这种算法来回收老年代；

      如Serial Old收集器、G1（从整体看）；</p></body></html></richcontent></node></node><node CREATED="1611300460012" ID="ID_da75705301a0" MODIFIED="1611300460012" TEXT="GC日志分析"><node CREATED="1611300460012" ID="ID_d7830bbecd8a" MODIFIED="1611300460012" TEXT="jvm命令行工具"><node CREATED="1611300460012" ID="ID_9564192499a1" MODIFIED="1611300460012" TEXT="java"><richcontent TYPE="NOTE"><html><head></head><body><p>Java 应用的启动程序
</p></body></html></richcontent></node><node CREATED="1611300460012" ID="ID_ee160ef8a961" MODIFIED="1611300460012" TEXT="javac"><richcontent TYPE="NOTE"><html><head></head><body><p>JDK 内置的编译工具</p></body></html></richcontent></node><node CREATED="1611300460012" ID="ID_47f3783ec268" MODIFIED="1611300460012" TEXT="javap"><richcontent TYPE="NOTE"><html><head></head><body><p>反编译 class 文件的工具</p></body></html></richcontent></node><node CREATED="1611300460012" ID="ID_52a714ea7212" MODIFIED="1611300460012" TEXT="jps"><richcontent TYPE="NOTE"><html><head></head><body><p>查看 java 进程</p></body></html></richcontent></node><node CREATED="1611300460012" ID="ID_ee60ab03e7db" MODIFIED="1611300460012" TEXT="jstat"><richcontent TYPE="NOTE"><html><head></head><body><p>查看 JVM 内部 gc 相关信息

jstat
- -options -class 类加载(Class loader)信息统计.
 -compiler JIT 即时编译器相关的统计信息。
 -gc GC 相关的堆内存信息. 
    用法: jstat -gc -h 10 -t 864 1s 20
-gccapacity 各个内存池分代空间的容量。
 -gccause 看上次 GC, 本次 GC（如果正在 GC中）的原因, 其 他输出和 -gcutil 选项一致。
 -gcnew 年轻代的统计信息. （New = Young = Eden + S0 + S1）
-gcnewcapacity 年轻代空间大小统计. 
-gcold 老年代和元数据区的行为统计。
 -gcoldcapacity old 空间大小统计. -gcmetacapacity meta 区大小统计. 
-gcutil GC 相关区域的使用率（utilization）统计。 
-printcompilation 打印 JVM 编译统计信息。

jstat -gcutil 10944 1000 1000
每隔1000毫秒，打印1000次

jstat -gc 10944 1000 1000
重点关注：OU（老年代使用空间）
YGCT（年轻代GC总消耗时间）
FGCT（FullGC总消耗时间）</p></body></html></richcontent></node><node CREATED="1611300460012" ID="ID_52cb321a828a" MODIFIED="1611300460012" TEXT="jmap"><richcontent TYPE="NOTE"><html><head></head><body><p>查看 heap 或类占用空间统计
常用选项就 3 个：
 -heap 打印堆内存（/内存池）的配置和 使用信息。 -histo 看哪些类占用的空间最多, 直方图 
-dump:format=b,file=xxxx.hprof Dump 堆内存。 演示: jmap -heap pid jmap -histo pid jmap -dump:format=b,file=3826.hprof 3826</p></body></html></richcontent></node></node><node CREATED="1611300460012" ID="ID_c6f289fe4ff1" MODIFIED="1611300460012" TEXT="启动参数"><node CREATED="1611300460012" ID="ID_1f3bbbb9c2cd" MODIFIED="1611300460012" TEXT="-XX:+PrintGCDateStamps"></node><node CREATED="1611300460012" ID="ID_8e54261a7e41" MODIFIED="1611300460012" TEXT="-XX:+PrintGCTimeStamps"></node><node CREATED="1611300460012" ID="ID_8e6acf3429d3" MODIFIED="1611300460012" TEXT="-XX:HeapDumpPath"><richcontent TYPE="NOTE"><html><head></head><body><p>比如：-XX:HeapDumpPath=/home/bankdplyop/jvm/heap_2021-01-21-16-13.hprof </p></body></html></richcontent></node><node CREATED="1611300460012" ID="ID_24a217cd942a" MODIFIED="1611300460012" TEXT="-XX:+HeapDumpOnOutOfMemoryError"></node><node CREATED="1611300460012" ID="ID_79bf7cc9b5c3" MODIFIED="1611300460012" TEXT="-Xloggc"><richcontent TYPE="NOTE"><html><head></head><body><p>-Xloggc:/home/bankdplyop/jvm/gc_2021-01-21-16-13.log</p></body></html></richcontent></node><node CREATED="1611300460012" ID="ID_6a54b8778b53" MODIFIED="1611300460012" TEXT="-XX:+UseSerialGC"></node><node CREATED="1611300460012" ID="ID_1ee9e775bced" MODIFIED="1611300460012" TEXT="-XX:+UseParallelGC"></node><node CREATED="1611300460012" ID="ID_90c92b22aa83" MODIFIED="1611300460012" TEXT="-XX:+UseConcMarkSweepGC"></node><node CREATED="1611300460012" ID="ID_898e2a505f28" MODIFIED="1611300460012" TEXT="-XX:+UseG1GC"></node></node><node CREATED="1611300460012" ID="ID_41cdb3c83570" MODIFIED="1611300460012" TEXT="jvm图形化工具"><node CREATED="1611300460012" ID="ID_c0e3aa8b98b4" MODIFIED="1611300460012" TEXT="jconsole"></node><node CREATED="1611300460012" ID="ID_515032595826" MODIFIED="1611300460012" TEXT="jvisualvm"></node><node CREATED="1611300460012" ID="ID_71648ba2a913" MODIFIED="1611300460012" TEXT="jmc"></node><node CREATED="1611300460012" ID="ID_9157d72699fb" MODIFIED="1611300460012" TEXT="Memory&amp;nbsp;Analyzer"><richcontent TYPE="NOTE"><html><head></head><body><p>eclipse推出的分析内存溢出日志的工具</p></body></html></richcontent></node><node CREATED="1611300460012" ID="ID_10e4a58b4844" MODIFIED="1611300460012" TEXT="GCViewer"><richcontent TYPE="NOTE"><html><head></head><body><p>可将GClog日志导入，可以通过它的分析得到吞吐量和延迟数据。
Summary和Pause看板
“ Summary (摘要)” 中比较有用的是： “ Throughput ”(吞吐量百分比)，吞吐量显示了有效工作的时间比例，剩下的部分就是GC的消耗 “ Number of GC pauses ”(GC暂停的次数) “ Number of full GC pauses ”(Full GC 暂停的次数)
“ Pause ” 展示了GC暂停的总时间，平均值，最小值和最大值，并且将 total 与minor/major 暂停分开 统计。如果要优化程序的延迟指标，这些统计可以很快判断出暂停时间是否过长。</p></body></html></richcontent></node></node></node><node CREATED="1611300460012" ID="ID_83e6bbfe7e7c" MODIFIED="1611300460012" TEXT="垃圾收集器"><node CREATED="1611300460012" ID="ID_60baae463ad2" MODIFIED="1611300460012" TEXT="串行GC"><richcontent TYPE="NOTE"><html><head></head><body><p>串行GC的特点是，无论是否单核，都采用一块cpu处理，执行GC时应用需要暂停处理业务，年轻代和老年代串行执行GC。
这种处理器常出现在早起java版本及单核处理器中，效率很低
</p></body></html></richcontent></node><node CREATED="1611300460012" ID="ID_2c312a9bafe8" MODIFIED="1611300460012" TEXT="并行GC"><richcontent TYPE="NOTE"><html><head></head><body><p>年轻代和老年代可以并行处理GC，充分利用多核CPU优势。
执行gc期间应用暂停，吞吐性能较好，但可能会出现较长延时问题。</p></body></html></richcontent></node><node CREATED="1611300460012" ID="ID_dc5107987dd7" MODIFIED="1611300460012" TEXT="CMSGC"><richcontent TYPE="NOTE"><html><head></head><body><p>年轻代并行，老年代并发。
在并行GC的基础上又更先进了一步，将GC过程中能和应用程序并发执行的尽量并发执行。</p></body></html></richcontent></node><node CREATED="1611300460012" ID="ID_36805e180289" MODIFIED="1611300460012" TEXT="G1GC"><richcontent TYPE="NOTE"><html><head></head><body><p>基于CMS又作了进一步改造升级。
有两个创新点：
1.不在分成连续的年轻代和老年代，而是分成很多个小块堆区域，小块堆区域一会是年轻代，一会是老年代。
2.提供-XX:MaxGCPauseMillis参数控制GC暂停时间，G1会尽可能地满足这个暂停指标。

G1和CMS比较。
1.G1 GC算法优越，在同等硬件条件下并不是都领先CMS
2. 较小内存在用CMS，较大内存考虑G1，但是还得具体分析</p></body></html></richcontent></node></node></node></node></map>