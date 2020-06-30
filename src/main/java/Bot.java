import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.ArrayList;
import java.util.List;



public class Bot extends TelegramLongPollingBot {

    private InfRepository repository = new InfRepository();

    public static void main(String[] args){
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try{
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(Message message, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            setButtons(sendMessage);
               execute(sendMessage);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }



    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if(message !=null && message.hasText()){
            switch (message.getText()){
                case "/start":
                    sendMsg(message, "Hello, do you need help? ");
                    break;
                case "/help":
                    sendMsg(message, "");
                    break;
                case "/id":
                    sendMsg(message, repository.getFio(message.getChatId()));
                   // sendMsg(message,);
                    break;
               // case "/adduser":
                 //   sendMsg(message,repository.getStaff());
                   // break;
                case "/test":
                    repository.getName();
                        break;
                case "/getData":
        sendMsg(message,repository.getData(message.getChatId()));
                default:
            }
        }

    }


   public void setButtons(SendMessage sendMessage) {
       ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
       sendMessage.setReplyMarkup(replyKeyboardMarkup);
       replyKeyboardMarkup.setSelective(true);
       replyKeyboardMarkup.setResizeKeyboard(true);
       replyKeyboardMarkup.setOneTimeKeyboard(false);

       List<KeyboardRow> keyboardRowList = new ArrayList<>();
       KeyboardRow keyboardFirstRow = new KeyboardRow();
       //keyboardFirstRow.add(new KeyboardButton("/start "));
       //keyboardFirstRow.add(new KeyboardButton("/help "));
       keyboardFirstRow.add(new KeyboardButton("/id"));
       //keyboardFirstRow.add(new KeyboardButton("/adduser "));
       keyboardFirstRow.add(new KeyboardButton("/test "));
       keyboardFirstRow.add(new KeyboardButton("/getData "));

       keyboardRowList.add(keyboardFirstRow);
       replyKeyboardMarkup.setKeyboard(keyboardRowList);
   }  
    @Override
    public String getBotUsername() {
        return "phoenix_practice_bot";
    }

    @Override
    public String getBotToken() {
        return "1367250041:AAFvjAtyovz_fYoglhWjpBMzPCFZC3rLnhI";
    }
}

