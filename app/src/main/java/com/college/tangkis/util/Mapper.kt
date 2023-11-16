package com.college.tangkis.util

import com.college.tangkis.data.source.remote.model.response.activity.ActivityResponse
import com.college.tangkis.data.source.remote.model.response.contact.EmergencyContactResponse
import com.college.tangkis.data.source.remote.model.response.report.AddReportResponse
import com.college.tangkis.data.source.remote.model.response.report.ReportDetailResponse
import com.college.tangkis.data.source.remote.model.response.report.ReportListResponse
import com.college.tangkis.data.source.remote.model.response.user.UserResponse
import com.college.tangkis.domain.model.activity.Activity
import com.college.tangkis.domain.model.contact.EmergencyContact
import com.college.tangkis.domain.model.report.AddReport
import com.college.tangkis.domain.model.report.ReportDetail
import com.college.tangkis.domain.model.report.ReportList
import com.college.tangkis.domain.model.user.User

fun UserResponse.toUser() = User(
    this.nim,
    this.name,
    this.whatsapp,
    this.studyProgram,
    this.password,
    this.salt
)

fun ActivityResponse.toActivity() = Activity(
    this.activityId,
    this.title,
    this.updateDate,
    this.progress
)

fun EmergencyContactResponse.toEmergencyContact() = EmergencyContact(
    this.contactId,
    this.name,
    this.number
)

fun AddReportResponse.toAddReport() = AddReport(
    this.reportId
)

fun ReportListResponse.toReportList() = ReportList(
    this.reportId,
    this.title,
    this.progress,
    this.updatedAt
)

fun ReportDetailResponse.toReportDetail() = ReportDetail(
    this.reportId,
    this.nim,
    this.name,
    this.whatsapp,
    this.story,
    this.isNeedConsultation,
    this.consultationId,
    this.progress,
    this.date
)