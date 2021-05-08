package com.example.examen2.roomDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface ComprasDAO {

    @Insert(onConflict = REPLACE)
    void insert(ComprasRoom comprasRoom);

    @Delete
    void delete(ComprasRoom comprasRoom);

    @Delete
    void reset(List<ComprasRoom> comprasRoomList);

    @Query("UPDATE compras SET imagen = :img WHERE ID = :sID")
    void update(int sID, byte[] img);

    @Query("SELECT * FROM compras WHERE id_Compra =:sID")
    ComprasRoom getByIdCompra(int sID);

    @Query("SELECT * FROM compras")
    List<ComprasRoom> getCompras();
}
