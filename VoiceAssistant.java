package com.example.VoiceAssistant;

import java.io.IOException;
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

public class VoiceAssistant {
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");

        // IMPORTANT: Use resource paths if your files are in arc/main/resources
        config.setDictionaryPath("resource:/5243.dic");
        config.setLanguageModelPath("resource:/5243.lm");

        try {
            LiveSpeechRecognizer speech = new LiveSpeechRecognizer(config);
            speech.startRecognition(true);

            SpeechResult speechResult;

            while ((speechResult = speech.getResult()) != null) {
                String voiceCommand = speechResult.getHypothesis();
                System.out.println("Voice Command is " + voiceCommand);

                if (voiceCommand.equalsIgnoreCase("Open Chrome")) {
                    Runtime.getRuntime().exec("cmd.exe /c start chrome");
                } else if (voiceCommand.equalsIgnoreCase("Open YouTube")) {
                    Runtime.getRuntime().exec("cmd.exe /c start chrome www.youtube.com");
                } else if (voiceCommand.equalsIgnoreCase("Close Chrome")) {
                    Runtime.getRuntime().exec("cmd.exe /c TASKKILL /IM chrome.exe");
                } else if (voiceCommand.equalsIgnoreCase("Exit Program")) {
                    System.out.println("Exiting program...");
                    break;
                }
            }

            speech.stopRecognition();
            System.exit(0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
