# java 小工具类


cuckoo filter
- com.client
- com.server
通过JNI调用cpp的cuckoo filter
客户端传输数据给服务端，服务端先通过cuckoo filter在存储
   
2. 哈希
- com.common.DigestUtili 
    - RSA: RSA非对称加密,java核心卷2上的内容，对称加密用的还是AES听过被破解了
    - Digest:sha加密,我是copy的以太坊besu的加密部分
    
