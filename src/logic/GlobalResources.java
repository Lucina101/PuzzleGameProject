package logic;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class GlobalResources {

	/// Audio
	public static MediaPlayer backgroundMusicPlayer;
	
	public static MediaPlayer backgroundMusicPlayerV2;

	public static AudioClip moveSound;
	
	public static AudioClip clickSound;
	
	public static GameController gameController;
	
	public static boolean onMap = false;
	
	public static Image[] cuteRandomGIF = new Image[7];
	/// Audio probably take long time to load due to the file size
	
	static {		
		
		moveSound = new AudioClip(ClassLoader.getSystemResource("Audio/moveSound.wav").toString());
		clickSound = new AudioClip(ClassLoader.getSystemResource("Audio/mouseClickSound.wav").toString());
		
		Media bgmMedia = new Media(ClassLoader.getSystemResource("Audio/calmBackgroundSound.mp3").toString());
		
		backgroundMusicPlayer = new MediaPlayer(bgmMedia);
		
		Media bgmMediaV2 = new Media(ClassLoader.getSystemResource("Audio/kawaikereba.mp3").toString());
		backgroundMusicPlayerV2 = new MediaPlayer(bgmMediaV2);
		
		backgroundMusicPlayerV2.setOnEndOfMedia(new Runnable() {
			public void run() {
				backgroundMusicPlayer.seek(Duration.ZERO);
			}
		});
		
		backgroundMusicPlayer.setOnEndOfMedia(new Runnable() {
		       public void run() {
		         backgroundMusicPlayer.seek(Duration.ZERO);
		       }
		   });
	}
	
	static  {
		for (int i = 0 ; i < 7 ; ++ i) {
			String url = ClassLoader.getSystemResource("Image/randomCuteGif" + i + ".gif").toString();
			Image cuteGIF = new Image(url, 350, 300, false , false);
			cuteRandomGIF[i] = cuteGIF;
		}
	}
	
	public static Image getRandomGIF() {
		Random random = new Random();
		int rnd  = random.nextInt(21) % 7;
		return cuteRandomGIF[rnd];
		
	}
	
	public static void rebuildController() {
		gameController = new GameController();
	}
	


}
