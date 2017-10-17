# MarkerRipple for Google Maps
[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
[![License](https://img.shields.io/badge/License-MIT-blue.svg?style=flat)](http://opensource.org/licenses/MIT)

A Google Map Marker Ripple Library written in java for Android


* Adds Ripple effect to your marker


## Screenshots
<img src="https://github.com/PonnamKarthik/MarkerRipple/raw/master/Screenshots/map.png" width="300">


## Getting started

#### Gradle Dependency (jcenter)

Easily reference the library in your Android projects using this dependency in your module's `build.gradle` file.

```java
dependencies {
    compile 'io.github.ponnamkarthik.mapmarkerripple:mapmarkerripple:1.0.0'
}
```


#### Basic 

## Syntax

```java
Repeater.repeat(CONTENT, COUNT);
```

##Example

```java
Repeater.repeat("text",5);
```

## Syntax

```java
MarkerRipple markerRipple = new MarkerRipple(this, sydney, googleMap) 
                .setRadius(100000) // radius in meters
                .setDuration(2000) // duration in milliseconds
                .setStrokeColor(R.color.colorAccent) // ripple color
                .setStrokeWidth(3f) // width of ripple stroke
                .startRipple();
```


## Working Example

* Check the Sample Project


>   Thanks to
>   Tracy Gipson  ([tracygipson](https://github.com/tracygipson))
