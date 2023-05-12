package com.example.gameslibrary.detail

import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailGamesViewModel() : ViewModel(), Parcelable {

    constructor(parcel: Parcel) : this() {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {

    }

    companion object CREATOR : Parcelable.Creator<DetailGamesViewModel> {
        override fun createFromParcel(parcel: Parcel): DetailGamesViewModel {
            return DetailGamesViewModel(parcel)
        }

        override fun newArray(size: Int): Array<DetailGamesViewModel?> {
            return arrayOfNulls(size)
        }
    }
}