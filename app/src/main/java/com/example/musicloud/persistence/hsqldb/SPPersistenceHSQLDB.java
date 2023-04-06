package com.example.musicloud.persistence.hsqldb;
import android.util.Log;

import com.example.musicloud.objects.Playlist;
import com.example.musicloud.objects.SP;
import com.example.musicloud.objects.Song;
import com.example.musicloud.persistence.SPPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SPPersistenceHSQLDB implements SPPersistence {
    private final String dbPath;
    private static boolean wasCreated = false;

    public SPPersistenceHSQLDB (final String dbPath) {
        this.dbPath = dbPath;
//        try{
//            createTable();
//
//            if(wasCreated){
//
//            }
//            else{
//                wasCreated = true;
//                insertDummyData();
//            }
//        }
//        catch(SQLException e){
//            e.printStackTrace();
//        }
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private SP fromResultSet (final ResultSet rs) throws SQLException {

        final int playlistId = rs.getInt(6);
        final String playlistName = rs.getString(7);
        final int songId = rs.getInt(1);
        final String songName = rs.getString(2);

        final Song song = new Song(songId, songName);
        final Playlist playlist = new Playlist (playlistId, playlistName);

        Log.wtf("results", rs.getString(2));

        return new SP(song,playlist);
    }
    @Override
    public List<SP> getSP(String songName) {
        final List<SP> songPlaylists = new ArrayList<>();
        try (final Connection c = connection()){
            final PreparedStatement st = c.prepareStatement("SELECT * FROM playlist, songsPlaylists WHERE playlist.playlist_id=songsPlaylists.playlist_id AND song_id = ?");
            st.setString(1, songName);

            final ResultSet rs = st.executeQuery();

            while (rs.next()){
                final SP record = fromResultSet(rs);
                songPlaylists.add(record);
            }

            rs.close();
            st.close();

        }
        catch (final SQLException e)
        {
            e.printStackTrace();
        }
        return songPlaylists;
    }

    @Override
    public List<SP> getPS(String playlistName) {

        final List<SP> songPlaylists = new ArrayList<>();
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM table_song, " +
                    "songsPlaylists WHERE table_song.id=songsPlaylists.song_id AND playlist_name = ?");
            st.setString(1, playlistName);

            final ResultSet rs = st.executeQuery();

            while (rs.next()) {
                final SP record = fromResultSet(rs);
                Log.wtf("records",record.getPlaylistId()+","+record.getSongID());

                songPlaylists.add(record);
            }

            rs.close();
            st.close();

        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return songPlaylists;
    }


    /**
     * creates table songsPlaylists if it doesn't exists yet
     */
    private void createTable() throws SQLException {
        final Connection conn = connection();
        String query = "CREATE TABLE IF NOT EXISTS songsPlaylists (playlist_id INTEGER , playlist_name VARCHAR(100), song_id INTEGER, song_name VARCHAR(100))";
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(query);
        stmt.close();
    }

    /**
     * inserts dummy data into table
     */
    private void insertDummyData() throws SQLException {
        final Connection conn = connection();
        String query1 = "INSERT INTO songPlaylists VALUES(100, 'name', 101, 'Rain Man')";
        String query2 = "INSERT INTO songPlaylists VALUES(101, 'good playlist', 102, 'Not Enough To Give')";
        String query3 = "INSERT INTO songPlaylists VALUES(102, 'bad playlist', 103, 'Nightfall')";


        Statement stmt = conn.createStatement();
        stmt.executeUpdate(query1);
        stmt.executeUpdate(query2);
        stmt.executeUpdate(query3);
        stmt.close();
    }

    public void deletePairs(Playlist current){
        try (final Connection c = connection()) {
            final PreparedStatement sc = c.prepareStatement("DELETE FROM songsPlaylists WHERE playlist_id = ?");
            sc.setInt(1, current.getId());
            sc.executeUpdate();
            sc.close();
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }
}
