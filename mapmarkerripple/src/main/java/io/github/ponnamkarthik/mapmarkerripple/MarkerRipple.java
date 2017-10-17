package io.github.ponnamkarthik.mapmarkerripple;

import android.animation.IntEvaluator;
import android.animation.ValueAnimator;
import android.location.Location;
import android.support.annotation.IntegerRes;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by ponna on 17-10-2017.
 */

public class MarkerRipple {

    int radius = 10000;
    float strokeWidth = 3f;

    ValueAnimator vAnimator;

    @IntegerRes
    int strokeColor = R.color.purple;

    Circle circle;
    Location location;
    GoogleMap googleMap;

    int duration = 2000;

    public MarkerRipple(LatLng latLng, GoogleMap googleMap) {
        if(latLng != null) {
            this.location = new Location("");
            location.setLatitude(latLng.latitude);
            location.setLongitude(latLng.longitude);
        } else {
            throw new NullPointerException("LatLng cannot be Null");
        }
        if(googleMap != null) {
            this.googleMap = googleMap;
        } else {
            throw  new NullPointerException("GoogleMap cannot be Null");
        }
    }

    public MarkerRipple setDuration(int duration) {
        if(duration > 0) {
            this.duration = duration;
        }
        return this;
    }

    public MarkerRipple setRadius(int radius) {
        if(radius > 0) {
            this.radius = radius;
        }
        return this;
    }

    public MarkerRipple setStrokeWidth(float strokeWidth) {
        if(strokeWidth > 0) {
            this.strokeWidth  = strokeWidth;
        }
        return this;
    }

    public MarkerRipple setStrokeColor (@IntegerRes int strokeColor) {
        this.strokeColor = strokeColor;
        return this;
    }

    public MarkerRipple startRipple() {
        if(googleMap != null) {
            if(vAnimator != null) {
                if(vAnimator.isRunning()) {
                    vAnimator.removeAllListeners();
                    vAnimator.end();
                    vAnimator.cancel();
                }
            }
            if(circle != null) {
                circle.setCenter(new LatLng(location.getLatitude(), location.getLongitude()));
            } else {
                circle = googleMap.addCircle(new CircleOptions().center(new LatLng(location.getLatitude(), location.getLongitude()))
                        .strokeColor(strokeColor)
                        .strokeWidth(strokeWidth)
                        .radius(radius));
            }

            if(vAnimator == null) {
                vAnimator = new ValueAnimator();
                vAnimator.setRepeatCount(ValueAnimator.INFINITE);
                vAnimator.setRepeatMode(ValueAnimator.RESTART);
                vAnimator.setIntValues(0, 100);
                vAnimator.setDuration(duration);
                vAnimator.setEvaluator(new IntEvaluator());
                vAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                vAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float animatedFraction = valueAnimator.getAnimatedFraction();
                        circle.setRadius(animatedFraction * 1000);
                    }
                });
                vAnimator.start();
            } else {
                vAnimator.start();
            }
        } else {
            throw new NullPointerException("Null Object (GoogleMap)") ;
        }

        return this;
    }

    public void clearRipple() {
        if(vAnimator.isRunning()) {
            vAnimator.removeAllListeners();
            vAnimator.end();
            vAnimator.cancel();
        }
    }

}
