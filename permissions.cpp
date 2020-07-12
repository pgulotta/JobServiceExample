#include "permissions.hpp"
#include <QMessageBox>
#include <QApplication>

#ifdef Q_OS_ANDROID
#include <QAndroidJniObject>
#include <QAndroidJniEnvironment>
#include <QtAndroid>
#include <QAndroidJniObject>
#endif


void Permissions::requestExternalStoragePermission()
{
  #if defined(Q_OS_ANDROID)
  mPermissionGranted = false;
  QtAndroid::PermissionResult request = QtAndroid::checkPermission( "android.permission.WRITE_EXTERNAL_STORAGE" );

  if ( request == QtAndroid::PermissionResult::Denied ) {
    QtAndroid::requestPermissionsSync( QStringList() <<  "android.permission.WRITE_EXTERNAL_STORAGE" );
    request = QtAndroid::checkPermission( "android.permission.WRITE_EXTERNAL_STORAGE" );

    if ( request == QtAndroid::PermissionResult::Denied ) {
      mPermissionGranted = false;

      if ( QtAndroid::shouldShowRequestPermissionRationale( "android.permission.READ_EXTERNAL_STORAGE" ) ) {
        QAndroidJniObject ( "com/example/jobserviceexample/ShowPermissionRationale",
                                                       "(Landroid/app/Activity;)V",QtAndroid::androidActivity().object<jobject>());
        QAndroidJniEnvironment env;

        if ( env->ExceptionCheck() ) {
          env->ExceptionClear();
        }
      }
    } else {
      mPermissionGranted = true;
    }
  } else {
    mPermissionGranted = true;
  }

  #else
  mPermissionGranted = true;
  #endif
}

bool Permissions::getPermissionResult()
{
  return mPermissionGranted;
}
