package sms;

import com.learning.webatm.model.SMS;

public class SMSSender {

    private SMS sms;

    public SMSSender(SMS sms) {
        this.sms = sms;
    }

    public void send(){
        System.out.println(sms);
    }

    public SMS getSms() {
        return sms;
    }

    public void setSms(SMS sms) {
        this.sms = sms;
    }
}
