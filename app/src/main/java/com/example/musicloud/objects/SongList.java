package com.example.musicloud.objects;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class SongList {

    //array containing Size objects
    //integer indicating number of elements in the array
    private Song[] songs ;
    private int num;

    public SongList(){
        //intialize the array with 100 elements
        songs = new Song[100];
        num = 0;
    }

    //getter to return the arrays of Song objects
    public Song[] getSongs(){
        return songs;
    }

    //getter for number of songs in the list
    public int getNum(){
        return num;
    }

    //to add a new Song at the end
    public void addSong(Song newSong){
        //case 1: array has remaining spots
        if(num < songs.length){
            songs[num] = newSong;
            num++;
        }
        else{
            //case when no empty spot
            //creates a new array of double the size and adds
            //all the old songs and the new song
            Song[] temp = new Song[num*2];

            System.arraycopy(songs, 0, temp, 0, num);
            temp[num] = newSong;
            num++;
            songs = temp;
        }
    }//addSong ends here

    //method remove song using name and artist and albumName
    public void removeSong(String songName, String artistName, String albumName){
        int indexToRemove = -1;
        for(int i=0;i<num;i++){
            if(songs[i].isEqual(songName, artistName, albumName)){
                indexToRemove = i;
            }
        }

        //if song found to remove
        if(indexToRemove != -1){

            //remove it using loop
            for(int i= indexToRemove;i<num-1;i++){
                songs[i] = songs[i+1];
            }

            songs[num] = null;
            num--;
        }
    }//removeSong ends here

    //to return song at index i
    public Song getSong(int i){
        if(i<num){
            return songs[i];
        }
        else{
            return null;
        }
    }//getSong ends here

    //to return the summary
    @NonNull
    public String toString(){
        StringBuilder ans = new StringBuilder();

        for(int i=0;i<num;i++){
            ans.append(songs[i]).append("\n");
        }

        ans.append("This is the summary of the songs object\nNumber of Songs:").append(num);
        return ans.toString();
    }

    //method to sort the songs using the name
    public void sortSongs(){
        for(int i=0;i < num;i++){
            for(int j=i+1;j<num-1;j++){
                if(songs[i].compareTo(songs[j]) > 0){
                    Song temp = songs[i];
                    songs[i] = songs[j];
                    songs[j] = temp;
                }
            }
        }
    }

    //method to get an arrayList of all song names
    public ArrayList<String> getAllSongs(){
        ArrayList<String> ans = new ArrayList<>();

        for(int i=0;i<num;i++){
            ans.add(songs[i].getSongName());
        }

        return ans;
    }

}
