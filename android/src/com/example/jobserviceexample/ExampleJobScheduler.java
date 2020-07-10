package com.example.jobserviceexample;

import android.util.Log;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;


public final class ExampleJobScheduler
{
  private static final String TAG = JobServiceExample.TAG;
  private static final int JOB_ID =  4321;

  private static void handleJob (Context context, long intervalinMS)
  {
    if ( context == null ) {
      Log.e ( TAG, "ExampleJobScheduler.handleJob context is null" );
      return;
    }

    try {
      ComponentName serviceComponent = new ComponentName( context, JobServiceExample.class );

      if ( serviceComponent == null ) {
        Log.e ( TAG, "ExampleJobScheduler.handleJob serviceComponent = null " );
        return;
      }

      JobScheduler jobScheduler = context.getSystemService( JobScheduler.class );

      if ( jobScheduler == null ) {
        Log.e ( TAG, "ExampleJobScheduler.handleJob jobScheduler = null " );
        return;
      }

      jobScheduler.cancel( JOB_ID );

      if ( intervalinMS == 0 ) {
        Log.i ( TAG, "ExampleJobScheduler.handleJob cancelled " );
        return;
      }

      JobInfo.Builder builder = new JobInfo.Builder( JOB_ID, serviceComponent );

      if ( builder == null ) {
        Log.e ( TAG, "builder.scheduleJob builder = null " );
        return;
      }

      builder.setPeriodic( intervalinMS );  // job should run within the provided intervalinMS; API 21
      builder.setPersisted( true ); // persist this job across device reboots; API 21
      builder.setRequiredNetworkType( JobInfo.NETWORK_TYPE_ANY ); // requires network connectivity; API 21
      builder.setRequiresDeviceIdle(false); // the job is runnable even when someone is interacting with the device; API 21
      int result = jobScheduler.schedule( builder.build() );
      String resultText = ( JobScheduler.RESULT_SUCCESS == result ) ? "successfully" : "failed";
      Log.i ( TAG, "ExampleJobScheduler.handleJob scheduled for intervalinMS of " + intervalinMS + " is "  + resultText );

    } catch ( Exception e ) {
      e.printStackTrace();
    }
  }

   public static void scheduleJob (Context context, int intervalinMS )
   {
     handleJob(context, intervalinMS );
   }


}
