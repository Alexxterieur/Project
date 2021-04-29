package musichub.business;

import java.io.Serializable;
import java.util.*;

import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
	
public class JMusicHub implements Serializable
{
 	public void Hub(){

		MusicHub theHub = new MusicHub ();
		
		System.out.println("Type h for available commands");
		
		
		Scanner scan = new Scanner(System.in);
		String choice = scan.nextLine();
		
		String albumTitle = null;
		
		if (choice.length() == 0) System.exit(0);						
		
		while (choice.charAt(0)!= 'q') 	{
			switch (choice.charAt(0)) 	{
				case 'h':
					printAvailableCommands();
					choice = scan.nextLine();
				break;
				case 't':
					//album titles, ordered by date
					System.out.println(theHub.getAlbumsTitlesSortedByDate());
					printAvailableCommands();
					choice = scan.nextLine();
				break;
				case 'g':
					//songs of an album, sorted by genre
					System.out.println("Songs of an album sorted by genre will be displayed; enter the album name, available albums are:");
					System.out.println(theHub.getAlbumsTitlesSortedByDate());
					
					albumTitle = scan.nextLine();
					try {
						System.out.println(theHub.getAlbumSongsSortedByGenre(albumTitle));
					} catch (NoAlbumFoundException ex) {
						System.out.println("No album found with the requested title " + ex.getMessage());
					}
					printAvailableCommands();
					choice = scan.nextLine();
				break;
				case 'd':
					//songs of an album
					System.out.println("Songs of an album will be displayed; enter the album name, available albums are:");
					System.out.println(theHub.getAlbumsTitlesSortedByDate());
					
					albumTitle = scan.nextLine();
					try {
						System.out.println(theHub.getAlbumSongs(albumTitle));
					} catch (NoAlbumFoundException ex) {
						System.out.println("No album found with the requested title " + ex.getMessage());
					}
					printAvailableCommands();
					choice = scan.nextLine();
				break;
				case 'u':
					//audiobooks ordered by author
					System.out.println(theHub.getAudiobooksTitlesSortedByAuthor());
					printAvailableCommands();
					choice = scan.nextLine();
				break;
				default:
				
				break;
			}
		}
		scan.close();
	}
	
	private static void printAvailableCommands() {
		System.out.println("t: display the album titles, ordered by date");
		System.out.println("g: display songs of an album, ordered by genre");
		System.out.println("d: display songs of an album");
		System.out.println("u: display audiobooks ordered by author");
		System.out.println("q: quit program");
	}
}