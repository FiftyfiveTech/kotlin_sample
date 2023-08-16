package com.fiftyfive.nativeandroidtemplate.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for the PhotoDetailEntity table.
 */
@Dao
interface PhotoListDAO {

    /**
     * Observes list of photos.
     *
     * @return all photos.
     */
    @Query("SELECT * FROM photodetailsentity")
    fun getPhotos() : List<PhotoDetailsEntity>

    /**
     * Inserts photos in table
     **/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPhotos(photoList: List<PhotoDetailsEntity>)

    /**
     *  Returns the photo details belonging to the provide id
     *  @param id the photo id
     **/
    @Query("SELECT * FROM photodetailsentity where id=:id")
    fun getPhotoDetail(id:String) : Flow<PhotoDetailsEntity>

    /**
     *  Updates the is_liked status of photo to 1
     *  @param id the photo id
     **/
    @Query("UPDATE photodetailsentity set is_liked = 1 WHERE id=:id")
    fun addToFavorite(id:String) : Int

    /**
     *  Updates the is_liked status of photo to 0
     *  @param id the photo id
     **/
    @Query("UPDATE photodetailsentity set is_liked = 0 WHERE id=:id")
    fun deleteFromFavorite(id:String) : Int

}