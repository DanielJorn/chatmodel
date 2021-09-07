package daniel.chatmodel.base.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun bindImageUrl(imageView: ImageView, imageUrl: String) {
    Picasso.get()
        .load(imageUrl)
        .into(imageView)
}