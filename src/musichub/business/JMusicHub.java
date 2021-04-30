package musichub.business;

import java.io.Serializable;
import java.util.*;

import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
	
public class JMusicHub implements Serializable
{


	public void ClientHub(){

		MusicHub theHub = new MusicHub();
		System.out.println("Type h for available commands");
		
		
		Scanner scan = new Scanner(System.in);
		String choice = scan.nextLine();
		
		String albumTitle = null;
		
		if (choice.length() == 0) System.exit(0);						
		
		while (choice.charAt(0)!= 'q') 	{
			switch (choice.charAt(0)) 	{
				case 'h':
					printAvailableCommandsClient();
					choice = scan.nextLine();
				break;
				case 't':
					//album titles, ordered by date
					System.out.println(theHub.getAlbumsTitlesSortedByDate());
					printAvailableCommandsClient();
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
					printAvailableCommandsClient();
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
					printAvailableCommandsClient();
					choice = scan.nextLine();
				break;
				case 'u':
					//audiobooks ordered by author
					System.out.println(theHub.getAudiobooksTitlesSortedByAuthor());

					choice = scan.nextLine();
				break;
				default:

				break;
			}
		}
		scan.close();
	}

	public void ServerHub() {
		MusicHub theHub2 = new MusicHub();
		System.out.println("Type h for available commands");

		Scanner scan2 = new Scanner(System.in);
		String choice2 = scan2.nextLine();

		if (choice2.length() == 0) System.exit(0);

		while (choice2.charAt(0) != 'q') {
			switch (choice2.charAt(0)) {
				case 'h':
					printAvailableCommandsServer();
					choice2 = scan2.nextLine();
					break;
				case 'c':
					// add a new song
					System.out.println("Enter a new song: ");
					System.out.println("Song title: ");
					String title = scan2.nextLine();
					System.out.println("Song genre (jazz, classic, hiphop, rock, pop, rap):");
					String genre = scan2.nextLine();
					System.out.println("Song artist: ");
					String artist = scan2.nextLine();
					System.out.println("Song length in seconds: ");
					int length = Integer.parseInt(scan2.nextLine());
					System.out.println("Song content: ");
					String content = scan2.nextLine();
					Song s = new Song(title, artist, length, content, genre);
					theHub2.addElement(s);
					System.out.println("New element list: ");
					Iterator<AudioElement> it = theHub2.elements();
					while (it.hasNext()) System.out.println(it.next().getTitle());
					System.out.println("Song created!");
					printAvailableCommandsServer();
					choice2 = scan2.nextLine();
					break;
				case 'a':
					// add a new album
					System.out.println("Enter a new album: ");
					System.out.println("Album title: ");
					String aTitle = scan2.nextLine();
					System.out.println("Album artist: ");
					String aArtist = scan2.nextLine();
					System.out.println("Album length in seconds: ");
					int aLength = Integer.parseInt(scan2.nextLine());
					System.out.println("Album date as YYYY-DD-MM: ");
					String aDate = scan2.nextLine();
					Album a = new Album(aTitle, aArtist, aLength, aDate);
					theHub2.addAlbum(a);
					System.out.println("New list of albums: ");
					Iterator<Album> ita = theHub2.albums();
					while (ita.hasNext()) System.out.println(ita.next().getTitle());
					System.out.println("Album created!");
					printAvailableCommandsServer();
					choice2 = scan2.nextLine();
					break;
				case '+':
					//add a song to an album:
					System.out.println("Add an existing song to an existing album");
					System.out.println("Type the name of the song you wish to add. Available songs: ");
					Iterator<AudioElement> itae = theHub2.elements();
					while (itae.hasNext()) {
						AudioElement ae = itae.next();
						if (ae instanceof Song) System.out.println(ae.getTitle());
					}
					String songTitle = scan2.nextLine();

					System.out.println("Type the name of the album you wish to enrich. Available albums: ");
					Iterator<Album> ait = theHub2.albums();
					while (ait.hasNext()) {
						Album al = ait.next();
						System.out.println(al.getTitle());
					}
					String titleAlbum = scan2.nextLine();
					try {
						theHub2.addElementToAlbum(songTitle, titleAlbum);
					} catch (NoAlbumFoundException ex) {
						System.out.println(ex.getMessage());
					} catch (NoElementFoundException ex) {
						System.out.println(ex.getMessage());
					}
					System.out.println("Song added to the album!");
					printAvailableCommandsServer();
					choice2 = scan2.nextLine();
					break;
				case 'l':
					// add a new audiobook
					System.out.println("Enter a new audiobook: ");
					System.out.println("AudioBook title: ");
					String bTitle = scan2.nextLine();
					System.out.println("AudioBook category (youth, novel, theater, documentary, speech)");
					String bCategory = scan2.nextLine();
					System.out.println("AudioBook artist: ");
					String bArtist = scan2.nextLine();
					System.out.println("AudioBook length in seconds: ");
					int bLength = Integer.parseInt(scan2.nextLine());
					System.out.println("AudioBook content: ");
					String bContent = scan2.nextLine();
					System.out.println("AudioBook language (french, english, italian, spanish, german)");
					String bLanguage = scan2.nextLine();
					AudioBook b = new AudioBook(bTitle, bArtist, bLength, bContent, bLanguage, bCategory);
					theHub2.addElement(b);
					System.out.println("Audiobook created! New element list: ");
					Iterator<AudioElement> itl = theHub2.elements();
					while (itl.hasNext()) System.out.println(itl.next().getTitle());
					printAvailableCommandsServer();
					choice2 = scan2.nextLine();
					break;
				case 'p':
					//create a new playlist from existing elements
					System.out.println("Add an existing song or audiobook to a new playlist");
					System.out.println("Existing playlists:");
					Iterator<PlayList> itpl = theHub2.playlists();
					while (itpl.hasNext()) {
						PlayList pl = itpl.next();
						System.out.println(pl.getTitle());
					}
					System.out.println("Type the name of the playlist you wish to create:");
					String playListTitle = scan2.nextLine();
					PlayList pl = new PlayList(playListTitle);
					theHub2.addPlaylist(pl);
					System.out.println("Available elements: ");

					Iterator<AudioElement> itael = theHub2.elements();
					while (itael.hasNext()) {
						AudioElement ae = itael.next();
						System.out.println(ae.getTitle());
					}
					while (choice2.charAt(0) != 'n') {
						System.out.println("Type the name of the audio element you wish to add or 'n' to exit:");
						String elementTitle = scan2.nextLine();
						try {
							theHub2.addElementToPlayList(elementTitle, playListTitle);
						} catch (NoPlayListFoundException ex) {
							System.out.println(ex.getMessage());
						} catch (NoElementFoundException ex) {
							System.out.println(ex.getMessage());
						}

						System.out.println("Type y to add a new one, n to end");
						choice2 = scan2.nextLine();
					}
					System.out.println("Playlist created!");
					printAvailableCommandsServer();
					choice2 = scan2.nextLine();
					break;
				case '-':
					//delete a playlist
					System.out.println("Delete an existing playlist. Available playlists:");
					Iterator<PlayList> itp = theHub2.playlists();
					while (itp.hasNext()) {
						PlayList p = itp.next();
						System.out.println(p.getTitle());
					}
					String plTitle = scan2.nextLine();
					try {
						theHub2.deletePlayList(plTitle);
					} catch (NoPlayListFoundException ex) {
						System.out.println(ex.getMessage());
					}
					System.out.println("Playlist deleted!");
					printAvailableCommandsServer();
					choice2 = scan2.nextLine();
					break;
				case 's':
					//save elements, albums, playlists
					theHub2.saveElements();
					theHub2.saveAlbums();
					theHub2.savePlayLists();
					System.out.println("Elements, albums and playlists saved!");
					printAvailableCommandsServer();
					choice2 = scan2.nextLine();
					break;
					default:

					break;
			}
		}
		scan2.close();
	}

	private static void printAvailableCommandsClient() {
		System.out.println("t: display the album titles, ordered by date");
		System.out.println("g: display songs of an album, ordered by genre");
		System.out.println("d: display songs of an album");
		System.out.println("u: display audiobooks ordered by author");
		System.out.println("q: quit program");
	}

	private static void printAvailableCommandsServer(){
		System.out.println("c: add a new song");
		System.out.println("a: add a new album");
		System.out.println("+: add a song to an album");
		System.out.println("l: add a new audiobook");
		System.out.println("p: create a new playlist from existing songs and audio books");
		System.out.println("-: delete an existing playlist");
		System.out.println("s: save elements, albums, playlists");
	}
}