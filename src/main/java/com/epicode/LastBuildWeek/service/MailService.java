package com.epicode.LastBuildWeek.service;

import com.epicode.LastBuildWeek.payload.MailModel;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MailService {

    @Autowired
    SendGrid sendGrid;

    // Impostazione mail di SENDGRID : utilizziamo gli oggetti Email e Content di SendGrid
    public String creaMail(MailModel mailModel) throws IOException {

        // Creazione oggetto Mail (sendGrid)
        Email indirizzoMittente = new Email(mailModel.getMittente());
        Email indirizzoDestinatario = new Email(mailModel.getDestinatario());
        Content contenutoMail = new Content("text/plain", mailModel.getContenuto() );
        Mail mailFinale = new Mail(indirizzoMittente, mailModel.getOggetto(), indirizzoDestinatario, contenutoMail);

        // Impostiamo l'oggetto Request (sendGrid) per l'invio della mail
        Request request = new Request();
        request.setEndpoint("mail/send");
        request.setMethod(Method.POST);
        request.setBody(mailFinale.build());

        // INVIO DELLA MAIL
        Response response = sendGrid.api(request);

        return "Mail inviata correttamente";
    }

}
