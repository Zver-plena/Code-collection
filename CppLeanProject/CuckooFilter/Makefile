CC=gcc
CCFLAG=-g -Wall

SHA=./mozilla-sha1/sha1.o
SHA_H=./mozilla-sha1/sha1.h

OUTPUT=main

OBJS=main.o\
 cuckoo_filter.o\
  ${SHA}\

all: ${OUTPUT}

${OUTPUT}: ${OBJS}
	${CC} ${CCFLAG}  -o ${OUTPUT} ${OBJS}



main.o: cuckoo_filter.h ${LIB_H}
cuckoo_filter.o: cuckoo_filter.h





# 如果没有这个伪目标，那么如果有clean文件夹make clean会失效
.PHONY:	clean
clean:
	-rm ${OBJS}


