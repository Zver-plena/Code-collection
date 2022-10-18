/*
 * @Autor: Zver
 * @Date: 2021-01-04 00:16:30
 * @LastEditTime: 2022-09-06 05:25:47
 */
/*
 * Copyright (C) 2015, Leo Ma <begeekmyfriend@gmail.com>
 */

#ifndef _CUCKOO_FILTER_H_
#define _CUCKOO_FILTER_H_

#define CUCKOO_DBG

/* Configuration */

// 页大小
#define SECTOR_SIZE    (1 << 5)

// DAI_LEN 数据大小，减去sha1之后
// 每次读取的数据大小
#define DAT_LEN        (SECTOR_SIZE - 20)  /* minus sha1 size */
#define ASSOC_WAY      (4)  /* 4-way association */
#define INVALID_OFFSET (-1)

/* Cuckoo hash */
#define force_align(addr, size)  ((void *)((((uintptr_t)(addr)) + (size) - 1) & ~((size) - 1)))
// size_t = unsigned long = 4 byte = 32 bit
#define cuckoo_hash_lsb(key, count)  (((size_t *)(key))[0] & (count - 1))
#define cuckoo_hash_msb(key, count)  (((size_t *)(key))[1] & (count - 1))

/* Flash driver interfaces. */
#define flash_align(addr)  (!((uintptr_t)(addr) & (SECTOR_SIZE - 1)))
#define flash_read(addr)  (*(volatile uint8_t *)(addr))
#define flash_write(addr, byte)  (*(volatile uint8_t *)(addr) = (byte))
#define flash_sector_erase(addr) \
        do { \
                uint32_t __i; \
                volatile uint8_t *__addr = (volatile uint8_t *)(addr); \
                for (__i = 0; __i < SECTOR_SIZE; __i++) { \
                        *(volatile uint8_t *)__addr = 0xff; \
                        __addr++; \
                } \
        } while (0)

/* The log entries store key-value pairs on flash and
 * each entry is assumed just one sector size fit.
 */

// 数据[k,v] 其中k是20字节的sha1
struct log_entry {
        uint8_t sha1[20];
        uint8_t data[DAT_LEN];
};

enum { AVAILIBLE, OCCUPIED, DELETED, };

/* The in-memory hash buckets cache filter keys (which are assumed SHA1 values)
 * via cuckoo hashing function and map them to log entries stored on flash.
 */

// 哈希表的slot
struct hash_slot_cache {
        uint32_t tag : 30;  // 哈希值
        uint32_t status : 2;  // 三种状态值 
        uint32_t offset;  // 在flash中的偏移位置
};

// 判断是2的幂
static inline int is_pow_of_2(uint32_t x)
{
	return !(x & (x-1));
        printf("HELLO\n");
}

// 变成2的幂
static inline uint32_t next_pow_of_2(uint32_t x)
{
	if (is_pow_of_2(x))
		return x;
	x |= x>>1;
	x |= x>>2;
	x |= x>>4;
	x |= x>>8;
	x |= x>>16;
	return x + 1;
}

int cuckoo_filter_init(size_t size);
uint8_t *cuckoo_filter_get(uint8_t *key);
void cuckoo_filter_put(uint8_t *key, uint8_t *value);

#endif /* _CUCKOO_FILTER_H_ */
