package com.android.androidstudy.fragment

import android.graphics.drawable.Drawable
import android.view.View
import com.android.androidstudy.R
import com.android.customview.fragment.BaseFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.fragment_second.*
import java.lang.reflect.Field
import java.lang.reflect.Method


/**
 * <p>描述:  </p>
 * <p>详细描述:  </p>
 * <p>创建时间: 2020/4/22 2:38 PM</p>
 *
 * @author 1578325085@qq.com
 * @version v1.0.0
 */
class SecondFragment : BaseFragment() {

    override fun getLayoutId(): Int = R.layout.fragment_second

    override fun initView(view: View) {

    }

    override fun initListener(view: View) {

    }

    override fun initData() {
        Glide.with(image1).load(R.drawable.once)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return true
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    if (resource != null && resource is GifDrawable) {
                        try {
                            (resource as GifDrawable)?.apply {
                                var gifStateField: Field =
                                    GifDrawable::class.java.getDeclaredField("state")
                                gifStateField.isAccessible = true
                                var gifStateClass =
                                    Class.forName("com.bumptech.glide.load.resource.gif.GifDrawable\$GifState")
                                var gifFrameLoaderField: Field =
                                    gifStateClass.getDeclaredField("frameLoader")
                                gifFrameLoaderField.isAccessible = true
                                var gifFrameLoaderClass =
                                    Class.forName("com.bumptech.glide.load.resource.gif.GifFrameLoader")
                                var gifDecoderField: Field =
                                    gifFrameLoaderClass.getDeclaredField("gifDecoder")
                                gifDecoderField.isAccessible = true
                                var gifDecoderClass =
                                    Class.forName("com.bumptech.glide.gifdecoder.GifDecoder")
                                var gifDecoder =
                                    gifDecoderField.get(
                                        gifFrameLoaderField.get(
                                            gifStateField.get(
                                                resource
                                            )
                                        )
                                    )
                                var getDelayMethod: Method = gifDecoderClass.getDeclaredMethod(
                                    "getDelay", Int::class.java
                                )
                                getDelayMethod.isAccessible = true
                                //设置只播放一次
                                setLoopCount(1)
                                //获得总帧数
                                var count: Int = resource.frameCount
                                var delay: Long = 0
                                var i = 0
                                for (i in 0 until count) {
                                    //计算每一帧所需要的时间进行累加
                                    delay += getDelayMethod.invoke(gifDecoder, i) as Int
                                }
                                image1.postDelayed({
                                    stop()
                                }, delay)
                            }
                        } catch (e: Exception) {
                            e.printStackTrace();
                        }
                    }
                    return false;
                }
            }).into(image1)
        Glide.with(image2).load(R.drawable.forever).into(image2)
    }
}