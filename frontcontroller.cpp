#include "frontcontroller.h"
#include <QDebug>

#ifdef Q_OS_ANDROID
#include <QAndroidJniObject>
#include <QAndroidJniEnvironment>
#include <QtAndroid>
#endif


FrontController::FrontController( QObject* parent ) : QObject( parent )
{

}
#ifdef Q_OS_ANDROID
void FrontController::scheduleJobService( int intervalinMS )
{
  qDebug() << Q_FUNC_INFO << intervalinMS;
  QAndroidJniObject::callStaticMethod<void>( "com/example/jobserviceexample/JobServiceExample",
                                             "scheduleJobService",
                                             "(Landroid/content/Context;I)V",
                                              QtAndroid::androidActivity().object(), intervalinMS);

}
#else
void FrontController::scheduleJobService( int intervalinMS )
{
  qDebug() << Q_FUNC_INFO << intervalinMS;
}
#endif
