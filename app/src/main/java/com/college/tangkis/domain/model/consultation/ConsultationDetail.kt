package com.college.tangkis.domain.model.consultation

data class ConsultationDetail(
    val consultationId: String,
    val title: String,
    val uid: String,
    val story: String,
    val progress: String,
    val counselorChoice: String,
    val consultationType: String,
    val date: String,
    val time: String,
    val createdAt: String,
    val updatedAt: String,
)
