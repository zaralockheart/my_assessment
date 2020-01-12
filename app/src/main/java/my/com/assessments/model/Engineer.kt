package my.com.assessments.model


import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName

@Keep
@Parcelize
data class Engineer(
    @SerialName("engineers")
    val engineers: List<EngineerX?>?
) : Parcelable