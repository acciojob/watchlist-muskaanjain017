package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class WatchlistRepository {
    List <Movie> mov = new ArrayList<>();
    List <Director> dir = new ArrayList<>();
    Map<Director, List<Movie>> dm = new HashMap<>();

    public String addMovie(Movie movie) {
        for(int i=0; i<mov.size(); i++){
            Movie m = mov.get(i);
            if(movie.getName().equals(m.getName())&& movie.getDurationInMinutes() == m.getDurationInMinutes()&& movie.getImdbRating()==m.getImdbRating()){
                return "Movie details already added";
            }
        }
        mov.add(movie);
        return "Movie added successfully";
    }
    public String addDirector(Director director){
        for(int i=0; i<dir.size(); i++){
            Director d = dir.get(i);
            if(director.getName().equals(d.getName())&& director.getNumberOfMovies()==d.getNumberOfMovies()&&director.getImdbRating()==d.getImdbRating()){
                return "Director details already added";
            }
        }
        dir.add(director);
        return "Director added successfully";
    }
    public String addMovieDirectorPair(String mName, String dName){
        Movie m  = null;
        Director d = null;
        for(int i=0; i<mov.size(); i++){
            if(mov.get(i).getName().equals(mName)){
                m = mov.get(i);
                break;
            }
        }
        for(int i=0; i<dir.size(); i++){
            if(dir.get(i).getName().equals(dName)){
                d = dir.get(i);
                break;
            }
        }
        if(!dm.containsKey(d)){
            List<Movie> dirmov = new ArrayList<>();
            dirmov.add(m);
            dm.put(d, dirmov);
            return "Pair added successfully";
        }
        List<Movie> dirmov = dm.get(d);
        dirmov.add(m);
        dm.put(d, dirmov);
        return "Pair added successfully";
    }
    public Movie getMovieByName(String movieName){
        for(int i=0; i<mov.size();i++){
            if(mov.get(i).getName().equals(movieName)){
                return mov.get(i);
            }
        }
        return null;
    }
    public Director getDirectorByName(String directorName){
        for(int i=0; i<dir.size(); i++){
            if(dir.get(i).getName().equals(directorName)){
                return dir.get(i);
            }
        }
        return null;
    }
    public List<String> getMoviesByDirectorName(String directorName){
        List<Movie> movieList = new ArrayList<>();
        List<String> moviesName = new ArrayList<>();
        Director d = null;
        for(int i=0; i<dir.size(); i++){
            if(dir.get(i).getName().equals(directorName)){
                d = dir.get(i);
                break;
            }
        }
        if(dm.containsKey(d)){
            movieList = dm.get(d);
            for(int i=0; i<movieList.size(); i++) {
                moviesName.add((movieList.get(i)).getName());
            }
        }
        return moviesName;
    }
    public List<String> findAllMovies(){
        List<String> allMovies = new ArrayList<>();
        for(int i=0; i<mov.size(); i++){
            allMovies.add(mov.get(i).getName());
        }
        return allMovies;
    }
    public String deleteDirectorByName(String directorName){
        Director d = null;
        for(int i=0; i<dir.size(); i++){
            if(dir.get(i).getName().equals(directorName)){
                d = dir.get(i);
                break;
            }
        }
        if(dm.containsKey(d)){
            dir.remove(d);
            List<Movie> dirMovObjList = dm.get(d);
            for(int i=0; i<dirMovObjList.size(); i++){
                mov.remove(dirMovObjList.get(i));
            }

            dm.remove(d);
            return "Delete Director details successfully";
        }
        return "Director Detail Not Found";
    }
    public String deleteAllDirectors(){
        for(int i=0; i<dir.size(); i++){
            List<Movie> delAllMovies = dm.get(dir.get(i));
            for(int j=0; j<delAllMovies.size(); j++){
                mov.remove(delAllMovies.get(j));
            }
        }
        dir.clear();
        dm.clear();
        return "All Directors and Movies deleted details successfully";
    }
}
