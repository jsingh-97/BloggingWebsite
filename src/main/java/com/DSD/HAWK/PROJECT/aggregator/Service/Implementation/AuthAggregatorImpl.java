package com.DSD.HAWK.PROJECT.aggregator.Service.Implementation;


import com.DSD.HAWK.PROJECT.aggregator.Request.SignUpRequest;
import com.DSD.HAWK.PROJECT.aggregator.Response.Auth.AuthResponse;
import com.DSD.HAWK.PROJECT.aggregator.Service.AuthAggregator;
import com.DSD.HAWK.PROJECT.lib.EmailSender;
import com.DSD.HAWK.PROJECT.model.ConfirmationToken;
import com.DSD.HAWK.PROJECT.model.UserData;
import com.DSD.HAWK.PROJECT.repository.Service.ConfirmationTokenService;
import com.DSD.HAWK.PROJECT.repository.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.logging.Logger;


@Service
public class AuthAggregatorImpl implements AuthAggregator {
    @Autowired
    UserService userService;
    @Autowired
    ConfirmationTokenService confirmationTokenService;
    @Autowired
    EmailSender emailSender;
    @Value("${APP_BASE_URL}")
    private String baseUrl;
    public AuthResponse getUserDetails(String email,String password){
        AuthResponse authResponse = new AuthResponse();

        Boolean emailExists = userService.emailExists(email);
        if (emailExists){
            UserData userData = userService.findByEmail(email);
            if(!password.equals(userData.getPassword())){
                authResponse.setIsLogged(false);
                authResponse.setText("Invalid username and password");
            }
            else if(userData.getEnabled()==true) {
                authResponse.setIsLogged(true);
                authResponse.setText("Welcome");
            }
            else{
                authResponse.setIsLogged(false);
                authResponse.setText("Please verify your email to complete registration process or try registering again if the link has expired");
            }
        }
        else{
            authResponse.setIsLogged(false);
            authResponse.setText("Invalid username and password");
        }
        return authResponse;
    }
    public  AuthResponse registerUser(SignUpRequest signUpRequest) {
        Boolean userExists = userService.emailExists(signUpRequest.getEmail());
        if(userExists==true) {
            generateAndSendToken(signUpRequest);
            return new AuthResponse("This User is already registered. Check your email to complete the registration or login if you have already completed");
        }
        UserData userDataNew = new UserData(signUpRequest.getName(), signUpRequest.getEmail(), signUpRequest.getPassword(), signUpRequest.getPhone(), signUpRequest.getCity(),false);
        try {
            userService.registerUser(userDataNew);
        }
        catch (Exception e) {
            return new AuthResponse("User registration failed Error : "+e.getMessage())  ;
        }
        generateAndSendToken(signUpRequest);
        return new AuthResponse("Please verify your email address to complete the registration process");
    }

    private void generateAndSendToken(SignUpRequest signUpRequest) {
        String token = UUID.randomUUID().toString();
        System.out.println("Token generated : "+ token);
        //generating token for session
        ConfirmationToken confirmationToken=new ConfirmationToken(token, LocalDateTime.now(),LocalDateTime.now().plusMinutes(10),null,signUpRequest.getEmail());
        confirmationTokenService.addToken(confirmationToken);
        //sending email to user
        String link = baseUrl+"/auth/user/confirmToken?token=" + token;
        try {
            emailSender.send(signUpRequest.getEmail(),buildEmail(signUpRequest.getName(),link));
        }catch (Exception ex){
            System.out.println("An exception occured while sending an email : "+ex);
        }

    }


    @Override
    public AuthResponse deleteUser(String email) {
        userService.deleteByEmail(email);
        return new AuthResponse("Your account is deleted successfully");
    }

    @Override
    public String confirmToken(String token) {
        try{
            ConfirmationToken confirmationToken = confirmationTokenService.getToken(token);
            if(confirmationToken==null)
                return "Token not found.Try registering again";
            if(confirmationToken.getConfirmedAt()!=null)
                return "Email is already verified";
            if(confirmationToken.getExpiresAt().isBefore(LocalDateTime.now())) {
                userService.deleteByEmail(confirmationToken.getEmail());
                return "This link is expired.Try registering again";
            }
            confirmationTokenService.setConfirmedAt(token);
            userService.verifyUser(confirmationToken.getEmail());
            return "confirmed";

        }catch (Exception e){
            return "Exception occured  err: "+e;
        }

    }
    private String buildEmail(String name,String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 5 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }
}