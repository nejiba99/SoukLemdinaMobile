/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.ShippingAddress;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.souklemdina.entities.Panier;
import com.souklemdina.services.PanierServices;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author hatem
 */
public class Paypal {
    BrowserComponent browser = new BrowserComponent();
private Form f;
        private Double prix=0.;

    private Label createForFont(Font fnt, String s) {
        Label l = new Label(s);
        l.getUnselectedStyle().setFont(fnt);
        return l;
    }
    public void affPaypal()
    {
        ArrayList<Panier> lstp = new ArrayList();
    PanierServices ps = new PanierServices();

                 ps.createDB();
         lstp=ps.returnPanier(3);
         for(Panier pan : lstp)         
{
    prix=prix+pan.getPrixTot();
}

f = new Form("paypal",new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        String clientId = "AYSq3RDGsmBLJE-otTkBtM-jBRd1TCQwFf9RGfwddNXWz0uFU9ztymylOhRS";
        String clientSecret = "EGnHDxD_qRPdaLdZz8iCr8N7_MzF-YHPTkjs6NKYQvQSBngp4PTTVWkPZRbL";

       // Set payer details
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");
        

// Set redirect URLs
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:3000/cancel");
        redirectUrls.setReturnUrl("http://localhost:3000/process");
        

// Set payment details
        Details details = new Details();
        details.setShipping("0");
        details.setSubtotal(String.valueOf(prix));
        details.setTax("0");
        

// Payment amount
        Amount amount = new Amount();
        amount.setCurrency("USD");
// Total must be equal to sum of shipping, tax and subtotal.
        amount.setTotal(String.valueOf(prix));
        amount.setDetails(details);

// Transaction information
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription("This is the payment transaction description.");

// Add transaction to a list
        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(transaction);

        // Shipping Address
        ShippingAddress address = new ShippingAddress();
        address.setRecipientName("mourouj")
                .setLine1("111 First Street")
                .setCity("Saratoga")
                .setState("CA")
                .setPostalCode("95070")
                .setCountryCode("US");
        // Item List
        ItemList itemList = new ItemList();
        itemList.setShippingAddress(address);
        transaction.setItemList(itemList);
        
        
// Add payment details
        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setRedirectUrls(redirectUrls);
        payment.setTransactions(transactions);
// Create payment
        try {
            APIContext apiContext = new APIContext(clientId, clientSecret, "sandbox");

            Payment createdPayment = payment.create(apiContext);

            Iterator links = createdPayment.getLinks().iterator();
            while (links.hasNext()) {
                Links link = (Links) links.next();
                if (link.getRel().equalsIgnoreCase("approval_url")) {
                    // REDIRECT USER TO 
                    browser.setURL(link.getHref());
                    //   System.out.println(link.toJSON());
                 /*   HttpRequest req;
                HttpResponse resp;
                System.out.println(req(www.google.com));*/
                   // System.out.println(link.getHref());
              //  System.out.println(createdPayment.getPayer());

                    //System.out.println(link.getHref());
                }
            }

        } catch (PayPalRESTException e) {
            System.err.println(e.getDetails());
        }
        browser.addWebEventListener(browser.onStart, new ActionListener() {

    @Override
    public void actionPerformed(ActionEvent evt) {
       String payId = "";
        if (browser.getURL().toString().startsWith("http://localhost:3000/process"))
        {
            System.out.println("url hédha : "+browser.getURL().toString());
            payId=browser.getURL().toString().substring(40, browser.getURL().toString().indexOf("&"));
        System.out.println("waaaaaaa"+payId);
       payment.setId(payId);
            APIContext apiContext = new APIContext(clientId, clientSecret, "sandbox");

PaymentExecution paymentExecution = new PaymentExecution();
paymentExecution.setPayerId("AVAD6N4Y8CC92");
try {
  Payment createdPayment = payment.execute(apiContext, paymentExecution);
        //AjouterCommandeEtLigneDeCommande acl = new AjouterCommandeEtLigneDeCommande();
        } catch (PayPalRESTException e) {
                          
                            
        AjouterLigneDeCommande acl = new AjouterLigneDeCommande();
                            
}
    
    }}
});
        f.add(BorderLayout.CENTER, browser);
        f.show();
    }
     public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
   /*engine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
        //System.out.println(engine.locationProperty().getValue());
                 String payId = "";

if (engine.locationProperty().getValue().startsWith("http://localhost:3000/process?"))
{
    payId=engine.locationProperty().getValue().substring(40, engine.locationProperty().getValue().indexOf("&"));
        System.out.println("waaaaaaa"+payId);
        
          payment.setId(payId);
            APIContext apiContext = new APIContext(clientId, clientSecret, "sandbox");

PaymentExecution paymentExecution = new PaymentExecution();
paymentExecution.setPayerId("AVAD6N4Y8CC92");
try {
  Payment createdPayment = payment.execute(apiContext, paymentExecution);
 // System.out.println(createdPayment);
  ajouterCommandeEtLigneCommande();
  AnchorPane p = null;
                     try {
                         p = FXMLLoader.load(getClass().getResource("AfficherCommandeFXML.fxml"));
                     } catch (IOException ex) {
                         Logger.getLogger(PaypalFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                     }
       this.anch_pane.getChildren().setAll(p);
} catch (PayPalRESTException e) {
  System.err.println(e.getDetails());
}
ajouterCommandeEtLigneCommande();
FXMLLoader loader = new FXMLLoader();

              
       
       
}   
            
        });*/
}
