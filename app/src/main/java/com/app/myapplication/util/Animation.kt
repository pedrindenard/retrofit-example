package com.app.myapplication.util

import android.view.View
import android.view.animation.AnimationUtils
import androidx.navigation.NavOptions
import com.app.myapplication.R

object Animation {

    val animFromRightToLeft = NavOptions.Builder()
        .setEnterAnim(R.anim.anim_from_right)
        .setExitAnim(R.anim.anim_to_left)
        .setPopEnterAnim(R.anim.anim_from_left)
        .setPopExitAnim(R.anim.anim_to_right)
        .build()

    val animFromUpToDown = NavOptions.Builder()
        .setEnterAnim(R.anim.anim_from_up)
        .setExitAnim(R.anim.anim_to_down)
        .setPopEnterAnim(R.anim.anim_from_down)
        .setPopExitAnim(R.anim.anim_to_up)
        .build()

    fun View.startAnimation() {
        startAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_fade_in))
        visibility = View.VISIBLE
    }

    fun View.endAnimation() {
        startAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_fade_out))
        visibility = View.GONE
    }
}