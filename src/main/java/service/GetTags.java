package service;

import dao.MusicDao;
import entity.Music;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by misha on 21.05.17.
 * get tags from mp3 file and save it to DB
 */

public class GetTags {

    Music music = new Music();
    MusicDao musicDao = new MusicDao();
    GetDate getDate = new GetDate();

    public static String songName = "";
    public static String genre = "";
    public static String singer = "";
    public static String composer = "";
    public static String album = "";

    public void saveAngGetTagsFromMP3(File mp3File) throws Exception{

        InputStream input = new FileInputStream(mp3File);
        ContentHandler handler = new DefaultHandler();
        Metadata metadata = new Metadata();
        Parser parser = new Mp3Parser();
        ParseContext parseCtx = new ParseContext();
        parser.parse(input, handler, metadata, parseCtx);
        input.close();

        String[] metadataNames = metadata.names();

        for(String name : metadataNames){
            System.out.println(name + ": " + metadata.get(name));
        }

        System.out.println("----------------------------------------------");
        System.out.println("Title: " + metadata.get("title"));
        System.out.println("Artists: " + metadata.get("xmpDM:artist"));
        System.out.println("Composer : "+metadata.get("xmpDM:composer"));
        System.out.println("Genre : "+metadata.get("xmpDM:genre"));
        System.out.println("Album : "+metadata.get("xmpDM:album"));

        songName = metadata.get("title");
        genre = metadata.get("xmpDM:genre");
        singer = metadata.get("xmpDM:artist");
        composer = metadata.get("xmpDM:composer");
        album = metadata.get("xmpDM:album");

        long fileSizeInBytes = mp3File.length();
        long fileSizeInKB = fileSizeInBytes / 1024;
        long fileSizeInMB = fileSizeInKB / 1024;

        music.setSong_name(metadata.get("title"));
        music.setDate(getDate.getToday());
        music.setGenre(metadata.get("xmpDM:genre"));
        music.setSinger(metadata.get("xmpDM:artist"));
        music.setSize(fileSizeInMB);

        musicDao.save(music);
    }
}