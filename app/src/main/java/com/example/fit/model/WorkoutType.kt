package com.example.fit.model

enum class ActivityType(val displayName: String, val emoji: String) {
    BICYCLE("Велосипед", "🚲"),
    RUNNING("Бег", "🏃"),
    SWIMMING("Плавание", "🏊"),
    YOGA("Йога", "🧘"),
    WORKOUT("Тренировка", "💪"),
    WALKING("Ходьба", "🚶"),
    SURFING("Сёрфинг", "🏄");

    fun getFullName(): String = "$displayName $emoji"
} 