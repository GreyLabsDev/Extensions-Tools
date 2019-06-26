fun SharedPreferences.putPrimitiveValue(key: String, value: Any): Boolean {
    return when (value) {
        is Boolean -> {
            this.edit().putBoolean(key, value).apply()
            true
        }
        is String -> {
            this.edit().putString(key, value).apply()
            true
        }
        is Int -> {
            this.edit().putInt(key, value).apply()
            true
        }
        is Float -> {
            this.edit().putFloat(key, value).apply()
            true
        }
        is Long -> {
            this.edit().putLong(key, value).apply()
            true
        }
        else -> false
    }
}
