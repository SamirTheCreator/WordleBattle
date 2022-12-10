package com.irgashevsamir.javafx_samples;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Player implements Runnable{

    public static ArrayList<Player> players = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String playerUsername;
    private WordleGame wordleGame;

    Player (Socket socket) {
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            players.add(this);
            System.out.println("added");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Player (Socket socket, WordleGame wordleGame) {
        try {
            this.socket = socket;
            this.wordleGame = wordleGame;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            players.add(this);
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void broadcastMessage(String msg) {

        for (Player player : players) {
            try {
                if (!player.playerUsername.equals(playerUsername)) {
                    player.bufferedWriter.write(msg);
                    player.bufferedWriter.newLine();
                    player.bufferedWriter.flush();
                }

            } catch (IOException e) {
                closeEverything(socket, bufferedWriter, bufferedReader);
            }
        }
    }

    public void removePlayer() {
        players.remove(this);
        broadcastMessage("Server: " + this.playerUsername + " left");
    }

    public void closeEverything(Socket s, BufferedWriter bf, BufferedReader br) {
        removePlayer();
        try {
            if (bf != null) {
                bf.close();
            }
            if (br != null) {
                br.close();
            }
            if (s != null) {
                s.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String messageFromPlayer;

        while(socket.isConnected()) {
            try {
                messageFromPlayer = bufferedReader.readLine();
                broadcastMessage(messageFromPlayer);

            } catch (IOException e) {
                closeEverything(socket, bufferedWriter, bufferedReader);
                break;
            }
        }
    }
}
