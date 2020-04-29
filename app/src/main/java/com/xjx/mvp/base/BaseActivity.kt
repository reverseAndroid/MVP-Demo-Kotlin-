package com.xjx.mvp.base

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xjx.mvp.utils.Retrofit.ApiRetrofit

abstract class BaseActivity<A, V, P : BasePresenter<A, V>> : AppCompatActivity(), BaseViewImp<A> {

    var mPresenter: P? = null
    var mActivity: A? = null
    var mActivityList = ArrayList<Activity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(initContentView())
        mActivity = getCurrentActivity()
        mActivityList.add(mActivity as Activity)
        mPresenter = initPresenter()
        mPresenter?.attachView(mActivity as A, this as V)
    }

    protected abstract fun initContentView(): Int

    protected abstract fun initPresenter(): P

    override fun onDestroy() {
        super.onDestroy()
        mActivityList.remove(mActivity as Activity)
        mPresenter?.detachView()
        mPresenter = null
    }
}