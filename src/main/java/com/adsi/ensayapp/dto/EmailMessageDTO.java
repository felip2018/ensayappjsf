package com.adsi.ensayapp.dto;

public class EmailMessageDTO {
    private StringBuilder structure;
    private String to;
    private String subject;
    private String body;
    
    public EmailMessageDTO(){
        this.structure = new StringBuilder();
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    
    public String getFullMessage(){
        structure.append("<!DOCTYPE html>");
        structure.append("<html>");
        structure.append("<head>");
        structure.append("    <meta charset='utf-8'>");
        structure.append("    <meta http-equiv='X-UA-Compatible' content='IE=edge'>");
        structure.append("    <title>Registro Exitoso</title>");
        structure.append("    <style type='text/css' media='screen'>");
        structure.append("	    @import url('https://fonts.googleapis.com/css2?family=Montserrat&display=swap');");
        structure.append("          @import url('https://fonts.googleapis.com/css2?family=Marck+Script&display=swap');");
        structure.append("          body{background: #c8d6e5;}");
        structure.append("	    .container {width: 600px;margin: auto;padding: 20px;border-radius: 10px;background: #ffffff;}");
        structure.append("	    .btn-activation {padding: 10px;border-radius: 10px;color: #ffffff;background: #9D255C;margin: auto;font-family: 'Montserrat', sans-serif;font-weight: bold;}");
        structure.append("	    a{text-decoration: none;margin: 10px;}");
        structure.append("	    .containerActivationButton{text-align: center;}");
        structure.append("	    p{font-family: 'Montserrat', sans-serif;}!");
        structure.append("	    h1{font-family: 'Marck Script', cursive;font-size: 20pt;}");
        structure.append("    </style>");
        structure.append("</head>");
        structure.append("<body>");
        structure.append("    <div class='container'>");
        structure.append(this.body);
        structure.append("    </div>");
        structure.append("</body>");
        structure.append("</html>");
        
        return structure.toString();
    }
}
