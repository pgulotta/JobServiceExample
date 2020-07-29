#include "permissions.hpp"
#include <QDebug>
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
    mPermissionGranted = ( request == QtAndroid::PermissionResult::Denied ) ? false : true;
  } else {
    mPermissionGranted = true;
  }

  #else
  mPermissionGranted = true;
  #endif
  qInfo() << Q_FUNC_INFO << " Permission granted = " << mPermissionGranted;
}


