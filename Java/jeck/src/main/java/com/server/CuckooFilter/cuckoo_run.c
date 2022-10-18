#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <string.h>
#include <sys/stat.h>

#include "cuckoo_filter.h"
#include "mozilla-sha1/sha1.h"

void run(char *input, char *output)
{
        SHA_CTX c;
        // 检测文件信息
        struct stat st;
        uint32_t key_num;
        uint8_t *keys;
        uint8_t **sha1_key;
        uint8_t value[DAT_LEN], *v;
        int bytes, i, j;
        FILE *f1, *f2;

        // rb ：read binary 
        f1 = fopen(input, "rb");
        if (f1 == NULL) {
                fprintf(stderr, "Fail to open %s!\n", input);
                exit(-1);
        }
        stat(input, &st);
        // wb+ : write and read  binary 
        f2 = fopen(output, "wb+");
        if (f2 == NULL) {
                fprintf(stderr, "Fail to open %s!\n", output);
                exit(-1);
        }

        /* Initialization */
        cuckoo_filter_init(st.st_size);
        
        /* Allocate SHA1 key space */
        key_num = next_pow_of_2(st.st_size) / DAT_LEN;
        keys = malloc(key_num * 20);
        sha1_key = malloc(key_num * sizeof(void *));
        if (!keys || !sha1_key) {
                fprintf(stderr, "Out of memory!\n");
                exit(-1);
        }
        for (i = 0; i < key_num; i++) {
                sha1_key[i] = keys + i * 20;
        }

        /* 每次读取*/
        i = 0;
        do {
                memset(value, 0, DAT_LEN);
                bytes = fread(value, 1, DAT_LEN, f1);
                SHA1_Init(&c);
                SHA1_Update(&c, value, bytes);      
                SHA1_Final(sha1_key[i], &c);
                cuckoo_filter_put(sha1_key[i], value);
                i++;
        } while (bytes == DAT_LEN);

        /* Real key number */
        key_num = i;
        printf("Total %u records.\n", key_num);

        /* Deletion test */
        // for (i = 0; i < key_num; i += 2) {
        //         cuckoo_filter_put(sha1_key[i], NULL);
        // }

        fseek(f1, 0, SEEK_SET);
        for (i = 0; i < key_num; i++) {
                memset(value, 0, DAT_LEN);
                bytes = fread(value, 1, DAT_LEN, f1);
                if (!(i & 0x1)) {
                        cuckoo_filter_put(sha1_key[i], value);
                }
        }

        /* Get logs on flash and write them into a new file. */
        for (j = 0; j < key_num; j++) {
                v = cuckoo_filter_get(sha1_key[j]);
                if (v != NULL) {
                        memcpy(value, v, DAT_LEN);
                        fwrite(value, 1, DAT_LEN, f2);
                }
        }

        fclose(f1);
        fclose(f2);

        free(keys);
        free(sha1_key);
        printf("执行完成run方法---\n");
//        return 0;
}
