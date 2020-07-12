QT += quick
QT += widgets # support of QMessageBox
android:QT += androidextras # support of QAndroidJniObject

CONFIG += c++1z

DEFINES += QT_DEPRECATED_WARNINGS
DEFINES += QT_DISABLE_DEPRECATED_BEFORE=0x060000

HEADERS += \
    frontcontroller.h \
    permissions.hpp

SOURCES += \
        frontcontroller.cpp \
        main.cpp \
        permissions.cpp

RESOURCES += qml.qrc

DISTFILES += \
    android/AndroidManifest.xml \
    android/build.gradle \
    android/gradle/wrapper/gradle-wrapper.jar \
    android/gradle/wrapper/gradle-wrapper.properties \
    android/gradlew \
    android/gradlew.bat \
    android/res/values/libs.xml \
    android/src/com/example/jobserviceexample/JobServiceExample.java \
    android/src/com/example/jobserviceexample/ExampleJobScheduler.java \
    android/src/com/example/jobserviceexample/ExampleWork.java \

QML_IMPORT_PATH =

QML_DESIGNER_IMPORT_PATH =

# Default rules for deployment.
qnx: target.path = /tmp/$${TARGET}/bin
else: unix:!android: target.path = /opt/$${TARGET}/bin
!isEmpty(target.path): INSTALLS += target


ANDROID_PACKAGE_SOURCE_DIR = $$PWD/android
