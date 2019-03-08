# achievement
it's an app to store your daily achievements [Room Persistence Library]

# Note
* compileSdkVersion,targetSdkVersion 28
* minSdkVersion 21 

# Functionality 
Inserting,Updating,Deleting fields using Room Persistence Library.

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



 
