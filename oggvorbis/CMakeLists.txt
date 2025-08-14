cmake_minimum_required(VERSION 3.28)
project(ogg_vorbis_android LANGUAGES C)

if(POLICY CMP0057)
	cmake_policy(SET CMP0057 NEW)
endif()

set(CMAKE_BUILD_TYPE Release CACHE STRING "" FORCE)
set(BUILD_SHARED_LIBS ON CACHE BOOL "Build shared libs" FORCE)

set(OGG_LIBRARY     ${CMAKE_SOURCE_DIR}/../third_party/ogg)
set(OGG_INCLUDE_DIR ${CMAKE_SOURCE_DIR}/../third_party/ogg/include)
set(VORBIS_LIBRARY  ${CMAKE_SOURCE_DIR}/../third_party/vorbis)

# option(BUILD_SHARED_LIBS "Build shared libs" ON)

add_subdirectory(${OGG_LIBRARY} ${CMAKE_BINARY_DIR}/ogg EXCLUDE_FROM_ALL)
set_target_properties(ogg PROPERTIES POSITION_INDEPENDENT_CODE ON)

add_subdirectory(${VORBIS_LIBRARY} ${CMAKE_BINARY_DIR}/vorbis EXCLUDE_FROM_ALL)

