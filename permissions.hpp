#pragma once


class Permissions
{
public:
  Permissions() = default;
  void requestExternalStoragePermission();
  bool getPermissionResult()const {return mPermissionGranted;}


private:
  int mPermissionGranted{false};


};

