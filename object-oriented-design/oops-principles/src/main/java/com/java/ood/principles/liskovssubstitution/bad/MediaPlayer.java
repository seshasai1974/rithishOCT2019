package com.java.ood.principles.liskovssubstitution.bad;

/**
 * Example media player super class which has ability to play video and audio
 * 
 * @author seshasai
 */
public class MediaPlayer {
	
	// Play audio implementation
	public void playAudio() {
		System.out.println("Playing audio...");
	}

	// Play video implementation
	public void playVideo() {
		System.out.println("Playing video...");
	}
}

