package com.example.fit.`data`.dao

import androidx.lifecycle.LiveData
import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.example.fit.`data`.converters.ActivityTypeConverter
import com.example.fit.model.ActivityType
import com.example.fit.model.UserActivity
import javax.`annotation`.processing.Generated
import kotlin.Double
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class UserActivityDao_Impl(
  __db: RoomDatabase,
) : UserActivityDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfUserActivity: EntityInsertAdapter<UserActivity>

  private val __activityTypeConverter: ActivityTypeConverter = ActivityTypeConverter()

  private val __deleteAdapterOfUserActivity: EntityDeleteOrUpdateAdapter<UserActivity>

  private val __updateAdapterOfUserActivity: EntityDeleteOrUpdateAdapter<UserActivity>
  init {
    this.__db = __db
    this.__insertAdapterOfUserActivity = object : EntityInsertAdapter<UserActivity>() {
      protected override fun createQuery(): String =
          "INSERT OR ABORT INTO `user_activities` (`id`,`activityType`,`startTime`,`endTime`,`distance`,`duration`) VALUES (nullif(?, 0),?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: UserActivity) {
        statement.bindLong(1, entity.id.toLong())
        val _tmp: String = __activityTypeConverter.fromActivityType(entity.activityType)
        statement.bindText(2, _tmp)
        statement.bindLong(3, entity.startTime)
        val _tmpEndTime: Long? = entity.endTime
        if (_tmpEndTime == null) {
          statement.bindNull(4)
        } else {
          statement.bindLong(4, _tmpEndTime)
        }
        val _tmpDistance: Double? = entity.distance
        if (_tmpDistance == null) {
          statement.bindNull(5)
        } else {
          statement.bindDouble(5, _tmpDistance)
        }
        val _tmpDuration: Long? = entity.duration
        if (_tmpDuration == null) {
          statement.bindNull(6)
        } else {
          statement.bindLong(6, _tmpDuration)
        }
      }
    }
    this.__deleteAdapterOfUserActivity = object : EntityDeleteOrUpdateAdapter<UserActivity>() {
      protected override fun createQuery(): String = "DELETE FROM `user_activities` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: UserActivity) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
    this.__updateAdapterOfUserActivity = object : EntityDeleteOrUpdateAdapter<UserActivity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `user_activities` SET `id` = ?,`activityType` = ?,`startTime` = ?,`endTime` = ?,`distance` = ?,`duration` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: UserActivity) {
        statement.bindLong(1, entity.id.toLong())
        val _tmp: String = __activityTypeConverter.fromActivityType(entity.activityType)
        statement.bindText(2, _tmp)
        statement.bindLong(3, entity.startTime)
        val _tmpEndTime: Long? = entity.endTime
        if (_tmpEndTime == null) {
          statement.bindNull(4)
        } else {
          statement.bindLong(4, _tmpEndTime)
        }
        val _tmpDistance: Double? = entity.distance
        if (_tmpDistance == null) {
          statement.bindNull(5)
        } else {
          statement.bindDouble(5, _tmpDistance)
        }
        val _tmpDuration: Long? = entity.duration
        if (_tmpDuration == null) {
          statement.bindNull(6)
        } else {
          statement.bindLong(6, _tmpDuration)
        }
        statement.bindLong(7, entity.id.toLong())
      }
    }
  }

  public override suspend fun insertActivity(activity: UserActivity): Long = performSuspending(__db,
      false, true) { _connection ->
    val _result: Long = __insertAdapterOfUserActivity.insertAndReturnId(_connection, activity)
    _result
  }

  public override suspend fun deleteActivity(activity: UserActivity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __deleteAdapterOfUserActivity.handle(_connection, activity)
  }

  public override suspend fun updateActivity(activity: UserActivity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __updateAdapterOfUserActivity.handle(_connection, activity)
  }

  public override fun getAllActivities(): LiveData<List<UserActivity>> {
    val _sql: String = "SELECT * FROM user_activities ORDER BY startTime DESC"
    return __db.invalidationTracker.createLiveData(arrayOf("user_activities"), false) {
        _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfActivityType: Int = getColumnIndexOrThrow(_stmt, "activityType")
        val _columnIndexOfStartTime: Int = getColumnIndexOrThrow(_stmt, "startTime")
        val _columnIndexOfEndTime: Int = getColumnIndexOrThrow(_stmt, "endTime")
        val _columnIndexOfDistance: Int = getColumnIndexOrThrow(_stmt, "distance")
        val _columnIndexOfDuration: Int = getColumnIndexOrThrow(_stmt, "duration")
        val _result: MutableList<UserActivity> = mutableListOf()
        while (_stmt.step()) {
          val _item: UserActivity
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpActivityType: ActivityType
          val _tmp: String
          _tmp = _stmt.getText(_columnIndexOfActivityType)
          _tmpActivityType = __activityTypeConverter.toActivityType(_tmp)
          val _tmpStartTime: Long
          _tmpStartTime = _stmt.getLong(_columnIndexOfStartTime)
          val _tmpEndTime: Long?
          if (_stmt.isNull(_columnIndexOfEndTime)) {
            _tmpEndTime = null
          } else {
            _tmpEndTime = _stmt.getLong(_columnIndexOfEndTime)
          }
          val _tmpDistance: Double?
          if (_stmt.isNull(_columnIndexOfDistance)) {
            _tmpDistance = null
          } else {
            _tmpDistance = _stmt.getDouble(_columnIndexOfDistance)
          }
          val _tmpDuration: Long?
          if (_stmt.isNull(_columnIndexOfDuration)) {
            _tmpDuration = null
          } else {
            _tmpDuration = _stmt.getLong(_columnIndexOfDuration)
          }
          _item =
              UserActivity(_tmpId,_tmpActivityType,_tmpStartTime,_tmpEndTime,_tmpDistance,_tmpDuration)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getActivityById(activityId: Int): UserActivity? {
    val _sql: String = "SELECT * FROM user_activities WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, activityId.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfActivityType: Int = getColumnIndexOrThrow(_stmt, "activityType")
        val _columnIndexOfStartTime: Int = getColumnIndexOrThrow(_stmt, "startTime")
        val _columnIndexOfEndTime: Int = getColumnIndexOrThrow(_stmt, "endTime")
        val _columnIndexOfDistance: Int = getColumnIndexOrThrow(_stmt, "distance")
        val _columnIndexOfDuration: Int = getColumnIndexOrThrow(_stmt, "duration")
        val _result: UserActivity?
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpActivityType: ActivityType
          val _tmp: String
          _tmp = _stmt.getText(_columnIndexOfActivityType)
          _tmpActivityType = __activityTypeConverter.toActivityType(_tmp)
          val _tmpStartTime: Long
          _tmpStartTime = _stmt.getLong(_columnIndexOfStartTime)
          val _tmpEndTime: Long?
          if (_stmt.isNull(_columnIndexOfEndTime)) {
            _tmpEndTime = null
          } else {
            _tmpEndTime = _stmt.getLong(_columnIndexOfEndTime)
          }
          val _tmpDistance: Double?
          if (_stmt.isNull(_columnIndexOfDistance)) {
            _tmpDistance = null
          } else {
            _tmpDistance = _stmt.getDouble(_columnIndexOfDistance)
          }
          val _tmpDuration: Long?
          if (_stmt.isNull(_columnIndexOfDuration)) {
            _tmpDuration = null
          } else {
            _tmpDuration = _stmt.getLong(_columnIndexOfDuration)
          }
          _result =
              UserActivity(_tmpId,_tmpActivityType,_tmpStartTime,_tmpEndTime,_tmpDistance,_tmpDuration)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteAllActivities() {
    val _sql: String = "DELETE FROM user_activities"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
