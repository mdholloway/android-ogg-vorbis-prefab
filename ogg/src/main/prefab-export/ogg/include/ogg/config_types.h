#ifndef OGG_CONFIG_TYPES_H
#define OGG_CONFIG_TYPES_H

#include <stdint.h>
#include <stddef.h>

/* Android/NDK: LP64 on arm64-v8a/x86_64; ILP32 on armeabi-v7a.
   These typedefs match the widths libogg expects. */
typedef int16_t   ogg_int16_t;
typedef uint16_t  ogg_uint16_t;
typedef int32_t   ogg_int32_t;
typedef uint32_t  ogg_uint32_t;
typedef int64_t   ogg_int64_t;
typedef uint64_t  ogg_uint64_t;

/* Pointer-sized signed integer (used occasionally in headers). */
typedef ptrdiff_t ogg_intptr_t;

#endif /* OGG_CONFIG_TYPES_H */

