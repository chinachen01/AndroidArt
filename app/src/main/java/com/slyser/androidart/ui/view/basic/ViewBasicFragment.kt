package com.slyser.androidart.ui.view.basic

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import android.view.ViewGroup
import com.slyser.androidart.R
import com.slyser.androidart.TAG
import kotlinx.android.synthetic.main.fragment_view_basic.btn_reset
import kotlinx.android.synthetic.main.fragment_view_basic.btn_show
import kotlinx.android.synthetic.main.fragment_view_basic.btn_translate
import kotlinx.android.synthetic.main.fragment_view_basic.tv_result
import kotlinx.android.synthetic.main.fragment_view_basic.view_sample

/**
 * author: [chenyong](chenyong@danlu.com) version: 1.0.0 since: 2018/9/29 11:34 AM
 *
 * 内容描述区域
 */
class ViewBasicFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_view_basic, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_translate.setOnClickListener {
            view_sample.translationX = 100F
            view.invalidate()
        }
        btn_reset.setOnClickListener {
            view_sample.translationX = 0F
            view.invalidate()
        }
        btn_show.setOnClickListener {
            val content = view_sample.let {
                "top:${it.top}\nleft:${it.left}\ngetY:${it.y}\n" +
                        "getX:${it.x}\ntranslationX:${it.translationX}\ntranslationY:${it.translationY}"
            }
            tv_result.text = content
        }

        val gestureDetector = GestureDetector(activity, object : GestureDetector.SimpleOnGestureListener() {
            override fun onDown(e: MotionEvent?): Boolean {
                return true
            }
        })
        gestureDetector.setOnDoubleTapListener(object : GestureDetector.OnDoubleTapListener {
            override fun onDoubleTap(e: MotionEvent?): Boolean {
                Log.d(TAG, "double tap")
                return false
            }

            override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
                return false
            }

            override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
                return false
            }
        })
        view_sample.setOnTouchListener { v, event ->
            val velocityTracker = VelocityTracker.obtain()
            velocityTracker.addMovement(event)
            velocityTracker.computeCurrentVelocity(1000)
            Log.d(TAG, "xVelocity:${velocityTracker.xVelocity}\nyVelocity:${velocityTracker.yVelocity}")
            velocityTracker.recycle()
            return@setOnTouchListener gestureDetector.onTouchEvent(event)
        }
    }
}