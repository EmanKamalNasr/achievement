# achievement
it's an app to store your daily achievements [Room Persistence Library]

# Note
* compileSdkVersion,targetSdkVersion 28
* minSdkVersion 24

# Functionality 
Inserting,Updating,Deleting fields using Room Persistence Library.

# Screenshots
![empty](https://user-images.githubusercontent.com/21147699/54227598-f4c33700-4508-11e9-8a01-5f3593f55491.jpg)
![add](https://user-images.githubusercontent.com/21147699/54227633-09073400-4509-11e9-9159-488cf39b1f53.jpg)
![edit](https://user-images.githubusercontent.com/21147699/54227656-158b8c80-4509-11e9-8f8a-b517ddb2d67f.jpg)
![home](https://user-images.githubusercontent.com/21147699/54227692-2936f300-4509-11e9-89ac-298c9d78731d.jpg)
![delete all](https://user-images.githubusercontent.com/21147699/54227715-348a1e80-4509-11e9-8051-9847d0f70aad.jpg)
![deleteallcheck](https://user-images.githubusercontent.com/21147699/54227727-381da580-4509-11e9-9be2-261d7fa09d30.jpg)

# Video 
[Demo](https://youtu.be/7o4swuDHctc)

# Features
* RoomDatabase
* LiveData
* Dao 
* Repository
* Entity
* ViewModel
* MVVM
* RecyclerView
* EmptyRecyclerView
* CardView
* Intent
* Menus
* RecyclerViewAdapter

# All Required Dependencies
* Room Setup
```
    def lifecycle_version = "2.1.0-alpha02"
    def room_version = "2.1.0-alpha04"
    implementation "androidx.lifecycle:lifecycle-extensions:$lifecycle_version"
    implementation "androidx.lifecycle:lifecycle-viewmodel:$lifecycle_version"
    annotationProcessor "androidx.lifecycle:lifecycle-compiler:$lifecycle_version"
    implementation "androidx.room:room-runtime:$room_version"
    annotationProcessor "androidx.room:room-compiler:$room_version"
```
* Design Setup 
```
    implementation "androidx.recyclerview:recyclerview:1.0.0"
    implementation "androidx.cardview:cardview:1.0.0"
    implementation "com.google.android.material:material:1.1.0-alpha04"
```



 
