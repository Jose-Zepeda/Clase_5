package org.example;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.*;

public class Bot extends TelegramLongPollingBot
{

    private List<String> mensajes = new ArrayList<>();
    @Override
    public String getBotUsername() {
        return "P3Jose";
    }

    @Override
    public String getBotToken() {
        return "7159691731:AAEafVH227a0xAM-tnzSwp04L5fiyeUWqGs";
    }

    @Override
    public void onUpdateReceived(Update update) {
        var usuario = update.getMessage().getFrom();
        var mensaje = update.getMessage().getText();
        var id = usuario.getId();

        mensajes.add(usuario + ":" + mensaje);

        if (mensaje.contains("desplegar") && mensajes.size() > 0) {
            String lista = "";
            for (String mensaje1 : mensajes) {
                lista += mensaje1 + "\n";
            }
            sendText(id, lista);
        }
        System.out.println("id: " + usuario.getId() + " nombre: " + usuario.getFirstName() + "apellido" + usuario.getLastName() + " mensaje: " + mensaje);

        sendText(id, mensaje);

  }
    public void sendText(Long who, String what){
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString()) //Who are we sending a message to
                .text(what).build();    //Message content
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }
}
