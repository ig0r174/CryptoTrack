package com.example.domain.model

import java.sql.Timestamp

data class StatusData(
    val timestamp: Timestamp,
    val error_code: Int,
    val error_message: String?,
    val elapsed: Int,
    val credit_count: Int,
    val notice: String?
)