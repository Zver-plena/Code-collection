Cuckoo Filter
=============

A key-value filter using cuckoo hashing, substituting for bloom filter.
Cuckoo Filter是谷歌提出的一个过滤器算法
论文中提到在一定程度上比bloom filter 更快更优

这个程序是github上找的学习的
程序展示出来的效果是：
1.从input_file.txt不断中截取20的字节内容,生成key放到cuckoo filter中,
	保留在flush中的index,存储在flush	
2.保留的key可以从cuckoo filter中读取index 在从flush中读取完整的内容

应用场景：
1.k.v数据库


Run
-----

```c
make
./cockoo_db input_file.txt output_file.txt
```

Define `CUCKOO_DBG` in cuckoo_filter.h to open debug logging.
