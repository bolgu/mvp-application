#!/bin/bash

echo "#################################"
project_path=~/MVPS/mvp-application/app-web/ionic-client
android_sdk_path=~/Library/Android/sdk/build-tools/22.0.1
android_adb_path=~/Library/Android/sdk/platform-tools
app_name=ItJobsBoard.apk

echo "#################################"
echo $project_path
echo "#################################"
echo $android_sdk_path

cd $project_path
cordova build --release android
cd $project_path

#apk
cp $project_path/platforms/android/build/outputs/apk/android-release-unsigned.apk .
jarsigner -verbose -sigalg SHA1withRSA -digestalg SHA1 -keystore jobsboard.keystore android-release-unsigned.apk jobsboard -storepass Password123!
echo $android_sdk_path/zipalign
$android_sdk_path/zipalign -vf 4 android-release-unsigned.apk android-$app_name

$android_adb_path/adb -d install -r $project_path/android-$app_name
