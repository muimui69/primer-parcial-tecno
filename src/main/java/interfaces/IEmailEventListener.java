package interfaces;

import java.util.List;

import utils.Email;

public interface IEmailEventListener {
    void onReceiveEmailEvent(List<Email> emails);
}
