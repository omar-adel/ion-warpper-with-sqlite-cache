this wrapper makes usage of ion library https://github.com/koush/ion simple and with less code ,
it supports also caching by sqlite using this wonderful repository https://github.com/teabow/android-json-data-cache  


Usage :

1 - for ion library

   in build.gradle add

    compile 'com.koushikdutta.ion:ion:2.+'
    

2 -  copy the following from our library folder to your project  :

   a - IonWrapper.java  

   b - sqlite package 

   c - copy raw to your res folder  " it is used by sqlite package "



3 -  change import statements to be suitable to your package name


4 - you will find

        this.progressBar = (ProgressBar) ((Activity) context).findViewById(R.id.progressBar);
        
in IonWrapper.java

this is because I show ProgressBar while loading

and my layout must contains 

       <ProgressBar
        android:id="@+id/progressBar"
        android:layout_width="60dp"
        android:layout_height="60dp"
        android:layout_centerInParent="true"
        android:indeterminate="true"
        android:visibility="gone"/>
        
        
if you don't want this then simply remove progressBar variable and any usage of it in

IonWrapper.java

5 -  in AndroidManifest.xml

add permissions

    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    
//////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////



I used https://github.com/teabow/android-json-data-cache 

"Json data cache for Android using SQLite " repository

to cache json response

and I made some changes to it to support string response


6- see 

https://github.com/omar-adel/ion-warpper-with-sqlite-cache/blob/master/sample%20-%20weather%20forecast%20app/WeatherForecast/app/src/main/java/com/freelanceapps33/weather/WeatherForecast.java

at sample project to see how integration with activity is done simply .

//////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////


Sample :

there is sample weather forecast app

to show my usage 

it displays weather in the current day and next 3 days

in ListView

if sample doesn't work with you it would because

key of weather api that I use is expired or has high traffic than 

what supposed to be

so go here

https://www.wunderground.com
<<<<<<< HEAD
=======

and register for account 

and get your key to use it
>>>>>>> 2368cebf3c52e4eeccd2f8ba9b297c1b37f1997c

and register for account 

and get your key to use it
