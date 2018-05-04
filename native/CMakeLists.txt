CMAKE_MINIMUM_REQUIRED(VERSION 2.6)

PROJECT(windows-file-chooser)

if (NOT WIN32)
    message(FATAL_ERROR "Windows only.")
endif ()

FIND_PACKAGE(JNI REQUIRED)
FIND_PACKAGE(MFC REQUIRED)

set(CMAKE_CXX_FLAGS_RELEASE "/MT")

set(CMAKE_CXX_FLAGS_DEBUG "/MTd")

add_definitions(-D_UNICODE)
add_definitions(-DUNICODE)

SET(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} /W3 /O1 /EHsc")

include_directories(${JNI_INCLUDE_DIRS})

set(SOURCE_FILES consulo_internal_windows_WindowsFileDialog.cpp consulo_internal_windows_WindowsFileDialog.h VistaThumbnailDialogHandler.cpp VistaThumbnailDialogHandler.h XPFolderDialog.cpp XPThumbnailDialog.cpp XPThumbnailDialog.h XPFolderDialog.h)

if (CMAKE_CL_64)
    add_definitions(-DOS_ARCH_X64)

    add_library(windows-file-chooser64 SHARED ${SOURCE_FILES})
else ()
    add_definitions(-DOS_ARCH_X86)

    add_library(windows-file-chooser SHARED ${SOURCE_FILES})
endif ()

