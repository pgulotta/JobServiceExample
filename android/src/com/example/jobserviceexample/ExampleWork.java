package com.example.jobserviceexample;

import android.util.Log;
import android.content.Context;
import android.os.Environment;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class ExampleWork implements Runnable
{
  private static final String TAG = JobServiceExample.TAG;
  public static Context mContext;

  public ExampleWork( Context context )
  {
    mContext = context;
  }

  @Override
  public void run()
  {
    try {
      if ( mContext == null  ) {
        Log.e( TAG, "ExampleWork.run failed.  in null" );
        return;
      }

      doWork( mContext );
    } catch ( Exception e ) {
      e.printStackTrace();
    }
  }

  public static void doWork( Context context )
  {
    try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String textToAppend = dateFormat.format(new Date());
        File path = Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_DOWNLOADS );
        String filename =  path.getAbsolutePath() + File.separatorChar + "JobServiceExampleLog.txt";
        Log.i( TAG, "ExampleWork.doWork path =" + filename + " appending text " + textToAppend);
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
        writer.newLine();
        writer.write(textToAppend);
        writer.close();
    } catch ( IOException e ) {
      e.printStackTrace();
    }
  }



}



