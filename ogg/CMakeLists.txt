cmake_minimum_required(VERSION 3.22.1)
project(ogg_prefab LANGUAGES C)

set(CMAKE_BUILD_TYPE Release CACHE STRING "" FORCE)
set(BUILD_SHARED_LIBS ON CACHE BOOL "Build shared libs" FORCE)

set(OGG_LIBRARY     ${CMAKE_SOURCE_DIR}/../third_party/ogg)

add_subdirectory(${OGG_LIBRARY} ${CMAKE_BINARY_DIR}/ogg EXCLUDE_FROM_ALL)

