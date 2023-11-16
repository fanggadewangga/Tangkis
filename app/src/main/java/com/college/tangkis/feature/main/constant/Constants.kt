package com.college.tangkis.feature.main.constant

import com.college.tangkis.domain.model.consultation.ConsultationTime
import com.google.android.gms.maps.model.LatLng

object Constants {
    const val ULTKSP_NUMBER = "+628113600584"
    const val BASE_URL = "https://tangkis-api.up.railway.app/"
    val FILKOM_LOCATION = LatLng(-7.954056949829905, 112.61448436435153)
    val CONSULTATION_TIME = listOf(
        ConsultationTime(
            consultationTimeId = "TIME-1",
            startTime = "13.00",
            endTime = "14.00"
        ),
        ConsultationTime(
            consultationTimeId = "TIME-2",
            startTime = "14.00",
            endTime = "15.00"
        ),
        ConsultationTime(
            consultationTimeId = "TIME-3",
            startTime = "15.00",
            endTime = "16.00"
        ),
    )
}
