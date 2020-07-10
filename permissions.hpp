#pragma once


class Permissions
{
public:
  void requestExternalStoragePermission();
  bool getPermissionResult();


private:
  int mPermissionGranted{false};


};

