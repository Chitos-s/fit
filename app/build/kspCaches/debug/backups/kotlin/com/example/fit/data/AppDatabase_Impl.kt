package com.example.fit.`data`

import androidx.room.InvalidationTracker
import androidx.room.RoomOpenDelegate
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.TableInfo
import androidx.room.util.TableInfo.Companion.read
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import com.example.fit.`data`.dao.UserActivityDao
import com.example.fit.`data`.dao.UserActivityDao_Impl
import javax.`annotation`.processing.Generated
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class AppDatabase_Impl : AppDatabase() {
  private val _userActivityDao: Lazy<UserActivityDao> = lazy {
    UserActivityDao_Impl(this)
  }

  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(1,
        "1ba4b024e9894073521310093caa0b0e", "a7ea74159fa83098d351bcae40304d0c") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `user_activities` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `activityType` TEXT NOT NULL, `startTime` INTEGER NOT NULL, `endTime` INTEGER, `distance` REAL, `duration` INTEGER)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '1ba4b024e9894073521310093caa0b0e')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `user_activities`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
        internalInitInvalidationTracker(connection)
      }

      public override fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override fun onValidateSchema(connection: SQLiteConnection):
          RoomOpenDelegate.ValidationResult {
        val _columnsUserActivities: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsUserActivities.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUserActivities.put("activityType", TableInfo.Column("activityType", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsUserActivities.put("startTime", TableInfo.Column("startTime", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsUserActivities.put("endTime", TableInfo.Column("endTime", "INTEGER", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUserActivities.put("distance", TableInfo.Column("distance", "REAL", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUserActivities.put("duration", TableInfo.Column("duration", "INTEGER", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysUserActivities: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesUserActivities: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoUserActivities: TableInfo = TableInfo("user_activities", _columnsUserActivities,
            _foreignKeysUserActivities, _indicesUserActivities)
        val _existingUserActivities: TableInfo = read(connection, "user_activities")
        if (!_infoUserActivities.equals(_existingUserActivities)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |user_activities(com.example.fit.model.UserActivity).
              | Expected:
              |""".trimMargin() + _infoUserActivities + """
              |
              | Found:
              |""".trimMargin() + _existingUserActivities)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "user_activities")
  }

  public override fun clearAllTables() {
    super.performClear(false, "user_activities")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(UserActivityDao::class, UserActivityDao_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override
      fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>):
      List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    return _autoMigrations
  }

  public override fun userActivityDao(): UserActivityDao = _userActivityDao.value
}
