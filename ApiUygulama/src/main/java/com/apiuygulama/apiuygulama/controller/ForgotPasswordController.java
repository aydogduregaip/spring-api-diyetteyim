package com.apiuygulama.apiuygulama.controller;

import com.apiuygulama.apiuygulama.exception.UserNotFoundException;
import com.apiuygulama.apiuygulama.security.services.UserService;
import com.apiuygulama.apiuygulama.model.User;

import io.swagger.annotations.Api;
import net.bytebuddy.utility.RandomString;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URI;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/account")
@Api(value = "ForgotPasswordController API documentation")
public class ForgotPasswordController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserService userService;

    private String scheme;
    private String serverName;
    private int serverPort;

    @GetMapping("/forgot_password")
    public String showForgotPasswordForm() {
        return "forgot_password_form";

    }

    public static String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    @PostMapping("/forgot_password")
    public String processForgotPassword(HttpServletRequest request, Model model) {
        scheme = request.getScheme();             // http
        serverName = request.getServerName();     // hostname.com
        serverPort = request.getServerPort();        // 80

        String email = request.getParameter("email");
        String token = RandomString.make(30);

        try {
            userService.updateResetPasswordToken(token, email);
            String resetPasswordLink = scheme + "://" + "localhost" + ":" + 5500 +"/reset_password_form.html?token=" + token;
            //String resetPasswordLink = getSiteURL(request) + "/api/account/reset_password?token=" + token;
            sendEmail(email, resetPasswordLink);
            model.addAttribute("message", "We have sent a reset password link to your email. Please check.");

        } catch (UserNotFoundException ex) {
            model.addAttribute("error", ex.getMessage());
        } catch (UnsupportedEncodingException | MessagingException e) {
            model.addAttribute("error", "Error while sending email");
        }

        return "forgot_password_form";
    }

    public void sendEmail(String recipientEmail, String link)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("calorienoreply@gmail.com", "Calorie Support");
        helper.setTo(recipientEmail);

        String subject = "Parolanızı Sıfırlayın";

        String content = "<p>Merhaba,</p>"
                + "<p>Parolanızı sıfırlama isteğinde bulundunuz.</p>"
                + "<p>Link'e tıklayarak parolanızı değiştirebilirsiniz:</p>"
                + "<p><a href=\"" + link + "\">Parolamı Değiştir</a></p>"
                + "<br>"
                + "<p>Bu isteği siz yapmadıysanız bu e-postayı dikkate almayınız</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }

    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        User customer = userService.getByResetPasswordToken(token);
        model.addAttribute("title", "Reset your password");

        if (customer == null) {
            model.addAttribute("message", "Invalid Token");
            return "message";
        } else {
            userService.updatePassword(customer, password);

            model.addAttribute("message", "You have successfully changed your password.");
        }

        return "message";
    }
}
