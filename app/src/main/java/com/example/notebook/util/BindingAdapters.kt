package com.example.notebook.util


import android.content.res.Resources
import android.net.Uri
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.notebook.R
import java.text.SimpleDateFormat
import java.util.*


class BindingAdapters {



    companion object {
        @JvmStatic
        @BindingAdapter("glide_url")
        fun loadImage(imageView:ImageView, url:String?)
        {
            if(!url.isNullOrEmpty())
            { val mUri = Uri.parse(url)
                Glide.with(imageView.context)
                    .load(mUri)
                    .apply(RequestOptions().error(R.drawable.ic_add_photo))
                    .into(imageView)

            }


        }

        @JvmStatic
        @BindingAdapter("customVisibility")
        fun customVisibility( textView: TextView, isUpdated:Boolean)
        {
            if (isUpdated)
            {
                textView.visibility=View.VISIBLE
            }else{
                textView.visibility=View.GONE
            }
        }
        @JvmStatic
        @BindingAdapter("custom_size")
        fun customImageSize( imageView:ImageView, url:String?)
        {
            if(url.equals(""))
            {
                val dimensionInPixel = 100


             val dimensionInDp = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    dimensionInPixel.toFloat(),
                 Resources.getSystem().displayMetrics
                ).toInt()
                imageView.getLayoutParams().height = dimensionInDp
                imageView.getLayoutParams().width = dimensionInDp
                imageView.setImageResource(R.drawable.ic_add_photo)
                imageView.requestLayout()
            }
            else if(!url.isNullOrEmpty())
            { val mUri = Uri.parse(url)
                Glide.with(imageView.context)
                    .load(mUri)
                    .apply(RequestOptions().error(R.drawable.ic_add_photo))
                    .into(imageView)

            }


        }

        @JvmStatic
        @BindingAdapter("android:parseDate")
        fun getDate(view:TextView,date: Date?)
        {
            val sdf = SimpleDateFormat("dd/M/yyyy")
            val currentDate = sdf.format(Date())
            view.text=currentDate
        }




    }











}