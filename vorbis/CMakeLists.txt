cmake_minimum_required(VERSION 3.22.1)
project(vorbis_prefab LANGUAGES C)

if(POLICY CMP0057)
	cmake_policy(SET CMP0057 NEW)
endif()

set(CMAKE_BUILD_TYPE Release CACHE STRING "" FORCE)
set(BUILD_SHARED_LIBS ON CACHE BOOL "Build shared libs" FORCE)
set(VORBIS_LIBRARY  ${CMAKE_SOURCE_DIR}/../third_party/vorbis)
set(CMAKE_FIND_PACKAGE_PREFER_CONFIG=ON)

find_package(ogg REQUIRED CONFIG)

# --- Bridge to FindOgg-style variables expected by some builds ----
# Includes
get_target_property(_OGG_INCLUDES ogg::ogg INTERFACE_INCLUDE_DIRECTORIES)
if(NOT _OGG_INCLUDES)
  message(FATAL_ERROR "ogg::ogg has no INTERFACE_INCLUDE_DIRECTORIES")
endif()

# Library path (handle single- vs multi-config)
get_target_property(_OGG_LIB ogg::ogg IMPORTED_LOCATION)
if(NOT _OGG_LIB)
  # try config-specific location (e.g., Release/Debug) if needed
  get_target_property(_OGG_LIB ogg::ogg "IMPORTED_LOCATION_${CMAKE_BUILD_TYPE}")
endif()
if(NOT _OGG_LIB)
  # last-ditch: some generators set NOCONFIG
  get_target_property(_OGG_LIB ogg::ogg IMPORTED_LOCATION_NOCONFIG)
endif()
if(NOT _OGG_LIB)
  message(FATAL_ERROR "Could not resolve IMPORTED_LOCATION for ogg::ogg")
endif()

# Populate the legacy cache vars the Vorbis CMake expects
set(OGG_INCLUDE_DIR "${_OGG_INCLUDES}" CACHE PATH "Ogg include dir (from prefab)")
set(OGG_LIBRARY     "${_OGG_LIB}"      CACHE FILEPATH "Ogg library (from prefab)")
set(OGG_LIBRARIES   "${OGG_LIBRARY}"   CACHE FILEPATH "Ogg libraries")
set(OGG_FOUND       TRUE               CACHE BOOL "Ogg found via prefab bridge")

# ---------------------------------------------------------------

add_subdirectory(${VORBIS_LIBRARY} ${CMAKE_BINARY_DIR}/vorbis EXCLUDE_FROM_ALL)

