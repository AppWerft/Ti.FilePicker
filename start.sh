#!/bin/bash

APPID=ti.filepicker
VERSION=1.0.0

cd android;ant clean; rm -rf build/*;ant ;  unzip -uo  dist/$APPID-android-$VERSION.zip  -d  ~/Library/Application\ Support/Titanium/;cd ..
cp android/dist/$APPID-android-$VERSION.zip .
