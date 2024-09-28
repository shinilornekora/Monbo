import android.os.Parcel
import android.os.Parcelable

data class UserCredential(val data: Map<String, String>) : Parcelable {
    constructor(parcel: Parcel) : this(
        (parcel.readHashMap(String::class.java.classLoader) as? Map<*, *>)?.mapKeys { it.key as String }
            ?.mapValues { it.value as String } ?: emptyMap()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeMap(data)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<UserCredential> {
        override fun createFromParcel(parcel: Parcel): UserCredential {
            return UserCredential(parcel)
        }

        override fun newArray(size: Int): Array<UserCredential?> {
            return arrayOfNulls(size)
        }
    }
}
