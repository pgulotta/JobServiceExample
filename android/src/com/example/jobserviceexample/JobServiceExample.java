package com.example.jobserviceexample;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;
import android.content.Context;
import android.content.Intent;


public final class JobServiceExample extends JobService
{
  public static final String TAG = "com.example.jobserviceexample";

    public static void scheduleJobService( Context context,  int intervalinMS)
    {
        Log.i( TAG, "JobServiceExample.scheduleJobService with the intervalinMS = " + intervalinMS );
        context.startService( new Intent( context, JobServiceExample.class ) );

        try {
          ExampleJobScheduler.scheduleJob(context, intervalinMS );
        } catch ( Exception e ) {
          e.printStackTrace();
        }
    }


    @Override
    public boolean onStartJob( JobParameters jobParameters )
    {
        Log.i( TAG, "JobServiceExample.onStartJob : jobParameters.getJobId() = " + jobParameters.getJobId() );

        try {
          Thread thread = new Thread( new ExampleWork( this ) );
          thread.start();
          thread.join();
        } catch ( Exception e ) {
          e.printStackTrace();
        }

        return false;  // Returns false from when job has finished. onStopJob will not be invoked
    }


    @Override
    public boolean onStopJob( JobParameters jobParameters )
    {
        // This method is typically not invoked
        Log.i( TAG, "JobServiceExample.onStopJob : jobParameters.getJobId() = " + jobParameters.getJobId() );
        return false;  // Returns false to end the job entirely
    }


}


